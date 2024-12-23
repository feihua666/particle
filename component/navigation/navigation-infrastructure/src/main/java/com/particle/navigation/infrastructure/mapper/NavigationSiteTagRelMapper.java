package com.particle.navigation.infrastructure.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.navigation.infrastructure.dos.NavigationSiteTagRelDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 导航网站标签关系 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:39:14
 */
@Mapper
public interface NavigationSiteTagRelMapper extends IBaseMapper<NavigationSiteTagRelDO> {

}
