package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprTrademarkTransferCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkTransferDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkTransferUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkTransferCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkTransferUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprTrademarkTransferApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkTransferCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkTransferVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkTransferWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkTransferExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprTrademarkTransferWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权商标转让信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:04
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprTrademarkTransferApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprTrademarkTransferApplicationService {

    private DataCompanyIprTrademarkTransferCreateCommandExecutor dataCompanyIprTrademarkTransferCreateCommandExecutor;

    private DataCompanyIprTrademarkTransferDeleteCommandExecutor dataCompanyIprTrademarkTransferDeleteCommandExecutor;

    private DataCompanyIprTrademarkTransferUpdateCommandExecutor dataCompanyIprTrademarkTransferUpdateCommandExecutor;

    private DataCompanyIprTrademarkTransferCommandExecutor dataCompanyIprTrademarkTransferCommandExecutor;

    private DataCompanyIprTrademarkTransferWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprTrademarkTransferVO> create(DataCompanyIprTrademarkTransferCreateCommand dataCompanyIprTrademarkTransferCreateCommand) {
        return dataCompanyIprTrademarkTransferCreateCommandExecutor.execute(dataCompanyIprTrademarkTransferCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprTrademarkTransferVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprTrademarkTransferDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprTrademarkTransferVO> update(DataCompanyIprTrademarkTransferUpdateCommand dataCompanyIprTrademarkTransferUpdateCommand) {
        return dataCompanyIprTrademarkTransferUpdateCommandExecutor.execute(dataCompanyIprTrademarkTransferUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprTrademarkTransferExWarehouseVO> warehouse(DataCompanyIprTrademarkTransferWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprTrademarkTransferCreateCommandExecutor(DataCompanyIprTrademarkTransferCreateCommandExecutor dataCompanyIprTrademarkTransferCreateCommandExecutor) {
        this.dataCompanyIprTrademarkTransferCreateCommandExecutor = dataCompanyIprTrademarkTransferCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprTrademarkTransferDeleteCommandExecutor(DataCompanyIprTrademarkTransferDeleteCommandExecutor dataCompanyIprTrademarkTransferDeleteCommandExecutor) {
        this.dataCompanyIprTrademarkTransferDeleteCommandExecutor = dataCompanyIprTrademarkTransferDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkTransferUpdateCommandExecutor(DataCompanyIprTrademarkTransferUpdateCommandExecutor dataCompanyIprTrademarkTransferUpdateCommandExecutor) {
        this.dataCompanyIprTrademarkTransferUpdateCommandExecutor = dataCompanyIprTrademarkTransferUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkTransferCommandExecutor(DataCompanyIprTrademarkTransferCommandExecutor dataCompanyIprTrademarkTransferCommandExecutor) {
        this.dataCompanyIprTrademarkTransferCommandExecutor = dataCompanyIprTrademarkTransferCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkTransferWarehouseCommandExecutor(DataCompanyIprTrademarkTransferWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}