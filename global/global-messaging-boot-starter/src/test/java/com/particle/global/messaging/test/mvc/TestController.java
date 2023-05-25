package com.particle.global.messaging.test.mvc;

import com.particle.global.messaging.event.api.MessageEventSender;
import com.particle.global.messaging.event.messaging.GlobalCloudSteamMessageUtil;
import com.particle.global.messaging.test.configuration.MessageObjDataTestEvent;
import com.particle.global.messaging.test.configuration.MessageStringDataTestEvent;
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

	/**
	 * 如果配置了 @Import(TestChannelBinderConfiguration.class) 走的是测试的binder,真正的rabbit binder并未生效
	 * @param message
	 * @return
	 */
	@GetMapping(value = "/testRabbitMessage")
	public String sendRabbitMessage(String message){
		// 需要和配置 binding 名称一致
		String mq = GlobalCloudSteamMessageUtil.outZeroBindingName("testRabbitProducer");
		messageEventSender.send(MessageStringDataTestEvent.create(mq,message));
		return mq + "---ok";
	}
	@GetMapping(value = "/testRabbitMessageObj")
	public String testRabbitMessageObj(String message){
		// 需要和配置 binding 名称一致
		String mq = GlobalCloudSteamMessageUtil.outZeroBindingName("testObjRabbitProducer");
		MessageObjDataTestEvent.TestObj testRabbitMessageObj = MessageObjDataTestEvent.TestObj.create("testRabbitMessageObj", true, message);
		messageEventSender.send(MessageObjDataTestEvent.create(mq,testRabbitMessageObj));
		return mq + "---ok";
	}
	/**
	 * 如果配置了 @Import(TestChannelBinderConfiguration.class) 走的是测试的binder,真正的 kafka binder并未生效
	 * @param message
	 * @return
	 */
	@GetMapping(value = "/testKafkaMessage")
	public String sendKafkaMessage(String message){
		// 需要和配置 binding 名称一致
		String mq = GlobalCloudSteamMessageUtil.outZeroBindingName("testKafkaProducer");
		messageEventSender.send(MessageStringDataTestEvent.create(mq,message));
		return mq + "---ok";
	}
	@GetMapping(value = "/testKafkaMessageObj")
	public String testKafkaMessageObj(String message){
		// 需要和配置 binding 名称一致
		String mq = GlobalCloudSteamMessageUtil.outZeroBindingName("testObjKafkaProducer");
		MessageObjDataTestEvent.TestObj testKafkaMessageObj = MessageObjDataTestEvent.TestObj.create("testKafkaMessageObj", true, message);
		messageEventSender.send(MessageObjDataTestEvent.create(mq,testKafkaMessageObj));
		return mq + "---ok";
	}
	/**
	 * 如果配置了 @Import(TestChannelBinderConfiguration.class) 走的是测试的binder，否则使用的是 default-binder
	 * @param message
	 * @return
	 */
	@GetMapping(value = "/testTestBinderMessage")
	public String sendTestBinderMessage(String message){
		// 需要和配置 binding 名称一致
		String mq = GlobalCloudSteamMessageUtil.outZeroBindingName("testTestBinderProducer");
		messageEventSender.send(MessageStringDataTestEvent.create(mq,message));
		return mq + "---ok";
	}

	@GetMapping(value = "/testTestBinderMessageObj")
	public String testTestBinderMessageObj(String message){
		// 需要和配置 binding 名称一致
		String mq = GlobalCloudSteamMessageUtil.outZeroBindingName("testObjTestBinderProducer");
		MessageObjDataTestEvent.TestObj testTestBinderMessageObj = MessageObjDataTestEvent.TestObj.create("testTestBinderMessageObj", true, message);
		messageEventSender.send(MessageObjDataTestEvent.create(mq,testTestBinderMessageObj));
		return mq + "---ok";
	}
}
