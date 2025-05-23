package com.particle.data.client.company.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
import java.time.LocalDateTime;
import java.math.BigDecimal;
/**
 * <p>
 * 企业年报 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:58
 */
@Data
@Schema
public class DataCompanyAnnualReportVO extends AbstractBaseIdVO {

    @Schema(description = "企业表ID")
    private Long companyId;

    @Schema(description = "年报年度")
    private Integer year;

    @Schema(description = "企业名称")
    private String companyName;

    @Schema(description = "统一社会信用代码")
    private String uscc;

    @Schema(description = "注册号")
    private String regNo;

    @Schema(description = "资金数额（万元）")
    private BigDecimal capital;

    @Schema(description = "资金数额币种")
    private Long capitalCurrencyDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "capitalCurrencyDictId",mapValueField = "name")
    @Schema(description = "资金数额币种对应字典名称")
    private String capitalCurrencyDictName;

    @Schema(description = "经营者id")
    private Long operatorCompanyPersonId;

    @Schema(description = "经营者名称")
    private String operatorName;

    @Schema(description = "企业通信地址")
    private String postalAddress;

    @Schema(description = "邮政编码")
    private String postCode;

    @Schema(description = "企业联系电话")
    private String contactPhone;

    @Schema(description = "企业电子邮箱")
    private String email;

    @Schema(description = "从业人数")
    private Integer employeeNum;

    @Schema(description = "其中女性从业人数")
    private Integer femaleEmployeeNum;

    @Schema(description = "企业经营状态")
    private Long statusDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "statusDictId",mapValueField = "name")
    @Schema(description = "企业经营状态对应字典名称")
    private String statusDictName;

    @Schema(description = "企业控股情况")
    private String holdingControlInfo;

    @Schema(description = "是否有投资信息或购买其他公司股权")
    private Boolean isHasInvestOrBugEquity;

    @Schema(description = "是否有网站或网店")
    private Boolean isHasWebsite;

    @Schema(description = "是否有对外提供担保信息")
    private Boolean isHasForeignGuarantee;

    @Schema(description = "有限责任公司本年度是否发生股东股权转让")
    private Boolean isHasEquityTransfer;

    @Schema(description = "经营范围（一般项目）")
    private String normalBusinessScope;

    @Schema(description = "经营范围（许可项目）")
    private String approvedBusinessScope;

    @Schema(description = "是否对外提供担保信息公示")
    private Boolean isIsHasForeignGuaranteePublic;

    @Schema(description = "是否其中女性从业人数公示")
    private Boolean isFemaleEmployeeNumPublic;

    @Schema(description = "是否企业控股情况公示")
    private Boolean isHoldingControlInfoPublic;

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;



}
