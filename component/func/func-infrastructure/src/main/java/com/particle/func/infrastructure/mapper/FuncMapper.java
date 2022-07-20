package com.particle.func.infrastructure.mapper;

import com.particle.func.infrastructure.dos.FuncDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 菜单功能 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Mapper
public interface FuncMapper extends IBaseMapper<FuncDO> {

}
