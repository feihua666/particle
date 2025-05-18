package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyVcInvestInstitutionQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyVcInvestInstitutionRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcInvestInstitutionPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcInvestInstitutionQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcInvestInstitutionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyVcInvestInstitutionExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcInvestInstitutionExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyVcInvestInstitutionExWarehouseCommandExecutor;
/**
 * <p>
 * 企业投资机构 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:13
 */
@Service
@CatchAndLog
public class DataCompanyVcInvestInstitutionRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyVcInvestInstitutionRepresentationApplicationService {

    private DataCompanyVcInvestInstitutionQueryCommandExecutor dataCompanyVcInvestInstitutionQueryCommandExecutor;
    private DataCompanyVcInvestInstitutionExWarehouseCommandExecutor dataCompanyVcInvestInstitutionExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyVcInvestInstitutionVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyVcInvestInstitutionQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyVcInvestInstitutionVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyVcInvestInstitutionQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyVcInvestInstitutionVO> pageQuery(DataCompanyVcInvestInstitutionPageQueryCommand dataCompanyVcInvestInstitutionPageQueryCommand) {
        return dataCompanyVcInvestInstitutionQueryCommandExecutor.execute(dataCompanyVcInvestInstitutionPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyVcInvestInstitutionVO> queryList(DataCompanyVcInvestInstitutionQueryListCommand dataCompanyVcInvestInstitutionQueryListCommand) {
        return dataCompanyVcInvestInstitutionQueryCommandExecutor.execute(dataCompanyVcInvestInstitutionQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyVcInvestInstitutionExWarehouseVO> exWarehouse(DataCompanyVcInvestInstitutionExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyVcInvestInstitutionExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyVcInvestInstitutionQueryCommandExecutor(DataCompanyVcInvestInstitutionQueryCommandExecutor dataCompanyVcInvestInstitutionQueryCommandExecutor) {
        this.dataCompanyVcInvestInstitutionQueryCommandExecutor = dataCompanyVcInvestInstitutionQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcInvestInstitutionExWarehouseCommandExecutor(DataCompanyVcInvestInstitutionExWarehouseCommandExecutor dataCompanyVcInvestInstitutionExWarehouseCommandExecutor) {
        this.dataCompanyVcInvestInstitutionExWarehouseCommandExecutor = dataCompanyVcInvestInstitutionExWarehouseCommandExecutor;
    }
}
