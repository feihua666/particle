package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyIprPlantVarietyWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyIprPlantVarietyWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPlantVarietyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业植物新品种信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-06-17 10:42:40
 */
@Component
public class DataCompanyIprPlantVarietyOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyIprPlantVarietyWrapExWarehouseCommandExecutor dataCompanyIprPlantVarietyWrapExWarehouseCommandExecutor;
    private DataCompanyIprPlantVarietyWrapWarehouseCommandExecutor dataCompanyIprPlantVarietyWrapWarehouseCommandExecutor;

    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_ipr_plant_variety".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyIprPlantVarietyExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyIprPlantVarietyExWarehouseQueryCommand dataCompanyIprPlantVarietyExWarehouseQueryCommand = (DataCompanyIprPlantVarietyExWarehouseQueryCommand)openapiCommand.getEx1Param();
        return dataCompanyIprPlantVarietyWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand,dataCompanyIprPlantVarietyExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyIprPlantVarietyExWarehouseVO> dataCompanyIprPlantVarietyExWarehouseVOPageResponse = (PageResponse<DataCompanyIprPlantVarietyExWarehouseVO>) warehouseCommand.getParam();
        dataCompanyIprPlantVarietyWrapWarehouseCommandExecutor.warehouse(dataCompanyIprPlantVarietyExWarehouseVOPageResponse);
    }

    @Autowired
    public void setDataCompanyIprPlantVarietyWrapExWarehouseCommandExecutor(DataCompanyIprPlantVarietyWrapExWarehouseCommandExecutor dataCompanyIprPlantVarietyWrapExWarehouseCommandExecutor) {
        this.dataCompanyIprPlantVarietyWrapExWarehouseCommandExecutor = dataCompanyIprPlantVarietyWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPlantVarietyWrapWarehouseCommandExecutor(DataCompanyIprPlantVarietyWrapWarehouseCommandExecutor dataCompanyIprPlantVarietyWrapWarehouseCommandExecutor) {
        this.dataCompanyIprPlantVarietyWrapWarehouseCommandExecutor = dataCompanyIprPlantVarietyWrapWarehouseCommandExecutor;
    }
}
