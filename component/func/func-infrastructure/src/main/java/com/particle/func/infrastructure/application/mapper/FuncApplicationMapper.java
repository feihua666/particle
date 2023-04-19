package com.particle.func.infrastructure.application.mapper;

import com.particle.func.infrastructure.application.dos.FuncApplicationDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 功能应用 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Mapper
public interface FuncApplicationMapper extends IBaseMapper<FuncApplicationDO> {

}
