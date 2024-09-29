package com.particle.openplatform.app.bill.api.impl;

import com.particle.openplatform.app.bill.executor.OpenplatformProviderRecordPrdMonthBillCreateCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformProviderRecordPrdMonthBillDeleteCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformProviderRecordPrdMonthBillUpdateCommandExecutor;
import com.particle.openplatform.app.bill.executor.OpenplatformProviderRecordPrdMonthBillCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdMonthBillUpdateCommand;
import com.particle.openplatform.client.bill.api.IOpenplatformProviderRecordPrdMonthBillApplicationService;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdMonthBillCreateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdMonthBillVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放平台供应商月账单 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:53
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformProviderRecordPrdMonthBillApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformProviderRecordPrdMonthBillApplicationService {

    private OpenplatformProviderRecordPrdMonthBillCreateCommandExecutor openplatformProviderRecordPrdMonthBillCreateCommandExecutor;

    private OpenplatformProviderRecordPrdMonthBillDeleteCommandExecutor openplatformProviderRecordPrdMonthBillDeleteCommandExecutor;

    private OpenplatformProviderRecordPrdMonthBillUpdateCommandExecutor openplatformProviderRecordPrdMonthBillUpdateCommandExecutor;

    private OpenplatformProviderRecordPrdMonthBillCommandExecutor openplatformProviderRecordPrdMonthBillCommandExecutor;


    @Override
    public SingleResponse<OpenplatformProviderRecordPrdMonthBillVO> create(OpenplatformProviderRecordPrdMonthBillCreateCommand openplatformProviderRecordPrdMonthBillCreateCommand) {
        return openplatformProviderRecordPrdMonthBillCreateCommandExecutor.execute(openplatformProviderRecordPrdMonthBillCreateCommand);
    }

    @Override
    public SingleResponse<OpenplatformProviderRecordPrdMonthBillVO> delete(IdCommand deleteCommand) {
        return openplatformProviderRecordPrdMonthBillDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<OpenplatformProviderRecordPrdMonthBillVO> update(OpenplatformProviderRecordPrdMonthBillUpdateCommand openplatformProviderRecordPrdMonthBillUpdateCommand) {
        return openplatformProviderRecordPrdMonthBillUpdateCommandExecutor.execute(openplatformProviderRecordPrdMonthBillUpdateCommand);
    }


    @Autowired
    public void setOpenplatformProviderRecordPrdMonthBillCreateCommandExecutor(OpenplatformProviderRecordPrdMonthBillCreateCommandExecutor openplatformProviderRecordPrdMonthBillCreateCommandExecutor) {
        this.openplatformProviderRecordPrdMonthBillCreateCommandExecutor = openplatformProviderRecordPrdMonthBillCreateCommandExecutor;
    }

    @Autowired
    public void setOpenplatformProviderRecordPrdMonthBillDeleteCommandExecutor(OpenplatformProviderRecordPrdMonthBillDeleteCommandExecutor openplatformProviderRecordPrdMonthBillDeleteCommandExecutor) {
        this.openplatformProviderRecordPrdMonthBillDeleteCommandExecutor = openplatformProviderRecordPrdMonthBillDeleteCommandExecutor;
    }
    @Autowired
    public void setOpenplatformProviderRecordPrdMonthBillUpdateCommandExecutor(OpenplatformProviderRecordPrdMonthBillUpdateCommandExecutor openplatformProviderRecordPrdMonthBillUpdateCommandExecutor) {
        this.openplatformProviderRecordPrdMonthBillUpdateCommandExecutor = openplatformProviderRecordPrdMonthBillUpdateCommandExecutor;
    }
    @Autowired
    public void setOpenplatformProviderRecordPrdMonthBillCommandExecutor(OpenplatformProviderRecordPrdMonthBillCommandExecutor openplatformProviderRecordPrdMonthBillCommandExecutor) {
        this.openplatformProviderRecordPrdMonthBillCommandExecutor = openplatformProviderRecordPrdMonthBillCommandExecutor;
    }
}
