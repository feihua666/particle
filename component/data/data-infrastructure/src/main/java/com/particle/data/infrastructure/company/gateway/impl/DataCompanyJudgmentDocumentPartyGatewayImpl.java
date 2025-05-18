package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyJudgmentDocumentParty;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentPartyId;
import com.particle.data.domain.company.gateway.DataCompanyJudgmentDocumentPartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDocumentPartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentPartyDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyJudgmentDocumentPartyInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业裁判文书当事人 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:05
 */
@Component
public class DataCompanyJudgmentDocumentPartyGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyJudgmentDocumentPartyId,DataCompanyJudgmentDocumentParty> implements DataCompanyJudgmentDocumentPartyGateway {

    private IDataCompanyJudgmentDocumentPartyService iDataCompanyJudgmentDocumentPartyService;

    @Override
    public DataCompanyJudgmentDocumentParty getById(DataCompanyJudgmentDocumentPartyId dataCompanyJudgmentDocumentPartyId) {
        DataCompanyJudgmentDocumentPartyDO byId = iDataCompanyJudgmentDocumentPartyService.getById(dataCompanyJudgmentDocumentPartyId.getId());
        DataCompanyJudgmentDocumentParty dataCompanyJudgmentDocumentParty = DomainFactory.create(DataCompanyJudgmentDocumentParty.class);
        dataCompanyJudgmentDocumentParty = DataCompanyJudgmentDocumentPartyInfrastructureStructMapping.instance. dataCompanyJudgmentDocumentPartyDOToDataCompanyJudgmentDocumentParty(dataCompanyJudgmentDocumentParty,byId);
        return dataCompanyJudgmentDocumentParty;
    }

    @Override
    public boolean doSave(DataCompanyJudgmentDocumentParty dataCompanyJudgmentDocumentParty) {
        DataCompanyJudgmentDocumentPartyDO dataCompanyJudgmentDocumentPartyDO = DataCompanyJudgmentDocumentPartyInfrastructureStructMapping.instance.dataCompanyJudgmentDocumentPartyToDataCompanyJudgmentDocumentPartyDO(dataCompanyJudgmentDocumentParty);
        if (dataCompanyJudgmentDocumentPartyDO.getId() == null) {
            dataCompanyJudgmentDocumentPartyDO.setAddControl(dataCompanyJudgmentDocumentParty.getAddControl());
            DataCompanyJudgmentDocumentPartyDO add = iDataCompanyJudgmentDocumentPartyService.add(dataCompanyJudgmentDocumentPartyDO);
            dataCompanyJudgmentDocumentParty.setId(DataCompanyJudgmentDocumentPartyId.of(add.getId()));
            return add != null;
        }
        dataCompanyJudgmentDocumentPartyDO.setUpdateControl(dataCompanyJudgmentDocumentParty.getUpdateControl());
        DataCompanyJudgmentDocumentPartyDO update = iDataCompanyJudgmentDocumentPartyService.update(dataCompanyJudgmentDocumentPartyDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyJudgmentDocumentPartyId dataCompanyJudgmentDocumentPartyId) {
        return iDataCompanyJudgmentDocumentPartyService.deleteById(dataCompanyJudgmentDocumentPartyId.getId());
    }

    @Override
    public boolean delete(DataCompanyJudgmentDocumentPartyId dataCompanyJudgmentDocumentPartyId, IdCommand idCommand) {
        return iDataCompanyJudgmentDocumentPartyService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyJudgmentDocumentPartyService(IDataCompanyJudgmentDocumentPartyService iDataCompanyJudgmentDocumentPartyService) {
        this.iDataCompanyJudgmentDocumentPartyService = iDataCompanyJudgmentDocumentPartyService;
    }
}
