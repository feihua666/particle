package com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.executor;

import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.api.config.IBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.executor.AbstractBigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.api.config.ElasticsearchBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.enums.ElasticsearchBigDatasourceApiConfigDataType;

/**
 * <p>
 * elasticsearch 大数据源接口执行器抽象父类
 * </p>
 *
 * @author yangwei
 * @since 2023-12-08 13:32:45
 */
public abstract class AbstractElasticsearchBigDatasourceApiExecutor extends AbstractBigDatasourceApiExecutor {


    /**
     * 分页查询
     * @param bigDatasourceApi
     * @param command
     * @return
     */
    public Object executePage(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {
        return doExecutePage(bigDatasourceApi,command,queryString);
    }

    public abstract Object doExecutePage(BigDatasourceApi bigDatasourceApi, Object command,String queryString);

    /**
     * 多条查询
     * @param bigDatasourceApi
     * @param command
     * @return
     */
    public Object executeMulti(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {
        return doExecuteMulti(bigDatasourceApi,command,queryString);
    }

    public abstract Object doExecuteMulti(BigDatasourceApi bigDatasourceApi, Object command,String queryString);

    /**
     * 单条查询
     * @param bigDatasourceApi
     * @param command
     * @return
     */
    public Object executeSingle(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {

        return doExecuteSingle(bigDatasourceApi,command,queryString);
    }

    public abstract Object doExecuteSingle(BigDatasourceApi bigDatasourceApi, Object command,String queryString);

    @Override
    public Object doExecute(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {
        IBigDatasourceApiConfig config = bigDatasourceApi.config();
        ElasticsearchBigDatasourceApiConfig elasticsearchBigDatasourceApiConfig = (ElasticsearchBigDatasourceApiConfig) config;

        ElasticsearchBigDatasourceApiConfigDataType dataType = elasticsearchBigDatasourceApiConfig.getDataType();
        if (dataType == null) {
            throw new BigDatasourceException("dataType can not be null");
        }

        if (dataType == ElasticsearchBigDatasourceApiConfigDataType.single) {
            return executeSingle(bigDatasourceApi, command,queryString);
        }
        if (dataType == ElasticsearchBigDatasourceApiConfigDataType.multiple) {
            return executeMulti(bigDatasourceApi, command,queryString);
        }
        if (dataType == ElasticsearchBigDatasourceApiConfigDataType.page) {
            return executePage(bigDatasourceApi, command,queryString);
        }

        throw new BigDatasourceException("this is like a bug,because here is unreachable for elasticsearch execute");

    }
}
