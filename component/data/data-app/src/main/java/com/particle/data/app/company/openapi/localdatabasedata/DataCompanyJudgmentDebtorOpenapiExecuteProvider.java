package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyJudgmentDebtorWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyJudgmentDebtorWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyJudgmentDebtorExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDebtorExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业被执行人信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-27 11:23:36
 */
@Component
public class DataCompanyJudgmentDebtorOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyJudgmentDebtorWrapExWarehouseCommandExecutor dataCompanyJudgmentDebtorWrapExWarehouseCommandExecutor;
    private DataCompanyJudgmentDebtorWrapWarehouseCommandExecutor dataCompanyJudgmentDebtorWrapWarehouseCommandExecutor;

    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_judgment_debtor".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyJudgmentDebtorExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyJudgmentDebtorExWarehouseQueryCommand dataCompanyJudgmentDebtorExWarehouseQueryCommand = (DataCompanyJudgmentDebtorExWarehouseQueryCommand)openapiCommand.getEx1Param();
        return dataCompanyJudgmentDebtorWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand,dataCompanyJudgmentDebtorExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyJudgmentDebtorExWarehouseVO> dataCompanyJudgmentDebtorExWarehouseVOPageResponse = (PageResponse<DataCompanyJudgmentDebtorExWarehouseVO>) warehouseCommand.getParam();
        dataCompanyJudgmentDebtorWrapWarehouseCommandExecutor.warehouse(dataCompanyJudgmentDebtorExWarehouseVOPageResponse);
    }

    @Autowired
    public void setDataCompanyJudgmentDebtorWrapExWarehouseCommandExecutor(DataCompanyJudgmentDebtorWrapExWarehouseCommandExecutor dataCompanyJudgmentDebtorWrapExWarehouseCommandExecutor) {
        this.dataCompanyJudgmentDebtorWrapExWarehouseCommandExecutor = dataCompanyJudgmentDebtorWrapExWarehouseCommandExecutor;
    }

    @Autowired
    public void setDataCompanyJudgmentDebtorWrapWarehouseCommandExecutor(DataCompanyJudgmentDebtorWrapWarehouseCommandExecutor dataCompanyJudgmentDebtorWrapWarehouseCommandExecutor) {
        this.dataCompanyJudgmentDebtorWrapWarehouseCommandExecutor = dataCompanyJudgmentDebtorWrapWarehouseCommandExecutor;
    }
}
