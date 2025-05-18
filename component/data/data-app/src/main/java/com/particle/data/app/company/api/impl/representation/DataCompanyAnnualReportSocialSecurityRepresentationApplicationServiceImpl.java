package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.company.executor.representation.DataCompanyAnnualReportSocialSecurityQueryCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyAnnualReportSocialSecurityRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportSocialSecurityPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportSocialSecurityQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportSocialSecurityExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportSocialSecurityVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportSocialSecurityExWarehouseVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 企业年报社保 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:03
 */
@Service
@CatchAndLog
public class DataCompanyAnnualReportSocialSecurityRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAnnualReportSocialSecurityRepresentationApplicationService {

    private DataCompanyAnnualReportSocialSecurityQueryCommandExecutor dataCompanyAnnualReportSocialSecurityQueryCommandExecutor;
    private DataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor dataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAnnualReportSocialSecurityVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyAnnualReportSocialSecurityQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportSocialSecurityVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyAnnualReportSocialSecurityQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyAnnualReportSocialSecurityVO> pageQuery(DataCompanyAnnualReportSocialSecurityPageQueryCommand dataCompanyAnnualReportSocialSecurityPageQueryCommand) {
        return dataCompanyAnnualReportSocialSecurityQueryCommandExecutor.execute(dataCompanyAnnualReportSocialSecurityPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyAnnualReportSocialSecurityVO> queryList(DataCompanyAnnualReportSocialSecurityQueryListCommand dataCompanyAnnualReportSocialSecurityQueryListCommand) {
        return dataCompanyAnnualReportSocialSecurityQueryCommandExecutor.execute(dataCompanyAnnualReportSocialSecurityQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyAnnualReportSocialSecurityExWarehouseVO> exWarehouse(DataCompanyAnnualReportSocialSecurityExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyAnnualReportSocialSecurityQueryCommandExecutor(DataCompanyAnnualReportSocialSecurityQueryCommandExecutor dataCompanyAnnualReportSocialSecurityQueryCommandExecutor) {
        this.dataCompanyAnnualReportSocialSecurityQueryCommandExecutor = dataCompanyAnnualReportSocialSecurityQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor(DataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor dataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor) {
        this.dataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor = dataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor;
    }

}
