package com.particle.test.infrastructure.service.impl;

import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.test.infrastructure.dos.TestDO;
import com.particle.test.infrastructure.mapper.TestMapper;
import com.particle.test.infrastructure.service.ITestService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Service
public class TestServiceImpl extends IBaseServiceImpl<TestMapper, TestDO> implements ITestService {

}
