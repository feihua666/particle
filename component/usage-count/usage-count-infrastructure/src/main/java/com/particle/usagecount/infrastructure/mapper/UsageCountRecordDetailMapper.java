package com.particle.usagecount.infrastructure.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.usagecount.infrastructure.dos.UsageCountRecordDetailDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 使用次数记录明细 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-10-23 16:23:29
 */
@Mapper
public interface UsageCountRecordDetailMapper extends IBaseMapper<UsageCountRecordDetailDO> {

}
