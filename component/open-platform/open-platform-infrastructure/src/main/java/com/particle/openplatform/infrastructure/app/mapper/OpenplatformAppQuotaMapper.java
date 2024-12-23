package com.particle.openplatform.infrastructure.app.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppQuotaDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 开放平台应用额度 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
@Mapper
public interface OpenplatformAppQuotaMapper extends IBaseMapper<OpenplatformAppQuotaDO> {

}
