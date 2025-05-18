package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyJudgmentDebtorCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyJudgmentDebtorDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyJudgmentDebtorUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyJudgmentDebtorCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDebtorUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyJudgmentDebtorApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDebtorCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDebtorVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDebtorWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDebtorExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyJudgmentDebtorWarehouseCommandExecutor;
/**
 * <p>
 * 企业被执行人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:07
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyJudgmentDebtorApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyJudgmentDebtorApplicationService {

    private DataCompanyJudgmentDebtorCreateCommandExecutor dataCompanyJudgmentDebtorCreateCommandExecutor;

    private DataCompanyJudgmentDebtorDeleteCommandExecutor dataCompanyJudgmentDebtorDeleteCommandExecutor;

    private DataCompanyJudgmentDebtorUpdateCommandExecutor dataCompanyJudgmentDebtorUpdateCommandExecutor;

    private DataCompanyJudgmentDebtorCommandExecutor dataCompanyJudgmentDebtorCommandExecutor;

    private DataCompanyJudgmentDebtorWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyJudgmentDebtorVO> create(DataCompanyJudgmentDebtorCreateCommand dataCompanyJudgmentDebtorCreateCommand) {
        return dataCompanyJudgmentDebtorCreateCommandExecutor.execute(dataCompanyJudgmentDebtorCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyJudgmentDebtorVO> delete(IdCommand deleteCommand) {
        return dataCompanyJudgmentDebtorDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyJudgmentDebtorVO> update(DataCompanyJudgmentDebtorUpdateCommand dataCompanyJudgmentDebtorUpdateCommand) {
        return dataCompanyJudgmentDebtorUpdateCommandExecutor.execute(dataCompanyJudgmentDebtorUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyJudgmentDebtorExWarehouseVO> warehouse(DataCompanyJudgmentDebtorWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyJudgmentDebtorCreateCommandExecutor(DataCompanyJudgmentDebtorCreateCommandExecutor dataCompanyJudgmentDebtorCreateCommandExecutor) {
        this.dataCompanyJudgmentDebtorCreateCommandExecutor = dataCompanyJudgmentDebtorCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyJudgmentDebtorDeleteCommandExecutor(DataCompanyJudgmentDebtorDeleteCommandExecutor dataCompanyJudgmentDebtorDeleteCommandExecutor) {
        this.dataCompanyJudgmentDebtorDeleteCommandExecutor = dataCompanyJudgmentDebtorDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyJudgmentDebtorUpdateCommandExecutor(DataCompanyJudgmentDebtorUpdateCommandExecutor dataCompanyJudgmentDebtorUpdateCommandExecutor) {
        this.dataCompanyJudgmentDebtorUpdateCommandExecutor = dataCompanyJudgmentDebtorUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyJudgmentDebtorCommandExecutor(DataCompanyJudgmentDebtorCommandExecutor dataCompanyJudgmentDebtorCommandExecutor) {
        this.dataCompanyJudgmentDebtorCommandExecutor = dataCompanyJudgmentDebtorCommandExecutor;
    }
    @Autowired
    public void setDataCompanyJudgmentDebtorWarehouseCommandExecutor(DataCompanyJudgmentDebtorWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}