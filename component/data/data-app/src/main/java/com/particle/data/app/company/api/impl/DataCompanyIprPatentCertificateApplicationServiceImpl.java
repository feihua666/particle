package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprPatentCertificateCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentCertificateDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentCertificateUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentCertificateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCertificateUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprPatentCertificateApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCertificateCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentCertificateVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentCertificateWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentCertificateExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprPatentCertificateWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利证书信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:02
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprPatentCertificateApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentCertificateApplicationService {

    private DataCompanyIprPatentCertificateCreateCommandExecutor dataCompanyIprPatentCertificateCreateCommandExecutor;

    private DataCompanyIprPatentCertificateDeleteCommandExecutor dataCompanyIprPatentCertificateDeleteCommandExecutor;

    private DataCompanyIprPatentCertificateUpdateCommandExecutor dataCompanyIprPatentCertificateUpdateCommandExecutor;

    private DataCompanyIprPatentCertificateCommandExecutor dataCompanyIprPatentCertificateCommandExecutor;

    private DataCompanyIprPatentCertificateWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentCertificateVO> create(DataCompanyIprPatentCertificateCreateCommand dataCompanyIprPatentCertificateCreateCommand) {
        return dataCompanyIprPatentCertificateCreateCommandExecutor.execute(dataCompanyIprPatentCertificateCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentCertificateVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprPatentCertificateDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentCertificateVO> update(DataCompanyIprPatentCertificateUpdateCommand dataCompanyIprPatentCertificateUpdateCommand) {
        return dataCompanyIprPatentCertificateUpdateCommandExecutor.execute(dataCompanyIprPatentCertificateUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprPatentCertificateExWarehouseVO> warehouse(DataCompanyIprPatentCertificateWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprPatentCertificateCreateCommandExecutor(DataCompanyIprPatentCertificateCreateCommandExecutor dataCompanyIprPatentCertificateCreateCommandExecutor) {
        this.dataCompanyIprPatentCertificateCreateCommandExecutor = dataCompanyIprPatentCertificateCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprPatentCertificateDeleteCommandExecutor(DataCompanyIprPatentCertificateDeleteCommandExecutor dataCompanyIprPatentCertificateDeleteCommandExecutor) {
        this.dataCompanyIprPatentCertificateDeleteCommandExecutor = dataCompanyIprPatentCertificateDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentCertificateUpdateCommandExecutor(DataCompanyIprPatentCertificateUpdateCommandExecutor dataCompanyIprPatentCertificateUpdateCommandExecutor) {
        this.dataCompanyIprPatentCertificateUpdateCommandExecutor = dataCompanyIprPatentCertificateUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentCertificateCommandExecutor(DataCompanyIprPatentCertificateCommandExecutor dataCompanyIprPatentCertificateCommandExecutor) {
        this.dataCompanyIprPatentCertificateCommandExecutor = dataCompanyIprPatentCertificateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentCertificateWarehouseCommandExecutor(DataCompanyIprPatentCertificateWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}