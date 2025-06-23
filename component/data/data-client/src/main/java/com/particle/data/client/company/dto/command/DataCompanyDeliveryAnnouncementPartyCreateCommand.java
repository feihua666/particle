package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyDeliveryAnnouncementPartyWarehouseCommand;

/**
 * <p>
 * 企业送达公告当事人 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:33
 */
@Data
@Schema
public class DataCompanyDeliveryAnnouncementPartyCreateCommand extends AbstractBaseCommand {



    @Schema(description = "送达公告表id")
    private Long companyDeliveryAnnouncementId;


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


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    









    public static DataCompanyDeliveryAnnouncementPartyCreateCommand createByWarehouseCommand(DataCompanyDeliveryAnnouncementPartyWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyDeliveryAnnouncementPartyCreateCommand command = new DataCompanyDeliveryAnnouncementPartyCreateCommand();
                command.companyDeliveryAnnouncementId = dataCompanyBasicWarehouseCommand.getCompanyDeliveryAnnouncementId();
        command.partyName = dataCompanyBasicWarehouseCommand.getPartyName();
        command.isPartyNaturalPerson = dataCompanyBasicWarehouseCommand.getIsPartyNaturalPerson();
        command.partyCompanyId = dataCompanyBasicWarehouseCommand.getPartyCompanyId();
        command.partyCompanyPersonId = dataCompanyBasicWarehouseCommand.getPartyCompanyPersonId();
        command.partyRoleDictId = dataCompanyBasicWarehouseCommand.getPartyRoleDictId();
        command.partyDescription = dataCompanyBasicWarehouseCommand.getPartyDescription();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.getDataMd5();


        return command;
    }
}