package com.particle.data.app.company.executor.representation.exwarehousewrap;

import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyJudgmentDebtorExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.AbstractBaseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyJudgmentDebtorExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDebtorExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业被执行人出库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyJudgmentDebtorWrapExWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyJudgmentDebtorExWarehouseCommandExecutor dataCompanyJudgmentDebtorExWarehouseCommandExecutor;

    /**
     * 企业被执行人出库
     * @param dataCompanyJudgmentDebtorExWarehouseQueryCommand
     */
    public PageResponse<DataCompanyJudgmentDebtorExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                       DataCompanyJudgmentDebtorExWarehouseQueryCommand dataCompanyJudgmentDebtorExWarehouseQueryCommand) {
        if (dataCompanyJudgmentDebtorExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyJudgmentDebtorExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyJudgmentDebtorExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        return dataCompanyJudgmentDebtorExWarehouseCommandExecutor.exWarehouse(dataCompanyJudgmentDebtorExWarehouseQueryCommand);

    }

    @Autowired
    public void setDataCompanyJudgmentDebtorExWarehouseCommandExecutor(DataCompanyJudgmentDebtorExWarehouseCommandExecutor dataCompanyJudgmentDebtorExWarehouseCommandExecutor) {
        this.dataCompanyJudgmentDebtorExWarehouseCommandExecutor = dataCompanyJudgmentDebtorExWarehouseCommandExecutor;
    }
}
