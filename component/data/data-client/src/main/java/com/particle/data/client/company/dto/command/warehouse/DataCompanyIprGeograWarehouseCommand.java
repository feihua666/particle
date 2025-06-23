package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprGeograExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业知识产权地理标识入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprGeograWarehouseCommand extends AbstractBaseCommand {



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



    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return StrUtil.isEmpty(publicNo)
                && Objects.isNull(publicDate)
                && StrUtil.isEmpty(name)
                && StrUtil.isEmpty(nationalEconomicClassification)
                && StrUtil.isEmpty(publicTypeName)
                && StrUtil.isEmpty(publicDeptName)
                && StrUtil.isEmpty(areaAddress)
                && StrUtil.isEmpty(protectScope)
                && StrUtil.isEmpty(protectFile)
                && StrUtil.isEmpty(qualityTechnicalRequirement)
                && StrUtil.isEmpty(specialSign)
                && StrUtil.isEmpty(applicantName)
                && Objects.isNull(isApplicantNameNaturalPerson)
                && Objects.isNull(applicantNameCompanyId)
                && Objects.isNull(applicantNameCompanyPersonId)
                && StrUtil.isEmpty(applicantAddress)
                && StrUtil.isEmpty(primaryReviewInstitute)
                && Objects.isNull(primaryReviewDate)
                && StrUtil.isEmpty(agencyName)
                && StrUtil.isEmpty(useProduct)
                && StrUtil.isEmpty(productGroup)
                && StrUtil.isEmpty(filePath)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprGeograExWarehouseVO exWarehouseVO) {
                if (Objects.equals(publicNo, exWarehouseVO.getPublicNo())) {
            this.publicNo = null;
        }
        if (Objects.equals(publicDate, exWarehouseVO.getPublicDate())) {
            this.publicDate = null;
        }
        if (Objects.equals(name, exWarehouseVO.getName())) {
            this.name = null;
        }
        if (Objects.equals(nationalEconomicClassification, exWarehouseVO.getNationalEconomicClassification())) {
            this.nationalEconomicClassification = null;
        }
        if (Objects.equals(publicTypeName, exWarehouseVO.getPublicTypeName())) {
            this.publicTypeName = null;
        }
        if (Objects.equals(publicDeptName, exWarehouseVO.getPublicDeptName())) {
            this.publicDeptName = null;
        }
        if (Objects.equals(areaAddress, exWarehouseVO.getAreaAddress())) {
            this.areaAddress = null;
        }
        if (Objects.equals(protectScope, exWarehouseVO.getProtectScope())) {
            this.protectScope = null;
        }
        if (Objects.equals(protectFile, exWarehouseVO.getProtectFile())) {
            this.protectFile = null;
        }
        if (Objects.equals(qualityTechnicalRequirement, exWarehouseVO.getQualityTechnicalRequirement())) {
            this.qualityTechnicalRequirement = null;
        }
        if (Objects.equals(specialSign, exWarehouseVO.getSpecialSign())) {
            this.specialSign = null;
        }
        if (Objects.equals(applicantName, exWarehouseVO.getApplicantName())) {
            this.applicantName = null;
        }
        if (Objects.equals(isApplicantNameNaturalPerson, exWarehouseVO.getIsApplicantNameNaturalPerson())) {
            this.isApplicantNameNaturalPerson = null;
        }
        if (Objects.equals(applicantNameCompanyId, exWarehouseVO.getApplicantNameCompanyId())) {
            this.applicantNameCompanyId = null;
        }
        if (Objects.equals(applicantNameCompanyPersonId, exWarehouseVO.getApplicantNameCompanyPersonId())) {
            this.applicantNameCompanyPersonId = null;
        }
        if (Objects.equals(applicantAddress, exWarehouseVO.getApplicantAddress())) {
            this.applicantAddress = null;
        }
        if (Objects.equals(primaryReviewInstitute, exWarehouseVO.getPrimaryReviewInstitute())) {
            this.primaryReviewInstitute = null;
        }
        if (Objects.equals(primaryReviewDate, exWarehouseVO.getPrimaryReviewDate())) {
            this.primaryReviewDate = null;
        }
        if (Objects.equals(agencyName, exWarehouseVO.getAgencyName())) {
            this.agencyName = null;
        }
        if (Objects.equals(useProduct, exWarehouseVO.getUseProduct())) {
            this.useProduct = null;
        }
        if (Objects.equals(productGroup, exWarehouseVO.getProductGroup())) {
            this.productGroup = null;
        }
        if (Objects.equals(filePath, exWarehouseVO.getFilePath())) {
            this.filePath = null;
        }

    }

    public static DataCompanyIprGeograWarehouseCommand createByDataCompanyIprGeograExWarehouseVO(DataCompanyIprGeograExWarehouseVO exWarehouseVO) {
        DataCompanyIprGeograWarehouseCommand command = new DataCompanyIprGeograWarehouseCommand();
        command.publicNo = exWarehouseVO.getPublicNo();
        command.publicDate = exWarehouseVO.getPublicDate();
        command.name = exWarehouseVO.getName();
        command.nationalEconomicClassification = exWarehouseVO.getNationalEconomicClassification();
        command.publicTypeName = exWarehouseVO.getPublicTypeName();
        command.publicDeptName = exWarehouseVO.getPublicDeptName();
        command.areaAddress = exWarehouseVO.getAreaAddress();
        command.protectScope = exWarehouseVO.getProtectScope();
        command.protectFile = exWarehouseVO.getProtectFile();
        command.qualityTechnicalRequirement = exWarehouseVO.getQualityTechnicalRequirement();
        command.specialSign = exWarehouseVO.getSpecialSign();
        command.applicantName = exWarehouseVO.getApplicantName();
        command.isApplicantNameNaturalPerson = exWarehouseVO.getIsApplicantNameNaturalPerson();
        command.applicantNameCompanyId = exWarehouseVO.getApplicantNameCompanyId();
        command.applicantNameCompanyPersonId = exWarehouseVO.getApplicantNameCompanyPersonId();
        command.applicantAddress = exWarehouseVO.getApplicantAddress();
        command.primaryReviewInstitute = exWarehouseVO.getPrimaryReviewInstitute();
        command.primaryReviewDate = exWarehouseVO.getPrimaryReviewDate();
        command.agencyName = exWarehouseVO.getAgencyName();
        command.useProduct = exWarehouseVO.getUseProduct();
        command.productGroup = exWarehouseVO.getProductGroup();
        command.filePath = exWarehouseVO.getFilePath();


        return command;
    }
}
