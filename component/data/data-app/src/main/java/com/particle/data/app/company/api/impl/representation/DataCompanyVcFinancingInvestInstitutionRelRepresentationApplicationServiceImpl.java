package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyVcFinancingInvestInstitutionRelQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyVcFinancingInvestInstitutionRelRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcFinancingInvestInstitutionRelPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcFinancingInvestInstitutionRelQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcFinancingInvestInstitutionRelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyVcFinancingInvestInstitutionRelExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcFinancingInvestInstitutionRelExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyVcFinancingInvestInstitutionRelExWarehouseCommandExecutor;
/**
 * <p>
 * 企业融资历史投资机构关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:28
 */
@Service
@CatchAndLog
public class DataCompanyVcFinancingInvestInstitutionRelRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyVcFinancingInvestInstitutionRelRepresentationApplicationService {

    private DataCompanyVcFinancingInvestInstitutionRelQueryCommandExecutor dataCompanyVcFinancingInvestInstitutionRelQueryCommandExecutor;
    private DataCompanyVcFinancingInvestInstitutionRelExWarehouseCommandExecutor dataCompanyVcFinancingInvestInstitutionRelExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyVcFinancingInvestInstitutionRelVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyVcFinancingInvestInstitutionRelQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyVcFinancingInvestInstitutionRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyVcFinancingInvestInstitutionRelQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyVcFinancingInvestInstitutionRelVO> pageQuery(DataCompanyVcFinancingInvestInstitutionRelPageQueryCommand dataCompanyVcFinancingInvestInstitutionRelPageQueryCommand) {
        return dataCompanyVcFinancingInvestInstitutionRelQueryCommandExecutor.execute(dataCompanyVcFinancingInvestInstitutionRelPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyVcFinancingInvestInstitutionRelVO> queryList(DataCompanyVcFinancingInvestInstitutionRelQueryListCommand dataCompanyVcFinancingInvestInstitutionRelQueryListCommand) {
        return dataCompanyVcFinancingInvestInstitutionRelQueryCommandExecutor.execute(dataCompanyVcFinancingInvestInstitutionRelQueryListCommand);
    }

	@Override
	public MultiResponse<Long> queryCompanyVcInvestInstitutionIdsByCompanyVcFinancingId(IdCommand companyVcFinancingIdCommand) {

		return dataCompanyVcFinancingInvestInstitutionRelQueryCommandExecutor.queryCompanyVcInvestInstitutionIdsByCompanyVcFinancingId(companyVcFinancingIdCommand);
	}

	@Override
	public MultiResponse<Long> queryCompanyVcFinancingIdsByCompanyVcInvestInstitutionId(IdCommand companyVcInvestInstitutionIdCommand) {
		return dataCompanyVcFinancingInvestInstitutionRelQueryCommandExecutor.queryCompanyVcFinancingIdsByCompanyVcInvestInstitutionId(companyVcInvestInstitutionIdCommand);
	}

    @Override
    public SingleResponse<DataCompanyVcFinancingInvestInstitutionRelExWarehouseVO> exWarehouse(DataCompanyVcFinancingInvestInstitutionRelExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyVcFinancingInvestInstitutionRelExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyVcFinancingInvestInstitutionRelQueryCommandExecutor(DataCompanyVcFinancingInvestInstitutionRelQueryCommandExecutor dataCompanyVcFinancingInvestInstitutionRelQueryCommandExecutor) {
        this.dataCompanyVcFinancingInvestInstitutionRelQueryCommandExecutor = dataCompanyVcFinancingInvestInstitutionRelQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcFinancingInvestInstitutionRelExWarehouseCommandExecutor(DataCompanyVcFinancingInvestInstitutionRelExWarehouseCommandExecutor dataCompanyVcFinancingInvestInstitutionRelExWarehouseCommandExecutor) {
        this.dataCompanyVcFinancingInvestInstitutionRelExWarehouseCommandExecutor = dataCompanyVcFinancingInvestInstitutionRelExWarehouseCommandExecutor;
    }
}