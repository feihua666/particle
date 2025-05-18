package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyJudgmentDebtorQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyJudgmentDebtorRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDebtorPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDebtorQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDebtorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyJudgmentDebtorExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDebtorExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyJudgmentDebtorExWarehouseCommandExecutor;
/**
 * <p>
 * 企业被执行人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:07
 */
@Service
@CatchAndLog
public class DataCompanyJudgmentDebtorRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyJudgmentDebtorRepresentationApplicationService {

    private DataCompanyJudgmentDebtorQueryCommandExecutor dataCompanyJudgmentDebtorQueryCommandExecutor;
    private DataCompanyJudgmentDebtorExWarehouseCommandExecutor dataCompanyJudgmentDebtorExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyJudgmentDebtorVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyJudgmentDebtorQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyJudgmentDebtorVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyJudgmentDebtorQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyJudgmentDebtorVO> pageQuery(DataCompanyJudgmentDebtorPageQueryCommand dataCompanyJudgmentDebtorPageQueryCommand) {
        return dataCompanyJudgmentDebtorQueryCommandExecutor.execute(dataCompanyJudgmentDebtorPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyJudgmentDebtorVO> queryList(DataCompanyJudgmentDebtorQueryListCommand dataCompanyJudgmentDebtorQueryListCommand) {
        return dataCompanyJudgmentDebtorQueryCommandExecutor.execute(dataCompanyJudgmentDebtorQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyJudgmentDebtorExWarehouseVO> exWarehouse(DataCompanyJudgmentDebtorExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyJudgmentDebtorExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyJudgmentDebtorQueryCommandExecutor(DataCompanyJudgmentDebtorQueryCommandExecutor dataCompanyJudgmentDebtorQueryCommandExecutor) {
        this.dataCompanyJudgmentDebtorQueryCommandExecutor = dataCompanyJudgmentDebtorQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyJudgmentDebtorExWarehouseCommandExecutor(DataCompanyJudgmentDebtorExWarehouseCommandExecutor dataCompanyJudgmentDebtorExWarehouseCommandExecutor) {
        this.dataCompanyJudgmentDebtorExWarehouseCommandExecutor = dataCompanyJudgmentDebtorExWarehouseCommandExecutor;
    }
}
