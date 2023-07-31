package com.particle.oauth2authorization.infrastructure.client.service;

import com.particle.oauth2authorization.infrastructure.client.dos.Oauth2RegisteredClientDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * oauth2客户端 服务类
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
public interface IOauth2RegisteredClientService extends IBaseService<Oauth2RegisteredClientDO> {

    /**
     * 根据客户端ID查询
     * @param clientId
     * @return
     */
    default Oauth2RegisteredClientDO getByClientId(String clientId) {
        Assert.notNull(clientId,"clientId 不能为空");
        return getOne(Wrappers.<Oauth2RegisteredClientDO>lambdaQuery().eq(Oauth2RegisteredClientDO::getClientId, clientId));
    }



    /**
     * 根据客户端ID查询多个
     * @param clientIds
     * @return
     */
    default List<Oauth2RegisteredClientDO> getByClientIds(List<String> clientIds) {
        Assert.notEmpty(clientIds,"clientIds 不能为空");
        return list(Wrappers.<Oauth2RegisteredClientDO>lambdaQuery().in(Oauth2RegisteredClientDO::getClientId, clientIds));
    }
            


















}
