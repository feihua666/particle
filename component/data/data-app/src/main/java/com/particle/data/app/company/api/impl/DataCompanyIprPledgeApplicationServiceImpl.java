package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprPledgeCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPledgeDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPledgeUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPledgeCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPledgeUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprPledgeApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPledgeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPledgeVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPledgeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPledgeExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprPledgeWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权出质 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:21
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprPledgeApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPledgeApplicationService {

    private DataCompanyIprPledgeCreateCommandExecutor dataCompanyIprPledgeCreateCommandExecutor;

    private DataCompanyIprPledgeDeleteCommandExecutor dataCompanyIprPledgeDeleteCommandExecutor;

    private DataCompanyIprPledgeUpdateCommandExecutor dataCompanyIprPledgeUpdateCommandExecutor;

    private DataCompanyIprPledgeCommandExecutor dataCompanyIprPledgeCommandExecutor;

    private DataCompanyIprPledgeWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPledgeVO> create(DataCompanyIprPledgeCreateCommand dataCompanyIprPledgeCreateCommand) {
        return dataCompanyIprPledgeCreateCommandExecutor.execute(dataCompanyIprPledgeCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPledgeVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprPledgeDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPledgeVO> update(DataCompanyIprPledgeUpdateCommand dataCompanyIprPledgeUpdateCommand) {
        return dataCompanyIprPledgeUpdateCommandExecutor.execute(dataCompanyIprPledgeUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprPledgeExWarehouseVO> warehouse(DataCompanyIprPledgeWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprPledgeCreateCommandExecutor(DataCompanyIprPledgeCreateCommandExecutor dataCompanyIprPledgeCreateCommandExecutor) {
        this.dataCompanyIprPledgeCreateCommandExecutor = dataCompanyIprPledgeCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprPledgeDeleteCommandExecutor(DataCompanyIprPledgeDeleteCommandExecutor dataCompanyIprPledgeDeleteCommandExecutor) {
        this.dataCompanyIprPledgeDeleteCommandExecutor = dataCompanyIprPledgeDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPledgeUpdateCommandExecutor(DataCompanyIprPledgeUpdateCommandExecutor dataCompanyIprPledgeUpdateCommandExecutor) {
        this.dataCompanyIprPledgeUpdateCommandExecutor = dataCompanyIprPledgeUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPledgeCommandExecutor(DataCompanyIprPledgeCommandExecutor dataCompanyIprPledgeCommandExecutor) {
        this.dataCompanyIprPledgeCommandExecutor = dataCompanyIprPledgeCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPledgeWarehouseCommandExecutor(DataCompanyIprPledgeWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}