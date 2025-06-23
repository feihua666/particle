package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyPrimeStaff;
import com.particle.data.domain.company.DataCompanyPrimeStaffId;
import com.particle.data.domain.company.gateway.DataCompanyPrimeStaffGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyPrimeStaffService;
import com.particle.data.infrastructure.company.dos.DataCompanyPrimeStaffDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyPrimeStaffInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业主要人员 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:44
 */
@Component
public class DataCompanyPrimeStaffGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyPrimeStaffId,DataCompanyPrimeStaff> implements DataCompanyPrimeStaffGateway {

    private IDataCompanyPrimeStaffService iDataCompanyPrimeStaffService;

    @Override
    public DataCompanyPrimeStaff getById(DataCompanyPrimeStaffId dataCompanyPrimeStaffId) {
        DataCompanyPrimeStaffDO byId = iDataCompanyPrimeStaffService.getById(dataCompanyPrimeStaffId.getId());
        DataCompanyPrimeStaff dataCompanyPrimeStaff = DomainFactory.create(DataCompanyPrimeStaff.class);
        dataCompanyPrimeStaff = DataCompanyPrimeStaffInfrastructureStructMapping.instance. dataCompanyPrimeStaffDOToDataCompanyPrimeStaff(dataCompanyPrimeStaff,byId);
        return dataCompanyPrimeStaff;
    }

    @Override
    public boolean doSave(DataCompanyPrimeStaff dataCompanyPrimeStaff) {
        DataCompanyPrimeStaffDO dataCompanyPrimeStaffDO = DataCompanyPrimeStaffInfrastructureStructMapping.instance.dataCompanyPrimeStaffToDataCompanyPrimeStaffDO(dataCompanyPrimeStaff);
        if (dataCompanyPrimeStaffDO.getId() == null) {
            dataCompanyPrimeStaffDO.setAddControl(dataCompanyPrimeStaff.getAddControl());
            DataCompanyPrimeStaffDO add = iDataCompanyPrimeStaffService.add(dataCompanyPrimeStaffDO);
            dataCompanyPrimeStaff.setId(DataCompanyPrimeStaffId.of(add.getId()));
            return add != null;
        }
        dataCompanyPrimeStaffDO.setUpdateControl(dataCompanyPrimeStaff.getUpdateControl());
        DataCompanyPrimeStaffDO update = iDataCompanyPrimeStaffService.update(dataCompanyPrimeStaffDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyPrimeStaffId dataCompanyPrimeStaffId) {
        return iDataCompanyPrimeStaffService.deleteById(dataCompanyPrimeStaffId.getId());
    }

    @Override
    public boolean delete(DataCompanyPrimeStaffId dataCompanyPrimeStaffId, IdCommand idCommand) {
        return iDataCompanyPrimeStaffService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyPrimeStaffService(IDataCompanyPrimeStaffService iDataCompanyPrimeStaffService) {
        this.iDataCompanyPrimeStaffService = iDataCompanyPrimeStaffService;
    }
}
