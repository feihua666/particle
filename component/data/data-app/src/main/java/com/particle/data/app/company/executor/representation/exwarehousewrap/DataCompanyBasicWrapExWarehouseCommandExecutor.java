package com.particle.data.app.company.executor.representation.exwarehousewrap;

import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyBasicExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyBasicExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyBasicExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyExWarehouseCandidateVO;
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
        DataCompanyExWarehouseCandidateVO company = null;
        if (dataCompanyBasicExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanyBasicExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanyBasicExWarehouseQueryCommand.getCompanyId() == null) {
            company = getCompany(dataCompanyExWarehouseQueryCommand);
            if (company != null) {
                dataCompanyBasicExWarehouseQueryCommand.setCompanyId(company.getId());
            }
        }
        if (dataCompanyBasicExWarehouseQueryCommand.getCompanyId() == null) {
            return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        SingleResponse<DataCompanyBasicExWarehouseVO> dataCompanyBasicExWarehouseVOSingleResponse = dataCompanyBasicExWarehouseCommandExecutor.exWarehouse(dataCompanyBasicExWarehouseQueryCommand);
        DataCompanyBasicExWarehouseVO dataCompanyBasicExWarehouseVO = dataCompanyBasicExWarehouseVOSingleResponse.getData();
        if (dataCompanyBasicExWarehouseVO != null) {
            if (company == null) {
                DataCompanyExWarehouseQueryCommand command = DataCompanyExWarehouseQueryCommand.create(dataCompanyBasicExWarehouseVO.getCompanyId());
                company = getCompany(dataCompanyExWarehouseQueryCommand);
            }
            dataCompanyBasicExWarehouseVO.setCompanyUnique(company);
        }
        return dataCompanyBasicExWarehouseVOSingleResponse;
    }

    @Autowired
    public void setDataCompanyBasicExWarehouseCommandExecutor(DataCompanyBasicExWarehouseCommandExecutor dataCompanyBasicExWarehouseCommandExecutor) {
        this.dataCompanyBasicExWarehouseCommandExecutor = dataCompanyBasicExWarehouseCommandExecutor;
    }
}
