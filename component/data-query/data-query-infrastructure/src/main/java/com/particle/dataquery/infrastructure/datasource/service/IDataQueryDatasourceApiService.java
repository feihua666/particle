package com.particle.dataquery.infrastructure.datasource.service;

import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceApiDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 数据查询数据源接口 服务类
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
public interface IDataQueryDatasourceApiService extends IBaseService<DataQueryDatasourceApiDO> {

    /**
     * 根据编码查询
     * @param code
     * @return
     */
    default DataQueryDatasourceApiDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<DataQueryDatasourceApiDO>lambdaQuery().eq(DataQueryDatasourceApiDO::getCode, code));
    }



    /**
     * 根据编码查询多个
     * @param codes
     * @return
     */
    default List<DataQueryDatasourceApiDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<DataQueryDatasourceApiDO>lambdaQuery().in(DataQueryDatasourceApiDO::getCode, codes));
    }
            



    /**
     * 根据数据查询供应商id查询
     * @param dataQueryProviderId
     * @return
     */
    default List<DataQueryDatasourceApiDO> getByDataQueryProviderId(Long dataQueryProviderId) {
        Assert.notNull(dataQueryProviderId,"dataQueryProviderId 不能为空");
        return list(Wrappers.<DataQueryDatasourceApiDO>lambdaQuery().eq(DataQueryDatasourceApiDO::getDataQueryProviderId, dataQueryProviderId));
    }



    /**
     * 根据数据查询供应商id查询多个
     * @param dataQueryProviderIds
     * @return
     */
    default List<DataQueryDatasourceApiDO> getByDataQueryProviderIds(List<Long> dataQueryProviderIds) {
        Assert.notEmpty(dataQueryProviderIds,"dataQueryProviderIds 不能为空");
        return list(Wrappers.<DataQueryDatasourceApiDO>lambdaQuery().in(DataQueryDatasourceApiDO::getDataQueryProviderId, dataQueryProviderIds));
    }
            


    /**
     * 根据数据查询数据源id查询
     * @param dataQueryDatasourceId
     * @return
     */
    default List<DataQueryDatasourceApiDO> getByDataQueryDatasourceId(Long dataQueryDatasourceId) {
        Assert.notNull(dataQueryDatasourceId,"dataQueryDatasourceId 不能为空");
        return list(Wrappers.<DataQueryDatasourceApiDO>lambdaQuery().eq(DataQueryDatasourceApiDO::getDataQueryDatasourceId, dataQueryDatasourceId));
    }



    /**
     * 根据数据查询数据源id查询多个
     * @param dataQueryDatasourceIds
     * @return
     */
    default List<DataQueryDatasourceApiDO> getByDataQueryDatasourceIds(List<Long> dataQueryDatasourceIds) {
        Assert.notEmpty(dataQueryDatasourceIds,"dataQueryDatasourceIds 不能为空");
        return list(Wrappers.<DataQueryDatasourceApiDO>lambdaQuery().in(DataQueryDatasourceApiDO::getDataQueryDatasourceId, dataQueryDatasourceIds));
    }
            

























}
