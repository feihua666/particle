package com.particle.data.app.company.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.company.executor.DataCompanyAnnualReportSocialSecurityCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportSocialSecurityCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportSocialSecurityDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportSocialSecurityUpdateCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor;
import com.particle.data.client.company.api.IDataCompanyAnnualReportSocialSecurityApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportSocialSecurityCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportSocialSecurityUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportSocialSecurityWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportSocialSecurityVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportSocialSecurityExWarehouseVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 企业年报社保 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:03
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyAnnualReportSocialSecurityApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAnnualReportSocialSecurityApplicationService {

    private DataCompanyAnnualReportSocialSecurityCreateCommandExecutor dataCompanyAnnualReportSocialSecurityCreateCommandExecutor;

    private DataCompanyAnnualReportSocialSecurityDeleteCommandExecutor dataCompanyAnnualReportSocialSecurityDeleteCommandExecutor;

    private DataCompanyAnnualReportSocialSecurityUpdateCommandExecutor dataCompanyAnnualReportSocialSecurityUpdateCommandExecutor;

    private DataCompanyAnnualReportSocialSecurityCommandExecutor dataCompanyAnnualReportSocialSecurityCommandExecutor;

    private DataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor dataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor;
    private DataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAnnualReportSocialSecurityVO> create(DataCompanyAnnualReportSocialSecurityCreateCommand dataCompanyAnnualReportSocialSecurityCreateCommand) {
        return dataCompanyAnnualReportSocialSecurityCreateCommandExecutor.execute(dataCompanyAnnualReportSocialSecurityCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportSocialSecurityVO> delete(IdCommand deleteCommand) {
        return dataCompanyAnnualReportSocialSecurityDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportSocialSecurityVO> update(DataCompanyAnnualReportSocialSecurityUpdateCommand dataCompanyAnnualReportSocialSecurityUpdateCommand) {
        return dataCompanyAnnualReportSocialSecurityUpdateCommandExecutor.execute(dataCompanyAnnualReportSocialSecurityUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyAnnualReportSocialSecurityExWarehouseVO> warehouse(DataCompanyAnnualReportSocialSecurityWarehouseCommand dataCompanyAnnualReportSocialSecurityWarehouseCommand) {
        return dataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor.warehouse(dataCompanyAnnualReportSocialSecurityWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyAnnualReportSocialSecurityCreateCommandExecutor(DataCompanyAnnualReportSocialSecurityCreateCommandExecutor dataCompanyAnnualReportSocialSecurityCreateCommandExecutor) {
        this.dataCompanyAnnualReportSocialSecurityCreateCommandExecutor = dataCompanyAnnualReportSocialSecurityCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyAnnualReportSocialSecurityDeleteCommandExecutor(DataCompanyAnnualReportSocialSecurityDeleteCommandExecutor dataCompanyAnnualReportSocialSecurityDeleteCommandExecutor) {
        this.dataCompanyAnnualReportSocialSecurityDeleteCommandExecutor = dataCompanyAnnualReportSocialSecurityDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportSocialSecurityUpdateCommandExecutor(DataCompanyAnnualReportSocialSecurityUpdateCommandExecutor dataCompanyAnnualReportSocialSecurityUpdateCommandExecutor) {
        this.dataCompanyAnnualReportSocialSecurityUpdateCommandExecutor = dataCompanyAnnualReportSocialSecurityUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportSocialSecurityCommandExecutor(DataCompanyAnnualReportSocialSecurityCommandExecutor dataCompanyAnnualReportSocialSecurityCommandExecutor) {
        this.dataCompanyAnnualReportSocialSecurityCommandExecutor = dataCompanyAnnualReportSocialSecurityCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor(DataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor dataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor) {
        this.dataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor = dataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor;
    }

}
