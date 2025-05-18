package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.company.executor.representation.DataCompanyBasicQueryCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyBasicExWarehouseCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyBasicRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyBasicPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyBasicQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyBasicExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.DataCompanyBasicVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyBasicExWarehouseVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 企业基本信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Service
@CatchAndLog
public class DataCompanyBasicRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyBasicRepresentationApplicationService {

    private DataCompanyBasicQueryCommandExecutor dataCompanyBasicQueryCommandExecutor;

    private DataCompanyBasicExWarehouseCommandExecutor dataCompanyBasicExWarehouseCommandExecutor;
    @Override
    public SingleResponse<DataCompanyBasicVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyBasicQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyBasicVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyBasicQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyBasicVO> pageQuery(DataCompanyBasicPageQueryCommand dataCompanyBasicPageQueryCommand) {
        return dataCompanyBasicQueryCommandExecutor.execute(dataCompanyBasicPageQueryCommand);
    }


    @Override
    public MultiResponse<DataCompanyBasicVO> queryList(DataCompanyBasicQueryListCommand dataCompanyBasicQueryListCommand) {
        return dataCompanyBasicQueryCommandExecutor.execute(dataCompanyBasicQueryListCommand);
    }

    @Override
    public SingleResponse<DataCompanyBasicExWarehouseVO> exWarehouse(DataCompanyBasicExWarehouseQueryCommand dataCompanyBasicExWarehouseQueryCommand) {
        return dataCompanyBasicExWarehouseCommandExecutor.exWarehouse(dataCompanyBasicExWarehouseQueryCommand);
    }

    @Autowired
    public void setDataCompanyBasicQueryCommandExecutor(DataCompanyBasicQueryCommandExecutor dataCompanyBasicQueryCommandExecutor) {
        this.dataCompanyBasicQueryCommandExecutor = dataCompanyBasicQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyBasicExWarehouseCommandExecutor(DataCompanyBasicExWarehouseCommandExecutor dataCompanyBasicExWarehouseCommandExecutor) {
        this.dataCompanyBasicExWarehouseCommandExecutor = dataCompanyBasicExWarehouseCommandExecutor;
    }
}
