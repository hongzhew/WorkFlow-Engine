package net.risesoft.controller.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.risesoft.api.itemadmin.position.Item4PositionApi;
import net.risesoft.consts.UtilConsts;
import net.risesoft.model.itemadmin.ItemModel;
import net.risesoft.y9.Y9LoginUserHolder;
import net.risesoft.y9.json.Y9JsonUtil;
import net.risesoft.y9.util.Y9Util;

/**
 * 获取app应用接口
 *
 * @author 10858
 *
 */
@RestController
@RequestMapping("/mobile/item")
public class MobileItemController {

    @Autowired
    private Item4PositionApi item4PositionApi;

    /**
     * 获取事项列表
     *
     * @param tenantId 租户id
     * @param userId 人员id
     * @param positionId 岗位id
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/getItemList")
    public void getItemList(@RequestHeader("auth-tenantId") String tenantId, @RequestHeader("auth-userId") String userId, @RequestHeader("auth-positionId") String positionId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resMap = new HashMap<String, Object>(16);
        try {
            Y9LoginUserHolder.setTenantId(tenantId);
            List<Map<String, Object>> listMap = item4PositionApi.getItemList(tenantId, positionId);
            List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> app : listMap) {
                Map<String, Object> map = new HashMap<String, Object>(16);
                map.put("itemId", app.get("url"));
                map.put("itemName", app.get("name"));
                ItemModel itemModel = item4PositionApi.getByItemId(tenantId, (String)app.get("url"));
                map.put("appIcon", StringUtils.isBlank(itemModel.getIconData()) ? "" : itemModel.getIconData());
                map.put("processDefinitionKey", itemModel.getWorkflowGuid());
                resList.add(map);
            }
            resMap.put(UtilConsts.SUCCESS, true);
            resMap.put("msg", "获取数据成功");
            resMap.put("itemList", listMap);
        } catch (Exception e) {
            resMap.put(UtilConsts.SUCCESS, false);
            resMap.put("msg", "获取数据异常");
            e.printStackTrace();
        }
        Y9Util.renderJson(response, Y9JsonUtil.writeValueAsString(resMap));
        return;
    }
}