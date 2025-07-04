package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportShareholderWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <p>
 * 企业年报股东 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:37
 */
@Data
@Schema
public class DataCompanyAnnualReportShareholderCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "企业id 不能为空")
    @Schema(description = "企业id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @NotNull(message = "企业年报id 不能为空")
    @Schema(description = "企业年报id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyAnnualReportId;


    @NotNull(message = "年报年度 不能为空")
    @Schema(description = "年报年度",requiredMode = Schema.RequiredMode.REQUIRED)
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

	@Schema(description = "实缴出资方式")
	private Long actualCapitalTypeDictId;


    @Schema(description = "实缴出资日期")
    private LocalDate actualCapitalDate;

    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;

    public static DataCompanyAnnualReportShareholderCreateCommand createByWarehouseCommand(DataCompanyAnnualReportShareholderWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyAnnualReportShareholderCreateCommand command = new DataCompanyAnnualReportShareholderCreateCommand();
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
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();


        return command;
    }

}
