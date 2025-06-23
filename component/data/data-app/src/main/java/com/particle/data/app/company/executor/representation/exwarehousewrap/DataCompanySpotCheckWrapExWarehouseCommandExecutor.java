package com.particle.data.app.company.executor.representation.exwarehousewrap;

import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanySpotCheckExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.AbstractBaseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanySpotCheckExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySpotCheckExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业抽查检查出库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanySpotCheckWrapExWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanySpotCheckExWarehouseCommandExecutor dataCompanySpotCheckExWarehouseCommandExecutor;

    /**
     * 企业抽查检查出库
     * @param dataCompanySpotCheckExWarehouseQueryCommand
     */
    public PageResponse<DataCompanySpotCheckExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                       DataCompanySpotCheckExWarehouseQueryCommand dataCompanySpotCheckExWarehouseQueryCommand) {
        if (dataCompanySpotCheckExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanySpotCheckExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanySpotCheckExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanySpotCheckExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanySpotCheckExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        return dataCompanySpotCheckExWarehouseCommandExecutor.exWarehouse(dataCompanySpotCheckExWarehouseQueryCommand);

    }

    @Autowired
    public void setDataCompanySpotCheckExWarehouseCommandExecutor(DataCompanySpotCheckExWarehouseCommandExecutor dataCompanySpotCheckExWarehouseCommandExecutor) {
        this.dataCompanySpotCheckExWarehouseCommandExecutor = dataCompanySpotCheckExWarehouseCommandExecutor;
    }
}
