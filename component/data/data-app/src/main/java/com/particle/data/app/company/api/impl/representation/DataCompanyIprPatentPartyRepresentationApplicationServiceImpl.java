package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprPatentPartyQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentPartyRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPartyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentPartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPartyExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentPartyExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权当事人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-27 18:00:12
 */
@Service
@CatchAndLog
public class DataCompanyIprPatentPartyRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentPartyRepresentationApplicationService {

    private DataCompanyIprPatentPartyQueryCommandExecutor dataCompanyIprPatentPartyQueryCommandExecutor;
    private DataCompanyIprPatentPartyExWarehouseCommandExecutor dataCompanyIprPatentPartyExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentPartyVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprPatentPartyQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentPartyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprPatentPartyQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprPatentPartyVO> pageQuery(DataCompanyIprPatentPartyPageQueryCommand dataCompanyIprPatentPartyPageQueryCommand) {
        return dataCompanyIprPatentPartyQueryCommandExecutor.execute(dataCompanyIprPatentPartyPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprPatentPartyVO> queryList(DataCompanyIprPatentPartyQueryListCommand dataCompanyIprPatentPartyQueryListCommand) {
        return dataCompanyIprPatentPartyQueryCommandExecutor.execute(dataCompanyIprPatentPartyQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprPatentPartyExWarehouseVO> exWarehouse(DataCompanyIprPatentPartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprPatentPartyExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprPatentPartyQueryCommandExecutor(DataCompanyIprPatentPartyQueryCommandExecutor dataCompanyIprPatentPartyQueryCommandExecutor) {
        this.dataCompanyIprPatentPartyQueryCommandExecutor = dataCompanyIprPatentPartyQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentPartyExWarehouseCommandExecutor(DataCompanyIprPatentPartyExWarehouseCommandExecutor dataCompanyIprPatentPartyExWarehouseCommandExecutor) {
        this.dataCompanyIprPatentPartyExWarehouseCommandExecutor = dataCompanyIprPatentPartyExWarehouseCommandExecutor;
    }
}
