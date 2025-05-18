package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyCaseFilingWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyCaseFilingWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyCaseFilingExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCaseFilingExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业立案信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-25 13:03:44
 */
@Component
public class DataCompanyCaseFilingOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {


    private DataCompanyCaseFilingWrapExWarehouseCommandExecutor dataCompanyCaseFilingWrapExWarehouseCommandExecutor;
    private DataCompanyCaseFilingWrapWarehouseCommandExecutor dataCompanyCaseFilingWrapWarehouseCommandExecutor;
    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_case_filing".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyCaseFilingExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        // 企业主体
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyCaseFilingExWarehouseQueryCommand dataCompanyCaseFilingExWarehouseQueryCommand = (DataCompanyCaseFilingExWarehouseQueryCommand)openapiCommand.getEx1Param();

        return dataCompanyCaseFilingWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand,dataCompanyCaseFilingExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyCaseFilingExWarehouseVO> response = (PageResponse<DataCompanyCaseFilingExWarehouseVO>) warehouseCommand.getParam();
        dataCompanyCaseFilingWrapWarehouseCommandExecutor.warehouse(response);
    }

    @Autowired
    public void setDataCompanyCaseFilingWrapExWarehouseCommandExecutor(DataCompanyCaseFilingWrapExWarehouseCommandExecutor dataCompanyCaseFilingWrapExWarehouseCommandExecutor) {
        this.dataCompanyCaseFilingWrapExWarehouseCommandExecutor = dataCompanyCaseFilingWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCaseFilingWrapWarehouseCommandExecutor(DataCompanyCaseFilingWrapWarehouseCommandExecutor dataCompanyCaseFilingWrapWarehouseCommandExecutor) {
        this.dataCompanyCaseFilingWrapWarehouseCommandExecutor = dataCompanyCaseFilingWrapWarehouseCommandExecutor;
    }
}
