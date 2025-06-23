package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportShareholderExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业年报股东入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyAnnualReportShareholderWarehouseCommand extends AbstractBaseCommand {



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

    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyAnnualReportShareholderDataMd5(shareholderName);
        }
        return dataMd5;
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
                && StrUtil.isEmpty(shareholderName)
                && Objects.isNull(isShareholderNaturalPerson)
                && Objects.isNull(shareholderCompanyId)
                && Objects.isNull(shareholderCompanyPersonId)
                && Objects.isNull(shareholdingPercent)
                && Objects.isNull(subCapital)
                && Objects.isNull(subCapitalCurrencyDictId)
                && Objects.isNull(subCapitalTypeDictId)
                && Objects.isNull(subCapitalDate)
                && Objects.isNull(actualCapital)
                && Objects.isNull(actualCapitalCurrencyDictId)
                && Objects.isNull(actualCapitalTypeDictId)
                && Objects.isNull(actualCapitalDate);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyAnnualReportShareholderExWarehouseVO exWarehouseVO) {
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
        if (Objects.equals(actualCapitalTypeDictId, exWarehouseVO.getActualCapitalTypeDictId())) {
            this.actualCapitalTypeDictId = null;
        }
        if (Objects.equals(actualCapitalDate, exWarehouseVO.getActualCapitalDate())) {
            this.actualCapitalDate = null;
        }

    }

    public static DataCompanyAnnualReportShareholderWarehouseCommand createByDataCompanyAnnualReportShareholderExWarehouseVO(DataCompanyAnnualReportShareholderExWarehouseVO exWarehouseVO) {
        DataCompanyAnnualReportShareholderWarehouseCommand command = new DataCompanyAnnualReportShareholderWarehouseCommand();
        command.companyId = exWarehouseVO.getCompanyId();
        command.companyAnnualReportId = exWarehouseVO.getCompanyAnnualReportId();
        command.year = exWarehouseVO.getYear();
        command.serialNumber = exWarehouseVO.getSerialNumber();
        command.shareholderName = exWarehouseVO.getShareholderName();
        command.isShareholderNaturalPerson = exWarehouseVO.getIsShareholderNaturalPerson();
        command.shareholderCompanyId = exWarehouseVO.getShareholderCompanyId();
        command.shareholderCompanyPersonId = exWarehouseVO.getShareholderCompanyPersonId();
        command.shareholdingPercent = exWarehouseVO.getShareholdingPercent();
        command.subCapital = exWarehouseVO.getSubCapital();
        command.subCapitalCurrencyDictId = exWarehouseVO.getSubCapitalCurrencyDictId();
        command.subCapitalTypeDictId = exWarehouseVO.getSubCapitalTypeDictId();
        command.subCapitalDate = exWarehouseVO.getSubCapitalDate();
        command.actualCapital = exWarehouseVO.getActualCapital();
        command.actualCapitalCurrencyDictId = exWarehouseVO.getActualCapitalCurrencyDictId();
        command.actualCapitalTypeDictId = exWarehouseVO.getActualCapitalTypeDictId();
        command.actualCapitalDate = exWarehouseVO.getActualCapitalDate();


        return command;
    }
}
