package com.particle.data.client.company.dto.data.exwarehouse;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 企业基本信息 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyBasicExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业ID")
    private Long companyId;

    @Schema(description = "企业标识信息")
    private DataCompanyExWarehouseVO companyUnique;

    @Schema(description = "纳税人识别号")
    private String tin;

    @Schema(description = "登记状态，字典id")
    private Long statusDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "statusDictId",mapValueField = "value")
    @Schema(description = "登记状态对应字典值")
    private String statusDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "statusDictId",mapValueField = "name")
    @Schema(description = "登记状态对应字典名称")
    private String statusDictName;

    @Schema(description = "性质，字典id")
    private Long natureDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "natureDictId",mapValueField = "value")
    @Schema(description = "性质对应字典值")
    private String natureDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "natureDictId",mapValueField = "name")
    @Schema(description = "性质对应字典名称")
    private String natureDictName;

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

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "value")
    @Schema(description = "企业类型对应字典值")
    private String typeDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @Schema(description = "企业类型对应字典名称")
    private String typeDictName;

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

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "regCapitalCurrencyDictId",mapValueField = "value")
    @Schema(description = "注册资金币种对应字典值")
    private String regCapitalCurrencyDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "regCapitalCurrencyDictId",mapValueField = "name")
    @Schema(description = "注册资金币种对应字典名称")
    private String regCapitalCurrencyDictName;

    @Schema(description = "认缴出资金额（万元）")
    private BigDecimal subCapital;

    @Schema(description = "认缴出资金额币种")
    private Long subCapitalCurrencyDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "subCapitalCurrencyDictId",mapValueField = "value")
    @Schema(description = "认缴出资金额币种对应字典值")
    private String subCapitalCurrencyDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "subCapitalCurrencyDictId",mapValueField = "name")
    @Schema(description = "认缴出资金额币种对应字典名称")
    private String subCapitalCurrencyDictName;

    @Schema(description = "认缴出资方式")
    private Long subCapitalTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "subCapitalTypeDictId",mapValueField = "value")
    @Schema(description = "认缴出资方式对应字典值")
    private String subCapitalTypeDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "subCapitalTypeDictId",mapValueField = "name")
    @Schema(description = "认缴出资方式对应字典名称")
    private String subCapitalTypeDictName;

    @Schema(description = "认缴出资日期")
    private LocalDate subCapitalDate;

    @Schema(description = "实缴出资金额（万元）")
    private BigDecimal actualCapital;

    @Schema(description = "实缴出资金额币种")
    private Long actualCapitalCurrencyDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "actualCapitalCurrencyDictId",mapValueField = "value")
    @Schema(description = "实缴出资金额币种对应字典值")
    private String actualCapitalCurrencyDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "actualCapitalCurrencyDictId",mapValueField = "name")
    @Schema(description = "实缴出资金额币种对应字典名称")
    private String actualCapitalCurrencyDictName;

    @Schema(description = "实缴出资方式")
    private Long capitalTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "capitalTypeDictId",mapValueField = "value")
    @Schema(description = "实缴出资方式对应字典值")
    private String capitalTypeDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "capitalTypeDictId",mapValueField = "name")
    @Schema(description = "实缴出资方式对应字典名称")
    private String capitalTypeDictName;

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

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "scaleTypeDictId",mapValueField = "value")
    @Schema(description = "规模类型对应字典值")
    private String scaleTypeDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "scaleTypeDictId",mapValueField = "name")
    @Schema(description = "规模类型对应字典名称")
    private String scaleTypeDictName;

    @Schema(description = "经度")
    private String longitude;

    @Schema(description = "纬度")
    private String latitude;

    @Schema(description = "所属行业门类，字典id")
    private Long industryMainDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "industryMainDictId",mapValueField = "value")
    @Schema(description = "所属行业门类对应字典值")
    private String industryMainDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "industryMainDictId",mapValueField = "name")
    @Schema(description = "所属行业门类对应字典名称")
    private String industryMainDictName;

    @Schema(description = "所属行业大类，字典id")
    private Long industryBigDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "industryBigDictId",mapValueField = "value")
    @Schema(description = "所属行业大类对应字典值")
    private String industryBigDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "industryBigDictId",mapValueField = "name")
    @Schema(description = "所属行业大类对应字典名称")
    private String industryBigDictName;

    @Schema(description = "所属行业中类，字典id")
    private Long industryMiddleDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "industryMiddleDictId",mapValueField = "value")
    @Schema(description = "所属行业中类对应字典值")
    private String industryMiddleDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "industryMiddleDictId",mapValueField = "name")
    @Schema(description = "所属行业中类对应字典名称")
    private String industryMiddleDictName;

    @Schema(description = "所属行业小类，字典id")
    private Long industrySmallDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "industrySmallDictId",mapValueField = "value")
    @Schema(description = "所属行业小类对应字典值")
    private String industrySmallDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "industrySmallDictId",mapValueField = "name")
    @Schema(description = "所属行业小类对应字典名称")
    private String industrySmallDictName;

    @Schema(description = "所在省份，区域id")
    private Long provinceAreaId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "provinceAreaId",mapValueField = "code")
    @Schema(description = "所在省份，区域编码")
    private String provinceAreaCode;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "provinceAreaId",mapValueField = "name")
    @Schema(description = "所在省份，区域名称")
    private String provinceAreaName;

    @Schema(description = "所在城市，区域id")
    private Long cityAreaId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "cityAreaId",mapValueField = "code")
    @Schema(description = "所在城市，区域编码")
    private String cityAreaCode;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "cityAreaId",mapValueField = "name")
    @Schema(description = "所在城市，区域名称")
    private String cityAreaName;

    @Schema(description = "所在区县，区域id")
    private Long countyAreaId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "countyAreaId",mapValueField = "code")
    @Schema(description = "所在区县，区域编码")
    private String countyAreaCode;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "countyAreaId",mapValueField = "name")
    @Schema(description = "所在区县，区域名称")
    private String countyAreaName;

    @Schema(description = "是否上市")
    private Boolean isListed;

    @Schema(description = "股票代码")
    private String stockCode;

    @Schema(description = "上市类型")
    private Long listedTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "listedTypeDictId",mapValueField = "value")
    @Schema(description = "上市类型对应字典值")
    private String listedTypeDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "listedTypeDictId",mapValueField = "name")
    @Schema(description = "上市类型对应字典名称")
    private String listedTypeDictName;

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;

}
