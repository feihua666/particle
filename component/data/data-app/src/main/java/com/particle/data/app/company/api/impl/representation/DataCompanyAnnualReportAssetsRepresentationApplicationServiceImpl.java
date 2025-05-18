package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.company.executor.representation.DataCompanyAnnualReportAssetsQueryCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAnnualReportAssetsExWarehouseCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyAnnualReportAssetsRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportAssetsPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportAssetsQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportAssetsExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportAssetsVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportAssetsExWarehouseVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 企业资产状况信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:31
 */
@Service
@CatchAndLog
public class DataCompanyAnnualReportAssetsRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAnnualReportAssetsRepresentationApplicationService {

    private DataCompanyAnnualReportAssetsQueryCommandExecutor dataCompanyAnnualReportAssetsQueryCommandExecutor;
    private DataCompanyAnnualReportAssetsExWarehouseCommandExecutor dataCompanyAnnualReportAssetsExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAnnualReportAssetsVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyAnnualReportAssetsQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportAssetsVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyAnnualReportAssetsQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyAnnualReportAssetsVO> pageQuery(DataCompanyAnnualReportAssetsPageQueryCommand dataCompanyAnnualReportAssetsPageQueryCommand) {
        return dataCompanyAnnualReportAssetsQueryCommandExecutor.execute(dataCompanyAnnualReportAssetsPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyAnnualReportAssetsVO> queryList(DataCompanyAnnualReportAssetsQueryListCommand dataCompanyAnnualReportAssetsQueryListCommand) {
        return dataCompanyAnnualReportAssetsQueryCommandExecutor.execute(dataCompanyAnnualReportAssetsQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyAnnualReportAssetsExWarehouseVO> exWarehouse(DataCompanyAnnualReportAssetsExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyAnnualReportAssetsExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyAnnualReportAssetsQueryCommandExecutor(DataCompanyAnnualReportAssetsQueryCommandExecutor dataCompanyAnnualReportAssetsQueryCommandExecutor) {
        this.dataCompanyAnnualReportAssetsQueryCommandExecutor = dataCompanyAnnualReportAssetsQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportAssetsExWarehouseCommandExecutor(DataCompanyAnnualReportAssetsExWarehouseCommandExecutor dataCompanyAnnualReportAssetsExWarehouseCommandExecutor) {
        this.dataCompanyAnnualReportAssetsExWarehouseCommandExecutor = dataCompanyAnnualReportAssetsExWarehouseCommandExecutor;
    }
}
