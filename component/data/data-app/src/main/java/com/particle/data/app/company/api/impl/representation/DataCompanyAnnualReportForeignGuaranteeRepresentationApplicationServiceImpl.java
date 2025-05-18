package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyAnnualReportForeignGuaranteeQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyAnnualReportForeignGuaranteeRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportForeignGuaranteePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportForeignGuaranteeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportForeignGuaranteeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportForeignGuaranteeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportForeignGuaranteeExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor;
/**
 * <p>
 * 企业年报对外担保 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:11
 */
@Service
@CatchAndLog
public class DataCompanyAnnualReportForeignGuaranteeRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAnnualReportForeignGuaranteeRepresentationApplicationService {

    private DataCompanyAnnualReportForeignGuaranteeQueryCommandExecutor dataCompanyAnnualReportForeignGuaranteeQueryCommandExecutor;
    private DataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor dataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAnnualReportForeignGuaranteeVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyAnnualReportForeignGuaranteeQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportForeignGuaranteeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyAnnualReportForeignGuaranteeQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyAnnualReportForeignGuaranteeVO> pageQuery(DataCompanyAnnualReportForeignGuaranteePageQueryCommand dataCompanyAnnualReportForeignGuaranteePageQueryCommand) {
        return dataCompanyAnnualReportForeignGuaranteeQueryCommandExecutor.execute(dataCompanyAnnualReportForeignGuaranteePageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyAnnualReportForeignGuaranteeVO> queryList(DataCompanyAnnualReportForeignGuaranteeQueryListCommand dataCompanyAnnualReportForeignGuaranteeQueryListCommand) {
        return dataCompanyAnnualReportForeignGuaranteeQueryCommandExecutor.execute(dataCompanyAnnualReportForeignGuaranteeQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyAnnualReportForeignGuaranteeExWarehouseVO> exWarehouse(DataCompanyAnnualReportForeignGuaranteeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyAnnualReportForeignGuaranteeQueryCommandExecutor(DataCompanyAnnualReportForeignGuaranteeQueryCommandExecutor dataCompanyAnnualReportForeignGuaranteeQueryCommandExecutor) {
        this.dataCompanyAnnualReportForeignGuaranteeQueryCommandExecutor = dataCompanyAnnualReportForeignGuaranteeQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor(DataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor dataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor) {
        this.dataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor = dataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor;
    }
}
