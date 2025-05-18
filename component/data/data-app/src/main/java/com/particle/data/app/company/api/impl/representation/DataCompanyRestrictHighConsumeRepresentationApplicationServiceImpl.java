package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyRestrictHighConsumeQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyRestrictHighConsumeRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyRestrictHighConsumePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyRestrictHighConsumeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyRestrictHighConsumeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyRestrictHighConsumeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyRestrictHighConsumeExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyRestrictHighConsumeExWarehouseCommandExecutor;
/**
 * <p>
 * 企业限制高消费 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:19
 */
@Service
@CatchAndLog
public class DataCompanyRestrictHighConsumeRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyRestrictHighConsumeRepresentationApplicationService {

    private DataCompanyRestrictHighConsumeQueryCommandExecutor dataCompanyRestrictHighConsumeQueryCommandExecutor;
    private DataCompanyRestrictHighConsumeExWarehouseCommandExecutor dataCompanyRestrictHighConsumeExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyRestrictHighConsumeVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyRestrictHighConsumeQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyRestrictHighConsumeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyRestrictHighConsumeQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyRestrictHighConsumeVO> pageQuery(DataCompanyRestrictHighConsumePageQueryCommand dataCompanyRestrictHighConsumePageQueryCommand) {
        return dataCompanyRestrictHighConsumeQueryCommandExecutor.execute(dataCompanyRestrictHighConsumePageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyRestrictHighConsumeVO> queryList(DataCompanyRestrictHighConsumeQueryListCommand dataCompanyRestrictHighConsumeQueryListCommand) {
        return dataCompanyRestrictHighConsumeQueryCommandExecutor.execute(dataCompanyRestrictHighConsumeQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyRestrictHighConsumeExWarehouseVO> exWarehouse(DataCompanyRestrictHighConsumeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyRestrictHighConsumeExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyRestrictHighConsumeQueryCommandExecutor(DataCompanyRestrictHighConsumeQueryCommandExecutor dataCompanyRestrictHighConsumeQueryCommandExecutor) {
        this.dataCompanyRestrictHighConsumeQueryCommandExecutor = dataCompanyRestrictHighConsumeQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyRestrictHighConsumeExWarehouseCommandExecutor(DataCompanyRestrictHighConsumeExWarehouseCommandExecutor dataCompanyRestrictHighConsumeExWarehouseCommandExecutor) {
        this.dataCompanyRestrictHighConsumeExWarehouseCommandExecutor = dataCompanyRestrictHighConsumeExWarehouseCommandExecutor;
    }
}
