package com.particle.dict.infrastructure.mapper;

import com.particle.dict.infrastructure.dos.DictDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 字典 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Mapper
public interface DictMapper extends IBaseMapper<DictDO> {

}
