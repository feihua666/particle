package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyVcProductQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyVcProductRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcProductPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcProductQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyVcProductExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcProductExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyVcProductExWarehouseCommandExecutor;
/**
 * <p>
 * 企业融资产品 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:14
 */
@Service
@CatchAndLog
public class DataCompanyVcProductRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyVcProductRepresentationApplicationService {

    private DataCompanyVcProductQueryCommandExecutor dataCompanyVcProductQueryCommandExecutor;
    private DataCompanyVcProductExWarehouseCommandExecutor dataCompanyVcProductExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyVcProductVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyVcProductQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyVcProductVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyVcProductQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyVcProductVO> pageQuery(DataCompanyVcProductPageQueryCommand dataCompanyVcProductPageQueryCommand) {
        return dataCompanyVcProductQueryCommandExecutor.execute(dataCompanyVcProductPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyVcProductVO> queryList(DataCompanyVcProductQueryListCommand dataCompanyVcProductQueryListCommand) {
        return dataCompanyVcProductQueryCommandExecutor.execute(dataCompanyVcProductQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyVcProductExWarehouseVO> exWarehouse(DataCompanyVcProductExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyVcProductExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyVcProductQueryCommandExecutor(DataCompanyVcProductQueryCommandExecutor dataCompanyVcProductQueryCommandExecutor) {
        this.dataCompanyVcProductQueryCommandExecutor = dataCompanyVcProductQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcProductExWarehouseCommandExecutor(DataCompanyVcProductExWarehouseCommandExecutor dataCompanyVcProductExWarehouseCommandExecutor) {
        this.dataCompanyVcProductExWarehouseCommandExecutor = dataCompanyVcProductExWarehouseCommandExecutor;
    }
}
