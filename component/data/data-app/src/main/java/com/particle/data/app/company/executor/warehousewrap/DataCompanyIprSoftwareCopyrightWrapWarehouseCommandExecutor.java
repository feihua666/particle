package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprSoftwareCopyrightWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprSoftwareCopyrightWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprSoftwareCopyrightExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业软件著作入库
 * </p>
 *
 * @author yangwei
 * @since 2025-06-17 10:35:04
 */
@Component
@Validated
public class DataCompanyIprSoftwareCopyrightWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyIprSoftwareCopyrightWarehouseCommandExecutor dataCompanyIprSoftwareCopyrightWarehouseCommandExecutor;

    /**
     * 企业软件著作入库
     * @param dataCompanyIprSoftwareCopyrightExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyIprSoftwareCopyrightExWarehouseVO> dataCompanyIprSoftwareCopyrightExWarehouseVOPageResponse) {
        List<DataCompanyIprSoftwareCopyrightExWarehouseVO> dataCompanyIprSoftwareCopyrightExWarehouseVOs = dataCompanyIprSoftwareCopyrightExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanyIprSoftwareCopyrightExWarehouseVOs)) {
            for (DataCompanyIprSoftwareCopyrightExWarehouseVO dataCompanyIprSoftwareCopyrightExWarehouseVO : dataCompanyIprSoftwareCopyrightExWarehouseVOs) {
                DataCompanyIprSoftwareCopyrightWarehouseCommand byDataCompanyIprSoftwareCopyrightExWarehouseVO = DataCompanyIprSoftwareCopyrightWarehouseCommand.createByDataCompanyIprSoftwareCopyrightExWarehouseVO(dataCompanyIprSoftwareCopyrightExWarehouseVO);
                fillId(byDataCompanyIprSoftwareCopyrightExWarehouseVO);
                dataCompanyIprSoftwareCopyrightWarehouseCommandExecutor.warehouse(byDataCompanyIprSoftwareCopyrightExWarehouseVO);
            }
        }

    }

    private void fillId(DataCompanyIprSoftwareCopyrightWarehouseCommand dataCompanyIprSoftwareCopyrightWarehouseCommand) {
        // 著作权人
        NaturePerson legalNaturePerson = checkNaturePerson(dataCompanyIprSoftwareCopyrightWarehouseCommand.getCopyrightOwner(),
                dataCompanyIprSoftwareCopyrightWarehouseCommand.getCopyrightOwnerCompanyId(),
                dataCompanyIprSoftwareCopyrightWarehouseCommand.getCopyrightOwnerCompanyPersonId(),
                dataCompanyIprSoftwareCopyrightWarehouseCommand.getIsCopyrightOwnerNaturalPerson());
        if (legalNaturePerson != null) {
            dataCompanyIprSoftwareCopyrightWarehouseCommand.setCopyrightOwnerCompanyId(legalNaturePerson.getCompanyId());
            dataCompanyIprSoftwareCopyrightWarehouseCommand.setCopyrightOwnerCompanyPersonId(legalNaturePerson.getPersonId());
            dataCompanyIprSoftwareCopyrightWarehouseCommand.setIsCopyrightOwnerNaturalPerson(legalNaturePerson.getIsNaturePerson());
        }
    }

    @Autowired
    public void setDataCompanyIprSoftwareCopyrightWarehouseCommandExecutor(DataCompanyIprSoftwareCopyrightWarehouseCommandExecutor dataCompanyIprSoftwareCopyrightWarehouseCommandExecutor) {
        this.dataCompanyIprSoftwareCopyrightWarehouseCommandExecutor = dataCompanyIprSoftwareCopyrightWarehouseCommandExecutor;
    }
}
