package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyJudgmentDocumentQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyJudgmentDocumentRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyJudgmentDocumentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyJudgmentDocumentExWarehouseCommandExecutor;
/**
 * <p>
 * 企业裁判文书 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:21
 */
@Service
@CatchAndLog
public class DataCompanyJudgmentDocumentRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyJudgmentDocumentRepresentationApplicationService {

    private DataCompanyJudgmentDocumentQueryCommandExecutor dataCompanyJudgmentDocumentQueryCommandExecutor;
    private DataCompanyJudgmentDocumentExWarehouseCommandExecutor dataCompanyJudgmentDocumentExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyJudgmentDocumentVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyJudgmentDocumentQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyJudgmentDocumentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyJudgmentDocumentQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyJudgmentDocumentVO> pageQuery(DataCompanyJudgmentDocumentPageQueryCommand dataCompanyJudgmentDocumentPageQueryCommand) {
        return dataCompanyJudgmentDocumentQueryCommandExecutor.execute(dataCompanyJudgmentDocumentPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyJudgmentDocumentVO> queryList(DataCompanyJudgmentDocumentQueryListCommand dataCompanyJudgmentDocumentQueryListCommand) {
        return dataCompanyJudgmentDocumentQueryCommandExecutor.execute(dataCompanyJudgmentDocumentQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyJudgmentDocumentExWarehouseVO> exWarehouse(DataCompanyJudgmentDocumentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyJudgmentDocumentExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyJudgmentDocumentQueryCommandExecutor(DataCompanyJudgmentDocumentQueryCommandExecutor dataCompanyJudgmentDocumentQueryCommandExecutor) {
        this.dataCompanyJudgmentDocumentQueryCommandExecutor = dataCompanyJudgmentDocumentQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyJudgmentDocumentExWarehouseCommandExecutor(DataCompanyJudgmentDocumentExWarehouseCommandExecutor dataCompanyJudgmentDocumentExWarehouseCommandExecutor) {
        this.dataCompanyJudgmentDocumentExWarehouseCommandExecutor = dataCompanyJudgmentDocumentExWarehouseCommandExecutor;
    }
}
