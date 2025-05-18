package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyHonorQualificationWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyHonorQualificationWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyHonorQualificationExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyHonorQualificationExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业荣誉资质信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-27 11:23:36
 */
@Component
public class DataCompanyHonorQualificationOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyHonorQualificationWrapExWarehouseCommandExecutor dataCompanyHonorQualificationWrapExWarehouseCommandExecutor;
    private DataCompanyHonorQualificationWrapWarehouseCommandExecutor dataCompanyHonorQualificationWrapWarehouseCommandExecutor;

    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_honor_qualification".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyHonorQualificationExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyHonorQualificationExWarehouseQueryCommand dataCompanyHonorQualificationExWarehouseQueryCommand = (DataCompanyHonorQualificationExWarehouseQueryCommand)openapiCommand.getEx1Param();
        return dataCompanyHonorQualificationWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand,dataCompanyHonorQualificationExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyHonorQualificationExWarehouseVO> dataCompanyHonorQualificationExWarehouseVOPageResponse = (PageResponse<DataCompanyHonorQualificationExWarehouseVO>) warehouseCommand.getParam();
        dataCompanyHonorQualificationWrapWarehouseCommandExecutor.warehouse(dataCompanyHonorQualificationExWarehouseVOPageResponse);
    }

    @Autowired
    public void setDataCompanyHonorQualificationWrapExWarehouseCommandExecutor(DataCompanyHonorQualificationWrapExWarehouseCommandExecutor dataCompanyHonorQualificationWrapExWarehouseCommandExecutor) {
        this.dataCompanyHonorQualificationWrapExWarehouseCommandExecutor = dataCompanyHonorQualificationWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyHonorQualificationWrapWarehouseCommandExecutor(DataCompanyHonorQualificationWrapWarehouseCommandExecutor dataCompanyHonorQualificationWrapWarehouseCommandExecutor) {
        this.dataCompanyHonorQualificationWrapWarehouseCommandExecutor = dataCompanyHonorQualificationWrapWarehouseCommandExecutor;
    }
}
