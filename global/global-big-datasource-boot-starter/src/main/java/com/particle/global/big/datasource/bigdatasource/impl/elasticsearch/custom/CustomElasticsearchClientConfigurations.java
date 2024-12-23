package com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.custom;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.JsonpMapper;
import co.elastic.clients.json.SimpleJsonpMapper;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientOptions;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>
 * 参考：{@link org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchClientConfigurations}
 * </p>
 *
 * @author yangwei
 * @since 2024/12/9 20:15
 */
public class CustomElasticsearchClientConfigurations {

    @Import({ JacksonJsonpMapperConfiguration.class,
            // JsonbJsonpMapperConfiguration.class,
            SimpleJsonpMapperConfiguration.class })
    static class JsonpMapperConfiguration {

    }

    @ConditionalOnMissingBean(JsonpMapper.class)
    @ConditionalOnClass(ObjectMapper.class)
    @Configuration(proxyBeanMethods = false)
    public static class JacksonJsonpMapperConfiguration {

        @Bean
        public JacksonJsonpMapper jacksonJsonpMapper() {
            return new JacksonJsonpMapper();
        }

    }

    // @ConditionalOnMissingBean(JsonpMapper.class)
    // @ConditionalOnBean(Jsonb.class)
    // @Configuration(proxyBeanMethods = false)
    // static class JsonbJsonpMapperConfiguration {
    //
    //     @Bean
    //     JsonbJsonpMapper jsonbJsonpMapper(Jsonb jsonb) {
    //         return new JsonbJsonpMapper(JsonProvider.provider(), jsonb);
    //     }
    //
    // }

    @ConditionalOnMissingBean(JsonpMapper.class)
    @Configuration(proxyBeanMethods = false)
    static class SimpleJsonpMapperConfiguration {

        @Bean
        SimpleJsonpMapper simpleJsonpMapper() {
            return new SimpleJsonpMapper();
        }

    }

    @ConditionalOnMissingBean(ElasticsearchTransport.class)
    public static class ElasticsearchTransportConfiguration {

        @Bean
        public RestClientTransport restClientTransport(RestClient restClient, JsonpMapper jsonMapper,
                                                ObjectProvider<RestClientOptions> restClientOptions) {
            return new RestClientTransport(restClient, jsonMapper, restClientOptions.getIfAvailable());
        }

    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnBean(ElasticsearchTransport.class)
    public static class ElasticsearchClientConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public ElasticsearchClient elasticsearchClient(ElasticsearchTransport transport) {
            return new ElasticsearchClient(transport);
        }

    }

}
