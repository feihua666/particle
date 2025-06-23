package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyVcProductWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyVcProductWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyVcProductExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcFinancingExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcProductExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业融资产品信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-27 11:23:36
 */
@Component
public class DataCompanyVcProductOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyVcProductWrapExWarehouseCommandExecutor dataCompanyVcProductWrapExWarehouseCommandExecutor;
    private DataCompanyVcProductWrapWarehouseCommandExecutor dataCompanyVcProductWrapWarehouseCommandExecutor;
    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_vc_product".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyVcProductExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyVcProductExWarehouseQueryCommand dataCompanyVcProductExWarehouseQueryCommand = (DataCompanyVcProductExWarehouseQueryCommand)openapiCommand.getEx1Param();
        return dataCompanyVcProductWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyVcProductExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyVcProductExWarehouseVO> response = (PageResponse<DataCompanyVcProductExWarehouseVO>) warehouseCommand.getParam();
        if (response != null && CollectionUtil.isNotEmpty(response.getData())) {
            DataCompanyVcProductExWarehouseVO next = response.getData().iterator().next();
            // 有一条有 companyId 则认为所有数据都有
            Long companyId = next.getCompanyId();
            if (companyId == null) {
                DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
                companyId = tryWarehouseCompanyAndGetCompanyId(dataCompanyExWarehouseQueryCommand);
                for (DataCompanyVcProductExWarehouseVO item : response.getData()) {
                    item.setCompanyId(companyId);
                }
            }
        }
        dataCompanyVcProductWrapWarehouseCommandExecutor.warehouse(response);
    }

    @Autowired
    public void setDataCompanyVcProductWrapExWarehouseCommandExecutor(DataCompanyVcProductWrapExWarehouseCommandExecutor dataCompanyVcProductWrapExWarehouseCommandExecutor) {
        this.dataCompanyVcProductWrapExWarehouseCommandExecutor = dataCompanyVcProductWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcProductWrapWarehouseCommandExecutor(DataCompanyVcProductWrapWarehouseCommandExecutor dataCompanyVcProductWrapWarehouseCommandExecutor) {
        this.dataCompanyVcProductWrapWarehouseCommandExecutor = dataCompanyVcProductWrapWarehouseCommandExecutor;
    }
}
