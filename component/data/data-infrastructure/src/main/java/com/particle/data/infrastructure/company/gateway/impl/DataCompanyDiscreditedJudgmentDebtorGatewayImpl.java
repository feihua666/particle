package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyDiscreditedJudgmentDebtor;
import com.particle.data.domain.company.DataCompanyDiscreditedJudgmentDebtorId;
import com.particle.data.domain.company.gateway.DataCompanyDiscreditedJudgmentDebtorGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyDiscreditedJudgmentDebtorService;
import com.particle.data.infrastructure.company.dos.DataCompanyDiscreditedJudgmentDebtorDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyDiscreditedJudgmentDebtorInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业失信被执行人 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:58
 */
@Component
public class DataCompanyDiscreditedJudgmentDebtorGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyDiscreditedJudgmentDebtorId,DataCompanyDiscreditedJudgmentDebtor> implements DataCompanyDiscreditedJudgmentDebtorGateway {

    private IDataCompanyDiscreditedJudgmentDebtorService iDataCompanyDiscreditedJudgmentDebtorService;

    @Override
    public DataCompanyDiscreditedJudgmentDebtor getById(DataCompanyDiscreditedJudgmentDebtorId dataCompanyDiscreditedJudgmentDebtorId) {
        DataCompanyDiscreditedJudgmentDebtorDO byId = iDataCompanyDiscreditedJudgmentDebtorService.getById(dataCompanyDiscreditedJudgmentDebtorId.getId());
        DataCompanyDiscreditedJudgmentDebtor dataCompanyDiscreditedJudgmentDebtor = DomainFactory.create(DataCompanyDiscreditedJudgmentDebtor.class);
        dataCompanyDiscreditedJudgmentDebtor = DataCompanyDiscreditedJudgmentDebtorInfrastructureStructMapping.instance. dataCompanyDiscreditedJudgmentDebtorDOToDataCompanyDiscreditedJudgmentDebtor(dataCompanyDiscreditedJudgmentDebtor,byId);
        return dataCompanyDiscreditedJudgmentDebtor;
    }

    @Override
    public boolean doSave(DataCompanyDiscreditedJudgmentDebtor dataCompanyDiscreditedJudgmentDebtor) {
        DataCompanyDiscreditedJudgmentDebtorDO dataCompanyDiscreditedJudgmentDebtorDO = DataCompanyDiscreditedJudgmentDebtorInfrastructureStructMapping.instance.dataCompanyDiscreditedJudgmentDebtorToDataCompanyDiscreditedJudgmentDebtorDO(dataCompanyDiscreditedJudgmentDebtor);
        if (dataCompanyDiscreditedJudgmentDebtorDO.getId() == null) {
            dataCompanyDiscreditedJudgmentDebtorDO.setAddControl(dataCompanyDiscreditedJudgmentDebtor.getAddControl());
            DataCompanyDiscreditedJudgmentDebtorDO add = iDataCompanyDiscreditedJudgmentDebtorService.add(dataCompanyDiscreditedJudgmentDebtorDO);
            dataCompanyDiscreditedJudgmentDebtor.setId(DataCompanyDiscreditedJudgmentDebtorId.of(add.getId()));
            return add != null;
        }
        dataCompanyDiscreditedJudgmentDebtorDO.setUpdateControl(dataCompanyDiscreditedJudgmentDebtor.getUpdateControl());
        DataCompanyDiscreditedJudgmentDebtorDO update = iDataCompanyDiscreditedJudgmentDebtorService.update(dataCompanyDiscreditedJudgmentDebtorDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyDiscreditedJudgmentDebtorId dataCompanyDiscreditedJudgmentDebtorId) {
        return iDataCompanyDiscreditedJudgmentDebtorService.deleteById(dataCompanyDiscreditedJudgmentDebtorId.getId());
    }

    @Override
    public boolean delete(DataCompanyDiscreditedJudgmentDebtorId dataCompanyDiscreditedJudgmentDebtorId, IdCommand idCommand) {
        return iDataCompanyDiscreditedJudgmentDebtorService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyDiscreditedJudgmentDebtorService(IDataCompanyDiscreditedJudgmentDebtorService iDataCompanyDiscreditedJudgmentDebtorService) {
        this.iDataCompanyDiscreditedJudgmentDebtorService = iDataCompanyDiscreditedJudgmentDebtorService;
    }
}
