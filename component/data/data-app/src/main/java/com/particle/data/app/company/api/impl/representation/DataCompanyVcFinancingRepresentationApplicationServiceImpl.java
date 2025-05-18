package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyVcFinancingQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyVcFinancingRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcFinancingPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcFinancingQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcFinancingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyVcFinancingExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcFinancingExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyVcFinancingExWarehouseCommandExecutor;
/**
 * <p>
 * 企业融资 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:43
 */
@Service
@CatchAndLog
public class DataCompanyVcFinancingRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyVcFinancingRepresentationApplicationService {

    private DataCompanyVcFinancingQueryCommandExecutor dataCompanyVcFinancingQueryCommandExecutor;
    private DataCompanyVcFinancingExWarehouseCommandExecutor dataCompanyVcFinancingExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyVcFinancingVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyVcFinancingQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyVcFinancingVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyVcFinancingQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyVcFinancingVO> pageQuery(DataCompanyVcFinancingPageQueryCommand dataCompanyVcFinancingPageQueryCommand) {
        return dataCompanyVcFinancingQueryCommandExecutor.execute(dataCompanyVcFinancingPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyVcFinancingVO> queryList(DataCompanyVcFinancingQueryListCommand dataCompanyVcFinancingQueryListCommand) {
        return dataCompanyVcFinancingQueryCommandExecutor.execute(dataCompanyVcFinancingQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyVcFinancingExWarehouseVO> exWarehouse(DataCompanyVcFinancingExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyVcFinancingExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyVcFinancingQueryCommandExecutor(DataCompanyVcFinancingQueryCommandExecutor dataCompanyVcFinancingQueryCommandExecutor) {
        this.dataCompanyVcFinancingQueryCommandExecutor = dataCompanyVcFinancingQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcFinancingExWarehouseCommandExecutor(DataCompanyVcFinancingExWarehouseCommandExecutor dataCompanyVcFinancingExWarehouseCommandExecutor) {
        this.dataCompanyVcFinancingExWarehouseCommandExecutor = dataCompanyVcFinancingExWarehouseCommandExecutor;
    }
}
