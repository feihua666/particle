package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyAnnualReportChangeQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyAnnualReportChangeRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportChangePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportChangeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportChangeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportChangeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportChangeExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAnnualReportChangeExWarehouseCommandExecutor;
/**
 * <p>
 * 企业年报变更 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:43
 */
@Service
@CatchAndLog
public class DataCompanyAnnualReportChangeRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAnnualReportChangeRepresentationApplicationService {

    private DataCompanyAnnualReportChangeQueryCommandExecutor dataCompanyAnnualReportChangeQueryCommandExecutor;
    private DataCompanyAnnualReportChangeExWarehouseCommandExecutor dataCompanyAnnualReportChangeExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAnnualReportChangeVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyAnnualReportChangeQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportChangeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyAnnualReportChangeQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyAnnualReportChangeVO> pageQuery(DataCompanyAnnualReportChangePageQueryCommand dataCompanyAnnualReportChangePageQueryCommand) {
        return dataCompanyAnnualReportChangeQueryCommandExecutor.execute(dataCompanyAnnualReportChangePageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyAnnualReportChangeVO> queryList(DataCompanyAnnualReportChangeQueryListCommand dataCompanyAnnualReportChangeQueryListCommand) {
        return dataCompanyAnnualReportChangeQueryCommandExecutor.execute(dataCompanyAnnualReportChangeQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyAnnualReportChangeExWarehouseVO> exWarehouse(DataCompanyAnnualReportChangeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyAnnualReportChangeExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyAnnualReportChangeQueryCommandExecutor(DataCompanyAnnualReportChangeQueryCommandExecutor dataCompanyAnnualReportChangeQueryCommandExecutor) {
        this.dataCompanyAnnualReportChangeQueryCommandExecutor = dataCompanyAnnualReportChangeQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportChangeExWarehouseCommandExecutor(DataCompanyAnnualReportChangeExWarehouseCommandExecutor dataCompanyAnnualReportChangeExWarehouseCommandExecutor) {
        this.dataCompanyAnnualReportChangeExWarehouseCommandExecutor = dataCompanyAnnualReportChangeExWarehouseCommandExecutor;
    }
}
