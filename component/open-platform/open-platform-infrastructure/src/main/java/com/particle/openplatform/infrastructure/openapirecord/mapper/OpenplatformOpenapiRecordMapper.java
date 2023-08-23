package com.particle.openplatform.infrastructure.openapirecord.mapper;

import com.particle.openplatform.infrastructure.openapirecord.dos.OpenplatformOpenapiRecordDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 开放平台开放接口调用记录 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:13:46
 */
@Mapper
public interface OpenplatformOpenapiRecordMapper extends IBaseMapper<OpenplatformOpenapiRecordDO> {

}
