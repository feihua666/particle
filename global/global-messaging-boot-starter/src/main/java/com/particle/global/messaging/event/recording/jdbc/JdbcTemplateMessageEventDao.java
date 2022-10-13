package com.particle.global.messaging.event.recording.jdbc;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.particle.global.dto.messaging.event.AbstractMessageEvent;
import com.particle.global.messaging.event.api.MessageEventRepository;
import com.particle.global.tool.json.JsonTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;
import java.util.function.Function;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Maps.newHashMap;

/**
 * <p>
 * jdbcTemplate 消息事件存储
 * </p>
 *
 * @author yangwei
 * @since 2022-09-20 13:10
 */
@Slf4j
public class JdbcTemplateMessageEventDao implements MessageEventRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;
    private final RowMapper<AbstractMessageEvent> mapper;

    private final String tableName = "global_message_event";

    public JdbcTemplateMessageEventDao(NamedParameterJdbcTemplate jdbcTemplate, ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
        this.mapper = eventMapper(objectMapper);
    }

    @Override
    public void save(List<AbstractMessageEvent> events) {
        String sql = "INSERT INTO DOMAIN_EVENT (ID, CONTENT) VALUES (:id, :json);";

        SqlParameterSource[] parameters = events.stream()
                .map((Function<AbstractMessageEvent, SqlParameterSource>) messageEvent ->
                        new MapSqlParameterSource()
                                .addValue("id", messageEvent.getMessageId())
                                .addValue("json", toJson(messageEvent)))
                .toArray(SqlParameterSource[]::new);

        jdbcTemplate.batchUpdate(sql, parameters);

    }

    @Override
    public void delete(String eventId) {
        String sql = "DELETE FROM "+ tableName +" WHERE ID = :id;";
        jdbcTemplate.update(sql, of("id", eventId));
    }

    @Override
    public AbstractMessageEvent get(String eventId) {
        String sql = "SELECT CONTENT FROM "+ tableName +" WHERE ID = :id;";
        return jdbcTemplate.queryForObject(sql, of("id", eventId), mapper);
    }

    @Override
    public List<AbstractMessageEvent> nextPublishBatch(int size) {
        String sql = "SELECT CONTENT FROM "+ tableName +" WHERE STATUS != 'FAILED' ORDER BY CREATE_AT LIMIT :limit;";
        return jdbcTemplate.query(sql, of("limit", size), mapper);
    }

    @Override
    public void markAsPublished(String eventId) {
        delete(eventId);
    }

    @Override
    public void markAsPublishFailed(String eventId) {
        String sql = "UPDATE "+ tableName +" SET STATUS = 'FAILED' WHERE ID = :id;";
        jdbcTemplate.update(sql, of("id", eventId));
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM "+ tableName +";";
        jdbcTemplate.update(sql, newHashMap());
    }

    private RowMapper<AbstractMessageEvent> eventMapper(ObjectMapper objectMapper) {
        return (rs, rowNum) -> fromJson(rs.getString("CONTENT"), AbstractMessageEvent.class);
    }

    private String toJson(Object obj) {
        if (objectMapper != null) {
            try {
                return objectMapper.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                log.error("objectMapper writeValueAsString failed. JsonTool.toJsonStr fallback!",e);
            }
        }
        return JsonTool.toJsonStr(obj);
    }

    private AbstractMessageEvent fromJson(String json,Class clazz) {
        if (objectMapper != null) {
            try {
                return (AbstractMessageEvent) objectMapper.readValue(json, clazz);
            } catch (JsonProcessingException e) {
                log.error("objectMapper readValue failed. JSONUtil.toBean fallback!",e);
            }
        }
        return (AbstractMessageEvent) JSONUtil.toBean(json, clazz);
    }
}

