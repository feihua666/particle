package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportForeignInvestWarehouseCommand;

/**
 * <p>
 * 企业年报对外投资 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:23
 */
@Data
@Schema
public class DataCompanyAnnualReportForeignInvestUpdateCommand extends AbstractBaseUpdateCommand {




    @Schema(description = "企业表ID")
    private Long companyId;



    @Schema(description = "企业年报表ID")
    private Long companyAnnualReportId;



    @Schema(description = "年报年度")
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


    public static DataCompanyAnnualReportForeignInvestUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyAnnualReportForeignInvestWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyAnnualReportForeignInvestUpdateCommand command = new DataCompanyAnnualReportForeignInvestUpdateCommand();
        command.setId(id);
        command.setVersion(version);
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
