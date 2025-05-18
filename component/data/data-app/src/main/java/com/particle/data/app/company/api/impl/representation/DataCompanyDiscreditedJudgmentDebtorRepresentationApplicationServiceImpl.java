package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.company.executor.representation.DataCompanyDiscreditedJudgmentDebtorQueryCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyDiscreditedJudgmentDebtorRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyDiscreditedJudgmentDebtorPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyDiscreditedJudgmentDebtorQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.DataCompanyDiscreditedJudgmentDebtorVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDiscreditedJudgmentDebtorExWarehouseVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 企业失信被执行人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:58
 */
@Service
@CatchAndLog
public class DataCompanyDiscreditedJudgmentDebtorRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyDiscreditedJudgmentDebtorRepresentationApplicationService {

    private DataCompanyDiscreditedJudgmentDebtorQueryCommandExecutor dataCompanyDiscreditedJudgmentDebtorQueryCommandExecutor;
    private DataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyDiscreditedJudgmentDebtorVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyDiscreditedJudgmentDebtorQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyDiscreditedJudgmentDebtorVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyDiscreditedJudgmentDebtorQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyDiscreditedJudgmentDebtorVO> pageQuery(DataCompanyDiscreditedJudgmentDebtorPageQueryCommand dataCompanyDiscreditedJudgmentDebtorPageQueryCommand) {
        return dataCompanyDiscreditedJudgmentDebtorQueryCommandExecutor.execute(dataCompanyDiscreditedJudgmentDebtorPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyDiscreditedJudgmentDebtorVO> queryList(DataCompanyDiscreditedJudgmentDebtorQueryListCommand dataCompanyDiscreditedJudgmentDebtorQueryListCommand) {
        return dataCompanyDiscreditedJudgmentDebtorQueryCommandExecutor.execute(dataCompanyDiscreditedJudgmentDebtorQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO> exWarehouse(DataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }


    @Autowired
    public void setDataCompanyDiscreditedJudgmentDebtorQueryCommandExecutor(DataCompanyDiscreditedJudgmentDebtorQueryCommandExecutor dataCompanyDiscreditedJudgmentDebtorQueryCommandExecutor) {
        this.dataCompanyDiscreditedJudgmentDebtorQueryCommandExecutor = dataCompanyDiscreditedJudgmentDebtorQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor(DataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor) {
        this.dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor = dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor;
    }
}
