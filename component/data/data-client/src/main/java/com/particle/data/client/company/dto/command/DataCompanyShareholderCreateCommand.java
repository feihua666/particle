package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import com.particle.data.client.company.dto.command.warehouse.DataCompanyShareholderWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * <p>
 * 企业股东 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:01
 */
@Data
@Schema
public class DataCompanyShareholderCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "企业表ID 不能为空")
    @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @Schema(description = "股东名称")
    private String shareholderName;


    @Schema(description = "是否法人为自然人")
    private Boolean isShareholderNaturalPerson;


    @Schema(description = "股东公司id")
    private Long shareholderCompanyId;


    @Schema(description = "股东个人id")
    private Long shareholderCompanyPersonId;


    @Schema(description = "持股比例")
    private BigDecimal shareholdingPercent;


    @Schema(description = "持股数量")
    private Integer shareholdingNum;


    @Schema(description = "持股金额（万元）")
    private BigDecimal shareholdingAmount;


    @Schema(description = "持股金额币种")
    private Long shareholdingAmountCurrencyDictId;


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


    @Schema(description = "是否工商登记股东")
    private Boolean isRegPublic;


    @Schema(description = "是否上市最新公示股东")
    private Boolean isListedLatestPublic;

	@Schema(description = "上市最新公示股东日期")
	private LocalDate listedLatestPublicDate;


    @Schema(description = "是否最新年报股东")
    private Boolean isYearReportLatestPublic;


    @Schema(description = "最新年报股东年份")
    private Integer yearReportLatestPublicYear;

    public static DataCompanyShareholderCreateCommand createByWarehouseCommand(DataCompanyShareholderWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyShareholderCreateCommand command = new DataCompanyShareholderCreateCommand();
        command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.shareholderName = dataCompanyBasicWarehouseCommand.getShareholderName();
        command.isShareholderNaturalPerson = dataCompanyBasicWarehouseCommand.getIsShareholderNaturalPerson();
        command.shareholderCompanyId = dataCompanyBasicWarehouseCommand.getShareholderCompanyId();
        command.shareholderCompanyPersonId = dataCompanyBasicWarehouseCommand.getShareholderCompanyPersonId();
        command.shareholdingPercent = dataCompanyBasicWarehouseCommand.getShareholdingPercent();
        command.shareholdingNum = dataCompanyBasicWarehouseCommand.getShareholdingNum();
        command.shareholdingAmount = dataCompanyBasicWarehouseCommand.getShareholdingAmount();
        command.shareholdingAmountCurrencyDictId = dataCompanyBasicWarehouseCommand.getShareholdingAmountCurrencyDictId();
        command.subCapital = dataCompanyBasicWarehouseCommand.getSubCapital();
        command.subCapitalCurrencyDictId = dataCompanyBasicWarehouseCommand.getSubCapitalCurrencyDictId();
        command.subCapitalTypeDictId = dataCompanyBasicWarehouseCommand.getSubCapitalTypeDictId();
        command.subCapitalDate = dataCompanyBasicWarehouseCommand.getSubCapitalDate();
        command.actualCapital = dataCompanyBasicWarehouseCommand.getActualCapital();
        command.actualCapitalCurrencyDictId = dataCompanyBasicWarehouseCommand.getActualCapitalCurrencyDictId();
        command.actualCapitalDate = dataCompanyBasicWarehouseCommand.getActualCapitalDate();
        command.isRegPublic = dataCompanyBasicWarehouseCommand.getIsRegPublic();
        command.isListedLatestPublic = dataCompanyBasicWarehouseCommand.getIsListedLatestPublic();
        command.listedLatestPublicDate = dataCompanyBasicWarehouseCommand.getListedLatestPublicDate();
        command.isYearReportLatestPublic = dataCompanyBasicWarehouseCommand.getIsYearReportLatestPublic();
        command.yearReportLatestPublicYear = dataCompanyBasicWarehouseCommand.getYearReportLatestPublicYear();

        return command;
    }
}
