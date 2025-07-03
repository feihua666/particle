package com.particle.cms.infrastructure.gateway.impl;

import com.particle.cms.domain.CmsSiteIndexViewRecord;
import com.particle.cms.domain.CmsSiteIndexViewRecordId;
import com.particle.cms.domain.gateway.CmsSiteIndexViewRecordGateway;
import com.particle.cms.infrastructure.service.ICmsSiteIndexViewRecordService;
import com.particle.cms.infrastructure.dos.CmsSiteIndexViewRecordDO;
import com.particle.cms.infrastructure.structmapping.CmsSiteIndexViewRecordInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 站点首页访问记录 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:10
 */
@Component
public class CmsSiteIndexViewRecordGatewayImpl extends AbstractBaseGatewayImpl<CmsSiteIndexViewRecordId,CmsSiteIndexViewRecord> implements CmsSiteIndexViewRecordGateway {

    private ICmsSiteIndexViewRecordService iCmsSiteIndexViewRecordService;

    @Override
    public CmsSiteIndexViewRecord getById(CmsSiteIndexViewRecordId cmsSiteIndexViewRecordId) {
        CmsSiteIndexViewRecordDO byId = iCmsSiteIndexViewRecordService.getById(cmsSiteIndexViewRecordId.getId());
        CmsSiteIndexViewRecord cmsSiteIndexViewRecord = DomainFactory.create(CmsSiteIndexViewRecord.class);
        cmsSiteIndexViewRecord = CmsSiteIndexViewRecordInfrastructureStructMapping.instance. cmsSiteIndexViewRecordDOToCmsSiteIndexViewRecord(cmsSiteIndexViewRecord,byId);
        return cmsSiteIndexViewRecord;
    }

    @Override
    public boolean doSave(CmsSiteIndexViewRecord cmsSiteIndexViewRecord) {
        CmsSiteIndexViewRecordDO cmsSiteIndexViewRecordDO = CmsSiteIndexViewRecordInfrastructureStructMapping.instance.cmsSiteIndexViewRecordToCmsSiteIndexViewRecordDO(cmsSiteIndexViewRecord);
        if (cmsSiteIndexViewRecordDO.getId() == null) {
            cmsSiteIndexViewRecordDO.setAddControl(cmsSiteIndexViewRecord.getAddControl());
            CmsSiteIndexViewRecordDO add = iCmsSiteIndexViewRecordService.add(cmsSiteIndexViewRecordDO);
            cmsSiteIndexViewRecord.setId(CmsSiteIndexViewRecordId.of(add.getId()));
            return add != null;
        }
        cmsSiteIndexViewRecordDO.setUpdateControl(cmsSiteIndexViewRecord.getUpdateControl());
        CmsSiteIndexViewRecordDO update = iCmsSiteIndexViewRecordService.update(cmsSiteIndexViewRecordDO);
        return update != null;
    }

    @Override
    public boolean delete(CmsSiteIndexViewRecordId cmsSiteIndexViewRecordId) {
        return iCmsSiteIndexViewRecordService.deleteById(cmsSiteIndexViewRecordId.getId());
    }

    @Override
    public boolean delete(CmsSiteIndexViewRecordId cmsSiteIndexViewRecordId, IdCommand idCommand) {
        return iCmsSiteIndexViewRecordService.deleteById(idCommand);
    }

    @Autowired
    public void setICmsSiteIndexViewRecordService(ICmsSiteIndexViewRecordService iCmsSiteIndexViewRecordService) {
        this.iCmsSiteIndexViewRecordService = iCmsSiteIndexViewRecordService;
    }
}
