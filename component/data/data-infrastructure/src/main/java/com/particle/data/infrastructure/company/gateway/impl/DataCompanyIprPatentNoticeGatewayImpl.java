package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprPatentNotice;
import com.particle.data.domain.company.DataCompanyIprPatentNoticeId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentNoticeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentNoticeService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentNoticeDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprPatentNoticeInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权专利通知书信息 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:13
 */
@Component
public class DataCompanyIprPatentNoticeGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprPatentNoticeId,DataCompanyIprPatentNotice> implements DataCompanyIprPatentNoticeGateway {

    private IDataCompanyIprPatentNoticeService iDataCompanyIprPatentNoticeService;

    @Override
    public DataCompanyIprPatentNotice getById(DataCompanyIprPatentNoticeId dataCompanyIprPatentNoticeId) {
        DataCompanyIprPatentNoticeDO byId = iDataCompanyIprPatentNoticeService.getById(dataCompanyIprPatentNoticeId.getId());
        DataCompanyIprPatentNotice dataCompanyIprPatentNotice = DomainFactory.create(DataCompanyIprPatentNotice.class);
        dataCompanyIprPatentNotice = DataCompanyIprPatentNoticeInfrastructureStructMapping.instance. dataCompanyIprPatentNoticeDOToDataCompanyIprPatentNotice(dataCompanyIprPatentNotice,byId);
        return dataCompanyIprPatentNotice;
    }

    @Override
    public boolean doSave(DataCompanyIprPatentNotice dataCompanyIprPatentNotice) {
        DataCompanyIprPatentNoticeDO dataCompanyIprPatentNoticeDO = DataCompanyIprPatentNoticeInfrastructureStructMapping.instance.dataCompanyIprPatentNoticeToDataCompanyIprPatentNoticeDO(dataCompanyIprPatentNotice);
        if (dataCompanyIprPatentNoticeDO.getId() == null) {
            dataCompanyIprPatentNoticeDO.setAddControl(dataCompanyIprPatentNotice.getAddControl());
            DataCompanyIprPatentNoticeDO add = iDataCompanyIprPatentNoticeService.add(dataCompanyIprPatentNoticeDO);
            dataCompanyIprPatentNotice.setId(DataCompanyIprPatentNoticeId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprPatentNoticeDO.setUpdateControl(dataCompanyIprPatentNotice.getUpdateControl());
        DataCompanyIprPatentNoticeDO update = iDataCompanyIprPatentNoticeService.update(dataCompanyIprPatentNoticeDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprPatentNoticeId dataCompanyIprPatentNoticeId) {
        return iDataCompanyIprPatentNoticeService.deleteById(dataCompanyIprPatentNoticeId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprPatentNoticeId dataCompanyIprPatentNoticeId, IdCommand idCommand) {
        return iDataCompanyIprPatentNoticeService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprPatentNoticeService(IDataCompanyIprPatentNoticeService iDataCompanyIprPatentNoticeService) {
        this.iDataCompanyIprPatentNoticeService = iDataCompanyIprPatentNoticeService;
    }
}
