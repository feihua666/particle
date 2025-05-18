package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprPatentPayment;
import com.particle.data.domain.company.DataCompanyIprPatentPaymentId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentPaymentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentPaymentService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPaymentDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprPatentPaymentInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权专利缴费信息 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:27
 */
@Component
public class DataCompanyIprPatentPaymentGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprPatentPaymentId,DataCompanyIprPatentPayment> implements DataCompanyIprPatentPaymentGateway {

    private IDataCompanyIprPatentPaymentService iDataCompanyIprPatentPaymentService;

    @Override
    public DataCompanyIprPatentPayment getById(DataCompanyIprPatentPaymentId dataCompanyIprPatentPaymentId) {
        DataCompanyIprPatentPaymentDO byId = iDataCompanyIprPatentPaymentService.getById(dataCompanyIprPatentPaymentId.getId());
        DataCompanyIprPatentPayment dataCompanyIprPatentPayment = DomainFactory.create(DataCompanyIprPatentPayment.class);
        dataCompanyIprPatentPayment = DataCompanyIprPatentPaymentInfrastructureStructMapping.instance. dataCompanyIprPatentPaymentDOToDataCompanyIprPatentPayment(dataCompanyIprPatentPayment,byId);
        return dataCompanyIprPatentPayment;
    }

    @Override
    public boolean doSave(DataCompanyIprPatentPayment dataCompanyIprPatentPayment) {
        DataCompanyIprPatentPaymentDO dataCompanyIprPatentPaymentDO = DataCompanyIprPatentPaymentInfrastructureStructMapping.instance.dataCompanyIprPatentPaymentToDataCompanyIprPatentPaymentDO(dataCompanyIprPatentPayment);
        if (dataCompanyIprPatentPaymentDO.getId() == null) {
            dataCompanyIprPatentPaymentDO.setAddControl(dataCompanyIprPatentPayment.getAddControl());
            DataCompanyIprPatentPaymentDO add = iDataCompanyIprPatentPaymentService.add(dataCompanyIprPatentPaymentDO);
            dataCompanyIprPatentPayment.setId(DataCompanyIprPatentPaymentId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprPatentPaymentDO.setUpdateControl(dataCompanyIprPatentPayment.getUpdateControl());
        DataCompanyIprPatentPaymentDO update = iDataCompanyIprPatentPaymentService.update(dataCompanyIprPatentPaymentDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprPatentPaymentId dataCompanyIprPatentPaymentId) {
        return iDataCompanyIprPatentPaymentService.deleteById(dataCompanyIprPatentPaymentId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprPatentPaymentId dataCompanyIprPatentPaymentId, IdCommand idCommand) {
        return iDataCompanyIprPatentPaymentService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprPatentPaymentService(IDataCompanyIprPatentPaymentService iDataCompanyIprPatentPaymentService) {
        this.iDataCompanyIprPatentPaymentService = iDataCompanyIprPatentPaymentService;
    }
}
