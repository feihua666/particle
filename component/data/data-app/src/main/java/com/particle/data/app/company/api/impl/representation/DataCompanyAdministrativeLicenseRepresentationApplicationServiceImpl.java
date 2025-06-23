package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyAdministrativeLicenseQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyAdministrativeLicenseRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAdministrativeLicensePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAdministrativeLicenseQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAdministrativeLicenseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAdministrativeLicenseExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAdministrativeLicenseExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAdministrativeLicenseExWarehouseCommandExecutor;
/**
 * <p>
 * 企业行政许可 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:17:53
 */
@Service
@CatchAndLog
public class DataCompanyAdministrativeLicenseRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAdministrativeLicenseRepresentationApplicationService {

    private DataCompanyAdministrativeLicenseQueryCommandExecutor dataCompanyAdministrativeLicenseQueryCommandExecutor;
    private DataCompanyAdministrativeLicenseExWarehouseCommandExecutor dataCompanyAdministrativeLicenseExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAdministrativeLicenseVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyAdministrativeLicenseQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyAdministrativeLicenseVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyAdministrativeLicenseQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyAdministrativeLicenseVO> pageQuery(DataCompanyAdministrativeLicensePageQueryCommand dataCompanyAdministrativeLicensePageQueryCommand) {
        return dataCompanyAdministrativeLicenseQueryCommandExecutor.execute(dataCompanyAdministrativeLicensePageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyAdministrativeLicenseVO> queryList(DataCompanyAdministrativeLicenseQueryListCommand dataCompanyAdministrativeLicenseQueryListCommand) {
        return dataCompanyAdministrativeLicenseQueryCommandExecutor.execute(dataCompanyAdministrativeLicenseQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyAdministrativeLicenseExWarehouseVO> exWarehouse(DataCompanyAdministrativeLicenseExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyAdministrativeLicenseExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyAdministrativeLicenseQueryCommandExecutor(DataCompanyAdministrativeLicenseQueryCommandExecutor dataCompanyAdministrativeLicenseQueryCommandExecutor) {
        this.dataCompanyAdministrativeLicenseQueryCommandExecutor = dataCompanyAdministrativeLicenseQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAdministrativeLicenseExWarehouseCommandExecutor(DataCompanyAdministrativeLicenseExWarehouseCommandExecutor dataCompanyAdministrativeLicenseExWarehouseCommandExecutor) {
        this.dataCompanyAdministrativeLicenseExWarehouseCommandExecutor = dataCompanyAdministrativeLicenseExWarehouseCommandExecutor;
    }
}
