package com.particle.data.app.company.executor.representation.exwarehousewrap;

import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyVcInvestInstitutionExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyVcInvestInstitutionExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyExWarehouseCandidateVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcInvestInstitutionExWarehouseVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业投资机构信息出库
 * </p>
 *
 * @author yangwei
 * @since 2025-06-05 11:41:29
 */
@Component
@Validated
public class DataCompanyVcInvestInstitutionWrapExWarehouseCommandExecutor extends AbstractBaseWrapExWarehouseCommandExecutor{

    private DataCompanyVcInvestInstitutionExWarehouseCommandExecutor dataCompanyVcInvestInstitutionExWarehouseCommandExecutor;

    /**
     * 企业投资机构信息出库
     * @param dataCompanyExWarehouseQueryCommand
     * @param dataCompanyVcInvestInstitutionExWarehouseQueryCommand
     * @return
     */
    public SingleResponse<DataCompanyVcInvestInstitutionExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                 DataCompanyVcInvestInstitutionExWarehouseQueryCommand dataCompanyVcInvestInstitutionExWarehouseQueryCommand) {
        DataCompanyExWarehouseCandidateVO company = null;
        if (dataCompanyVcInvestInstitutionExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanyVcInvestInstitutionExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanyVcInvestInstitutionExWarehouseQueryCommand.getCompanyId() == null) {
            company = getCompany(dataCompanyExWarehouseQueryCommand);
            if (company != null) {
                dataCompanyVcInvestInstitutionExWarehouseQueryCommand.setCompanyId(company.getId());
            }
        }
        if (dataCompanyVcInvestInstitutionExWarehouseQueryCommand.getCompanyId() == null) {
            return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        SingleResponse<DataCompanyVcInvestInstitutionExWarehouseVO> dataCompanyVcInvestInstitutionExWarehouseVOSingleResponse = dataCompanyVcInvestInstitutionExWarehouseCommandExecutor.exWarehouse(dataCompanyVcInvestInstitutionExWarehouseQueryCommand);
        return dataCompanyVcInvestInstitutionExWarehouseVOSingleResponse;
    }

    @Autowired
    public void setDataCompanyVcInvestInstitutionExWarehouseCommandExecutor(DataCompanyVcInvestInstitutionExWarehouseCommandExecutor dataCompanyVcInvestInstitutionExWarehouseCommandExecutor) {
        this.dataCompanyVcInvestInstitutionExWarehouseCommandExecutor = dataCompanyVcInvestInstitutionExWarehouseCommandExecutor;
    }
}
