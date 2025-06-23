package com.particle.data.app.company.executor.warehousewrap;

import com.particle.data.app.company.executor.warehouse.DataCompanyIprTrademarkPartyWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkPartyExWarehouseVO;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业知识产权商标当事人入库
 * </p>
 *
 * @author yangwei
 * @since 2025-06-16 17:03:20
 */
@Component
@Validated
public class DataCompanyIprTrademarkPartyWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor{

    private DataCompanyIprTrademarkPartyWarehouseCommandExecutor dataCompanyIprTrademarkPartyWarehouseCommandExecutor;

    /**
     * 企业知识产权商标当事人入库
     *
     * @param dataCompanyIprTrademarkPartyExWarehouseVOS
     * @param companyIprTrademarkId
     * @return
     */
    public void warehouse(List<DataCompanyIprTrademarkPartyExWarehouseVO> dataCompanyIprTrademarkPartyExWarehouseVOS,Long companyIprTrademarkId) {
        for (DataCompanyIprTrademarkPartyExWarehouseVO dataCompanyIprTrademarkPartyExWarehouseVO : dataCompanyIprTrademarkPartyExWarehouseVOS) {
            warehouse(dataCompanyIprTrademarkPartyExWarehouseVO,companyIprTrademarkId);
        }
    }
    /**
     * 企业知识产权商标当事人入库
     *
     * @param dataCompanyIprTrademarkPartyExWarehouseVO
     * @param companyIprTrademarkId
     * @return
     */
    public SingleResponse<DataCompanyIprTrademarkPartyExWarehouseVO> warehouse(DataCompanyIprTrademarkPartyExWarehouseVO dataCompanyIprTrademarkPartyExWarehouseVO,Long companyIprTrademarkId) {

        DataCompanyIprTrademarkPartyWarehouseCommand dataCompanyIprTrademarkPartyWarehouseCommand = DataCompanyIprTrademarkPartyWarehouseCommand.createByDataCompanyIprTrademarkPartyExWarehouseVO(dataCompanyIprTrademarkPartyExWarehouseVO);
        dataCompanyIprTrademarkPartyWarehouseCommand.setCompanyIprTrademarkId(companyIprTrademarkId);
        fillCompanyId(dataCompanyIprTrademarkPartyWarehouseCommand);
        return dataCompanyIprTrademarkPartyWarehouseCommandExecutor.warehouse(dataCompanyIprTrademarkPartyWarehouseCommand);
    }

    /**
     * 填充公司id
     * @param dataCompanyIprTrademarkPartyWarehouseCommand
     */
    private void fillCompanyId(DataCompanyIprTrademarkPartyWarehouseCommand dataCompanyIprTrademarkPartyWarehouseCommand) {
        String partyName = dataCompanyIprTrademarkPartyWarehouseCommand.getPartyName();
        NaturePerson naturePerson = checkNaturePerson(partyName,
                dataCompanyIprTrademarkPartyWarehouseCommand.getPartyCompanyId(),
                dataCompanyIprTrademarkPartyWarehouseCommand.getPartyCompanyPersonId(),
                dataCompanyIprTrademarkPartyWarehouseCommand.getIsPartyNaturalPerson());
        if (naturePerson != null) {
            dataCompanyIprTrademarkPartyWarehouseCommand.setPartyCompanyId(naturePerson.getCompanyId());
            dataCompanyIprTrademarkPartyWarehouseCommand.setPartyCompanyPersonId(naturePerson.getPersonId());
            dataCompanyIprTrademarkPartyWarehouseCommand.setIsPartyNaturalPerson(naturePerson.getIsNaturePerson());
        }

    }
    @Autowired
    public void setDataCompanyIprTrademarkPartyWarehouseCommandExecutor(DataCompanyIprTrademarkPartyWarehouseCommandExecutor dataCompanyIprTrademarkPartyWarehouseCommandExecutor) {
        this.dataCompanyIprTrademarkPartyWarehouseCommandExecutor = dataCompanyIprTrademarkPartyWarehouseCommandExecutor;
    }
}
