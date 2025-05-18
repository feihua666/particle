package com.particle.data.app.company.executor.representation.exwarehousewrap;

import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyHonorQualificationExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.AbstractBaseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyHonorQualificationExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyHonorQualificationExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业荣誉资质出库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyHonorQualificationWrapExWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyHonorQualificationExWarehouseCommandExecutor dataCompanyHonorQualificationExWarehouseCommandExecutor;

    /**
     * 企业荣誉资质出库
     * @param dataCompanyHonorQualificationExWarehouseQueryCommand
     */
    public PageResponse<DataCompanyHonorQualificationExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                       DataCompanyHonorQualificationExWarehouseQueryCommand dataCompanyHonorQualificationExWarehouseQueryCommand) {
        if (dataCompanyHonorQualificationExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyHonorQualificationExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyHonorQualificationExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        return dataCompanyHonorQualificationExWarehouseCommandExecutor.exWarehouse(dataCompanyHonorQualificationExWarehouseQueryCommand);

    }

    @Autowired
    public void setDataCompanyHonorQualificationExWarehouseCommandExecutor(DataCompanyHonorQualificationExWarehouseCommandExecutor dataCompanyHonorQualificationExWarehouseCommandExecutor) {
        this.dataCompanyHonorQualificationExWarehouseCommandExecutor = dataCompanyHonorQualificationExWarehouseCommandExecutor;
    }
}
