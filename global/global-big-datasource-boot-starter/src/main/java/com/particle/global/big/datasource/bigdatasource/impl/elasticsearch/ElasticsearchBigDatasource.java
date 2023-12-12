package com.particle.global.big.datasource.bigdatasource.impl.elasticsearch;

import com.particle.global.big.datasource.bigdatasource.AbstractBigDatasource;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceType;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.config.ElasticsearchBigDatasourceConfig;
import com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.executor.ElasticsearchBigDatasourceApiExecutor;
import com.particle.global.tool.spring.SpringContextHolder;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.domain.EntityScanner;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.boot.autoconfigure.elasticsearch.RestClientBuilderCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;
import org.springframework.data.elasticsearch.core.convert.MappingElasticsearchConverter;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * <p>
 * 基于elasticsearch的数据源实现，该数据源旨在查询es
 * </p>
 *
 * @author yangwei
 * @since 2023-12-07 17:47:54
 */
@Data
public class ElasticsearchBigDatasource extends AbstractBigDatasource {

    protected ElasticsearchRestTemplate elasticsearchRestTemplate;

    protected RestHighLevelClient restHighLevelClient;

    protected RestClient restClient;

    private ElasticsearchBigDatasourceApiExecutor elasticsearchBigDatasourceApiExecutor;

    @Override
    public BigDatasourceApiExecutor getApiExecutor() throws BigDatasourceException {
        return elasticsearchBigDatasourceApiExecutor;
    }

    @Override
    public void close() throws IOException {
        restHighLevelClient.close();
    }

    /**
     * 创建数据源实例
     * @param elasticsearchProperties
     * @return
     */
    @SneakyThrows
    public static ElasticsearchBigDatasource create(String name, BigDatasourceType type, ElasticsearchProperties elasticsearchProperties) {

        ElasticsearchBigDatasource elasticsearchBigDatasource = new ElasticsearchBigDatasource();
        elasticsearchBigDatasource.setName(name);
        elasticsearchBigDatasource.setType(type);

        RestClientBuilder restClientBuilder = elasticsearchRestClientBuilder(elasticsearchProperties, BigDatasourceEmptyObjectProvider.create());
        RestHighLevelClient restHighLevelClient = elasticsearchRestHighLevelClient(restClientBuilder);
        elasticsearchBigDatasource.restHighLevelClient = restHighLevelClient;
        RestClient lowLevelClient = restHighLevelClient.getLowLevelClient();
        elasticsearchBigDatasource.restClient = lowLevelClient;

        ElasticsearchCustomConversions elasticsearchCustomConversions = elasticsearchCustomConversions();

        ApplicationContext applicationContext = null;
        try {
            applicationContext = SpringContextHolder.getApplicationContext();
        } catch (Exception e) {
        }
        SimpleElasticsearchMappingContext simpleElasticsearchMappingContext = mappingContext(applicationContext, elasticsearchCustomConversions);
        ElasticsearchConverter elasticsearchConverter = elasticsearchConverter(simpleElasticsearchMappingContext,elasticsearchCustomConversions);

        ElasticsearchRestTemplate elasticsearchRestTemplate = elasticsearchTemplate(restHighLevelClient, elasticsearchConverter);
        elasticsearchBigDatasource.elasticsearchRestTemplate = elasticsearchRestTemplate;


        ElasticsearchBigDatasourceApiExecutor elasticsearchBigDatasourceApiExecutor = ElasticsearchBigDatasourceApiExecutor.create(elasticsearchRestTemplate, restHighLevelClient, lowLevelClient,null);
        elasticsearchBigDatasource.setElasticsearchBigDatasourceApiExecutor(elasticsearchBigDatasourceApiExecutor);


        return elasticsearchBigDatasource;
    }

    /**
     * 根据配置创建数据源实例
     * @param name
     * @param type
     * @param elasticsearchBigDatasourceConfig
     * @return
     */
    public static ElasticsearchBigDatasource createByElasticsearchBigDatasourceConfig(String name, BigDatasourceType type, ElasticsearchBigDatasourceConfig elasticsearchBigDatasourceConfig) {

        return create(name, type, elasticsearchBigDatasourceConfig.getElasticsearchProperties());
    }

    /**
     * 参考了 {@link org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientConfigurations.RestClientBuilderConfiguration#elasticsearchRestClientBuilder(ObjectProvider)}
     * @param builderCustomizers
     * @return
     */
    private static RestClientBuilder elasticsearchRestClientBuilder(ElasticsearchProperties properties,
            ObjectProvider<RestClientBuilderCustomizer> builderCustomizers) {
        HttpHost[] hosts = properties.getUris().stream().map(ElasticsearchBigDatasource::createHttpHost).toArray(HttpHost[]::new);
        RestClientBuilder builder = RestClient.builder(hosts);
        builder.setHttpClientConfigCallback((httpClientBuilder) -> {
            builderCustomizers.orderedStream().forEach((customizer) -> customizer.customize(httpClientBuilder));
            return httpClientBuilder;
        });
        builder.setRequestConfigCallback((requestConfigBuilder) -> {
            builderCustomizers.orderedStream().forEach((customizer) -> customizer.customize(requestConfigBuilder));
            return requestConfigBuilder;
        });
        if (properties.getPathPrefix() != null) {
            builder.setPathPrefix(properties.getPathPrefix());
        }
        builderCustomizers.orderedStream().forEach((customizer) -> customizer.customize(builder));
        return builder;
    }


