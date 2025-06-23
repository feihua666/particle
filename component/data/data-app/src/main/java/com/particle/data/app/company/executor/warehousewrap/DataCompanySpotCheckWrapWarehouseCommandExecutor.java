package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanySpotCheckWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanySpotCheckWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySpotCheckExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业抽查检查入库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanySpotCheckWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanySpotCheckWarehouseCommandExecutor dataCompanySpotCheckWarehouseCommandExecutor;

    /**
     * 企业抽查检查入库
     * @param dataCompanySpotCheckExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanySpotCheckExWarehouseVO> dataCompanySpotCheckExWarehouseVOPageResponse) {
        List<DataCompanySpotCheckExWarehouseVO> dataCompanySpotCheckExWarehouseVOs = dataCompanySpotCheckExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanySpotCheckExWarehouseVOs)) {
            for (DataCompanySpotCheckExWarehouseVO dataCompanySpotCheckExWarehouseVO : dataCompanySpotCheckExWarehouseVOs) {
                DataCompanySpotCheckWarehouseCommand dataCompanySpotCheckWarehouseCommand = DataCompanySpotCheckWarehouseCommand.createByDataCompanySpotCheckExWarehouseVO(dataCompanySpotCheckExWarehouseVO);
                fillIds(dataCompanySpotCheckWarehouseCommand, dataCompanySpotCheckExWarehouseVO);
                dataCompanySpotCheckWarehouseCommandExecutor.warehouse(dataCompanySpotCheckWarehouseCommand);
            }
        }
    }

    private void fillIds(DataCompanySpotCheckWarehouseCommand dataCompanySpotCheckWarehouseCommand, DataCompanySpotCheckExWarehouseVO dataCompanySpotCheckExWarehouseVO) {
        // 检查实施机关
        Long checkInstituteCompanyId = warehouseCompanyGetCompanyId(dataCompanySpotCheckWarehouseCommand.getCheckInstitute());
        dataCompanySpotCheckWarehouseCommand.setCheckInstituteCompanyId(checkInstituteCompanyId);
    }

    @Autowired
    public void setDataCompanySpotCheckWarehouseCommandExecutor(DataCompanySpotCheckWarehouseCommandExecutor dataCompanySpotCheckWarehouseCommandExecutor) {
        this.dataCompanySpotCheckWarehouseCommandExecutor = dataCompanySpotCheckWarehouseCommandExecutor;
    }
}
