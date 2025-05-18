package com.particle.openplatform.messaging;

import com.particle.global.bootstrap.boot.OnApplicationRunnerListener;
import com.particle.openplatform.app.messaging.OpenplatformOpenapiRecordMessageConsumer;
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
public class OpenplatformOpenapiRecordMessageOnApplicationRunnerListener implements OnApplicationRunnerListener {

    @Autowired
    private OpenplatformOpenapiRecordMessageConsumer openplatformOpenapiRecordMessageConsumer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("openplatformOpenapiRecordMessage scheduleData start");
        openplatformOpenapiRecordMessageConsumer.scheduleDeductAppQuota();
        openplatformOpenapiRecordMessageConsumer.scheduleSaveAppOpenapiDayRtSummary();
    }
}
