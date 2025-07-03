package com.particle.cms.infrastructure.gateway.impl;

import com.particle.cms.domain.CmsSite;
import com.particle.cms.domain.CmsSiteId;
import com.particle.cms.domain.gateway.CmsSiteGateway;
import com.particle.cms.infrastructure.service.ICmsSiteService;
import com.particle.cms.infrastructure.dos.CmsSiteDO;
import com.particle.cms.infrastructure.structmapping.CmsSiteInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 站点 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
@Component
public class CmsSiteGatewayImpl extends AbstractBaseGatewayImpl<CmsSiteId,CmsSite> implements CmsSiteGateway {

    private ICmsSiteService iCmsSiteService;

    @Override
    public CmsSite getById(CmsSiteId cmsSiteId) {
        CmsSiteDO byId = iCmsSiteService.getById(cmsSiteId.getId());
        CmsSite cmsSite = DomainFactory.create(CmsSite.class);
        cmsSite = CmsSiteInfrastructureStructMapping.instance. cmsSiteDOToCmsSite(cmsSite,byId);
        return cmsSite;
    }

    @Override
    public boolean doSave(CmsSite cmsSite) {
        CmsSiteDO cmsSiteDO = CmsSiteInfrastructureStructMapping.instance.cmsSiteToCmsSiteDO(cmsSite);
        if (cmsSiteDO.getId() == null) {
            cmsSiteDO.setAddControl(cmsSite.getAddControl());
            CmsSiteDO add = iCmsSiteService.add(cmsSiteDO);
            cmsSite.setId(CmsSiteId.of(add.getId()));
            return add != null;
        }
        cmsSiteDO.setUpdateControl(cmsSite.getUpdateControl());
        CmsSiteDO update = iCmsSiteService.update(cmsSiteDO);
        return update != null;
    }

    @Override
    public boolean delete(CmsSiteId cmsSiteId) {
        return iCmsSiteService.deleteById(cmsSiteId.getId());
    }

    @Override
    public boolean delete(CmsSiteId cmsSiteId, IdCommand idCommand) {
        return iCmsSiteService.deleteById(idCommand);
    }

    @Autowired
    public void setICmsSiteService(ICmsSiteService iCmsSiteService) {
        this.iCmsSiteService = iCmsSiteService;
    }
}
