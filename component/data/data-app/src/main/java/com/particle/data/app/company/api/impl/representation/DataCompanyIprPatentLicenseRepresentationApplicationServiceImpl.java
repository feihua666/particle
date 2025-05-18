package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprPatentLicenseQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentLicenseRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentLicensePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentLicenseQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentLicenseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentLicenseExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentLicenseExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentLicenseExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利许可信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:59
 */
@Service
@CatchAndLog
public class DataCompanyIprPatentLicenseRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentLicenseRepresentationApplicationService {

    private DataCompanyIprPatentLicenseQueryCommandExecutor dataCompanyIprPatentLicenseQueryCommandExecutor;
    private DataCompanyIprPatentLicenseExWarehouseCommandExecutor dataCompanyIprPatentLicenseExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentLicenseVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprPatentLicenseQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentLicenseVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprPatentLicenseQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprPatentLicenseVO> pageQuery(DataCompanyIprPatentLicensePageQueryCommand dataCompanyIprPatentLicensePageQueryCommand) {
        return dataCompanyIprPatentLicenseQueryCommandExecutor.execute(dataCompanyIprPatentLicensePageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprPatentLicenseVO> queryList(DataCompanyIprPatentLicenseQueryListCommand dataCompanyIprPatentLicenseQueryListCommand) {
        return dataCompanyIprPatentLicenseQueryCommandExecutor.execute(dataCompanyIprPatentLicenseQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprPatentLicenseExWarehouseVO> exWarehouse(DataCompanyIprPatentLicenseExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprPatentLicenseExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprPatentLicenseQueryCommandExecutor(DataCompanyIprPatentLicenseQueryCommandExecutor dataCompanyIprPatentLicenseQueryCommandExecutor) {
        this.dataCompanyIprPatentLicenseQueryCommandExecutor = dataCompanyIprPatentLicenseQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentLicenseExWarehouseCommandExecutor(DataCompanyIprPatentLicenseExWarehouseCommandExecutor dataCompanyIprPatentLicenseExWarehouseCommandExecutor) {
        this.dataCompanyIprPatentLicenseExWarehouseCommandExecutor = dataCompanyIprPatentLicenseExWarehouseCommandExecutor;
    }
}
