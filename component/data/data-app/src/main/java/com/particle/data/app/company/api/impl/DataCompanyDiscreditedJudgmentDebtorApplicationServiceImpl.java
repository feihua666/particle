package com.particle.data.app.company.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.company.executor.DataCompanyDiscreditedJudgmentDebtorCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyDiscreditedJudgmentDebtorCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyDiscreditedJudgmentDebtorDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyDiscreditedJudgmentDebtorUpdateCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor;
import com.particle.data.client.company.api.IDataCompanyDiscreditedJudgmentDebtorApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyDiscreditedJudgmentDebtorCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyDiscreditedJudgmentDebtorUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyDiscreditedJudgmentDebtorWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyDiscreditedJudgmentDebtorVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDiscreditedJudgmentDebtorExWarehouseVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 企业失信被执行人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:58
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyDiscreditedJudgmentDebtorApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyDiscreditedJudgmentDebtorApplicationService {

    private DataCompanyDiscreditedJudgmentDebtorCreateCommandExecutor dataCompanyDiscreditedJudgmentDebtorCreateCommandExecutor;

    private DataCompanyDiscreditedJudgmentDebtorDeleteCommandExecutor dataCompanyDiscreditedJudgmentDebtorDeleteCommandExecutor;

    private DataCompanyDiscreditedJudgmentDebtorUpdateCommandExecutor dataCompanyDiscreditedJudgmentDebtorUpdateCommandExecutor;

    private DataCompanyDiscreditedJudgmentDebtorCommandExecutor dataCompanyDiscreditedJudgmentDebtorCommandExecutor;

    private DataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor dataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor;
    private DataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyDiscreditedJudgmentDebtorVO> create(DataCompanyDiscreditedJudgmentDebtorCreateCommand dataCompanyDiscreditedJudgmentDebtorCreateCommand) {
        return dataCompanyDiscreditedJudgmentDebtorCreateCommandExecutor.execute(dataCompanyDiscreditedJudgmentDebtorCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyDiscreditedJudgmentDebtorVO> delete(IdCommand deleteCommand) {
        return dataCompanyDiscreditedJudgmentDebtorDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyDiscreditedJudgmentDebtorVO> update(DataCompanyDiscreditedJudgmentDebtorUpdateCommand dataCompanyDiscreditedJudgmentDebtorUpdateCommand) {
        return dataCompanyDiscreditedJudgmentDebtorUpdateCommandExecutor.execute(dataCompanyDiscreditedJudgmentDebtorUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO> warehouse(DataCompanyDiscreditedJudgmentDebtorWarehouseCommand dataCompanyDiscreditedJudgmentDebtorWarehouseCommand) {
        return dataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor.warehouse(dataCompanyDiscreditedJudgmentDebtorWarehouseCommand);
    }

    @Autowired
    public void setDataCompanyDiscreditedJudgmentDebtorCreateCommandExecutor(DataCompanyDiscreditedJudgmentDebtorCreateCommandExecutor dataCompanyDiscreditedJudgmentDebtorCreateCommandExecutor) {
        this.dataCompanyDiscreditedJudgmentDebtorCreateCommandExecutor = dataCompanyDiscreditedJudgmentDebtorCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyDiscreditedJudgmentDebtorDeleteCommandExecutor(DataCompanyDiscreditedJudgmentDebtorDeleteCommandExecutor dataCompanyDiscreditedJudgmentDebtorDeleteCommandExecutor) {
        this.dataCompanyDiscreditedJudgmentDebtorDeleteCommandExecutor = dataCompanyDiscreditedJudgmentDebtorDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyDiscreditedJudgmentDebtorUpdateCommandExecutor(DataCompanyDiscreditedJudgmentDebtorUpdateCommandExecutor dataCompanyDiscreditedJudgmentDebtorUpdateCommandExecutor) {
        this.dataCompanyDiscreditedJudgmentDebtorUpdateCommandExecutor = dataCompanyDiscreditedJudgmentDebtorUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyDiscreditedJudgmentDebtorCommandExecutor(DataCompanyDiscreditedJudgmentDebtorCommandExecutor dataCompanyDiscreditedJudgmentDebtorCommandExecutor) {
        this.dataCompanyDiscreditedJudgmentDebtorCommandExecutor = dataCompanyDiscreditedJudgmentDebtorCommandExecutor;
    }
    @Autowired
    public void setDataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor(DataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor dataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor) {
        this.dataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor = dataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor;
    }

}
