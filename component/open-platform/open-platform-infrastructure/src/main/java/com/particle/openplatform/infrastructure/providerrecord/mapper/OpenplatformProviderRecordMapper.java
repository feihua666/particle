package com.particle.openplatform.infrastructure.providerrecord.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.openplatform.infrastructure.providerrecord.dos.OpenplatformProviderRecordDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 开放平台开放接口供应商调用记录 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:17:25
 */
@Mapper
public interface OpenplatformProviderRecordMapper extends IBaseMapper<OpenplatformProviderRecordDO> {

}
