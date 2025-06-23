package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprPledgeQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprPledgeRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPledgePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPledgeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPledgeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPledgeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPledgeExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPledgeExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权出质 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:21
 */
@Service
@CatchAndLog
public class DataCompanyIprPledgeRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPledgeRepresentationApplicationService {

    private DataCompanyIprPledgeQueryCommandExecutor dataCompanyIprPledgeQueryCommandExecutor;
    private DataCompanyIprPledgeExWarehouseCommandExecutor dataCompanyIprPledgeExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPledgeVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprPledgeQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPledgeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprPledgeQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprPledgeVO> pageQuery(DataCompanyIprPledgePageQueryCommand dataCompanyIprPledgePageQueryCommand) {
        return dataCompanyIprPledgeQueryCommandExecutor.execute(dataCompanyIprPledgePageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprPledgeVO> queryList(DataCompanyIprPledgeQueryListCommand dataCompanyIprPledgeQueryListCommand) {
        return dataCompanyIprPledgeQueryCommandExecutor.execute(dataCompanyIprPledgeQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprPledgeExWarehouseVO> exWarehouse(DataCompanyIprPledgeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprPledgeExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprPledgeQueryCommandExecutor(DataCompanyIprPledgeQueryCommandExecutor dataCompanyIprPledgeQueryCommandExecutor) {
        this.dataCompanyIprPledgeQueryCommandExecutor = dataCompanyIprPledgeQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPledgeExWarehouseCommandExecutor(DataCompanyIprPledgeExWarehouseCommandExecutor dataCompanyIprPledgeExWarehouseCommandExecutor) {
        this.dataCompanyIprPledgeExWarehouseCommandExecutor = dataCompanyIprPledgeExWarehouseCommandExecutor;
    }
}
