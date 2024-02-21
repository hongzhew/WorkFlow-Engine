package net.risesoft.api.processadmin;

import java.util.List;
import java.util.Map;

import net.risesoft.model.processadmin.ExecutionModel;
import net.risesoft.model.processadmin.ProcessInstanceModel;

/**
 * @author qinman
 * @author zhangchongjie
 * @date 2022/12/19
 */
public interface RuntimeApi {

    /**
     *
     * Description: 加签
     *
     * @param tenantId 租户id
     * @param activityId 执行实例id
     * @param parentExecutionId 父执行实例id
     * @param map 参数
     * @throws Exception 异常
     */
    void addMultiInstanceExecution(String tenantId, String activityId, String parentExecutionId,
        Map<String, Object> map) throws Exception;

    /**
     * 加签/岗位
     *
     * @param tenantId 租户id
     * @param positionId 岗位id
     * @param processInstanceId 流程实例id
     * @param taskId 任务id
     * @throws Exception Exception
     */
    void complete4Position(String tenantId, String positionId, String processInstanceId, String taskId)
        throws Exception;

    /**
     *
     * Description: 真办结
     *
     * @param tenantId 租户id
     * @param userId 人员id
     * @param processInstanceId 流程实例id
     * @param taskId 任务id
     * @throws Exception Exception
     */
    void completed(String tenantId, String userId, String processInstanceId, String taskId) throws Exception;

    /**
     * 减签
     *
     * @param tenantId 租户id
     * @param executionId 执行实例id
     * @throws Exception Exception
     */
    void deleteMultiInstanceExecution(String tenantId, String executionId) throws Exception;

    /**
     * 根据执行Id获取当前活跃的节点信息
     *
     * @param tenantId 租户id
     * @param executionId 执行实例id
     * @return List&lt;String&gt;
     */
    List<String> getActiveActivityIds(String tenantId, String executionId);

    /**
     * 根据执行实例Id查找执行实例
     *
     * @param tenantId 租户id
     * @param executionId 执行实例id
     * @return ExecutionModel
     */
    ExecutionModel getExecutionById(String tenantId, String executionId);

    /**
     * 根据父流程实例获取子流程实例
     *
     * @param tenantId 租户id
     * @param superProcessInstanceId 父流程实例id
     * @return List&lt;ProcessInstanceModel&gt;
     */
    List<ProcessInstanceModel> getListBySuperProcessInstanceId(String tenantId, String superProcessInstanceId);

    /**
     * 根据流程实例Id获取流程实例
     *
     * @param tenantId 租户id
     * @param processInstanceId 流程实例id
     * @return ProcessInstanceModel
     */
    ProcessInstanceModel getProcessInstance(String tenantId, String processInstanceId);

    /**
     * 根据流程定义id获取流程实例列表
     *
     * @param tenantId 租户id
     * @param processDefinitionId 流程定义id
     * @param page 页码
     * @param rows 行数
     * @return Map&lt;String, Object&gt;
     */
    Map<String, Object> getProcessInstancesByDefId(String tenantId, String processDefinitionId, Integer page,
        Integer rows);

    /**
     * 根据流程定义Key获取流程实例列表
     *
     * @param tenantId 租户id
     * @param processDefinitionKey 流程定义key
     * @return List&lt;ProcessInstanceModel&gt;
     */
    List<ProcessInstanceModel> getProcessInstancesByKey(String tenantId, String processDefinitionKey);

    /**
     *
     * Description: 真办结后恢复流程实例为待办状态
     *
     * @param tenantId 租户id
     * @param userId 人员id
     * @param processInstanceId 流程实例id
     * @param year 年份
     * @throws Exception 异常
     */
    void recovery4Completed(String tenantId, String userId, String processInstanceId, String year) throws Exception;

    /**
     * 恢复流程实例为待办状态，其实是先激活，再设置流程实例的结束时间为null
     *
     * @param tenantId 租户id
     * @param processInstanceId 流程实例id
     * @throws Exception Exception
     */
    void recovery4SetUpCompleted(String tenantId, String processInstanceId) throws Exception;

    /**
     * 设置流程实例为办结的状态，其实是先暂停，再设置流程结束时间为当前时间
     *
     * @param tenantId 租户id
     * @param processInstanceId 流程实例id
     * @throws Exception Exception
     */
    void setUpCompleted(String tenantId, String processInstanceId) throws Exception;

    /**
     * 根据流程实例id设置流程变量
     *
     * @param tenantId 租户id
     * @param processInstanceId 流程实例id
     * @param key 变量key
     * @param map 变量值
     * @throws Exception Exception
     */
    void setVariable(String tenantId, String processInstanceId, String key, Map<String, Object> map) throws Exception;

    /**
     * 根据流程实例id设置流程变量
     *
     * @param tenantId 租户id
     * @param executionId 执行实例id
     * @param map 变量map
     */
    void setVariables(String tenantId, String executionId, Map<String, Object> map);

    /**
     * 根据流程定义Key启动流程实例，设置流程变量,并返回流程实例,流程启动人是人员Id
     *
     * @param tenantId 租户id
     * @param userId 人员id
     * @param processDefinitionKey 流程定义key
     * @param systemName 系统名称
     * @param map 变量map
     * @return ProcessInstanceModel
     */
    ProcessInstanceModel startProcessInstanceByKey(String tenantId, String userId, String processDefinitionKey,
        String systemName, Map<String, Object> map);

    /**
     * 判断是否是挂起实例
     *
     * @param tenantId 租户id
     * @param processInstanceId 流程实例id
     * @return Boolean
     */
    Boolean suspendedByProcessInstanceId(String tenantId, String processInstanceId);

    /**
     * 挂起或者激活流程实例
     *
     * @param tenantId 租户id
     * @param processInstanceId 流程实例id
     * @param state 状态
     */
    void switchSuspendOrActive(String tenantId, String processInstanceId, String state);

}