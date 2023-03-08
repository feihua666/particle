package com.particle.dataquery.infrastructure.datasource.service;

import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 数据查询数据源 服务类
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
public interface IDataQueryDatasourceService extends IBaseService<DataQueryDatasourceDO> {

    /**
     * 根据编码查询
     * @param code
     * @return
     */
    default DataQueryDatasourceDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<DataQueryDatasourceDO>lambdaQuery().eq(DataQueryDatasourceDO::getCode, code));
    }



    /**
     * 根据编码查询多个
     * @param codes
     * @return
     */
    default List<DataQueryDatasourceDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<DataQueryDatasourceDO>lambdaQuery().in(DataQueryDatasourceDO::getCode, codes));
    }
            






    /**
     * 根据数据查询供应商id查询
     * @param dataQueryProviderId
     * @return
     */
    default List<DataQueryDatasourceDO> getByDataQueryProviderId(Long dataQueryProviderId) {
        Assert.notNull(dataQueryProviderId,"dataQueryProvider 不能为空");
        return list(Wrappers.<DataQueryDatasourceDO>lambdaQuery().eq(DataQueryDatasourceDO::getDataQueryProviderId, dataQueryProviderId));
    }



    /**
     * 根据数据查询供应商id查询多个
     * @param dataQueryProviderIds
     * @return
     */
    default List<DataQueryDatasourceDO> getByDataQueryProviderIds(List<Long> dataQueryProviderIds) {
        Assert.notEmpty(dataQueryProviderIds,"dataQueryProviders 不能为空");
        return list(Wrappers.<DataQueryDatasourceDO>lambdaQuery().in(DataQueryDatasourceDO::getDataQueryProviderId, dataQueryProviderIds));
    }
            









}
