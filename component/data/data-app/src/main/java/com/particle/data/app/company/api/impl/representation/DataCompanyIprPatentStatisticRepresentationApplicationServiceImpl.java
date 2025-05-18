package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprPatentStatisticQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentStatisticRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentStatisticPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentStatisticQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentStatisticVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentStatisticExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentStatisticExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentStatisticExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利统计 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:36
 */
@Service
@CatchAndLog
public class DataCompanyIprPatentStatisticRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentStatisticRepresentationApplicationService {

    private DataCompanyIprPatentStatisticQueryCommandExecutor dataCompanyIprPatentStatisticQueryCommandExecutor;
    private DataCompanyIprPatentStatisticExWarehouseCommandExecutor dataCompanyIprPatentStatisticExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentStatisticVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprPatentStatisticQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentStatisticVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprPatentStatisticQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprPatentStatisticVO> pageQuery(DataCompanyIprPatentStatisticPageQueryCommand dataCompanyIprPatentStatisticPageQueryCommand) {
        return dataCompanyIprPatentStatisticQueryCommandExecutor.execute(dataCompanyIprPatentStatisticPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprPatentStatisticVO> queryList(DataCompanyIprPatentStatisticQueryListCommand dataCompanyIprPatentStatisticQueryListCommand) {
        return dataCompanyIprPatentStatisticQueryCommandExecutor.execute(dataCompanyIprPatentStatisticQueryListCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprPatentStatisticExWarehouseVO> exWarehouse(DataCompanyIprPatentStatisticExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprPatentStatisticExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprPatentStatisticQueryCommandExecutor(DataCompanyIprPatentStatisticQueryCommandExecutor dataCompanyIprPatentStatisticQueryCommandExecutor) {
        this.dataCompanyIprPatentStatisticQueryCommandExecutor = dataCompanyIprPatentStatisticQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentStatisticExWarehouseCommandExecutor(DataCompanyIprPatentStatisticExWarehouseCommandExecutor dataCompanyIprPatentStatisticExWarehouseCommandExecutor) {
        this.dataCompanyIprPatentStatisticExWarehouseCommandExecutor = dataCompanyIprPatentStatisticExWarehouseCommandExecutor;
    }
}