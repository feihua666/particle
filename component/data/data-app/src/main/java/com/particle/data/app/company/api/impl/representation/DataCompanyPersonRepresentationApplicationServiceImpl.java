package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyPersonQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyPersonRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyPersonPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyPersonQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyPersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyPersonExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPersonExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyPersonExWarehouseCommandExecutor;
/**
 * <p>
 * 企业个人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:50
 */
@Service
@CatchAndLog
public class DataCompanyPersonRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyPersonRepresentationApplicationService {

    private DataCompanyPersonQueryCommandExecutor dataCompanyPersonQueryCommandExecutor;
    private DataCompanyPersonExWarehouseCommandExecutor dataCompanyPersonExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyPersonVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyPersonQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyPersonVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyPersonQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyPersonVO> pageQuery(DataCompanyPersonPageQueryCommand dataCompanyPersonPageQueryCommand) {
        return dataCompanyPersonQueryCommandExecutor.execute(dataCompanyPersonPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyPersonVO> queryList(DataCompanyPersonQueryListCommand dataCompanyPersonQueryListCommand) {
        return dataCompanyPersonQueryCommandExecutor.execute(dataCompanyPersonQueryListCommand);
    }


    @Override
    public SingleResponse<DataCompanyPersonExWarehouseVO> exWarehouse(DataCompanyPersonExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyPersonExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyPersonQueryCommandExecutor(DataCompanyPersonQueryCommandExecutor dataCompanyPersonQueryCommandExecutor) {
        this.dataCompanyPersonQueryCommandExecutor = dataCompanyPersonQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyPersonExWarehouseCommandExecutor(DataCompanyPersonExWarehouseCommandExecutor dataCompanyPersonExWarehouseCommandExecutor) {
        this.dataCompanyPersonExWarehouseCommandExecutor = dataCompanyPersonExWarehouseCommandExecutor;
    }
}