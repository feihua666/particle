package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportForeignGuaranteeExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业年报对外担保入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyAnnualReportForeignGuaranteeWarehouseCommand extends AbstractBaseCommand {

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

    public String obtainDataMd5() {
        return SomeMd5Tool.dataCompanyAnnualReportForeignGuaranteeDataMd5(debtorName, creditorName);
    }
    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyId)
                && Objects.isNull(companyAnnualReportId)
                && Objects.isNull(year)
                && Objects.isNull(serialNumber)
                && StrUtil.isEmpty(debtorName)
                && Objects.isNull(isDebtorNaturalPerson)
                && Objects.isNull(debtorCompanyId)
                && Objects.isNull(debtorCompanyPersonId)
                && StrUtil.isEmpty(creditorName)
                && Objects.isNull(isCreditorNaturalPerson)
                && Objects.isNull(creditorCompanyId)
                && Objects.isNull(creditorCompanyPersonId)
                && Objects.isNull(creditoTypeDictId)
                && Objects.isNull(creditoAmount)
                && Objects.isNull(creditoAmountCurrencyDictId)
                && Objects.isNull(debtFromDate)
                && Objects.isNull(debtToDate)
                && StrUtil.isEmpty(guaranteeScope)
                && Objects.isNull(guaranteeTermDictId)
                && Objects.isNull(guaranteeTypeDictId)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyAnnualReportForeignGuaranteeExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyId, exWarehouseVO.getCompanyId())) {
            this.companyId = null;
        }
        if (Objects.equals(companyAnnualReportId, exWarehouseVO.getCompanyAnnualReportId())) {
            this.companyAnnualReportId = null;
        }
        if (Objects.equals(year, exWarehouseVO.getYear())) {
            this.year = null;
        }
        if (Objects.equals(serialNumber, exWarehouseVO.getSerialNumber())) {
            this.serialNumber = null;
        }
        if (Objects.equals(debtorName, exWarehouseVO.getDebtorName())) {
            this.debtorName = null;
        }
        if (Objects.equals(isDebtorNaturalPerson, exWarehouseVO.getIsDebtorNaturalPerson())) {
            this.isDebtorNaturalPerson = null;
        }
        if (Objects.equals(debtorCompanyId, exWarehouseVO.getDebtorCompanyId())) {
            this.debtorCompanyId = null;
        }
        if (Objects.equals(debtorCompanyPersonId, exWarehouseVO.getDebtorCompanyPersonId())) {
            this.debtorCompanyPersonId = null;
        }
        if (Objects.equals(creditorName, exWarehouseVO.getCreditorName())) {
            this.creditorName = null;
        }
        if (Objects.equals(isCreditorNaturalPerson, exWarehouseVO.getIsCreditorNaturalPerson())) {
            this.isCreditorNaturalPerson = null;
        }
        if (Objects.equals(creditorCompanyId, exWarehouseVO.getCreditorCompanyId())) {
            this.creditorCompanyId = null;
        }
        if (Objects.equals(creditorCompanyPersonId, exWarehouseVO.getCreditorCompanyPersonId())) {
            this.creditorCompanyPersonId = null;
        }
        if (Objects.equals(creditoTypeDictId, exWarehouseVO.getCreditoTypeDictId())) {
            this.creditoTypeDictId = null;
        }
        if (NumberUtil.equals(creditoAmount, exWarehouseVO.getCreditoAmount())) {
            this.creditoAmount = null;
        }
        if (Objects.equals(creditoAmountCurrencyDictId, exWarehouseVO.getCreditoAmountCurrencyDictId())) {
            this.creditoAmountCurrencyDictId = null;
        }
        if (Objects.equals(debtFromDate, exWarehouseVO.getDebtFromDate())) {
            this.debtFromDate = null;
        }
        if (Objects.equals(debtToDate, exWarehouseVO.getDebtToDate())) {
            this.debtToDate = null;
        }
        if (Objects.equals(guaranteeScope, exWarehouseVO.getGuaranteeScope())) {
            this.guaranteeScope = null;
        }
        if (Objects.equals(guaranteeTermDictId, exWarehouseVO.getGuaranteeTermDictId())) {
            this.guaranteeTermDictId = null;
        }
        if (Objects.equals(guaranteeTypeDictId, exWarehouseVO.getGuaranteeTypeDictId())) {
            this.guaranteeTypeDictId = null;
        }

    }

    public static DataCompanyAnnualReportForeignGuaranteeWarehouseCommand createByDataCompanyAnnualReportForeignGuaranteeExWarehouseVO(DataCompanyAnnualReportForeignGuaranteeExWarehouseVO exWarehouseVO) {
        DataCompanyAnnualReportForeignGuaranteeWarehouseCommand command = new DataCompanyAnnualReportForeignGuaranteeWarehouseCommand();
        command.companyId = exWarehouseVO.getCompanyId();
        command.companyAnnualReportId = exWarehouseVO.getCompanyAnnualReportId();
        command.year = exWarehouseVO.getYear();
        command.serialNumber = exWarehouseVO.getSerialNumber();
        command.debtorName = exWarehouseVO.getDebtorName();
        command.isDebtorNaturalPerson = exWarehouseVO.getIsDebtorNaturalPerson();
        command.debtorCompanyId = exWarehouseVO.getDebtorCompanyId();
        command.debtorCompanyPersonId = exWarehouseVO.getDebtorCompanyPersonId();
        command.creditorName = exWarehouseVO.getCreditorName();
        command.isCreditorNaturalPerson = exWarehouseVO.getIsCreditorNaturalPerson();
        command.creditorCompanyId = exWarehouseVO.getCreditorCompanyId();
        command.creditorCompanyPersonId = exWarehouseVO.getCreditorCompanyPersonId();
        command.creditoTypeDictId = exWarehouseVO.getCreditoTypeDictId();
        command.creditoAmount = exWarehouseVO.getCreditoAmount();
        command.creditoAmountCurrencyDictId = exWarehouseVO.getCreditoAmountCurrencyDictId();
        command.debtFromDate = exWarehouseVO.getDebtFromDate();
        command.debtToDate = exWarehouseVO.getDebtToDate();
        command.guaranteeScope = exWarehouseVO.getGuaranteeScope();
        command.guaranteeTermDictId = exWarehouseVO.getGuaranteeTermDictId();
        command.guaranteeTypeDictId = exWarehouseVO.getGuaranteeTypeDictId();


        return command;
    }
}
