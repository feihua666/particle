package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprPatentPartyCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentPartyDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentPartyUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentPartyCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPartyUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprPatentPartyApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPartyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPartyVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPartyExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprPatentPartyWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利当事人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-27 18:00:12
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprPatentPartyApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentPartyApplicationService {

    private DataCompanyIprPatentPartyCreateCommandExecutor dataCompanyIprPatentPartyCreateCommandExecutor;

    private DataCompanyIprPatentPartyDeleteCommandExecutor dataCompanyIprPatentPartyDeleteCommandExecutor;

    private DataCompanyIprPatentPartyUpdateCommandExecutor dataCompanyIprPatentPartyUpdateCommandExecutor;

    private DataCompanyIprPatentPartyCommandExecutor dataCompanyIprPatentPartyCommandExecutor;

    private DataCompanyIprPatentPartyWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentPartyVO> create(DataCompanyIprPatentPartyCreateCommand dataCompanyIprPatentPartyCreateCommand) {
        return dataCompanyIprPatentPartyCreateCommandExecutor.execute(dataCompanyIprPatentPartyCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentPartyVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprPatentPartyDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentPartyVO> update(DataCompanyIprPatentPartyUpdateCommand dataCompanyIprPatentPartyUpdateCommand) {
        return dataCompanyIprPatentPartyUpdateCommandExecutor.execute(dataCompanyIprPatentPartyUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprPatentPartyExWarehouseVO> warehouse(DataCompanyIprPatentPartyWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprPatentPartyCreateCommandExecutor(DataCompanyIprPatentPartyCreateCommandExecutor dataCompanyIprPatentPartyCreateCommandExecutor) {
        this.dataCompanyIprPatentPartyCreateCommandExecutor = dataCompanyIprPatentPartyCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprPatentPartyDeleteCommandExecutor(DataCompanyIprPatentPartyDeleteCommandExecutor dataCompanyIprPatentPartyDeleteCommandExecutor) {
        this.dataCompanyIprPatentPartyDeleteCommandExecutor = dataCompanyIprPatentPartyDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentPartyUpdateCommandExecutor(DataCompanyIprPatentPartyUpdateCommandExecutor dataCompanyIprPatentPartyUpdateCommandExecutor) {
        this.dataCompanyIprPatentPartyUpdateCommandExecutor = dataCompanyIprPatentPartyUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentPartyCommandExecutor(DataCompanyIprPatentPartyCommandExecutor dataCompanyIprPatentPartyCommandExecutor) {
        this.dataCompanyIprPatentPartyCommandExecutor = dataCompanyIprPatentPartyCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentPartyWarehouseCommandExecutor(DataCompanyIprPatentPartyWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}
