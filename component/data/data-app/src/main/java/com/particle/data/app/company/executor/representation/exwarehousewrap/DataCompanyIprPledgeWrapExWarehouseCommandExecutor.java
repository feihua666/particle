package com.particle.data.app.company.executor.representation.exwarehousewrap;

import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPledgeExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.AbstractBaseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPledgeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPledgeExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权出质出库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyIprPledgeWrapExWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyIprPledgeExWarehouseCommandExecutor dataCompanyIprPledgeExWarehouseCommandExecutor;

    /**
     * 企业知识产权出质出库
     * @param dataCompanyIprPledgeExWarehouseQueryCommand
     */
    public PageResponse<DataCompanyIprPledgeExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                       DataCompanyIprPledgeExWarehouseQueryCommand dataCompanyIprPledgeExWarehouseQueryCommand) {
        if (dataCompanyIprPledgeExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanyIprPledgeExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanyIprPledgeExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyIprPledgeExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyIprPledgeExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        return dataCompanyIprPledgeExWarehouseCommandExecutor.exWarehouse(dataCompanyIprPledgeExWarehouseQueryCommand);

    }

    @Autowired
    public void setDataCompanyIprPledgeExWarehouseCommandExecutor(DataCompanyIprPledgeExWarehouseCommandExecutor dataCompanyIprPledgeExWarehouseCommandExecutor) {
        this.dataCompanyIprPledgeExWarehouseCommandExecutor = dataCompanyIprPledgeExWarehouseCommandExecutor;
    }
}
