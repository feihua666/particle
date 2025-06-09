package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.dict.CurrencyType;
import com.particle.data.app.company.executor.warehouse.DataCompanyAbnormalWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAbnormalWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAbnormalExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业经营异常入库
 * </p>
 *
 * @author yangwei
 * @since 2025-05-29 11:12:15
 */
@Component
@Validated
public class DataCompanyAbnormalWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyAbnormalWarehouseCommandExecutor dataCompanyAbnormalWarehouseCommandExecutor;

    /**
     * 企业经营异常入库
     * @param dataCompanyAbnormalExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyAbnormalExWarehouseVO> dataCompanyAbnormalExWarehouseVOPageResponse) {
        List<DataCompanyAbnormalExWarehouseVO> dataCompanyAbnormalExWarehouseVOs = dataCompanyAbnormalExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanyAbnormalExWarehouseVOs)) {
            for (DataCompanyAbnormalExWarehouseVO dataCompanyAbnormalExWarehouseVO : dataCompanyAbnormalExWarehouseVOs) {
                DataCompanyAbnormalWarehouseCommand dataCompanyAbnormalWarehouseCommand = DataCompanyAbnormalWarehouseCommand.createByDataCompanyAbnormalExWarehouseVO(dataCompanyAbnormalExWarehouseVO);
                fillIds(dataCompanyAbnormalWarehouseCommand, dataCompanyAbnormalExWarehouseVO);
                dataCompanyAbnormalWarehouseCommandExecutor.warehouse(dataCompanyAbnormalWarehouseCommand);
            }
        }
    }

    private void fillIds(DataCompanyAbnormalWarehouseCommand dataCompanyAbnormalWarehouseCommand, DataCompanyAbnormalExWarehouseVO dataCompanyAbnormalExWarehouseVO) {
        // 企业id
        if (dataCompanyAbnormalWarehouseCommand.getCompanyId() == null) {
            if (StrUtil.isNotEmpty(dataCompanyAbnormalWarehouseCommand.getCompanyName())) {
                Long companyId = warehouseCompanyGetCompanyId(dataCompanyAbnormalWarehouseCommand.getCompanyName());
                dataCompanyAbnormalWarehouseCommand.setCompanyId(companyId);
            }
        }
        // 列入机关公司id
        if (dataCompanyAbnormalWarehouseCommand.getPutInstituteCompanyId() == null) {
            if (StrUtil.isNotEmpty(dataCompanyAbnormalWarehouseCommand.getPutInstituteName())) {
                Long companyId = warehouseCompanyGetCompanyId(dataCompanyAbnormalWarehouseCommand.getPutInstituteName());
                dataCompanyAbnormalWarehouseCommand.setPutInstituteCompanyId(companyId);
            }
        }
        // 移出机关公司id
        if (dataCompanyAbnormalWarehouseCommand.getRemoveInstituteCompanyId() == null) {
            if (StrUtil.isNotEmpty(dataCompanyAbnormalWarehouseCommand.getRemoveInstituteName())) {
                Long companyId = warehouseCompanyGetCompanyId(dataCompanyAbnormalWarehouseCommand.getRemoveInstituteName());
                dataCompanyAbnormalWarehouseCommand.setRemoveInstituteCompanyId(companyId);
            }
        }

    }

    @Autowired
    public void setDataCompanyAbnormalWarehouseCommandExecutor(DataCompanyAbnormalWarehouseCommandExecutor dataCompanyAbnormalWarehouseCommandExecutor) {
        this.dataCompanyAbnormalWarehouseCommandExecutor = dataCompanyAbnormalWarehouseCommandExecutor;
    }
}
