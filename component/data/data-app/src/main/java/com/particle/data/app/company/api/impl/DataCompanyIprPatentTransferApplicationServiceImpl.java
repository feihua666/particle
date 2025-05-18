package com.particle.data.app.company.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.company.executor.DataCompanyIprPatentTransferCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentTransferCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentTransferDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentTransferUpdateCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprPatentTransferWarehouseCommandExecutor;
import com.particle.data.client.company.api.IDataCompanyIprPatentTransferApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentTransferCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentTransferUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentTransferWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentTransferVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentTransferExWarehouseVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 企业知识产权专利转让信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:51
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprPatentTransferApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentTransferApplicationService {

    private DataCompanyIprPatentTransferCreateCommandExecutor dataCompanyIprPatentTransferCreateCommandExecutor;

    private DataCompanyIprPatentTransferDeleteCommandExecutor dataCompanyIprPatentTransferDeleteCommandExecutor;

    private DataCompanyIprPatentTransferUpdateCommandExecutor dataCompanyIprPatentTransferUpdateCommandExecutor;

    private DataCompanyIprPatentTransferCommandExecutor dataCompanyIprPatentTransferCommandExecutor;

    private DataCompanyIprPatentTransferWarehouseCommandExecutor dataCompanyIprPatentTransferWarehouseCommandExecutor;
    private DataCompanyIprPatentTransferWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentTransferVO> create(DataCompanyIprPatentTransferCreateCommand dataCompanyIprPatentTransferCreateCommand) {
        return dataCompanyIprPatentTransferCreateCommandExecutor.execute(dataCompanyIprPatentTransferCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentTransferVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprPatentTransferDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentTransferVO> update(DataCompanyIprPatentTransferUpdateCommand dataCompanyIprPatentTransferUpdateCommand) {
        return dataCompanyIprPatentTransferUpdateCommandExecutor.execute(dataCompanyIprPatentTransferUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprPatentTransferExWarehouseVO> warehouse(DataCompanyIprPatentTransferWarehouseCommand dataCompanyIprPatentTransferWarehouseCommand) {
        return dataCompanyIprPatentTransferWarehouseCommandExecutor.warehouse(dataCompanyIprPatentTransferWarehouseCommand);
    }

    @Autowired
    public void setDataCompanyIprPatentTransferCreateCommandExecutor(DataCompanyIprPatentTransferCreateCommandExecutor dataCompanyIprPatentTransferCreateCommandExecutor) {
        this.dataCompanyIprPatentTransferCreateCommandExecutor = dataCompanyIprPatentTransferCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprPatentTransferDeleteCommandExecutor(DataCompanyIprPatentTransferDeleteCommandExecutor dataCompanyIprPatentTransferDeleteCommandExecutor) {
        this.dataCompanyIprPatentTransferDeleteCommandExecutor = dataCompanyIprPatentTransferDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentTransferUpdateCommandExecutor(DataCompanyIprPatentTransferUpdateCommandExecutor dataCompanyIprPatentTransferUpdateCommandExecutor) {
        this.dataCompanyIprPatentTransferUpdateCommandExecutor = dataCompanyIprPatentTransferUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentTransferCommandExecutor(DataCompanyIprPatentTransferCommandExecutor dataCompanyIprPatentTransferCommandExecutor) {
        this.dataCompanyIprPatentTransferCommandExecutor = dataCompanyIprPatentTransferCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentTransferWarehouseCommandExecutor(DataCompanyIprPatentTransferWarehouseCommandExecutor dataCompanyIprPatentTransferWarehouseCommandExecutor) {
        this.dataCompanyIprPatentTransferWarehouseCommandExecutor = dataCompanyIprPatentTransferWarehouseCommandExecutor;
    }

}
