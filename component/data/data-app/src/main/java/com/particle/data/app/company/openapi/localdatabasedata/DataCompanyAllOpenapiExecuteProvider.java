package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyAllWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyAllWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAllExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAdministrativeLicenseExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAllExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业全貌 开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-06-05 10:11:23
 */
@Component
public class DataCompanyAllOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyAllWrapWarehouseCommandExecutor dataCompanyAllWrapWarehouseCommandExecutor;
    private DataCompanyAllWrapExWarehouseCommandExecutor dataCompanyAllWrapExWarehouseCommandExecutor;

    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_all".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public SingleResponse<DataCompanyAllExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        // 企业主体出库指令
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        // 出库指令
        DataCompanyAllExWarehouseQueryCommand dataCompanyAllExWarehouseQueryCommand = (DataCompanyAllExWarehouseQueryCommand)openapiCommand.getEx1Param();
        return dataCompanyAllWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand,
                dataCompanyAllExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        SingleResponse<DataCompanyAllExWarehouseVO> response = (SingleResponse<DataCompanyAllExWarehouseVO>) warehouseCommand.getParam();
        Long companyId = null;
        if (response != null && response.getData() != null) {
            DataCompanyAllExWarehouseVO data = response.getData();
            // 有一条有 companyId 则认为所有数据都有

            if (data.getBasic() == null) {
                DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
                companyId = tryWarehouseCompanyAndGetCompanyId(dataCompanyExWarehouseQueryCommand);
            }
        }
        dataCompanyAllWrapWarehouseCommandExecutor.warehouse(response,companyId);
    }
    @Autowired
    public void setDataCompanyAllWarehouseCommandExecutor(DataCompanyAllWrapWarehouseCommandExecutor dataCompanyAllWrapWarehouseCommandExecutor) {
        this.dataCompanyAllWrapWarehouseCommandExecutor = dataCompanyAllWrapWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAllWrapExWarehouseCommandExecutor(DataCompanyAllWrapExWarehouseCommandExecutor dataCompanyAllWrapExWarehouseCommandExecutor) {
        this.dataCompanyAllWrapExWarehouseCommandExecutor = dataCompanyAllWrapExWarehouseCommandExecutor;
    }
}
