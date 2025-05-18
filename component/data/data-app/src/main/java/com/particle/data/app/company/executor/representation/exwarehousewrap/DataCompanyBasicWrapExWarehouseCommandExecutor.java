package com.particle.data.app.company.executor.representation.exwarehousewrap;

import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyBasicExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyBasicExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyBasicExWarehouseVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业基本信息出库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyBasicWrapExWarehouseCommandExecutor extends AbstractBaseWrapExWarehouseCommandExecutor{

    private DataCompanyBasicExWarehouseCommandExecutor dataCompanyBasicExWarehouseCommandExecutor;

    /**
     * 企业基本信息出库
     * @param dataCompanyExWarehouseQueryCommand
     * @param dataCompanyBasicExWarehouseQueryCommand
     * @return
     */
    public SingleResponse<DataCompanyBasicExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                 DataCompanyBasicExWarehouseQueryCommand dataCompanyBasicExWarehouseQueryCommand) {
        if (dataCompanyBasicExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyBasicExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyBasicExWarehouseQueryCommand.getCompanyId() == null) {
            return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        return dataCompanyBasicExWarehouseCommandExecutor.exWarehouse(dataCompanyBasicExWarehouseQueryCommand);
    }

    @Autowired
    public void setDataCompanyBasicExWarehouseCommandExecutor(DataCompanyBasicExWarehouseCommandExecutor dataCompanyBasicExWarehouseCommandExecutor) {
        this.dataCompanyBasicExWarehouseCommandExecutor = dataCompanyBasicExWarehouseCommandExecutor;
    }
}
