package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.company.executor.representation.DataCompanyShareholderQueryCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyShareholderExWarehouseCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyShareholderRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyShareholderPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyShareholderQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyShareholderExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.DataCompanyShareholderVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyShareholderExWarehouseVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 企业股东 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:01
 */
@Service
@CatchAndLog
public class DataCompanyShareholderRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyShareholderRepresentationApplicationService {

    private DataCompanyShareholderQueryCommandExecutor dataCompanyShareholderQueryCommandExecutor;
    private DataCompanyShareholderExWarehouseCommandExecutor dataCompanyShareholderExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyShareholderVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyShareholderQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyShareholderVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyShareholderQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyShareholderVO> pageQuery(DataCompanyShareholderPageQueryCommand dataCompanyShareholderPageQueryCommand) {
        return dataCompanyShareholderQueryCommandExecutor.execute(dataCompanyShareholderPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyShareholderVO> queryList(DataCompanyShareholderQueryListCommand dataCompanyShareholderQueryListCommand) {
        return dataCompanyShareholderQueryCommandExecutor.execute(dataCompanyShareholderQueryListCommand);
    }

    @Override
    public PageResponse<DataCompanyShareholderExWarehouseVO> exWarehouse(DataCompanyShareholderExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyShareholderExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyShareholderQueryCommandExecutor(DataCompanyShareholderQueryCommandExecutor dataCompanyShareholderQueryCommandExecutor) {
        this.dataCompanyShareholderQueryCommandExecutor = dataCompanyShareholderQueryCommandExecutor;
    }

    @Autowired
    public void setDataCompanyShareholderExWarehouseCommandExecutor(DataCompanyShareholderExWarehouseCommandExecutor dataCompanyShareholderExWarehouseCommandExecutor) {
        this.dataCompanyShareholderExWarehouseCommandExecutor = dataCompanyShareholderExWarehouseCommandExecutor;
    }
}
