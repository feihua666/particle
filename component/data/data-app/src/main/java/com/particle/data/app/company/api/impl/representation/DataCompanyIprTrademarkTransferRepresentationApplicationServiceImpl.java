package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprTrademarkTransferQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprTrademarkTransferRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkTransferPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkTransferQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkTransferVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkTransferExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkTransferExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprTrademarkTransferExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权商标转让信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:04
 */
@Service
@CatchAndLog
public class DataCompanyIprTrademarkTransferRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprTrademarkTransferRepresentationApplicationService {

    private DataCompanyIprTrademarkTransferQueryCommandExecutor dataCompanyIprTrademarkTransferQueryCommandExecutor;
    private DataCompanyIprTrademarkTransferExWarehouseCommandExecutor dataCompanyIprTrademarkTransferExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprTrademarkTransferVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprTrademarkTransferQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprTrademarkTransferVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprTrademarkTransferQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprTrademarkTransferVO> pageQuery(DataCompanyIprTrademarkTransferPageQueryCommand dataCompanyIprTrademarkTransferPageQueryCommand) {
        return dataCompanyIprTrademarkTransferQueryCommandExecutor.execute(dataCompanyIprTrademarkTransferPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprTrademarkTransferVO> queryList(DataCompanyIprTrademarkTransferQueryListCommand dataCompanyIprTrademarkTransferQueryListCommand) {
        return dataCompanyIprTrademarkTransferQueryCommandExecutor.execute(dataCompanyIprTrademarkTransferQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprTrademarkTransferExWarehouseVO> exWarehouse(DataCompanyIprTrademarkTransferExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprTrademarkTransferExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprTrademarkTransferQueryCommandExecutor(DataCompanyIprTrademarkTransferQueryCommandExecutor dataCompanyIprTrademarkTransferQueryCommandExecutor) {
        this.dataCompanyIprTrademarkTransferQueryCommandExecutor = dataCompanyIprTrademarkTransferQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkTransferExWarehouseCommandExecutor(DataCompanyIprTrademarkTransferExWarehouseCommandExecutor dataCompanyIprTrademarkTransferExWarehouseCommandExecutor) {
        this.dataCompanyIprTrademarkTransferExWarehouseCommandExecutor = dataCompanyIprTrademarkTransferExWarehouseCommandExecutor;
    }
}
