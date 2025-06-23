package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprWorkCopyrightWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprWorkCopyrightWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprWorkCopyrightExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业作品著作入库
 * </p>
 *
 * @author yangwei
 * @since 2025-06-17 10:35:04
 */
@Component
@Validated
public class DataCompanyIprWorkCopyrightWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyIprWorkCopyrightWarehouseCommandExecutor dataCompanyIprWorkCopyrightWarehouseCommandExecutor;

    /**
     * 企业作品著作入库
     * @param dataCompanyIprWorkCopyrightExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyIprWorkCopyrightExWarehouseVO> dataCompanyIprWorkCopyrightExWarehouseVOPageResponse) {
        List<DataCompanyIprWorkCopyrightExWarehouseVO> dataCompanyIprWorkCopyrightExWarehouseVOs = dataCompanyIprWorkCopyrightExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanyIprWorkCopyrightExWarehouseVOs)) {
            for (DataCompanyIprWorkCopyrightExWarehouseVO dataCompanyIprWorkCopyrightExWarehouseVO : dataCompanyIprWorkCopyrightExWarehouseVOs) {
                DataCompanyIprWorkCopyrightWarehouseCommand byDataCompanyIprWorkCopyrightExWarehouseVO = DataCompanyIprWorkCopyrightWarehouseCommand.createByDataCompanyIprWorkCopyrightExWarehouseVO(dataCompanyIprWorkCopyrightExWarehouseVO);
                fillId(byDataCompanyIprWorkCopyrightExWarehouseVO);
                dataCompanyIprWorkCopyrightWarehouseCommandExecutor.warehouse(byDataCompanyIprWorkCopyrightExWarehouseVO);
            }
        }

    }

    private void fillId(DataCompanyIprWorkCopyrightWarehouseCommand dataCompanyIprWorkCopyrightWarehouseCommand) {
        // 著作权人
        NaturePerson legalNaturePerson = checkNaturePerson(dataCompanyIprWorkCopyrightWarehouseCommand.getCopyrightOwner(),
                dataCompanyIprWorkCopyrightWarehouseCommand.getCopyrightOwnerCompanyId(),
                dataCompanyIprWorkCopyrightWarehouseCommand.getCopyrightOwnerCompanyPersonId(),
                dataCompanyIprWorkCopyrightWarehouseCommand.getIsCopyrightOwnerNaturalPerson());
        if (legalNaturePerson != null) {
            dataCompanyIprWorkCopyrightWarehouseCommand.setCopyrightOwnerCompanyId(legalNaturePerson.getCompanyId());
            dataCompanyIprWorkCopyrightWarehouseCommand.setCopyrightOwnerCompanyPersonId(legalNaturePerson.getPersonId());
            dataCompanyIprWorkCopyrightWarehouseCommand.setIsCopyrightOwnerNaturalPerson(legalNaturePerson.getIsNaturePerson());
        }
    }

    @Autowired
    public void setDataCompanyIprWorkCopyrightWarehouseCommandExecutor(DataCompanyIprWorkCopyrightWarehouseCommandExecutor dataCompanyIprWorkCopyrightWarehouseCommandExecutor) {
        this.dataCompanyIprWorkCopyrightWarehouseCommandExecutor = dataCompanyIprWorkCopyrightWarehouseCommandExecutor;
    }
}
