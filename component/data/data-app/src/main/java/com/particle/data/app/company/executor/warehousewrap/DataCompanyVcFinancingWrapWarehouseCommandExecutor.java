package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanyVcFinancingInvestInstitutionRelWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyVcFinancingWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyVcInvestInstitutionWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcFinancingInvestInstitutionRelWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcFinancingWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcInvestInstitutionWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcFinancingExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcInvestInstitutionExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业融资入库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyVcFinancingWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyVcFinancingWarehouseCommandExecutor dataCompanyVcFinancingWarehouseCommandExecutor;
    private DataCompanyVcInvestInstitutionWarehouseCommandExecutor dataCompanyVcInvestInstitutionWarehouseCommandExecutor;
    private DataCompanyVcFinancingInvestInstitutionRelWarehouseCommandExecutor dataCompanyVcFinancingInvestInstitutionRelWarehouseCommandExecutor;

    /**
     * 企业融资入库
     * @param dataCompanyVcFinancingExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyVcFinancingExWarehouseVO> dataCompanyVcFinancingExWarehouseVOPageResponse) {
        List<DataCompanyVcFinancingExWarehouseVO> dataCompanyVcFinancingExWarehouseVOs = dataCompanyVcFinancingExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanyVcFinancingExWarehouseVOs)) {
            for (DataCompanyVcFinancingExWarehouseVO dataCompanyVcFinancingExWarehouseVO : dataCompanyVcFinancingExWarehouseVOs) {
                DataCompanyVcFinancingWarehouseCommand byDataCompanyVcFinancingExWarehouseVO = DataCompanyVcFinancingWarehouseCommand.createByDataCompanyVcFinancingExWarehouseVO(dataCompanyVcFinancingExWarehouseVO);
                SingleResponse<DataCompanyVcFinancingExWarehouseVO> financingWarehouseVOSingleResponse = dataCompanyVcFinancingWarehouseCommandExecutor.warehouse(byDataCompanyVcFinancingExWarehouseVO);
                List<DataCompanyVcInvestInstitutionExWarehouseVO> vcInvestInstitutions = dataCompanyVcFinancingExWarehouseVO.getVcInvestInstitutions();
                if (CollectionUtil.isNotEmpty(vcInvestInstitutions)) {
                    // 投资机构入库
                    for (DataCompanyVcInvestInstitutionExWarehouseVO vcInvestInstitution : vcInvestInstitutions) {
                        DataCompanyVcInvestInstitutionWarehouseCommand dataCompanyVcInvestInstitutionWarehouseCommand = DataCompanyVcInvestInstitutionWarehouseCommand.createByDataCompanyVcInvestInstitutionExWarehouseVO(vcInvestInstitution);
                        SingleResponse<DataCompanyVcInvestInstitutionExWarehouseVO> vcInvestInstitutionWarehouseVOSingleResponse = dataCompanyVcInvestInstitutionWarehouseCommandExecutor.warehouse(dataCompanyVcInvestInstitutionWarehouseCommand);
                        // 关联表入库
                        DataCompanyVcFinancingInvestInstitutionRelWarehouseCommand dataCompanyVcFinancingInvestInstitutionRelWarehouseCommand = DataCompanyVcFinancingInvestInstitutionRelWarehouseCommand.create(financingWarehouseVOSingleResponse.getData().getId(), vcInvestInstitutionWarehouseVOSingleResponse.getData().getId());
                        dataCompanyVcFinancingInvestInstitutionRelWarehouseCommandExecutor.warehouse(dataCompanyVcFinancingInvestInstitutionRelWarehouseCommand);
                    }
                }
            }
        }

    }

    @Autowired
    public void setDataCompanyVcFinancingWarehouseCommandExecutor(DataCompanyVcFinancingWarehouseCommandExecutor dataCompanyVcFinancingWarehouseCommandExecutor) {
        this.dataCompanyVcFinancingWarehouseCommandExecutor = dataCompanyVcFinancingWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcInvestInstitutionWarehouseCommandExecutor(DataCompanyVcInvestInstitutionWarehouseCommandExecutor dataCompanyVcInvestInstitutionWarehouseCommandExecutor) {
        this.dataCompanyVcInvestInstitutionWarehouseCommandExecutor = dataCompanyVcInvestInstitutionWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcFinancingInvestInstitutionRelWarehouseCommandExecutor(DataCompanyVcFinancingInvestInstitutionRelWarehouseCommandExecutor dataCompanyVcFinancingInvestInstitutionRelWarehouseCommandExecutor) {
        this.dataCompanyVcFinancingInvestInstitutionRelWarehouseCommandExecutor = dataCompanyVcFinancingInvestInstitutionRelWarehouseCommandExecutor;
    }
}
