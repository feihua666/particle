package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanyHonorQualificationWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyHonorQualificationWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyHonorQualificationExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业荣誉资质入库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyHonorQualificationWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyHonorQualificationWarehouseCommandExecutor dataCompanyHonorQualificationWarehouseCommandExecutor;

    /**
     * 企业荣誉资质入库
     * @param dataCompanyHonorQualificationExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyHonorQualificationExWarehouseVO> dataCompanyHonorQualificationExWarehouseVOPageResponse) {
        List<DataCompanyHonorQualificationExWarehouseVO> dataCompanyHonorQualificationExWarehouseVOs = dataCompanyHonorQualificationExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanyHonorQualificationExWarehouseVOs)) {
            for (DataCompanyHonorQualificationExWarehouseVO dataCompanyHonorQualificationExWarehouseVO : dataCompanyHonorQualificationExWarehouseVOs) {
                DataCompanyHonorQualificationWarehouseCommand byDataCompanyHonorQualificationExWarehouseVO = DataCompanyHonorQualificationWarehouseCommand.createByDataCompanyHonorQualificationExWarehouseVO(dataCompanyHonorQualificationExWarehouseVO);
                dataCompanyHonorQualificationWarehouseCommandExecutor.warehouse(byDataCompanyHonorQualificationExWarehouseVO);
            }
        }

    }

    @Autowired
    public void setDataCompanyHonorQualificationWarehouseCommandExecutor(DataCompanyHonorQualificationWarehouseCommandExecutor dataCompanyHonorQualificationWarehouseCommandExecutor) {
        this.dataCompanyHonorQualificationWarehouseCommandExecutor = dataCompanyHonorQualificationWarehouseCommandExecutor;
    }
}
