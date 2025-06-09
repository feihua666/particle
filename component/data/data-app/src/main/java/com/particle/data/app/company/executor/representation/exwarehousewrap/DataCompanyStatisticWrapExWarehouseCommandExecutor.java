package com.particle.data.app.company.executor.representation.exwarehousewrap;

import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyStatisticExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyStatisticExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyExWarehouseCandidateVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyStatisticExWarehouseVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业统计信息出库
 * </p>
 *
 * @author yangwei
 * @since 2025-06-04 16:04:00
 */
@Component
@Validated
public class DataCompanyStatisticWrapExWarehouseCommandExecutor extends AbstractBaseWrapExWarehouseCommandExecutor{

    private DataCompanyStatisticExWarehouseCommandExecutor dataCompanyStatisticExWarehouseCommandExecutor;

    /**
     * 企业统计信息出库
     * @param dataCompanyExWarehouseQueryCommand
     * @param dataCompanyStatisticExWarehouseQueryCommand
     * @return
     */
    public SingleResponse<DataCompanyStatisticExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                 DataCompanyStatisticExWarehouseQueryCommand dataCompanyStatisticExWarehouseQueryCommand) {
        DataCompanyExWarehouseCandidateVO company = null;
        if (dataCompanyStatisticExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanyStatisticExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanyStatisticExWarehouseQueryCommand.getCompanyId() == null) {
            company = getCompany(dataCompanyExWarehouseQueryCommand);
            if (company != null) {
                dataCompanyStatisticExWarehouseQueryCommand.setCompanyId(company.getId());
            }
        }
        if (dataCompanyStatisticExWarehouseQueryCommand.getCompanyId() == null) {
            return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        SingleResponse<DataCompanyStatisticExWarehouseVO> dataCompanyStatisticExWarehouseVOSingleResponse = dataCompanyStatisticExWarehouseCommandExecutor.exWarehouse(dataCompanyStatisticExWarehouseQueryCommand);
        return dataCompanyStatisticExWarehouseVOSingleResponse;
    }

    @Autowired
    public void setDataCompanyStatisticExWarehouseCommandExecutor(DataCompanyStatisticExWarehouseCommandExecutor dataCompanyStatisticExWarehouseCommandExecutor) {
        this.dataCompanyStatisticExWarehouseCommandExecutor = dataCompanyStatisticExWarehouseCommandExecutor;
    }
}
