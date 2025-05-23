package com.particle.data.app.company.executor.warehousewrap;

import com.particle.data.app.company.executor.warehouse.DataCompanyIprPatentPartyWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPartyExWarehouseVO;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

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
     * @param dataCompanyIprPatentPartyExWarehouseVOS
     * @param companyIprPatentId
     * @return
     */
    public void warehouse(List<DataCompanyIprPatentPartyExWarehouseVO> dataCompanyIprPatentPartyExWarehouseVOS,Long companyIprPatentId) {
        for (DataCompanyIprPatentPartyExWarehouseVO dataCompanyIprPatentPartyExWarehouseVO : dataCompanyIprPatentPartyExWarehouseVOS) {
            warehouse(dataCompanyIprPatentPartyExWarehouseVO,companyIprPatentId);
        }
    }
    /**
     * 企业知识产权当事人入库
     *
     * @param dataCompanyIprPatentPartyExWarehouseVO
     * @param companyIprPatentId
     * @return
     */
    public SingleResponse<DataCompanyIprPatentPartyExWarehouseVO> warehouse(DataCompanyIprPatentPartyExWarehouseVO dataCompanyIprPatentPartyExWarehouseVO,Long companyIprPatentId) {

        DataCompanyIprPatentPartyWarehouseCommand dataCompanyIprPatentPartyWarehouseCommand = DataCompanyIprPatentPartyWarehouseCommand.createByDataCompanyIprPatentPartyExWarehouseVO(dataCompanyIprPatentPartyExWarehouseVO);
        dataCompanyIprPatentPartyWarehouseCommand.setCompanyIprPatentId(companyIprPatentId);
        fillCompanyId(dataCompanyIprPatentPartyWarehouseCommand);
        return dataCompanyIprPatentPartyWarehouseCommandExecutor.warehouse(dataCompanyIprPatentPartyWarehouseCommand);
    }

    /**
     * 填充公司id
     * @param dataCompanyIprPatentPartyWarehouseCommand
     */
    private void fillCompanyId(DataCompanyIprPatentPartyWarehouseCommand dataCompanyIprPatentPartyWarehouseCommand) {
        String partyNameCn = dataCompanyIprPatentPartyWarehouseCommand.getPartyNameCn();
        NaturePerson naturePerson = checkNaturePerson(partyNameCn,
                dataCompanyIprPatentPartyWarehouseCommand.getPartyCompanyId(),
                dataCompanyIprPatentPartyWarehouseCommand.getPartyCompanyPersonId(),
                dataCompanyIprPatentPartyWarehouseCommand.getIsPartyNaturalPerson());
        if (naturePerson != null) {
            dataCompanyIprPatentPartyWarehouseCommand.setPartyCompanyId(naturePerson.getCompanyId());
            dataCompanyIprPatentPartyWarehouseCommand.setPartyCompanyPersonId(naturePerson.getPersonId());
            dataCompanyIprPatentPartyWarehouseCommand.setIsPartyNaturalPerson(naturePerson.getIsNaturePerson());
        }

    }
    @Autowired
    public void setDataCompanyIprPatentPartyWarehouseCommandExecutor(DataCompanyIprPatentPartyWarehouseCommandExecutor dataCompanyIprPatentPartyWarehouseCommandExecutor) {
        this.dataCompanyIprPatentPartyWarehouseCommandExecutor = dataCompanyIprPatentPartyWarehouseCommandExecutor;
    }
}
