package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprPatentFamilyQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentFamilyRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentFamilyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentFamilyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentFamilyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentFamilyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentFamilyExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentFamilyExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利同族信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:43
 */
@Service
@CatchAndLog
public class DataCompanyIprPatentFamilyRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentFamilyRepresentationApplicationService {

    private DataCompanyIprPatentFamilyQueryCommandExecutor dataCompanyIprPatentFamilyQueryCommandExecutor;
    private DataCompanyIprPatentFamilyExWarehouseCommandExecutor dataCompanyIprPatentFamilyExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentFamilyVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprPatentFamilyQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentFamilyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprPatentFamilyQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprPatentFamilyVO> pageQuery(DataCompanyIprPatentFamilyPageQueryCommand dataCompanyIprPatentFamilyPageQueryCommand) {
        return dataCompanyIprPatentFamilyQueryCommandExecutor.execute(dataCompanyIprPatentFamilyPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprPatentFamilyVO> queryList(DataCompanyIprPatentFamilyQueryListCommand dataCompanyIprPatentFamilyQueryListCommand) {
        return dataCompanyIprPatentFamilyQueryCommandExecutor.execute(dataCompanyIprPatentFamilyQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprPatentFamilyExWarehouseVO> exWarehouse(DataCompanyIprPatentFamilyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprPatentFamilyExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprPatentFamilyQueryCommandExecutor(DataCompanyIprPatentFamilyQueryCommandExecutor dataCompanyIprPatentFamilyQueryCommandExecutor) {
        this.dataCompanyIprPatentFamilyQueryCommandExecutor = dataCompanyIprPatentFamilyQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentFamilyExWarehouseCommandExecutor(DataCompanyIprPatentFamilyExWarehouseCommandExecutor dataCompanyIprPatentFamilyExWarehouseCommandExecutor) {
        this.dataCompanyIprPatentFamilyExWarehouseCommandExecutor = dataCompanyIprPatentFamilyExWarehouseCommandExecutor;
    }
}
