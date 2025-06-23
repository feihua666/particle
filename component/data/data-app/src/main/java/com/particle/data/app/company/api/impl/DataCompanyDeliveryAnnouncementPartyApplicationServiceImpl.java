package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyDeliveryAnnouncementPartyCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyDeliveryAnnouncementPartyDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyDeliveryAnnouncementPartyUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyDeliveryAnnouncementPartyCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementPartyUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyDeliveryAnnouncementPartyApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementPartyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementPartyVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyDeliveryAnnouncementPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementPartyExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyDeliveryAnnouncementPartyWarehouseCommandExecutor;
/**
 * <p>
 * 企业送达公告当事人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:33
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyDeliveryAnnouncementPartyApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyDeliveryAnnouncementPartyApplicationService {

    private DataCompanyDeliveryAnnouncementPartyCreateCommandExecutor dataCompanyDeliveryAnnouncementPartyCreateCommandExecutor;

    private DataCompanyDeliveryAnnouncementPartyDeleteCommandExecutor dataCompanyDeliveryAnnouncementPartyDeleteCommandExecutor;

    private DataCompanyDeliveryAnnouncementPartyUpdateCommandExecutor dataCompanyDeliveryAnnouncementPartyUpdateCommandExecutor;

    private DataCompanyDeliveryAnnouncementPartyCommandExecutor dataCompanyDeliveryAnnouncementPartyCommandExecutor;

    private DataCompanyDeliveryAnnouncementPartyWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyDeliveryAnnouncementPartyVO> create(DataCompanyDeliveryAnnouncementPartyCreateCommand dataCompanyDeliveryAnnouncementPartyCreateCommand) {
        return dataCompanyDeliveryAnnouncementPartyCreateCommandExecutor.execute(dataCompanyDeliveryAnnouncementPartyCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyDeliveryAnnouncementPartyVO> delete(IdCommand deleteCommand) {
        return dataCompanyDeliveryAnnouncementPartyDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyDeliveryAnnouncementPartyVO> update(DataCompanyDeliveryAnnouncementPartyUpdateCommand dataCompanyDeliveryAnnouncementPartyUpdateCommand) {
        return dataCompanyDeliveryAnnouncementPartyUpdateCommandExecutor.execute(dataCompanyDeliveryAnnouncementPartyUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyDeliveryAnnouncementPartyExWarehouseVO> warehouse(DataCompanyDeliveryAnnouncementPartyWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyDeliveryAnnouncementPartyCreateCommandExecutor(DataCompanyDeliveryAnnouncementPartyCreateCommandExecutor dataCompanyDeliveryAnnouncementPartyCreateCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementPartyCreateCommandExecutor = dataCompanyDeliveryAnnouncementPartyCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyDeliveryAnnouncementPartyDeleteCommandExecutor(DataCompanyDeliveryAnnouncementPartyDeleteCommandExecutor dataCompanyDeliveryAnnouncementPartyDeleteCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementPartyDeleteCommandExecutor = dataCompanyDeliveryAnnouncementPartyDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyDeliveryAnnouncementPartyUpdateCommandExecutor(DataCompanyDeliveryAnnouncementPartyUpdateCommandExecutor dataCompanyDeliveryAnnouncementPartyUpdateCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementPartyUpdateCommandExecutor = dataCompanyDeliveryAnnouncementPartyUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyDeliveryAnnouncementPartyCommandExecutor(DataCompanyDeliveryAnnouncementPartyCommandExecutor dataCompanyDeliveryAnnouncementPartyCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementPartyCommandExecutor = dataCompanyDeliveryAnnouncementPartyCommandExecutor;
    }
    @Autowired
    public void setDataCompanyDeliveryAnnouncementPartyWarehouseCommandExecutor(DataCompanyDeliveryAnnouncementPartyWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}