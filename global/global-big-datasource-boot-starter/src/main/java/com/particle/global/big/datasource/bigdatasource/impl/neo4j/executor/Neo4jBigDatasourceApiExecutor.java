package com.particle.global.big.datasource.bigdatasource.impl.neo4j.executor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.big.datasource.bigdatasource.AbstractBigDatasource;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.api.config.PageableAdapterConfig;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.executor.JdbcBigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.impl.neo4j.api.config.Neo4jBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.neo4j.executor.Neo4jBigDatasourceApiExecutor;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.tool.template.TemplateRenderDataWrap;
import lombok.Data;
import org.neo4j.driver.Driver;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.core.PreparedQuery;
import org.springframework.data.neo4j.repository.query.QueryFragmentsAndParameters;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.*;
import java.util.function.LongSupplier;

/**
 * <p>
 * neo4j 大数据源接口执行器抽象父类
 * </p>
 *
 * @author yangwei
 * @since 2023/11/21 09:48
 */
@Data
public class Neo4jBigDatasourceApiExecutor extends AbstractNeo4jBigDatasourceApiExecutor {

    private Driver driver;

    private Neo4jClient neo4jClient;

    private Neo4jTemplate neo4jTemplate;

    /**
     * 实例数据，持有一些其它数据以供扩展使用，主要是解决生产和测试或框架系统版本不兼容，放置一些额外数据以做处理
     * 该属性应该是{@link AbstractBigDatasource#instanceMap}的引用
     */
    private Map<String, Object> neo4jBigDatasourceInstanceMap;


    public static Neo4jBigDatasourceApiExecutor create(Driver driver,
                                                       Neo4jClient neo4jClient,
                                                       Neo4jTemplate neo4jTemplate,
                                                       Map<String, Object> neo4jBigDatasourceInstanceMap){
        Neo4jBigDatasourceApiExecutor neo4jBigDatasourceApiExecutor = new Neo4jBigDatasourceApiExecutor();
        neo4jBigDatasourceApiExecutor.setDriver(driver);
        neo4jBigDatasourceApiExecutor.setNeo4jClient(neo4jClient);
        neo4jBigDatasourceApiExecutor.setNeo4jTemplate(neo4jTemplate);
        neo4jBigDatasourceApiExecutor.setNeo4jBigDatasourceInstanceMap(neo4jBigDatasourceInstanceMap);
        // 初始化监听
        neo4jBigDatasourceApiExecutor.executorInfrastructureListenerInitFromSpring();
        neo4jBigDatasourceApiExecutor.bigDatasourceApiExecutorExeCacheInitFromSpring();
        neo4jBigDatasourceApiExecutor.bigDatasourceTransSupportServiceInitFromSpring();

        return neo4jBigDatasourceApiExecutor;
    }

    @Override
    public Object doExecutePage(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {
        Page pageQuery = JdbcBigDatasourceApiExecutor.pageQuery(bigDatasourceApi, command, queryString);
        Neo4jBigDatasourceApiConfig config = (Neo4jBigDatasourceApiConfig) bigDatasourceApi.config();
        Neo4jBigDatasourceApiConfig.RenderResult renderResult = config.render(driver, neo4jClient, neo4jTemplate,neo4jBigDatasourceInstanceMap, command, queryString);
        if (renderResult.getResult() != null) {
            boolean b = renderResult.getResult() instanceof Page;
            if (b) {
                return ((Page<?>) renderResult.getResult());
            } else {
                throw new BigDatasourceException(" neo4j render result must be instance of " + Page.class.getName());
            }
        }
        // 开始处理图库
        PageRequest pageRequest = PageRequest.of((int) (pageQuery.getCurrent() - 1), ((int) pageQuery.getSize()));


        String pageStatementTemplate = " skip {} limit {}";
        Map<String, Object> renderMap = TemplateRenderDataWrap.create(command).toRenderMap();
        PreparedQuery<Map> preparedQuery = PreparedQuery.queryFor(Map.class)
                .withCypherQuery(renderResult.getStrTemplateResult() + StrUtil.format(pageStatementTemplate,pageRequest.getOffset(),pageRequest.getPageSize()))
                .withParameters(renderMap)
                .build();

        List<?> allResult = neo4jTemplate.toExecutableQuery(preparedQuery).getResults();

        // 尝试查询总数
        LongSupplier totalCountSupplier = () -> 0;

        if (StrUtil.isNotEmpty(renderResult.getStrCountTemplateResult())) {
            totalCountSupplier = () -> neo4jTemplate.count(renderResult.getStrCountTemplateResult(), renderMap);
        }

        pageQuery.setTotal(totalCountSupplier.getAsLong());
        pageQuery.setRecords(allResult);
        return pageQuery;
    }

    @Override
    public Object doExecuteMulti(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {
        Neo4jBigDatasourceApiConfig config = (Neo4jBigDatasourceApiConfig) bigDatasourceApi.config();
        Neo4jBigDatasourceApiConfig.RenderResult renderResult = config.render(driver,neo4jClient,neo4jTemplate, neo4jBigDatasourceInstanceMap,command,queryString);
        if (renderResult.getResult() != null) {
            boolean b = renderResult.getResult() instanceof Collection;
            if (b) {
                return ((Collection) renderResult.getResult());
            }else {
                throw new BigDatasourceException(" neo4j render result must be instance of " + Collection.class.getName());
            }
        }
        Map<String, Object> renderMap = TemplateRenderDataWrap.create(command).toRenderMap();
        PreparedQuery<Map> preparedQuery = PreparedQuery.queryFor(Map.class)
                .withCypherQuery(renderResult.getStrTemplateResult())
                .withParameters(renderMap)
                .build();
        List<?> allResult = neo4jTemplate.toExecutableQuery(preparedQuery).getResults();
        return allResult;
    }

    @Override
    public Object doExecuteSingle(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {
        Neo4jBigDatasourceApiConfig config = (Neo4jBigDatasourceApiConfig) bigDatasourceApi.config();
        Neo4jBigDatasourceApiConfig.RenderResult renderResult = config.render(driver,neo4jClient,neo4jTemplate,neo4jBigDatasourceInstanceMap, command,queryString);
        if (renderResult.getResult() != null) {
            return renderResult.getResult();
        }
        Map<String, Object> renderMap = TemplateRenderDataWrap.create(command).toRenderMap();
        PreparedQuery<Map> preparedQuery = PreparedQuery.queryFor(Map.class)
                .withCypherQuery(renderResult.getStrTemplateResult())
                .withParameters(renderMap)
                .build();
        Map result = neo4jTemplate.toExecutableQuery(preparedQuery).getSingleResult().orElse(null);
        return result;
    }
}
