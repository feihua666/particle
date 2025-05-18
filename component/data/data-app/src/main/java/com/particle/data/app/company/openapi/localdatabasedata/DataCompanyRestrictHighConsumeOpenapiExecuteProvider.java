package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyRestrictHighConsumeWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyRestrictHighConsumeWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyRestrictHighConsumeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyRestrictHighConsumeExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业限制高消费信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-25 13:03:44
 */
@Component
public class DataCompanyRestrictHighConsumeOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyRestrictHighConsumeWrapExWarehouseCommandExecutor dataCompanyRestrictHighConsumeWrapExWarehouseCommandExecutor;
    private DataCompanyRestrictHighConsumeWrapWarehouseCommandExecutor dataCompanyRestrictHighConsumeWrapWarehouseCommandExecutor;

    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_restrict_high_consume".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyRestrictHighConsumeExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        // 企业id
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyRestrictHighConsumeExWarehouseQueryCommand dataCompanyRestrictHighConsumeExWarehouseQueryCommand = (DataCompanyRestrictHighConsumeExWarehouseQueryCommand)openapiCommand.getEx1Param();

        return dataCompanyRestrictHighConsumeWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand,dataCompanyRestrictHighConsumeExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyRestrictHighConsumeExWarehouseVO> response = (PageResponse<DataCompanyRestrictHighConsumeExWarehouseVO>) warehouseCommand.getParam();
        dataCompanyRestrictHighConsumeWrapWarehouseCommandExecutor.warehouse(response);

    }

    @Autowired
    public void setDataCompanyRestrictHighConsumeWrapExWarehouseCommandExecutor(DataCompanyRestrictHighConsumeWrapExWarehouseCommandExecutor dataCompanyRestrictHighConsumeWrapExWarehouseCommandExecutor) {
        this.dataCompanyRestrictHighConsumeWrapExWarehouseCommandExecutor = dataCompanyRestrictHighConsumeWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyRestrictHighConsumeWrapWarehouseCommandExecutor(DataCompanyRestrictHighConsumeWrapWarehouseCommandExecutor dataCompanyRestrictHighConsumeWrapWarehouseCommandExecutor) {
        this.dataCompanyRestrictHighConsumeWrapWarehouseCommandExecutor = dataCompanyRestrictHighConsumeWrapWarehouseCommandExecutor;
    }
}
