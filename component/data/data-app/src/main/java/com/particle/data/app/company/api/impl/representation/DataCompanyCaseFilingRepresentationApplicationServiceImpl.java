package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyCaseFilingQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyCaseFilingRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyCaseFilingPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCaseFilingQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyCaseFilingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyCaseFilingExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCaseFilingExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyCaseFilingExWarehouseCommandExecutor;
/**
 * <p>
 * 企业立案信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:36
 */
@Service
@CatchAndLog
public class DataCompanyCaseFilingRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyCaseFilingRepresentationApplicationService {

    private DataCompanyCaseFilingQueryCommandExecutor dataCompanyCaseFilingQueryCommandExecutor;
    private DataCompanyCaseFilingExWarehouseCommandExecutor dataCompanyCaseFilingExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyCaseFilingVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyCaseFilingQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyCaseFilingVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyCaseFilingQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyCaseFilingVO> pageQuery(DataCompanyCaseFilingPageQueryCommand dataCompanyCaseFilingPageQueryCommand) {
        return dataCompanyCaseFilingQueryCommandExecutor.execute(dataCompanyCaseFilingPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyCaseFilingVO> queryList(DataCompanyCaseFilingQueryListCommand dataCompanyCaseFilingQueryListCommand) {
        return dataCompanyCaseFilingQueryCommandExecutor.execute(dataCompanyCaseFilingQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyCaseFilingExWarehouseVO> exWarehouse(DataCompanyCaseFilingExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyCaseFilingExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyCaseFilingQueryCommandExecutor(DataCompanyCaseFilingQueryCommandExecutor dataCompanyCaseFilingQueryCommandExecutor) {
        this.dataCompanyCaseFilingQueryCommandExecutor = dataCompanyCaseFilingQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCaseFilingExWarehouseCommandExecutor(DataCompanyCaseFilingExWarehouseCommandExecutor dataCompanyCaseFilingExWarehouseCommandExecutor) {
        this.dataCompanyCaseFilingExWarehouseCommandExecutor = dataCompanyCaseFilingExWarehouseCommandExecutor;
    }
}
