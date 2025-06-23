package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprTrademarkQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprTrademarkRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprTrademarkExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权商标 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:14:45
 */
@Service
@CatchAndLog
public class DataCompanyIprTrademarkRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprTrademarkRepresentationApplicationService {

    private DataCompanyIprTrademarkQueryCommandExecutor dataCompanyIprTrademarkQueryCommandExecutor;
    private DataCompanyIprTrademarkExWarehouseCommandExecutor dataCompanyIprTrademarkExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprTrademarkVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprTrademarkQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprTrademarkVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprTrademarkQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprTrademarkVO> pageQuery(DataCompanyIprTrademarkPageQueryCommand dataCompanyIprTrademarkPageQueryCommand) {
        return dataCompanyIprTrademarkQueryCommandExecutor.execute(dataCompanyIprTrademarkPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprTrademarkVO> queryList(DataCompanyIprTrademarkQueryListCommand dataCompanyIprTrademarkQueryListCommand) {
        return dataCompanyIprTrademarkQueryCommandExecutor.execute(dataCompanyIprTrademarkQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprTrademarkExWarehouseVO> exWarehouse(DataCompanyIprTrademarkExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprTrademarkExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprTrademarkQueryCommandExecutor(DataCompanyIprTrademarkQueryCommandExecutor dataCompanyIprTrademarkQueryCommandExecutor) {
        this.dataCompanyIprTrademarkQueryCommandExecutor = dataCompanyIprTrademarkQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkExWarehouseCommandExecutor(DataCompanyIprTrademarkExWarehouseCommandExecutor dataCompanyIprTrademarkExWarehouseCommandExecutor) {
        this.dataCompanyIprTrademarkExWarehouseCommandExecutor = dataCompanyIprTrademarkExWarehouseCommandExecutor;
    }
}
