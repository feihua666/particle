package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprGeograQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprGeograRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprGeograPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprGeograQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprGeograVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprGeograExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprGeograExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprGeograExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权地理标识 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:33
 */
@Service
@CatchAndLog
public class DataCompanyIprGeograRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprGeograRepresentationApplicationService {

    private DataCompanyIprGeograQueryCommandExecutor dataCompanyIprGeograQueryCommandExecutor;
    private DataCompanyIprGeograExWarehouseCommandExecutor dataCompanyIprGeograExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprGeograVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprGeograQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprGeograVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprGeograQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprGeograVO> pageQuery(DataCompanyIprGeograPageQueryCommand dataCompanyIprGeograPageQueryCommand) {
        return dataCompanyIprGeograQueryCommandExecutor.execute(dataCompanyIprGeograPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprGeograVO> queryList(DataCompanyIprGeograQueryListCommand dataCompanyIprGeograQueryListCommand) {
        return dataCompanyIprGeograQueryCommandExecutor.execute(dataCompanyIprGeograQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprGeograExWarehouseVO> exWarehouse(DataCompanyIprGeograExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprGeograExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprGeograQueryCommandExecutor(DataCompanyIprGeograQueryCommandExecutor dataCompanyIprGeograQueryCommandExecutor) {
        this.dataCompanyIprGeograQueryCommandExecutor = dataCompanyIprGeograQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprGeograExWarehouseCommandExecutor(DataCompanyIprGeograExWarehouseCommandExecutor dataCompanyIprGeograExWarehouseCommandExecutor) {
        this.dataCompanyIprGeograExWarehouseCommandExecutor = dataCompanyIprGeograExWarehouseCommandExecutor;
    }
}
