package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanySeriousIllegalQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanySeriousIllegalRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanySeriousIllegalPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanySeriousIllegalQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanySeriousIllegalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanySeriousIllegalExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySeriousIllegalExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanySeriousIllegalExWarehouseCommandExecutor;
/**
 * <p>
 * 企业严重违法 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:45
 */
@Service
@CatchAndLog
public class DataCompanySeriousIllegalRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanySeriousIllegalRepresentationApplicationService {

    private DataCompanySeriousIllegalQueryCommandExecutor dataCompanySeriousIllegalQueryCommandExecutor;
    private DataCompanySeriousIllegalExWarehouseCommandExecutor dataCompanySeriousIllegalExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanySeriousIllegalVO> queryDetail(IdCommand detailCommand) {
        return dataCompanySeriousIllegalQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanySeriousIllegalVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanySeriousIllegalQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanySeriousIllegalVO> pageQuery(DataCompanySeriousIllegalPageQueryCommand dataCompanySeriousIllegalPageQueryCommand) {
        return dataCompanySeriousIllegalQueryCommandExecutor.execute(dataCompanySeriousIllegalPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanySeriousIllegalVO> queryList(DataCompanySeriousIllegalQueryListCommand dataCompanySeriousIllegalQueryListCommand) {
        return dataCompanySeriousIllegalQueryCommandExecutor.execute(dataCompanySeriousIllegalQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanySeriousIllegalExWarehouseVO> exWarehouse(DataCompanySeriousIllegalExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanySeriousIllegalExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanySeriousIllegalQueryCommandExecutor(DataCompanySeriousIllegalQueryCommandExecutor dataCompanySeriousIllegalQueryCommandExecutor) {
        this.dataCompanySeriousIllegalQueryCommandExecutor = dataCompanySeriousIllegalQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanySeriousIllegalExWarehouseCommandExecutor(DataCompanySeriousIllegalExWarehouseCommandExecutor dataCompanySeriousIllegalExWarehouseCommandExecutor) {
        this.dataCompanySeriousIllegalExWarehouseCommandExecutor = dataCompanySeriousIllegalExWarehouseCommandExecutor;
    }
}
