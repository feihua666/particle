package com.particle.cms.infrastructure.gateway.impl;

import com.particle.cms.domain.CmsContentViewRecord;
import com.particle.cms.domain.CmsContentViewRecordId;
import com.particle.cms.domain.gateway.CmsContentViewRecordGateway;
import com.particle.cms.infrastructure.service.ICmsContentViewRecordService;
import com.particle.cms.infrastructure.dos.CmsContentViewRecordDO;
import com.particle.cms.infrastructure.structmapping.CmsContentViewRecordInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 内容访问记录 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:38
 */
@Component
public class CmsContentViewRecordGatewayImpl extends AbstractBaseGatewayImpl<CmsContentViewRecordId,CmsContentViewRecord> implements CmsContentViewRecordGateway {

    private ICmsContentViewRecordService iCmsContentViewRecordService;

    @Override
    public CmsContentViewRecord getById(CmsContentViewRecordId cmsContentViewRecordId) {
        CmsContentViewRecordDO byId = iCmsContentViewRecordService.getById(cmsContentViewRecordId.getId());
        CmsContentViewRecord cmsContentViewRecord = DomainFactory.create(CmsContentViewRecord.class);
        cmsContentViewRecord = CmsContentViewRecordInfrastructureStructMapping.instance. cmsContentViewRecordDOToCmsContentViewRecord(cmsContentViewRecord,byId);
        return cmsContentViewRecord;
    }

    @Override
    public boolean doSave(CmsContentViewRecord cmsContentViewRecord) {
        CmsContentViewRecordDO cmsContentViewRecordDO = CmsContentViewRecordInfrastructureStructMapping.instance.cmsContentViewRecordToCmsContentViewRecordDO(cmsContentViewRecord);
        if (cmsContentViewRecordDO.getId() == null) {
            cmsContentViewRecordDO.setAddControl(cmsContentViewRecord.getAddControl());
            CmsContentViewRecordDO add = iCmsContentViewRecordService.add(cmsContentViewRecordDO);
            cmsContentViewRecord.setId(CmsContentViewRecordId.of(add.getId()));
            return add != null;
        }
        cmsContentViewRecordDO.setUpdateControl(cmsContentViewRecord.getUpdateControl());
        CmsContentViewRecordDO update = iCmsContentViewRecordService.update(cmsContentViewRecordDO);
        return update != null;
    }

    @Override
    public boolean delete(CmsContentViewRecordId cmsContentViewRecordId) {
        return iCmsContentViewRecordService.deleteById(cmsContentViewRecordId.getId());
    }

    @Override
    public boolean delete(CmsContentViewRecordId cmsContentViewRecordId, IdCommand idCommand) {
        return iCmsContentViewRecordService.deleteById(idCommand);
    }

    @Autowired
    public void setICmsContentViewRecordService(ICmsContentViewRecordService iCmsContentViewRecordService) {
        this.iCmsContentViewRecordService = iCmsContentViewRecordService;
    }
}
