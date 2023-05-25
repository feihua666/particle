package com.particle.global.messaging.event.recording.jdbc;

import com.google.common.collect.ImmutableMap;
import com.particle.global.dto.messaging.event.AbstractMessageEvent;
import com.particle.global.messaging.event.api.MessageEventConsumeRecorder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.time.LocalDateTime;

import static com.google.common.collect.Maps.newHashMap;

/**
 * <p>
 * jdbcTemplate 消息事件消费记录器
 * </p>
 *
 * @author yangwei
 * @since 2022-09-20 13:10
 */
@Slf4j
public class JdbcTemplateMessageEventConsumeRecorder implements MessageEventConsumeRecorder {

    public static String tableName = "global_message_event_consume_record";
    public static String eventIdColumn = "ID";
    public static String eventIdProperty = "id";
    public static String createAtColumn = "CREATE_AT";
    public static String createAtProperty = "createAt";

    public static String insertSql = String.format("INSERT INTO %s (%s,%s) VALUES (:%s,:%s);",
            tableName, eventIdColumn,createAtColumn, eventIdProperty,createAtProperty);
    public static String deleteAllSql = String.format("DELETE FROM %s;", tableName);



    private final NamedParameterJdbcTemplate jdbcTemplate;


    public JdbcTemplateMessageEventConsumeRecorder(NamedParameterJdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean record(AbstractMessageEvent event) {

        String eventId = event.getMessageId();
        try {
            jdbcTemplate.update(insertSql, ImmutableMap.of(JdbcTemplateMessageEventConsumeRecorder.eventIdProperty, eventId,JdbcTemplateMessageEventConsumeRecorder.createAtProperty, LocalDateTime.now()));
            return true;
        } catch (DuplicateKeyException e) {
            log.debug("Duplicated key:{}.", eventId);
        } catch (Throwable t) {
            log.debug("Error while record {}.", eventId,t);
        }
        return false;
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update(deleteAllSql, newHashMap());

    }
}
