package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprIntegratedCircuitQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprIntegratedCircuitRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprIntegratedCircuitPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprIntegratedCircuitQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprIntegratedCircuitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprIntegratedCircuitExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprIntegratedCircuitExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprIntegratedCircuitExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权集成电路 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:28
 */
@Service
@CatchAndLog
public class DataCompanyIprIntegratedCircuitRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprIntegratedCircuitRepresentationApplicationService {

    private DataCompanyIprIntegratedCircuitQueryCommandExecutor dataCompanyIprIntegratedCircuitQueryCommandExecutor;
    private DataCompanyIprIntegratedCircuitExWarehouseCommandExecutor dataCompanyIprIntegratedCircuitExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprIntegratedCircuitVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprIntegratedCircuitQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprIntegratedCircuitVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprIntegratedCircuitQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprIntegratedCircuitVO> pageQuery(DataCompanyIprIntegratedCircuitPageQueryCommand dataCompanyIprIntegratedCircuitPageQueryCommand) {
        return dataCompanyIprIntegratedCircuitQueryCommandExecutor.execute(dataCompanyIprIntegratedCircuitPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprIntegratedCircuitVO> queryList(DataCompanyIprIntegratedCircuitQueryListCommand dataCompanyIprIntegratedCircuitQueryListCommand) {
        return dataCompanyIprIntegratedCircuitQueryCommandExecutor.execute(dataCompanyIprIntegratedCircuitQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprIntegratedCircuitExWarehouseVO> exWarehouse(DataCompanyIprIntegratedCircuitExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprIntegratedCircuitExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprIntegratedCircuitQueryCommandExecutor(DataCompanyIprIntegratedCircuitQueryCommandExecutor dataCompanyIprIntegratedCircuitQueryCommandExecutor) {
        this.dataCompanyIprIntegratedCircuitQueryCommandExecutor = dataCompanyIprIntegratedCircuitQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprIntegratedCircuitExWarehouseCommandExecutor(DataCompanyIprIntegratedCircuitExWarehouseCommandExecutor dataCompanyIprIntegratedCircuitExWarehouseCommandExecutor) {
        this.dataCompanyIprIntegratedCircuitExWarehouseCommandExecutor = dataCompanyIprIntegratedCircuitExWarehouseCommandExecutor;
    }
}
