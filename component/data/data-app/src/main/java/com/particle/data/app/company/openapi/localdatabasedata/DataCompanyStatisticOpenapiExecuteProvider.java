package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyStatisticWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyStatisticWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyStatisticExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyStatisticExWarehouseVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业统计信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-25 13:03:44
 */
@Component
public class DataCompanyStatisticOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyStatisticWrapExWarehouseCommandExecutor dataCompanyStatisticWrapExWarehouseCommandExecutor;
    private DataCompanyStatisticWrapWarehouseCommandExecutor dataCompanyStatisticWrapWarehouseCommandExecutor;


    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_statistic".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public SingleResponse<DataCompanyStatisticExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        // 企业主体
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyStatisticExWarehouseQueryCommand dataCompanyStatisticExWarehouseQueryCommand = (DataCompanyStatisticExWarehouseQueryCommand)openapiCommand.getEx1Param();

        return dataCompanyStatisticWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyStatisticExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        SingleResponse<DataCompanyStatisticExWarehouseVO> response = (SingleResponse<DataCompanyStatisticExWarehouseVO>) warehouseCommand.getParam();
        dataCompanyStatisticWrapWarehouseCommandExecutor.warehouse(response);
    }

    @Autowired
    public void setDataCompanyStatisticWrapExWarehouseCommandExecutor(DataCompanyStatisticWrapExWarehouseCommandExecutor dataCompanyStatisticWrapExWarehouseCommandExecutor) {
        this.dataCompanyStatisticWrapExWarehouseCommandExecutor = dataCompanyStatisticWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyStatisticWrapWarehouseCommandExecutor(DataCompanyStatisticWrapWarehouseCommandExecutor dataCompanyStatisticWrapWarehouseCommandExecutor) {
        this.dataCompanyStatisticWrapWarehouseCommandExecutor = dataCompanyStatisticWrapWarehouseCommandExecutor;
    }
}
