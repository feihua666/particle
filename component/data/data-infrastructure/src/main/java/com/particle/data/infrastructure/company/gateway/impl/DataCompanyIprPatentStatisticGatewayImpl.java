package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprPatentStatistic;
import com.particle.data.domain.company.DataCompanyIprPatentStatisticId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentStatisticGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentStatisticService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentStatisticDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprPatentStatisticInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权专利统计 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:36
 */
@Component
public class DataCompanyIprPatentStatisticGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprPatentStatisticId,DataCompanyIprPatentStatistic> implements DataCompanyIprPatentStatisticGateway {

    private IDataCompanyIprPatentStatisticService iDataCompanyIprPatentStatisticService;

    @Override
    public DataCompanyIprPatentStatistic getById(DataCompanyIprPatentStatisticId dataCompanyIprPatentStatisticId) {
        DataCompanyIprPatentStatisticDO byId = iDataCompanyIprPatentStatisticService.getById(dataCompanyIprPatentStatisticId.getId());
        DataCompanyIprPatentStatistic dataCompanyIprPatentStatistic = DomainFactory.create(DataCompanyIprPatentStatistic.class);
        dataCompanyIprPatentStatistic = DataCompanyIprPatentStatisticInfrastructureStructMapping.instance. dataCompanyIprPatentStatisticDOToDataCompanyIprPatentStatistic(dataCompanyIprPatentStatistic,byId);
        return dataCompanyIprPatentStatistic;
    }

    @Override
    public boolean doSave(DataCompanyIprPatentStatistic dataCompanyIprPatentStatistic) {
        DataCompanyIprPatentStatisticDO dataCompanyIprPatentStatisticDO = DataCompanyIprPatentStatisticInfrastructureStructMapping.instance.dataCompanyIprPatentStatisticToDataCompanyIprPatentStatisticDO(dataCompanyIprPatentStatistic);
        if (dataCompanyIprPatentStatisticDO.getId() == null) {
            dataCompanyIprPatentStatisticDO.setAddControl(dataCompanyIprPatentStatistic.getAddControl());
            DataCompanyIprPatentStatisticDO add = iDataCompanyIprPatentStatisticService.add(dataCompanyIprPatentStatisticDO);
            dataCompanyIprPatentStatistic.setId(DataCompanyIprPatentStatisticId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprPatentStatisticDO.setUpdateControl(dataCompanyIprPatentStatistic.getUpdateControl());
        DataCompanyIprPatentStatisticDO update = iDataCompanyIprPatentStatisticService.update(dataCompanyIprPatentStatisticDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprPatentStatisticId dataCompanyIprPatentStatisticId) {
        return iDataCompanyIprPatentStatisticService.deleteById(dataCompanyIprPatentStatisticId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprPatentStatisticId dataCompanyIprPatentStatisticId, IdCommand idCommand) {
        return iDataCompanyIprPatentStatisticService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprPatentStatisticService(IDataCompanyIprPatentStatisticService iDataCompanyIprPatentStatisticService) {
        this.iDataCompanyIprPatentStatisticService = iDataCompanyIprPatentStatisticService;
    }
}
