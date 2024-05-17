package com.particle.dream.infrastructure.ssq.mapper;

import com.particle.dream.infrastructure.ssq.dos.SsqCodeDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 双色球号码 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:47:01
 */
@Mapper
public interface SsqCodeMapper extends IBaseMapper<SsqCodeDO> {

}
