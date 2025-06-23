package com.particle.data.client.company.dto.data.exwarehouse;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业知识产权地理标识 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyIprGeograExWarehouseVO extends AbstractBaseIdVO {

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

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;

    @Schema(description = "企业知识产权地理标识核准公告")
    private List<DataCompanyIprGeograApproveAnnouncementExWarehouseVO> approveAnnouncements;

}
