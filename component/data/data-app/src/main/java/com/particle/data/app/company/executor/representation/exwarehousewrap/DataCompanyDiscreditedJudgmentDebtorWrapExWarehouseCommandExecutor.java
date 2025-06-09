package com.particle.data.app.company.executor.representation.exwarehousewrap;

import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.AbstractBaseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDiscreditedJudgmentDebtorExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业失信被执行人出库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyDiscreditedJudgmentDebtorWrapExWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor;

    /**
     * 企业失信被执行人出库
     * @param dataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand
     */
    public PageResponse<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                       DataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand dataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand) {
        if (dataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        return dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor.exWarehouse(dataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand);

    }

    @Autowired
    public void setDataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor(DataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor) {
        this.dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor = dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor;
    }
}
