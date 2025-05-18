package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyRestrictHighConsumePartyCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyRestrictHighConsumePartyDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyRestrictHighConsumePartyUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyRestrictHighConsumePartyCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyRestrictHighConsumePartyUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyRestrictHighConsumePartyApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyRestrictHighConsumePartyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyRestrictHighConsumePartyVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyRestrictHighConsumePartyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyRestrictHighConsumePartyExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyRestrictHighConsumePartyWarehouseCommandExecutor;
/**
 * <p>
 * 企业限制高消费当事人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:32
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyRestrictHighConsumePartyApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyRestrictHighConsumePartyApplicationService {

    private DataCompanyRestrictHighConsumePartyCreateCommandExecutor dataCompanyRestrictHighConsumePartyCreateCommandExecutor;

    private DataCompanyRestrictHighConsumePartyDeleteCommandExecutor dataCompanyRestrictHighConsumePartyDeleteCommandExecutor;

    private DataCompanyRestrictHighConsumePartyUpdateCommandExecutor dataCompanyRestrictHighConsumePartyUpdateCommandExecutor;

    private DataCompanyRestrictHighConsumePartyCommandExecutor dataCompanyRestrictHighConsumePartyCommandExecutor;

    private DataCompanyRestrictHighConsumePartyWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyRestrictHighConsumePartyVO> create(DataCompanyRestrictHighConsumePartyCreateCommand dataCompanyRestrictHighConsumePartyCreateCommand) {
        return dataCompanyRestrictHighConsumePartyCreateCommandExecutor.execute(dataCompanyRestrictHighConsumePartyCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyRestrictHighConsumePartyVO> delete(IdCommand deleteCommand) {
        return dataCompanyRestrictHighConsumePartyDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyRestrictHighConsumePartyVO> update(DataCompanyRestrictHighConsumePartyUpdateCommand dataCompanyRestrictHighConsumePartyUpdateCommand) {
        return dataCompanyRestrictHighConsumePartyUpdateCommandExecutor.execute(dataCompanyRestrictHighConsumePartyUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyRestrictHighConsumePartyExWarehouseVO> warehouse(DataCompanyRestrictHighConsumePartyWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyRestrictHighConsumePartyCreateCommandExecutor(DataCompanyRestrictHighConsumePartyCreateCommandExecutor dataCompanyRestrictHighConsumePartyCreateCommandExecutor) {
        this.dataCompanyRestrictHighConsumePartyCreateCommandExecutor = dataCompanyRestrictHighConsumePartyCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyRestrictHighConsumePartyDeleteCommandExecutor(DataCompanyRestrictHighConsumePartyDeleteCommandExecutor dataCompanyRestrictHighConsumePartyDeleteCommandExecutor) {
        this.dataCompanyRestrictHighConsumePartyDeleteCommandExecutor = dataCompanyRestrictHighConsumePartyDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyRestrictHighConsumePartyUpdateCommandExecutor(DataCompanyRestrictHighConsumePartyUpdateCommandExecutor dataCompanyRestrictHighConsumePartyUpdateCommandExecutor) {
        this.dataCompanyRestrictHighConsumePartyUpdateCommandExecutor = dataCompanyRestrictHighConsumePartyUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyRestrictHighConsumePartyCommandExecutor(DataCompanyRestrictHighConsumePartyCommandExecutor dataCompanyRestrictHighConsumePartyCommandExecutor) {
        this.dataCompanyRestrictHighConsumePartyCommandExecutor = dataCompanyRestrictHighConsumePartyCommandExecutor;
    }
    @Autowired
    public void setDataCompanyRestrictHighConsumePartyWarehouseCommandExecutor(DataCompanyRestrictHighConsumePartyWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}