package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyVcProductCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyVcProductDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyVcProductUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyVcProductCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyVcProductUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyVcProductApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyVcProductCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcProductVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcProductWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcProductExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyVcProductWarehouseCommandExecutor;
/**
 * <p>
 * 企业融资产品 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:14
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyVcProductApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyVcProductApplicationService {

    private DataCompanyVcProductCreateCommandExecutor dataCompanyVcProductCreateCommandExecutor;

    private DataCompanyVcProductDeleteCommandExecutor dataCompanyVcProductDeleteCommandExecutor;

    private DataCompanyVcProductUpdateCommandExecutor dataCompanyVcProductUpdateCommandExecutor;

    private DataCompanyVcProductCommandExecutor dataCompanyVcProductCommandExecutor;

    private DataCompanyVcProductWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyVcProductVO> create(DataCompanyVcProductCreateCommand dataCompanyVcProductCreateCommand) {
        return dataCompanyVcProductCreateCommandExecutor.execute(dataCompanyVcProductCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyVcProductVO> delete(IdCommand deleteCommand) {
        return dataCompanyVcProductDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyVcProductVO> update(DataCompanyVcProductUpdateCommand dataCompanyVcProductUpdateCommand) {
        return dataCompanyVcProductUpdateCommandExecutor.execute(dataCompanyVcProductUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyVcProductExWarehouseVO> warehouse(DataCompanyVcProductWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyVcProductCreateCommandExecutor(DataCompanyVcProductCreateCommandExecutor dataCompanyVcProductCreateCommandExecutor) {
        this.dataCompanyVcProductCreateCommandExecutor = dataCompanyVcProductCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyVcProductDeleteCommandExecutor(DataCompanyVcProductDeleteCommandExecutor dataCompanyVcProductDeleteCommandExecutor) {
        this.dataCompanyVcProductDeleteCommandExecutor = dataCompanyVcProductDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcProductUpdateCommandExecutor(DataCompanyVcProductUpdateCommandExecutor dataCompanyVcProductUpdateCommandExecutor) {
        this.dataCompanyVcProductUpdateCommandExecutor = dataCompanyVcProductUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcProductCommandExecutor(DataCompanyVcProductCommandExecutor dataCompanyVcProductCommandExecutor) {
        this.dataCompanyVcProductCommandExecutor = dataCompanyVcProductCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcProductWarehouseCommandExecutor(DataCompanyVcProductWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}