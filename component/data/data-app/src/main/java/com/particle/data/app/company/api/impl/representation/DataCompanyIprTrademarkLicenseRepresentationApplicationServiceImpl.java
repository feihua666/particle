package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprTrademarkLicenseQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprTrademarkLicenseRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkLicensePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkLicenseQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkLicenseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkLicenseExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkLicenseExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprTrademarkLicenseExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权商标许可信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:10
 */
@Service
@CatchAndLog
public class DataCompanyIprTrademarkLicenseRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprTrademarkLicenseRepresentationApplicationService {

    private DataCompanyIprTrademarkLicenseQueryCommandExecutor dataCompanyIprTrademarkLicenseQueryCommandExecutor;
    private DataCompanyIprTrademarkLicenseExWarehouseCommandExecutor dataCompanyIprTrademarkLicenseExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprTrademarkLicenseVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprTrademarkLicenseQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprTrademarkLicenseVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprTrademarkLicenseQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprTrademarkLicenseVO> pageQuery(DataCompanyIprTrademarkLicensePageQueryCommand dataCompanyIprTrademarkLicensePageQueryCommand) {
        return dataCompanyIprTrademarkLicenseQueryCommandExecutor.execute(dataCompanyIprTrademarkLicensePageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprTrademarkLicenseVO> queryList(DataCompanyIprTrademarkLicenseQueryListCommand dataCompanyIprTrademarkLicenseQueryListCommand) {
        return dataCompanyIprTrademarkLicenseQueryCommandExecutor.execute(dataCompanyIprTrademarkLicenseQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprTrademarkLicenseExWarehouseVO> exWarehouse(DataCompanyIprTrademarkLicenseExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprTrademarkLicenseExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprTrademarkLicenseQueryCommandExecutor(DataCompanyIprTrademarkLicenseQueryCommandExecutor dataCompanyIprTrademarkLicenseQueryCommandExecutor) {
        this.dataCompanyIprTrademarkLicenseQueryCommandExecutor = dataCompanyIprTrademarkLicenseQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkLicenseExWarehouseCommandExecutor(DataCompanyIprTrademarkLicenseExWarehouseCommandExecutor dataCompanyIprTrademarkLicenseExWarehouseCommandExecutor) {
        this.dataCompanyIprTrademarkLicenseExWarehouseCommandExecutor = dataCompanyIprTrademarkLicenseExWarehouseCommandExecutor;
    }
}
