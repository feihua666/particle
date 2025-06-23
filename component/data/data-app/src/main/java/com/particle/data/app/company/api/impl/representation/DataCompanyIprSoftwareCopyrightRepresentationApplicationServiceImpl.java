package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprSoftwareCopyrightQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprSoftwareCopyrightRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprSoftwareCopyrightPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprSoftwareCopyrightQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprSoftwareCopyrightVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprSoftwareCopyrightExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprSoftwareCopyrightExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权软件著作 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:01
 */
@Service
@CatchAndLog
public class DataCompanyIprSoftwareCopyrightRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprSoftwareCopyrightRepresentationApplicationService {

    private DataCompanyIprSoftwareCopyrightQueryCommandExecutor dataCompanyIprSoftwareCopyrightQueryCommandExecutor;
    private DataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor dataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprSoftwareCopyrightVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprSoftwareCopyrightQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprSoftwareCopyrightVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprSoftwareCopyrightQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprSoftwareCopyrightVO> pageQuery(DataCompanyIprSoftwareCopyrightPageQueryCommand dataCompanyIprSoftwareCopyrightPageQueryCommand) {
        return dataCompanyIprSoftwareCopyrightQueryCommandExecutor.execute(dataCompanyIprSoftwareCopyrightPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprSoftwareCopyrightVO> queryList(DataCompanyIprSoftwareCopyrightQueryListCommand dataCompanyIprSoftwareCopyrightQueryListCommand) {
        return dataCompanyIprSoftwareCopyrightQueryCommandExecutor.execute(dataCompanyIprSoftwareCopyrightQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprSoftwareCopyrightExWarehouseVO> exWarehouse(DataCompanyIprSoftwareCopyrightExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprSoftwareCopyrightQueryCommandExecutor(DataCompanyIprSoftwareCopyrightQueryCommandExecutor dataCompanyIprSoftwareCopyrightQueryCommandExecutor) {
        this.dataCompanyIprSoftwareCopyrightQueryCommandExecutor = dataCompanyIprSoftwareCopyrightQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor(DataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor dataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor) {
        this.dataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor = dataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor;
    }
}
