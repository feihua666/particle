package com.particle.test.infrastructure.mapper;

import com.particle.test.infrastructure.dos.UserSimpleDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 简单用户 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Mapper
public interface UserSimpleMapper extends IBaseMapper<UserSimpleDO> {

}
