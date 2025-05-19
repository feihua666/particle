package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportShareholderWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 企业年报股东 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:37
 */
@Data
@Schema
public class DataCompanyAnnualReportShareholderUpdateCommand extends AbstractBaseUpdateCommand {




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


    @Schema(description = "是否股东名称为自然人")
    private Boolean isShareholderNaturalPerson;


    @Schema(description = "股东名称公司id")
    private Long shareholderCompanyId;


    @Schema(description = "股东名称个人id")
    private Long shareholderCompanyPersonId;


    @Schema(description = "持股比例")
    private BigDecimal shareholdingPercent;


    @Schema(description = "认缴出资金额（万元）")
    private BigDecimal subCapital;


    @Schema(description = "认缴出资金额币种")
    private Long subCapitalCurrencyDictId;


    @Schema(description = "认缴出资方式")
    private Long subCapitalTypeDictId;


    @Schema(description = "认缴出资日期")
    private LocalDate subCapitalDate;


    @Schema(description = "实缴出资金额（万元）")
    private BigDecimal actualCapital;


    @Schema(description = "实缴出资金额币种")
    private Long actualCapitalCurrencyDictId;


    @Schema(description = "实缴出资日期")
    private LocalDate actualCapitalDate;

    public static DataCompanyAnnualReportShareholderUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyAnnualReportShareholderWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyAnnualReportShareholderUpdateCommand command = new DataCompanyAnnualReportShareholderUpdateCommand();
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
        command.shareholdingPercent = dataCompanyBasicWarehouseCommand.getShareholdingPercent();
        command.subCapital = dataCompanyBasicWarehouseCommand.getSubCapital();
        command.subCapitalCurrencyDictId = dataCompanyBasicWarehouseCommand.getSubCapitalCurrencyDictId();
        command.subCapitalTypeDictId = dataCompanyBasicWarehouseCommand.getSubCapitalTypeDictId();
        command.subCapitalDate = dataCompanyBasicWarehouseCommand.getSubCapitalDate();
        command.actualCapital = dataCompanyBasicWarehouseCommand.getActualCapital();
        command.actualCapitalCurrencyDictId = dataCompanyBasicWarehouseCommand.getActualCapitalCurrencyDictId();
        command.actualCapitalDate = dataCompanyBasicWarehouseCommand.getActualCapitalDate();

        return command;
    }
}
