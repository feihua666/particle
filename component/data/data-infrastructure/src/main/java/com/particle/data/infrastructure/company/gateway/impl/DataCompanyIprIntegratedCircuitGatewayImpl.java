package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprIntegratedCircuit;
import com.particle.data.domain.company.DataCompanyIprIntegratedCircuitId;
import com.particle.data.domain.company.gateway.DataCompanyIprIntegratedCircuitGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprIntegratedCircuitService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprIntegratedCircuitDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprIntegratedCircuitInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权集成电路 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:28
 */
@Component
public class DataCompanyIprIntegratedCircuitGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprIntegratedCircuitId,DataCompanyIprIntegratedCircuit> implements DataCompanyIprIntegratedCircuitGateway {

    private IDataCompanyIprIntegratedCircuitService iDataCompanyIprIntegratedCircuitService;

    @Override
    public DataCompanyIprIntegratedCircuit getById(DataCompanyIprIntegratedCircuitId dataCompanyIprIntegratedCircuitId) {
        DataCompanyIprIntegratedCircuitDO byId = iDataCompanyIprIntegratedCircuitService.getById(dataCompanyIprIntegratedCircuitId.getId());
        DataCompanyIprIntegratedCircuit dataCompanyIprIntegratedCircuit = DomainFactory.create(DataCompanyIprIntegratedCircuit.class);
        dataCompanyIprIntegratedCircuit = DataCompanyIprIntegratedCircuitInfrastructureStructMapping.instance. dataCompanyIprIntegratedCircuitDOToDataCompanyIprIntegratedCircuit(dataCompanyIprIntegratedCircuit,byId);
        return dataCompanyIprIntegratedCircuit;
    }

    @Override
    public boolean doSave(DataCompanyIprIntegratedCircuit dataCompanyIprIntegratedCircuit) {
        DataCompanyIprIntegratedCircuitDO dataCompanyIprIntegratedCircuitDO = DataCompanyIprIntegratedCircuitInfrastructureStructMapping.instance.dataCompanyIprIntegratedCircuitToDataCompanyIprIntegratedCircuitDO(dataCompanyIprIntegratedCircuit);
        if (dataCompanyIprIntegratedCircuitDO.getId() == null) {
            dataCompanyIprIntegratedCircuitDO.setAddControl(dataCompanyIprIntegratedCircuit.getAddControl());
            DataCompanyIprIntegratedCircuitDO add = iDataCompanyIprIntegratedCircuitService.add(dataCompanyIprIntegratedCircuitDO);
            dataCompanyIprIntegratedCircuit.setId(DataCompanyIprIntegratedCircuitId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprIntegratedCircuitDO.setUpdateControl(dataCompanyIprIntegratedCircuit.getUpdateControl());
        DataCompanyIprIntegratedCircuitDO update = iDataCompanyIprIntegratedCircuitService.update(dataCompanyIprIntegratedCircuitDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprIntegratedCircuitId dataCompanyIprIntegratedCircuitId) {
        return iDataCompanyIprIntegratedCircuitService.deleteById(dataCompanyIprIntegratedCircuitId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprIntegratedCircuitId dataCompanyIprIntegratedCircuitId, IdCommand idCommand) {
        return iDataCompanyIprIntegratedCircuitService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprIntegratedCircuitService(IDataCompanyIprIntegratedCircuitService iDataCompanyIprIntegratedCircuitService) {
        this.iDataCompanyIprIntegratedCircuitService = iDataCompanyIprIntegratedCircuitService;
    }
}
