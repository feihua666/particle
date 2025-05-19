package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportEquityChangeWarehouseCommand;

/**
 * <p>
 * 企业年报股权变更 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:57
 */
@Data
@Schema
public class DataCompanyAnnualReportEquityChangeUpdateCommand extends AbstractBaseUpdateCommand {




    @Schema(description = "企业表ID")
    private Long companyId;



    @Schema(description = "企业年报表ID")
    private Long companyAnnualReportId;



    @Schema(description = "年报年度")
    private Integer year;



    @Schema(description = "序号")
    private Integer serialNumber;


    @Schema(description = "股东名称")
    private String shareholderName;


    @Schema(description = "是否股东为自然人")
    private Boolean isShareholderNaturalPerson;


    @Schema(description = "股东公司id")
    private Long shareholderCompanyId;


    @Schema(description = "股东个人id")
    private Long shareholderCompanyPersonId;


    @Schema(description = "变更前比例")
    private BigDecimal percentBefore;


    @Schema(description = "变更后比例")
    private BigDecimal percentAfter;


    @Schema(description = "变更日期")
    private LocalDate changeDate;


    public static DataCompanyAnnualReportEquityChangeUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyAnnualReportEquityChangeWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyAnnualReportEquityChangeUpdateCommand command = new DataCompanyAnnualReportEquityChangeUpdateCommand();
        command.setId(id);
        command.setVersion(version);
                command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.companyAnnualReportId = dataCompanyBasicWarehouseCommand.getCompanyAnnualReportId();
        command.year = dataCompanyBasicWarehouseCommand.getYear();
        command.serialNumber = dataCompanyBasicWarehouseCommand.getSerialNumber();
        command.shareholderName = dataCompanyBasicWarehouseCommand.getShareholderName();
        command.isShareholderNaturalPerson = dataCompanyBasicWarehouseCommand.getIsShareholderNaturalPerson();
        command.shareholderCompanyId = dataCompanyBasicWarehouseCommand.getShareholderCompanyId();
        command.shareholderCompanyPersonId = dataCompanyBasicWarehouseCommand.getShareholderCompanyPersonId();
        command.percentBefore = dataCompanyBasicWarehouseCommand.getPercentBefore();
        command.percentAfter = dataCompanyBasicWarehouseCommand.getPercentAfter();
        command.changeDate = dataCompanyBasicWarehouseCommand.getChangeDate();


        return command;
    }
}
