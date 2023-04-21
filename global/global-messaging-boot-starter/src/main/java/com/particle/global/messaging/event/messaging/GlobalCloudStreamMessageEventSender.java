package com.particle.global.messaging.event.messaging;

import com.google.common.collect.Lists;
import com.particle.global.dto.messaging.event.AbstractMessageEvent;
import com.particle.global.messaging.event.api.MessageEventRepository;
import com.particle.global.messaging.event.api.MessageEventSender;
import com.particle.global.tool.json.JsonTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 全局默认发送器
 * </p>
 *
 * @author yangwei
 * @since 2022-10-12 17:16
 */
@Slf4j
@Component
public class GlobalCloudStreamMessageEventSender implements MessageEventSender {


	@Resource
	private StreamBridge streamBridge;
	@Autowired(required = false)
	private MessageEventRepository messageEventRepository;

	@Autowired(required = false)
	private List<LocalMessageListener> localMessageListeners;

	@Override
	public boolean send(AbstractMessageEvent event) {
		if (AbstractMessageEvent.defaultLocalListenerSyncVirtualMq.equals(event.getMq())) {
			if (localMessageListeners != null) {
				for (LocalMessageListener localMessageListener : localMessageListeners) {
					localMessageListener.onMessage(event);
				}
			}
			return true;
		}
		try {
			streamBridge.send(event.getMq(), event);
			return true;
		} catch (Throwable e) {
			// 消息发送失败，启动备用重发
			if (messageEventRepository != null) {
				AbstractMessageEvent abstractMessageEvent = messageEventRepository.get(event.getMessageId());
				if (abstractMessageEvent == null) {
					event.setStatus(AbstractMessageEvent.Status.send_failed_back.name());
					messageEventRepository.save(Lists.newArrayList(event));
				}else {
					messageEventRepository.markAsPublishFailed(event.getMessageId());
				}
			}else {
				log.error("message send failed! config messageEventRepository or else you see the log. event={} ", JsonTool.toJsonStr(event),e);
			}
			return false;
		}
	}
}
