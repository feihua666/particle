package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyAnnualReportForeignGuarantee;
import com.particle.data.domain.company.DataCompanyAnnualReportForeignGuaranteeId;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportForeignGuaranteeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportForeignGuaranteeService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportForeignGuaranteeDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyAnnualReportForeignGuaranteeInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业年报对外担保 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:11
 */
@Component
public class DataCompanyAnnualReportForeignGuaranteeGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyAnnualReportForeignGuaranteeId,DataCompanyAnnualReportForeignGuarantee> implements DataCompanyAnnualReportForeignGuaranteeGateway {

    private IDataCompanyAnnualReportForeignGuaranteeService iDataCompanyAnnualReportForeignGuaranteeService;

    @Override
    public DataCompanyAnnualReportForeignGuarantee getById(DataCompanyAnnualReportForeignGuaranteeId dataCompanyAnnualReportForeignGuaranteeId) {
        DataCompanyAnnualReportForeignGuaranteeDO byId = iDataCompanyAnnualReportForeignGuaranteeService.getById(dataCompanyAnnualReportForeignGuaranteeId.getId());
        DataCompanyAnnualReportForeignGuarantee dataCompanyAnnualReportForeignGuarantee = DomainFactory.create(DataCompanyAnnualReportForeignGuarantee.class);
        dataCompanyAnnualReportForeignGuarantee = DataCompanyAnnualReportForeignGuaranteeInfrastructureStructMapping.instance. dataCompanyAnnualReportForeignGuaranteeDOToDataCompanyAnnualReportForeignGuarantee(dataCompanyAnnualReportForeignGuarantee,byId);
        return dataCompanyAnnualReportForeignGuarantee;
    }

    @Override
    public boolean doSave(DataCompanyAnnualReportForeignGuarantee dataCompanyAnnualReportForeignGuarantee) {
        DataCompanyAnnualReportForeignGuaranteeDO dataCompanyAnnualReportForeignGuaranteeDO = DataCompanyAnnualReportForeignGuaranteeInfrastructureStructMapping.instance.dataCompanyAnnualReportForeignGuaranteeToDataCompanyAnnualReportForeignGuaranteeDO(dataCompanyAnnualReportForeignGuarantee);
        if (dataCompanyAnnualReportForeignGuaranteeDO.getId() == null) {
            dataCompanyAnnualReportForeignGuaranteeDO.setAddControl(dataCompanyAnnualReportForeignGuarantee.getAddControl());
            DataCompanyAnnualReportForeignGuaranteeDO add = iDataCompanyAnnualReportForeignGuaranteeService.add(dataCompanyAnnualReportForeignGuaranteeDO);
            dataCompanyAnnualReportForeignGuarantee.setId(DataCompanyAnnualReportForeignGuaranteeId.of(add.getId()));
            return add != null;
        }
        dataCompanyAnnualReportForeignGuaranteeDO.setUpdateControl(dataCompanyAnnualReportForeignGuarantee.getUpdateControl());
        DataCompanyAnnualReportForeignGuaranteeDO update = iDataCompanyAnnualReportForeignGuaranteeService.update(dataCompanyAnnualReportForeignGuaranteeDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyAnnualReportForeignGuaranteeId dataCompanyAnnualReportForeignGuaranteeId) {
        return iDataCompanyAnnualReportForeignGuaranteeService.deleteById(dataCompanyAnnualReportForeignGuaranteeId.getId());
    }

    @Override
    public boolean delete(DataCompanyAnnualReportForeignGuaranteeId dataCompanyAnnualReportForeignGuaranteeId, IdCommand idCommand) {
        return iDataCompanyAnnualReportForeignGuaranteeService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyAnnualReportForeignGuaranteeService(IDataCompanyAnnualReportForeignGuaranteeService iDataCompanyAnnualReportForeignGuaranteeService) {
        this.iDataCompanyAnnualReportForeignGuaranteeService = iDataCompanyAnnualReportForeignGuaranteeService;
    }
}
