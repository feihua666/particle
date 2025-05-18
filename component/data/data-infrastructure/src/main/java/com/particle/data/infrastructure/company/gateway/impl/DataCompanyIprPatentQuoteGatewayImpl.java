package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprPatentQuote;
import com.particle.data.domain.company.DataCompanyIprPatentQuoteId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentQuoteGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentQuoteService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentQuoteDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprPatentQuoteInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权专利引证信息 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:24
 */
@Component
public class DataCompanyIprPatentQuoteGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprPatentQuoteId,DataCompanyIprPatentQuote> implements DataCompanyIprPatentQuoteGateway {

    private IDataCompanyIprPatentQuoteService iDataCompanyIprPatentQuoteService;

    @Override
    public DataCompanyIprPatentQuote getById(DataCompanyIprPatentQuoteId dataCompanyIprPatentQuoteId) {
        DataCompanyIprPatentQuoteDO byId = iDataCompanyIprPatentQuoteService.getById(dataCompanyIprPatentQuoteId.getId());
        DataCompanyIprPatentQuote dataCompanyIprPatentQuote = DomainFactory.create(DataCompanyIprPatentQuote.class);
        dataCompanyIprPatentQuote = DataCompanyIprPatentQuoteInfrastructureStructMapping.instance. dataCompanyIprPatentQuoteDOToDataCompanyIprPatentQuote(dataCompanyIprPatentQuote,byId);
        return dataCompanyIprPatentQuote;
    }

    @Override
    public boolean doSave(DataCompanyIprPatentQuote dataCompanyIprPatentQuote) {
        DataCompanyIprPatentQuoteDO dataCompanyIprPatentQuoteDO = DataCompanyIprPatentQuoteInfrastructureStructMapping.instance.dataCompanyIprPatentQuoteToDataCompanyIprPatentQuoteDO(dataCompanyIprPatentQuote);
        if (dataCompanyIprPatentQuoteDO.getId() == null) {
            dataCompanyIprPatentQuoteDO.setAddControl(dataCompanyIprPatentQuote.getAddControl());
            DataCompanyIprPatentQuoteDO add = iDataCompanyIprPatentQuoteService.add(dataCompanyIprPatentQuoteDO);
            dataCompanyIprPatentQuote.setId(DataCompanyIprPatentQuoteId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprPatentQuoteDO.setUpdateControl(dataCompanyIprPatentQuote.getUpdateControl());
        DataCompanyIprPatentQuoteDO update = iDataCompanyIprPatentQuoteService.update(dataCompanyIprPatentQuoteDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprPatentQuoteId dataCompanyIprPatentQuoteId) {
        return iDataCompanyIprPatentQuoteService.deleteById(dataCompanyIprPatentQuoteId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprPatentQuoteId dataCompanyIprPatentQuoteId, IdCommand idCommand) {
        return iDataCompanyIprPatentQuoteService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprPatentQuoteService(IDataCompanyIprPatentQuoteService iDataCompanyIprPatentQuoteService) {
        this.iDataCompanyIprPatentQuoteService = iDataCompanyIprPatentQuoteService;
    }
}
