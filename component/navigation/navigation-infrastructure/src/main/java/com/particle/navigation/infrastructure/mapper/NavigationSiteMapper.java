package com.particle.navigation.infrastructure.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.navigation.infrastructure.dos.NavigationSiteDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 导航网站 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:56
 */
@Mapper
public interface NavigationSiteMapper extends IBaseMapper<NavigationSiteDO> {

}
