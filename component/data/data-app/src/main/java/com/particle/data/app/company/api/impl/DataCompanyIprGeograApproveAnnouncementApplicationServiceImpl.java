package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprGeograApproveAnnouncementCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprGeograApproveAnnouncementDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprGeograApproveAnnouncementUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprGeograApproveAnnouncementCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprGeograApproveAnnouncementUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprGeograApproveAnnouncementApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprGeograApproveAnnouncementCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprGeograApproveAnnouncementVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprGeograApproveAnnouncementWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprGeograApproveAnnouncementExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprGeograApproveAnnouncementWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权地理标识核准公告 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:21
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprGeograApproveAnnouncementApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprGeograApproveAnnouncementApplicationService {

    private DataCompanyIprGeograApproveAnnouncementCreateCommandExecutor dataCompanyIprGeograApproveAnnouncementCreateCommandExecutor;

    private DataCompanyIprGeograApproveAnnouncementDeleteCommandExecutor dataCompanyIprGeograApproveAnnouncementDeleteCommandExecutor;

    private DataCompanyIprGeograApproveAnnouncementUpdateCommandExecutor dataCompanyIprGeograApproveAnnouncementUpdateCommandExecutor;

    private DataCompanyIprGeograApproveAnnouncementCommandExecutor dataCompanyIprGeograApproveAnnouncementCommandExecutor;

    private DataCompanyIprGeograApproveAnnouncementWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprGeograApproveAnnouncementVO> create(DataCompanyIprGeograApproveAnnouncementCreateCommand dataCompanyIprGeograApproveAnnouncementCreateCommand) {
        return dataCompanyIprGeograApproveAnnouncementCreateCommandExecutor.execute(dataCompanyIprGeograApproveAnnouncementCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprGeograApproveAnnouncementVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprGeograApproveAnnouncementDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprGeograApproveAnnouncementVO> update(DataCompanyIprGeograApproveAnnouncementUpdateCommand dataCompanyIprGeograApproveAnnouncementUpdateCommand) {
        return dataCompanyIprGeograApproveAnnouncementUpdateCommandExecutor.execute(dataCompanyIprGeograApproveAnnouncementUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprGeograApproveAnnouncementExWarehouseVO> warehouse(DataCompanyIprGeograApproveAnnouncementWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprGeograApproveAnnouncementCreateCommandExecutor(DataCompanyIprGeograApproveAnnouncementCreateCommandExecutor dataCompanyIprGeograApproveAnnouncementCreateCommandExecutor) {
        this.dataCompanyIprGeograApproveAnnouncementCreateCommandExecutor = dataCompanyIprGeograApproveAnnouncementCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprGeograApproveAnnouncementDeleteCommandExecutor(DataCompanyIprGeograApproveAnnouncementDeleteCommandExecutor dataCompanyIprGeograApproveAnnouncementDeleteCommandExecutor) {
        this.dataCompanyIprGeograApproveAnnouncementDeleteCommandExecutor = dataCompanyIprGeograApproveAnnouncementDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprGeograApproveAnnouncementUpdateCommandExecutor(DataCompanyIprGeograApproveAnnouncementUpdateCommandExecutor dataCompanyIprGeograApproveAnnouncementUpdateCommandExecutor) {
        this.dataCompanyIprGeograApproveAnnouncementUpdateCommandExecutor = dataCompanyIprGeograApproveAnnouncementUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprGeograApproveAnnouncementCommandExecutor(DataCompanyIprGeograApproveAnnouncementCommandExecutor dataCompanyIprGeograApproveAnnouncementCommandExecutor) {
        this.dataCompanyIprGeograApproveAnnouncementCommandExecutor = dataCompanyIprGeograApproveAnnouncementCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprGeograApproveAnnouncementWarehouseCommandExecutor(DataCompanyIprGeograApproveAnnouncementWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}