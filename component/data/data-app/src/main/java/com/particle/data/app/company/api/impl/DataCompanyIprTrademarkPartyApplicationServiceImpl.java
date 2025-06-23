package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprTrademarkPartyCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkPartyDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkPartyUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkPartyCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkPartyUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprTrademarkPartyApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkPartyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkPartyVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkPartyExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprTrademarkPartyWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权商标当事人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:34
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprTrademarkPartyApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprTrademarkPartyApplicationService {

    private DataCompanyIprTrademarkPartyCreateCommandExecutor dataCompanyIprTrademarkPartyCreateCommandExecutor;

    private DataCompanyIprTrademarkPartyDeleteCommandExecutor dataCompanyIprTrademarkPartyDeleteCommandExecutor;

    private DataCompanyIprTrademarkPartyUpdateCommandExecutor dataCompanyIprTrademarkPartyUpdateCommandExecutor;

    private DataCompanyIprTrademarkPartyCommandExecutor dataCompanyIprTrademarkPartyCommandExecutor;

    private DataCompanyIprTrademarkPartyWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprTrademarkPartyVO> create(DataCompanyIprTrademarkPartyCreateCommand dataCompanyIprTrademarkPartyCreateCommand) {
        return dataCompanyIprTrademarkPartyCreateCommandExecutor.execute(dataCompanyIprTrademarkPartyCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprTrademarkPartyVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprTrademarkPartyDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprTrademarkPartyVO> update(DataCompanyIprTrademarkPartyUpdateCommand dataCompanyIprTrademarkPartyUpdateCommand) {
        return dataCompanyIprTrademarkPartyUpdateCommandExecutor.execute(dataCompanyIprTrademarkPartyUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprTrademarkPartyExWarehouseVO> warehouse(DataCompanyIprTrademarkPartyWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprTrademarkPartyCreateCommandExecutor(DataCompanyIprTrademarkPartyCreateCommandExecutor dataCompanyIprTrademarkPartyCreateCommandExecutor) {
        this.dataCompanyIprTrademarkPartyCreateCommandExecutor = dataCompanyIprTrademarkPartyCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprTrademarkPartyDeleteCommandExecutor(DataCompanyIprTrademarkPartyDeleteCommandExecutor dataCompanyIprTrademarkPartyDeleteCommandExecutor) {
        this.dataCompanyIprTrademarkPartyDeleteCommandExecutor = dataCompanyIprTrademarkPartyDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkPartyUpdateCommandExecutor(DataCompanyIprTrademarkPartyUpdateCommandExecutor dataCompanyIprTrademarkPartyUpdateCommandExecutor) {
        this.dataCompanyIprTrademarkPartyUpdateCommandExecutor = dataCompanyIprTrademarkPartyUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkPartyCommandExecutor(DataCompanyIprTrademarkPartyCommandExecutor dataCompanyIprTrademarkPartyCommandExecutor) {
        this.dataCompanyIprTrademarkPartyCommandExecutor = dataCompanyIprTrademarkPartyCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkPartyWarehouseCommandExecutor(DataCompanyIprTrademarkPartyWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}