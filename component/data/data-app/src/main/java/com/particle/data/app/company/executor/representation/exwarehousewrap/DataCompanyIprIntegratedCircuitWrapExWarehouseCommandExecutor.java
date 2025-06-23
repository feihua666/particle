package com.particle.data.app.company.executor.representation.exwarehousewrap;

import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprIntegratedCircuitExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.AbstractBaseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprIntegratedCircuitExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprIntegratedCircuitExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业集成电路出库
 * </p>
 *
 * @author yangwei
 * @since 2025-06-17 10:34:00
 */
@Component
@Validated
public class DataCompanyIprIntegratedCircuitWrapExWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyIprIntegratedCircuitExWarehouseCommandExecutor dataCompanyIprIntegratedCircuitExWarehouseCommandExecutor;

    /**
     * 企业集成电路出库
     * @param dataCompanyIprIntegratedCircuitExWarehouseQueryCommand
     */
    public PageResponse<DataCompanyIprIntegratedCircuitExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                       DataCompanyIprIntegratedCircuitExWarehouseQueryCommand dataCompanyIprIntegratedCircuitExWarehouseQueryCommand) {
        if (dataCompanyIprIntegratedCircuitExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanyIprIntegratedCircuitExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanyIprIntegratedCircuitExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyIprIntegratedCircuitExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyIprIntegratedCircuitExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        return dataCompanyIprIntegratedCircuitExWarehouseCommandExecutor.exWarehouse(dataCompanyIprIntegratedCircuitExWarehouseQueryCommand);

    }

    @Autowired
    public void setDataCompanyIprIntegratedCircuitExWarehouseCommandExecutor(DataCompanyIprIntegratedCircuitExWarehouseCommandExecutor dataCompanyIprIntegratedCircuitExWarehouseCommandExecutor) {
        this.dataCompanyIprIntegratedCircuitExWarehouseCommandExecutor = dataCompanyIprIntegratedCircuitExWarehouseCommandExecutor;
    }
}
