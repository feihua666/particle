package com.particle.global.openapi;

import com.particle.global.concurrency.threadpool.CustomExecutors;
import com.particle.global.light.share.scheduler.SchedulerConstants;
import com.particle.global.openapi.api.GlobalOpenapiCache;
import com.particle.global.openapi.api.GlobalOpenapiClientProvider;
import com.particle.global.openapi.api.OpenapiHelper;
import com.particle.global.openapi.api.impl.DefaultOpenApiGlobal;
import com.particle.global.openapi.api.impl.InMemoryGlobalOpenapiCacheImpl;
import com.particle.global.openapi.api.impl.InMemoryGlobalOpenapiClientProviderImpl;
import com.particle.global.openapi.api.portal.OpenapiExecutePortalService;
import com.particle.global.openapi.api.portal.OpenapiExecuteProviderLoadBalancer;
import com.particle.global.openapi.api.portal.impl.DefaultOpenapiExecutePortalServiceImpl;
import com.particle.global.openapi.api.portal.impl.DemoOpenapiExecuteProvider;
import com.particle.global.openapi.api.portal.impl.OpenapiSingleExecuteProviderLoadBalancerImpl;
import com.particle.global.openapi.api.portal.impl.OpenapiSpecifyExecuteProviderLoadBalancerImpl;
import com.particle.global.openapi.api.limitrule.ratelimit.GlobalOpenapiRateLimitService;
import com.particle.global.openapi.filter.GlobalOpenApiFilter;
import com.particle.global.openapi.filter.OpenapiRequestResponseLogMatchResponseResolver;
import com.particle.global.projectinfo.ProjectInfo;
import com.particle.global.ratelimit.local.DefaultThreadLocalRateLimitInterceptServiceImpl;
import com.particle.global.swagger.ApplicationContexSwaggertHelper;
import com.particle.global.swagger.SwaggerInfo;
import com.particle.global.swagger.factory.SwaggerFactory;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * 全局开放接口自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2023-08-01 14:43
 */
@ComponentScan
@Configuration
@EnableConfigurationProperties({GlobalOpenapiProperties.class})
@ConditionalOnProperty(prefix = "particle.openapi", name = "enabled", havingValue = "true", matchIfMissing = true)
public class GlobalOpenapiAutoConfiguration {

	public static final String global_openapi_scheduled_task_executor = "globalOpenapiScheduledTaskExecutor";

	@Bean
	public GlobalOpenApiFilter globalOpenApiFilterBean() {
		return new GlobalOpenApiFilter();
	}

	/**
	 * 可读请求体过滤器
	 * 注：确保该filter在{@link WebSecurityConfiguration#springSecurityFilterChain()} 之后，目前在没有加排序的情况下，刚在在其后面
	 * @return
	 */
	@Bean
	public FilterRegistrationBean globalOpenApiFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(globalOpenApiFilterBean());
		return registrationBean;
	}

	/**
	 * 开放接口数据辅助
	 * @return
	 */
	@Bean
	public OpenapiHelper openapiHelper() {
		return new OpenapiHelper();
	}

	/**
	 * openApi的实现
	 * @param globalOpenapiProperties
	 * @return
	 */
	@Bean
	public DefaultOpenApiGlobal defaultOpenApiGlobal(GlobalOpenapiProperties globalOpenapiProperties) {
		return new DefaultOpenApiGlobal(globalOpenapiProperties.getUrlPatterns());
	}

	/**
	 * 默认的提供可配置的客户端支持
	 * @param globalOpenapiProperties
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(GlobalOpenapiClientProvider.class)
	public GlobalOpenapiClientProvider inMemoryGlobalOpenapiClientProvider(GlobalOpenapiProperties globalOpenapiProperties) {
		return new InMemoryGlobalOpenapiClientProviderImpl(globalOpenapiProperties.getClients());
	}

	/**
	 * 开放接口相关缓存
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(GlobalOpenapiCache.class)
	public GlobalOpenapiCache globalOpenapiCache(){
		return new InMemoryGlobalOpenapiCacheImpl();
	}

	/**
	 * 开放接口文档
	 * @param projectInfo 参数不能去，依赖projectInfo
	 * @return
	 */
	@ConditionalOnBean({ApplicationContexSwaggertHelper.class})
	@Bean
	public GroupedOpenApi createGlobalOpenapiRestApi(ProjectInfo projectInfo) {
		List<SecurityScheme> parameters = new ArrayList<>();

		return SwaggerFactory.createRestApi(SwaggerInfo.builder()
				.groupName("openapi接口")
				.basePackage("com.particle.global.openapi.endpoint")
				.securitySchemes(parameters)
				.version(ProjectInfo.VERSION)
				.title(ProjectInfo.NAME + " Swagger Apis")
				.description(ProjectInfo.NAME + " Swagger Apis Description")
				.build());
	}

	/**
	 * provider 接口实现方式入口服务类
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public OpenapiExecutePortalService defaultOpenapiExecutePortalService() {
		return new DefaultOpenapiExecutePortalServiceImpl();
	}

	/**
	 * 按指定的供应商调用
	 * @return
	 */
	@Bean
	public OpenapiExecuteProviderLoadBalancer openapiSpecifyExecuteProviderLoadBalancer(){
		OpenapiSpecifyExecuteProviderLoadBalancerImpl openapiSpecifyExecuteProviderLoadBalancer = new OpenapiSpecifyExecuteProviderLoadBalancerImpl();
		return openapiSpecifyExecuteProviderLoadBalancer;
	}

	/**
	 * 当只有一个供应商时，就使用该供应商
	 * @return
	 */
	@Bean
	public OpenapiExecuteProviderLoadBalancer openapisingleExecuteProviderLoadBalancer(){
		OpenapiSingleExecuteProviderLoadBalancerImpl openapisingleExecuteProviderLoadBalancer = new OpenapiSingleExecuteProviderLoadBalancerImpl();
		return openapisingleExecuteProviderLoadBalancer;
	}

	/**
	 * 开放接口依赖请求与响应可重复读，这里指定一个可重复读的匹配
	 * @return
	 */
	@Bean
	public OpenapiRequestResponseLogMatchResponseResolver openapiRequestResponseLogMatchResponseResolver(){
		OpenapiRequestResponseLogMatchResponseResolver openapiRequestResponseLogMatchResponseResolver = new OpenapiRequestResponseLogMatchResponseResolver();
		return openapiRequestResponseLogMatchResponseResolver;
	}

	/**
	 * 开放接口provider 示例
	 * @return
	 */
	@Bean
	public DemoOpenapiExecuteProvider demoOpenapiExecuteProvider() {
		return new DemoOpenapiExecuteProvider();
	}

	@Configuration
	@ConditionalOnClass(DefaultThreadLocalRateLimitInterceptServiceImpl.class)
	public static class RateLimitConfig{

		@Bean
		@ConditionalOnBean(DefaultThreadLocalRateLimitInterceptServiceImpl.class)
		public GlobalOpenapiRateLimitService globalOpenapiRateLimitService() {
			return new GlobalOpenapiRateLimitService();
		}
	}

	@Autowired
	private BeanFactory beanFactory;
	/**
	 * 延迟/任务计划执行线程池
	 * @return
	 */
	@Bean(name = global_openapi_scheduled_task_executor, destroyMethod = "shutdown")
	public ScheduledExecutorService globalScheduledTaskExecutor() {
		return CustomExecutors.newScheduledExecutorService(beanFactory,
				global_openapi_scheduled_task_executor,
				5,
				// 如果拒绝自己执行
				new ThreadPoolExecutor.CallerRunsPolicy(),
				false);

	}
}
