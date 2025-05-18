package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyJudgmentDocumentWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyJudgmentDocumentWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyJudgmentDocumentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业裁判文书信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-25 13:03:44
 */
@Component
public class DataCompanyJudgmentDocumentOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyJudgmentDocumentWrapExWarehouseCommandExecutor dataCompanyJudgmentDocumentWrapExWarehouseCommandExecutor;
    private DataCompanyJudgmentDocumentWrapWarehouseCommandExecutor dataCompanyJudgmentDocumentWrapWarehouseCommandExecutor;

    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_judgment_document".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyJudgmentDocumentExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        // 企业id
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyJudgmentDocumentExWarehouseQueryCommand dataCompanyJudgmentDocumentExWarehouseQueryCommand = (DataCompanyJudgmentDocumentExWarehouseQueryCommand)openapiCommand.getEx1Param();
        return dataCompanyJudgmentDocumentWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyJudgmentDocumentExWarehouseQueryCommand);

    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyJudgmentDocumentExWarehouseVO> response = (PageResponse<DataCompanyJudgmentDocumentExWarehouseVO>) warehouseCommand.getParam();
        dataCompanyJudgmentDocumentWrapWarehouseCommandExecutor.warehouse(response);
    }

    @Autowired
    public void setDataCompanyJudgmentDocumentWrapExWarehouseCommandExecutor(DataCompanyJudgmentDocumentWrapExWarehouseCommandExecutor dataCompanyJudgmentDocumentWrapExWarehouseCommandExecutor) {
        this.dataCompanyJudgmentDocumentWrapExWarehouseCommandExecutor = dataCompanyJudgmentDocumentWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyJudgmentDocumentWrapWarehouseCommandExecutor(DataCompanyJudgmentDocumentWrapWarehouseCommandExecutor dataCompanyJudgmentDocumentWrapWarehouseCommandExecutor) {
        this.dataCompanyJudgmentDocumentWrapWarehouseCommandExecutor = dataCompanyJudgmentDocumentWrapWarehouseCommandExecutor;
    }
}
