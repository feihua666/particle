package com.particle.dataconstraint.infrastructure.service;

import com.particle.dataconstraint.infrastructure.dos.DataScopeDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 数据范围 服务类
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:38
 */
public interface IDataScopeService extends IBaseService<DataScopeDO> {

    /**
     * 根据数据范围编码查询
     * @param code
     * @return
     */
    default DataScopeDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<DataScopeDO>lambdaQuery().eq(DataScopeDO::getCode, code));
    }



    /**
     * 根据数据范围编码查询多个
     * @param codes
     * @return
     */
    default List<DataScopeDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<DataScopeDO>lambdaQuery().in(DataScopeDO::getCode, codes));
    }
            


    /**
     * 根据数据对象id查询
     * @param dataObjectId
     * @return
     */
    default List<DataScopeDO> getByDataObjectId(Long dataObjectId) {
        Assert.notNull(dataObjectId,"dataObjectId 不能为空");
        return list(Wrappers.<DataScopeDO>lambdaQuery().eq(DataScopeDO::getDataObjectId, dataObjectId));
    }



    /**
     * 根据数据对象id查询多个
     * @param dataObjectIds
     * @return
     */
    default List<DataScopeDO> getByDataObjectIds(List<Long> dataObjectIds) {
        Assert.notEmpty(dataObjectIds,"dataObjectIds 不能为空");
        return list(Wrappers.<DataScopeDO>lambdaQuery().in(DataScopeDO::getDataObjectId, dataObjectIds));
    }
            
















}
