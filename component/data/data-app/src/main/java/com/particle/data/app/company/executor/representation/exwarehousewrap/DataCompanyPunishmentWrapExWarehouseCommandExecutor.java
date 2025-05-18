package com.particle.data.app.company.executor.representation.exwarehousewrap;

import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyPunishmentExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.AbstractBaseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyPunishmentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPunishmentExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业行政处罚出库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyPunishmentWrapExWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyPunishmentExWarehouseCommandExecutor dataCompanyPunishmentExWarehouseCommandExecutor;

    /**
     * 企业行政处罚出库
     * @param dataCompanyPunishmentExWarehouseQueryCommand
     */
    public PageResponse<DataCompanyPunishmentExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                       DataCompanyPunishmentExWarehouseQueryCommand dataCompanyPunishmentExWarehouseQueryCommand) {
        if (dataCompanyPunishmentExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyPunishmentExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyPunishmentExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        return dataCompanyPunishmentExWarehouseCommandExecutor.exWarehouse(dataCompanyPunishmentExWarehouseQueryCommand);

    }

    @Autowired
    public void setDataCompanyPunishmentExWarehouseCommandExecutor(DataCompanyPunishmentExWarehouseCommandExecutor dataCompanyPunishmentExWarehouseCommandExecutor) {
        this.dataCompanyPunishmentExWarehouseCommandExecutor = dataCompanyPunishmentExWarehouseCommandExecutor;
    }
}
