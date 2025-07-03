package com.particle.cms.infrastructure.mapper;

import com.particle.cms.infrastructure.dos.CmsSiteDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 站点 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
@Mapper
public interface CmsSiteMapper extends IBaseMapper<CmsSiteDO> {

}
