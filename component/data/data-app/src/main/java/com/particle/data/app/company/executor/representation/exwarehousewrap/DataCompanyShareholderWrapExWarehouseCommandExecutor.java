package com.particle.data.app.company.executor.representation.exwarehousewrap;

import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyShareholderExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.AbstractBaseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyShareholderExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyShareholderExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业股东出库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyShareholderWrapExWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyShareholderExWarehouseCommandExecutor dataCompanyShareholderExWarehouseCommandExecutor;

    /**
     * 企业股东出库
     * @param dataCompanyShareholderExWarehouseQueryCommand
     */
    public PageResponse<DataCompanyShareholderExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                       DataCompanyShareholderExWarehouseQueryCommand dataCompanyShareholderExWarehouseQueryCommand) {
        if (dataCompanyShareholderExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyShareholderExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyShareholderExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        return dataCompanyShareholderExWarehouseCommandExecutor.exWarehouse(dataCompanyShareholderExWarehouseQueryCommand);

    }

    @Autowired
    public void setDataCompanyShareholderExWarehouseCommandExecutor(DataCompanyShareholderExWarehouseCommandExecutor dataCompanyShareholderExWarehouseCommandExecutor) {
        this.dataCompanyShareholderExWarehouseCommandExecutor = dataCompanyShareholderExWarehouseCommandExecutor;
    }
}
