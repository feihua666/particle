package com.particle.tracking.infrastructure.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.tracking.infrastructure.dos.TrackingPageDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 埋点页面 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
@Mapper
public interface TrackingPageMapper extends IBaseMapper<TrackingPageDO> {

}
