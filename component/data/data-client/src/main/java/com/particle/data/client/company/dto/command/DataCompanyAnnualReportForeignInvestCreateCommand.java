package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportForeignInvestWarehouseCommand;

/**
 * <p>
 * 企业年报对外投资 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:23
 */
@Data
@Schema
public class DataCompanyAnnualReportForeignInvestCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "企业表ID 不能为空")
        @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @NotNull(message = "企业年报表ID 不能为空")
        @Schema(description = "企业年报表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyAnnualReportId;


    @NotNull(message = "年报年度 不能为空")
        @Schema(description = "年报年度",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer year;



        @Schema(description = "序号")
    private Integer serialNumber;


    @Schema(description = "对外投资企业")
    private Long investCompanyId;

	@Schema(description = "对外投资企业名称")
	private String investCompanyName;

	@Schema(description = "对外投资企业统一社会信用代码")
	private String investCompanyUscc;


    @Schema(description = "对外投资比例")
    private BigDecimal investPercent;


    @Schema(description = "对外投资金额（万元）")
    private BigDecimal investAmount;


    @Schema(description = "对外投资金额币种")
    private Long investAmountCurrencyDictId;


    public static DataCompanyAnnualReportForeignInvestCreateCommand createByWarehouseCommand(DataCompanyAnnualReportForeignInvestWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyAnnualReportForeignInvestCreateCommand command = new DataCompanyAnnualReportForeignInvestCreateCommand();
        command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.companyAnnualReportId = dataCompanyBasicWarehouseCommand.getCompanyAnnualReportId();
        command.year = dataCompanyBasicWarehouseCommand.getYear();
        command.serialNumber = dataCompanyBasicWarehouseCommand.getSerialNumber();
        command.investCompanyId = dataCompanyBasicWarehouseCommand.getInvestCompanyId();
        command.investCompanyName = dataCompanyBasicWarehouseCommand.getInvestCompanyName();
        command.investCompanyUscc = dataCompanyBasicWarehouseCommand.getInvestCompanyUscc();
        command.investPercent = dataCompanyBasicWarehouseCommand.getInvestPercent();
        command.investAmount = dataCompanyBasicWarehouseCommand.getInvestAmount();
        command.investAmountCurrencyDictId = dataCompanyBasicWarehouseCommand.getInvestAmountCurrencyDictId();


        return command;
    }
}
