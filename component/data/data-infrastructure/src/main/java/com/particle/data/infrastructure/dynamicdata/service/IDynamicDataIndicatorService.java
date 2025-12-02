package com.particle.data.infrastructure.dynamicdata.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorDO;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.util.List;

/**
 * <p>
 * 动态数据指标 服务类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:31:12
 */
public interface IDynamicDataIndicatorService extends IBaseService<DynamicDataIndicatorDO> {

    /**
     * 根据动态数据指标分类id查询列表
     * @param dynamicDataIndicatorCategoryId
     * @return
     */
    default List<DynamicDataIndicatorDO> listByDynamicDataIndicatorCategoryId(Long dynamicDataIndicatorCategoryId) {
        Assert.notNull(dynamicDataIndicatorCategoryId,"dynamicDataIndicatorCategoryId 不能为空");
        return list(Wrappers.<DynamicDataIndicatorDO>lambdaQuery()
                .eq(DynamicDataIndicatorDO::getDynamicDataIndicatorCategoryId, dynamicDataIndicatorCategoryId));
    }










}
