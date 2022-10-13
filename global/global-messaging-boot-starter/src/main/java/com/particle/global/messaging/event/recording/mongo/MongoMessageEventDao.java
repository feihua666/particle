package com.particle.global.messaging.event.recording.mongo;

import com.particle.global.dto.messaging.event.AbstractMessageEvent;
import com.particle.global.messaging.event.api.MessageEventRepository;

import java.util.List;

/**
 * <p>
 * mongo 消息事件存储实现类
 * </p>
 *
 * @author yangwei
 * @since 2022-09-20 13:10
 */
public class MongoMessageEventDao implements MessageEventRepository {

    @Override
    public void save(List<AbstractMessageEvent> events) {

    }

    @Override
    public void delete(String eventId) {

    }

    @Override
    public AbstractMessageEvent get(String eventId) {
        return null;
    }

    @Override
    public List<AbstractMessageEvent> nextPublishBatch(int size) {
        return null;
    }

    @Override
    public void markAsPublished(String eventId) {

    }

    @Override
    public void markAsPublishFailed(String eventId) {

    }

    @Override
    public void deleteAll() {

    }
}
