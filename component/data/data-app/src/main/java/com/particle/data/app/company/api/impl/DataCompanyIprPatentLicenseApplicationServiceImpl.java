package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprPatentLicenseCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentLicenseDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentLicenseUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentLicenseCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentLicenseUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprPatentLicenseApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentLicenseCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentLicenseVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentLicenseWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentLicenseExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprPatentLicenseWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利许可信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:59
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprPatentLicenseApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentLicenseApplicationService {

    private DataCompanyIprPatentLicenseCreateCommandExecutor dataCompanyIprPatentLicenseCreateCommandExecutor;

    private DataCompanyIprPatentLicenseDeleteCommandExecutor dataCompanyIprPatentLicenseDeleteCommandExecutor;

    private DataCompanyIprPatentLicenseUpdateCommandExecutor dataCompanyIprPatentLicenseUpdateCommandExecutor;

    private DataCompanyIprPatentLicenseCommandExecutor dataCompanyIprPatentLicenseCommandExecutor;

    private DataCompanyIprPatentLicenseWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentLicenseVO> create(DataCompanyIprPatentLicenseCreateCommand dataCompanyIprPatentLicenseCreateCommand) {
        return dataCompanyIprPatentLicenseCreateCommandExecutor.execute(dataCompanyIprPatentLicenseCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentLicenseVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprPatentLicenseDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentLicenseVO> update(DataCompanyIprPatentLicenseUpdateCommand dataCompanyIprPatentLicenseUpdateCommand) {
        return dataCompanyIprPatentLicenseUpdateCommandExecutor.execute(dataCompanyIprPatentLicenseUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprPatentLicenseExWarehouseVO> warehouse(DataCompanyIprPatentLicenseWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprPatentLicenseCreateCommandExecutor(DataCompanyIprPatentLicenseCreateCommandExecutor dataCompanyIprPatentLicenseCreateCommandExecutor) {
        this.dataCompanyIprPatentLicenseCreateCommandExecutor = dataCompanyIprPatentLicenseCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprPatentLicenseDeleteCommandExecutor(DataCompanyIprPatentLicenseDeleteCommandExecutor dataCompanyIprPatentLicenseDeleteCommandExecutor) {
        this.dataCompanyIprPatentLicenseDeleteCommandExecutor = dataCompanyIprPatentLicenseDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentLicenseUpdateCommandExecutor(DataCompanyIprPatentLicenseUpdateCommandExecutor dataCompanyIprPatentLicenseUpdateCommandExecutor) {
        this.dataCompanyIprPatentLicenseUpdateCommandExecutor = dataCompanyIprPatentLicenseUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentLicenseCommandExecutor(DataCompanyIprPatentLicenseCommandExecutor dataCompanyIprPatentLicenseCommandExecutor) {
        this.dataCompanyIprPatentLicenseCommandExecutor = dataCompanyIprPatentLicenseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentLicenseWarehouseCommandExecutor(DataCompanyIprPatentLicenseWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}