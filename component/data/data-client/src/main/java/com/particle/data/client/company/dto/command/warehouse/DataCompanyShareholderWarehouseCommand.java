package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyShareholderExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业股东入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyShareholderWarehouseCommand extends AbstractBaseCommand {

    @NotNull(message = "企业表ID 不能为空")
    @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;

    @NotEmpty(message = "股东名称 不能为空")
    @Schema(description = "股东名称",requiredMode = Schema.RequiredMode.REQUIRED)
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

    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyShareholderDataMd5(shareholderName);
        }
        return dataMd5;
    }

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyId)
                && StrUtil.isEmpty(shareholderName)
                && Objects.isNull(isShareholderNaturalPerson)
                && Objects.isNull(shareholderCompanyId)
                && Objects.isNull(shareholderCompanyPersonId)
                && Objects.isNull(shareholdingPercent)
                && Objects.isNull(shareholdingNum)
                && Objects.isNull(shareholdingAmount)
                && Objects.isNull(shareholdingAmountCurrencyDictId)
                && Objects.isNull(subCapital)
                && Objects.isNull(subCapitalCurrencyDictId)
                && Objects.isNull(subCapitalTypeDictId)
                && Objects.isNull(subCapitalDate)
                && Objects.isNull(actualCapital)
                && Objects.isNull(actualCapitalCurrencyDictId)
                && Objects.isNull(actualCapitalDate)
                && Objects.isNull(isRegPublic)
                && Objects.isNull(isListedLatestPublic)
                && Objects.isNull(listedLatestPublicDate)
                && Objects.isNull(isYearReportLatestPublic)
                && Objects.isNull(yearReportLatestPublicYear);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyShareholderExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyId, exWarehouseVO.getCompanyId())) {
            this.companyId = null;
        }
        if (Objects.equals(shareholderName, exWarehouseVO.getShareholderName())) {
            this.shareholderName = null;
        }
        if (Objects.equals(isShareholderNaturalPerson, exWarehouseVO.getIsShareholderNaturalPerson())) {
            this.isShareholderNaturalPerson = null;
        }
        if (Objects.equals(shareholderCompanyId, exWarehouseVO.getShareholderCompanyId())) {
            this.shareholderCompanyId = null;
        }
        if (Objects.equals(shareholderCompanyPersonId, exWarehouseVO.getShareholderCompanyPersonId())) {
            this.shareholderCompanyPersonId = null;
        }
        if (NumberUtil.equals(shareholdingPercent, exWarehouseVO.getShareholdingPercent())) {
            this.shareholdingPercent = null;
        }
        if (Objects.equals(shareholdingNum, exWarehouseVO.getShareholdingNum())) {
            this.shareholdingNum = null;
        }
        if (NumberUtil.equals(shareholdingAmount, exWarehouseVO.getShareholdingAmount())) {
            this.shareholdingAmount = null;
        }
        if (Objects.equals(shareholdingAmountCurrencyDictId, exWarehouseVO.getShareholdingAmountCurrencyDictId())) {
            this.shareholdingAmountCurrencyDictId = null;
        }
        if (NumberUtil.equals(subCapital, exWarehouseVO.getSubCapital())) {
            this.subCapital = null;
        }
        if (Objects.equals(subCapitalCurrencyDictId, exWarehouseVO.getSubCapitalCurrencyDictId())) {
            this.subCapitalCurrencyDictId = null;
        }
        if (Objects.equals(subCapitalTypeDictId, exWarehouseVO.getSubCapitalTypeDictId())) {
            this.subCapitalTypeDictId = null;
        }
        if (Objects.equals(subCapitalDate, exWarehouseVO.getSubCapitalDate())) {
            this.subCapitalDate = null;
        }
        if (NumberUtil.equals(actualCapital, exWarehouseVO.getActualCapital())) {
            this.actualCapital = null;
        }
        if (Objects.equals(actualCapitalCurrencyDictId, exWarehouseVO.getActualCapitalCurrencyDictId())) {
            this.actualCapitalCurrencyDictId = null;
        }
        if (Objects.equals(actualCapitalDate, exWarehouseVO.getActualCapitalDate())) {
            this.actualCapitalDate = null;
        }
        if (Objects.equals(isRegPublic, exWarehouseVO.getIsRegPublic())) {
            this.isRegPublic = null;
        }
        if (Objects.equals(isListedLatestPublic, exWarehouseVO.getIsListedLatestPublic())) {
            this.isListedLatestPublic = null;
        }
        if (Objects.equals(listedLatestPublicDate, exWarehouseVO.getListedLatestPublicDate())) {
            this.listedLatestPublicDate = null;
        }
        if (Objects.equals(isYearReportLatestPublic, exWarehouseVO.getIsYearReportLatestPublic())) {
            this.isYearReportLatestPublic = null;
        }
        if (Objects.equals(yearReportLatestPublicYear, exWarehouseVO.getYearReportLatestPublicYear())) {
            this.yearReportLatestPublicYear = null;
        }

    }

    public static DataCompanyShareholderWarehouseCommand createByDataCompanyShareholderExWarehouseVO(DataCompanyShareholderExWarehouseVO dataCompanyBasicWarehouseCommand){
        DataCompanyShareholderWarehouseCommand command = new DataCompanyShareholderWarehouseCommand();
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
