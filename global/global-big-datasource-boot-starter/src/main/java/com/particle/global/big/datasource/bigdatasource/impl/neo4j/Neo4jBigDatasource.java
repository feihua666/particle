package com.particle.global.big.datasource.bigdatasource.impl.neo4j;

import com.particle.global.big.datasource.bigdatasource.AbstractBigDatasource;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceType;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceExecutor;
import com.particle.global.big.datasource.bigdatasource.impl.neo4j.config.Neo4jBigDatasourceConfig;
import com.particle.global.big.datasource.bigdatasource.impl.neo4j.config.Neo4jDriverFallback;
import com.particle.global.big.datasource.bigdatasource.impl.neo4j.executor.Neo4jBigDatasourceApiExecutor;
import com.particle.global.tool.spring.SpringContextHolder;
import lombok.Data;
import org.neo4j.driver.Driver;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataProperties;
import org.springframework.boot.autoconfigure.neo4j.ConfigBuilderCustomizer;
import org.springframework.boot.autoconfigure.neo4j.Neo4jAutoConfiguration;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.core.DatabaseSelectionProvider;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * <p>
 * 基于neo4j的数据源实现，该数据源旨在查询图数据库
 * </p>
 *
 * @author yangwei
 * @since 2023/11/17 10:34
 */
@Data
public class Neo4jBigDatasource extends AbstractBigDatasource {

    /**
     * neo4j原生驱动实例，参考{@link Neo4jBigDatasource#neo4jDriver(Neo4jProperties)} 创建
     */
    private Driver driver;

    private Neo4jClient neo4jClient;

    private Neo4jTemplate neo4jTemplate;

    private Neo4jBigDatasourceApiExecutor neo4jBigDatasourceApiExecutor;

    @Override
    public BigDatasourceApiExecutor getApiExecutor() throws BigDatasourceException {
        return neo4jBigDatasourceApiExecutor;
    }

    @Override
    public void close() throws IOException {
        driver.close();
    }

    /**
     * 创建数据源实例
     * @param neo4jProperties
     * @return
     */
    public static Neo4jBigDatasource create(String name, BigDatasourceType type,Neo4jProperties neo4jProperties, Neo4jDataProperties neo4jDataProperties) {

        Neo4jBigDatasource neo4jBigDatasource = new Neo4jBigDatasource();
        neo4jBigDatasource.setName(name);
        neo4jBigDatasource.setType(type);

        Driver neo4jDriver = neo4jDriver(neo4jProperties);
        neo4jBigDatasource.setDriver(neo4jDriver);

        // 借助 Neo4jDataAutoConfiguration 自动配置类中的方法构建需要的实例
        Neo4jDataAutoConfiguration neo4jDataAutoConfigurationHelper = new Neo4jDataAutoConfiguration();

        DatabaseSelectionProvider databaseSelectionProvider = neo4jDataAutoConfigurationHelper.databaseSelectionProvider(neo4jDataProperties);
        Neo4jClient neo4jClient = neo4jDataAutoConfigurationHelper.neo4jClient(neo4jDriver, databaseSelectionProvider);
        neo4jBigDatasource.setNeo4jClient(neo4jClient);

        Neo4jTemplate neo4jTemplate = new Neo4jTemplate(neo4jClient);
        neo4jBigDatasource.setNeo4jTemplate(neo4jTemplate);

        Neo4jBigDatasourceApiExecutor neo4jBigDatasourceApiExecutor = Neo4jBigDatasourceApiExecutor.create(neo4jDriver, neo4jClient, neo4jTemplate);
        neo4jBigDatasource.setNeo4jBigDatasourceApiExecutor(neo4jBigDatasourceApiExecutor);


        return neo4jBigDatasource;
    }

    /**
     * 根据配置创建数据源实例
     * @param name
     * @param type
     * @param neo4jBigDatasourceConfig
     * @return
     */
    public static Neo4jBigDatasource createByNeo4jBigDatasourceConfig(String name, BigDatasourceType type, Neo4jBigDatasourceConfig neo4jBigDatasourceConfig) {

        return create(name, type, neo4jBigDatasourceConfig.getNeo4jProperties(), neo4jBigDatasourceConfig.getNeo4jDataProperties());
    }


        /**
         * neo4j driver
         * 参考 {@link Neo4jAutoConfiguration} 实现实例的{@link Driver}创建
         * @param properties
         * @return
         */
    private static Driver neo4jDriver(Neo4jProperties properties) {
        Neo4jAutoConfiguration neo4jAutoConfiguration = new Neo4jAutoConfiguration();

        Environment environment = BigDatasourceNeo4jDriverEnvironment.create();
        ObjectProvider<ConfigBuilderCustomizer> configBuilderCustomizers = BigDatasourceEmptyObjectProvider.create();

        try {
            return neo4jAutoConfiguration.neo4jDriver(properties, environment, configBuilderCustomizers);
        } catch (Throwable e) {
            // 发现 本项spring间接引入的版本和生产server版本有差异，这里兼容一下，进行额外处理，主要是排除对应的包
            Neo4jDriverFallback bean = null;
            try {
                bean = SpringContextHolder.getBean(Neo4jDriverFallback.class);
            } catch (Exception ex) {
            }
            if (bean != null) {
                return bean.neo4jDriver(properties, e);
            }
            throw e;
        }
    }


    /**
     * 实现一个空环境，以实现{@link Neo4jBigDatasource#neo4jDriver(Neo4jProperties)} 的逻辑
     */
    private static class BigDatasourceNeo4jDriverEnvironment extends AbstractEnvironment {
        public static BigDatasourceNeo4jDriverEnvironment create() {
            return new BigDatasourceNeo4jDriverEnvironment();
        }
    }

    /**
     * 实现一个空实现，以实现{@link Neo4jBigDatasource#neo4jDriver(Neo4jProperties)} 的逻辑
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
        protected static <T> BigDatasourceEmptyObjectProvider<T> create() {
            return new BigDatasourceEmptyObjectProvider<T>();
        }
    }
}
