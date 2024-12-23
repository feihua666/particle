package com.particle.navigation.infrastructure.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.navigation.infrastructure.dos.NavigationSubmitDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 导航提交 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
@Mapper
public interface NavigationSubmitMapper extends IBaseMapper<NavigationSubmitDO> {

}
