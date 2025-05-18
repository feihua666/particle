package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyJudgmentDocumentContentCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyJudgmentDocumentContentDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyJudgmentDocumentContentUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyJudgmentDocumentContentCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentContentUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyJudgmentDocumentContentApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentContentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentContentVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDocumentContentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentContentExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyJudgmentDocumentContentWarehouseCommandExecutor;
/**
 * <p>
 * 企业裁判文书内容 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:53
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyJudgmentDocumentContentApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyJudgmentDocumentContentApplicationService {

    private DataCompanyJudgmentDocumentContentCreateCommandExecutor dataCompanyJudgmentDocumentContentCreateCommandExecutor;

    private DataCompanyJudgmentDocumentContentDeleteCommandExecutor dataCompanyJudgmentDocumentContentDeleteCommandExecutor;

    private DataCompanyJudgmentDocumentContentUpdateCommandExecutor dataCompanyJudgmentDocumentContentUpdateCommandExecutor;

    private DataCompanyJudgmentDocumentContentCommandExecutor dataCompanyJudgmentDocumentContentCommandExecutor;

    private DataCompanyJudgmentDocumentContentWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyJudgmentDocumentContentVO> create(DataCompanyJudgmentDocumentContentCreateCommand dataCompanyJudgmentDocumentContentCreateCommand) {
        return dataCompanyJudgmentDocumentContentCreateCommandExecutor.execute(dataCompanyJudgmentDocumentContentCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyJudgmentDocumentContentVO> delete(IdCommand deleteCommand) {
        return dataCompanyJudgmentDocumentContentDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyJudgmentDocumentContentVO> update(DataCompanyJudgmentDocumentContentUpdateCommand dataCompanyJudgmentDocumentContentUpdateCommand) {
        return dataCompanyJudgmentDocumentContentUpdateCommandExecutor.execute(dataCompanyJudgmentDocumentContentUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyJudgmentDocumentContentExWarehouseVO> warehouse(DataCompanyJudgmentDocumentContentWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyJudgmentDocumentContentCreateCommandExecutor(DataCompanyJudgmentDocumentContentCreateCommandExecutor dataCompanyJudgmentDocumentContentCreateCommandExecutor) {
        this.dataCompanyJudgmentDocumentContentCreateCommandExecutor = dataCompanyJudgmentDocumentContentCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyJudgmentDocumentContentDeleteCommandExecutor(DataCompanyJudgmentDocumentContentDeleteCommandExecutor dataCompanyJudgmentDocumentContentDeleteCommandExecutor) {
        this.dataCompanyJudgmentDocumentContentDeleteCommandExecutor = dataCompanyJudgmentDocumentContentDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyJudgmentDocumentContentUpdateCommandExecutor(DataCompanyJudgmentDocumentContentUpdateCommandExecutor dataCompanyJudgmentDocumentContentUpdateCommandExecutor) {
        this.dataCompanyJudgmentDocumentContentUpdateCommandExecutor = dataCompanyJudgmentDocumentContentUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyJudgmentDocumentContentCommandExecutor(DataCompanyJudgmentDocumentContentCommandExecutor dataCompanyJudgmentDocumentContentCommandExecutor) {
        this.dataCompanyJudgmentDocumentContentCommandExecutor = dataCompanyJudgmentDocumentContentCommandExecutor;
    }
    @Autowired
    public void setDataCompanyJudgmentDocumentContentWarehouseCommandExecutor(DataCompanyJudgmentDocumentContentWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}