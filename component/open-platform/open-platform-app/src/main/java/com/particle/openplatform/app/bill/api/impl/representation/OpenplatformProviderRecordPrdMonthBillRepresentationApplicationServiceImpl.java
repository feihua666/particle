package com.particle.openplatform.app.bill.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.bill.executor.representation.OpenplatformProviderRecordPrdMonthBillQueryCommandExecutor;
import com.particle.openplatform.client.bill.api.representation.IOpenplatformProviderRecordPrdMonthBillRepresentationApplicationService;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdMonthBillPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdMonthBillQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdMonthBillVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放平台供应商月账单 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:53
 */
@Service
@CatchAndLog
public class OpenplatformProviderRecordPrdMonthBillRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformProviderRecordPrdMonthBillRepresentationApplicationService {

    private OpenplatformProviderRecordPrdMonthBillQueryCommandExecutor openplatformProviderRecordPrdMonthBillQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformProviderRecordPrdMonthBillVO> queryDetail(IdCommand detailCommand) {
        return openplatformProviderRecordPrdMonthBillQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformProviderRecordPrdMonthBillVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformProviderRecordPrdMonthBillQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformProviderRecordPrdMonthBillVO> pageQuery(OpenplatformProviderRecordPrdMonthBillPageQueryCommand openplatformProviderRecordPrdMonthBillPageQueryCommand) {
        return openplatformProviderRecordPrdMonthBillQueryCommandExecutor.execute(openplatformProviderRecordPrdMonthBillPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformProviderRecordPrdMonthBillVO> queryList(OpenplatformProviderRecordPrdMonthBillQueryListCommand openplatformProviderRecordPrdMonthBillQueryListCommand) {
        return openplatformProviderRecordPrdMonthBillQueryCommandExecutor.execute(openplatformProviderRecordPrdMonthBillQueryListCommand);
    }


    @Autowired
    public void setOpenplatformProviderRecordPrdMonthBillQueryCommandExecutor(OpenplatformProviderRecordPrdMonthBillQueryCommandExecutor openplatformProviderRecordPrdMonthBillQueryCommandExecutor) {
        this.openplatformProviderRecordPrdMonthBillQueryCommandExecutor = openplatformProviderRecordPrdMonthBillQueryCommandExecutor;
    }
}
