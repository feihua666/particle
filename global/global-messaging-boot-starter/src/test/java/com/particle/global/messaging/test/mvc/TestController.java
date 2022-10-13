package com.particle.global.messaging.test.mvc;

import com.particle.global.messaging.event.api.MessageEventSender;
import com.particle.global.messaging.event.messaging.GlobalCloudSteamMessageUtil;
import com.particle.global.messaging.test.configuration.Test1RabbitMessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2022-10-11 17:47
 */
@RestController
@RequestMapping("/mq/test")
public class TestController {

	@Autowired
	private MessageEventSender messageEventSender;

	@GetMapping(value = "/test1RabbitMessage")
	public String sendRabbitMessage(String message){
		// 需要和配置 binding 名称一致
		String mq = GlobalCloudSteamMessageUtil.outZeroBindingName("test1RabbitProducer");
		messageEventSender.send(Test1RabbitMessageEvent.create(mq,message));
		return mq + "---ok";
	}

	@GetMapping(value = "/test1KafkaMessage")
	public String sendKafkaMessage(String message){
		// 需要和配置 binding 名称一致
		String mq = GlobalCloudSteamMessageUtil.outZeroBindingName("test1KafkaProducer");
		messageEventSender.send(Test1RabbitMessageEvent.create(mq,message));
		return mq + "---ok";
	}
}
