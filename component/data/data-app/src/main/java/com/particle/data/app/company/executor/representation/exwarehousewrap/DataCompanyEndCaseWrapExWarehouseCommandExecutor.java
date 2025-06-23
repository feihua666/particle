package com.particle.data.app.company.executor.representation.exwarehousewrap;

import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyEndCaseExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.AbstractBaseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyEndCaseExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyEndCaseExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业终本案件出库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyEndCaseWrapExWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyEndCaseExWarehouseCommandExecutor dataCompanyEndCaseExWarehouseCommandExecutor;

    /**
     * 企业终本案件出库
     * @param dataCompanyEndCaseExWarehouseQueryCommand
     */
    public PageResponse<DataCompanyEndCaseExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                       DataCompanyEndCaseExWarehouseQueryCommand dataCompanyEndCaseExWarehouseQueryCommand) {
        if (dataCompanyEndCaseExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanyEndCaseExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanyEndCaseExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyEndCaseExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyEndCaseExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        return dataCompanyEndCaseExWarehouseCommandExecutor.exWarehouse(dataCompanyEndCaseExWarehouseQueryCommand);

    }

    @Autowired
    public void setDataCompanyEndCaseExWarehouseCommandExecutor(DataCompanyEndCaseExWarehouseCommandExecutor dataCompanyEndCaseExWarehouseCommandExecutor) {
        this.dataCompanyEndCaseExWarehouseCommandExecutor = dataCompanyEndCaseExWarehouseCommandExecutor;
    }
}
