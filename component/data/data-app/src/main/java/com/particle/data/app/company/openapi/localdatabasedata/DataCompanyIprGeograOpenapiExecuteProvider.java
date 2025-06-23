package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyIprGeograWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyIprGeograWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprGeograExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprGeograExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业地理标识信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-06-17 10:42:40
 */
@Component
public class DataCompanyIprGeograOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyIprGeograWrapExWarehouseCommandExecutor dataCompanyIprGeograWrapExWarehouseCommandExecutor;
    private DataCompanyIprGeograWrapWarehouseCommandExecutor dataCompanyIprGeograWrapWarehouseCommandExecutor;

    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_ipr_geogra".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyIprGeograExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyIprGeograExWarehouseQueryCommand dataCompanyIprGeograExWarehouseQueryCommand = (DataCompanyIprGeograExWarehouseQueryCommand)openapiCommand.getEx1Param();
        return dataCompanyIprGeograWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand,dataCompanyIprGeograExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyIprGeograExWarehouseVO> dataCompanyIprGeograExWarehouseVOPageResponse = (PageResponse<DataCompanyIprGeograExWarehouseVO>) warehouseCommand.getParam();
        dataCompanyIprGeograWrapWarehouseCommandExecutor.warehouse(dataCompanyIprGeograExWarehouseVOPageResponse);
    }

    @Autowired
    public void setDataCompanyIprGeograWrapExWarehouseCommandExecutor(DataCompanyIprGeograWrapExWarehouseCommandExecutor dataCompanyIprGeograWrapExWarehouseCommandExecutor) {
        this.dataCompanyIprGeograWrapExWarehouseCommandExecutor = dataCompanyIprGeograWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprGeograWrapWarehouseCommandExecutor(DataCompanyIprGeograWrapWarehouseCommandExecutor dataCompanyIprGeograWrapWarehouseCommandExecutor) {
        this.dataCompanyIprGeograWrapWarehouseCommandExecutor = dataCompanyIprGeograWrapWarehouseCommandExecutor;
    }
}
