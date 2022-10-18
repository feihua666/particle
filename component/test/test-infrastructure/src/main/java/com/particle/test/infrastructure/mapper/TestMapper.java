package com.particle.test.infrastructure.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.test.infrastructure.dos.TestDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 测试 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Mapper
public interface TestMapper extends IBaseMapper<TestDO> {

}
