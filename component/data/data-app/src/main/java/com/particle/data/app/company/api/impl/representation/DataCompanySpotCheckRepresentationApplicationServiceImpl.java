package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanySpotCheckQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanySpotCheckRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanySpotCheckPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanySpotCheckQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanySpotCheckVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanySpotCheckExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySpotCheckExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanySpotCheckExWarehouseCommandExecutor;
/**
 * <p>
 * 企业抽查检查 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:39
 */
@Service
@CatchAndLog
public class DataCompanySpotCheckRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanySpotCheckRepresentationApplicationService {

    private DataCompanySpotCheckQueryCommandExecutor dataCompanySpotCheckQueryCommandExecutor;
    private DataCompanySpotCheckExWarehouseCommandExecutor dataCompanySpotCheckExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanySpotCheckVO> queryDetail(IdCommand detailCommand) {
        return dataCompanySpotCheckQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanySpotCheckVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanySpotCheckQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanySpotCheckVO> pageQuery(DataCompanySpotCheckPageQueryCommand dataCompanySpotCheckPageQueryCommand) {
        return dataCompanySpotCheckQueryCommandExecutor.execute(dataCompanySpotCheckPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanySpotCheckVO> queryList(DataCompanySpotCheckQueryListCommand dataCompanySpotCheckQueryListCommand) {
        return dataCompanySpotCheckQueryCommandExecutor.execute(dataCompanySpotCheckQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanySpotCheckExWarehouseVO> exWarehouse(DataCompanySpotCheckExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanySpotCheckExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanySpotCheckQueryCommandExecutor(DataCompanySpotCheckQueryCommandExecutor dataCompanySpotCheckQueryCommandExecutor) {
        this.dataCompanySpotCheckQueryCommandExecutor = dataCompanySpotCheckQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanySpotCheckExWarehouseCommandExecutor(DataCompanySpotCheckExWarehouseCommandExecutor dataCompanySpotCheckExWarehouseCommandExecutor) {
        this.dataCompanySpotCheckExWarehouseCommandExecutor = dataCompanySpotCheckExWarehouseCommandExecutor;
    }
}
