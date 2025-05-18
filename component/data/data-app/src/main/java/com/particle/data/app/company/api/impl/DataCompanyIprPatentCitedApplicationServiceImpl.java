package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprPatentCitedCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentCitedDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentCitedUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentCitedCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCitedUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprPatentCitedApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCitedCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentCitedVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentCitedWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentCitedExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprPatentCitedWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利被引证信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:15
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprPatentCitedApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentCitedApplicationService {

    private DataCompanyIprPatentCitedCreateCommandExecutor dataCompanyIprPatentCitedCreateCommandExecutor;

    private DataCompanyIprPatentCitedDeleteCommandExecutor dataCompanyIprPatentCitedDeleteCommandExecutor;

    private DataCompanyIprPatentCitedUpdateCommandExecutor dataCompanyIprPatentCitedUpdateCommandExecutor;

    private DataCompanyIprPatentCitedCommandExecutor dataCompanyIprPatentCitedCommandExecutor;

    private DataCompanyIprPatentCitedWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentCitedVO> create(DataCompanyIprPatentCitedCreateCommand dataCompanyIprPatentCitedCreateCommand) {
        return dataCompanyIprPatentCitedCreateCommandExecutor.execute(dataCompanyIprPatentCitedCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentCitedVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprPatentCitedDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentCitedVO> update(DataCompanyIprPatentCitedUpdateCommand dataCompanyIprPatentCitedUpdateCommand) {
        return dataCompanyIprPatentCitedUpdateCommandExecutor.execute(dataCompanyIprPatentCitedUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprPatentCitedExWarehouseVO> warehouse(DataCompanyIprPatentCitedWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprPatentCitedCreateCommandExecutor(DataCompanyIprPatentCitedCreateCommandExecutor dataCompanyIprPatentCitedCreateCommandExecutor) {
        this.dataCompanyIprPatentCitedCreateCommandExecutor = dataCompanyIprPatentCitedCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprPatentCitedDeleteCommandExecutor(DataCompanyIprPatentCitedDeleteCommandExecutor dataCompanyIprPatentCitedDeleteCommandExecutor) {
        this.dataCompanyIprPatentCitedDeleteCommandExecutor = dataCompanyIprPatentCitedDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentCitedUpdateCommandExecutor(DataCompanyIprPatentCitedUpdateCommandExecutor dataCompanyIprPatentCitedUpdateCommandExecutor) {
        this.dataCompanyIprPatentCitedUpdateCommandExecutor = dataCompanyIprPatentCitedUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentCitedCommandExecutor(DataCompanyIprPatentCitedCommandExecutor dataCompanyIprPatentCitedCommandExecutor) {
        this.dataCompanyIprPatentCitedCommandExecutor = dataCompanyIprPatentCitedCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentCitedWarehouseCommandExecutor(DataCompanyIprPatentCitedWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}