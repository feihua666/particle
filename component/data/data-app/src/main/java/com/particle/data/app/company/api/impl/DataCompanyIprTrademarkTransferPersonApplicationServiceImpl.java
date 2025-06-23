package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprTrademarkTransferPersonCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkTransferPersonDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkTransferPersonUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkTransferPersonCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkTransferPersonUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprTrademarkTransferPersonApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkTransferPersonCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkTransferPersonVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkTransferPersonWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkTransferPersonExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprTrademarkTransferPersonWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权商标转让人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:13
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprTrademarkTransferPersonApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprTrademarkTransferPersonApplicationService {

    private DataCompanyIprTrademarkTransferPersonCreateCommandExecutor dataCompanyIprTrademarkTransferPersonCreateCommandExecutor;

    private DataCompanyIprTrademarkTransferPersonDeleteCommandExecutor dataCompanyIprTrademarkTransferPersonDeleteCommandExecutor;

    private DataCompanyIprTrademarkTransferPersonUpdateCommandExecutor dataCompanyIprTrademarkTransferPersonUpdateCommandExecutor;

    private DataCompanyIprTrademarkTransferPersonCommandExecutor dataCompanyIprTrademarkTransferPersonCommandExecutor;

    private DataCompanyIprTrademarkTransferPersonWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprTrademarkTransferPersonVO> create(DataCompanyIprTrademarkTransferPersonCreateCommand dataCompanyIprTrademarkTransferPersonCreateCommand) {
        return dataCompanyIprTrademarkTransferPersonCreateCommandExecutor.execute(dataCompanyIprTrademarkTransferPersonCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprTrademarkTransferPersonVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprTrademarkTransferPersonDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprTrademarkTransferPersonVO> update(DataCompanyIprTrademarkTransferPersonUpdateCommand dataCompanyIprTrademarkTransferPersonUpdateCommand) {
        return dataCompanyIprTrademarkTransferPersonUpdateCommandExecutor.execute(dataCompanyIprTrademarkTransferPersonUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprTrademarkTransferPersonExWarehouseVO> warehouse(DataCompanyIprTrademarkTransferPersonWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprTrademarkTransferPersonCreateCommandExecutor(DataCompanyIprTrademarkTransferPersonCreateCommandExecutor dataCompanyIprTrademarkTransferPersonCreateCommandExecutor) {
        this.dataCompanyIprTrademarkTransferPersonCreateCommandExecutor = dataCompanyIprTrademarkTransferPersonCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprTrademarkTransferPersonDeleteCommandExecutor(DataCompanyIprTrademarkTransferPersonDeleteCommandExecutor dataCompanyIprTrademarkTransferPersonDeleteCommandExecutor) {
        this.dataCompanyIprTrademarkTransferPersonDeleteCommandExecutor = dataCompanyIprTrademarkTransferPersonDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkTransferPersonUpdateCommandExecutor(DataCompanyIprTrademarkTransferPersonUpdateCommandExecutor dataCompanyIprTrademarkTransferPersonUpdateCommandExecutor) {
        this.dataCompanyIprTrademarkTransferPersonUpdateCommandExecutor = dataCompanyIprTrademarkTransferPersonUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkTransferPersonCommandExecutor(DataCompanyIprTrademarkTransferPersonCommandExecutor dataCompanyIprTrademarkTransferPersonCommandExecutor) {
        this.dataCompanyIprTrademarkTransferPersonCommandExecutor = dataCompanyIprTrademarkTransferPersonCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkTransferPersonWarehouseCommandExecutor(DataCompanyIprTrademarkTransferPersonWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}