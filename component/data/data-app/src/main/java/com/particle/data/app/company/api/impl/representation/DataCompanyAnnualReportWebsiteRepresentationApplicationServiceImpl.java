package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyAnnualReportWebsiteQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyAnnualReportWebsiteRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportWebsitePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportWebsiteQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportWebsiteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportWebsiteExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportWebsiteExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAnnualReportWebsiteExWarehouseCommandExecutor;
/**
 * <p>
 * 企业年报网站网店 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:16
 */
@Service
@CatchAndLog
public class DataCompanyAnnualReportWebsiteRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAnnualReportWebsiteRepresentationApplicationService {

    private DataCompanyAnnualReportWebsiteQueryCommandExecutor dataCompanyAnnualReportWebsiteQueryCommandExecutor;
    private DataCompanyAnnualReportWebsiteExWarehouseCommandExecutor dataCompanyAnnualReportWebsiteExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAnnualReportWebsiteVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyAnnualReportWebsiteQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportWebsiteVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyAnnualReportWebsiteQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyAnnualReportWebsiteVO> pageQuery(DataCompanyAnnualReportWebsitePageQueryCommand dataCompanyAnnualReportWebsitePageQueryCommand) {
        return dataCompanyAnnualReportWebsiteQueryCommandExecutor.execute(dataCompanyAnnualReportWebsitePageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyAnnualReportWebsiteVO> queryList(DataCompanyAnnualReportWebsiteQueryListCommand dataCompanyAnnualReportWebsiteQueryListCommand) {
        return dataCompanyAnnualReportWebsiteQueryCommandExecutor.execute(dataCompanyAnnualReportWebsiteQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyAnnualReportWebsiteExWarehouseVO> exWarehouse(DataCompanyAnnualReportWebsiteExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyAnnualReportWebsiteExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyAnnualReportWebsiteQueryCommandExecutor(DataCompanyAnnualReportWebsiteQueryCommandExecutor dataCompanyAnnualReportWebsiteQueryCommandExecutor) {
        this.dataCompanyAnnualReportWebsiteQueryCommandExecutor = dataCompanyAnnualReportWebsiteQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportWebsiteExWarehouseCommandExecutor(DataCompanyAnnualReportWebsiteExWarehouseCommandExecutor dataCompanyAnnualReportWebsiteExWarehouseCommandExecutor) {
        this.dataCompanyAnnualReportWebsiteExWarehouseCommandExecutor = dataCompanyAnnualReportWebsiteExWarehouseCommandExecutor;
    }
}
