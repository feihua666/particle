package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportForeignGuaranteeWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <p>
 * 企业年报对外担保 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:11
 */
@Data
@Schema
public class DataCompanyAnnualReportForeignGuaranteeCreateCommand extends AbstractBaseCommand {



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


    @Schema(description = "债务人名称")
    private String debtorName;


    @Schema(description = "是否债务人为自然人")
    private Boolean isDebtorNaturalPerson;


    @Schema(description = "债务人公司id")
    private Long debtorCompanyId;


    @Schema(description = "债务人个人id")
    private Long debtorCompanyPersonId;


    @Schema(description = "债权人名称")
    private String creditorName;


    @Schema(description = "是否债权人为自然人")
    private Boolean isCreditorNaturalPerson;


    @Schema(description = "债权人公司id")
    private Long creditorCompanyId;


    @Schema(description = "债权人个人id")
    private Long creditorCompanyPersonId;


    @Schema(description = "主债权种类")
    private Long creditoTypeDictId;


    @Schema(description = "主债权金额(万元)")
    private BigDecimal creditoAmount;


    @Schema(description = "主债权金额币种")
    private Long creditoAmountCurrencyDictId;


    @Schema(description = "履行债务的期限自")
    private LocalDate debtFromDate;


    @Schema(description = "履行债务的期限至")
    private LocalDate debtToDate;


    @Schema(description = "保证担保的范围")
    private String guaranteeScope;


    @Schema(description = "保证的期间")
    private Long guaranteeTermDictId;


    @Schema(description = "保证的方式")
    private Long guaranteeTypeDictId;


    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;

    public static DataCompanyAnnualReportForeignGuaranteeCreateCommand createByWarehouseCommand(DataCompanyAnnualReportForeignGuaranteeWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyAnnualReportForeignGuaranteeCreateCommand command = new DataCompanyAnnualReportForeignGuaranteeCreateCommand();
        command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.companyAnnualReportId = dataCompanyBasicWarehouseCommand.getCompanyAnnualReportId();
        command.year = dataCompanyBasicWarehouseCommand.getYear();
        command.serialNumber = dataCompanyBasicWarehouseCommand.getSerialNumber();
        command.debtorName = dataCompanyBasicWarehouseCommand.getDebtorName();
        command.isDebtorNaturalPerson = dataCompanyBasicWarehouseCommand.getIsDebtorNaturalPerson();
        command.debtorCompanyId = dataCompanyBasicWarehouseCommand.getDebtorCompanyId();
        command.debtorCompanyPersonId = dataCompanyBasicWarehouseCommand.getDebtorCompanyPersonId();
        command.creditorName = dataCompanyBasicWarehouseCommand.getCreditorName();
        command.isCreditorNaturalPerson = dataCompanyBasicWarehouseCommand.getIsCreditorNaturalPerson();
        command.creditorCompanyId = dataCompanyBasicWarehouseCommand.getCreditorCompanyId();
        command.creditorCompanyPersonId = dataCompanyBasicWarehouseCommand.getCreditorCompanyPersonId();
        command.creditoTypeDictId = dataCompanyBasicWarehouseCommand.getCreditoTypeDictId();
        command.creditoAmount = dataCompanyBasicWarehouseCommand.getCreditoAmount();
        command.creditoAmountCurrencyDictId = dataCompanyBasicWarehouseCommand.getCreditoAmountCurrencyDictId();
        command.debtFromDate = dataCompanyBasicWarehouseCommand.getDebtFromDate();
        command.debtToDate = dataCompanyBasicWarehouseCommand.getDebtToDate();
        command.guaranteeScope = dataCompanyBasicWarehouseCommand.getGuaranteeScope();
        command.guaranteeTermDictId = dataCompanyBasicWarehouseCommand.getGuaranteeTermDictId();
        command.guaranteeTypeDictId = dataCompanyBasicWarehouseCommand.getGuaranteeTypeDictId();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();


        return command;
    }
}
