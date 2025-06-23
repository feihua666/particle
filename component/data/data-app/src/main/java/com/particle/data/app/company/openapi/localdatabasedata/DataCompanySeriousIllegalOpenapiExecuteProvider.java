package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanySeriousIllegalWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanySeriousIllegalWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanySeriousIllegalExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySeriousIllegalExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyShareholderExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业严重违法信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-27 11:23:36
 */
@Component
public class DataCompanySeriousIllegalOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanySeriousIllegalWrapExWarehouseCommandExecutor dataCompanySeriousIllegalWrapExWarehouseCommandExecutor;
    private DataCompanySeriousIllegalWrapWarehouseCommandExecutor dataCompanySeriousIllegalWrapWarehouseCommandExecutor;

    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_serious_illegal".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanySeriousIllegalExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanySeriousIllegalExWarehouseQueryCommand dataCompanySeriousIllegalExWarehouseQueryCommand = (DataCompanySeriousIllegalExWarehouseQueryCommand)openapiCommand.getEx1Param();
        return dataCompanySeriousIllegalWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand,dataCompanySeriousIllegalExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanySeriousIllegalExWarehouseVO> response = (PageResponse<DataCompanySeriousIllegalExWarehouseVO>) warehouseCommand.getParam();
        if (response != null && CollectionUtil.isNotEmpty(response.getData())) {
            DataCompanySeriousIllegalExWarehouseVO next = response.getData().iterator().next();
            // 有一条有 companyId 则认为所有数据都有
            Long companyId = next.getCompanyId();
            if (companyId == null) {
                DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
                companyId = tryWarehouseCompanyAndGetCompanyId(dataCompanyExWarehouseQueryCommand);
                for (DataCompanySeriousIllegalExWarehouseVO item : response.getData()) {
                    item.setCompanyId(companyId);
                }
            }
        }
        dataCompanySeriousIllegalWrapWarehouseCommandExecutor.warehouse(response);
    }

    @Autowired
    public void setDataCompanySeriousIllegalWrapExWarehouseCommandExecutor(DataCompanySeriousIllegalWrapExWarehouseCommandExecutor dataCompanySeriousIllegalWrapExWarehouseCommandExecutor) {
        this.dataCompanySeriousIllegalWrapExWarehouseCommandExecutor = dataCompanySeriousIllegalWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanySeriousIllegalWrapWarehouseCommandExecutor(DataCompanySeriousIllegalWrapWarehouseCommandExecutor dataCompanySeriousIllegalWrapWarehouseCommandExecutor) {
        this.dataCompanySeriousIllegalWrapWarehouseCommandExecutor = dataCompanySeriousIllegalWrapWarehouseCommandExecutor;
    }
}
