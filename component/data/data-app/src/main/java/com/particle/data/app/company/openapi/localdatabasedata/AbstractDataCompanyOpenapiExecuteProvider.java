package com.particle.data.app.company.openapi.localdatabasedata;

import com.particle.data.app.company.executor.warehouse.DataCompanyWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyExWarehouseVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.openapi.api.portal.impl.AbstractLocalDataBaseDataOpenapiExecuteProvider;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 企业开放接口实现基础类
 * </p>
 *
 * @author yangwei
 * @since 2025-04-25 13:03:44
 */
public abstract class AbstractDataCompanyOpenapiExecuteProvider extends AbstractLocalDataBaseDataOpenapiExecuteProvider {
    private DataCompanyWarehouseCommandExecutor dataCompanyWarehouseCommandExecutor;


    /**
     * 尝试根据查询条件入库，并获取公司id
     * @param companyExWarehouseQueryCommand
     * @return
     */
    public Long tryWarehouseCompanyAndGetCompanyId(DataCompanyExWarehouseQueryCommand companyExWarehouseQueryCommand) {
        if (companyExWarehouseQueryCommand.isQueryConditionAllEmpty()) {
            return null;
        }
        if (companyExWarehouseQueryCommand.getId() != null) {
            return companyExWarehouseQueryCommand.getId();
        }
        DataCompanyWarehouseCommand dataCompanyWarehouseCommand = DataCompanyWarehouseCommand.createByDataCompanyExWarehouseQueryCommand(companyExWarehouseQueryCommand);
        DataCompanyExWarehouseVO dataCompanyExWarehouseVO = warehouseCompany(dataCompanyWarehouseCommand);
        if (dataCompanyExWarehouseVO != null) {
            return dataCompanyExWarehouseVO.getId();
        }
        return null;
    }

    /**
     * 入库
     * @param dataCompanyWarehouseCommand
     * @return
     */
    public DataCompanyExWarehouseVO warehouseCompany(DataCompanyWarehouseCommand dataCompanyWarehouseCommand) {
        SingleResponse<DataCompanyExWarehouseVO> dataCompanyExWarehouseVOSingleResponse = dataCompanyWarehouseCommandExecutor.warehouse(dataCompanyWarehouseCommand);
        return dataCompanyExWarehouseVOSingleResponse.getData();
    }

    @Autowired
    public void setDataCompanyWarehouseCommandExecutor(DataCompanyWarehouseCommandExecutor dataCompanyWarehouseCommandExecutor) {
        this.dataCompanyWarehouseCommandExecutor = dataCompanyWarehouseCommandExecutor;
    }
}
