package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyAbnormalQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyAbnormalRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAbnormalPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAbnormalQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAbnormalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAbnormalExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAbnormalExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAbnormalExWarehouseCommandExecutor;
/**
 * <p>
 * 企业经营异常 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-05-29 10:47:31
 */
@Service
@CatchAndLog
public class DataCompanyAbnormalRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAbnormalRepresentationApplicationService {

    private DataCompanyAbnormalQueryCommandExecutor dataCompanyAbnormalQueryCommandExecutor;
    private DataCompanyAbnormalExWarehouseCommandExecutor dataCompanyAbnormalExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAbnormalVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyAbnormalQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyAbnormalVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyAbnormalQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyAbnormalVO> pageQuery(DataCompanyAbnormalPageQueryCommand dataCompanyAbnormalPageQueryCommand) {
        return dataCompanyAbnormalQueryCommandExecutor.execute(dataCompanyAbnormalPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyAbnormalVO> queryList(DataCompanyAbnormalQueryListCommand dataCompanyAbnormalQueryListCommand) {
        return dataCompanyAbnormalQueryCommandExecutor.execute(dataCompanyAbnormalQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyAbnormalExWarehouseVO> exWarehouse(DataCompanyAbnormalExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyAbnormalExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyAbnormalQueryCommandExecutor(DataCompanyAbnormalQueryCommandExecutor dataCompanyAbnormalQueryCommandExecutor) {
        this.dataCompanyAbnormalQueryCommandExecutor = dataCompanyAbnormalQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAbnormalExWarehouseCommandExecutor(DataCompanyAbnormalExWarehouseCommandExecutor dataCompanyAbnormalExWarehouseCommandExecutor) {
        this.dataCompanyAbnormalExWarehouseCommandExecutor = dataCompanyAbnormalExWarehouseCommandExecutor;
    }
}
