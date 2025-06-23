package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyBasicExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业基本信息入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyBasicWarehouseCommand extends AbstractBaseCommand {

    @NotNull(message = "企业id 不能为空")
    @Schema(description = "企业id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @Schema(description = "纳税人识别号")
    private String tin;


    @Schema(description = "登记状态")
    private Long statusDictId;


    @Schema(description = "性质")
    private Long natureDictId;


    @Schema(description = "法人名称")
    private String legalPersonName;


    @Schema(description = "是否法人为自然人")
    private Boolean isLegalPersonNaturalPerson;


    @Schema(description = "法人公司id")
    private Long legalPersonCompanyId;


    @Schema(description = "法人个人id")
    private Long legalPersonCompanyPersonId;


    @Schema(description = "企业类型")
    private Long typeDictId;


    @Schema(description = "注册地址")
    private String regAddress;


    @Schema(description = "注册地址")
    private String regAddressPostalCode;


    @Schema(description = "经营地址")
    private String businessAddress;


    @Schema(description = "经营地址")
    private String businessAddressPostalCode;


    @Schema(description = "成立日期")
    private LocalDate establishDate;


    @Schema(description = "营业期限开始日期")
    private LocalDate businessFromDate;


    @Schema(description = "营业期限终止日期")
    private LocalDate businessToDate;


    @Schema(description = "核准日期")
    private LocalDate approveDate;


    @Schema(description = "注销日期")
    private LocalDate cancelDate;


    @Schema(description = "注销原因")
    private String cancelReason;


    @Schema(description = "吊销日期")
    private LocalDate revokeDate;


    @Schema(description = "吊销原因")
    private String revokeReason;


    @Schema(description = "经营范围")
    private String businessScope;


    @Schema(description = "注册机关公司id")
    private Long regInstituteCompanyId;


    @Schema(description = "注册机关名称")
    private String regInstituteName;


    @Schema(description = "注册资本（万元）")
    private BigDecimal regCapital;


    @Schema(description = "注册资金币种")
    private Long regCapitalCurrencyDictId;


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
    private Long capitalTypeDictId;


    @Schema(description = "实缴出资日期")
    private LocalDate actualCapitalDate;


    @Schema(description = "手机号码")
    private String mobile;


    @Schema(description = "电话号码")
    private String telephone;


    @Schema(description = "邮箱")
    private String email;


    @Schema(description = "从业人数")
    private Integer employeeNum;


    @Schema(description = "社保人数")
    private Integer socialSecurityNum;


    @Schema(description = "最新年报年份")
    private Integer latestYearReportYear;


    @Schema(description = "规模类型")
    private Long scaleTypeDictId;


    @Schema(description = "经度")
    private String longitude;


    @Schema(description = "纬度")
    private String latitude;


    @Schema(description = "所属行业")
    private Long industryMainDictId;


    @Schema(description = "所属行业")
    private Long industryBigDictId;


    @Schema(description = "所属行业")
    private Long industryMiddleDictId;


    @Schema(description = "所属行业")
    private Long industrySmallDictId;


    @Schema(description = "所在省份")
    private Long provinceAreaId;


    @Schema(description = "所在城市")
    private Long cityAreaId;


    @Schema(description = "所在区县")
    private Long countyAreaId;


    @Schema(description = "是否上市")
    private Boolean isListed;


    @Schema(description = "股票代码")
    private String stockCode;


    @Schema(description = "上市类型")
    private Long listedTypeDictId;

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyId)
                && StrUtil.isEmpty(tin)
                && Objects.isNull(statusDictId)
                && Objects.isNull(natureDictId)
                && StrUtil.isEmpty(legalPersonName)
                && Objects.isNull(isLegalPersonNaturalPerson)
                && Objects.isNull(legalPersonCompanyId)
                && Objects.isNull(legalPersonCompanyPersonId)
                && Objects.isNull(typeDictId)
                && StrUtil.isEmpty(regAddress)
                && StrUtil.isEmpty(regAddressPostalCode)
                && StrUtil.isEmpty(businessAddress)
                && StrUtil.isEmpty(businessAddressPostalCode)
                && Objects.isNull(establishDate)
                && Objects.isNull(businessFromDate)
                && Objects.isNull(businessToDate)
                && Objects.isNull(approveDate)
                && Objects.isNull(cancelDate)
                && StrUtil.isEmpty(cancelReason)
                && Objects.isNull(revokeDate)
                && StrUtil.isEmpty(revokeReason)
                && StrUtil.isEmpty(businessScope)
                && Objects.isNull(regInstituteCompanyId)
                && StrUtil.isEmpty(regInstituteName)
                && Objects.isNull(regCapital)
                && Objects.isNull(regCapitalCurrencyDictId)
                && Objects.isNull(subCapital)
                && Objects.isNull(subCapitalCurrencyDictId)
                && Objects.isNull(subCapitalTypeDictId)
                && Objects.isNull(subCapitalDate)
                && Objects.isNull(actualCapital)
                && Objects.isNull(actualCapitalCurrencyDictId)
                && Objects.isNull(capitalTypeDictId)
                && Objects.isNull(actualCapitalDate)
                && StrUtil.isEmpty(mobile)
                && StrUtil.isEmpty(telephone)
                && StrUtil.isEmpty(email)
                && Objects.isNull(employeeNum)
                && Objects.isNull(socialSecurityNum)
                && Objects.isNull(latestYearReportYear)
                && Objects.isNull(scaleTypeDictId)
                && StrUtil.isEmpty(longitude)
                && StrUtil.isEmpty(latitude)
                && Objects.isNull(industryMainDictId)
                && Objects.isNull(industryBigDictId)
                && Objects.isNull(industryMiddleDictId)
                && Objects.isNull(industrySmallDictId)
                && Objects.isNull(provinceAreaId)
                && Objects.isNull(cityAreaId)
                && Objects.isNull(countyAreaId)
                && Objects.isNull(isListed)
                && StrUtil.isEmpty(stockCode)
                && Objects.isNull(listedTypeDictId);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyBasicExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyId, exWarehouseVO.getCompanyId())) {
            this.companyId = null;
        }
        if (Objects.equals(tin, exWarehouseVO.getTin())) {
            this.tin = null;
        }
        if (Objects.equals(statusDictId, exWarehouseVO.getStatusDictId())) {
            this.statusDictId = null;
        }
        if (Objects.equals(natureDictId, exWarehouseVO.getNatureDictId())) {
            this.natureDictId = null;
        }
        if (Objects.equals(legalPersonName, exWarehouseVO.getLegalPersonName())) {
            this.legalPersonName = null;
        }
        if (Objects.equals(isLegalPersonNaturalPerson, exWarehouseVO.getIsLegalPersonNaturalPerson())) {
            this.isLegalPersonNaturalPerson = null;
        }
        if (Objects.equals(legalPersonCompanyId, exWarehouseVO.getLegalPersonCompanyId())) {
            this.legalPersonCompanyId = null;
        }
        if (Objects.equals(legalPersonCompanyPersonId, exWarehouseVO.getLegalPersonCompanyPersonId())) {
            this.legalPersonCompanyPersonId = null;
        }
        if (Objects.equals(typeDictId, exWarehouseVO.getTypeDictId())) {
            this.typeDictId = null;
        }
        if (Objects.equals(regAddress, exWarehouseVO.getRegAddress())) {
            this.regAddress = null;
        }
        if (Objects.equals(regAddressPostalCode, exWarehouseVO.getRegAddressPostalCode())) {
            this.regAddressPostalCode = null;
        }
        if (Objects.equals(businessAddress, exWarehouseVO.getBusinessAddress())) {
            this.businessAddress = null;
        }
        if (Objects.equals(businessAddressPostalCode, exWarehouseVO.getBusinessAddressPostalCode())) {
            this.businessAddressPostalCode = null;
        }
        if (Objects.equals(establishDate, exWarehouseVO.getEstablishDate())) {
            this.establishDate = null;
        }
        if (Objects.equals(businessFromDate, exWarehouseVO.getBusinessFromDate())) {
            this.businessFromDate = null;
        }
        if (Objects.equals(businessToDate, exWarehouseVO.getBusinessToDate())) {
            this.businessToDate = null;
        }
        if (Objects.equals(approveDate, exWarehouseVO.getApproveDate())) {
            this.approveDate = null;
        }
        if (Objects.equals(cancelDate, exWarehouseVO.getCancelDate())) {
            this.cancelDate = null;
        }
        if (Objects.equals(cancelReason, exWarehouseVO.getCancelReason())) {
            this.cancelReason = null;
        }
        if (Objects.equals(revokeDate, exWarehouseVO.getRevokeDate())) {
            this.revokeDate = null;
        }
        if (Objects.equals(revokeReason, exWarehouseVO.getRevokeReason())) {
            this.revokeReason = null;
        }
        if (Objects.equals(businessScope, exWarehouseVO.getBusinessScope())) {
            this.businessScope = null;
        }
        if (Objects.equals(regInstituteCompanyId, exWarehouseVO.getRegInstituteCompanyId())) {
            this.regInstituteCompanyId = null;
        }
        if (Objects.equals(regInstituteName, exWarehouseVO.getRegInstituteName())) {
            this.regInstituteName = null;
        }
        if (NumberUtil.equals(regCapital, exWarehouseVO.getRegCapital())) {
            this.regCapital = null;
        }
        if (Objects.equals(regCapitalCurrencyDictId, exWarehouseVO.getRegCapitalCurrencyDictId())) {
            this.regCapitalCurrencyDictId = null;
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
        if (Objects.equals(capitalTypeDictId, exWarehouseVO.getCapitalTypeDictId())) {
            this.capitalTypeDictId = null;
        }
        if (Objects.equals(actualCapitalDate, exWarehouseVO.getActualCapitalDate())) {
            this.actualCapitalDate = null;
        }
        if (Objects.equals(mobile, exWarehouseVO.getMobile())) {
            this.mobile = null;
        }
        if (Objects.equals(telephone, exWarehouseVO.getTelephone())) {
            this.telephone = null;
        }
        if (Objects.equals(email, exWarehouseVO.getEmail())) {
            this.email = null;
        }
        if (Objects.equals(employeeNum, exWarehouseVO.getEmployeeNum())) {
            this.employeeNum = null;
        }
        if (Objects.equals(socialSecurityNum, exWarehouseVO.getSocialSecurityNum())) {
            this.socialSecurityNum = null;
        }
        if (Objects.equals(latestYearReportYear, exWarehouseVO.getLatestYearReportYear())) {
            this.latestYearReportYear = null;
        }
        if (Objects.equals(scaleTypeDictId, exWarehouseVO.getScaleTypeDictId())) {
            this.scaleTypeDictId = null;
        }
        if (Objects.equals(longitude, exWarehouseVO.getLongitude())) {
            this.longitude = null;
        }
        if (Objects.equals(latitude, exWarehouseVO.getLatitude())) {
            this.latitude = null;
        }
        if (Objects.equals(industryMainDictId, exWarehouseVO.getIndustryMainDictId())) {
            this.industryMainDictId = null;
        }
        if (Objects.equals(industryBigDictId, exWarehouseVO.getIndustryBigDictId())) {
            this.industryBigDictId = null;
        }
        if (Objects.equals(industryMiddleDictId, exWarehouseVO.getIndustryMiddleDictId())) {
            this.industryMiddleDictId = null;
        }
        if (Objects.equals(industrySmallDictId, exWarehouseVO.getIndustrySmallDictId())) {
            this.industrySmallDictId = null;
        }
        if (Objects.equals(provinceAreaId, exWarehouseVO.getProvinceAreaId())) {
            this.provinceAreaId = null;
        }
        if (Objects.equals(cityAreaId, exWarehouseVO.getCityAreaId())) {
            this.cityAreaId = null;
        }
        if (Objects.equals(countyAreaId, exWarehouseVO.getCountyAreaId())) {
            this.countyAreaId = null;
        }
        if (Objects.equals(isListed, exWarehouseVO.getIsListed())) {
            this.isListed = null;
        }
        if (Objects.equals(stockCode, exWarehouseVO.getStockCode())) {
            this.stockCode = null;
        }
        if (Objects.equals(listedTypeDictId, exWarehouseVO.getListedTypeDictId())) {
            this.listedTypeDictId = null;
        }
    }

    public static DataCompanyBasicWarehouseCommand createByDataCompanyBasicExWarehouseVO(DataCompanyBasicExWarehouseVO dataCompanyBasicExWarehouseVO) {
        DataCompanyBasicWarehouseCommand command = new DataCompanyBasicWarehouseCommand();
        command.companyId = dataCompanyBasicExWarehouseVO.getCompanyId();
        command.tin = dataCompanyBasicExWarehouseVO.getTin();
        command.statusDictId = dataCompanyBasicExWarehouseVO.getStatusDictId();
        command.natureDictId = dataCompanyBasicExWarehouseVO.getNatureDictId();
        command.legalPersonName = dataCompanyBasicExWarehouseVO.getLegalPersonName();
        command.isLegalPersonNaturalPerson = dataCompanyBasicExWarehouseVO.getIsLegalPersonNaturalPerson();
        command.legalPersonCompanyId = dataCompanyBasicExWarehouseVO.getLegalPersonCompanyId();
        command.legalPersonCompanyPersonId = dataCompanyBasicExWarehouseVO.getLegalPersonCompanyPersonId();
        command.typeDictId = dataCompanyBasicExWarehouseVO.getTypeDictId();
        command.regAddress = dataCompanyBasicExWarehouseVO.getRegAddress();
        command.regAddressPostalCode = dataCompanyBasicExWarehouseVO.getRegAddressPostalCode();
        command.businessAddress = dataCompanyBasicExWarehouseVO.getBusinessAddress();
        command.businessAddressPostalCode = dataCompanyBasicExWarehouseVO.getBusinessAddressPostalCode();
        command.establishDate = dataCompanyBasicExWarehouseVO.getEstablishDate();
        command.businessFromDate = dataCompanyBasicExWarehouseVO.getBusinessFromDate();
        command.businessToDate = dataCompanyBasicExWarehouseVO.getBusinessToDate();
        command.approveDate = dataCompanyBasicExWarehouseVO.getApproveDate();
        command.cancelDate = dataCompanyBasicExWarehouseVO.getCancelDate();
        command.cancelReason = dataCompanyBasicExWarehouseVO.getCancelReason();
        command.revokeDate = dataCompanyBasicExWarehouseVO.getRevokeDate();
        command.revokeReason = dataCompanyBasicExWarehouseVO.getRevokeReason();
        command.businessScope = dataCompanyBasicExWarehouseVO.getBusinessScope();
        command.regInstituteCompanyId = dataCompanyBasicExWarehouseVO.getRegInstituteCompanyId();
        command.regInstituteName = dataCompanyBasicExWarehouseVO.getRegInstituteName();
        command.regCapital = dataCompanyBasicExWarehouseVO.getRegCapital();
        command.subCapital = dataCompanyBasicExWarehouseVO.getSubCapital();
        command.subCapitalCurrencyDictId = dataCompanyBasicExWarehouseVO.getSubCapitalCurrencyDictId();
        command.subCapitalTypeDictId = dataCompanyBasicExWarehouseVO.getSubCapitalTypeDictId();
        command.subCapitalDate = dataCompanyBasicExWarehouseVO.getSubCapitalDate();
        command.actualCapital = dataCompanyBasicExWarehouseVO.getActualCapital();
        command.actualCapitalCurrencyDictId = dataCompanyBasicExWarehouseVO.getActualCapitalCurrencyDictId();
        command.capitalTypeDictId = dataCompanyBasicExWarehouseVO.getCapitalTypeDictId();
        command.actualCapitalDate = dataCompanyBasicExWarehouseVO.getActualCapitalDate();
        command.mobile = dataCompanyBasicExWarehouseVO.getMobile();
        command.telephone = dataCompanyBasicExWarehouseVO.getTelephone();
        command.email = dataCompanyBasicExWarehouseVO.getEmail();
        command.employeeNum = dataCompanyBasicExWarehouseVO.getEmployeeNum();
        command.socialSecurityNum = dataCompanyBasicExWarehouseVO.getSocialSecurityNum();
        command.latestYearReportYear = dataCompanyBasicExWarehouseVO.getLatestYearReportYear();
        command.scaleTypeDictId = dataCompanyBasicExWarehouseVO.getScaleTypeDictId();
        command.longitude = dataCompanyBasicExWarehouseVO.getLongitude();
        command.latitude = dataCompanyBasicExWarehouseVO.getLatitude();
        command.industryMainDictId = dataCompanyBasicExWarehouseVO.getIndustryMainDictId();
        command.industryBigDictId = dataCompanyBasicExWarehouseVO.getIndustryBigDictId();
        command.industryMiddleDictId = dataCompanyBasicExWarehouseVO.getIndustryMiddleDictId();
        command.industrySmallDictId = dataCompanyBasicExWarehouseVO.getIndustrySmallDictId();
        command.provinceAreaId = dataCompanyBasicExWarehouseVO.getProvinceAreaId();
        command.cityAreaId = dataCompanyBasicExWarehouseVO.getCityAreaId();
        command.countyAreaId = dataCompanyBasicExWarehouseVO.getCountyAreaId();
        command.isListed = dataCompanyBasicExWarehouseVO.getIsListed();
        command.stockCode = dataCompanyBasicExWarehouseVO.getStockCode();
        command.listedTypeDictId = dataCompanyBasicExWarehouseVO.getListedTypeDictId();

        return command;
    }

}
