package com.particle.cms.infrastructure.mapper;

import com.particle.cms.infrastructure.dos.CmsSiteIndexViewRecordDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 站点首页访问记录 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:10
 */
@Mapper
public interface CmsSiteIndexViewRecordMapper extends IBaseMapper<CmsSiteIndexViewRecordDO> {

}
