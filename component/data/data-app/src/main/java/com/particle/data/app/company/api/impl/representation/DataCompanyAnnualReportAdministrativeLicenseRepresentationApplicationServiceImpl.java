package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.company.executor.representation.DataCompanyAnnualReportAdministrativeLicenseQueryCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAnnualReportAdministrativeLicenseExWarehouseCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyAnnualReportAdministrativeLicenseRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportAdministrativeLicensePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportAdministrativeLicenseQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportAdministrativeLicenseExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportAdministrativeLicenseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 企业年报行政许可 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:15
 */
@Service
@CatchAndLog
public class DataCompanyAnnualReportAdministrativeLicenseRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAnnualReportAdministrativeLicenseRepresentationApplicationService {

    private DataCompanyAnnualReportAdministrativeLicenseQueryCommandExecutor dataCompanyAnnualReportAdministrativeLicenseQueryCommandExecutor;
    private DataCompanyAnnualReportAdministrativeLicenseExWarehouseCommandExecutor dataCompanyAnnualReportAdministrativeLicenseExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAnnualReportAdministrativeLicenseVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyAnnualReportAdministrativeLicenseQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportAdministrativeLicenseVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyAnnualReportAdministrativeLicenseQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyAnnualReportAdministrativeLicenseVO> pageQuery(DataCompanyAnnualReportAdministrativeLicensePageQueryCommand dataCompanyAnnualReportAdministrativeLicensePageQueryCommand) {
        return dataCompanyAnnualReportAdministrativeLicenseQueryCommandExecutor.execute(dataCompanyAnnualReportAdministrativeLicensePageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyAnnualReportAdministrativeLicenseVO> queryList(DataCompanyAnnualReportAdministrativeLicenseQueryListCommand dataCompanyAnnualReportAdministrativeLicenseQueryListCommand) {
        return dataCompanyAnnualReportAdministrativeLicenseQueryCommandExecutor.execute(dataCompanyAnnualReportAdministrativeLicenseQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO> exWarehouse(DataCompanyAnnualReportAdministrativeLicenseExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyAnnualReportAdministrativeLicenseExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyAnnualReportAdministrativeLicenseQueryCommandExecutor(DataCompanyAnnualReportAdministrativeLicenseQueryCommandExecutor dataCompanyAnnualReportAdministrativeLicenseQueryCommandExecutor) {
        this.dataCompanyAnnualReportAdministrativeLicenseQueryCommandExecutor = dataCompanyAnnualReportAdministrativeLicenseQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportAdministrativeLicenseExWarehouseCommandExecutor(DataCompanyAnnualReportAdministrativeLicenseExWarehouseCommandExecutor dataCompanyAnnualReportAdministrativeLicenseExWarehouseCommandExecutor) {
        this.dataCompanyAnnualReportAdministrativeLicenseExWarehouseCommandExecutor = dataCompanyAnnualReportAdministrativeLicenseExWarehouseCommandExecutor;
    }
}
