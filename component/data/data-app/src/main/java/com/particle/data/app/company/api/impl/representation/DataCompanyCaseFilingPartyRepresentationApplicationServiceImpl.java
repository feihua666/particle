package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyCaseFilingPartyQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyCaseFilingPartyRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyCaseFilingPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCaseFilingPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyCaseFilingPartyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyCaseFilingPartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCaseFilingPartyExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyCaseFilingPartyExWarehouseCommandExecutor;
/**
 * <p>
 * 企业立案信息当事人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:50
 */
@Service
@CatchAndLog
public class DataCompanyCaseFilingPartyRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyCaseFilingPartyRepresentationApplicationService {

    private DataCompanyCaseFilingPartyQueryCommandExecutor dataCompanyCaseFilingPartyQueryCommandExecutor;
    private DataCompanyCaseFilingPartyExWarehouseCommandExecutor dataCompanyCaseFilingPartyExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyCaseFilingPartyVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyCaseFilingPartyQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyCaseFilingPartyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyCaseFilingPartyQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyCaseFilingPartyVO> pageQuery(DataCompanyCaseFilingPartyPageQueryCommand dataCompanyCaseFilingPartyPageQueryCommand) {
        return dataCompanyCaseFilingPartyQueryCommandExecutor.execute(dataCompanyCaseFilingPartyPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyCaseFilingPartyVO> queryList(DataCompanyCaseFilingPartyQueryListCommand dataCompanyCaseFilingPartyQueryListCommand) {
        return dataCompanyCaseFilingPartyQueryCommandExecutor.execute(dataCompanyCaseFilingPartyQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyCaseFilingPartyExWarehouseVO> exWarehouse(DataCompanyCaseFilingPartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyCaseFilingPartyExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyCaseFilingPartyQueryCommandExecutor(DataCompanyCaseFilingPartyQueryCommandExecutor dataCompanyCaseFilingPartyQueryCommandExecutor) {
        this.dataCompanyCaseFilingPartyQueryCommandExecutor = dataCompanyCaseFilingPartyQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCaseFilingPartyExWarehouseCommandExecutor(DataCompanyCaseFilingPartyExWarehouseCommandExecutor dataCompanyCaseFilingPartyExWarehouseCommandExecutor) {
        this.dataCompanyCaseFilingPartyExWarehouseCommandExecutor = dataCompanyCaseFilingPartyExWarehouseCommandExecutor;
    }
}
