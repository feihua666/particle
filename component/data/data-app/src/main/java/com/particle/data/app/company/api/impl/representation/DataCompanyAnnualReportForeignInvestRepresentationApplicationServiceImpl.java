package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyAnnualReportForeignInvestQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyAnnualReportForeignInvestRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportForeignInvestPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportForeignInvestQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportForeignInvestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportForeignInvestExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportForeignInvestExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor;
/**
 * <p>
 * 企业年报对外投资 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:23
 */
@Service
@CatchAndLog
public class DataCompanyAnnualReportForeignInvestRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAnnualReportForeignInvestRepresentationApplicationService {

    private DataCompanyAnnualReportForeignInvestQueryCommandExecutor dataCompanyAnnualReportForeignInvestQueryCommandExecutor;
    private DataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor dataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAnnualReportForeignInvestVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyAnnualReportForeignInvestQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportForeignInvestVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyAnnualReportForeignInvestQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyAnnualReportForeignInvestVO> pageQuery(DataCompanyAnnualReportForeignInvestPageQueryCommand dataCompanyAnnualReportForeignInvestPageQueryCommand) {
        return dataCompanyAnnualReportForeignInvestQueryCommandExecutor.execute(dataCompanyAnnualReportForeignInvestPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyAnnualReportForeignInvestVO> queryList(DataCompanyAnnualReportForeignInvestQueryListCommand dataCompanyAnnualReportForeignInvestQueryListCommand) {
        return dataCompanyAnnualReportForeignInvestQueryCommandExecutor.execute(dataCompanyAnnualReportForeignInvestQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyAnnualReportForeignInvestExWarehouseVO> exWarehouse(DataCompanyAnnualReportForeignInvestExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyAnnualReportForeignInvestQueryCommandExecutor(DataCompanyAnnualReportForeignInvestQueryCommandExecutor dataCompanyAnnualReportForeignInvestQueryCommandExecutor) {
        this.dataCompanyAnnualReportForeignInvestQueryCommandExecutor = dataCompanyAnnualReportForeignInvestQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor(DataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor dataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor) {
        this.dataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor = dataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor;
    }
}
