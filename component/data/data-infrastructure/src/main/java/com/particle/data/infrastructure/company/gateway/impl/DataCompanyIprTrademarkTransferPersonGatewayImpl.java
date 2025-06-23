package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprTrademarkTransferPerson;
import com.particle.data.domain.company.DataCompanyIprTrademarkTransferPersonId;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkTransferPersonGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkTransferPersonService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkTransferPersonDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprTrademarkTransferPersonInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权商标转让人 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:13
 */
@Component
public class DataCompanyIprTrademarkTransferPersonGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprTrademarkTransferPersonId,DataCompanyIprTrademarkTransferPerson> implements DataCompanyIprTrademarkTransferPersonGateway {

    private IDataCompanyIprTrademarkTransferPersonService iDataCompanyIprTrademarkTransferPersonService;

    @Override
    public DataCompanyIprTrademarkTransferPerson getById(DataCompanyIprTrademarkTransferPersonId dataCompanyIprTrademarkTransferPersonId) {
        DataCompanyIprTrademarkTransferPersonDO byId = iDataCompanyIprTrademarkTransferPersonService.getById(dataCompanyIprTrademarkTransferPersonId.getId());
        DataCompanyIprTrademarkTransferPerson dataCompanyIprTrademarkTransferPerson = DomainFactory.create(DataCompanyIprTrademarkTransferPerson.class);
        dataCompanyIprTrademarkTransferPerson = DataCompanyIprTrademarkTransferPersonInfrastructureStructMapping.instance. dataCompanyIprTrademarkTransferPersonDOToDataCompanyIprTrademarkTransferPerson(dataCompanyIprTrademarkTransferPerson,byId);
        return dataCompanyIprTrademarkTransferPerson;
    }

    @Override
    public boolean doSave(DataCompanyIprTrademarkTransferPerson dataCompanyIprTrademarkTransferPerson) {
        DataCompanyIprTrademarkTransferPersonDO dataCompanyIprTrademarkTransferPersonDO = DataCompanyIprTrademarkTransferPersonInfrastructureStructMapping.instance.dataCompanyIprTrademarkTransferPersonToDataCompanyIprTrademarkTransferPersonDO(dataCompanyIprTrademarkTransferPerson);
        if (dataCompanyIprTrademarkTransferPersonDO.getId() == null) {
            dataCompanyIprTrademarkTransferPersonDO.setAddControl(dataCompanyIprTrademarkTransferPerson.getAddControl());
            DataCompanyIprTrademarkTransferPersonDO add = iDataCompanyIprTrademarkTransferPersonService.add(dataCompanyIprTrademarkTransferPersonDO);
            dataCompanyIprTrademarkTransferPerson.setId(DataCompanyIprTrademarkTransferPersonId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprTrademarkTransferPersonDO.setUpdateControl(dataCompanyIprTrademarkTransferPerson.getUpdateControl());
        DataCompanyIprTrademarkTransferPersonDO update = iDataCompanyIprTrademarkTransferPersonService.update(dataCompanyIprTrademarkTransferPersonDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprTrademarkTransferPersonId dataCompanyIprTrademarkTransferPersonId) {
        return iDataCompanyIprTrademarkTransferPersonService.deleteById(dataCompanyIprTrademarkTransferPersonId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprTrademarkTransferPersonId dataCompanyIprTrademarkTransferPersonId, IdCommand idCommand) {
        return iDataCompanyIprTrademarkTransferPersonService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprTrademarkTransferPersonService(IDataCompanyIprTrademarkTransferPersonService iDataCompanyIprTrademarkTransferPersonService) {
        this.iDataCompanyIprTrademarkTransferPersonService = iDataCompanyIprTrademarkTransferPersonService;
    }
}
