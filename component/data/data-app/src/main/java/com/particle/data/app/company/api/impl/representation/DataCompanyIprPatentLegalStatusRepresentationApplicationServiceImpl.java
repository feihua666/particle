package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprPatentLegalStatusQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentLegalStatusRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentLegalStatusPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentLegalStatusQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentLegalStatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentLegalStatusExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentLegalStatusExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentLegalStatusExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利法律状态 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:03
 */
@Service
@CatchAndLog
public class DataCompanyIprPatentLegalStatusRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentLegalStatusRepresentationApplicationService {

    private DataCompanyIprPatentLegalStatusQueryCommandExecutor dataCompanyIprPatentLegalStatusQueryCommandExecutor;
    private DataCompanyIprPatentLegalStatusExWarehouseCommandExecutor dataCompanyIprPatentLegalStatusExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentLegalStatusVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprPatentLegalStatusQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentLegalStatusVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprPatentLegalStatusQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprPatentLegalStatusVO> pageQuery(DataCompanyIprPatentLegalStatusPageQueryCommand dataCompanyIprPatentLegalStatusPageQueryCommand) {
        return dataCompanyIprPatentLegalStatusQueryCommandExecutor.execute(dataCompanyIprPatentLegalStatusPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprPatentLegalStatusVO> queryList(DataCompanyIprPatentLegalStatusQueryListCommand dataCompanyIprPatentLegalStatusQueryListCommand) {
        return dataCompanyIprPatentLegalStatusQueryCommandExecutor.execute(dataCompanyIprPatentLegalStatusQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprPatentLegalStatusExWarehouseVO> exWarehouse(DataCompanyIprPatentLegalStatusExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprPatentLegalStatusExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprPatentLegalStatusQueryCommandExecutor(DataCompanyIprPatentLegalStatusQueryCommandExecutor dataCompanyIprPatentLegalStatusQueryCommandExecutor) {
        this.dataCompanyIprPatentLegalStatusQueryCommandExecutor = dataCompanyIprPatentLegalStatusQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentLegalStatusExWarehouseCommandExecutor(DataCompanyIprPatentLegalStatusExWarehouseCommandExecutor dataCompanyIprPatentLegalStatusExWarehouseCommandExecutor) {
        this.dataCompanyIprPatentLegalStatusExWarehouseCommandExecutor = dataCompanyIprPatentLegalStatusExWarehouseCommandExecutor;
    }
}
