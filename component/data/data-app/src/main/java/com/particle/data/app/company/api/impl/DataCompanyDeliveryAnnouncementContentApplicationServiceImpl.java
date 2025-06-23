package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyDeliveryAnnouncementContentCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyDeliveryAnnouncementContentDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyDeliveryAnnouncementContentUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyDeliveryAnnouncementContentCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementContentUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyDeliveryAnnouncementContentApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementContentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementContentVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyDeliveryAnnouncementContentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementContentExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyDeliveryAnnouncementContentWarehouseCommandExecutor;
/**
 * <p>
 * 企业送达公告内容 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:18
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyDeliveryAnnouncementContentApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyDeliveryAnnouncementContentApplicationService {

    private DataCompanyDeliveryAnnouncementContentCreateCommandExecutor dataCompanyDeliveryAnnouncementContentCreateCommandExecutor;

    private DataCompanyDeliveryAnnouncementContentDeleteCommandExecutor dataCompanyDeliveryAnnouncementContentDeleteCommandExecutor;

    private DataCompanyDeliveryAnnouncementContentUpdateCommandExecutor dataCompanyDeliveryAnnouncementContentUpdateCommandExecutor;

    private DataCompanyDeliveryAnnouncementContentCommandExecutor dataCompanyDeliveryAnnouncementContentCommandExecutor;

    private DataCompanyDeliveryAnnouncementContentWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyDeliveryAnnouncementContentVO> create(DataCompanyDeliveryAnnouncementContentCreateCommand dataCompanyDeliveryAnnouncementContentCreateCommand) {
        return dataCompanyDeliveryAnnouncementContentCreateCommandExecutor.execute(dataCompanyDeliveryAnnouncementContentCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyDeliveryAnnouncementContentVO> delete(IdCommand deleteCommand) {
        return dataCompanyDeliveryAnnouncementContentDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyDeliveryAnnouncementContentVO> update(DataCompanyDeliveryAnnouncementContentUpdateCommand dataCompanyDeliveryAnnouncementContentUpdateCommand) {
        return dataCompanyDeliveryAnnouncementContentUpdateCommandExecutor.execute(dataCompanyDeliveryAnnouncementContentUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyDeliveryAnnouncementContentExWarehouseVO> warehouse(DataCompanyDeliveryAnnouncementContentWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyDeliveryAnnouncementContentCreateCommandExecutor(DataCompanyDeliveryAnnouncementContentCreateCommandExecutor dataCompanyDeliveryAnnouncementContentCreateCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementContentCreateCommandExecutor = dataCompanyDeliveryAnnouncementContentCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyDeliveryAnnouncementContentDeleteCommandExecutor(DataCompanyDeliveryAnnouncementContentDeleteCommandExecutor dataCompanyDeliveryAnnouncementContentDeleteCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementContentDeleteCommandExecutor = dataCompanyDeliveryAnnouncementContentDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyDeliveryAnnouncementContentUpdateCommandExecutor(DataCompanyDeliveryAnnouncementContentUpdateCommandExecutor dataCompanyDeliveryAnnouncementContentUpdateCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementContentUpdateCommandExecutor = dataCompanyDeliveryAnnouncementContentUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyDeliveryAnnouncementContentCommandExecutor(DataCompanyDeliveryAnnouncementContentCommandExecutor dataCompanyDeliveryAnnouncementContentCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementContentCommandExecutor = dataCompanyDeliveryAnnouncementContentCommandExecutor;
    }
    @Autowired
    public void setDataCompanyDeliveryAnnouncementContentWarehouseCommandExecutor(DataCompanyDeliveryAnnouncementContentWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}