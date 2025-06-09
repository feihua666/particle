package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyStatisticQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyStatisticRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyStatisticPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyStatisticQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyStatisticVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyStatisticExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyStatisticExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyStatisticExWarehouseCommandExecutor;
/**
 * <p>
 * 企业统计 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-04 15:53:01
 */
@Service
@CatchAndLog
public class DataCompanyStatisticRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyStatisticRepresentationApplicationService {

    private DataCompanyStatisticQueryCommandExecutor dataCompanyStatisticQueryCommandExecutor;
    private DataCompanyStatisticExWarehouseCommandExecutor dataCompanyStatisticExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyStatisticVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyStatisticQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyStatisticVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyStatisticQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyStatisticVO> pageQuery(DataCompanyStatisticPageQueryCommand dataCompanyStatisticPageQueryCommand) {
        return dataCompanyStatisticQueryCommandExecutor.execute(dataCompanyStatisticPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyStatisticVO> queryList(DataCompanyStatisticQueryListCommand dataCompanyStatisticQueryListCommand) {
        return dataCompanyStatisticQueryCommandExecutor.execute(dataCompanyStatisticQueryListCommand);
    }


    @Override
    public SingleResponse<DataCompanyStatisticExWarehouseVO> exWarehouse(DataCompanyStatisticExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyStatisticExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyStatisticQueryCommandExecutor(DataCompanyStatisticQueryCommandExecutor dataCompanyStatisticQueryCommandExecutor) {
        this.dataCompanyStatisticQueryCommandExecutor = dataCompanyStatisticQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyStatisticExWarehouseCommandExecutor(DataCompanyStatisticExWarehouseCommandExecutor dataCompanyStatisticExWarehouseCommandExecutor) {
        this.dataCompanyStatisticExWarehouseCommandExecutor = dataCompanyStatisticExWarehouseCommandExecutor;
    }
}