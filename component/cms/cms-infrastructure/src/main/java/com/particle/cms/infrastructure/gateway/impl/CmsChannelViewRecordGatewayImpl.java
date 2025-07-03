package com.particle.cms.infrastructure.gateway.impl;

import com.particle.cms.domain.CmsChannelViewRecord;
import com.particle.cms.domain.CmsChannelViewRecordId;
import com.particle.cms.domain.gateway.CmsChannelViewRecordGateway;
import com.particle.cms.infrastructure.service.ICmsChannelViewRecordService;
import com.particle.cms.infrastructure.dos.CmsChannelViewRecordDO;
import com.particle.cms.infrastructure.structmapping.CmsChannelViewRecordInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 栏目访问记录 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:22
 */
@Component
public class CmsChannelViewRecordGatewayImpl extends AbstractBaseGatewayImpl<CmsChannelViewRecordId,CmsChannelViewRecord> implements CmsChannelViewRecordGateway {

    private ICmsChannelViewRecordService iCmsChannelViewRecordService;

    @Override
    public CmsChannelViewRecord getById(CmsChannelViewRecordId cmsChannelViewRecordId) {
        CmsChannelViewRecordDO byId = iCmsChannelViewRecordService.getById(cmsChannelViewRecordId.getId());
        CmsChannelViewRecord cmsChannelViewRecord = DomainFactory.create(CmsChannelViewRecord.class);
        cmsChannelViewRecord = CmsChannelViewRecordInfrastructureStructMapping.instance. cmsChannelViewRecordDOToCmsChannelViewRecord(cmsChannelViewRecord,byId);
        return cmsChannelViewRecord;
    }

    @Override
    public boolean doSave(CmsChannelViewRecord cmsChannelViewRecord) {
        CmsChannelViewRecordDO cmsChannelViewRecordDO = CmsChannelViewRecordInfrastructureStructMapping.instance.cmsChannelViewRecordToCmsChannelViewRecordDO(cmsChannelViewRecord);
        if (cmsChannelViewRecordDO.getId() == null) {
            cmsChannelViewRecordDO.setAddControl(cmsChannelViewRecord.getAddControl());
            CmsChannelViewRecordDO add = iCmsChannelViewRecordService.add(cmsChannelViewRecordDO);
            cmsChannelViewRecord.setId(CmsChannelViewRecordId.of(add.getId()));
            return add != null;
        }
        cmsChannelViewRecordDO.setUpdateControl(cmsChannelViewRecord.getUpdateControl());
        CmsChannelViewRecordDO update = iCmsChannelViewRecordService.update(cmsChannelViewRecordDO);
        return update != null;
    }

    @Override
    public boolean delete(CmsChannelViewRecordId cmsChannelViewRecordId) {
        return iCmsChannelViewRecordService.deleteById(cmsChannelViewRecordId.getId());
    }

    @Override
    public boolean delete(CmsChannelViewRecordId cmsChannelViewRecordId, IdCommand idCommand) {
        return iCmsChannelViewRecordService.deleteById(idCommand);
    }

    @Autowired
    public void setICmsChannelViewRecordService(ICmsChannelViewRecordService iCmsChannelViewRecordService) {
        this.iCmsChannelViewRecordService = iCmsChannelViewRecordService;
    }
}
