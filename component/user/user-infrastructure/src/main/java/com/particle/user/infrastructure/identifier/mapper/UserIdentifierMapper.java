package com.particle.user.infrastructure.identifier.mapper;

import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 用户登录标识 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Mapper
public interface UserIdentifierMapper extends IBaseMapper<UserIdentifierDO> {

}
