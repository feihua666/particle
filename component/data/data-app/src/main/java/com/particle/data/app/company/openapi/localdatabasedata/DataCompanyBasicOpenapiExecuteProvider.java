package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyBasicWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyBasicWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyBasicExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyBasicExWarehouseVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业基本信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-25 13:03:44
 */
@Component
public class DataCompanyBasicOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyBasicWrapExWarehouseCommandExecutor dataCompanyBasicWrapExWarehouseCommandExecutor;
    private DataCompanyBasicWrapWarehouseCommandExecutor dataCompanyBasicWrapWarehouseCommandExecutor;

    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_basic".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public SingleResponse<DataCompanyBasicExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyBasicExWarehouseQueryCommand dataCompanyBasicExWarehouseQueryCommand = ((DataCompanyBasicExWarehouseQueryCommand) openapiCommand.getEx1Param());
        return dataCompanyBasicWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand,dataCompanyBasicExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        SingleResponse<DataCompanyBasicExWarehouseVO> dataCompanyBasicExWarehouseVOSingleResponse = (SingleResponse<DataCompanyBasicExWarehouseVO>) warehouseCommand.getParam();
        dataCompanyBasicWrapWarehouseCommandExecutor.warehouse(dataCompanyBasicExWarehouseVOSingleResponse);
    }


    @Autowired
    public void setDataCompanyBasicWrapExWarehouseCommandExecutor(DataCompanyBasicWrapExWarehouseCommandExecutor dataCompanyBasicWrapExWarehouseCommandExecutor) {
        this.dataCompanyBasicWrapExWarehouseCommandExecutor = dataCompanyBasicWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyBasicWrapWarehouseCommandExecutor(DataCompanyBasicWrapWarehouseCommandExecutor dataCompanyBasicWrapWarehouseCommandExecutor) {
        this.dataCompanyBasicWrapWarehouseCommandExecutor = dataCompanyBasicWrapWarehouseCommandExecutor;
    }
}
