package com.particle.data.app.company.executor.representation.exwarehousewrap;

import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanySeriousIllegalExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.AbstractBaseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanySeriousIllegalExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySeriousIllegalExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业严重违法出库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanySeriousIllegalWrapExWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanySeriousIllegalExWarehouseCommandExecutor dataCompanySeriousIllegalExWarehouseCommandExecutor;

    /**
     * 企业严重违法出库
     * @param dataCompanySeriousIllegalExWarehouseQueryCommand
     */
    public PageResponse<DataCompanySeriousIllegalExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                       DataCompanySeriousIllegalExWarehouseQueryCommand dataCompanySeriousIllegalExWarehouseQueryCommand) {
        if (dataCompanySeriousIllegalExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanySeriousIllegalExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanySeriousIllegalExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        return dataCompanySeriousIllegalExWarehouseCommandExecutor.exWarehouse(dataCompanySeriousIllegalExWarehouseQueryCommand);

    }

    @Autowired
    public void setDataCompanySeriousIllegalExWarehouseCommandExecutor(DataCompanySeriousIllegalExWarehouseCommandExecutor dataCompanySeriousIllegalExWarehouseCommandExecutor) {
        this.dataCompanySeriousIllegalExWarehouseCommandExecutor = dataCompanySeriousIllegalExWarehouseCommandExecutor;
    }
}
