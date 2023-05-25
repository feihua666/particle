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
public class KafkaTestConfiguration {


	private Integer id = 0;

/*	@Bean
	public Supplier<MessageStringDataTestEvent> testKafkaProducer(){
		return () -> {
			String msg = StrUtil.format("这是一个kafka消息{}", id++);
			log.info(msg);
			return MessageStringDataTestEvent.create(GlobalCloudSteamMessageUtil.inZeroBindingName("testKafkaConsumer"),msg);
		};
	}*/

	/**
	 * 注意这样写没有 {@link CloudStreamConsume} 注解，在消费时将不能避免重复消费问题
	 * 正解：参见{@link RabbitTestConfiguration#testObjRabbitConsumer()}
	 * @return
	 */
	@Bean
	public Consumer<MessageStringDataTestEvent> testKafkaConsumer(){
		return event ->{
			log.info("收到kafka消息 TestKafkaMessageEvent={}", JsonTool.toJsonStr(event));
		};
	}
	/**
	 * 注意这样写没有 {@link CloudStreamConsume} 注解，在消费时将不能避免重复消费问题
	 * 正解：参见{@link RabbitTestConfiguration#testObjRabbitConsumer()}
	 * @return
	 */
	@Bean
	public Consumer<MessageObjDataTestEvent> testObjKafkaConsumer(){
		return event ->{
			log.info("收到kafka消息 MessageObjDataTestEvent={}", JsonTool.toJsonStr(event));
		};
	}
}
