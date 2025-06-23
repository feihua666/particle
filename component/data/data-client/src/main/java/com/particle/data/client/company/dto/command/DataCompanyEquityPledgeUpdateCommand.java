package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyEquityPledgeWarehouseCommand;

/**
 * <p>
 * 企业股权出质 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:10
 */
@Data
@Schema
public class DataCompanyEquityPledgeUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "企业表ID 不能为空")
        @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @Schema(description = "登记编号")
    private String regNo;


    @Schema(description = "出质人")
    private String pledgor;


    @Schema(description = "是否出质人为自然人")
    private Boolean isPledgorNaturalPerson;


    @Schema(description = "出质人公司id")
    private Long pledgorCompanyId;


    @Schema(description = "出质人个人id")
    private Long pledgorCompanyPersonId;


    @Schema(description = "出质人证照/证件号码")
    private String pledgorLicenseNo;


    @Schema(description = "出质股权数额（万股）")
    private BigDecimal equityAmount;
    

    @Schema(description = "质权人")
    private String pledgee;


    @Schema(description = "是否质权人为自然人")
    private Boolean isPledgeeNaturalPerson;


    @Schema(description = "质权人公司id")
    private Long pledgeeCompanyId;


    @Schema(description = "质权人个人id")
    private Long pledgeeCompanyPersonId;


    @Schema(description = "质权人证照/证件号码")
    private String pledgeeLicenseNo;


    @Schema(description = "股权出质设立登记日期")
    private LocalDate regDate;
    

    @Schema(description = "状态")
    private String statusName;


    @Schema(description = "公示日期")
    private LocalDate publishDate;
    

    @Schema(description = "变化情况")
    private String changeSituation;


    @Schema(description = "注销日期")
    private LocalDate cancelDate;
    

    @Schema(description = "注销原因")
    private String cancelReason;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    









    public static DataCompanyEquityPledgeUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyEquityPledgeWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyEquityPledgeUpdateCommand command = new DataCompanyEquityPledgeUpdateCommand();
        command.setId(id);
        command.setVersion(version);
                command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.regNo = dataCompanyBasicWarehouseCommand.getRegNo();
        command.pledgor = dataCompanyBasicWarehouseCommand.getPledgor();
        command.isPledgorNaturalPerson = dataCompanyBasicWarehouseCommand.getIsPledgorNaturalPerson();
        command.pledgorCompanyId = dataCompanyBasicWarehouseCommand.getPledgorCompanyId();
        command.pledgorCompanyPersonId = dataCompanyBasicWarehouseCommand.getPledgorCompanyPersonId();
        command.pledgorLicenseNo = dataCompanyBasicWarehouseCommand.getPledgorLicenseNo();
        command.equityAmount = dataCompanyBasicWarehouseCommand.getEquityAmount();
        command.pledgee = dataCompanyBasicWarehouseCommand.getPledgee();
        command.isPledgeeNaturalPerson = dataCompanyBasicWarehouseCommand.getIsPledgeeNaturalPerson();
        command.pledgeeCompanyId = dataCompanyBasicWarehouseCommand.getPledgeeCompanyId();
        command.pledgeeCompanyPersonId = dataCompanyBasicWarehouseCommand.getPledgeeCompanyPersonId();
        command.pledgeeLicenseNo = dataCompanyBasicWarehouseCommand.getPledgeeLicenseNo();
        command.regDate = dataCompanyBasicWarehouseCommand.getRegDate();
        command.statusName = dataCompanyBasicWarehouseCommand.getStatusName();
        command.publishDate = dataCompanyBasicWarehouseCommand.getPublishDate();
        command.changeSituation = dataCompanyBasicWarehouseCommand.getChangeSituation();
        command.cancelDate = dataCompanyBasicWarehouseCommand.getCancelDate();
        command.cancelReason = dataCompanyBasicWarehouseCommand.getCancelReason();


        return command;
    }
}