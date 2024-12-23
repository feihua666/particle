package com.particle.openplatform.infrastructure.app.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppOpenapiDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 开放平台应用与开放接口配置 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:49:06
 */
@Mapper
public interface OpenplatformAppOpenapiMapper extends IBaseMapper<OpenplatformAppOpenapiDO> {

}
