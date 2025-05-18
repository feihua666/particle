package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprPatentPledgeCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentPledgeDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentPledgeUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentPledgeCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPledgeUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprPatentPledgeApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPledgeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPledgeVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentPledgeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPledgeExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprPatentPledgeWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利质押信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:40
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprPatentPledgeApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentPledgeApplicationService {

    private DataCompanyIprPatentPledgeCreateCommandExecutor dataCompanyIprPatentPledgeCreateCommandExecutor;

    private DataCompanyIprPatentPledgeDeleteCommandExecutor dataCompanyIprPatentPledgeDeleteCommandExecutor;

    private DataCompanyIprPatentPledgeUpdateCommandExecutor dataCompanyIprPatentPledgeUpdateCommandExecutor;

    private DataCompanyIprPatentPledgeCommandExecutor dataCompanyIprPatentPledgeCommandExecutor;

    private DataCompanyIprPatentPledgeWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentPledgeVO> create(DataCompanyIprPatentPledgeCreateCommand dataCompanyIprPatentPledgeCreateCommand) {
        return dataCompanyIprPatentPledgeCreateCommandExecutor.execute(dataCompanyIprPatentPledgeCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentPledgeVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprPatentPledgeDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentPledgeVO> update(DataCompanyIprPatentPledgeUpdateCommand dataCompanyIprPatentPledgeUpdateCommand) {
        return dataCompanyIprPatentPledgeUpdateCommandExecutor.execute(dataCompanyIprPatentPledgeUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprPatentPledgeExWarehouseVO> warehouse(DataCompanyIprPatentPledgeWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprPatentPledgeCreateCommandExecutor(DataCompanyIprPatentPledgeCreateCommandExecutor dataCompanyIprPatentPledgeCreateCommandExecutor) {
        this.dataCompanyIprPatentPledgeCreateCommandExecutor = dataCompanyIprPatentPledgeCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprPatentPledgeDeleteCommandExecutor(DataCompanyIprPatentPledgeDeleteCommandExecutor dataCompanyIprPatentPledgeDeleteCommandExecutor) {
        this.dataCompanyIprPatentPledgeDeleteCommandExecutor = dataCompanyIprPatentPledgeDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentPledgeUpdateCommandExecutor(DataCompanyIprPatentPledgeUpdateCommandExecutor dataCompanyIprPatentPledgeUpdateCommandExecutor) {
        this.dataCompanyIprPatentPledgeUpdateCommandExecutor = dataCompanyIprPatentPledgeUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentPledgeCommandExecutor(DataCompanyIprPatentPledgeCommandExecutor dataCompanyIprPatentPledgeCommandExecutor) {
        this.dataCompanyIprPatentPledgeCommandExecutor = dataCompanyIprPatentPledgeCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentPledgeWarehouseCommandExecutor(DataCompanyIprPatentPledgeWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}