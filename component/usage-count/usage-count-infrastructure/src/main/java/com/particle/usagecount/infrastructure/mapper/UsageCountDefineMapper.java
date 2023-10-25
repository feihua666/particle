package com.particle.usagecount.infrastructure.mapper;

import com.particle.usagecount.infrastructure.dos.UsageCountDefineDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 使用次数定义 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:08
 */
@Mapper
public interface UsageCountDefineMapper extends IBaseMapper<UsageCountDefineDO> {

}
