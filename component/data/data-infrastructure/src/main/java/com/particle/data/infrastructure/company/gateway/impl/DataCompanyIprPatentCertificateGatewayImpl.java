package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprPatentCertificate;
import com.particle.data.domain.company.DataCompanyIprPatentCertificateId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentCertificateGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentCertificateService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentCertificateDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprPatentCertificateInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权专利证书信息 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:02
 */
@Component
public class DataCompanyIprPatentCertificateGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprPatentCertificateId,DataCompanyIprPatentCertificate> implements DataCompanyIprPatentCertificateGateway {

    private IDataCompanyIprPatentCertificateService iDataCompanyIprPatentCertificateService;

    @Override
    public DataCompanyIprPatentCertificate getById(DataCompanyIprPatentCertificateId dataCompanyIprPatentCertificateId) {
        DataCompanyIprPatentCertificateDO byId = iDataCompanyIprPatentCertificateService.getById(dataCompanyIprPatentCertificateId.getId());
        DataCompanyIprPatentCertificate dataCompanyIprPatentCertificate = DomainFactory.create(DataCompanyIprPatentCertificate.class);
        dataCompanyIprPatentCertificate = DataCompanyIprPatentCertificateInfrastructureStructMapping.instance. dataCompanyIprPatentCertificateDOToDataCompanyIprPatentCertificate(dataCompanyIprPatentCertificate,byId);
        return dataCompanyIprPatentCertificate;
    }

    @Override
    public boolean doSave(DataCompanyIprPatentCertificate dataCompanyIprPatentCertificate) {
        DataCompanyIprPatentCertificateDO dataCompanyIprPatentCertificateDO = DataCompanyIprPatentCertificateInfrastructureStructMapping.instance.dataCompanyIprPatentCertificateToDataCompanyIprPatentCertificateDO(dataCompanyIprPatentCertificate);
        if (dataCompanyIprPatentCertificateDO.getId() == null) {
            dataCompanyIprPatentCertificateDO.setAddControl(dataCompanyIprPatentCertificate.getAddControl());
            DataCompanyIprPatentCertificateDO add = iDataCompanyIprPatentCertificateService.add(dataCompanyIprPatentCertificateDO);
            dataCompanyIprPatentCertificate.setId(DataCompanyIprPatentCertificateId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprPatentCertificateDO.setUpdateControl(dataCompanyIprPatentCertificate.getUpdateControl());
        DataCompanyIprPatentCertificateDO update = iDataCompanyIprPatentCertificateService.update(dataCompanyIprPatentCertificateDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprPatentCertificateId dataCompanyIprPatentCertificateId) {
        return iDataCompanyIprPatentCertificateService.deleteById(dataCompanyIprPatentCertificateId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprPatentCertificateId dataCompanyIprPatentCertificateId, IdCommand idCommand) {
        return iDataCompanyIprPatentCertificateService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprPatentCertificateService(IDataCompanyIprPatentCertificateService iDataCompanyIprPatentCertificateService) {
        this.iDataCompanyIprPatentCertificateService = iDataCompanyIprPatentCertificateService;
    }
}
