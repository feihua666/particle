package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyEquityPledgeQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyEquityPledgeRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyEquityPledgePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyEquityPledgeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyEquityPledgeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyEquityPledgeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyEquityPledgeExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyEquityPledgeExWarehouseCommandExecutor;
/**
 * <p>
 * 企业股权出质 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:10
 */
@Service
@CatchAndLog
public class DataCompanyEquityPledgeRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyEquityPledgeRepresentationApplicationService {

    private DataCompanyEquityPledgeQueryCommandExecutor dataCompanyEquityPledgeQueryCommandExecutor;
    private DataCompanyEquityPledgeExWarehouseCommandExecutor dataCompanyEquityPledgeExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyEquityPledgeVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyEquityPledgeQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyEquityPledgeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyEquityPledgeQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyEquityPledgeVO> pageQuery(DataCompanyEquityPledgePageQueryCommand dataCompanyEquityPledgePageQueryCommand) {
        return dataCompanyEquityPledgeQueryCommandExecutor.execute(dataCompanyEquityPledgePageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyEquityPledgeVO> queryList(DataCompanyEquityPledgeQueryListCommand dataCompanyEquityPledgeQueryListCommand) {
        return dataCompanyEquityPledgeQueryCommandExecutor.execute(dataCompanyEquityPledgeQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyEquityPledgeExWarehouseVO> exWarehouse(DataCompanyEquityPledgeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyEquityPledgeExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyEquityPledgeQueryCommandExecutor(DataCompanyEquityPledgeQueryCommandExecutor dataCompanyEquityPledgeQueryCommandExecutor) {
        this.dataCompanyEquityPledgeQueryCommandExecutor = dataCompanyEquityPledgeQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyEquityPledgeExWarehouseCommandExecutor(DataCompanyEquityPledgeExWarehouseCommandExecutor dataCompanyEquityPledgeExWarehouseCommandExecutor) {
        this.dataCompanyEquityPledgeExWarehouseCommandExecutor = dataCompanyEquityPledgeExWarehouseCommandExecutor;
    }
}
