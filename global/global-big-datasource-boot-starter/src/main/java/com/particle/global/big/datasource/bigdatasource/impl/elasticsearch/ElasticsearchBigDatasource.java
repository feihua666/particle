package com.particle.global.big.datasource.bigdatasource.impl.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.particle.global.big.datasource.bigdatasource.AbstractBigDatasource;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceType;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.impl.BigDatasourceEmptyObjectProvider;
import com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.config.ElasticsearchBigDatasourceConfig;
import com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.custom.CustomElasticsearchClientConfigurations;
import com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.custom.CustomElasticsearchDataConfiguration;
import com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.custom.CustomElasticsearchRestClientConfigurations;
import com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.executor.ElasticsearchBigDatasourceApiExecutor;
import com.particle.global.tool.spring.SpringContextHolder;
import lombok.Data;
import lombok.SneakyThrows;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.boot.autoconfigure.elasticsearch.RestClientBuilderCustomizer;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.ApplicationContext;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext;

import java.io.IOException;

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

    protected ElasticsearchTemplate elasticsearchTemplate;


    protected ElasticsearchClient elasticsearchClient;

    protected RestClient restClient;

    private ElasticsearchBigDatasourceApiExecutor elasticsearchBigDatasourceApiExecutor;

    @Override
    public BigDatasourceApiExecutor getApiExecutor() throws BigDatasourceException {
        return elasticsearchBigDatasourceApiExecutor;
    }

    @Override
    public void close() throws IOException {
        restClient.close();
    }

    /**
     * 创建数据源实例
     * @param elasticsearchProperties
     * @return
     */
    @SneakyThrows
    public static ElasticsearchBigDatasource create(String name, BigDatasourceType type, ElasticsearchProperties elasticsearchProperties,SslBundles sslBundles) {

        ElasticsearchBigDatasource elasticsearchBigDatasource = new ElasticsearchBigDatasource();
        elasticsearchBigDatasource.setName(name);
        elasticsearchBigDatasource.setType(type);

        CustomElasticsearchRestClientConfigurations.RestClientBuilderConfiguration restClientBuilderConfiguration = new CustomElasticsearchRestClientConfigurations.RestClientBuilderConfiguration(elasticsearchProperties);
        CustomElasticsearchRestClientConfigurations.PropertiesElasticsearchConnectionDetails propertiesElasticsearchConnectionDetails = restClientBuilderConfiguration.elasticsearchConnectionDetails();
        RestClientBuilderCustomizer defaultRestClientBuilderCustomizer = restClientBuilderConfiguration.defaultRestClientBuilderCustomizer(propertiesElasticsearchConnectionDetails);
        // builder
        RestClientBuilder elasticsearchRestClientBuilder = restClientBuilderConfiguration.elasticsearchRestClientBuilder(
                propertiesElasticsearchConnectionDetails,
                BigDatasourceEmptyObjectProvider.create(defaultRestClientBuilderCustomizer),
                BigDatasourceEmptyObjectProvider.create(sslBundles)
                );
        CustomElasticsearchRestClientConfigurations.RestClientConfiguration restClientConfiguration = new CustomElasticsearchRestClientConfigurations.RestClientConfiguration();
        // restClient
        RestClient restClient = restClientConfiguration.elasticsearchRestClient(elasticsearchRestClientBuilder);

        CustomElasticsearchClientConfigurations.JacksonJsonpMapperConfiguration jacksonJsonpMapperConfiguration = new CustomElasticsearchClientConfigurations.JacksonJsonpMapperConfiguration();
        JacksonJsonpMapper jacksonJsonpMapper = jacksonJsonpMapperConfiguration.jacksonJsonpMapper();

        CustomElasticsearchClientConfigurations.ElasticsearchTransportConfiguration elasticsearchTransportConfiguration = new CustomElasticsearchClientConfigurations.ElasticsearchTransportConfiguration();
        RestClientTransport restClientTransport = elasticsearchTransportConfiguration.restClientTransport(restClient, jacksonJsonpMapper, BigDatasourceEmptyObjectProvider.create());

        CustomElasticsearchClientConfigurations.ElasticsearchClientConfiguration elasticsearchClientConfiguration = new CustomElasticsearchClientConfigurations.ElasticsearchClientConfiguration();

        // elasticsearchClient
        ElasticsearchClient elasticsearchClient = elasticsearchClientConfiguration.elasticsearchClient(restClientTransport);

        elasticsearchBigDatasource.elasticsearchClient = elasticsearchClient;
        elasticsearchBigDatasource.restClient = restClient;


        CustomElasticsearchDataConfiguration.BaseConfiguration baseConfiguration = new CustomElasticsearchDataConfiguration.BaseConfiguration();
        ElasticsearchCustomConversions elasticsearchCustomConversions = baseConfiguration.elasticsearchCustomConversions();

        ApplicationContext applicationContext = null;
        try {
            applicationContext = SpringContextHolder.getApplicationContext();
        } catch (Exception e) {
        }

        SimpleElasticsearchMappingContext simpleElasticsearchMappingContext = baseConfiguration.elasticsearchMappingContext(applicationContext, elasticsearchCustomConversions);

        ElasticsearchConverter elasticsearchConverter = baseConfiguration.elasticsearchConverter(simpleElasticsearchMappingContext, elasticsearchCustomConversions);

        // elasticsearchTemplate
        CustomElasticsearchDataConfiguration.JavaClientConfiguration javaClientConfiguration = new CustomElasticsearchDataConfiguration.JavaClientConfiguration();

        ElasticsearchTemplate elasticsearchTemplate = javaClientConfiguration.elasticsearchTemplate(elasticsearchClient, elasticsearchConverter);
        elasticsearchBigDatasource.elasticsearchTemplate = elasticsearchTemplate;


        ElasticsearchBigDatasourceApiExecutor elasticsearchBigDatasourceApiExecutor = ElasticsearchBigDatasourceApiExecutor.create(elasticsearchTemplate, elasticsearchClient, restClient,null);
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

        return create(name, type, elasticsearchBigDatasourceConfig.getElasticsearchProperties(),elasticsearchBigDatasourceConfig.getSslBundles());
    }

}
