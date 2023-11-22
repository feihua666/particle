package com.particle.global.big.datasource.bigdatasource.impl.neo4j.executor;

import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.api.config.IBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.executor.AbstractBigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.impl.neo4j.api.config.Neo4jBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.neo4j.enums.Neo4jBigDatasourceApiConfigDataType;

/**
 * <p>
 * neo4j 大数据源接口执行器抽象父类
 * </p>
 *
 * @author yangwei
 * @since 2023/11/21 09:48
 */
public abstract class AbstractNeo4jBigDatasourceApiExecutor extends AbstractBigDatasourceApiExecutor {


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
        Neo4jBigDatasourceApiConfig neo4jBigDatasourceApiConfig = (Neo4jBigDatasourceApiConfig) config;

        Neo4jBigDatasourceApiConfigDataType dataType = neo4jBigDatasourceApiConfig.getDataType();
        if (dataType == null) {
            throw new BigDatasourceException("dataType can not be null");
        }

        if (dataType == Neo4jBigDatasourceApiConfigDataType.single) {
            return executeSingle(bigDatasourceApi, command,queryString);
        }
        if (dataType == Neo4jBigDatasourceApiConfigDataType.multiple) {
            return executeMulti(bigDatasourceApi, command,queryString);
        }
        if (dataType == Neo4jBigDatasourceApiConfigDataType.page) {
            return executePage(bigDatasourceApi, command,queryString);
        }

        throw new BigDatasourceException("this is like a bug,because here is unreachable for neo4j execute");

    }
}
