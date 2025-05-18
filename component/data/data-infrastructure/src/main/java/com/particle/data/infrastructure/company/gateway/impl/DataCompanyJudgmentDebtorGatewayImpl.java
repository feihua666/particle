package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyJudgmentDebtor;
import com.particle.data.domain.company.DataCompanyJudgmentDebtorId;
import com.particle.data.domain.company.gateway.DataCompanyJudgmentDebtorGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDebtorService;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDebtorDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyJudgmentDebtorInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业被执行人 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:07
 */
@Component
public class DataCompanyJudgmentDebtorGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyJudgmentDebtorId,DataCompanyJudgmentDebtor> implements DataCompanyJudgmentDebtorGateway {

    private IDataCompanyJudgmentDebtorService iDataCompanyJudgmentDebtorService;

    @Override
    public DataCompanyJudgmentDebtor getById(DataCompanyJudgmentDebtorId dataCompanyJudgmentDebtorId) {
        DataCompanyJudgmentDebtorDO byId = iDataCompanyJudgmentDebtorService.getById(dataCompanyJudgmentDebtorId.getId());
        DataCompanyJudgmentDebtor dataCompanyJudgmentDebtor = DomainFactory.create(DataCompanyJudgmentDebtor.class);
        dataCompanyJudgmentDebtor = DataCompanyJudgmentDebtorInfrastructureStructMapping.instance. dataCompanyJudgmentDebtorDOToDataCompanyJudgmentDebtor(dataCompanyJudgmentDebtor,byId);
        return dataCompanyJudgmentDebtor;
    }

    @Override
    public boolean doSave(DataCompanyJudgmentDebtor dataCompanyJudgmentDebtor) {
        DataCompanyJudgmentDebtorDO dataCompanyJudgmentDebtorDO = DataCompanyJudgmentDebtorInfrastructureStructMapping.instance.dataCompanyJudgmentDebtorToDataCompanyJudgmentDebtorDO(dataCompanyJudgmentDebtor);
        if (dataCompanyJudgmentDebtorDO.getId() == null) {
            dataCompanyJudgmentDebtorDO.setAddControl(dataCompanyJudgmentDebtor.getAddControl());
            DataCompanyJudgmentDebtorDO add = iDataCompanyJudgmentDebtorService.add(dataCompanyJudgmentDebtorDO);
            dataCompanyJudgmentDebtor.setId(DataCompanyJudgmentDebtorId.of(add.getId()));
            return add != null;
        }
        dataCompanyJudgmentDebtorDO.setUpdateControl(dataCompanyJudgmentDebtor.getUpdateControl());
        DataCompanyJudgmentDebtorDO update = iDataCompanyJudgmentDebtorService.update(dataCompanyJudgmentDebtorDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyJudgmentDebtorId dataCompanyJudgmentDebtorId) {
        return iDataCompanyJudgmentDebtorService.deleteById(dataCompanyJudgmentDebtorId.getId());
    }

    @Override
    public boolean delete(DataCompanyJudgmentDebtorId dataCompanyJudgmentDebtorId, IdCommand idCommand) {
        return iDataCompanyJudgmentDebtorService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyJudgmentDebtorService(IDataCompanyJudgmentDebtorService iDataCompanyJudgmentDebtorService) {
        this.iDataCompanyJudgmentDebtorService = iDataCompanyJudgmentDebtorService;
    }
}
