package com.particle.user.infrastructure.service.impl;

import com.particle.user.infrastructure.dos.UserDO;
import com.particle.user.infrastructure.mapper.UserMapper;
import com.particle.user.infrastructure.service.IUserService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台管理用户 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Service
public class UserServiceImpl extends IBaseServiceImpl<UserMapper, UserDO> implements IUserService {

}
