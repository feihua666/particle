package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanySpotCheckWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanySpotCheckWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanySpotCheckExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySpotCheckExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业抽查检查信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-25 13:03:44
 */
@Component
public class DataCompanySpotCheckOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanySpotCheckWrapExWarehouseCommandExecutor dataCompanySpotCheckWrapExWarehouseCommandExecutor;
    private DataCompanySpotCheckWrapWarehouseCommandExecutor dataCompanySpotCheckWrapWarehouseCommandExecutor;


    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_spot_check".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanySpotCheckExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        // 企业主体
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanySpotCheckExWarehouseQueryCommand dataCompanySpotCheckExWarehouseQueryCommand = (DataCompanySpotCheckExWarehouseQueryCommand)openapiCommand.getEx1Param();
        return dataCompanySpotCheckWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanySpotCheckExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanySpotCheckExWarehouseVO> response = (PageResponse<DataCompanySpotCheckExWarehouseVO>) warehouseCommand.getParam();
        if (response != null && CollectionUtil.isNotEmpty(response.getData())) {
            DataCompanySpotCheckExWarehouseVO next = response.getData().iterator().next();
            // 有一条有 companyId 则认为所有数据都有
            Long companyId = next.getCompanyId();
            if (companyId == null) {
                DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
                companyId = tryWarehouseCompanyAndGetCompanyId(dataCompanyExWarehouseQueryCommand);
                for (DataCompanySpotCheckExWarehouseVO item : response.getData()) {
                    item.setCompanyId(companyId);
                }
            }
        }
        dataCompanySpotCheckWrapWarehouseCommandExecutor.warehouse(response);
    }

    @Autowired
    public void setDataCompanySpotCheckWrapExWarehouseCommandExecutor(DataCompanySpotCheckWrapExWarehouseCommandExecutor dataCompanySpotCheckWrapExWarehouseCommandExecutor) {
        this.dataCompanySpotCheckWrapExWarehouseCommandExecutor = dataCompanySpotCheckWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanySpotCheckWrapWarehouseCommandExecutor(DataCompanySpotCheckWrapWarehouseCommandExecutor dataCompanySpotCheckWrapWarehouseCommandExecutor) {
        this.dataCompanySpotCheckWrapWarehouseCommandExecutor = dataCompanySpotCheckWrapWarehouseCommandExecutor;
    }
}
