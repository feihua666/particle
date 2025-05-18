package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprPatentPledgeQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentPledgeRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPledgePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPledgeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPledgeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentPledgeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPledgeExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentPledgeExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利质押信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:40
 */
@Service
@CatchAndLog
public class DataCompanyIprPatentPledgeRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentPledgeRepresentationApplicationService {

    private DataCompanyIprPatentPledgeQueryCommandExecutor dataCompanyIprPatentPledgeQueryCommandExecutor;
    private DataCompanyIprPatentPledgeExWarehouseCommandExecutor dataCompanyIprPatentPledgeExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentPledgeVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprPatentPledgeQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentPledgeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprPatentPledgeQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprPatentPledgeVO> pageQuery(DataCompanyIprPatentPledgePageQueryCommand dataCompanyIprPatentPledgePageQueryCommand) {
        return dataCompanyIprPatentPledgeQueryCommandExecutor.execute(dataCompanyIprPatentPledgePageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprPatentPledgeVO> queryList(DataCompanyIprPatentPledgeQueryListCommand dataCompanyIprPatentPledgeQueryListCommand) {
        return dataCompanyIprPatentPledgeQueryCommandExecutor.execute(dataCompanyIprPatentPledgeQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprPatentPledgeExWarehouseVO> exWarehouse(DataCompanyIprPatentPledgeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprPatentPledgeExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprPatentPledgeQueryCommandExecutor(DataCompanyIprPatentPledgeQueryCommandExecutor dataCompanyIprPatentPledgeQueryCommandExecutor) {
        this.dataCompanyIprPatentPledgeQueryCommandExecutor = dataCompanyIprPatentPledgeQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentPledgeExWarehouseCommandExecutor(DataCompanyIprPatentPledgeExWarehouseCommandExecutor dataCompanyIprPatentPledgeExWarehouseCommandExecutor) {
        this.dataCompanyIprPatentPledgeExWarehouseCommandExecutor = dataCompanyIprPatentPledgeExWarehouseCommandExecutor;
    }
}
