package com.particle.data.infrastructure.dynamicdata.service;

import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorCategoryDO;
import com.particle.global.mybatis.plus.crud.IBaseService;

/**
 * <p>
 * 动态数据指标分类 服务类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:58
 */
public interface IDynamicDataIndicatorCategoryService extends IBaseService<DynamicDataIndicatorCategoryDO> {

    /**
     * 更新指标数量
     * @return
     */
    boolean updateDynamicDataIndicatorNum(Long dynamicDataIndicatorCategoryId);
    /**
     * 更新数据数量
     * @return
     */
    boolean updateDynamicDataIndicatorCategoryDataNum(Long dynamicDataIndicatorCategoryId,String tableName,Integer dynamicDataIndicatorCategoryDataNum);

}
