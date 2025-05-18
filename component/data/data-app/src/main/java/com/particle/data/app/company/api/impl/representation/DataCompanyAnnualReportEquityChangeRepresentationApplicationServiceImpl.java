package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.company.executor.representation.DataCompanyAnnualReportEquityChangeQueryCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyAnnualReportEquityChangeRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportEquityChangePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportEquityChangeQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportEquityChangeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportEquityChangeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportEquityChangeExWarehouseVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 企业年报股权变更 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:57
 */
@Service
@CatchAndLog
public class DataCompanyAnnualReportEquityChangeRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAnnualReportEquityChangeRepresentationApplicationService {

    private DataCompanyAnnualReportEquityChangeQueryCommandExecutor dataCompanyAnnualReportEquityChangeQueryCommandExecutor;
    private DataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor dataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAnnualReportEquityChangeVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyAnnualReportEquityChangeQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportEquityChangeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyAnnualReportEquityChangeQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyAnnualReportEquityChangeVO> pageQuery(DataCompanyAnnualReportEquityChangePageQueryCommand dataCompanyAnnualReportEquityChangePageQueryCommand) {
        return dataCompanyAnnualReportEquityChangeQueryCommandExecutor.execute(dataCompanyAnnualReportEquityChangePageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyAnnualReportEquityChangeVO> queryList(DataCompanyAnnualReportEquityChangeQueryListCommand dataCompanyAnnualReportEquityChangeQueryListCommand) {
        return dataCompanyAnnualReportEquityChangeQueryCommandExecutor.execute(dataCompanyAnnualReportEquityChangeQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyAnnualReportEquityChangeExWarehouseVO> exWarehouse(DataCompanyAnnualReportEquityChangeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyAnnualReportEquityChangeQueryCommandExecutor(DataCompanyAnnualReportEquityChangeQueryCommandExecutor dataCompanyAnnualReportEquityChangeQueryCommandExecutor) {
        this.dataCompanyAnnualReportEquityChangeQueryCommandExecutor = dataCompanyAnnualReportEquityChangeQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor(DataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor dataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor) {
        this.dataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor = dataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor;
    }
}
