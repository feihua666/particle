package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprPatentQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:48
 */
@Service
@CatchAndLog
public class DataCompanyIprPatentRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentRepresentationApplicationService {

    private DataCompanyIprPatentQueryCommandExecutor dataCompanyIprPatentQueryCommandExecutor;
    private DataCompanyIprPatentExWarehouseCommandExecutor dataCompanyIprPatentExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprPatentQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprPatentQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprPatentVO> pageQuery(DataCompanyIprPatentPageQueryCommand dataCompanyIprPatentPageQueryCommand) {
        return dataCompanyIprPatentQueryCommandExecutor.execute(dataCompanyIprPatentPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprPatentVO> queryList(DataCompanyIprPatentQueryListCommand dataCompanyIprPatentQueryListCommand) {
        return dataCompanyIprPatentQueryCommandExecutor.execute(dataCompanyIprPatentQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprPatentExWarehouseVO> exWarehouse(DataCompanyIprPatentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprPatentExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprPatentQueryCommandExecutor(DataCompanyIprPatentQueryCommandExecutor dataCompanyIprPatentQueryCommandExecutor) {
        this.dataCompanyIprPatentQueryCommandExecutor = dataCompanyIprPatentQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentExWarehouseCommandExecutor(DataCompanyIprPatentExWarehouseCommandExecutor dataCompanyIprPatentExWarehouseCommandExecutor) {
        this.dataCompanyIprPatentExWarehouseCommandExecutor = dataCompanyIprPatentExWarehouseCommandExecutor;
    }
}
