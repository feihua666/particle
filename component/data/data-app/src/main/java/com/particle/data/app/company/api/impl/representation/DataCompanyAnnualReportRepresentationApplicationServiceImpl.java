package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.company.executor.representation.DataCompanyAnnualReportQueryCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAnnualReportExWarehouseCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyAnnualReportRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportExWarehouseVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 企业年报 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:58
 */
@Service
@CatchAndLog
public class DataCompanyAnnualReportRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAnnualReportRepresentationApplicationService {

    private DataCompanyAnnualReportQueryCommandExecutor dataCompanyAnnualReportQueryCommandExecutor;
    private DataCompanyAnnualReportExWarehouseCommandExecutor dataCompanyAnnualReportExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAnnualReportVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyAnnualReportQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyAnnualReportQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyAnnualReportVO> pageQuery(DataCompanyAnnualReportPageQueryCommand dataCompanyAnnualReportPageQueryCommand) {
        return dataCompanyAnnualReportQueryCommandExecutor.execute(dataCompanyAnnualReportPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyAnnualReportVO> queryList(DataCompanyAnnualReportQueryListCommand dataCompanyAnnualReportQueryListCommand) {
        return dataCompanyAnnualReportQueryCommandExecutor.execute(dataCompanyAnnualReportQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyAnnualReportExWarehouseVO> exWarehouse(DataCompanyAnnualReportExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyAnnualReportExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyAnnualReportQueryCommandExecutor(DataCompanyAnnualReportQueryCommandExecutor dataCompanyAnnualReportQueryCommandExecutor) {
        this.dataCompanyAnnualReportQueryCommandExecutor = dataCompanyAnnualReportQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportExWarehouseCommandExecutor(DataCompanyAnnualReportExWarehouseCommandExecutor dataCompanyAnnualReportExWarehouseCommandExecutor) {
        this.dataCompanyAnnualReportExWarehouseCommandExecutor = dataCompanyAnnualReportExWarehouseCommandExecutor;
    }
}
