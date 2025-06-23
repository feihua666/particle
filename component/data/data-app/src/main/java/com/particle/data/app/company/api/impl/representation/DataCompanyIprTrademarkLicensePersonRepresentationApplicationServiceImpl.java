package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprTrademarkLicensePersonQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprTrademarkLicensePersonRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkLicensePersonPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkLicensePersonQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkLicensePersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkLicensePersonExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkLicensePersonExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprTrademarkLicensePersonExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权商标许可人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:22
 */
@Service
@CatchAndLog
public class DataCompanyIprTrademarkLicensePersonRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprTrademarkLicensePersonRepresentationApplicationService {

    private DataCompanyIprTrademarkLicensePersonQueryCommandExecutor dataCompanyIprTrademarkLicensePersonQueryCommandExecutor;
    private DataCompanyIprTrademarkLicensePersonExWarehouseCommandExecutor dataCompanyIprTrademarkLicensePersonExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprTrademarkLicensePersonVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprTrademarkLicensePersonQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprTrademarkLicensePersonVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprTrademarkLicensePersonQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprTrademarkLicensePersonVO> pageQuery(DataCompanyIprTrademarkLicensePersonPageQueryCommand dataCompanyIprTrademarkLicensePersonPageQueryCommand) {
        return dataCompanyIprTrademarkLicensePersonQueryCommandExecutor.execute(dataCompanyIprTrademarkLicensePersonPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprTrademarkLicensePersonVO> queryList(DataCompanyIprTrademarkLicensePersonQueryListCommand dataCompanyIprTrademarkLicensePersonQueryListCommand) {
        return dataCompanyIprTrademarkLicensePersonQueryCommandExecutor.execute(dataCompanyIprTrademarkLicensePersonQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprTrademarkLicensePersonExWarehouseVO> exWarehouse(DataCompanyIprTrademarkLicensePersonExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprTrademarkLicensePersonExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprTrademarkLicensePersonQueryCommandExecutor(DataCompanyIprTrademarkLicensePersonQueryCommandExecutor dataCompanyIprTrademarkLicensePersonQueryCommandExecutor) {
        this.dataCompanyIprTrademarkLicensePersonQueryCommandExecutor = dataCompanyIprTrademarkLicensePersonQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkLicensePersonExWarehouseCommandExecutor(DataCompanyIprTrademarkLicensePersonExWarehouseCommandExecutor dataCompanyIprTrademarkLicensePersonExWarehouseCommandExecutor) {
        this.dataCompanyIprTrademarkLicensePersonExWarehouseCommandExecutor = dataCompanyIprTrademarkLicensePersonExWarehouseCommandExecutor;
    }
}
