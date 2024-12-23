package com.particle.user.infrastructure.login.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.user.infrastructure.login.dos.UserLoginRecordDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 用户登录记录 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Mapper
public interface UserLoginRecordMapper extends IBaseMapper<UserLoginRecordDO> {

}
