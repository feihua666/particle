package com.particle.data.app.company.executor.warehousewrap;

import com.particle.data.app.company.executor.warehouse.DataCompanyVcInvestInstitutionWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcInvestInstitutionWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcInvestInstitutionExWarehouseVO;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业投资机构信息入库
 * </p>
 *
 * @author yangwei
 * @since 2025-06-05 10:44:40
 */
@Component
@Validated
public class DataCompanyVcInvestInstitutionWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyVcInvestInstitutionWarehouseCommandExecutor dataCompanyVcInvestInstitutionWarehouseCommandExecutor;


    /**
     * 企业投资机构信息入库
     * @param dataCompanyVcInvestInstitutionExWarehouseVOSingleResponse
     */
    public void warehouse(SingleResponse<DataCompanyVcInvestInstitutionExWarehouseVO> dataCompanyVcInvestInstitutionExWarehouseVOSingleResponse) {
        DataCompanyVcInvestInstitutionExWarehouseVO dataCompanyVcInvestInstitutionExWarehouseVO = dataCompanyVcInvestInstitutionExWarehouseVOSingleResponse.getData();
        if (dataCompanyVcInvestInstitutionExWarehouseVO != null) {
            DataCompanyVcInvestInstitutionWarehouseCommand dataCompanyVcInvestInstitutionWarehouseCommand = DataCompanyVcInvestInstitutionWarehouseCommand.createByDataCompanyVcInvestInstitutionExWarehouseVO(dataCompanyVcInvestInstitutionExWarehouseVO);
            dataCompanyVcInvestInstitutionWarehouseCommandExecutor.warehouse(dataCompanyVcInvestInstitutionWarehouseCommand);
        }
    }
    @Autowired
    public void setDataCompanyVcInvestInstitutionWarehouseCommandExecutor(DataCompanyVcInvestInstitutionWarehouseCommandExecutor dataCompanyVcInvestInstitutionWarehouseCommandExecutor) {
        this.dataCompanyVcInvestInstitutionWarehouseCommandExecutor = dataCompanyVcInvestInstitutionWarehouseCommandExecutor;
    }
}
