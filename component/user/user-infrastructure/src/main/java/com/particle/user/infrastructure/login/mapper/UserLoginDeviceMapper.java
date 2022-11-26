package com.particle.user.infrastructure.login.mapper;

import com.particle.user.infrastructure.login.dos.UserLoginDeviceDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 用户登录设备 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Mapper
public interface UserLoginDeviceMapper extends IBaseMapper<UserLoginDeviceDO> {

}
