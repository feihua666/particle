package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.dict.CurrencyType;
import com.particle.data.app.company.executor.warehouse.DataCompanyAdministrativeLicenseWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAdministrativeLicenseWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAdministrativeLicenseExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业行政许可入库
 * </p>
 *
 * @author yangwei
 * @since 2025-06-20 18:12:45
 */
@Component
@Validated
public class DataCompanyAdministrativeLicenseWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyAdministrativeLicenseWarehouseCommandExecutor dataCompanyAdministrativeLicenseWarehouseCommandExecutor;

    /**
     * 企业行政许可入库
     * @param dataCompanyAdministrativeLicenseExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyAdministrativeLicenseExWarehouseVO> dataCompanyAdministrativeLicenseExWarehouseVOPageResponse) {
        List<DataCompanyAdministrativeLicenseExWarehouseVO> dataCompanyAdministrativeLicenseExWarehouseVOs = dataCompanyAdministrativeLicenseExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanyAdministrativeLicenseExWarehouseVOs)) {
            for (DataCompanyAdministrativeLicenseExWarehouseVO dataCompanyAdministrativeLicenseExWarehouseVO : dataCompanyAdministrativeLicenseExWarehouseVOs) {
                DataCompanyAdministrativeLicenseWarehouseCommand dataCompanyAdministrativeLicenseWarehouseCommand = DataCompanyAdministrativeLicenseWarehouseCommand.createByDataCompanyAdministrativeLicenseExWarehouseVO(dataCompanyAdministrativeLicenseExWarehouseVO);
                fillIds(dataCompanyAdministrativeLicenseWarehouseCommand, dataCompanyAdministrativeLicenseExWarehouseVO);
                dataCompanyAdministrativeLicenseWarehouseCommandExecutor.warehouse(dataCompanyAdministrativeLicenseWarehouseCommand);
            }
        }
    }

    private void fillIds(DataCompanyAdministrativeLicenseWarehouseCommand dataCompanyAdministrativeLicenseWarehouseCommand, DataCompanyAdministrativeLicenseExWarehouseVO dataCompanyAdministrativeLicenseExWarehouseVO) {

        // 许可机关
        if (StrUtil.isNotEmpty(dataCompanyAdministrativeLicenseWarehouseCommand.getInstitute())) {
            Long instituteCompanyId = warehouseCompanyGetCompanyId(dataCompanyAdministrativeLicenseWarehouseCommand.getInstitute(),
                    dataCompanyAdministrativeLicenseWarehouseCommand.getInstituteUscc());
            dataCompanyAdministrativeLicenseWarehouseCommand.setInstituteCompanyId(instituteCompanyId);
        }
        // 数据来源单位
        if (StrUtil.isNotEmpty(dataCompanyAdministrativeLicenseWarehouseCommand.getDataFrom())) {
            Long dataFromCompanyId = warehouseCompanyGetCompanyId(dataCompanyAdministrativeLicenseWarehouseCommand.getDataFrom(),
                    dataCompanyAdministrativeLicenseWarehouseCommand.getDataFromUscc());
            dataCompanyAdministrativeLicenseWarehouseCommand.setDataFromCompanyId(dataFromCompanyId);
        }


    }

    @Autowired
    public void setDataCompanyAdministrativeLicenseWarehouseCommandExecutor(DataCompanyAdministrativeLicenseWarehouseCommandExecutor dataCompanyAdministrativeLicenseWarehouseCommandExecutor) {
        this.dataCompanyAdministrativeLicenseWarehouseCommandExecutor = dataCompanyAdministrativeLicenseWarehouseCommandExecutor;
    }
}
