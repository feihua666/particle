package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyPrimeStaffQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyPrimeStaffRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyPrimeStaffPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyPrimeStaffQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyPrimeStaffVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyPrimeStaffExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPrimeStaffExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyPrimeStaffExWarehouseCommandExecutor;
/**
 * <p>
 * 企业主要人员 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:44
 */
@Service
@CatchAndLog
public class DataCompanyPrimeStaffRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyPrimeStaffRepresentationApplicationService {

    private DataCompanyPrimeStaffQueryCommandExecutor dataCompanyPrimeStaffQueryCommandExecutor;
    private DataCompanyPrimeStaffExWarehouseCommandExecutor dataCompanyPrimeStaffExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyPrimeStaffVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyPrimeStaffQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyPrimeStaffVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyPrimeStaffQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyPrimeStaffVO> pageQuery(DataCompanyPrimeStaffPageQueryCommand dataCompanyPrimeStaffPageQueryCommand) {
        return dataCompanyPrimeStaffQueryCommandExecutor.execute(dataCompanyPrimeStaffPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyPrimeStaffVO> queryList(DataCompanyPrimeStaffQueryListCommand dataCompanyPrimeStaffQueryListCommand) {
        return dataCompanyPrimeStaffQueryCommandExecutor.execute(dataCompanyPrimeStaffQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyPrimeStaffExWarehouseVO> exWarehouse(DataCompanyPrimeStaffExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyPrimeStaffExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyPrimeStaffQueryCommandExecutor(DataCompanyPrimeStaffQueryCommandExecutor dataCompanyPrimeStaffQueryCommandExecutor) {
        this.dataCompanyPrimeStaffQueryCommandExecutor = dataCompanyPrimeStaffQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyPrimeStaffExWarehouseCommandExecutor(DataCompanyPrimeStaffExWarehouseCommandExecutor dataCompanyPrimeStaffExWarehouseCommandExecutor) {
        this.dataCompanyPrimeStaffExWarehouseCommandExecutor = dataCompanyPrimeStaffExWarehouseCommandExecutor;
    }
}
