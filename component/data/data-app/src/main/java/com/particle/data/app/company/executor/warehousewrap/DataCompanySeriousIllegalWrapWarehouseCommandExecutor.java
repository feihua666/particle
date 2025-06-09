package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanySeriousIllegalWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanySeriousIllegalWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanySeriousIllegalWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySeriousIllegalExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySeriousIllegalExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业严重违法入库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanySeriousIllegalWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanySeriousIllegalWarehouseCommandExecutor dataCompanySeriousIllegalWarehouseCommandExecutor;

    /**
     * 企业严重违法入库
     * @param dataCompanySeriousIllegalExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanySeriousIllegalExWarehouseVO> dataCompanySeriousIllegalExWarehouseVOPageResponse) {
        List<DataCompanySeriousIllegalExWarehouseVO> dataCompanySeriousIllegalExWarehouseVOs = dataCompanySeriousIllegalExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanySeriousIllegalExWarehouseVOs)) {
            for (DataCompanySeriousIllegalExWarehouseVO dataCompanySeriousIllegalExWarehouseVO : dataCompanySeriousIllegalExWarehouseVOs) {
                DataCompanySeriousIllegalWarehouseCommand dataCompanySeriousIllegalWarehouseCommand = DataCompanySeriousIllegalWarehouseCommand.createByDataCompanySeriousIllegalExWarehouseVO(dataCompanySeriousIllegalExWarehouseVO);
                fillIds(dataCompanySeriousIllegalWarehouseCommand, dataCompanySeriousIllegalExWarehouseVO);
                dataCompanySeriousIllegalWarehouseCommandExecutor.warehouse(dataCompanySeriousIllegalWarehouseCommand);
            }
        }

    }
    private void fillIds(DataCompanySeriousIllegalWarehouseCommand dataCompanySeriousIllegalWarehouseCommand, DataCompanySeriousIllegalExWarehouseVO dataCompanySeriousIllegalExWarehouseVO) {
        // 企业id
        if (dataCompanySeriousIllegalWarehouseCommand.getCompanyId() == null) {
            if (StrUtil.isNotEmpty(dataCompanySeriousIllegalWarehouseCommand.getCompanyName())) {
                Long companyId = warehouseCompanyGetCompanyId(dataCompanySeriousIllegalWarehouseCommand.getCompanyName());
                dataCompanySeriousIllegalWarehouseCommand.setCompanyId(companyId);
            }
        }
        // 列入机关公司id
        if (dataCompanySeriousIllegalWarehouseCommand.getPutInstituteCompanyId() == null) {
            if (StrUtil.isNotEmpty(dataCompanySeriousIllegalWarehouseCommand.getPutInstituteName())) {
                Long companyId = warehouseCompanyGetCompanyId(dataCompanySeriousIllegalWarehouseCommand.getPutInstituteName());
                dataCompanySeriousIllegalWarehouseCommand.setPutInstituteCompanyId(companyId);
            }
        }
        // 移出机关公司id
        if (dataCompanySeriousIllegalWarehouseCommand.getRemoveInstituteCompanyId() == null) {
            if (StrUtil.isNotEmpty(dataCompanySeriousIllegalWarehouseCommand.getRemoveInstituteName())) {
                Long companyId = warehouseCompanyGetCompanyId(dataCompanySeriousIllegalWarehouseCommand.getRemoveInstituteName());
                dataCompanySeriousIllegalWarehouseCommand.setRemoveInstituteCompanyId(companyId);
            }
        }
    }
    @Autowired
    public void setDataCompanySeriousIllegalWarehouseCommandExecutor(DataCompanySeriousIllegalWarehouseCommandExecutor dataCompanySeriousIllegalWarehouseCommandExecutor) {
        this.dataCompanySeriousIllegalWarehouseCommandExecutor = dataCompanySeriousIllegalWarehouseCommandExecutor;
    }
}
