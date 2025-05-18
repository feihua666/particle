package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanyVcProductCompetitiveProductRelWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyVcProductWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcProductCompetitiveProductRelWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcProductWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcProductExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业融资产品入库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyVcProductWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyVcProductWarehouseCommandExecutor dataCompanyVcProductWarehouseCommandExecutor;
    private DataCompanyVcProductCompetitiveProductRelWarehouseCommandExecutor dataCompanyVcProductCompetitiveProductRelWarehouseCommandExecutor;

    /**
     * 企业融资产品入库
     * @param dataCompanyVcProductExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyVcProductExWarehouseVO> dataCompanyVcProductExWarehouseVOPageResponse) {
        List<DataCompanyVcProductExWarehouseVO> dataCompanyVcProductExWarehouseVOs = dataCompanyVcProductExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanyVcProductExWarehouseVOs)) {
            for (DataCompanyVcProductExWarehouseVO dataCompanyVcProductExWarehouseVO : dataCompanyVcProductExWarehouseVOs) {
                if (dataCompanyVcProductExWarehouseVO != null) {
                    DataCompanyVcProductWarehouseCommand byDataCompanyVcProductExWarehouseVO = DataCompanyVcProductWarehouseCommand.createByDataCompanyVcProductExWarehouseVO(dataCompanyVcProductExWarehouseVO);
                    SingleResponse<DataCompanyVcProductExWarehouseVO> dataCompanyVcProductExWarehouseVOWarehouseSingleResponse = dataCompanyVcProductWarehouseCommandExecutor.warehouse(byDataCompanyVcProductExWarehouseVO);
                    List<DataCompanyVcProductExWarehouseVO> dataCompanyVcProductExWarehouseVOWarehouseSingleResponseCompetitiveProducts = dataCompanyVcProductExWarehouseVO.getCompetitiveProducts();
                    if (CollectionUtil.isNotEmpty(dataCompanyVcProductExWarehouseVOWarehouseSingleResponseCompetitiveProducts)) {
                        // 融资产品入库
                        for (DataCompanyVcProductExWarehouseVO competitiveProductVO : dataCompanyVcProductExWarehouseVOWarehouseSingleResponseCompetitiveProducts) {
                            DataCompanyVcProductWarehouseCommand dataCompanyVcProductWarehouseCommand = DataCompanyVcProductWarehouseCommand.createByDataCompanyVcProductExWarehouseVO(competitiveProductVO);
                            SingleResponse<DataCompanyVcProductExWarehouseVO> competitiveProductsWarehouseVOSingleResponse = dataCompanyVcProductWarehouseCommandExecutor.warehouse(dataCompanyVcProductWarehouseCommand);
                            // 关联表入库
                            DataCompanyVcProductCompetitiveProductRelWarehouseCommand dataCompanyVcProductCompetitiveProductRelWarehouseCommand = DataCompanyVcProductCompetitiveProductRelWarehouseCommand.create(dataCompanyVcProductExWarehouseVOWarehouseSingleResponse.getData().getId(), competitiveProductsWarehouseVOSingleResponse.getData().getId());
                            dataCompanyVcProductCompetitiveProductRelWarehouseCommandExecutor.warehouse(dataCompanyVcProductCompetitiveProductRelWarehouseCommand);
                        }
                    }
                }
            }
        }
    }

    @Autowired
    public void setDataCompanyVcProductWarehouseCommandExecutor(DataCompanyVcProductWarehouseCommandExecutor dataCompanyVcProductWarehouseCommandExecutor) {
        this.dataCompanyVcProductWarehouseCommandExecutor = dataCompanyVcProductWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcProductCompetitiveProductRelWarehouseCommandExecutor(DataCompanyVcProductCompetitiveProductRelWarehouseCommandExecutor dataCompanyVcProductCompetitiveProductRelWarehouseCommandExecutor) {
        this.dataCompanyVcProductCompetitiveProductRelWarehouseCommandExecutor = dataCompanyVcProductCompetitiveProductRelWarehouseCommandExecutor;
    }
}
