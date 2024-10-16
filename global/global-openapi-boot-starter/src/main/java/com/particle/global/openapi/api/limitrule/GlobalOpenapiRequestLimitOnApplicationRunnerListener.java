package com.particle.global.openapi.api.limitrule;

import com.particle.global.bootstrap.boot.OnApplicationRunnerListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 在项目启动后，统计请求限制的数据
 * </p>
 *
 * @author yangwei
 * @since 2024/10/14 18:19
 */
@Slf4j
@Component
public class GlobalOpenapiRequestLimitOnApplicationRunnerListener implements OnApplicationRunnerListener {

    @Autowired
    private GlobalOpenapiRequestLimitService globalOpenapiRequestLimitService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("globalOpenapiRequestLimit sheduleData start");
        globalOpenapiRequestLimitService.sheduleStatisticData();
        globalOpenapiRequestLimitService.scheduleAppQuotaLimitData();
    }
}
