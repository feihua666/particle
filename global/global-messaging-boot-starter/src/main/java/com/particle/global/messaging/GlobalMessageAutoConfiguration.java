package com.particle.global.messaging;

import com.particle.global.messaging.event.GlobalMessageEventConfiguration;
import com.particle.global.messaging.event.messaging.CloudStreamConfiguration;
import com.particle.global.messaging.event.recording.jdbc.JdbcTemplateMessageEventConfiguration;
import com.particle.global.messaging.event.recording.mongo.MongoMessageEventConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>
 * 消息自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2022-09-20 13:10
 */
@Configuration
@Import({GlobalMessageEventConfiguration.class,
		MongoMessageEventConfiguration.class,
		CloudStreamConfiguration.class,
		JdbcTemplateMessageEventConfiguration.class})
public class GlobalMessageAutoConfiguration {
}
