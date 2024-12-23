package com.particle.navigation.infrastructure.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.navigation.infrastructure.dos.NavigationStaticDeployDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 导航网站静态部署 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-11-01 10:02:52
 */
@Mapper
public interface NavigationStaticDeployMapper extends IBaseMapper<NavigationStaticDeployDO> {

}
