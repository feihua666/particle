package com.particle.global.messaging.test.configuration;

import cn.hutool.core.util.StrUtil;
import com.particle.global.messaging.event.messaging.CloudStreamConsume;
import com.particle.global.messaging.event.messaging.GlobalCloudSteamMessageUtil;
import com.particle.global.tool.json.JsonTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Supplier;

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
	public Supplier<Test1RabbitMessageEvent> test1RabbitProducer(){
		return () -> {
			String msg = StrUtil.format("这是一个rabbit消息{}", id++);
			log.info(msg);
			return Test1RabbitMessageEvent.create(GlobalCloudSteamMessageUtil.inZeroBindingName("test1RabbitConsumer"),msg);
		};
	}*/

	@Bean
	public Consumer<Test1RabbitMessageEvent> test1RabbitConsumer(){

		return new Consumer<Test1RabbitMessageEvent>() {
			@CloudStreamConsume
			@Override
			public void accept(Test1RabbitMessageEvent test1RabbitMessageEvent) {
				log.info("收到rabbit消息 Test1KafkaMessageEvent={}", JsonTool.toJsonStr(test1RabbitMessageEvent));
			}
		};

	}
}
