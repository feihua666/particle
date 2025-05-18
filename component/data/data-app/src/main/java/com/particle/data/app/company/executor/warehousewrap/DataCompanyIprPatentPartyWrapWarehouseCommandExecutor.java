package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprPatentPartyWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPartyExWarehouseVO;
import com.particle.data.common.tool.CompanyNameCheckTool;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权当事人入库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/16 17:26
 */
@Component
@Validated
public class DataCompanyIprPatentPartyWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor{

    private DataCompanyIprPatentPartyWarehouseCommandExecutor dataCompanyIprPatentPartyWarehouseCommandExecutor;

    /**
     * 企业知识产权当事人入库
     *
     * @param dataCompanyIprPatentPartyWarehouseCommand
     * @return
     */
    public SingleResponse<DataCompanyIprPatentPartyExWarehouseVO> warehouse(DataCompanyIprPatentPartyWarehouseCommand dataCompanyIprPatentPartyWarehouseCommand) {
        fillCompanyId(dataCompanyIprPatentPartyWarehouseCommand);
        return dataCompanyIprPatentPartyWarehouseCommandExecutor.warehouse(dataCompanyIprPatentPartyWarehouseCommand);
    }

    /**
     * 填充公司id
     * @param dataCompanyIprPatentPartyWarehouseCommand
     */
    private void fillCompanyId(DataCompanyIprPatentPartyWarehouseCommand dataCompanyIprPatentPartyWarehouseCommand) {
        String partyNameCn = dataCompanyIprPatentPartyWarehouseCommand.getPartyNameCn();
        if (StrUtil.isNotEmpty(partyNameCn)) {
            boolean checkIsCompanyName = CompanyNameCheckTool.checkIsCompanyName(partyNameCn);
            if (checkIsCompanyName) {
                Long companyId = warehouseByCompanyNameAndGetCompanyId(partyNameCn);
                dataCompanyIprPatentPartyWarehouseCommand.setPartyCompanyId(companyId);
                dataCompanyIprPatentPartyWarehouseCommand.setIsPartyNaturalPerson(false);
            }
        }

    }
    @Autowired
    public void setDataCompanyIprPatentPartyWarehouseCommandExecutor(DataCompanyIprPatentPartyWarehouseCommandExecutor dataCompanyIprPatentPartyWarehouseCommandExecutor) {
        this.dataCompanyIprPatentPartyWarehouseCommandExecutor = dataCompanyIprPatentPartyWarehouseCommandExecutor;
    }
}
