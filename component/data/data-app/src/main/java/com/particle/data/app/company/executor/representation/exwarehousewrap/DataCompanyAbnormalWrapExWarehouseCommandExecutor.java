package com.particle.data.app.company.executor.representation.exwarehousewrap;

import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAbnormalExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.AbstractBaseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAbnormalExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAbnormalExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业经营异常出库
 * </p>
 *
 * @author yangwei
 * @since 2025-05-29 11:16:49
 */
@Component
@Validated
public class DataCompanyAbnormalWrapExWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyAbnormalExWarehouseCommandExecutor dataCompanyAbnormalExWarehouseCommandExecutor;

    /**
     * 企业经营异常出库
     * @param dataCompanyAbnormalExWarehouseQueryCommand
     */
    public PageResponse<DataCompanyAbnormalExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                       DataCompanyAbnormalExWarehouseQueryCommand dataCompanyAbnormalExWarehouseQueryCommand) {
        if (dataCompanyAbnormalExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanyAbnormalExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanyAbnormalExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyAbnormalExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyAbnormalExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        return dataCompanyAbnormalExWarehouseCommandExecutor.exWarehouse(dataCompanyAbnormalExWarehouseQueryCommand);

    }

    @Autowired
    public void setDataCompanyAbnormalExWarehouseCommandExecutor(DataCompanyAbnormalExWarehouseCommandExecutor dataCompanyAbnormalExWarehouseCommandExecutor) {
        this.dataCompanyAbnormalExWarehouseCommandExecutor = dataCompanyAbnormalExWarehouseCommandExecutor;
    }
}
