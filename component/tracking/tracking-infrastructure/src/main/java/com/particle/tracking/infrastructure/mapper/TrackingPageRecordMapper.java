package com.particle.tracking.infrastructure.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.tracking.infrastructure.dos.TrackingPageRecordDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 页面埋点记录 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
@Mapper
public interface TrackingPageRecordMapper extends IBaseMapper<TrackingPageRecordDO> {

}
