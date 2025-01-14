package net.risesoft.api.itemadmin;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.risesoft.model.itemadmin.CommonSentencesModel;
import net.risesoft.pojo.Y9Result;

/**
 * 常用语接口
 *
 * @author qinman
 * @author zhangchongjie
 * @date 2022/12/19
 */
public interface CommonSentencesApi {

    /**
     * 删除常用语
     *
     * @param tenantId 租户id
     * @param id 常用语id
     * @return {@code Y9Result<Object>} 通用请求返回对象
     * @since 9.6.6
     */
    @PostMapping("/delete")
    Y9Result<Object> delete(@RequestParam("tenantId") String tenantId, @RequestParam("id") String id);

    /**
     * 获取常用语
     *
     * @param tenantId 租户id
     * @param userId 人员id
     * @return {@code Y9Result<List<CommonSentencesModel>>} 通用请求返回对象 - data 是常用语列表
     * @since 9.6.6
     */
    @GetMapping("/listSentencesService")
    Y9Result<List<CommonSentencesModel>> listSentencesService(@RequestParam("tenantId") String tenantId,
        @RequestParam("userId") String userId);

    /**
     * 根据排序号tabIndex删除常用语
     *
     * @param tenantId 租户id
     * @param userId 人员id
     * @param tabIndex 排序号
     * @return {@code Y9Result<Object>} 通用请求返回对象
     * @since 9.6.6
     */
    @PostMapping("/removeCommonSentences")
    Y9Result<Object> removeCommonSentences(@RequestParam("tenantId") String tenantId,
        @RequestParam("userId") String userId, @RequestParam("tabIndex") int tabIndex);

    /**
     * 清空常用语使用次数
     *
     * @param tenantId 租户id
     * @param userId 人员id
     * @return {@code Y9Result<Object>} 通用请求返回对象
     * @since 9.6.6
     */
    @PostMapping("/removeUseNumber")
    Y9Result<Object> removeUseNumber(@RequestParam("tenantId") String tenantId, @RequestParam("userId") String userId);

    /**
     * 根据id保存更新常用语
     *
     * @param tenantId 租户id
     * @param userId 人员id
     * @param id 常用语的唯一标识
     * @param content 内容
     * @return {@code Y9Result<Object>} 通用请求返回对象
     * @since 9.6.6
     */
    @PostMapping("/save")
    Y9Result<Object> save(@RequestParam("tenantId") String tenantId, @RequestParam("userId") String userId,
        @RequestParam("id") String id, @RequestParam("content") String content);

    /**
     * 根据排序号tabIndex保存更新常用语
     *
     * @param tenantId 租户id
     * @param userId 人员id
     * @param content 常用语内容
     * @param tabIndex 排序号
     * @return {@code Y9Result<Object>} 通用请求返回对象
     * @since 9.6.6
     */
    @PostMapping("/saveCommonSentences")
    Y9Result<Object> saveCommonSentences(@RequestParam("tenantId") String tenantId,
        @RequestParam("userId") String userId, @RequestParam("content") String content,
        @RequestParam("tabIndex") int tabIndex);

    /**
     * 更新常用语使用次数
     *
     * @param tenantId 租户id
     * @param id 常用语id
     * @return {@code Y9Result<Object>} 通用请求返回对象
     * @since 9.6.6
     */
    @PostMapping("/updateUseNumber")
    Y9Result<Object> updateUseNumber(@RequestParam("tenantId") String tenantId, @RequestParam("id") String id);
}
