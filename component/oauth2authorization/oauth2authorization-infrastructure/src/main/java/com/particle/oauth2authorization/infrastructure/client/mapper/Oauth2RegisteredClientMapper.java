package com.particle.oauth2authorization.infrastructure.client.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.oauth2authorization.infrastructure.client.dos.Oauth2RegisteredClientDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * oauth2客户端 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
@Mapper
public interface Oauth2RegisteredClientMapper extends IBaseMapper<Oauth2RegisteredClientDO> {

}
