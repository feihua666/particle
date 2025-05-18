package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyOpenCourtAnnouncementPartyCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyOpenCourtAnnouncementPartyDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyOpenCourtAnnouncementPartyUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyOpenCourtAnnouncementPartyCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementPartyUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyOpenCourtAnnouncementPartyApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementPartyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementPartyVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyOpenCourtAnnouncementPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementPartyExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyOpenCourtAnnouncementPartyWarehouseCommandExecutor;
/**
 * <p>
 * 企业开庭公告当事人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:03
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyOpenCourtAnnouncementPartyApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyOpenCourtAnnouncementPartyApplicationService {

    private DataCompanyOpenCourtAnnouncementPartyCreateCommandExecutor dataCompanyOpenCourtAnnouncementPartyCreateCommandExecutor;

    private DataCompanyOpenCourtAnnouncementPartyDeleteCommandExecutor dataCompanyOpenCourtAnnouncementPartyDeleteCommandExecutor;

    private DataCompanyOpenCourtAnnouncementPartyUpdateCommandExecutor dataCompanyOpenCourtAnnouncementPartyUpdateCommandExecutor;

    private DataCompanyOpenCourtAnnouncementPartyCommandExecutor dataCompanyOpenCourtAnnouncementPartyCommandExecutor;

    private DataCompanyOpenCourtAnnouncementPartyWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyOpenCourtAnnouncementPartyVO> create(DataCompanyOpenCourtAnnouncementPartyCreateCommand dataCompanyOpenCourtAnnouncementPartyCreateCommand) {
        return dataCompanyOpenCourtAnnouncementPartyCreateCommandExecutor.execute(dataCompanyOpenCourtAnnouncementPartyCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyOpenCourtAnnouncementPartyVO> delete(IdCommand deleteCommand) {
        return dataCompanyOpenCourtAnnouncementPartyDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyOpenCourtAnnouncementPartyVO> update(DataCompanyOpenCourtAnnouncementPartyUpdateCommand dataCompanyOpenCourtAnnouncementPartyUpdateCommand) {
        return dataCompanyOpenCourtAnnouncementPartyUpdateCommandExecutor.execute(dataCompanyOpenCourtAnnouncementPartyUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyOpenCourtAnnouncementPartyExWarehouseVO> warehouse(DataCompanyOpenCourtAnnouncementPartyWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyOpenCourtAnnouncementPartyCreateCommandExecutor(DataCompanyOpenCourtAnnouncementPartyCreateCommandExecutor dataCompanyOpenCourtAnnouncementPartyCreateCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementPartyCreateCommandExecutor = dataCompanyOpenCourtAnnouncementPartyCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyOpenCourtAnnouncementPartyDeleteCommandExecutor(DataCompanyOpenCourtAnnouncementPartyDeleteCommandExecutor dataCompanyOpenCourtAnnouncementPartyDeleteCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementPartyDeleteCommandExecutor = dataCompanyOpenCourtAnnouncementPartyDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyOpenCourtAnnouncementPartyUpdateCommandExecutor(DataCompanyOpenCourtAnnouncementPartyUpdateCommandExecutor dataCompanyOpenCourtAnnouncementPartyUpdateCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementPartyUpdateCommandExecutor = dataCompanyOpenCourtAnnouncementPartyUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyOpenCourtAnnouncementPartyCommandExecutor(DataCompanyOpenCourtAnnouncementPartyCommandExecutor dataCompanyOpenCourtAnnouncementPartyCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementPartyCommandExecutor = dataCompanyOpenCourtAnnouncementPartyCommandExecutor;
    }
    @Autowired
    public void setDataCompanyOpenCourtAnnouncementPartyWarehouseCommandExecutor(DataCompanyOpenCourtAnnouncementPartyWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}