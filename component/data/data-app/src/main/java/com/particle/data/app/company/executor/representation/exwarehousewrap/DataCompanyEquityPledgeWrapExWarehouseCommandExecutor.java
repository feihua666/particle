package com.particle.data.app.company.executor.representation.exwarehousewrap;

import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyEquityPledgeExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.AbstractBaseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyEquityPledgeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyEquityPledgeExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业股权出质出库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyEquityPledgeWrapExWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyEquityPledgeExWarehouseCommandExecutor dataCompanyEquityPledgeExWarehouseCommandExecutor;

    /**
     * 企业股权出质出库
     * @param dataCompanyEquityPledgeExWarehouseQueryCommand
     */
    public PageResponse<DataCompanyEquityPledgeExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                       DataCompanyEquityPledgeExWarehouseQueryCommand dataCompanyEquityPledgeExWarehouseQueryCommand) {
        if (dataCompanyEquityPledgeExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanyEquityPledgeExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanyEquityPledgeExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyEquityPledgeExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyEquityPledgeExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        return dataCompanyEquityPledgeExWarehouseCommandExecutor.exWarehouse(dataCompanyEquityPledgeExWarehouseQueryCommand);

    }

    @Autowired
    public void setDataCompanyEquityPledgeExWarehouseCommandExecutor(DataCompanyEquityPledgeExWarehouseCommandExecutor dataCompanyEquityPledgeExWarehouseCommandExecutor) {
        this.dataCompanyEquityPledgeExWarehouseCommandExecutor = dataCompanyEquityPledgeExWarehouseCommandExecutor;
    }
}
