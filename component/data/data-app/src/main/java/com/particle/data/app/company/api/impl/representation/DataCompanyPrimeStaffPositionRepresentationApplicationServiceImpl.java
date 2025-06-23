package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyPrimeStaffPositionQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyPrimeStaffPositionRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyPrimeStaffPositionPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyPrimeStaffPositionQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyPrimeStaffPositionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyPrimeStaffPositionExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPrimeStaffPositionExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyPrimeStaffPositionExWarehouseCommandExecutor;
/**
 * <p>
 * 企业主要人员职位 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-22 15:07:33
 */
@Service
@CatchAndLog
public class DataCompanyPrimeStaffPositionRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyPrimeStaffPositionRepresentationApplicationService {

    private DataCompanyPrimeStaffPositionQueryCommandExecutor dataCompanyPrimeStaffPositionQueryCommandExecutor;
    private DataCompanyPrimeStaffPositionExWarehouseCommandExecutor dataCompanyPrimeStaffPositionExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyPrimeStaffPositionVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyPrimeStaffPositionQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyPrimeStaffPositionVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyPrimeStaffPositionQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyPrimeStaffPositionVO> pageQuery(DataCompanyPrimeStaffPositionPageQueryCommand dataCompanyPrimeStaffPositionPageQueryCommand) {
        return dataCompanyPrimeStaffPositionQueryCommandExecutor.execute(dataCompanyPrimeStaffPositionPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyPrimeStaffPositionVO> queryList(DataCompanyPrimeStaffPositionQueryListCommand dataCompanyPrimeStaffPositionQueryListCommand) {
        return dataCompanyPrimeStaffPositionQueryCommandExecutor.execute(dataCompanyPrimeStaffPositionQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyPrimeStaffPositionExWarehouseVO> exWarehouse(DataCompanyPrimeStaffPositionExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyPrimeStaffPositionExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyPrimeStaffPositionQueryCommandExecutor(DataCompanyPrimeStaffPositionQueryCommandExecutor dataCompanyPrimeStaffPositionQueryCommandExecutor) {
        this.dataCompanyPrimeStaffPositionQueryCommandExecutor = dataCompanyPrimeStaffPositionQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyPrimeStaffPositionExWarehouseCommandExecutor(DataCompanyPrimeStaffPositionExWarehouseCommandExecutor dataCompanyPrimeStaffPositionExWarehouseCommandExecutor) {
        this.dataCompanyPrimeStaffPositionExWarehouseCommandExecutor = dataCompanyPrimeStaffPositionExWarehouseCommandExecutor;
    }
}
