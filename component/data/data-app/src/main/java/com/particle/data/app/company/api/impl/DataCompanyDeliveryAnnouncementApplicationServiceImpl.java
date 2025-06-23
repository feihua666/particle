package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyDeliveryAnnouncementCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyDeliveryAnnouncementDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyDeliveryAnnouncementUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyDeliveryAnnouncementCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyDeliveryAnnouncementApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyDeliveryAnnouncementWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyDeliveryAnnouncementWarehouseCommandExecutor;
/**
 * <p>
 * 企业送达公告 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:06
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyDeliveryAnnouncementApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyDeliveryAnnouncementApplicationService {

    private DataCompanyDeliveryAnnouncementCreateCommandExecutor dataCompanyDeliveryAnnouncementCreateCommandExecutor;

    private DataCompanyDeliveryAnnouncementDeleteCommandExecutor dataCompanyDeliveryAnnouncementDeleteCommandExecutor;

    private DataCompanyDeliveryAnnouncementUpdateCommandExecutor dataCompanyDeliveryAnnouncementUpdateCommandExecutor;

    private DataCompanyDeliveryAnnouncementCommandExecutor dataCompanyDeliveryAnnouncementCommandExecutor;

    private DataCompanyDeliveryAnnouncementWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyDeliveryAnnouncementVO> create(DataCompanyDeliveryAnnouncementCreateCommand dataCompanyDeliveryAnnouncementCreateCommand) {
        return dataCompanyDeliveryAnnouncementCreateCommandExecutor.execute(dataCompanyDeliveryAnnouncementCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyDeliveryAnnouncementVO> delete(IdCommand deleteCommand) {
        return dataCompanyDeliveryAnnouncementDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyDeliveryAnnouncementVO> update(DataCompanyDeliveryAnnouncementUpdateCommand dataCompanyDeliveryAnnouncementUpdateCommand) {
        return dataCompanyDeliveryAnnouncementUpdateCommandExecutor.execute(dataCompanyDeliveryAnnouncementUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyDeliveryAnnouncementExWarehouseVO> warehouse(DataCompanyDeliveryAnnouncementWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyDeliveryAnnouncementCreateCommandExecutor(DataCompanyDeliveryAnnouncementCreateCommandExecutor dataCompanyDeliveryAnnouncementCreateCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementCreateCommandExecutor = dataCompanyDeliveryAnnouncementCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyDeliveryAnnouncementDeleteCommandExecutor(DataCompanyDeliveryAnnouncementDeleteCommandExecutor dataCompanyDeliveryAnnouncementDeleteCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementDeleteCommandExecutor = dataCompanyDeliveryAnnouncementDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyDeliveryAnnouncementUpdateCommandExecutor(DataCompanyDeliveryAnnouncementUpdateCommandExecutor dataCompanyDeliveryAnnouncementUpdateCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementUpdateCommandExecutor = dataCompanyDeliveryAnnouncementUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyDeliveryAnnouncementCommandExecutor(DataCompanyDeliveryAnnouncementCommandExecutor dataCompanyDeliveryAnnouncementCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementCommandExecutor = dataCompanyDeliveryAnnouncementCommandExecutor;
    }
    @Autowired
    public void setDataCompanyDeliveryAnnouncementWarehouseCommandExecutor(DataCompanyDeliveryAnnouncementWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}