package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprPatentNoticeCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentNoticeDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentNoticeUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentNoticeCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentNoticeUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprPatentNoticeApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentNoticeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentNoticeVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentNoticeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentNoticeExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprPatentNoticeWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利通知书信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:13
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprPatentNoticeApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentNoticeApplicationService {

    private DataCompanyIprPatentNoticeCreateCommandExecutor dataCompanyIprPatentNoticeCreateCommandExecutor;

    private DataCompanyIprPatentNoticeDeleteCommandExecutor dataCompanyIprPatentNoticeDeleteCommandExecutor;

    private DataCompanyIprPatentNoticeUpdateCommandExecutor dataCompanyIprPatentNoticeUpdateCommandExecutor;

    private DataCompanyIprPatentNoticeCommandExecutor dataCompanyIprPatentNoticeCommandExecutor;

    private DataCompanyIprPatentNoticeWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentNoticeVO> create(DataCompanyIprPatentNoticeCreateCommand dataCompanyIprPatentNoticeCreateCommand) {
        return dataCompanyIprPatentNoticeCreateCommandExecutor.execute(dataCompanyIprPatentNoticeCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentNoticeVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprPatentNoticeDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentNoticeVO> update(DataCompanyIprPatentNoticeUpdateCommand dataCompanyIprPatentNoticeUpdateCommand) {
        return dataCompanyIprPatentNoticeUpdateCommandExecutor.execute(dataCompanyIprPatentNoticeUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprPatentNoticeExWarehouseVO> warehouse(DataCompanyIprPatentNoticeWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprPatentNoticeCreateCommandExecutor(DataCompanyIprPatentNoticeCreateCommandExecutor dataCompanyIprPatentNoticeCreateCommandExecutor) {
        this.dataCompanyIprPatentNoticeCreateCommandExecutor = dataCompanyIprPatentNoticeCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprPatentNoticeDeleteCommandExecutor(DataCompanyIprPatentNoticeDeleteCommandExecutor dataCompanyIprPatentNoticeDeleteCommandExecutor) {
        this.dataCompanyIprPatentNoticeDeleteCommandExecutor = dataCompanyIprPatentNoticeDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentNoticeUpdateCommandExecutor(DataCompanyIprPatentNoticeUpdateCommandExecutor dataCompanyIprPatentNoticeUpdateCommandExecutor) {
        this.dataCompanyIprPatentNoticeUpdateCommandExecutor = dataCompanyIprPatentNoticeUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentNoticeCommandExecutor(DataCompanyIprPatentNoticeCommandExecutor dataCompanyIprPatentNoticeCommandExecutor) {
        this.dataCompanyIprPatentNoticeCommandExecutor = dataCompanyIprPatentNoticeCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentNoticeWarehouseCommandExecutor(DataCompanyIprPatentNoticeWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}