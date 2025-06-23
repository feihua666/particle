package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyIprIntegratedCircuitWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyIprIntegratedCircuitWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprIntegratedCircuitExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprIntegratedCircuitExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业集成电路信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-06-17 11:23:33
 */
@Component
public class DataCompanyIprIntegratedCircuitOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyIprIntegratedCircuitWrapExWarehouseCommandExecutor dataCompanyIprIntegratedCircuitWrapExWarehouseCommandExecutor;
    private DataCompanyIprIntegratedCircuitWrapWarehouseCommandExecutor dataCompanyIprIntegratedCircuitWrapWarehouseCommandExecutor;

    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_ipr_integrated_circuit".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyIprIntegratedCircuitExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyIprIntegratedCircuitExWarehouseQueryCommand dataCompanyIprIntegratedCircuitExWarehouseQueryCommand = (DataCompanyIprIntegratedCircuitExWarehouseQueryCommand)openapiCommand.getEx1Param();
        return dataCompanyIprIntegratedCircuitWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand,dataCompanyIprIntegratedCircuitExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyIprIntegratedCircuitExWarehouseVO> dataCompanyIprIntegratedCircuitExWarehouseVOPageResponse = (PageResponse<DataCompanyIprIntegratedCircuitExWarehouseVO>) warehouseCommand.getParam();
        dataCompanyIprIntegratedCircuitWrapWarehouseCommandExecutor.warehouse(dataCompanyIprIntegratedCircuitExWarehouseVOPageResponse);
    }

    @Autowired
    public void setDataCompanyIprIntegratedCircuitWrapExWarehouseCommandExecutor(DataCompanyIprIntegratedCircuitWrapExWarehouseCommandExecutor dataCompanyIprIntegratedCircuitWrapExWarehouseCommandExecutor) {
        this.dataCompanyIprIntegratedCircuitWrapExWarehouseCommandExecutor = dataCompanyIprIntegratedCircuitWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprIntegratedCircuitWrapWarehouseCommandExecutor(DataCompanyIprIntegratedCircuitWrapWarehouseCommandExecutor dataCompanyIprIntegratedCircuitWrapWarehouseCommandExecutor) {
        this.dataCompanyIprIntegratedCircuitWrapWarehouseCommandExecutor = dataCompanyIprIntegratedCircuitWrapWarehouseCommandExecutor;
    }
}
