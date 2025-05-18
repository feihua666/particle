package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>
 * 企业年报 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:58
 */
@Data
@Schema
public class DataCompanyAnnualReportCreateCommand extends AbstractBaseCommand {



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


    public static DataCompanyAnnualReportCreateCommand createByWarehouseCommand(DataCompanyAnnualReportWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyAnnualReportCreateCommand command = new DataCompanyAnnualReportCreateCommand();
        command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.year = dataCompanyBasicWarehouseCommand.getYear();
        command.companyName = dataCompanyBasicWarehouseCommand.getCompanyName();
        command.uscc = dataCompanyBasicWarehouseCommand.getUscc();
        command.regNo = dataCompanyBasicWarehouseCommand.getRegNo();
        command.capital = dataCompanyBasicWarehouseCommand.getCapital();
        command.capitalCurrencyDictId = dataCompanyBasicWarehouseCommand.getCapitalCurrencyDictId();
        command.operatorCompanyPersonId = dataCompanyBasicWarehouseCommand.getOperatorCompanyPersonId();
        command.operatorName = dataCompanyBasicWarehouseCommand.getOperatorName();
        command.postalAddress = dataCompanyBasicWarehouseCommand.getPostalAddress();
        command.postCode = dataCompanyBasicWarehouseCommand.getPostCode();
        command.contactPhone = dataCompanyBasicWarehouseCommand.getContactPhone();
        command.email = dataCompanyBasicWarehouseCommand.getEmail();
        command.employeeNum = dataCompanyBasicWarehouseCommand.getEmployeeNum();
        command.femaleEmployeeNum = dataCompanyBasicWarehouseCommand.getFemaleEmployeeNum();
        command.statusDictId = dataCompanyBasicWarehouseCommand.getStatusDictId();
        command.holdingControlInfo = dataCompanyBasicWarehouseCommand.getHoldingControlInfo();
        command.isHasInvestOrBugEquity = dataCompanyBasicWarehouseCommand.getIsHasInvestOrBugEquity();
        command.isHasWebsite = dataCompanyBasicWarehouseCommand.getIsHasWebsite();
        command.isHasForeignGuarantee = dataCompanyBasicWarehouseCommand.getIsHasForeignGuarantee();
        command.isHasEquityTransfer = dataCompanyBasicWarehouseCommand.getIsHasEquityTransfer();
        command.normalBusinessScope = dataCompanyBasicWarehouseCommand.getNormalBusinessScope();
        command.approvedBusinessScope = dataCompanyBasicWarehouseCommand.getApprovedBusinessScope();
        command.isIsHasForeignGuaranteePublic = dataCompanyBasicWarehouseCommand.getIsIsHasForeignGuaranteePublic();
        command.isFemaleEmployeeNumPublic = dataCompanyBasicWarehouseCommand.getIsFemaleEmployeeNumPublic();
        command.isHoldingControlInfoPublic = dataCompanyBasicWarehouseCommand.getIsHoldingControlInfoPublic();


        return command;
    }
}
