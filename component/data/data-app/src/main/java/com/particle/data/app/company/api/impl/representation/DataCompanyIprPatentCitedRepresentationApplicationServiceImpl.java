package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprPatentCitedQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentCitedRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentCitedPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentCitedQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentCitedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentCitedExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentCitedExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentCitedExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利被引证信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:15
 */
@Service
@CatchAndLog
public class DataCompanyIprPatentCitedRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentCitedRepresentationApplicationService {

    private DataCompanyIprPatentCitedQueryCommandExecutor dataCompanyIprPatentCitedQueryCommandExecutor;
    private DataCompanyIprPatentCitedExWarehouseCommandExecutor dataCompanyIprPatentCitedExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentCitedVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprPatentCitedQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentCitedVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprPatentCitedQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprPatentCitedVO> pageQuery(DataCompanyIprPatentCitedPageQueryCommand dataCompanyIprPatentCitedPageQueryCommand) {
        return dataCompanyIprPatentCitedQueryCommandExecutor.execute(dataCompanyIprPatentCitedPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprPatentCitedVO> queryList(DataCompanyIprPatentCitedQueryListCommand dataCompanyIprPatentCitedQueryListCommand) {
        return dataCompanyIprPatentCitedQueryCommandExecutor.execute(dataCompanyIprPatentCitedQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprPatentCitedExWarehouseVO> exWarehouse(DataCompanyIprPatentCitedExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprPatentCitedExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprPatentCitedQueryCommandExecutor(DataCompanyIprPatentCitedQueryCommandExecutor dataCompanyIprPatentCitedQueryCommandExecutor) {
        this.dataCompanyIprPatentCitedQueryCommandExecutor = dataCompanyIprPatentCitedQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentCitedExWarehouseCommandExecutor(DataCompanyIprPatentCitedExWarehouseCommandExecutor dataCompanyIprPatentCitedExWarehouseCommandExecutor) {
        this.dataCompanyIprPatentCitedExWarehouseCommandExecutor = dataCompanyIprPatentCitedExWarehouseCommandExecutor;
    }
}
