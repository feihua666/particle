package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprGeograWarehouseCommand;

/**
 * <p>
 * 企业知识产权地理标识 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:33
 */
@Data
@Schema
public class DataCompanyIprGeograCreateCommand extends AbstractBaseCommand {



    @Schema(description = "公告号")
    private String publicNo;


    @Schema(description = "公告日期")
    private LocalDate publicDate;


    @Schema(description = "产品名称")
    private String name;


    @Schema(description = "国民经济行业分类")
    private String nationalEconomicClassification;


    @Schema(description = "公告类型")
    private String publicTypeName;


    @Schema(description = "公告单位")
    private String publicDeptName;


    @Schema(description = "所在地域 ")
    private String areaAddress;


    @Schema(description = "保护范围")
    private String protectScope;


    @Schema(description = "保护范围界定文件")
    private String protectFile;


    @Schema(description = "质量技术要求")
    private String qualityTechnicalRequirement;


    @Schema(description = "专用标志")
    private String specialSign;


    @Schema(description = "申请人名称")
    private String applicantName;


    @Schema(description = "是否申请人为自然人")
    private Boolean isApplicantNameNaturalPerson;


    @Schema(description = "申请人公司id")
    private Long applicantNameCompanyId;


    @Schema(description = "申请人个人id")
    private Long applicantNameCompanyPersonId;


    @Schema(description = "申请人地址")
    private String applicantAddress;


    @Schema(description = "初审机构")
    private String primaryReviewInstitute;


    @Schema(description = "初审日期")
    private LocalDate primaryReviewDate;


    @Schema(description = "代理机构")
    private String agencyName;


    @Schema(description = "使用商品")
    private String useProduct;


    @Schema(description = "商品组别")
    private String productGroup;


    @Schema(description = "文件存放路径")
    private String filePath;


    public static DataCompanyIprGeograCreateCommand createByWarehouseCommand(DataCompanyIprGeograWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprGeograCreateCommand command = new DataCompanyIprGeograCreateCommand();
        command.publicNo = dataCompanyBasicWarehouseCommand.getPublicNo();
        command.publicDate = dataCompanyBasicWarehouseCommand.getPublicDate();
        command.name = dataCompanyBasicWarehouseCommand.getName();
        command.nationalEconomicClassification = dataCompanyBasicWarehouseCommand.getNationalEconomicClassification();
        command.publicTypeName = dataCompanyBasicWarehouseCommand.getPublicTypeName();
        command.publicDeptName = dataCompanyBasicWarehouseCommand.getPublicDeptName();
        command.areaAddress = dataCompanyBasicWarehouseCommand.getAreaAddress();
        command.protectScope = dataCompanyBasicWarehouseCommand.getProtectScope();
        command.protectFile = dataCompanyBasicWarehouseCommand.getProtectFile();
        command.qualityTechnicalRequirement = dataCompanyBasicWarehouseCommand.getQualityTechnicalRequirement();
        command.specialSign = dataCompanyBasicWarehouseCommand.getSpecialSign();
        command.applicantName = dataCompanyBasicWarehouseCommand.getApplicantName();
        command.isApplicantNameNaturalPerson = dataCompanyBasicWarehouseCommand.getIsApplicantNameNaturalPerson();
        command.applicantNameCompanyId = dataCompanyBasicWarehouseCommand.getApplicantNameCompanyId();
        command.applicantNameCompanyPersonId = dataCompanyBasicWarehouseCommand.getApplicantNameCompanyPersonId();
        command.applicantAddress = dataCompanyBasicWarehouseCommand.getApplicantAddress();
        command.primaryReviewInstitute = dataCompanyBasicWarehouseCommand.getPrimaryReviewInstitute();
        command.primaryReviewDate = dataCompanyBasicWarehouseCommand.getPrimaryReviewDate();
        command.agencyName = dataCompanyBasicWarehouseCommand.getAgencyName();
        command.useProduct = dataCompanyBasicWarehouseCommand.getUseProduct();
        command.productGroup = dataCompanyBasicWarehouseCommand.getProductGroup();
        command.filePath = dataCompanyBasicWarehouseCommand.getFilePath();


        return command;
    }
}
