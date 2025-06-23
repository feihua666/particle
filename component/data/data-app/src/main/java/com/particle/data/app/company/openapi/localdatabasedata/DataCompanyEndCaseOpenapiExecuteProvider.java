package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyEndCaseWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyEndCaseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyEndCaseExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyEndCaseExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业终本案件信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-25 13:03:44
 */
@Component
public class DataCompanyEndCaseOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyEndCaseWrapExWarehouseCommandExecutor dataCompanyEndCaseWrapExWarehouseCommandExecutor;
    private DataCompanyEndCaseWrapWarehouseCommandExecutor dataCompanyEndCaseWrapWarehouseCommandExecutor;


    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_end_case".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyEndCaseExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        // 企业主体
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyEndCaseExWarehouseQueryCommand dataCompanyEndCaseExWarehouseQueryCommand = (DataCompanyEndCaseExWarehouseQueryCommand)openapiCommand.getEx1Param();

        return dataCompanyEndCaseWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyEndCaseExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyEndCaseExWarehouseVO> response = (PageResponse<DataCompanyEndCaseExWarehouseVO>) warehouseCommand.getParam();
        dataCompanyEndCaseWrapWarehouseCommandExecutor.warehouse(response);
    }

    @Autowired
    public void setDataCompanyEndCaseWrapExWarehouseCommandExecutor(DataCompanyEndCaseWrapExWarehouseCommandExecutor dataCompanyEndCaseWrapExWarehouseCommandExecutor) {
        this.dataCompanyEndCaseWrapExWarehouseCommandExecutor = dataCompanyEndCaseWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyEndCaseWrapWarehouseCommandExecutor(DataCompanyEndCaseWrapWarehouseCommandExecutor dataCompanyEndCaseWrapWarehouseCommandExecutor) {
        this.dataCompanyEndCaseWrapWarehouseCommandExecutor = dataCompanyEndCaseWrapWarehouseCommandExecutor;
    }
}
