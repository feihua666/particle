package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprPatentQuoteCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentQuoteDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentQuoteUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentQuoteCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentQuoteUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprPatentQuoteApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentQuoteCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentQuoteVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentQuoteWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentQuoteExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprPatentQuoteWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利引证信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:24
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprPatentQuoteApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentQuoteApplicationService {

    private DataCompanyIprPatentQuoteCreateCommandExecutor dataCompanyIprPatentQuoteCreateCommandExecutor;

    private DataCompanyIprPatentQuoteDeleteCommandExecutor dataCompanyIprPatentQuoteDeleteCommandExecutor;

    private DataCompanyIprPatentQuoteUpdateCommandExecutor dataCompanyIprPatentQuoteUpdateCommandExecutor;

    private DataCompanyIprPatentQuoteCommandExecutor dataCompanyIprPatentQuoteCommandExecutor;

    private DataCompanyIprPatentQuoteWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentQuoteVO> create(DataCompanyIprPatentQuoteCreateCommand dataCompanyIprPatentQuoteCreateCommand) {
        return dataCompanyIprPatentQuoteCreateCommandExecutor.execute(dataCompanyIprPatentQuoteCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentQuoteVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprPatentQuoteDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentQuoteVO> update(DataCompanyIprPatentQuoteUpdateCommand dataCompanyIprPatentQuoteUpdateCommand) {
        return dataCompanyIprPatentQuoteUpdateCommandExecutor.execute(dataCompanyIprPatentQuoteUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprPatentQuoteExWarehouseVO> warehouse(DataCompanyIprPatentQuoteWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprPatentQuoteCreateCommandExecutor(DataCompanyIprPatentQuoteCreateCommandExecutor dataCompanyIprPatentQuoteCreateCommandExecutor) {
        this.dataCompanyIprPatentQuoteCreateCommandExecutor = dataCompanyIprPatentQuoteCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprPatentQuoteDeleteCommandExecutor(DataCompanyIprPatentQuoteDeleteCommandExecutor dataCompanyIprPatentQuoteDeleteCommandExecutor) {
        this.dataCompanyIprPatentQuoteDeleteCommandExecutor = dataCompanyIprPatentQuoteDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentQuoteUpdateCommandExecutor(DataCompanyIprPatentQuoteUpdateCommandExecutor dataCompanyIprPatentQuoteUpdateCommandExecutor) {
        this.dataCompanyIprPatentQuoteUpdateCommandExecutor = dataCompanyIprPatentQuoteUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentQuoteCommandExecutor(DataCompanyIprPatentQuoteCommandExecutor dataCompanyIprPatentQuoteCommandExecutor) {
        this.dataCompanyIprPatentQuoteCommandExecutor = dataCompanyIprPatentQuoteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentQuoteWarehouseCommandExecutor(DataCompanyIprPatentQuoteWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}