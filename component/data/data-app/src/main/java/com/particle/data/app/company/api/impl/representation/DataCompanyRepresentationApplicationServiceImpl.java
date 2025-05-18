package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.company.executor.representation.DataCompanyQueryCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyExWarehouseCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyExWarehouseCandidateVO;
import com.particle.data.client.company.dto.data.DataCompanyVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 企业 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Service
@CatchAndLog
public class DataCompanyRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyRepresentationApplicationService {

    private DataCompanyQueryCommandExecutor dataCompanyQueryCommandExecutor;
    private DataCompanyExWarehouseCommandExecutor dataCompanyExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyVO> pageQuery(DataCompanyPageQueryCommand dataCompanyPageQueryCommand) {
        return dataCompanyQueryCommandExecutor.execute(dataCompanyPageQueryCommand);
    }

    @Override
    public SingleResponse<DataCompanyExWarehouseCandidateVO> exWarehouse(DataCompanyExWarehouseQueryCommand exWarehouseQueryCommand) {
        return dataCompanyExWarehouseCommandExecutor.exWarehouse(exWarehouseQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyVO> queryList(DataCompanyQueryListCommand dataCompanyQueryListCommand) {
        return dataCompanyQueryCommandExecutor.execute(dataCompanyQueryListCommand);
    }


    @Autowired
    public void setDataCompanyQueryCommandExecutor(DataCompanyQueryCommandExecutor dataCompanyQueryCommandExecutor) {
        this.dataCompanyQueryCommandExecutor = dataCompanyQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyExWarehouseCommandExecutor(DataCompanyExWarehouseCommandExecutor dataCompanyExWarehouseCommandExecutor) {
        this.dataCompanyExWarehouseCommandExecutor = dataCompanyExWarehouseCommandExecutor;
    }
}
