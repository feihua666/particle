package com.particle.dataconstraint.infrastructure.service;

import com.particle.dataconstraint.infrastructure.dos.DataScopeCustomDataRelDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 数据范围自定义数据关系 服务类
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:55
 */
public interface IDataScopeCustomDataRelService extends IBaseService<DataScopeCustomDataRelDO> {

    /**
     * 根据数据范围id查询
     * @param dataScopeId
     * @return
     */
    default DataScopeCustomDataRelDO getByDataScopeId(Long dataScopeId) {
        Assert.notNull(dataScopeId,"dataScopeId 不能为空");
        return getOne(Wrappers.<DataScopeCustomDataRelDO>lambdaQuery().eq(DataScopeCustomDataRelDO::getDataScopeId, dataScopeId));
    }



    /**
     * 根据数据范围id查询多个
     * @param dataScopeIds
     * @return
     */
    default List<DataScopeCustomDataRelDO> getByDataScopeIds(List<Long> dataScopeIds) {
        Assert.notEmpty(dataScopeIds,"dataScopeIds 不能为空");
        return list(Wrappers.<DataScopeCustomDataRelDO>lambdaQuery().in(DataScopeCustomDataRelDO::getDataScopeId, dataScopeIds));
    }
            

    /**
     * 根据自定义数据id查询
     * @param dataId
     * @return
     */
    default DataScopeCustomDataRelDO getByDataId(Long dataId) {
        Assert.notNull(dataId,"dataId 不能为空");
        return getOne(Wrappers.<DataScopeCustomDataRelDO>lambdaQuery().eq(DataScopeCustomDataRelDO::getDataId, dataId));
    }



    /**
     * 根据自定义数据id查询多个
     * @param dataIds
     * @return
     */
    default List<DataScopeCustomDataRelDO> getByDataIds(List<Long> dataIds) {
        Assert.notEmpty(dataIds,"dataIds 不能为空");
        return list(Wrappers.<DataScopeCustomDataRelDO>lambdaQuery().in(DataScopeCustomDataRelDO::getDataId, dataIds));
    }
            








}
