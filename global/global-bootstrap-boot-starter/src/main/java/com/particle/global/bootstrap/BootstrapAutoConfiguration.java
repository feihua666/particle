package com.particle.global.bootstrap;

import com.particle.global.bootstrap.boot.OnCommandLineListener;
import com.particle.global.projectinfo.SystemInfoPrinter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * <p>
 * 应用启动关闭自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2022-05-31 20:36
 */

@Configuration
@ComponentScan
public class BootstrapAutoConfiguration {


	@Bean
	@Order(Ordered.LOWEST_PRECEDENCE)
	public OnCommandLineListener systemInfoPrintListener(SystemInfoPrinter systemInfoPrinter){

		return new OnCommandLineListener(){
			@Override
			public void run(String... args) throws Exception {
				systemInfoPrinter.print();
			}
		};
	}
}
