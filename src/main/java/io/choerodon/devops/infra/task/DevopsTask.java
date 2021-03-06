package io.choerodon.devops.infra.task;

import java.util.*;

import io.choerodon.asgard.schedule.QuartzDefinition;
import io.choerodon.asgard.schedule.annotation.JobParam;
import io.choerodon.asgard.schedule.annotation.JobTask;
import io.choerodon.asgard.schedule.annotation.TaskParam;
import io.choerodon.asgard.schedule.annotation.TimedTask;
import io.choerodon.devops.app.service.DevopsCheckLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zmf
 */
@Component
public class DevopsTask {
    private static final Logger logger = LoggerFactory.getLogger(DevopsTask.class);

    @Autowired
    private DevopsCheckLogService devopsCheckLogService;

    @JobTask(maxRetryCount = 3, code = "syncNewVersionApplications", params = {
            @JobParam(name = "test", defaultValue = "test")
    }, description = "升级到0.15.0同步应用到iam,给应用加配置")
    @TimedTask(name = "syncNewVersionApplications", description = "升级到0.15.0同步应用到iam,给应用加配置", oneExecution = true,
            repeatCount = 0, repeatInterval = 1, repeatIntervalUnit = QuartzDefinition.SimpleRepeatIntervalUnit.HOURS, params = {
            @TaskParam(name = "test", value = "test")
    })
    public void syncNewVersionApplications(Map<String, Object> map) {
        logger.info("begin to upgrade 0.14.0 to 0.15.0");
        devopsCheckLogService.checkLog("0.15.0");
    }
}
