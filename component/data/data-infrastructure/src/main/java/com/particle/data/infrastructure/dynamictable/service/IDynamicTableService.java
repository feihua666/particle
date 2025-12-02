package com.particle.data.infrastructure.dynamictable.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 * 动态数据表格 服务类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
public interface IDynamicTableService extends IBaseService<DynamicTableDO> {


    /**
     * 根据名称查询
     * @param name
     * @return
     */
    default DynamicTableDO getByName(String name) {
        Assert.hasText(name,"name不能为空");
        return getOne(Wrappers.<DynamicTableDO>lambdaQuery().eq(DynamicTableDO::getName,name));
    }

    /**
     * 根据名称查询
     * @param names
     * @return
     */
    default List<DynamicTableDO> getByNames(List<String> names) {
        Assert.notEmpty(names,"names不能为空");
        return list(Wrappers.<DynamicTableDO>lambdaQuery().in(DynamicTableDO::getName,names));
    }

    /**
     * 更新字段数量
     * @return
     */
    boolean updateDynamicTableFieldNum(Long dynamicTableId);
    /**
     * 更新数据数量
     * @return
     */
    boolean updateDynamicTableDataNum(Long dynamicTableId,Integer dynamicTableDataNum);

    /**
     * 获取动态数据数量
     * @param tableName
     * @param isPublic
     * @return
     */
    Integer countDynamicTableDataNum(String tableName,Boolean isPublic);
}
