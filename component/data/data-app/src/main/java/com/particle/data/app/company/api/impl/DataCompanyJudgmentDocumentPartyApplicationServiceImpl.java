package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyJudgmentDocumentPartyCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyJudgmentDocumentPartyDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyJudgmentDocumentPartyUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyJudgmentDocumentPartyCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentPartyUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyJudgmentDocumentPartyApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentPartyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentPartyVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDocumentPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentPartyExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyJudgmentDocumentPartyWarehouseCommandExecutor;
/**
 * <p>
 * 企业裁判文书当事人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:05
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyJudgmentDocumentPartyApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyJudgmentDocumentPartyApplicationService {

    private DataCompanyJudgmentDocumentPartyCreateCommandExecutor dataCompanyJudgmentDocumentPartyCreateCommandExecutor;

    private DataCompanyJudgmentDocumentPartyDeleteCommandExecutor dataCompanyJudgmentDocumentPartyDeleteCommandExecutor;

    private DataCompanyJudgmentDocumentPartyUpdateCommandExecutor dataCompanyJudgmentDocumentPartyUpdateCommandExecutor;

    private DataCompanyJudgmentDocumentPartyCommandExecutor dataCompanyJudgmentDocumentPartyCommandExecutor;

    private DataCompanyJudgmentDocumentPartyWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyJudgmentDocumentPartyVO> create(DataCompanyJudgmentDocumentPartyCreateCommand dataCompanyJudgmentDocumentPartyCreateCommand) {
        return dataCompanyJudgmentDocumentPartyCreateCommandExecutor.execute(dataCompanyJudgmentDocumentPartyCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyJudgmentDocumentPartyVO> delete(IdCommand deleteCommand) {
        return dataCompanyJudgmentDocumentPartyDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyJudgmentDocumentPartyVO> update(DataCompanyJudgmentDocumentPartyUpdateCommand dataCompanyJudgmentDocumentPartyUpdateCommand) {
        return dataCompanyJudgmentDocumentPartyUpdateCommandExecutor.execute(dataCompanyJudgmentDocumentPartyUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyJudgmentDocumentPartyExWarehouseVO> warehouse(DataCompanyJudgmentDocumentPartyWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyJudgmentDocumentPartyCreateCommandExecutor(DataCompanyJudgmentDocumentPartyCreateCommandExecutor dataCompanyJudgmentDocumentPartyCreateCommandExecutor) {
        this.dataCompanyJudgmentDocumentPartyCreateCommandExecutor = dataCompanyJudgmentDocumentPartyCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyJudgmentDocumentPartyDeleteCommandExecutor(DataCompanyJudgmentDocumentPartyDeleteCommandExecutor dataCompanyJudgmentDocumentPartyDeleteCommandExecutor) {
        this.dataCompanyJudgmentDocumentPartyDeleteCommandExecutor = dataCompanyJudgmentDocumentPartyDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyJudgmentDocumentPartyUpdateCommandExecutor(DataCompanyJudgmentDocumentPartyUpdateCommandExecutor dataCompanyJudgmentDocumentPartyUpdateCommandExecutor) {
        this.dataCompanyJudgmentDocumentPartyUpdateCommandExecutor = dataCompanyJudgmentDocumentPartyUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyJudgmentDocumentPartyCommandExecutor(DataCompanyJudgmentDocumentPartyCommandExecutor dataCompanyJudgmentDocumentPartyCommandExecutor) {
        this.dataCompanyJudgmentDocumentPartyCommandExecutor = dataCompanyJudgmentDocumentPartyCommandExecutor;
    }
    @Autowired
    public void setDataCompanyJudgmentDocumentPartyWarehouseCommandExecutor(DataCompanyJudgmentDocumentPartyWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}