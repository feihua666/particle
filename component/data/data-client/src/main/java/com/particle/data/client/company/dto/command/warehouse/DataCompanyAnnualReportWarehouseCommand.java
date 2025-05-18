package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportCreateCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Objects;
import cn.hutool.core.util.NumberUtil;

/**
 * <p>
 * 企业年报入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyAnnualReportWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业表ID 不能为空")
    @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @NotNull(message = "年报年度 不能为空")
    @Schema(description = "年报年度",requiredMode = Schema.RequiredMode.REQUIRED)
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


    @Schema(description = "经营者名称")
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


    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyId)
                && Objects.isNull(year)
                && StrUtil.isEmpty(companyName)
                && StrUtil.isEmpty(uscc)
                && StrUtil.isEmpty(regNo)
                && Objects.isNull(capital)
                && Objects.isNull(capitalCurrencyDictId)
                && Objects.isNull(operatorCompanyPersonId)
                && StrUtil.isEmpty(operatorName)
                && StrUtil.isEmpty(postalAddress)
                && StrUtil.isEmpty(postCode)
                && StrUtil.isEmpty(contactPhone)
                && StrUtil.isEmpty(email)
                && Objects.isNull(employeeNum)
                && Objects.isNull(femaleEmployeeNum)
                && Objects.isNull(statusDictId)
                && StrUtil.isEmpty(holdingControlInfo)
                && Objects.isNull(isHasInvestOrBugEquity)
                && Objects.isNull(isHasWebsite)
                && Objects.isNull(isHasForeignGuarantee)
                && Objects.isNull(isHasEquityTransfer)
                && StrUtil.isEmpty(normalBusinessScope)
                && StrUtil.isEmpty(approvedBusinessScope)
                && Objects.isNull(isIsHasForeignGuaranteePublic)
                && Objects.isNull(isFemaleEmployeeNumPublic)
                && Objects.isNull(isHoldingControlInfoPublic);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyAnnualReportExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyId, exWarehouseVO.getCompanyId())) {
            this.companyId = null;
        }
        if (Objects.equals(year, exWarehouseVO.getYear())) {
            this.year = null;
        }
        if (Objects.equals(companyName, exWarehouseVO.getCompanyName())) {
            this.companyName = null;
        }
        if (Objects.equals(uscc, exWarehouseVO.getUscc())) {
            this.uscc = null;
        }
        if (Objects.equals(regNo, exWarehouseVO.getRegNo())) {
            this.regNo = null;
        }
        if (NumberUtil.equals(capital, exWarehouseVO.getCapital())) {
            this.capital = null;
        }
        if (Objects.equals(capitalCurrencyDictId, exWarehouseVO.getCapitalCurrencyDictId())) {
            this.capitalCurrencyDictId = null;
        }
        if (Objects.equals(operatorCompanyPersonId, exWarehouseVO.getOperatorCompanyPersonId())) {
            this.operatorCompanyPersonId = null;
        }
        if (Objects.equals(operatorName, exWarehouseVO.getOperatorName())) {
            this.operatorName = null;
        }
        if (Objects.equals(postalAddress, exWarehouseVO.getPostalAddress())) {
            this.postalAddress = null;
        }
        if (Objects.equals(postCode, exWarehouseVO.getPostCode())) {
            this.postCode = null;
        }
        if (Objects.equals(contactPhone, exWarehouseVO.getContactPhone())) {
            this.contactPhone = null;
        }
        if (Objects.equals(email, exWarehouseVO.getEmail())) {
            this.email = null;
        }
        if (Objects.equals(employeeNum, exWarehouseVO.getEmployeeNum())) {
            this.employeeNum = null;
        }
        if (Objects.equals(femaleEmployeeNum, exWarehouseVO.getFemaleEmployeeNum())) {
            this.femaleEmployeeNum = null;
        }
        if (Objects.equals(statusDictId, exWarehouseVO.getStatusDictId())) {
            this.statusDictId = null;
        }
        if (Objects.equals(holdingControlInfo, exWarehouseVO.getHoldingControlInfo())) {
            this.holdingControlInfo = null;
        }
        if (Objects.equals(isHasInvestOrBugEquity, exWarehouseVO.getIsHasInvestOrBugEquity())) {
            this.isHasInvestOrBugEquity = null;
        }
        if (Objects.equals(isHasWebsite, exWarehouseVO.getIsHasWebsite())) {
            this.isHasWebsite = null;
        }
        if (Objects.equals(isHasForeignGuarantee, exWarehouseVO.getIsHasForeignGuarantee())) {
            this.isHasForeignGuarantee = null;
        }
        if (Objects.equals(isHasEquityTransfer, exWarehouseVO.getIsHasEquityTransfer())) {
            this.isHasEquityTransfer = null;
        }
        if (Objects.equals(normalBusinessScope, exWarehouseVO.getNormalBusinessScope())) {
            this.normalBusinessScope = null;
        }
        if (Objects.equals(approvedBusinessScope, exWarehouseVO.getApprovedBusinessScope())) {
            this.approvedBusinessScope = null;
        }
        if (Objects.equals(isIsHasForeignGuaranteePublic, exWarehouseVO.getIsIsHasForeignGuaranteePublic())) {
            this.isIsHasForeignGuaranteePublic = null;
        }
        if (Objects.equals(isFemaleEmployeeNumPublic, exWarehouseVO.getIsFemaleEmployeeNumPublic())) {
            this.isFemaleEmployeeNumPublic = null;
        }
        if (Objects.equals(isHoldingControlInfoPublic, exWarehouseVO.getIsHoldingControlInfoPublic())) {
            this.isHoldingControlInfoPublic = null;
        }

    }

    public static DataCompanyAnnualReportWarehouseCommand createByDataCompanyAnnualReportExWarehouseVO(DataCompanyAnnualReportExWarehouseVO exWarehouseVO) {
        DataCompanyAnnualReportWarehouseCommand command = new DataCompanyAnnualReportWarehouseCommand();
        command.companyId = exWarehouseVO.getCompanyId();
        command.year = exWarehouseVO.getYear();
        command.companyName = exWarehouseVO.getCompanyName();
        command.uscc = exWarehouseVO.getUscc();
        command.regNo = exWarehouseVO.getRegNo();
        command.capital = exWarehouseVO.getCapital();
        command.capitalCurrencyDictId = exWarehouseVO.getCapitalCurrencyDictId();
        command.operatorCompanyPersonId = exWarehouseVO.getOperatorCompanyPersonId();
        command.operatorName = exWarehouseVO.getOperatorName();
        command.postalAddress = exWarehouseVO.getPostalAddress();
        command.postCode = exWarehouseVO.getPostCode();
        command.contactPhone = exWarehouseVO.getContactPhone();
        command.email = exWarehouseVO.getEmail();
        command.employeeNum = exWarehouseVO.getEmployeeNum();
        command.femaleEmployeeNum = exWarehouseVO.getFemaleEmployeeNum();
        command.statusDictId = exWarehouseVO.getStatusDictId();
        command.holdingControlInfo = exWarehouseVO.getHoldingControlInfo();
        command.isHasInvestOrBugEquity = exWarehouseVO.getIsHasInvestOrBugEquity();
        command.isHasWebsite = exWarehouseVO.getIsHasWebsite();
        command.isHasForeignGuarantee = exWarehouseVO.getIsHasForeignGuarantee();
        command.isHasEquityTransfer = exWarehouseVO.getIsHasEquityTransfer();
        command.normalBusinessScope = exWarehouseVO.getNormalBusinessScope();
        command.approvedBusinessScope = exWarehouseVO.getApprovedBusinessScope();
        command.isIsHasForeignGuaranteePublic = exWarehouseVO.getIsIsHasForeignGuaranteePublic();
        command.isFemaleEmployeeNumPublic = exWarehouseVO.getIsFemaleEmployeeNumPublic();
        command.isHoldingControlInfoPublic = exWarehouseVO.getIsHoldingControlInfoPublic();

        return command;
    }

}
