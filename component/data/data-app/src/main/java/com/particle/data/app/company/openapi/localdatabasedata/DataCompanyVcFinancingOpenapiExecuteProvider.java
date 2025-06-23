package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyVcFinancingWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyVcFinancingWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyVcFinancingExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySeriousIllegalExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcFinancingExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业融资信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-27 11:23:36
 */
@Component
public class DataCompanyVcFinancingOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyVcFinancingWrapExWarehouseCommandExecutor dataCompanyVcFinancingWrapExWarehouseCommandExecutor;
    private DataCompanyVcFinancingWrapWarehouseCommandExecutor dataCompanyVcFinancingWrapWarehouseCommandExecutor;

    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_vc_financing".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyVcFinancingExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyVcFinancingExWarehouseQueryCommand dataCompanyVcFinancingExWarehouseQueryCommand = (DataCompanyVcFinancingExWarehouseQueryCommand)openapiCommand.getEx1Param();
        return dataCompanyVcFinancingWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyVcFinancingExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyVcFinancingExWarehouseVO> response = (PageResponse<DataCompanyVcFinancingExWarehouseVO>) warehouseCommand.getParam();
        if (response != null && CollectionUtil.isNotEmpty(response.getData())) {
            DataCompanyVcFinancingExWarehouseVO next = response.getData().iterator().next();
            // 有一条有 companyId 则认为所有数据都有
            Long companyId = next.getCompanyId();
            if (companyId == null) {
                DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
                companyId = tryWarehouseCompanyAndGetCompanyId(dataCompanyExWarehouseQueryCommand);
                for (DataCompanyVcFinancingExWarehouseVO item : response.getData()) {
                    item.setCompanyId(companyId);
                }
            }
        }
        dataCompanyVcFinancingWrapWarehouseCommandExecutor.warehouse(response);
    }

    @Autowired
    public void setDataCompanyVcFinancingWrapExWarehouseCommandExecutor(DataCompanyVcFinancingWrapExWarehouseCommandExecutor dataCompanyVcFinancingWrapExWarehouseCommandExecutor) {
        this.dataCompanyVcFinancingWrapExWarehouseCommandExecutor = dataCompanyVcFinancingWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcFinancingWrapWarehouseCommandExecutor(DataCompanyVcFinancingWrapWarehouseCommandExecutor dataCompanyVcFinancingWrapWarehouseCommandExecutor) {
        this.dataCompanyVcFinancingWrapWarehouseCommandExecutor = dataCompanyVcFinancingWrapWarehouseCommandExecutor;
    }
}
