package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyRestrictHighConsumePartyQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyRestrictHighConsumePartyRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyRestrictHighConsumePartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyRestrictHighConsumePartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyRestrictHighConsumePartyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyRestrictHighConsumePartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyRestrictHighConsumePartyExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor;
/**
 * <p>
 * 企业限制高消费当事人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:32
 */
@Service
@CatchAndLog
public class DataCompanyRestrictHighConsumePartyRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyRestrictHighConsumePartyRepresentationApplicationService {

    private DataCompanyRestrictHighConsumePartyQueryCommandExecutor dataCompanyRestrictHighConsumePartyQueryCommandExecutor;
    private DataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor dataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyRestrictHighConsumePartyVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyRestrictHighConsumePartyQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyRestrictHighConsumePartyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyRestrictHighConsumePartyQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyRestrictHighConsumePartyVO> pageQuery(DataCompanyRestrictHighConsumePartyPageQueryCommand dataCompanyRestrictHighConsumePartyPageQueryCommand) {
        return dataCompanyRestrictHighConsumePartyQueryCommandExecutor.execute(dataCompanyRestrictHighConsumePartyPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyRestrictHighConsumePartyVO> queryList(DataCompanyRestrictHighConsumePartyQueryListCommand dataCompanyRestrictHighConsumePartyQueryListCommand) {
        return dataCompanyRestrictHighConsumePartyQueryCommandExecutor.execute(dataCompanyRestrictHighConsumePartyQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyRestrictHighConsumePartyExWarehouseVO> exWarehouse(DataCompanyRestrictHighConsumePartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyRestrictHighConsumePartyQueryCommandExecutor(DataCompanyRestrictHighConsumePartyQueryCommandExecutor dataCompanyRestrictHighConsumePartyQueryCommandExecutor) {
        this.dataCompanyRestrictHighConsumePartyQueryCommandExecutor = dataCompanyRestrictHighConsumePartyQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor(DataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor dataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor) {
        this.dataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor = dataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor;
    }
}