    /**
     * {@link org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientConfigurations.RestClientBuilderConfiguration#createHttpHost(String)}
     * @param uri
     * @return
     */
    private static HttpHost createHttpHost(String uri) {
        try {
            return createHttpHost(URI.create(uri));
        }
        catch (IllegalArgumentException ex) {
            return HttpHost.create(uri);
        }
    }

    /**
     * {@link org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientConfigurations.RestClientBuilderConfiguration#createHttpHost(URI)}
     * @param uri
     * @return
     */
    private static HttpHost createHttpHost(URI uri) {
        if (!StringUtils.hasLength(uri.getUserInfo())) {
            return HttpHost.create(uri.toString());
        }
        try {
            return HttpHost.create(new URI(uri.getScheme(), null, uri.getHost(), uri.getPort(), uri.getPath(),
                    uri.getQuery(), uri.getFragment())
                    .toString());
        }
        catch (URISyntaxException ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * {@link org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientConfigurations.RestHighLevelClientConfiguration#elasticsearchRestHighLevelClient(RestClientBuilder)}
     * @param restClientBuilder
     * @return
     */
    private static org.elasticsearch.client.RestHighLevelClient elasticsearchRestHighLevelClient(
            RestClientBuilder restClientBuilder) {
        return new org.elasticsearch.client.RestHighLevelClient(restClientBuilder);
    }


    /**
     * {@link org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataConfiguration.BaseConfiguration#elasticsearchCustomConversions()}
     * @return
     */
    private static ElasticsearchCustomConversions elasticsearchCustomConversions() {
        return new ElasticsearchCustomConversions(Collections.emptyList());
    }

    /**
     * {@link org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataConfiguration.BaseConfiguration#mappingContext(ApplicationContext, ElasticsearchCustomConversions)}
     * @param applicationContext
     * @param elasticsearchCustomConversions
     * @return
     * @throws ClassNotFoundException
     */
    private static SimpleElasticsearchMappingContext mappingContext(ApplicationContext applicationContext,
                                                     ElasticsearchCustomConversions elasticsearchCustomConversions) throws ClassNotFoundException {
        SimpleElasticsearchMappingContext mappingContext = new SimpleElasticsearchMappingContext();
        if (applicationContext != null) {
            mappingContext.setInitialEntitySet(new EntityScanner(applicationContext).scan(Document.class));
        }
        mappingContext.setSimpleTypeHolder(elasticsearchCustomConversions.getSimpleTypeHolder());
        return mappingContext;
    }

    /**
     * {@link org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataConfiguration.BaseConfiguration#elasticsearchConverter(SimpleElasticsearchMappingContext, ElasticsearchCustomConversions)}
     * @param mappingContext
     * @param elasticsearchCustomConversions
     * @return
     */
    private static ElasticsearchConverter elasticsearchConverter(SimpleElasticsearchMappingContext mappingContext,
                                                  ElasticsearchCustomConversions elasticsearchCustomConversions) {
        MappingElasticsearchConverter converter = new MappingElasticsearchConverter(mappingContext);
        converter.setConversions(elasticsearchCustomConversions);
        return converter;
    }

    /**
     * {@link org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataConfiguration.RestClientConfiguration#elasticsearchTemplate(RestHighLevelClient, ElasticsearchConverter)}
     * @param client
     * @param converter
     * @return
     */
    private static ElasticsearchRestTemplate elasticsearchTemplate(org.elasticsearch.client.RestHighLevelClient client,
                                                    ElasticsearchConverter converter) {
        return new ElasticsearchRestTemplate(client, converter);
    }
    /**
     * 实现一个空实现，以实现调用{@link ElasticsearchBigDatasource#elasticsearchRestClientBuilder(ElasticsearchProperties, ObjectProvider)} 的逻辑
     * @param <T>
     */
    private static class BigDatasourceEmptyObjectProvider<T> implements ObjectProvider<T> {

        public T getObject(Object... args) throws BeansException {
            return null;
        }

        public T getIfAvailable() throws BeansException {
            return null;
        }

        public T getIfUnique() throws BeansException {
            return null;
        }

        public T getObject() throws BeansException {
            return null;
        }

        public void forEach(Consumer action) {
        }

        @Override
        public Stream<T> stream() {
            return Stream.empty();
        }

        @Override
        public Stream<T> orderedStream() {
            return Stream.empty();
        }

        /**
         * 创建实例
         * @return
         * @param <T>
         */
        protected static <T> ElasticsearchBigDatasource.BigDatasourceEmptyObjectProvider<T> create() {
            return new ElasticsearchBigDatasource.BigDatasourceEmptyObjectProvider<T>();
        }
    }
}
