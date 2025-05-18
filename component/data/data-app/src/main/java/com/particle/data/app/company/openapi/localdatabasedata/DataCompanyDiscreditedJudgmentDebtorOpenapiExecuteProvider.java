package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyDiscreditedJudgmentDebtorWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyDiscreditedJudgmentDebtorWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDiscreditedJudgmentDebtorExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业失信被执行人信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-27 11:23:36
 */
@Component
public class DataCompanyDiscreditedJudgmentDebtorOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyDiscreditedJudgmentDebtorWrapExWarehouseCommandExecutor dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor;
    private DataCompanyDiscreditedJudgmentDebtorWrapWarehouseCommandExecutor dataCompanyDiscreditedJudgmentDebtorWrapWarehouseCommandExecutor;

    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_discredited_judgment_debtor".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand dataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand = (DataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand)openapiCommand.getEx1Param();
        return dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand,dataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO> dataCompanyDiscreditedJudgmentDebtorExWarehouseVOPageResponse = (PageResponse<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO>) warehouseCommand.getParam();
        dataCompanyDiscreditedJudgmentDebtorWrapWarehouseCommandExecutor.warehouse(dataCompanyDiscreditedJudgmentDebtorExWarehouseVOPageResponse);
    }

    @Autowired
    public void setDataCompanyDiscreditedJudgmentDebtorWrapWarehouseCommandExecutor(DataCompanyDiscreditedJudgmentDebtorWrapWarehouseCommandExecutor dataCompanyDiscreditedJudgmentDebtorWrapWarehouseCommandExecutor) {
        this.dataCompanyDiscreditedJudgmentDebtorWrapWarehouseCommandExecutor = dataCompanyDiscreditedJudgmentDebtorWrapWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor(DataCompanyDiscreditedJudgmentDebtorWrapExWarehouseCommandExecutor dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor) {
        this.dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor = dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor;
    }
}
