package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprTrademarkLicensePersonCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkLicensePersonDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkLicensePersonUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkLicensePersonCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkLicensePersonUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprTrademarkLicensePersonApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkLicensePersonCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkLicensePersonVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkLicensePersonWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkLicensePersonExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprTrademarkLicensePersonWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权商标许可人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:22
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprTrademarkLicensePersonApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprTrademarkLicensePersonApplicationService {

    private DataCompanyIprTrademarkLicensePersonCreateCommandExecutor dataCompanyIprTrademarkLicensePersonCreateCommandExecutor;

    private DataCompanyIprTrademarkLicensePersonDeleteCommandExecutor dataCompanyIprTrademarkLicensePersonDeleteCommandExecutor;

    private DataCompanyIprTrademarkLicensePersonUpdateCommandExecutor dataCompanyIprTrademarkLicensePersonUpdateCommandExecutor;

    private DataCompanyIprTrademarkLicensePersonCommandExecutor dataCompanyIprTrademarkLicensePersonCommandExecutor;

    private DataCompanyIprTrademarkLicensePersonWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprTrademarkLicensePersonVO> create(DataCompanyIprTrademarkLicensePersonCreateCommand dataCompanyIprTrademarkLicensePersonCreateCommand) {
        return dataCompanyIprTrademarkLicensePersonCreateCommandExecutor.execute(dataCompanyIprTrademarkLicensePersonCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprTrademarkLicensePersonVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprTrademarkLicensePersonDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprTrademarkLicensePersonVO> update(DataCompanyIprTrademarkLicensePersonUpdateCommand dataCompanyIprTrademarkLicensePersonUpdateCommand) {
        return dataCompanyIprTrademarkLicensePersonUpdateCommandExecutor.execute(dataCompanyIprTrademarkLicensePersonUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprTrademarkLicensePersonExWarehouseVO> warehouse(DataCompanyIprTrademarkLicensePersonWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprTrademarkLicensePersonCreateCommandExecutor(DataCompanyIprTrademarkLicensePersonCreateCommandExecutor dataCompanyIprTrademarkLicensePersonCreateCommandExecutor) {
        this.dataCompanyIprTrademarkLicensePersonCreateCommandExecutor = dataCompanyIprTrademarkLicensePersonCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprTrademarkLicensePersonDeleteCommandExecutor(DataCompanyIprTrademarkLicensePersonDeleteCommandExecutor dataCompanyIprTrademarkLicensePersonDeleteCommandExecutor) {
        this.dataCompanyIprTrademarkLicensePersonDeleteCommandExecutor = dataCompanyIprTrademarkLicensePersonDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkLicensePersonUpdateCommandExecutor(DataCompanyIprTrademarkLicensePersonUpdateCommandExecutor dataCompanyIprTrademarkLicensePersonUpdateCommandExecutor) {
        this.dataCompanyIprTrademarkLicensePersonUpdateCommandExecutor = dataCompanyIprTrademarkLicensePersonUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkLicensePersonCommandExecutor(DataCompanyIprTrademarkLicensePersonCommandExecutor dataCompanyIprTrademarkLicensePersonCommandExecutor) {
        this.dataCompanyIprTrademarkLicensePersonCommandExecutor = dataCompanyIprTrademarkLicensePersonCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkLicensePersonWarehouseCommandExecutor(DataCompanyIprTrademarkLicensePersonWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}