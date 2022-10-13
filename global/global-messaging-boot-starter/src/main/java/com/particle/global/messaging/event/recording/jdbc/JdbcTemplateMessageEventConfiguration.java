package com.particle.global.messaging.event.recording.jdbc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.particle.global.messaging.event.api.MessageEventConsumeRecorder;
import com.particle.global.messaging.event.api.MessageEventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * <p>
 * jdbcTemplate 消息事件存储配置类
 * </p>
 *
 * @author yangwei
 * @since 2022-09-20 13:10
 */
@Configuration
public class JdbcTemplateMessageEventConfiguration {

    @Bean
    public MessageEventRepository messageEventRepository(NamedParameterJdbcTemplate jdbcTemplate,
                                                 ObjectMapper objectMapper) {
        return new JdbcTemplateMessageEventDao(jdbcTemplate, objectMapper);
    }

    @Bean
    public MessageEventConsumeRecorder messageEventConsumeRecorder(NamedParameterJdbcTemplate jdbcTemplate) {
        return new JdbcTemplateMessageEventConsumeRecorder(jdbcTemplate);
    }
}
