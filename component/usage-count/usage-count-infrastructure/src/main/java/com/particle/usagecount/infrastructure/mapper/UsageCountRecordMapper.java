package com.particle.usagecount.infrastructure.mapper;

import com.particle.usagecount.infrastructure.dos.UsageCountRecordDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 使用次数记录 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
@Mapper
public interface UsageCountRecordMapper extends IBaseMapper<UsageCountRecordDO> {

}
