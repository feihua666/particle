package com.particle.data.app.company.executor.representation.exwarehousewrap;

import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.AbstractBaseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprSoftwareCopyrightExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprSoftwareCopyrightExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业软件著作出库
 * </p>
 *
 * @author yangwei
 * @since 2025-06-17 10:34:00
 */
@Component
@Validated
public class DataCompanyIprSoftwareCopyrightWrapExWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor dataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor;

    /**
     * 企业软件著作出库
     * @param dataCompanyIprSoftwareCopyrightExWarehouseQueryCommand
     */
    public PageResponse<DataCompanyIprSoftwareCopyrightExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                       DataCompanyIprSoftwareCopyrightExWarehouseQueryCommand dataCompanyIprSoftwareCopyrightExWarehouseQueryCommand) {
        if (dataCompanyIprSoftwareCopyrightExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanyIprSoftwareCopyrightExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanyIprSoftwareCopyrightExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyIprSoftwareCopyrightExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyIprSoftwareCopyrightExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        return dataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor.exWarehouse(dataCompanyIprSoftwareCopyrightExWarehouseQueryCommand);

    }

    @Autowired
    public void setDataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor(DataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor dataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor) {
        this.dataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor = dataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor;
    }
}
