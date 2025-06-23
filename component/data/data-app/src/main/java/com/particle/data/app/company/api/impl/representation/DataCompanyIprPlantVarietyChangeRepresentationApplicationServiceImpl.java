package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprPlantVarietyChangeQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprPlantVarietyChangeRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPlantVarietyChangePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPlantVarietyChangeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPlantVarietyChangeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPlantVarietyChangeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyChangeExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权植物新品种变更信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:52
 */
@Service
@CatchAndLog
public class DataCompanyIprPlantVarietyChangeRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPlantVarietyChangeRepresentationApplicationService {

    private DataCompanyIprPlantVarietyChangeQueryCommandExecutor dataCompanyIprPlantVarietyChangeQueryCommandExecutor;
    private DataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor dataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPlantVarietyChangeVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprPlantVarietyChangeQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPlantVarietyChangeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprPlantVarietyChangeQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprPlantVarietyChangeVO> pageQuery(DataCompanyIprPlantVarietyChangePageQueryCommand dataCompanyIprPlantVarietyChangePageQueryCommand) {
        return dataCompanyIprPlantVarietyChangeQueryCommandExecutor.execute(dataCompanyIprPlantVarietyChangePageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprPlantVarietyChangeVO> queryList(DataCompanyIprPlantVarietyChangeQueryListCommand dataCompanyIprPlantVarietyChangeQueryListCommand) {
        return dataCompanyIprPlantVarietyChangeQueryCommandExecutor.execute(dataCompanyIprPlantVarietyChangeQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprPlantVarietyChangeExWarehouseVO> exWarehouse(DataCompanyIprPlantVarietyChangeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprPlantVarietyChangeQueryCommandExecutor(DataCompanyIprPlantVarietyChangeQueryCommandExecutor dataCompanyIprPlantVarietyChangeQueryCommandExecutor) {
        this.dataCompanyIprPlantVarietyChangeQueryCommandExecutor = dataCompanyIprPlantVarietyChangeQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor(DataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor dataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor) {
        this.dataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor = dataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor;
    }
}
