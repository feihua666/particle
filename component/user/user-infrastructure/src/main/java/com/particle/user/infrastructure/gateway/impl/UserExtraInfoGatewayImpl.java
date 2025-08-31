package com.particle.user.infrastructure.gateway.impl;

import com.particle.user.domain.UserExtraInfo;
import com.particle.user.domain.UserExtraInfoId;
import com.particle.user.domain.gateway.UserExtraInfoGateway;
import com.particle.user.infrastructure.service.IUserExtraInfoService;
import com.particle.user.infrastructure.dos.UserExtraInfoDO;
import com.particle.user.infrastructure.structmapping.UserExtraInfoInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户扩展信息 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
@Component
public class UserExtraInfoGatewayImpl extends AbstractBaseGatewayImpl<UserExtraInfoId,UserExtraInfo> implements UserExtraInfoGateway {

    private IUserExtraInfoService iUserExtraInfoService;

    @Override
    public UserExtraInfo getById(UserExtraInfoId userExtraInfoId) {
        UserExtraInfoDO byId = iUserExtraInfoService.getById(userExtraInfoId.getId());
        UserExtraInfo userExtraInfo = DomainFactory.create(UserExtraInfo.class);
        userExtraInfo = UserExtraInfoInfrastructureStructMapping.instance. userExtraInfoDOToUserExtraInfo(userExtraInfo,byId);
        return userExtraInfo;
    }

    @Override
    public boolean doSave(UserExtraInfo userExtraInfo) {
        UserExtraInfoDO userExtraInfoDO = UserExtraInfoInfrastructureStructMapping.instance.userExtraInfoToUserExtraInfoDO(userExtraInfo);
        if (userExtraInfoDO.getId() == null) {
            userExtraInfoDO.setAddControl(userExtraInfo.getAddControl());
            UserExtraInfoDO add = iUserExtraInfoService.add(userExtraInfoDO);
            userExtraInfo.setId(UserExtraInfoId.of(add.getId()));
            return add != null;
        }
        userExtraInfoDO.setUpdateControl(userExtraInfo.getUpdateControl());
        UserExtraInfoDO update = iUserExtraInfoService.update(userExtraInfoDO);
        return update != null;
    }

    @Override
    public boolean delete(UserExtraInfoId userExtraInfoId) {
        return iUserExtraInfoService.deleteById(userExtraInfoId.getId());
    }

    @Override
    public boolean delete(UserExtraInfoId userExtraInfoId, IdCommand idCommand) {
        return iUserExtraInfoService.deleteById(idCommand);
    }

    @Autowired
    public void setIUserExtraInfoService(IUserExtraInfoService iUserExtraInfoService) {
        this.iUserExtraInfoService = iUserExtraInfoService;
    }
}
