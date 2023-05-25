package com.particle.global.messaging.test.configuration;

import com.particle.global.messaging.event.messaging.CloudStreamConsume;
import com.particle.global.tool.json.JsonTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2022-10-12 16:22
 */
@Slf4j
@Configuration
public class RabbitTestConfiguration {


	private Integer id = 0;

/*	@Bean
	public Supplier<MessageStringDataTestEvent> testRabbitProducer(){
		return () -> {
			String msg = StrUtil.format("这是一个rabbit消息{}", id++);
			log.info(msg);
			return MessageStringDataTestEvent.create(GlobalCloudSteamMessageUtil.inZeroBindingName("testRabbitConsumer"),msg);
		};
	}*/

	@Bean
	public Consumer<MessageStringDataTestEvent> testRabbitConsumer(){

		return new Consumer<MessageStringDataTestEvent>() {
			@CloudStreamConsume
			@Override
			public void accept(MessageStringDataTestEvent rabbitMessageStringDataTestEvent) {
				log.info("收到rabbit消息 MessageStringDataTestEvent={}", JsonTool.toJsonStr(rabbitMessageStringDataTestEvent));
			}
		};

	}
	@Bean
	public Consumer<MessageObjDataTestEvent> testObjRabbitConsumer(){

		return new Consumer<MessageObjDataTestEvent>() {
			@CloudStreamConsume
			@Override
			public void accept(MessageObjDataTestEvent messageObjDataTestEvent) {
				log.info("收到rabbit消息 MessageObjDataTestEvent={}", JsonTool.toJsonStr(messageObjDataTestEvent));
			}
		};

	}
}
