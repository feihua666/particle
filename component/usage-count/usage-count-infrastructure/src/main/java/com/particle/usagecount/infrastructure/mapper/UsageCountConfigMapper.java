package com.particle.usagecount.infrastructure.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.usagecount.infrastructure.dos.UsageCountConfigDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 使用次数配置 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
@Mapper
public interface UsageCountConfigMapper extends IBaseMapper<UsageCountConfigDO> {

}
