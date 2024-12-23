package com.particle.navigation.infrastructure.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.navigation.infrastructure.dos.NavigationSiteCategoryRelDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 导航网站分类关系 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
@Mapper
public interface NavigationSiteCategoryRelMapper extends IBaseMapper<NavigationSiteCategoryRelDO> {

}
