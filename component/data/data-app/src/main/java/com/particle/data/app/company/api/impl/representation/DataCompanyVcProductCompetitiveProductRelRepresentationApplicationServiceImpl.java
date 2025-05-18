package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyVcProductCompetitiveProductRelQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyVcProductCompetitiveProductRelRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcProductCompetitiveProductRelPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcProductCompetitiveProductRelQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcProductCompetitiveProductRelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyVcProductCompetitiveProductRelExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcProductCompetitiveProductRelExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyVcProductCompetitiveProductRelExWarehouseCommandExecutor;
/**
 * <p>
 * 企业融资产品竞品关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:00
 */
@Service
@CatchAndLog
public class DataCompanyVcProductCompetitiveProductRelRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyVcProductCompetitiveProductRelRepresentationApplicationService {

    private DataCompanyVcProductCompetitiveProductRelQueryCommandExecutor dataCompanyVcProductCompetitiveProductRelQueryCommandExecutor;
    private DataCompanyVcProductCompetitiveProductRelExWarehouseCommandExecutor dataCompanyVcProductCompetitiveProductRelExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyVcProductCompetitiveProductRelVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyVcProductCompetitiveProductRelQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyVcProductCompetitiveProductRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyVcProductCompetitiveProductRelQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyVcProductCompetitiveProductRelVO> pageQuery(DataCompanyVcProductCompetitiveProductRelPageQueryCommand dataCompanyVcProductCompetitiveProductRelPageQueryCommand) {
        return dataCompanyVcProductCompetitiveProductRelQueryCommandExecutor.execute(dataCompanyVcProductCompetitiveProductRelPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyVcProductCompetitiveProductRelVO> queryList(DataCompanyVcProductCompetitiveProductRelQueryListCommand dataCompanyVcProductCompetitiveProductRelQueryListCommand) {
        return dataCompanyVcProductCompetitiveProductRelQueryCommandExecutor.execute(dataCompanyVcProductCompetitiveProductRelQueryListCommand);
    }

	@Override
	public MultiResponse<Long> queryCompanyVcCompetitiveProductIdsByCompanyVcProductId(IdCommand companyVcProductIdCommand) {

		return dataCompanyVcProductCompetitiveProductRelQueryCommandExecutor.queryCompanyVcCompetitiveProductIdsByCompanyVcProductId(companyVcProductIdCommand);
	}

	@Override
	public MultiResponse<Long> queryCompanyVcProductIdsByCompanyVcCompetitiveProductId(IdCommand companyVcCompetitiveProductIdCommand) {
		return dataCompanyVcProductCompetitiveProductRelQueryCommandExecutor.queryCompanyVcProductIdsByCompanyVcCompetitiveProductId(companyVcCompetitiveProductIdCommand);
	}

    @Override
    public SingleResponse<DataCompanyVcProductCompetitiveProductRelExWarehouseVO> exWarehouse(DataCompanyVcProductCompetitiveProductRelExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyVcProductCompetitiveProductRelExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyVcProductCompetitiveProductRelQueryCommandExecutor(DataCompanyVcProductCompetitiveProductRelQueryCommandExecutor dataCompanyVcProductCompetitiveProductRelQueryCommandExecutor) {
        this.dataCompanyVcProductCompetitiveProductRelQueryCommandExecutor = dataCompanyVcProductCompetitiveProductRelQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcProductCompetitiveProductRelExWarehouseCommandExecutor(DataCompanyVcProductCompetitiveProductRelExWarehouseCommandExecutor dataCompanyVcProductCompetitiveProductRelExWarehouseCommandExecutor) {
        this.dataCompanyVcProductCompetitiveProductRelExWarehouseCommandExecutor = dataCompanyVcProductCompetitiveProductRelExWarehouseCommandExecutor;
    }
}