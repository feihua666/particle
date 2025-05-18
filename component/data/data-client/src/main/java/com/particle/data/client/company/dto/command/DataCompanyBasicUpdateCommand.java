package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import com.particle.data.client.company.dto.command.warehouse.DataCompanyBasicWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * <p>
 * 企业基本信息 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyBasicUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "企业表ID 不能为空")
    @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
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


    public static DataCompanyBasicUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyBasicWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyBasicUpdateCommand command = new DataCompanyBasicUpdateCommand();
        command.setId(id);
        command.setVersion(version);
        command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.tin = dataCompanyBasicWarehouseCommand.getTin();
        command.statusDictId = dataCompanyBasicWarehouseCommand.getStatusDictId();
        command.natureDictId = dataCompanyBasicWarehouseCommand.getNatureDictId();
        command.legalPersonName = dataCompanyBasicWarehouseCommand.getLegalPersonName();
        command.isLegalPersonNaturalPerson = dataCompanyBasicWarehouseCommand.getIsLegalPersonNaturalPerson();
        command.legalPersonCompanyId = dataCompanyBasicWarehouseCommand.getLegalPersonCompanyId();
        command.legalPersonCompanyPersonId = dataCompanyBasicWarehouseCommand.getLegalPersonCompanyPersonId();
        command.typeDictId = dataCompanyBasicWarehouseCommand.getTypeDictId();
        command.regAddress = dataCompanyBasicWarehouseCommand.getRegAddress();
        command.regAddressPostalCode = dataCompanyBasicWarehouseCommand.getRegAddressPostalCode();
        command.businessAddress = dataCompanyBasicWarehouseCommand.getBusinessAddress();
        command.businessAddressPostalCode = dataCompanyBasicWarehouseCommand.getBusinessAddressPostalCode();
        command.establishDate = dataCompanyBasicWarehouseCommand.getEstablishDate();
        command.businessFromDate = dataCompanyBasicWarehouseCommand.getBusinessFromDate();
        command.businessToDate = dataCompanyBasicWarehouseCommand.getBusinessToDate();
        command.approveDate = dataCompanyBasicWarehouseCommand.getApproveDate();
        command.cancelDate = dataCompanyBasicWarehouseCommand.getCancelDate();
        command.cancelReason = dataCompanyBasicWarehouseCommand.getCancelReason();
        command.revokeDate = dataCompanyBasicWarehouseCommand.getRevokeDate();
        command.revokeReason = dataCompanyBasicWarehouseCommand.getRevokeReason();
        command.businessScope = dataCompanyBasicWarehouseCommand.getBusinessScope();
        command.regInstituteCompanyId = dataCompanyBasicWarehouseCommand.getRegInstituteCompanyId();
        command.regInstituteName = dataCompanyBasicWarehouseCommand.getRegInstituteName();
        command.regCapital = dataCompanyBasicWarehouseCommand.getRegCapital();
        command.subCapital = dataCompanyBasicWarehouseCommand.getSubCapital();
        command.subCapitalCurrencyDictId = dataCompanyBasicWarehouseCommand.getSubCapitalCurrencyDictId();
        command.subCapitalTypeDictId = dataCompanyBasicWarehouseCommand.getSubCapitalTypeDictId();
        command.subCapitalDate = dataCompanyBasicWarehouseCommand.getSubCapitalDate();
        command.actualCapital = dataCompanyBasicWarehouseCommand.getActualCapital();
        command.actualCapitalCurrencyDictId = dataCompanyBasicWarehouseCommand.getActualCapitalCurrencyDictId();
        command.capitalTypeDictId = dataCompanyBasicWarehouseCommand.getCapitalTypeDictId();
        command.actualCapitalDate = dataCompanyBasicWarehouseCommand.getActualCapitalDate();
        command.mobile = dataCompanyBasicWarehouseCommand.getMobile();
        command.telephone = dataCompanyBasicWarehouseCommand.getTelephone();
        command.email = dataCompanyBasicWarehouseCommand.getEmail();
        command.employeeNum = dataCompanyBasicWarehouseCommand.getEmployeeNum();
        command.socialSecurityNum = dataCompanyBasicWarehouseCommand.getSocialSecurityNum();
        command.latestYearReportYear = dataCompanyBasicWarehouseCommand.getLatestYearReportYear();
        command.scaleTypeDictId = dataCompanyBasicWarehouseCommand.getScaleTypeDictId();
        command.longitude = dataCompanyBasicWarehouseCommand.getLongitude();
        command.latitude = dataCompanyBasicWarehouseCommand.getLatitude();
        command.industryBigDictId = dataCompanyBasicWarehouseCommand.getIndustryBigDictId();
        command.industryMiddleDictId = dataCompanyBasicWarehouseCommand.getIndustryMiddleDictId();
        command.industrySmallDictId = dataCompanyBasicWarehouseCommand.getIndustrySmallDictId();
        command.provinceAreaId = dataCompanyBasicWarehouseCommand.getProvinceAreaId();
        command.cityAreaId = dataCompanyBasicWarehouseCommand.getCityAreaId();
        command.countyAreaId = dataCompanyBasicWarehouseCommand.getCountyAreaId();
        command.isListed = dataCompanyBasicWarehouseCommand.getIsListed();
        command.stockCode = dataCompanyBasicWarehouseCommand.getStockCode();
        command.listedTypeDictId = dataCompanyBasicWarehouseCommand.getListedTypeDictId();

        return command;
    }

}
