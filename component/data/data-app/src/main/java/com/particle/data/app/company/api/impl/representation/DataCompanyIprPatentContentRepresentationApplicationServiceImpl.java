package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprPatentContentQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentContentRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentContentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentContentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentContentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentContentExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentContentExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利内容 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:27
 */
@Service
@CatchAndLog
public class DataCompanyIprPatentContentRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentContentRepresentationApplicationService {

    private DataCompanyIprPatentContentQueryCommandExecutor dataCompanyIprPatentContentQueryCommandExecutor;
    private DataCompanyIprPatentContentExWarehouseCommandExecutor dataCompanyIprPatentContentExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentContentVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprPatentContentQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentContentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprPatentContentQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprPatentContentVO> pageQuery(DataCompanyIprPatentContentPageQueryCommand dataCompanyIprPatentContentPageQueryCommand) {
        return dataCompanyIprPatentContentQueryCommandExecutor.execute(dataCompanyIprPatentContentPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprPatentContentVO> queryList(DataCompanyIprPatentContentQueryListCommand dataCompanyIprPatentContentQueryListCommand) {
        return dataCompanyIprPatentContentQueryCommandExecutor.execute(dataCompanyIprPatentContentQueryListCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprPatentContentExWarehouseVO> exWarehouse(DataCompanyIprPatentContentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprPatentContentExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprPatentContentQueryCommandExecutor(DataCompanyIprPatentContentQueryCommandExecutor dataCompanyIprPatentContentQueryCommandExecutor) {
        this.dataCompanyIprPatentContentQueryCommandExecutor = dataCompanyIprPatentContentQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentContentExWarehouseCommandExecutor(DataCompanyIprPatentContentExWarehouseCommandExecutor dataCompanyIprPatentContentExWarehouseCommandExecutor) {
        this.dataCompanyIprPatentContentExWarehouseCommandExecutor = dataCompanyIprPatentContentExWarehouseCommandExecutor;
    }
}