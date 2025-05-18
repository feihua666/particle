package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyJudgmentDocumentCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyJudgmentDocumentDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyJudgmentDocumentUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyJudgmentDocumentCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyJudgmentDocumentApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDocumentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyJudgmentDocumentWarehouseCommandExecutor;
/**
 * <p>
 * 企业裁判文书 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:21
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyJudgmentDocumentApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyJudgmentDocumentApplicationService {

    private DataCompanyJudgmentDocumentCreateCommandExecutor dataCompanyJudgmentDocumentCreateCommandExecutor;

    private DataCompanyJudgmentDocumentDeleteCommandExecutor dataCompanyJudgmentDocumentDeleteCommandExecutor;

    private DataCompanyJudgmentDocumentUpdateCommandExecutor dataCompanyJudgmentDocumentUpdateCommandExecutor;

    private DataCompanyJudgmentDocumentCommandExecutor dataCompanyJudgmentDocumentCommandExecutor;

    private DataCompanyJudgmentDocumentWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyJudgmentDocumentVO> create(DataCompanyJudgmentDocumentCreateCommand dataCompanyJudgmentDocumentCreateCommand) {
        return dataCompanyJudgmentDocumentCreateCommandExecutor.execute(dataCompanyJudgmentDocumentCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyJudgmentDocumentVO> delete(IdCommand deleteCommand) {
        return dataCompanyJudgmentDocumentDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyJudgmentDocumentVO> update(DataCompanyJudgmentDocumentUpdateCommand dataCompanyJudgmentDocumentUpdateCommand) {
        return dataCompanyJudgmentDocumentUpdateCommandExecutor.execute(dataCompanyJudgmentDocumentUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyJudgmentDocumentExWarehouseVO> warehouse(DataCompanyJudgmentDocumentWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyJudgmentDocumentCreateCommandExecutor(DataCompanyJudgmentDocumentCreateCommandExecutor dataCompanyJudgmentDocumentCreateCommandExecutor) {
        this.dataCompanyJudgmentDocumentCreateCommandExecutor = dataCompanyJudgmentDocumentCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyJudgmentDocumentDeleteCommandExecutor(DataCompanyJudgmentDocumentDeleteCommandExecutor dataCompanyJudgmentDocumentDeleteCommandExecutor) {
        this.dataCompanyJudgmentDocumentDeleteCommandExecutor = dataCompanyJudgmentDocumentDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyJudgmentDocumentUpdateCommandExecutor(DataCompanyJudgmentDocumentUpdateCommandExecutor dataCompanyJudgmentDocumentUpdateCommandExecutor) {
        this.dataCompanyJudgmentDocumentUpdateCommandExecutor = dataCompanyJudgmentDocumentUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyJudgmentDocumentCommandExecutor(DataCompanyJudgmentDocumentCommandExecutor dataCompanyJudgmentDocumentCommandExecutor) {
        this.dataCompanyJudgmentDocumentCommandExecutor = dataCompanyJudgmentDocumentCommandExecutor;
    }
    @Autowired
    public void setDataCompanyJudgmentDocumentWarehouseCommandExecutor(DataCompanyJudgmentDocumentWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}