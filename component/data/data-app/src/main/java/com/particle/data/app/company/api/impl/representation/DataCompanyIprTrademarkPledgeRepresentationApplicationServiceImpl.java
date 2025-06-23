package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprTrademarkPledgeQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprTrademarkPledgeRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPledgePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPledgeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkPledgeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkPledgeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkPledgeExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprTrademarkPledgeExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权商标质押信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:53
 */
@Service
@CatchAndLog
public class DataCompanyIprTrademarkPledgeRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprTrademarkPledgeRepresentationApplicationService {

    private DataCompanyIprTrademarkPledgeQueryCommandExecutor dataCompanyIprTrademarkPledgeQueryCommandExecutor;
    private DataCompanyIprTrademarkPledgeExWarehouseCommandExecutor dataCompanyIprTrademarkPledgeExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprTrademarkPledgeVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprTrademarkPledgeQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprTrademarkPledgeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprTrademarkPledgeQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprTrademarkPledgeVO> pageQuery(DataCompanyIprTrademarkPledgePageQueryCommand dataCompanyIprTrademarkPledgePageQueryCommand) {
        return dataCompanyIprTrademarkPledgeQueryCommandExecutor.execute(dataCompanyIprTrademarkPledgePageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprTrademarkPledgeVO> queryList(DataCompanyIprTrademarkPledgeQueryListCommand dataCompanyIprTrademarkPledgeQueryListCommand) {
        return dataCompanyIprTrademarkPledgeQueryCommandExecutor.execute(dataCompanyIprTrademarkPledgeQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprTrademarkPledgeExWarehouseVO> exWarehouse(DataCompanyIprTrademarkPledgeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprTrademarkPledgeExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprTrademarkPledgeQueryCommandExecutor(DataCompanyIprTrademarkPledgeQueryCommandExecutor dataCompanyIprTrademarkPledgeQueryCommandExecutor) {
        this.dataCompanyIprTrademarkPledgeQueryCommandExecutor = dataCompanyIprTrademarkPledgeQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkPledgeExWarehouseCommandExecutor(DataCompanyIprTrademarkPledgeExWarehouseCommandExecutor dataCompanyIprTrademarkPledgeExWarehouseCommandExecutor) {
        this.dataCompanyIprTrademarkPledgeExWarehouseCommandExecutor = dataCompanyIprTrademarkPledgeExWarehouseCommandExecutor;
    }
}
