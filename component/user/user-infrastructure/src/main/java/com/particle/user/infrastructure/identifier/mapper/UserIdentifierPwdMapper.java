package com.particle.user.infrastructure.identifier.mapper;

import com.particle.user.infrastructure.identifier.dos.UserIdentifierPwdDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 用户密码 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Mapper
public interface UserIdentifierPwdMapper extends IBaseMapper<UserIdentifierPwdDO> {

}
