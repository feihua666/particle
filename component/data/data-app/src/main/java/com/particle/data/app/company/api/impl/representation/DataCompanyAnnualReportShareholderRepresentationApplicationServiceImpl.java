package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.company.executor.representation.DataCompanyAnnualReportShareholderQueryCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAnnualReportShareholderExWarehouseCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyAnnualReportShareholderRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportShareholderPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportShareholderQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportShareholderExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportShareholderVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportShareholderExWarehouseVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 企业年报股东 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:37
 */
@Service
@CatchAndLog
public class DataCompanyAnnualReportShareholderRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAnnualReportShareholderRepresentationApplicationService {

    private DataCompanyAnnualReportShareholderQueryCommandExecutor dataCompanyAnnualReportShareholderQueryCommandExecutor;
    private DataCompanyAnnualReportShareholderExWarehouseCommandExecutor dataCompanyAnnualReportShareholderExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAnnualReportShareholderVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyAnnualReportShareholderQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportShareholderVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyAnnualReportShareholderQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyAnnualReportShareholderVO> pageQuery(DataCompanyAnnualReportShareholderPageQueryCommand dataCompanyAnnualReportShareholderPageQueryCommand) {
        return dataCompanyAnnualReportShareholderQueryCommandExecutor.execute(dataCompanyAnnualReportShareholderPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyAnnualReportShareholderVO> queryList(DataCompanyAnnualReportShareholderQueryListCommand dataCompanyAnnualReportShareholderQueryListCommand) {
        return dataCompanyAnnualReportShareholderQueryCommandExecutor.execute(dataCompanyAnnualReportShareholderQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyAnnualReportShareholderExWarehouseVO> exWarehouse(DataCompanyAnnualReportShareholderExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyAnnualReportShareholderExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyAnnualReportShareholderQueryCommandExecutor(DataCompanyAnnualReportShareholderQueryCommandExecutor dataCompanyAnnualReportShareholderQueryCommandExecutor) {
        this.dataCompanyAnnualReportShareholderQueryCommandExecutor = dataCompanyAnnualReportShareholderQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportShareholderExWarehouseCommandExecutor(DataCompanyAnnualReportShareholderExWarehouseCommandExecutor dataCompanyAnnualReportShareholderExWarehouseCommandExecutor) {
        this.dataCompanyAnnualReportShareholderExWarehouseCommandExecutor = dataCompanyAnnualReportShareholderExWarehouseCommandExecutor;
    }

}
