package com.particle.navigation.infrastructure.mapper;

import com.particle.navigation.infrastructure.dos.NavigationCategoryDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 导航分类 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
@Mapper
public interface NavigationCategoryMapper extends IBaseMapper<NavigationCategoryDO> {

}
