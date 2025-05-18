package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyVcProduct;
import com.particle.data.domain.company.DataCompanyVcProductId;
import com.particle.data.domain.company.gateway.DataCompanyVcProductGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyVcProductService;
import com.particle.data.infrastructure.company.dos.DataCompanyVcProductDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyVcProductInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业融资产品 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:14
 */
@Component
public class DataCompanyVcProductGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyVcProductId,DataCompanyVcProduct> implements DataCompanyVcProductGateway {

    private IDataCompanyVcProductService iDataCompanyVcProductService;

    @Override
    public DataCompanyVcProduct getById(DataCompanyVcProductId dataCompanyVcProductId) {
        DataCompanyVcProductDO byId = iDataCompanyVcProductService.getById(dataCompanyVcProductId.getId());
        DataCompanyVcProduct dataCompanyVcProduct = DomainFactory.create(DataCompanyVcProduct.class);
        dataCompanyVcProduct = DataCompanyVcProductInfrastructureStructMapping.instance. dataCompanyVcProductDOToDataCompanyVcProduct(dataCompanyVcProduct,byId);
        return dataCompanyVcProduct;
    }

    @Override
    public boolean doSave(DataCompanyVcProduct dataCompanyVcProduct) {
        DataCompanyVcProductDO dataCompanyVcProductDO = DataCompanyVcProductInfrastructureStructMapping.instance.dataCompanyVcProductToDataCompanyVcProductDO(dataCompanyVcProduct);
        if (dataCompanyVcProductDO.getId() == null) {
            dataCompanyVcProductDO.setAddControl(dataCompanyVcProduct.getAddControl());
            DataCompanyVcProductDO add = iDataCompanyVcProductService.add(dataCompanyVcProductDO);
            dataCompanyVcProduct.setId(DataCompanyVcProductId.of(add.getId()));
            return add != null;
        }
        dataCompanyVcProductDO.setUpdateControl(dataCompanyVcProduct.getUpdateControl());
        DataCompanyVcProductDO update = iDataCompanyVcProductService.update(dataCompanyVcProductDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyVcProductId dataCompanyVcProductId) {
        return iDataCompanyVcProductService.deleteById(dataCompanyVcProductId.getId());
    }

    @Override
    public boolean delete(DataCompanyVcProductId dataCompanyVcProductId, IdCommand idCommand) {
        return iDataCompanyVcProductService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyVcProductService(IDataCompanyVcProductService iDataCompanyVcProductService) {
        this.iDataCompanyVcProductService = iDataCompanyVcProductService;
    }
}
