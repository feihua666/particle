package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprWorkCopyrightQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprWorkCopyrightRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprWorkCopyrightPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprWorkCopyrightQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprWorkCopyrightVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprWorkCopyrightExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprWorkCopyrightExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprWorkCopyrightExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权作品著作 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:45
 */
@Service
@CatchAndLog
public class DataCompanyIprWorkCopyrightRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprWorkCopyrightRepresentationApplicationService {

    private DataCompanyIprWorkCopyrightQueryCommandExecutor dataCompanyIprWorkCopyrightQueryCommandExecutor;
    private DataCompanyIprWorkCopyrightExWarehouseCommandExecutor dataCompanyIprWorkCopyrightExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprWorkCopyrightVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprWorkCopyrightQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprWorkCopyrightVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprWorkCopyrightQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprWorkCopyrightVO> pageQuery(DataCompanyIprWorkCopyrightPageQueryCommand dataCompanyIprWorkCopyrightPageQueryCommand) {
        return dataCompanyIprWorkCopyrightQueryCommandExecutor.execute(dataCompanyIprWorkCopyrightPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprWorkCopyrightVO> queryList(DataCompanyIprWorkCopyrightQueryListCommand dataCompanyIprWorkCopyrightQueryListCommand) {
        return dataCompanyIprWorkCopyrightQueryCommandExecutor.execute(dataCompanyIprWorkCopyrightQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprWorkCopyrightExWarehouseVO> exWarehouse(DataCompanyIprWorkCopyrightExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprWorkCopyrightExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprWorkCopyrightQueryCommandExecutor(DataCompanyIprWorkCopyrightQueryCommandExecutor dataCompanyIprWorkCopyrightQueryCommandExecutor) {
        this.dataCompanyIprWorkCopyrightQueryCommandExecutor = dataCompanyIprWorkCopyrightQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprWorkCopyrightExWarehouseCommandExecutor(DataCompanyIprWorkCopyrightExWarehouseCommandExecutor dataCompanyIprWorkCopyrightExWarehouseCommandExecutor) {
        this.dataCompanyIprWorkCopyrightExWarehouseCommandExecutor = dataCompanyIprWorkCopyrightExWarehouseCommandExecutor;
    }
}
