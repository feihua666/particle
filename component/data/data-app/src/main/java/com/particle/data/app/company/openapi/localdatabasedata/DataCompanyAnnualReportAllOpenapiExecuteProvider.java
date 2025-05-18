package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyAnnualReportAllWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyAnnualReportAllWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportAllExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业年报 开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-25 15:35:13
 */
@Component
public class DataCompanyAnnualReportAllOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyAnnualReportAllWrapWarehouseCommandExecutor dataCompanyAnnualReportAllWrapWarehouseCommandExecutor;
    private DataCompanyAnnualReportAllWrapExWarehouseCommandExecutor dataCompanyAnnualReportAllWrapExWarehouseCommandExecutor;

    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_annual_report_all".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyAnnualReportAllExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        // 企业主体出库指令
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        // 年报基本信息出库指令
        DataCompanyAnnualReportExWarehouseQueryCommand dataCompanyAnnualReportExWarehouseQueryCommand = (DataCompanyAnnualReportExWarehouseQueryCommand)openapiCommand.getEx1Param();
        return dataCompanyAnnualReportAllWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand,
                dataCompanyAnnualReportExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyAnnualReportAllExWarehouseVO> response = (PageResponse<DataCompanyAnnualReportAllExWarehouseVO>) warehouseCommand.getParam();
        dataCompanyAnnualReportAllWrapWarehouseCommandExecutor.warehouse(response);
    }
    @Autowired
    public void setDataCompanyAnnualReportAllWarehouseCommandExecutor(DataCompanyAnnualReportAllWrapWarehouseCommandExecutor dataCompanyAnnualReportAllWrapWarehouseCommandExecutor) {
        this.dataCompanyAnnualReportAllWrapWarehouseCommandExecutor = dataCompanyAnnualReportAllWrapWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportAllWrapExWarehouseCommandExecutor(DataCompanyAnnualReportAllWrapExWarehouseCommandExecutor dataCompanyAnnualReportAllWrapExWarehouseCommandExecutor) {
        this.dataCompanyAnnualReportAllWrapExWarehouseCommandExecutor = dataCompanyAnnualReportAllWrapExWarehouseCommandExecutor;
    }
}
