package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyIprPatentAllWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyIprPatentAllWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentAllExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权专利信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-25 13:03:44
 */
@Component
public class DataCompanyIprPatentAllOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {


    private DataCompanyIprPatentAllWrapWarehouseCommandExecutor dataCompanyIprPatentAllWrapWarehouseCommandExecutor;
    private DataCompanyIprPatentAllWrapExWarehouseCommandExecutor dataCompanyIprPatentAllWrapExWarehouseCommandExecutor;

    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_ipr_patent_all".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyIprPatentAllExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        // 企业主体
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyIprPatentExWarehouseQueryCommand dataCompanyIprPatentExWarehouseQueryCommand = (DataCompanyIprPatentExWarehouseQueryCommand)openapiCommand.getEx1Param();
        return dataCompanyIprPatentAllWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyIprPatentExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyIprPatentAllExWarehouseVO> response = (PageResponse<DataCompanyIprPatentAllExWarehouseVO>) warehouseCommand.getParam();
        dataCompanyIprPatentAllWrapWarehouseCommandExecutor.warehouse(response);
    }

    @Autowired
    public void setDataCompanyIprPatentAllWarehouseCommandExecutor(DataCompanyIprPatentAllWrapWarehouseCommandExecutor dataCompanyIprPatentAllWrapWarehouseCommandExecutor) {
        this.dataCompanyIprPatentAllWrapWarehouseCommandExecutor = dataCompanyIprPatentAllWrapWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentAllExWarehouseCommandExecutor(DataCompanyIprPatentAllWrapExWarehouseCommandExecutor dataCompanyIprPatentAllWrapExWarehouseCommandExecutor) {
        this.dataCompanyIprPatentAllWrapExWarehouseCommandExecutor = dataCompanyIprPatentAllWrapExWarehouseCommandExecutor;
    }
}
