package com.particle.global.messaging.binder.local;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binder.Binder;
import org.springframework.cloud.stream.binder.ConsumerProperties;
import org.springframework.cloud.stream.binder.ProducerProperties;
import org.springframework.cloud.stream.config.BinderFactoryAutoConfiguration;
import org.springframework.cloud.stream.config.BindingServiceConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.integration.config.EnableIntegration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
/**
 * <p>
 * https://github.com/spring-cloud/spring-cloud-stream/blob/v3.2.8/core/spring-cloud-stream/src/test/java/org/springframework/cloud/stream/binder/test/TestChannelBinderConfiguration.java
 * </p>
 *
 * @author yangwei
 * @since 2023-05-22 10:45
 */
@Configuration
@ConditionalOnMissingBean(Binder.class)
@Import(BinderFactoryAutoConfiguration.class)
@EnableIntegration
public class LocalChannelBinderConfiguration<T> {

	// 改了这里，换成了local
	//public static final String NAME = "integration";
	public static final String NAME = "local";

	/**
	 * Utility operation to return an array of configuration classes defined in
	 * {@link EnableBinding} annotation. Typically used for tests that do not rely on
	 * creating an SCSt boot application annotated with {@link EnableBinding}, yet require
	 * full {@link Binder} configuration.
	 * @param additionalConfigurationClasses config classes to be added to the default
	 * config
	 * @return an array of configuration classes defined in {@link EnableBinding}
	 * annotation
	 */
	public static Class<?>[] getCompleteConfiguration(
			Class<?>... additionalConfigurationClasses) {
		List<Class<?>> configClasses = new ArrayList<>();
		configClasses.add(LocalChannelBinderConfiguration.class);
		Import annotation = AnnotationUtils.getAnnotation(EnableBinding.class,
				Import.class);
		Map<String, Object> annotationAttributes = AnnotationUtils
				.getAnnotationAttributes(annotation);
		configClasses
				.addAll(Arrays.asList((Class<?>[]) annotationAttributes.get("value")));
		configClasses.add(BindingServiceConfiguration.class);
		if (additionalConfigurationClasses != null) {
			configClasses.addAll(Arrays.asList(additionalConfigurationClasses));
		}
		return configClasses.toArray(new Class<?>[] {});
	}

	/**
	 * Create an {@link ApplicationContextRunner} with user configuration using {@link #getCompleteConfiguration}.
	 * @param additionalConfigurationClasses config classes to be added to the default
	 * config
	 * @return the ApplicationContextRunner
	 */
//	改了这里，注释掉了
/*	public static ApplicationContextRunner applicationContextRunner(Class<?>... additionalConfigurationClasses) {
		return new ApplicationContextRunner()
				.withUserConfiguration(getCompleteConfiguration(additionalConfigurationClasses));
	}*/

	@Bean
	public LocalInputDestination sourceDestination() {
		return new LocalInputDestination();
	}

	@Bean
	public LocalOutputDestination targetDestination() {
		return new LocalOutputDestination();
	}

	@SuppressWarnings("unchecked")
	@Bean
	public Binder<T, ? extends ConsumerProperties, ? extends ProducerProperties> springIntegrationChannelBinder(
			LocalChannelBinderProvisioner provisioner) {
		return (Binder<T, ? extends ConsumerProperties, ? extends ProducerProperties>) new LocalChannelBinder(
				provisioner);
	}

	@Bean
	public LocalChannelBinderProvisioner springIntegrationProvisioner() {
		return new LocalChannelBinderProvisioner();
	}

}
