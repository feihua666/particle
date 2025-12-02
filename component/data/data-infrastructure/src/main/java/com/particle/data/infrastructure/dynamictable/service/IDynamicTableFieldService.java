package com.particle.data.infrastructure.dynamictable.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableFieldDO;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.util.List;

/**
 * <p>
 * 动态数据表格字段 服务类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:01
 */
public interface IDynamicTableFieldService extends IBaseService<DynamicTableFieldDO> {


    /**
     * 根据动态数据表格id和字段名称查询
     * @param dynamicTableId
     * @param name
     * @return
     */
    default DynamicTableFieldDO getByDynamicTableIdAndName(Long dynamicTableId, String name) {
        Assert.notNull(dynamicTableId,"dynamicTableId 不能为空");
        Assert.notNull(name,"name 不能为空");
        return getOne(Wrappers.<DynamicTableFieldDO>lambdaQuery()
                .eq(DynamicTableFieldDO::getDynamicTableId, dynamicTableId)
                .eq(DynamicTableFieldDO::getName, name));
    }

    /**
     * 根据动态数据表格id和注释称查询
     * @param dynamicTableId
     * @param comment
     * @return
     */
    default DynamicTableFieldDO getByDynamicTableIdAndComment(Long dynamicTableId, String comment) {
        Assert.notNull(dynamicTableId,"dynamicTableId 不能为空");
        Assert.notNull(comment,"comment 不能为空");
        return getOne(Wrappers.<DynamicTableFieldDO>lambdaQuery()
                .eq(DynamicTableFieldDO::getDynamicTableId, dynamicTableId)
                .eq(DynamicTableFieldDO::getComment, comment));
    }
    /**
     * 根据动态数据表格id查询列表
     * @param dynamicTableId
     * @return
     */
    default List<DynamicTableFieldDO> listByDynamicTableId(Long dynamicTableId) {
        Assert.notNull(dynamicTableId,"dynamicTableId 不能为空");
        return list(Wrappers.<DynamicTableFieldDO>lambdaQuery()
                .eq(DynamicTableFieldDO::getDynamicTableId, dynamicTableId));
    }

    /**
     * 根据动态数据表格id查询列表
     * @param dynamicTableIds
     * @return
     */
    default List<DynamicTableFieldDO> listByDynamicTableIds(List<Long> dynamicTableIds) {
        Assert.notEmpty(dynamicTableIds,"dynamicTableIds 不能为空");
        return list(Wrappers.<DynamicTableFieldDO>lambdaQuery()
                .in(DynamicTableFieldDO::getDynamicTableId, dynamicTableIds));
    }

}
