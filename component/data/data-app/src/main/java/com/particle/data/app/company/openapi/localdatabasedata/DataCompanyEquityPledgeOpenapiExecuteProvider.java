package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyEquityPledgeWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyEquityPledgeWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyEquityPledgeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAdministrativeLicenseExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyEquityPledgeExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业股权出质信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-25 13:03:44
 */
@Component
public class DataCompanyEquityPledgeOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyEquityPledgeWrapExWarehouseCommandExecutor dataCompanyEquityPledgeWrapExWarehouseCommandExecutor;
    private DataCompanyEquityPledgeWrapWarehouseCommandExecutor dataCompanyEquityPledgeWrapWarehouseCommandExecutor;


    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_equity_pledge".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyEquityPledgeExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        // 企业主体
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyEquityPledgeExWarehouseQueryCommand dataCompanyEquityPledgeExWarehouseQueryCommand = (DataCompanyEquityPledgeExWarehouseQueryCommand)openapiCommand.getEx1Param();

        return dataCompanyEquityPledgeWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyEquityPledgeExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyEquityPledgeExWarehouseVO> response = (PageResponse<DataCompanyEquityPledgeExWarehouseVO>) warehouseCommand.getParam();
        if (response != null && CollectionUtil.isNotEmpty(response.getData())) {
            DataCompanyEquityPledgeExWarehouseVO next = response.getData().iterator().next();
            // 有一条有 companyId 则认为所有数据都有
            Long companyId = next.getCompanyId();
            if (companyId == null) {
                DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
                companyId = tryWarehouseCompanyAndGetCompanyId(dataCompanyExWarehouseQueryCommand);
                for (DataCompanyEquityPledgeExWarehouseVO item : response.getData()) {
                    item.setCompanyId(companyId);
                }
            }
        }
        dataCompanyEquityPledgeWrapWarehouseCommandExecutor.warehouse(response);
    }

    @Autowired
    public void setDataCompanyEquityPledgeWrapExWarehouseCommandExecutor(DataCompanyEquityPledgeWrapExWarehouseCommandExecutor dataCompanyEquityPledgeWrapExWarehouseCommandExecutor) {
        this.dataCompanyEquityPledgeWrapExWarehouseCommandExecutor = dataCompanyEquityPledgeWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyEquityPledgeWrapWarehouseCommandExecutor(DataCompanyEquityPledgeWrapWarehouseCommandExecutor dataCompanyEquityPledgeWrapWarehouseCommandExecutor) {
        this.dataCompanyEquityPledgeWrapWarehouseCommandExecutor = dataCompanyEquityPledgeWrapWarehouseCommandExecutor;
    }
}
