package com.particle.global.messaging.test.configuration;

import com.particle.global.messaging.event.messaging.CloudStreamConsumer;
import com.particle.global.tool.json.JsonTool;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 开放平台调用记录事件消费
 * </p>
 *
 * @author yangwei
 * @since 2023-08-21 11:22
 */
@Slf4j
public class TestBinderTestMessageConsumer implements CloudStreamConsumer<MessageObjDataTestEvent> {

	@Override
	public void consume(MessageObjDataTestEvent event) {
		log.info("收到testBinder消息 MessageObjDataTestEvent={}", JsonTool.toJsonStr(event));
	}
}
