package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyEquityPledgeCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyEquityPledgeDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyEquityPledgeUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyEquityPledgeCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyEquityPledgeUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyEquityPledgeApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyEquityPledgeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyEquityPledgeVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyEquityPledgeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyEquityPledgeExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyEquityPledgeWarehouseCommandExecutor;
/**
 * <p>
 * 企业股权出质 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:10
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyEquityPledgeApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyEquityPledgeApplicationService {

    private DataCompanyEquityPledgeCreateCommandExecutor dataCompanyEquityPledgeCreateCommandExecutor;

    private DataCompanyEquityPledgeDeleteCommandExecutor dataCompanyEquityPledgeDeleteCommandExecutor;

    private DataCompanyEquityPledgeUpdateCommandExecutor dataCompanyEquityPledgeUpdateCommandExecutor;

    private DataCompanyEquityPledgeCommandExecutor dataCompanyEquityPledgeCommandExecutor;

    private DataCompanyEquityPledgeWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyEquityPledgeVO> create(DataCompanyEquityPledgeCreateCommand dataCompanyEquityPledgeCreateCommand) {
        return dataCompanyEquityPledgeCreateCommandExecutor.execute(dataCompanyEquityPledgeCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyEquityPledgeVO> delete(IdCommand deleteCommand) {
        return dataCompanyEquityPledgeDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyEquityPledgeVO> update(DataCompanyEquityPledgeUpdateCommand dataCompanyEquityPledgeUpdateCommand) {
        return dataCompanyEquityPledgeUpdateCommandExecutor.execute(dataCompanyEquityPledgeUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyEquityPledgeExWarehouseVO> warehouse(DataCompanyEquityPledgeWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyEquityPledgeCreateCommandExecutor(DataCompanyEquityPledgeCreateCommandExecutor dataCompanyEquityPledgeCreateCommandExecutor) {
        this.dataCompanyEquityPledgeCreateCommandExecutor = dataCompanyEquityPledgeCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyEquityPledgeDeleteCommandExecutor(DataCompanyEquityPledgeDeleteCommandExecutor dataCompanyEquityPledgeDeleteCommandExecutor) {
        this.dataCompanyEquityPledgeDeleteCommandExecutor = dataCompanyEquityPledgeDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyEquityPledgeUpdateCommandExecutor(DataCompanyEquityPledgeUpdateCommandExecutor dataCompanyEquityPledgeUpdateCommandExecutor) {
        this.dataCompanyEquityPledgeUpdateCommandExecutor = dataCompanyEquityPledgeUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyEquityPledgeCommandExecutor(DataCompanyEquityPledgeCommandExecutor dataCompanyEquityPledgeCommandExecutor) {
        this.dataCompanyEquityPledgeCommandExecutor = dataCompanyEquityPledgeCommandExecutor;
    }
    @Autowired
    public void setDataCompanyEquityPledgeWarehouseCommandExecutor(DataCompanyEquityPledgeWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}