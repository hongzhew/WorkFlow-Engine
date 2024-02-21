package net.risesoft.api;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.risesoft.api.processadmin.MonitorApi;
import net.risesoft.service.CustomMonitorService;
import net.risesoft.service.CustomRecycleService;
import net.risesoft.service.FlowableTenantInfoHolder;

/**
 * @author qinman
 * @author zhangchongjie
 * @date 2022/12/30
 */
@RestController
@RequestMapping(value = "/services/rest/monitor")
public class MonitorApiImpl implements MonitorApi {

    @Autowired
    private CustomMonitorService customMonitorService;

    @Autowired
    private CustomRecycleService customRecycleService;

    /**
     * 根据流程定义Key获取监控在办件统计
     *
     * @param tenantId 租户Id
     * @param processDefinitionKey 流程定义Key
     * @return Integer
     */
    @Override
    @GetMapping(value = "/getDoingCountByProcessDefinitionKey", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getDoingCountByProcessDefinitionKey(String tenantId, String processDefinitionKey) {
        FlowableTenantInfoHolder.setTenantId(tenantId);
        long totalCount = customMonitorService.getDoingCountByProcessDefinitionKey(processDefinitionKey);
        return totalCount;
    }

    /**
     * 根据系统英文名称获取监控在办件数量
     *
     * @param tenantId 租户Id
     * @param systemName 系统英文名称
     * @return Integer
     */
    @Override
    @GetMapping(value = "/getDoingCountBySystemName", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getDoingCountBySystemName(String tenantId, String systemName) {
        FlowableTenantInfoHolder.setTenantId(tenantId);
        long totalCount = customMonitorService.getDoingCountBySystemName(systemName);
        return totalCount;
    }

    /**
     * 根据流程定义Key获取监控在办件
     *
     * @param tenantId 租户Id
     * @param processDefinitionKey 流程定义Key
     * @param page 页码
     * @param rows 行数
     * @return Map<String, Object>
     */
    @Override
    @GetMapping(value = "/getDoingListByProcessDefinitionKey", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getDoingListByProcessDefinitionKey(String tenantId, String processDefinitionKey,
        Integer page, Integer rows) {
        FlowableTenantInfoHolder.setTenantId(tenantId);
        return customMonitorService.getDoingListByProcessDefinitionKey(processDefinitionKey, page, rows);
    }

    /**
     * 根据系统名称获取监控在办件
     *
     * @param tenantId 租户Id
     * @param systemName 系统英文名称
     * @param page 页码
     * @param rows 行数
     * @return Map<String, Object>
     */
    @Override
    @GetMapping(value = "/getDoingListBySystemName", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getDoingListBySystemName(String tenantId, String systemName, Integer page,
        Integer rows) {
        FlowableTenantInfoHolder.setTenantId(tenantId);
        return customMonitorService.getDoingListBySystemName(systemName, page, rows);
    }

    /**
     * 根据流程定义Key获取监控办结件统计
     *
     * @param tenantId 租户Id
     * @param processDefinitionKey 流程定义Key
     * @return Integer
     */
    @Override
    @GetMapping(value = "/getDoneCountByProcessDefinitionKey", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getDoneCountByProcessDefinitionKey(String tenantId, String processDefinitionKey) {
        FlowableTenantInfoHolder.setTenantId(tenantId);
        long totalCount = customMonitorService.getDoneCountByProcessDefinitionKey(processDefinitionKey);
        return totalCount;
    }

    /**
     * 根据系统英文名称获取监控办结件统计
     *
     * @param tenantId 租户Id
     * @param systemName 系统英文名称
     * @return Integer
     */
    @Override
    @GetMapping(value = "/getDoneCountBySystemName", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getDoneCountBySystemName(String tenantId, String systemName) {
        FlowableTenantInfoHolder.setTenantId(tenantId);
        long totalCount = customMonitorService.getDoneCountBySystemName(systemName);
        return totalCount;
    }

    /**
     * 根据流程定义Key获取监控办结件
     *
     * @param tenantId 租户Id
     * @param processDefinitionKey 流程定义Key
     * @param page 页码
     * @param rows 行数
     * @return Map<String, Object>
     */
    @Override
    @GetMapping(value = "/getDoneListByProcessDefinitionKey", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getDoneListByProcessDefinitionKey(String tenantId, String processDefinitionKey,
        Integer page, Integer rows) {
        FlowableTenantInfoHolder.setTenantId(tenantId);
        return customMonitorService.getDoneListByProcessDefinitionKey(processDefinitionKey, page, rows);
    }

    /**
     * 根据系统英文名称获取监控办结件
     *
     * @param tenantId 租户Id
     * @param systemName 系统英文名称
     * @param page 页码
     * @param rows 行数
     * @return Map<String, Object>
     */
    @Override
    @GetMapping(value = "/getDoneListBySystemName", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getDoneListBySystemName(String tenantId, String systemName, Integer page, Integer rows) {
        FlowableTenantInfoHolder.setTenantId(tenantId);
        return customMonitorService.getDoneListBySystemName(systemName, page, rows);
    }

    /**
     * 根据流程定义Key获取监控回收站统计
     *
     * @param tenantId 租户Id
     * @param processDefinitionKey 流程定义Key
     * @return Integer
     */
    @Override
    @GetMapping(value = "/getRecycleCountByProcessDefinitionKey", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getRecycleCountByProcessDefinitionKey(String tenantId, String processDefinitionKey) {
        FlowableTenantInfoHolder.setTenantId(tenantId);
        long totalCount = customRecycleService.getRecycleCountByProcessDefinitionKey(processDefinitionKey);
        return totalCount;
    }

    /**
     * 根据系统英文名称获取监控回收站统计
     *
     * @param tenantId 租户Id
     * @param systemName 系统英文名称
     * @return Integer
     */
    @Override
    @GetMapping(value = "/getRecycleCountBySystemName", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getRecycleCountBySystemName(String tenantId, String systemName) {
        FlowableTenantInfoHolder.setTenantId(tenantId);
        return customRecycleService.getRecycleCountBySystemName(systemName);
    }

    /**
     * 根据人员Id，流程定义Key获取监控回收站统计
     *
     * @param tenantId 租户Id
     * @param userId 人员Id
     * @param processDefinitionKey 流程定义Key
     * @return Integer
     */
    @Override
    @GetMapping(value = "/getRecycleCountByUserIdAndProcessDefinitionKey", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getRecycleCountByUserIdAndProcessDefinitionKey(String tenantId, String userId,
        String processDefinitionKey) {
        FlowableTenantInfoHolder.setTenantId(tenantId);
        return customRecycleService.getRecycleCountByUserIdAndProcessDefinitionKey(userId, processDefinitionKey);
    }

    /**
     * 根据人员id获取监控回收站统计
     *
     * @param tenantId 租户Id
     * @param userId 人员Id
     * @param systemName 系统英文名称
     * @return Integer
     */
    @Override
    @GetMapping(value = "/getRecycleCountByUserIdAndSystemName", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getRecycleCountByUserIdAndSystemName(String tenantId, String userId, String systemName) {
        FlowableTenantInfoHolder.setTenantId(tenantId);
        return customRecycleService.getRecycleCountByUserIdAndSystemName(userId, systemName);
    }

    /**
     * 根据流程定义key获取回收站列表
     *
     * @param tenantId 租户Id
     * @param processDefinitionKey 流程定义Key
     * @param page 页码
     * @param rows 行数
     * @return Map<String, Object>
     */
    @Override
    @GetMapping(value = "/getRecycleListByProcessDefinitionKey", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getRecycleListByProcessDefinitionKey(String tenantId, String processDefinitionKey,
        Integer page, Integer rows) {
        FlowableTenantInfoHolder.setTenantId(tenantId);
        return customRecycleService.getRecycleListByProcessDefinitionKey(processDefinitionKey, page, rows);
    }

    /**
     * 根据系统英文名称获取回收站列表
     *
     * @param tenantId 租户Id
     * @param systemName 系统英文名称
     * @param page 页码
     * @param rows 行数
     * @return Map<String, Object>
     */
    @Override
    @GetMapping(value = "/getRecycleListBySystemName", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getRecycleListBySystemName(String tenantId, String systemName, Integer page,
        Integer rows) {
        FlowableTenantInfoHolder.setTenantId(tenantId);
        return customRecycleService.getRecycleListBySystemName(systemName, page, rows);
    }

    /**
     * 根据流程定义Key获取回收站列表
     *
     * @param tenantId 租户Id
     * @param userId 用户Id
     * @param processDefinitionKey 流程定义Key
     * @param page 页码
     * @param rows 行数
     * @return Map<String, Object>
     */
    @Override
    @GetMapping(value = "/getRecycleListByUserIdAndProcessDefinitionKey", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getRecycleListByUserIdAndProcessDefinitionKey(String tenantId, String userId,
        String processDefinitionKey, Integer page, Integer rows) {
        FlowableTenantInfoHolder.setTenantId(tenantId);
        return customRecycleService.getRecycleListByUserIdAndProcessDefinitionKey(userId, processDefinitionKey, page,
            rows);
    }

    /**
     * 根据人员id，系统英文名称获取回收站列表
     *
     * @param tenantId 租户Id
     * @param userId 人员Id
     * @param systemName 系统英文名称
     * @param page 当前页
     * @param rows 总条数
     * @return Map<String, Object>
     */
    @Override
    @GetMapping(value = "/getRecycleListByUserIdAndSystemName", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getRecycleListByUserIdAndSystemName(String tenantId, String userId, String systemName,
        Integer page, Integer rows) {
        FlowableTenantInfoHolder.setTenantId(tenantId);
        return customRecycleService.getRecycleListByUserIdAndSystemName(userId, systemName, page, rows);
    }

    /**
     * 根据流程定义Key条件搜索在办件
     *
     * @param tenantId 租户id
     * @param processDefinitionKey 流程定义Key
     * @param searchTerm 搜索词
     * @param page 页码
     * @param rows 行数
     * @return Map<String, Object>
     * @throws Exception Exception
     */
    @Override
    @GetMapping(value = "/searchDoingListByProcessDefinitionKey", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> searchDoingListByProcessDefinitionKey(String tenantId, String processDefinitionKey,
        String searchTerm, Integer page, Integer rows) throws Exception {
        if (StringUtils.isEmpty(tenantId) || StringUtils.isEmpty(processDefinitionKey)) {
            throw new Exception("tenantId or processDefinitionKey is null !");
        }
        FlowableTenantInfoHolder.setTenantId(tenantId);
        return customMonitorService.searchDoingListByProcessDefinitionKey(processDefinitionKey, searchTerm, page, rows);
    }

    /**
     * 根据系统英文名称条件搜索在办件
     *
     * @param tenantId 租户id
     * @param systemName 系统英文名称
     * @param searchTerm 搜索词
     * @param page 页码
     * @param rows 行数
     * @return Map<String, Object>
     * @throws Exception Exception
     */
    @Override
    @GetMapping(value = "/searchDoingListBySystemName", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> searchDoingListBySystemName(String tenantId, String systemName, String searchTerm,
        Integer page, Integer rows) throws Exception {
        if (StringUtils.isEmpty(tenantId) || StringUtils.isEmpty(systemName)) {
            throw new Exception("tenantId or systemName is null !");
        }
        FlowableTenantInfoHolder.setTenantId(tenantId);
        return customMonitorService.searchDoingListBySystemName(systemName, searchTerm, page, rows);
    }

    /**
     * 根据流程定义Key条件搜索办结件
     *
     * @param tenantId 租户id
     * @param processDefinitionKey 流程定义Key
     * @param searchTerm 搜索词
     * @param page 页码
     * @param rows 行数
     * @return Map<String, Object>
     * @throws Exception Exception
     */
    @Override
    @GetMapping(value = "/searchDoneListByProcessDefinitionKey", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> searchDoneListByProcessDefinitionKey(String tenantId, String processDefinitionKey,
        String searchTerm, Integer page, Integer rows) throws Exception {
        if (StringUtils.isEmpty(tenantId) || StringUtils.isEmpty(processDefinitionKey)) {
            throw new Exception("tenantId or processDefinitionKey is null !");
        }
        FlowableTenantInfoHolder.setTenantId(tenantId);
        return customMonitorService.searchDoneListByProcessDefinitionKey(processDefinitionKey, searchTerm, page, rows);
    }

    /**
     * 根据系统英文名称条件搜索在办件
     *
     * @param tenantId 租户id
     * @param systemName 系统英文名称
     * @param searchTerm 搜索词
     * @param page 页码
     * @param rows 行数
     * @return Map<String, Object>
     * @throws Exception Exception
     */
    @Override
    @GetMapping(value = "/searchDoneListBySystemName", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> searchDoneListBySystemName(String tenantId, String systemName, String searchTerm,
        Integer page, Integer rows) throws Exception {
        if (StringUtils.isEmpty(tenantId) || StringUtils.isEmpty(systemName)) {
            throw new Exception("tenantId or systemName is null !");
        }
        FlowableTenantInfoHolder.setTenantId(tenantId);
        return customMonitorService.searchDoneListBySystemName(systemName, searchTerm, page, rows);
    }

    /**
     * 根据流程定义Key条件搜索回收站件
     *
     * @param tenantId 租户id
     * @param processDefinitionKey 流程定义Key
     * @param searchTerm 搜索词
     * @param page 页码
     * @param rows 行数
     * @return Map<String, Object>
     * @throws Exception Exception
     */
    @Override
    @GetMapping(value = "/searchRecycleListByProcessDefinitionKey", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> searchRecycleListByProcessDefinitionKey(String tenantId, String processDefinitionKey,
        String searchTerm, Integer page, Integer rows) throws Exception {
        if (StringUtils.isEmpty(tenantId) || StringUtils.isEmpty(processDefinitionKey)) {
            throw new Exception("tenantId or processDefinitionKey is null !");
        }
        FlowableTenantInfoHolder.setTenantId(tenantId);
        return customRecycleService.searchRecycleListByProcessDefinitionKey(processDefinitionKey, searchTerm, page,
            rows);
    }

    /**
     * 根据系统英文名称条件搜索回收站件
     *
     * @param tenantId 租户id
     * @param systemName 系统英文名称
     * @param searchTerm 搜索词
     * @param page 页码
     * @param rows 行数
     * @return Map<String, Object>
     * @throws Exception Exception
     */
    @Override
    @GetMapping(value = "/searchRecycleListBySystemName", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> searchRecycleListBySystemName(String tenantId, String systemName, String searchTerm,
        Integer page, Integer rows) throws Exception {
        if (StringUtils.isEmpty(tenantId) || StringUtils.isEmpty(systemName)) {
            throw new Exception("tenantId or systemName is null !");
        }
        FlowableTenantInfoHolder.setTenantId(tenantId);
        return customRecycleService.searchRecycleListBySystemName(systemName, searchTerm, page, rows);
    }

    /**
     * 根据用户Id，流程定义Key条件搜索回收站件
     *
     * @param tenantId 租户id
     * @param userId 用户Id
     * @param processDefinitionKey 流程定义Key
     * @param searchTerm 搜索词
     * @param page 页码
     * @param rows 行数
     * @return Map<String, Object>
     * @throws Exception Exception
     */
    @Override
    @GetMapping(value = "/searchRecycleListByUserIdAndProcessDefinitionKey",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> searchRecycleListByUserIdAndProcessDefinitionKey(String tenantId, String userId,
        String processDefinitionKey, String searchTerm, Integer page, Integer rows) throws Exception {
        if (StringUtils.isEmpty(tenantId) || StringUtils.isEmpty(userId) || StringUtils.isEmpty(processDefinitionKey)) {
            throw new Exception("tenantId or userId or processDefinitionKey is null !");
        }
        FlowableTenantInfoHolder.setTenantId(tenantId);
        return customRecycleService.searchRecycleListByUserIdAndProcessDefinitionKey(userId, processDefinitionKey,
            searchTerm, page, rows);
    }

    /**
     * 根据用户Id，系统英文名称条件搜索回收站件
     *
     * @param tenantId 租户id
     * @param userId 用户Id
     * @param systemName 系统英文名称
     * @param searchTerm 搜索词
     * @param page 页码
     * @param rows 行数
     * @return Map<String, Object>
     * @throws Exception Exception
     */
    @Override
    @GetMapping(value = "/searchRecycleListByUserIdAndSystemName", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> searchRecycleListByUserIdAndSystemName(String tenantId, String userId, String systemName,
        String searchTerm, Integer page, Integer rows) throws Exception {
        if (StringUtils.isEmpty(tenantId) || StringUtils.isEmpty(userId) || StringUtils.isEmpty(systemName)) {
            throw new Exception("tenantId or userId or systemName is null !");
        }
        FlowableTenantInfoHolder.setTenantId(tenantId);
        return customRecycleService.searchRecycleListByUserIdAndSystemName(userId, systemName, searchTerm, page, rows);
    }
}