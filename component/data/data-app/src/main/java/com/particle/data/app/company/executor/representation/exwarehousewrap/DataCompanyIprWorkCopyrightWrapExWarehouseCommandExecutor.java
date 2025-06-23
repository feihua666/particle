package com.particle.data.app.company.executor.representation.exwarehousewrap;

import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprWorkCopyrightExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.AbstractBaseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprWorkCopyrightExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprWorkCopyrightExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业作品著作出库
 * </p>
 *
 * @author yangwei
 * @since 2025-06-17 10:34:00
 */
@Component
@Validated
public class DataCompanyIprWorkCopyrightWrapExWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyIprWorkCopyrightExWarehouseCommandExecutor dataCompanyIprWorkCopyrightExWarehouseCommandExecutor;

    /**
     * 企业作品著作出库
     * @param dataCompanyIprWorkCopyrightExWarehouseQueryCommand
     */
    public PageResponse<DataCompanyIprWorkCopyrightExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                       DataCompanyIprWorkCopyrightExWarehouseQueryCommand dataCompanyIprWorkCopyrightExWarehouseQueryCommand) {
        if (dataCompanyIprWorkCopyrightExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanyIprWorkCopyrightExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanyIprWorkCopyrightExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyIprWorkCopyrightExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyIprWorkCopyrightExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        return dataCompanyIprWorkCopyrightExWarehouseCommandExecutor.exWarehouse(dataCompanyIprWorkCopyrightExWarehouseQueryCommand);

    }

    @Autowired
    public void setDataCompanyIprWorkCopyrightExWarehouseCommandExecutor(DataCompanyIprWorkCopyrightExWarehouseCommandExecutor dataCompanyIprWorkCopyrightExWarehouseCommandExecutor) {
        this.dataCompanyIprWorkCopyrightExWarehouseCommandExecutor = dataCompanyIprWorkCopyrightExWarehouseCommandExecutor;
    }
}
