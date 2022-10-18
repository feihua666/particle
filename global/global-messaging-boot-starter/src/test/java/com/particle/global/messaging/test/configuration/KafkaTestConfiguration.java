package com.particle.global.messaging.test.configuration;

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
	public Supplier<Test1KafkaMessageEvent> test1KafkaProducer(){
		return () -> {
			String msg = StrUtil.format("这是一个kafka消息{}", id++);
			log.info(msg);
			return Test1KafkaMessageEvent.create(GlobalCloudSteamMessageUtil.inZeroBindingName("test1KafkaConsumer"),msg);
		};
	}*/

	@Bean
	public Consumer<Test1KafkaMessageEvent> test1KafkaConsumer(){
		return event ->{
			log.info("收到kafka消息 Test1KafkaMessageEvent={}", JsonTool.toJsonStr(event));
		};
	}
}
