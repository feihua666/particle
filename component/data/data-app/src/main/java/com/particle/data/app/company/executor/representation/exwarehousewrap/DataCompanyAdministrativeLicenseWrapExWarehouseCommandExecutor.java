package com.particle.data.app.company.executor.representation.exwarehousewrap;

import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAdministrativeLicenseExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.AbstractBaseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAdministrativeLicenseExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAdministrativeLicenseExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业行政许可出库
 * </p>
 *
 * @author yangwei
 * @since 2025-06-20 18:12:35
 */
@Component
@Validated
public class DataCompanyAdministrativeLicenseWrapExWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyAdministrativeLicenseExWarehouseCommandExecutor dataCompanyAdministrativeLicenseExWarehouseCommandExecutor;

    /**
     * 企业行政许可出库
     * @param dataCompanyAdministrativeLicenseExWarehouseQueryCommand
     */
    public PageResponse<DataCompanyAdministrativeLicenseExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                       DataCompanyAdministrativeLicenseExWarehouseQueryCommand dataCompanyAdministrativeLicenseExWarehouseQueryCommand) {
        if (dataCompanyAdministrativeLicenseExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanyAdministrativeLicenseExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanyAdministrativeLicenseExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyAdministrativeLicenseExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyAdministrativeLicenseExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        return dataCompanyAdministrativeLicenseExWarehouseCommandExecutor.exWarehouse(dataCompanyAdministrativeLicenseExWarehouseQueryCommand);

    }

    @Autowired
    public void setDataCompanyAdministrativeLicenseExWarehouseCommandExecutor(DataCompanyAdministrativeLicenseExWarehouseCommandExecutor dataCompanyAdministrativeLicenseExWarehouseCommandExecutor) {
        this.dataCompanyAdministrativeLicenseExWarehouseCommandExecutor = dataCompanyAdministrativeLicenseExWarehouseCommandExecutor;
    }
}
