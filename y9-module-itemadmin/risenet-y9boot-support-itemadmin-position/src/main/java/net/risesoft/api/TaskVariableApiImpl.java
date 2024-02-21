package net.risesoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.risesoft.api.itemadmin.TaskVariableApi;
import net.risesoft.entity.TaskVariable;
import net.risesoft.model.itemadmin.TaskVariableModel;
import net.risesoft.repository.jpa.TaskVariableRepository;
import net.risesoft.y9.Y9LoginUserHolder;
import net.risesoft.y9.util.Y9BeanUtil;

/**
 * @author qinman
 * @author zhangchongjie
 * @date 2022/12/20
 */
@RestController
@RequestMapping(value = "/services/rest/taskVariable")
public class TaskVariableApiImpl implements TaskVariableApi {

    @Autowired
    private TaskVariableRepository taskVariableRepository;

    @Override
    @GetMapping(value = "/findByTaskIdAndKeyName", produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskVariableModel findByTaskIdAndKeyName(String tenantId, String taskId, String keyName) {
        Y9LoginUserHolder.setTenantId(tenantId);
        TaskVariable taskVariable = taskVariableRepository.findByTaskIdAndKeyName(taskId, keyName);
        TaskVariableModel taskVariableModel = null;
        if (taskVariable != null) {
            taskVariableModel = new TaskVariableModel();
            Y9BeanUtil.copyProperties(taskVariable, taskVariableModel);
        }
        return taskVariableModel;
    }

}