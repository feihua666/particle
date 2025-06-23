package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCaseFilingPartyWarehouseCommand;

/**
 * <p>
 * 企业立案信息当事人 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:50
 */
@Data
@Schema
public class DataCompanyCaseFilingPartyUpdateCommand extends AbstractBaseUpdateCommand {



    @Schema(description = "立案信息表id")
    private Long companyCaseFilingId;


    @Schema(description = "当事人名称")
    private String partyName;


    @Schema(description = "是否法人为自然人")
    private Boolean isPartyNaturalPerson;


    @Schema(description = "当事人公司id")
    private Long partyCompanyId;


    @Schema(description = "当事人个人id")
    private Long partyCompanyPersonId;


    @Schema(description = "当事人角色")
    private Long partyRoleDictId;


    @Schema(description = "当事人描述信息")
    private String partyDescription;

    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;
    public static DataCompanyCaseFilingPartyUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyCaseFilingPartyWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyCaseFilingPartyUpdateCommand command = new DataCompanyCaseFilingPartyUpdateCommand();
        command.setId(id);
        command.setVersion(version);
        command.companyCaseFilingId = dataCompanyBasicWarehouseCommand.getCompanyCaseFilingId();
        command.partyName = dataCompanyBasicWarehouseCommand.getPartyName();
        command.isPartyNaturalPerson = dataCompanyBasicWarehouseCommand.getIsPartyNaturalPerson();
        command.partyCompanyId = dataCompanyBasicWarehouseCommand.getPartyCompanyId();
        command.partyCompanyPersonId = dataCompanyBasicWarehouseCommand.getPartyCompanyPersonId();
        command.partyRoleDictId = dataCompanyBasicWarehouseCommand.getPartyRoleDictId();
        command.partyDescription = dataCompanyBasicWarehouseCommand.getPartyDescription();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();

        return command;
    }
}
