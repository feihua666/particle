package com.particle.dataquery.infrastructure.datasource.gateway.impl.bigdatasource;

import com.particle.dataquery.domain.datasource.gateway.bigdatasource.DataQueryDatasourceDynamicBigDatasourceGateway;
import com.particle.dataquery.infrastructure.datasource.bigdatasource.DataQueryDatasourceDynamicBigDatasourceProvider;
import com.particle.dataquery.infrastructure.datasource.bigdatasource.DataQueryDatasourceDynamicBigDatasourceRoutingFallback;
import com.particle.global.big.datasource.bigdatasource.BigDatasource;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasource;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKey;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKeyFactory;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.JdbcBigDatasource;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>
 * 数据查询中的数据源，映射到大数据源相关操作实现
 * </p>
 *
 * @author yangwei
 * @since 2025/5/13 10:52
 */
@Component
public class DataQueryDatasourceDynamicBigDatasourceGatewayImpl implements DataQueryDatasourceDynamicBigDatasourceGateway {

    private DataQueryDatasourceDynamicBigDatasourceRoutingFallback dataQueryDatasourceDynamicBigDatasourceRoutingFallback;
    private DynamicBigDatasource dynamicBigDatasource;

    @SneakyThrows
    @Override
    public boolean reloadDataQueryDatasource(Long dataQueryDatasourceId, boolean removeOnly) {
        // 先加载大数据源
        BigDatasource bigDatasource = dataQueryDatasourceDynamicBigDatasourceRoutingFallback.getBigDatasourceByDataQueryDatasourceId(dataQueryDatasourceId,true);
        if (bigDatasource == null) {
            return false;
        }
        Map<DynamicBigDatasourceRoutingKey, BigDatasource> bigDatasourceMap = dynamicBigDatasource.getBigDatasourceMap();
        // jdbc数据源的特殊情况，单独处理
        if (bigDatasource instanceof JdbcBigDatasource) {
            DynamicBigDatasourceRoutingKey jdbcBigDatasourceRoutingKey = DynamicBigDatasourceRoutingKeyFactory.of(DataQueryDatasourceDynamicBigDatasourceProvider.dataQueryGlobalBigDatasourceRoutingKey);
            BigDatasource jdbcBigDatasouce = bigDatasourceMap.get(jdbcBigDatasourceRoutingKey);
            if (jdbcBigDatasouce == null) {
                if (!removeOnly) {
                    dynamicBigDatasource.addBigDatasource(jdbcBigDatasourceRoutingKey,bigDatasource);
                }
            }else {
                ((JdbcBigDatasource) jdbcBigDatasouce).removeDataSource(dataQueryDatasourceId.toString());
                if (!removeOnly) {
                    dynamicBigDatasource.addBigDatasource(jdbcBigDatasourceRoutingKey,bigDatasource);
                }
            }
        }
        // 其它数据源，先删除再添加，移除后，如果有程序正在调用，可能会有路由不到数据源的情况，这里会走routingFallback逻辑
        else{
            DynamicBigDatasourceRoutingKey dynamicBigDatasourceRoutingKey = DynamicBigDatasourceRoutingKeyFactory.of(dataQueryDatasourceId.toString());
            dynamicBigDatasource.removeBigDatasource(dynamicBigDatasourceRoutingKey);
            if (!removeOnly) {
                dynamicBigDatasource.addBigDatasource(dynamicBigDatasourceRoutingKey,bigDatasource);
            }
        }

        return true;
    }

    @Autowired
    public void setDataQueryDatasourceDynamicBigDatasourceRoutingFallback(DataQueryDatasourceDynamicBigDatasourceRoutingFallback dataQueryDatasourceDynamicBigDatasourceRoutingFallback) {
        this.dataQueryDatasourceDynamicBigDatasourceRoutingFallback = dataQueryDatasourceDynamicBigDatasourceRoutingFallback;
    }
    @Autowired
    public void setDynamicBigDatasource(DynamicBigDatasource dynamicBigDatasource) {
        this.dynamicBigDatasource = dynamicBigDatasource;
    }
}
