package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyHonorQualification;
import com.particle.data.domain.company.DataCompanyHonorQualificationId;
import com.particle.data.domain.company.gateway.DataCompanyHonorQualificationGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyHonorQualificationService;
import com.particle.data.infrastructure.company.dos.DataCompanyHonorQualificationDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyHonorQualificationInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业荣誉资质 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:14
 */
@Component
public class DataCompanyHonorQualificationGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyHonorQualificationId,DataCompanyHonorQualification> implements DataCompanyHonorQualificationGateway {

    private IDataCompanyHonorQualificationService iDataCompanyHonorQualificationService;

    @Override
    public DataCompanyHonorQualification getById(DataCompanyHonorQualificationId dataCompanyHonorQualificationId) {
        DataCompanyHonorQualificationDO byId = iDataCompanyHonorQualificationService.getById(dataCompanyHonorQualificationId.getId());
        DataCompanyHonorQualification dataCompanyHonorQualification = DomainFactory.create(DataCompanyHonorQualification.class);
        dataCompanyHonorQualification = DataCompanyHonorQualificationInfrastructureStructMapping.instance. dataCompanyHonorQualificationDOToDataCompanyHonorQualification(dataCompanyHonorQualification,byId);
        return dataCompanyHonorQualification;
    }

    @Override
    public boolean doSave(DataCompanyHonorQualification dataCompanyHonorQualification) {
        DataCompanyHonorQualificationDO dataCompanyHonorQualificationDO = DataCompanyHonorQualificationInfrastructureStructMapping.instance.dataCompanyHonorQualificationToDataCompanyHonorQualificationDO(dataCompanyHonorQualification);
        if (dataCompanyHonorQualificationDO.getId() == null) {
            dataCompanyHonorQualificationDO.setAddControl(dataCompanyHonorQualification.getAddControl());
            DataCompanyHonorQualificationDO add = iDataCompanyHonorQualificationService.add(dataCompanyHonorQualificationDO);
            dataCompanyHonorQualification.setId(DataCompanyHonorQualificationId.of(add.getId()));
            return add != null;
        }
        dataCompanyHonorQualificationDO.setUpdateControl(dataCompanyHonorQualification.getUpdateControl());
        DataCompanyHonorQualificationDO update = iDataCompanyHonorQualificationService.update(dataCompanyHonorQualificationDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyHonorQualificationId dataCompanyHonorQualificationId) {
        return iDataCompanyHonorQualificationService.deleteById(dataCompanyHonorQualificationId.getId());
    }

    @Override
    public boolean delete(DataCompanyHonorQualificationId dataCompanyHonorQualificationId, IdCommand idCommand) {
        return iDataCompanyHonorQualificationService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyHonorQualificationService(IDataCompanyHonorQualificationService iDataCompanyHonorQualificationService) {
        this.iDataCompanyHonorQualificationService = iDataCompanyHonorQualificationService;
    }
}
