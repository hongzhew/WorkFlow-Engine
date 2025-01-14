package net.risesoft.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import net.risesoft.api.itemadmin.ItemLinkApi;
import net.risesoft.api.platform.permission.PositionRoleApi;
import net.risesoft.entity.ItemLinkBind;
import net.risesoft.entity.ItemLinkRole;
import net.risesoft.entity.ItemNodeLinkBind;
import net.risesoft.entity.LinkInfo;
import net.risesoft.model.itemadmin.LinkInfoModel;
import net.risesoft.pojo.Y9Result;
import net.risesoft.repository.jpa.ItemLinkBindRepository;
import net.risesoft.repository.jpa.ItemLinkRoleRepository;
import net.risesoft.repository.jpa.ItemNodeLinkBindRepository;
import net.risesoft.repository.jpa.LinkInfoRepository;
import net.risesoft.y9.Y9LoginUserHolder;
import net.risesoft.y9.util.Y9BeanUtil;

/**
 * 事项链接接口
 *
 * @author zhangchongjie
 * @date 2024/05/14
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/services/rest/itemLink", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemLinkApiImpl implements ItemLinkApi {

    private final PositionRoleApi positionRoleApi;

    private final ItemLinkBindRepository itemLinkBindRepository;

    private final ItemNodeLinkBindRepository itemNodeLinkBindRepository;

    private final LinkInfoRepository linkInfoRepository;

    private final ItemLinkRoleRepository itemLinkRoleRepository;

    /**
     * 获取有权限的事项绑定链接
     *
     * @param tenantId 租户id
     * @param orgUnitId 人员、岗位id
     * @param itemId 事项id
     * @return {@code Y9Result<List<LinkInfoModel>>} 通用请求返回对象 - data 是事项绑定链接
     * @since 9.6.6
     */
    @Override
    public Y9Result<List<LinkInfoModel>> getItemLinkList(String tenantId, String orgUnitId, String itemId) {
        Y9LoginUserHolder.setTenantId(tenantId);
        List<LinkInfoModel> linkList = new ArrayList<>();
        List<ItemLinkBind> list = itemLinkBindRepository.findByItemIdOrderByCreateTimeDesc(itemId);
        for (ItemLinkBind bind : list) {
            List<ItemLinkRole> roleList = itemLinkRoleRepository.findByItemLinkId(bind.getId());
            if (roleList.isEmpty()) {// 未配置角色，没权限
                continue;
            }
            for (ItemLinkRole linkRole : roleList) {
                boolean b = positionRoleApi.hasRole(tenantId, linkRole.getRoleId(), orgUnitId).getData();
                if (b) {
                    LinkInfo linkInfo = linkInfoRepository.findById(bind.getLinkId()).orElse(null);
                    LinkInfoModel model = new LinkInfoModel();
                    Y9BeanUtil.copyProperties(linkInfo, model);
                    linkList.add(model);
                    break;
                }
            }
        }
        return Y9Result.success(linkList);
    }

    /**
     * 获取有权限的节点绑定链接
     *
     * @param tenantId 租户id
     * @param orgUnitId 人员、岗位id
     * @param itemId 事项id
     * @param processDefinitionId 流程定义id
     * @param taskDefKey 节点id
     * @return {@code Y9Result<LinkInfoModel>} 通用请求返回对象 - data 是节点绑定链接
     * @since 9.6.6
     */
    @Override
    public Y9Result<LinkInfoModel> getItemNodeLinkList(String tenantId, String orgUnitId, String itemId,
        String processDefinitionId, String taskDefKey) {
        Y9LoginUserHolder.setTenantId(tenantId);
        LinkInfoModel model = null;
        ItemNodeLinkBind bind = itemNodeLinkBindRepository.findByItemIdAndProcessDefinitionIdAndTaskDefKey(itemId,
            processDefinitionId, taskDefKey);
        if (bind != null) {
            List<ItemLinkRole> roleList = itemLinkRoleRepository.findByItemLinkId(bind.getId());
            if (roleList.isEmpty()) {// 未配置角色，所有人权限
                model = new LinkInfoModel();
                LinkInfo linkInfo = linkInfoRepository.findById(bind.getLinkId()).orElse(null);
                Y9BeanUtil.copyProperties(linkInfo, model);
                return Y9Result.success(model);
            }
            for (ItemLinkRole linkRole : roleList) {
                boolean b = positionRoleApi.hasRole(tenantId, linkRole.getRoleId(), orgUnitId).getData();
                if (b) {
                    model = new LinkInfoModel();
                    LinkInfo linkInfo = linkInfoRepository.findById(bind.getLinkId()).orElse(null);
                    Y9BeanUtil.copyProperties(linkInfo, model);
                    break;
                }
            }
            return Y9Result.success(model);
        }
        return Y9Result.success();
    }

}
