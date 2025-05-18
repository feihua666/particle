package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanySeriousIllegalCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanySeriousIllegalDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanySeriousIllegalUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanySeriousIllegalCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanySeriousIllegalUpdateCommand;
import com.particle.data.client.company.api.IDataCompanySeriousIllegalApplicationService;
import com.particle.data.client.company.dto.command.DataCompanySeriousIllegalCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanySeriousIllegalVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanySeriousIllegalWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySeriousIllegalExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanySeriousIllegalWarehouseCommandExecutor;
/**
 * <p>
 * 企业严重违法 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:45
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanySeriousIllegalApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanySeriousIllegalApplicationService {

    private DataCompanySeriousIllegalCreateCommandExecutor dataCompanySeriousIllegalCreateCommandExecutor;

    private DataCompanySeriousIllegalDeleteCommandExecutor dataCompanySeriousIllegalDeleteCommandExecutor;

    private DataCompanySeriousIllegalUpdateCommandExecutor dataCompanySeriousIllegalUpdateCommandExecutor;

    private DataCompanySeriousIllegalCommandExecutor dataCompanySeriousIllegalCommandExecutor;

    private DataCompanySeriousIllegalWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanySeriousIllegalVO> create(DataCompanySeriousIllegalCreateCommand dataCompanySeriousIllegalCreateCommand) {
        return dataCompanySeriousIllegalCreateCommandExecutor.execute(dataCompanySeriousIllegalCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanySeriousIllegalVO> delete(IdCommand deleteCommand) {
        return dataCompanySeriousIllegalDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanySeriousIllegalVO> update(DataCompanySeriousIllegalUpdateCommand dataCompanySeriousIllegalUpdateCommand) {
        return dataCompanySeriousIllegalUpdateCommandExecutor.execute(dataCompanySeriousIllegalUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanySeriousIllegalExWarehouseVO> warehouse(DataCompanySeriousIllegalWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanySeriousIllegalCreateCommandExecutor(DataCompanySeriousIllegalCreateCommandExecutor dataCompanySeriousIllegalCreateCommandExecutor) {
        this.dataCompanySeriousIllegalCreateCommandExecutor = dataCompanySeriousIllegalCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanySeriousIllegalDeleteCommandExecutor(DataCompanySeriousIllegalDeleteCommandExecutor dataCompanySeriousIllegalDeleteCommandExecutor) {
        this.dataCompanySeriousIllegalDeleteCommandExecutor = dataCompanySeriousIllegalDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanySeriousIllegalUpdateCommandExecutor(DataCompanySeriousIllegalUpdateCommandExecutor dataCompanySeriousIllegalUpdateCommandExecutor) {
        this.dataCompanySeriousIllegalUpdateCommandExecutor = dataCompanySeriousIllegalUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanySeriousIllegalCommandExecutor(DataCompanySeriousIllegalCommandExecutor dataCompanySeriousIllegalCommandExecutor) {
        this.dataCompanySeriousIllegalCommandExecutor = dataCompanySeriousIllegalCommandExecutor;
    }
    @Autowired
    public void setDataCompanySeriousIllegalWarehouseCommandExecutor(DataCompanySeriousIllegalWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}