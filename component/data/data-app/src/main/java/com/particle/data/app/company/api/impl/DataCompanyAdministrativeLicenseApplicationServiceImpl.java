package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyAdministrativeLicenseCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAdministrativeLicenseDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAdministrativeLicenseUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAdministrativeLicenseCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAdministrativeLicenseUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyAdministrativeLicenseApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAdministrativeLicenseCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAdministrativeLicenseVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAdministrativeLicenseWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAdministrativeLicenseExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyAdministrativeLicenseWarehouseCommandExecutor;
/**
 * <p>
 * 企业行政许可 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:17:53
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyAdministrativeLicenseApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAdministrativeLicenseApplicationService {

    private DataCompanyAdministrativeLicenseCreateCommandExecutor dataCompanyAdministrativeLicenseCreateCommandExecutor;

    private DataCompanyAdministrativeLicenseDeleteCommandExecutor dataCompanyAdministrativeLicenseDeleteCommandExecutor;

    private DataCompanyAdministrativeLicenseUpdateCommandExecutor dataCompanyAdministrativeLicenseUpdateCommandExecutor;

    private DataCompanyAdministrativeLicenseCommandExecutor dataCompanyAdministrativeLicenseCommandExecutor;

    private DataCompanyAdministrativeLicenseWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAdministrativeLicenseVO> create(DataCompanyAdministrativeLicenseCreateCommand dataCompanyAdministrativeLicenseCreateCommand) {
        return dataCompanyAdministrativeLicenseCreateCommandExecutor.execute(dataCompanyAdministrativeLicenseCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyAdministrativeLicenseVO> delete(IdCommand deleteCommand) {
        return dataCompanyAdministrativeLicenseDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyAdministrativeLicenseVO> update(DataCompanyAdministrativeLicenseUpdateCommand dataCompanyAdministrativeLicenseUpdateCommand) {
        return dataCompanyAdministrativeLicenseUpdateCommandExecutor.execute(dataCompanyAdministrativeLicenseUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyAdministrativeLicenseExWarehouseVO> warehouse(DataCompanyAdministrativeLicenseWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyAdministrativeLicenseCreateCommandExecutor(DataCompanyAdministrativeLicenseCreateCommandExecutor dataCompanyAdministrativeLicenseCreateCommandExecutor) {
        this.dataCompanyAdministrativeLicenseCreateCommandExecutor = dataCompanyAdministrativeLicenseCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyAdministrativeLicenseDeleteCommandExecutor(DataCompanyAdministrativeLicenseDeleteCommandExecutor dataCompanyAdministrativeLicenseDeleteCommandExecutor) {
        this.dataCompanyAdministrativeLicenseDeleteCommandExecutor = dataCompanyAdministrativeLicenseDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAdministrativeLicenseUpdateCommandExecutor(DataCompanyAdministrativeLicenseUpdateCommandExecutor dataCompanyAdministrativeLicenseUpdateCommandExecutor) {
        this.dataCompanyAdministrativeLicenseUpdateCommandExecutor = dataCompanyAdministrativeLicenseUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAdministrativeLicenseCommandExecutor(DataCompanyAdministrativeLicenseCommandExecutor dataCompanyAdministrativeLicenseCommandExecutor) {
        this.dataCompanyAdministrativeLicenseCommandExecutor = dataCompanyAdministrativeLicenseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAdministrativeLicenseWarehouseCommandExecutor(DataCompanyAdministrativeLicenseWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}