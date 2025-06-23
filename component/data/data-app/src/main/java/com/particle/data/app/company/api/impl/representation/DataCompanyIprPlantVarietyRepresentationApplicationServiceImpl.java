package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprPlantVarietyQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprPlantVarietyRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPlantVarietyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPlantVarietyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPlantVarietyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPlantVarietyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPlantVarietyExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权植物新品种 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:40
 */
@Service
@CatchAndLog
public class DataCompanyIprPlantVarietyRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPlantVarietyRepresentationApplicationService {

    private DataCompanyIprPlantVarietyQueryCommandExecutor dataCompanyIprPlantVarietyQueryCommandExecutor;
    private DataCompanyIprPlantVarietyExWarehouseCommandExecutor dataCompanyIprPlantVarietyExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPlantVarietyVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprPlantVarietyQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPlantVarietyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprPlantVarietyQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprPlantVarietyVO> pageQuery(DataCompanyIprPlantVarietyPageQueryCommand dataCompanyIprPlantVarietyPageQueryCommand) {
        return dataCompanyIprPlantVarietyQueryCommandExecutor.execute(dataCompanyIprPlantVarietyPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprPlantVarietyVO> queryList(DataCompanyIprPlantVarietyQueryListCommand dataCompanyIprPlantVarietyQueryListCommand) {
        return dataCompanyIprPlantVarietyQueryCommandExecutor.execute(dataCompanyIprPlantVarietyQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprPlantVarietyExWarehouseVO> exWarehouse(DataCompanyIprPlantVarietyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprPlantVarietyExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprPlantVarietyQueryCommandExecutor(DataCompanyIprPlantVarietyQueryCommandExecutor dataCompanyIprPlantVarietyQueryCommandExecutor) {
        this.dataCompanyIprPlantVarietyQueryCommandExecutor = dataCompanyIprPlantVarietyQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPlantVarietyExWarehouseCommandExecutor(DataCompanyIprPlantVarietyExWarehouseCommandExecutor dataCompanyIprPlantVarietyExWarehouseCommandExecutor) {
        this.dataCompanyIprPlantVarietyExWarehouseCommandExecutor = dataCompanyIprPlantVarietyExWarehouseCommandExecutor;
    }
}
