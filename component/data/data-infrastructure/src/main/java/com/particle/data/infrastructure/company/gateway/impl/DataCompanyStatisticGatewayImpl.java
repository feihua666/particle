package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyStatistic;
import com.particle.data.domain.company.DataCompanyStatisticId;
import com.particle.data.domain.company.gateway.DataCompanyStatisticGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyStatisticService;
import com.particle.data.infrastructure.company.dos.DataCompanyStatisticDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyStatisticInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业统计 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-04 15:53:01
 */
@Component
public class DataCompanyStatisticGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyStatisticId,DataCompanyStatistic> implements DataCompanyStatisticGateway {

    private IDataCompanyStatisticService iDataCompanyStatisticService;

    @Override
    public DataCompanyStatistic getById(DataCompanyStatisticId dataCompanyStatisticId) {
        DataCompanyStatisticDO byId = iDataCompanyStatisticService.getById(dataCompanyStatisticId.getId());
        DataCompanyStatistic dataCompanyStatistic = DomainFactory.create(DataCompanyStatistic.class);
        dataCompanyStatistic = DataCompanyStatisticInfrastructureStructMapping.instance. dataCompanyStatisticDOToDataCompanyStatistic(dataCompanyStatistic,byId);
        return dataCompanyStatistic;
    }

    @Override
    public boolean doSave(DataCompanyStatistic dataCompanyStatistic) {
        DataCompanyStatisticDO dataCompanyStatisticDO = DataCompanyStatisticInfrastructureStructMapping.instance.dataCompanyStatisticToDataCompanyStatisticDO(dataCompanyStatistic);
        if (dataCompanyStatisticDO.getId() == null) {
            dataCompanyStatisticDO.setAddControl(dataCompanyStatistic.getAddControl());
            DataCompanyStatisticDO add = iDataCompanyStatisticService.add(dataCompanyStatisticDO);
            dataCompanyStatistic.setId(DataCompanyStatisticId.of(add.getId()));
            return add != null;
        }
        dataCompanyStatisticDO.setUpdateControl(dataCompanyStatistic.getUpdateControl());
        DataCompanyStatisticDO update = iDataCompanyStatisticService.update(dataCompanyStatisticDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyStatisticId dataCompanyStatisticId) {
        return iDataCompanyStatisticService.deleteById(dataCompanyStatisticId.getId());
    }

    @Override
    public boolean delete(DataCompanyStatisticId dataCompanyStatisticId, IdCommand idCommand) {
        return iDataCompanyStatisticService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyStatisticService(IDataCompanyStatisticService iDataCompanyStatisticService) {
        this.iDataCompanyStatisticService = iDataCompanyStatisticService;
    }
}
