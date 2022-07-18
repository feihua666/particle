package com.particle.test.infrastructure.service.impl;

import com.particle.test.infrastructure.dos.UserSimpleDO;
import com.particle.test.infrastructure.mapper.UserSimpleMapper;
import com.particle.test.infrastructure.service.IUserSimpleService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 简单用户 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Service
public class UserSimpleServiceImpl extends IBaseServiceImpl<UserSimpleMapper, UserSimpleDO> implements IUserSimpleService {

}
