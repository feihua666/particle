package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyIprPledgeWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyIprPledgeWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPledgeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyHonorQualificationExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPledgeExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权出质信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-25 13:03:44
 */
@Component
public class DataCompanyIprPledgeOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyIprPledgeWrapExWarehouseCommandExecutor dataCompanyIprPledgeWrapExWarehouseCommandExecutor;
    private DataCompanyIprPledgeWrapWarehouseCommandExecutor dataCompanyIprPledgeWrapWarehouseCommandExecutor;


    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_ipr_pledge".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyIprPledgeExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        // 企业主体
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyIprPledgeExWarehouseQueryCommand dataCompanyIprPledgeExWarehouseQueryCommand = (DataCompanyIprPledgeExWarehouseQueryCommand)openapiCommand.getEx1Param();

        return dataCompanyIprPledgeWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyIprPledgeExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyIprPledgeExWarehouseVO> response = (PageResponse<DataCompanyIprPledgeExWarehouseVO>) warehouseCommand.getParam();
        if (response != null && CollectionUtil.isNotEmpty(response.getData())) {
            DataCompanyIprPledgeExWarehouseVO next = response.getData().iterator().next();
            // 有一条有 companyId 则认为所有数据都有
            Long companyId = next.getCompanyId();
            if (companyId == null) {
                DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
                companyId = tryWarehouseCompanyAndGetCompanyId(dataCompanyExWarehouseQueryCommand);
                for (DataCompanyIprPledgeExWarehouseVO item : response.getData()) {
                    item.setCompanyId(companyId);
                }
            }
        }
        dataCompanyIprPledgeWrapWarehouseCommandExecutor.warehouse(response);
    }

    @Autowired
    public void setDataCompanyIprPledgeWrapExWarehouseCommandExecutor(DataCompanyIprPledgeWrapExWarehouseCommandExecutor dataCompanyIprPledgeWrapExWarehouseCommandExecutor) {
        this.dataCompanyIprPledgeWrapExWarehouseCommandExecutor = dataCompanyIprPledgeWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPledgeWrapWarehouseCommandExecutor(DataCompanyIprPledgeWrapWarehouseCommandExecutor dataCompanyIprPledgeWrapWarehouseCommandExecutor) {
        this.dataCompanyIprPledgeWrapWarehouseCommandExecutor = dataCompanyIprPledgeWrapWarehouseCommandExecutor;
    }
}
