package com.particle.global.freemarker;


import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * <p>
 * 全局freemarker相关自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2024-10-24 14:35:43
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan
public class GlobalFreemarkerAutoConfiguration {

    @Autowired(required = false)
    private List<GlobalFreemarkerCustomConfigure> globalFreemarkerCustomConfigures;
    /**
     * 前面需要带 斜杠
     */
    @Value("${server.servlet.context-path:}")
    private String contextPath;
    @Bean
    public GlobalFreemarkerConfig globalFreemarkerConfig(freemarker.template.Configuration configuration) throws TemplateModelException {
        if (globalFreemarkerCustomConfigures != null) {
            for (GlobalFreemarkerCustomConfigure globalFreemarkerCustomConfigure : globalFreemarkerCustomConfigures) {
                globalFreemarkerCustomConfigure.customConfigure(configuration);
            }
        }
        configuration.setSharedVariable("contextPath", contextPath);
        configuration.setSharedVariable("contextPathSimple", "/".equals(contextPath) ? null : contextPath);
        // 设置 ObjectWrapper 并启用 useAdaptersForContainers
        DefaultObjectWrapperBuilder owb = new DefaultObjectWrapperBuilder(freemarker.template.Configuration.VERSION_2_3_0);
        owb.setUseAdaptersForContainers(true);

        configuration.setObjectWrapper(owb.build());
        return new GlobalFreemarkerConfig();
    }
}
