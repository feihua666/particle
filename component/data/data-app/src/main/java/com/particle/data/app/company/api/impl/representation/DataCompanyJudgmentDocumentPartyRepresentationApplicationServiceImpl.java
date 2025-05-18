package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyJudgmentDocumentPartyQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyJudgmentDocumentPartyRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentPartyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyJudgmentDocumentPartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentPartyExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor;
/**
 * <p>
 * 企业裁判文书当事人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:05
 */
@Service
@CatchAndLog
public class DataCompanyJudgmentDocumentPartyRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyJudgmentDocumentPartyRepresentationApplicationService {

    private DataCompanyJudgmentDocumentPartyQueryCommandExecutor dataCompanyJudgmentDocumentPartyQueryCommandExecutor;
    private DataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor dataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyJudgmentDocumentPartyVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyJudgmentDocumentPartyQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyJudgmentDocumentPartyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyJudgmentDocumentPartyQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyJudgmentDocumentPartyVO> pageQuery(DataCompanyJudgmentDocumentPartyPageQueryCommand dataCompanyJudgmentDocumentPartyPageQueryCommand) {
        return dataCompanyJudgmentDocumentPartyQueryCommandExecutor.execute(dataCompanyJudgmentDocumentPartyPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyJudgmentDocumentPartyVO> queryList(DataCompanyJudgmentDocumentPartyQueryListCommand dataCompanyJudgmentDocumentPartyQueryListCommand) {
        return dataCompanyJudgmentDocumentPartyQueryCommandExecutor.execute(dataCompanyJudgmentDocumentPartyQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyJudgmentDocumentPartyExWarehouseVO> exWarehouse(DataCompanyJudgmentDocumentPartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyJudgmentDocumentPartyQueryCommandExecutor(DataCompanyJudgmentDocumentPartyQueryCommandExecutor dataCompanyJudgmentDocumentPartyQueryCommandExecutor) {
        this.dataCompanyJudgmentDocumentPartyQueryCommandExecutor = dataCompanyJudgmentDocumentPartyQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor(DataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor dataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor) {
        this.dataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor = dataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor;
    }
}
