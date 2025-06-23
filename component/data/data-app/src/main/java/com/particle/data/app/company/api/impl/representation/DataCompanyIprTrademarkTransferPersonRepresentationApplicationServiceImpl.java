package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprTrademarkTransferPersonQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprTrademarkTransferPersonRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkTransferPersonPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkTransferPersonQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkTransferPersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkTransferPersonExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkTransferPersonExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprTrademarkTransferPersonExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权商标转让人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:13
 */
@Service
@CatchAndLog
public class DataCompanyIprTrademarkTransferPersonRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprTrademarkTransferPersonRepresentationApplicationService {

    private DataCompanyIprTrademarkTransferPersonQueryCommandExecutor dataCompanyIprTrademarkTransferPersonQueryCommandExecutor;
    private DataCompanyIprTrademarkTransferPersonExWarehouseCommandExecutor dataCompanyIprTrademarkTransferPersonExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprTrademarkTransferPersonVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprTrademarkTransferPersonQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprTrademarkTransferPersonVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprTrademarkTransferPersonQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprTrademarkTransferPersonVO> pageQuery(DataCompanyIprTrademarkTransferPersonPageQueryCommand dataCompanyIprTrademarkTransferPersonPageQueryCommand) {
        return dataCompanyIprTrademarkTransferPersonQueryCommandExecutor.execute(dataCompanyIprTrademarkTransferPersonPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprTrademarkTransferPersonVO> queryList(DataCompanyIprTrademarkTransferPersonQueryListCommand dataCompanyIprTrademarkTransferPersonQueryListCommand) {
        return dataCompanyIprTrademarkTransferPersonQueryCommandExecutor.execute(dataCompanyIprTrademarkTransferPersonQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprTrademarkTransferPersonExWarehouseVO> exWarehouse(DataCompanyIprTrademarkTransferPersonExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprTrademarkTransferPersonExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprTrademarkTransferPersonQueryCommandExecutor(DataCompanyIprTrademarkTransferPersonQueryCommandExecutor dataCompanyIprTrademarkTransferPersonQueryCommandExecutor) {
        this.dataCompanyIprTrademarkTransferPersonQueryCommandExecutor = dataCompanyIprTrademarkTransferPersonQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkTransferPersonExWarehouseCommandExecutor(DataCompanyIprTrademarkTransferPersonExWarehouseCommandExecutor dataCompanyIprTrademarkTransferPersonExWarehouseCommandExecutor) {
        this.dataCompanyIprTrademarkTransferPersonExWarehouseCommandExecutor = dataCompanyIprTrademarkTransferPersonExWarehouseCommandExecutor;
    }
}
