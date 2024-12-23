package com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.custom;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;
import org.springframework.data.elasticsearch.core.convert.MappingElasticsearchConverter;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext;

import java.util.Collections;

/**
 * <p>
 * 参考：{@link org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataConfiguration}
 * </p>
 *
 * @author yangwei
 * @since 2024/12/9 21:04
 */
public class CustomElasticsearchDataConfiguration {

    @Configuration(proxyBeanMethods = false)
    public static class BaseConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public ElasticsearchCustomConversions elasticsearchCustomConversions() {
            return new ElasticsearchCustomConversions(Collections.emptyList());
        }

        @Bean
        @ConditionalOnMissingBean
        public SimpleElasticsearchMappingContext elasticsearchMappingContext(ApplicationContext applicationContext,
                                                                      ElasticsearchCustomConversions elasticsearchCustomConversions) throws ClassNotFoundException {
            SimpleElasticsearchMappingContext mappingContext = new SimpleElasticsearchMappingContext();
            mappingContext.setInitialEntitySet(new EntityScanner(applicationContext).scan(Document.class));
            mappingContext.setSimpleTypeHolder(elasticsearchCustomConversions.getSimpleTypeHolder());
            return mappingContext;
        }

        @Bean
        @ConditionalOnMissingBean
        public ElasticsearchConverter elasticsearchConverter(SimpleElasticsearchMappingContext mappingContext,
                                                      ElasticsearchCustomConversions elasticsearchCustomConversions) {
            MappingElasticsearchConverter converter = new MappingElasticsearchConverter(mappingContext);
            converter.setConversions(elasticsearchCustomConversions);
            return converter;
        }

    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(ElasticsearchClient.class)
    public static class JavaClientConfiguration {

        @Bean
        @ConditionalOnMissingBean(value = ElasticsearchOperations.class, name = "elasticsearchTemplate")
        @ConditionalOnBean(ElasticsearchClient.class)
        public ElasticsearchTemplate elasticsearchTemplate(ElasticsearchClient client, ElasticsearchConverter converter) {
            return new ElasticsearchTemplate(client, converter);
        }

    }

    @Configuration(proxyBeanMethods = false)
    static class ReactiveRestClientConfiguration {

        @Bean
        @ConditionalOnMissingBean(value = ReactiveElasticsearchOperations.class, name = "reactiveElasticsearchTemplate")
        @ConditionalOnBean(ReactiveElasticsearchClient.class)
        ReactiveElasticsearchTemplate reactiveElasticsearchTemplate(ReactiveElasticsearchClient client,
                                                                    ElasticsearchConverter converter) {
            return new ReactiveElasticsearchTemplate(client, converter);
        }

    }

}
