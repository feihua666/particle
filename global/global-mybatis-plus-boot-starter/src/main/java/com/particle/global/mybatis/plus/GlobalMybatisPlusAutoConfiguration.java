package com.particle.global.mybatis.plus;

import com.particle.global.dataaudit.audit.DataAuditCollectTool;
import com.particle.global.mybatis.plus.config.GlobalMybatisExecutorsConfig;
import com.particle.global.mybatis.plus.config.GlobalMybatisPlusConfig;
import com.particle.global.mybatis.plus.dataaudit.DataAuditHelperTool;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2022-06-29 17:36
 */
@Configuration(proxyBeanMethods = false)
@Import({GlobalMybatisPlusConfig.class, GlobalMybatisExecutorsConfig.class})
@MapperScan("com.particle.global.mybatis.plus.mapper")
public class GlobalMybatisPlusAutoConfiguration {


	@Configuration(proxyBeanMethods = false)
	@ConditionalOnClass(DataAuditCollectTool.class)
	protected static class DataAuditCollectToolDependConfig{

		@Bean
		@ConditionalOnClass(DataAuditCollectTool.class)
		public DataAuditHelperTool dataAuditHelperTool(){
			DataAuditHelperTool dataAuditHelperTool = new DataAuditHelperTool();
			return dataAuditHelperTool;
		}
	}
}
