package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyAnnualReportForeignGuaranteeCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportForeignGuaranteeDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportForeignGuaranteeUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportForeignGuaranteeCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportForeignGuaranteeUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyAnnualReportForeignGuaranteeApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportForeignGuaranteeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportForeignGuaranteeVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportForeignGuaranteeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportForeignGuaranteeExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyAnnualReportForeignGuaranteeWarehouseCommandExecutor;
/**
 * <p>
 * 企业年报对外担保 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:11
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyAnnualReportForeignGuaranteeApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAnnualReportForeignGuaranteeApplicationService {

    private DataCompanyAnnualReportForeignGuaranteeCreateCommandExecutor dataCompanyAnnualReportForeignGuaranteeCreateCommandExecutor;

    private DataCompanyAnnualReportForeignGuaranteeDeleteCommandExecutor dataCompanyAnnualReportForeignGuaranteeDeleteCommandExecutor;

    private DataCompanyAnnualReportForeignGuaranteeUpdateCommandExecutor dataCompanyAnnualReportForeignGuaranteeUpdateCommandExecutor;

    private DataCompanyAnnualReportForeignGuaranteeCommandExecutor dataCompanyAnnualReportForeignGuaranteeCommandExecutor;

    private DataCompanyAnnualReportForeignGuaranteeWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAnnualReportForeignGuaranteeVO> create(DataCompanyAnnualReportForeignGuaranteeCreateCommand dataCompanyAnnualReportForeignGuaranteeCreateCommand) {
        return dataCompanyAnnualReportForeignGuaranteeCreateCommandExecutor.execute(dataCompanyAnnualReportForeignGuaranteeCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportForeignGuaranteeVO> delete(IdCommand deleteCommand) {
        return dataCompanyAnnualReportForeignGuaranteeDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportForeignGuaranteeVO> update(DataCompanyAnnualReportForeignGuaranteeUpdateCommand dataCompanyAnnualReportForeignGuaranteeUpdateCommand) {
        return dataCompanyAnnualReportForeignGuaranteeUpdateCommandExecutor.execute(dataCompanyAnnualReportForeignGuaranteeUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyAnnualReportForeignGuaranteeExWarehouseVO> warehouse(DataCompanyAnnualReportForeignGuaranteeWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyAnnualReportForeignGuaranteeCreateCommandExecutor(DataCompanyAnnualReportForeignGuaranteeCreateCommandExecutor dataCompanyAnnualReportForeignGuaranteeCreateCommandExecutor) {
        this.dataCompanyAnnualReportForeignGuaranteeCreateCommandExecutor = dataCompanyAnnualReportForeignGuaranteeCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyAnnualReportForeignGuaranteeDeleteCommandExecutor(DataCompanyAnnualReportForeignGuaranteeDeleteCommandExecutor dataCompanyAnnualReportForeignGuaranteeDeleteCommandExecutor) {
        this.dataCompanyAnnualReportForeignGuaranteeDeleteCommandExecutor = dataCompanyAnnualReportForeignGuaranteeDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportForeignGuaranteeUpdateCommandExecutor(DataCompanyAnnualReportForeignGuaranteeUpdateCommandExecutor dataCompanyAnnualReportForeignGuaranteeUpdateCommandExecutor) {
        this.dataCompanyAnnualReportForeignGuaranteeUpdateCommandExecutor = dataCompanyAnnualReportForeignGuaranteeUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportForeignGuaranteeCommandExecutor(DataCompanyAnnualReportForeignGuaranteeCommandExecutor dataCompanyAnnualReportForeignGuaranteeCommandExecutor) {
        this.dataCompanyAnnualReportForeignGuaranteeCommandExecutor = dataCompanyAnnualReportForeignGuaranteeCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportForeignGuaranteeWarehouseCommandExecutor(DataCompanyAnnualReportForeignGuaranteeWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}