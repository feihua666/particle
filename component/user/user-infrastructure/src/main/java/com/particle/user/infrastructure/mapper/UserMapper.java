package com.particle.user.infrastructure.mapper;

import com.particle.user.infrastructure.dos.UserDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Mapper
public interface UserMapper extends IBaseMapper<UserDO> {

}
