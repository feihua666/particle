package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyJudgmentDocumentContentQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyJudgmentDocumentContentRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentContentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentContentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyJudgmentDocumentContentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentContentExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyJudgmentDocumentContentExWarehouseCommandExecutor;
/**
 * <p>
 * 企业裁判文书内容 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:53
 */
@Service
@CatchAndLog
public class DataCompanyJudgmentDocumentContentRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyJudgmentDocumentContentRepresentationApplicationService {

    private DataCompanyJudgmentDocumentContentQueryCommandExecutor dataCompanyJudgmentDocumentContentQueryCommandExecutor;
    private DataCompanyJudgmentDocumentContentExWarehouseCommandExecutor dataCompanyJudgmentDocumentContentExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyJudgmentDocumentContentVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyJudgmentDocumentContentQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyJudgmentDocumentContentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyJudgmentDocumentContentQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyJudgmentDocumentContentVO> pageQuery(DataCompanyJudgmentDocumentContentPageQueryCommand dataCompanyJudgmentDocumentContentPageQueryCommand) {
        return dataCompanyJudgmentDocumentContentQueryCommandExecutor.execute(dataCompanyJudgmentDocumentContentPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyJudgmentDocumentContentVO> queryList(DataCompanyJudgmentDocumentContentQueryListCommand dataCompanyJudgmentDocumentContentQueryListCommand) {
        return dataCompanyJudgmentDocumentContentQueryCommandExecutor.execute(dataCompanyJudgmentDocumentContentQueryListCommand);
    }


    @Override
    public SingleResponse<DataCompanyJudgmentDocumentContentExWarehouseVO> exWarehouse(DataCompanyJudgmentDocumentContentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyJudgmentDocumentContentExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyJudgmentDocumentContentQueryCommandExecutor(DataCompanyJudgmentDocumentContentQueryCommandExecutor dataCompanyJudgmentDocumentContentQueryCommandExecutor) {
        this.dataCompanyJudgmentDocumentContentQueryCommandExecutor = dataCompanyJudgmentDocumentContentQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyJudgmentDocumentContentExWarehouseCommandExecutor(DataCompanyJudgmentDocumentContentExWarehouseCommandExecutor dataCompanyJudgmentDocumentContentExWarehouseCommandExecutor) {
        this.dataCompanyJudgmentDocumentContentExWarehouseCommandExecutor = dataCompanyJudgmentDocumentContentExWarehouseCommandExecutor;
    }
}