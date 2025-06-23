package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyIprTrademarkAllWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyIprTrademarkAllWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkAllExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权商标信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-06-16 16:17:21
 */
@Component
public class DataCompanyIprTrademarkAllOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {


    private DataCompanyIprTrademarkAllWrapWarehouseCommandExecutor dataCompanyIprTrademarkAllWrapWarehouseCommandExecutor;
    private DataCompanyIprTrademarkAllWrapExWarehouseCommandExecutor dataCompanyIprTrademarkAllWrapExWarehouseCommandExecutor;

    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_ipr_trademark_all".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyIprTrademarkAllExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        // 企业主体
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyIprTrademarkExWarehouseQueryCommand dataCompanyIprTrademarkExWarehouseQueryCommand = (DataCompanyIprTrademarkExWarehouseQueryCommand)openapiCommand.getEx1Param();
        return dataCompanyIprTrademarkAllWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyIprTrademarkExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyIprTrademarkAllExWarehouseVO> response = (PageResponse<DataCompanyIprTrademarkAllExWarehouseVO>) warehouseCommand.getParam();
        dataCompanyIprTrademarkAllWrapWarehouseCommandExecutor.warehouse(response);
    }

    @Autowired
    public void setDataCompanyIprTrademarkAllWarehouseCommandExecutor(DataCompanyIprTrademarkAllWrapWarehouseCommandExecutor dataCompanyIprTrademarkAllWrapWarehouseCommandExecutor) {
        this.dataCompanyIprTrademarkAllWrapWarehouseCommandExecutor = dataCompanyIprTrademarkAllWrapWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkAllExWarehouseCommandExecutor(DataCompanyIprTrademarkAllWrapExWarehouseCommandExecutor dataCompanyIprTrademarkAllWrapExWarehouseCommandExecutor) {
        this.dataCompanyIprTrademarkAllWrapExWarehouseCommandExecutor = dataCompanyIprTrademarkAllWrapExWarehouseCommandExecutor;
    }
}
