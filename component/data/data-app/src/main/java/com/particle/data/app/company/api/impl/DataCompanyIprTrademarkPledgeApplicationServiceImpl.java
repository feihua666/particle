package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprTrademarkPledgeCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkPledgeDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkPledgeUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkPledgeCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkPledgeUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprTrademarkPledgeApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkPledgeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkPledgeVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkPledgeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkPledgeExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprTrademarkPledgeWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权商标质押信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:53
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprTrademarkPledgeApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprTrademarkPledgeApplicationService {

    private DataCompanyIprTrademarkPledgeCreateCommandExecutor dataCompanyIprTrademarkPledgeCreateCommandExecutor;

    private DataCompanyIprTrademarkPledgeDeleteCommandExecutor dataCompanyIprTrademarkPledgeDeleteCommandExecutor;

    private DataCompanyIprTrademarkPledgeUpdateCommandExecutor dataCompanyIprTrademarkPledgeUpdateCommandExecutor;

    private DataCompanyIprTrademarkPledgeCommandExecutor dataCompanyIprTrademarkPledgeCommandExecutor;

    private DataCompanyIprTrademarkPledgeWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprTrademarkPledgeVO> create(DataCompanyIprTrademarkPledgeCreateCommand dataCompanyIprTrademarkPledgeCreateCommand) {
        return dataCompanyIprTrademarkPledgeCreateCommandExecutor.execute(dataCompanyIprTrademarkPledgeCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprTrademarkPledgeVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprTrademarkPledgeDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprTrademarkPledgeVO> update(DataCompanyIprTrademarkPledgeUpdateCommand dataCompanyIprTrademarkPledgeUpdateCommand) {
        return dataCompanyIprTrademarkPledgeUpdateCommandExecutor.execute(dataCompanyIprTrademarkPledgeUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprTrademarkPledgeExWarehouseVO> warehouse(DataCompanyIprTrademarkPledgeWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprTrademarkPledgeCreateCommandExecutor(DataCompanyIprTrademarkPledgeCreateCommandExecutor dataCompanyIprTrademarkPledgeCreateCommandExecutor) {
        this.dataCompanyIprTrademarkPledgeCreateCommandExecutor = dataCompanyIprTrademarkPledgeCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprTrademarkPledgeDeleteCommandExecutor(DataCompanyIprTrademarkPledgeDeleteCommandExecutor dataCompanyIprTrademarkPledgeDeleteCommandExecutor) {
        this.dataCompanyIprTrademarkPledgeDeleteCommandExecutor = dataCompanyIprTrademarkPledgeDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkPledgeUpdateCommandExecutor(DataCompanyIprTrademarkPledgeUpdateCommandExecutor dataCompanyIprTrademarkPledgeUpdateCommandExecutor) {
        this.dataCompanyIprTrademarkPledgeUpdateCommandExecutor = dataCompanyIprTrademarkPledgeUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkPledgeCommandExecutor(DataCompanyIprTrademarkPledgeCommandExecutor dataCompanyIprTrademarkPledgeCommandExecutor) {
        this.dataCompanyIprTrademarkPledgeCommandExecutor = dataCompanyIprTrademarkPledgeCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkPledgeWarehouseCommandExecutor(DataCompanyIprTrademarkPledgeWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}