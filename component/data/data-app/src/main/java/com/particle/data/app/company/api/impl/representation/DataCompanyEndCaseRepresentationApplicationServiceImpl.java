package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyEndCaseQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyEndCaseRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyEndCasePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyEndCaseQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyEndCaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyEndCaseExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyEndCaseExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyEndCaseExWarehouseCommandExecutor;
/**
 * <p>
 * 企业终本案件 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:57
 */
@Service
@CatchAndLog
public class DataCompanyEndCaseRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyEndCaseRepresentationApplicationService {

    private DataCompanyEndCaseQueryCommandExecutor dataCompanyEndCaseQueryCommandExecutor;
    private DataCompanyEndCaseExWarehouseCommandExecutor dataCompanyEndCaseExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyEndCaseVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyEndCaseQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyEndCaseVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyEndCaseQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyEndCaseVO> pageQuery(DataCompanyEndCasePageQueryCommand dataCompanyEndCasePageQueryCommand) {
        return dataCompanyEndCaseQueryCommandExecutor.execute(dataCompanyEndCasePageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyEndCaseVO> queryList(DataCompanyEndCaseQueryListCommand dataCompanyEndCaseQueryListCommand) {
        return dataCompanyEndCaseQueryCommandExecutor.execute(dataCompanyEndCaseQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyEndCaseExWarehouseVO> exWarehouse(DataCompanyEndCaseExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyEndCaseExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyEndCaseQueryCommandExecutor(DataCompanyEndCaseQueryCommandExecutor dataCompanyEndCaseQueryCommandExecutor) {
        this.dataCompanyEndCaseQueryCommandExecutor = dataCompanyEndCaseQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyEndCaseExWarehouseCommandExecutor(DataCompanyEndCaseExWarehouseCommandExecutor dataCompanyEndCaseExWarehouseCommandExecutor) {
        this.dataCompanyEndCaseExWarehouseCommandExecutor = dataCompanyEndCaseExWarehouseCommandExecutor;
    }
}
