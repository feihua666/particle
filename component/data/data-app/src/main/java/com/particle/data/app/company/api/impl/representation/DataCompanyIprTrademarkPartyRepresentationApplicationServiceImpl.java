package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprTrademarkPartyQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprTrademarkPartyRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkPartyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkPartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkPartyExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprTrademarkPartyExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权商标当事人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:34
 */
@Service
@CatchAndLog
public class DataCompanyIprTrademarkPartyRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprTrademarkPartyRepresentationApplicationService {

    private DataCompanyIprTrademarkPartyQueryCommandExecutor dataCompanyIprTrademarkPartyQueryCommandExecutor;
    private DataCompanyIprTrademarkPartyExWarehouseCommandExecutor dataCompanyIprTrademarkPartyExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprTrademarkPartyVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprTrademarkPartyQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprTrademarkPartyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprTrademarkPartyQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprTrademarkPartyVO> pageQuery(DataCompanyIprTrademarkPartyPageQueryCommand dataCompanyIprTrademarkPartyPageQueryCommand) {
        return dataCompanyIprTrademarkPartyQueryCommandExecutor.execute(dataCompanyIprTrademarkPartyPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprTrademarkPartyVO> queryList(DataCompanyIprTrademarkPartyQueryListCommand dataCompanyIprTrademarkPartyQueryListCommand) {
        return dataCompanyIprTrademarkPartyQueryCommandExecutor.execute(dataCompanyIprTrademarkPartyQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprTrademarkPartyExWarehouseVO> exWarehouse(DataCompanyIprTrademarkPartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprTrademarkPartyExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprTrademarkPartyQueryCommandExecutor(DataCompanyIprTrademarkPartyQueryCommandExecutor dataCompanyIprTrademarkPartyQueryCommandExecutor) {
        this.dataCompanyIprTrademarkPartyQueryCommandExecutor = dataCompanyIprTrademarkPartyQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkPartyExWarehouseCommandExecutor(DataCompanyIprTrademarkPartyExWarehouseCommandExecutor dataCompanyIprTrademarkPartyExWarehouseCommandExecutor) {
        this.dataCompanyIprTrademarkPartyExWarehouseCommandExecutor = dataCompanyIprTrademarkPartyExWarehouseCommandExecutor;
    }
}
