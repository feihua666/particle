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
 * 企业知识产权专利 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyIprPatentExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "原始标题")
    private String title;

    @Schema(description = "中文标题")
    private String titleCn;

    @Schema(description = "英文标题")
    private String titleEn;

    @Schema(description = "原始申请号")
    private String applyNo;

    @Schema(description = "标准申请号")
    private String standardApplyNo;

    @Schema(description = "申请日期")
    private LocalDate applyDate;

    @Schema(description = "原始公布号")
    private String publicNo;

    @Schema(description = "标准公布号")
    private String standardPublicNo;

    @Schema(description = "公布日")
    private LocalDate publicDate;

    @Schema(description = "授权公告号")
    private String authorizePublicNo;

    @Schema(description = "授权公告日")
    private LocalDate authorizePublicDate;

    @Schema(description = "优先权号")
    private String priorityNo;

    @Schema(description = "优先权日")
    private LocalDate priorityDate;

    @Schema(description = "失效日期")
    private LocalDate invalidDate;

    @Schema(description = "国际申请号")
    private String internationalApplyNo;

    @Schema(description = "国际申请日期")
    private LocalDate internationalApplyDate;

    @Schema(description = "国际公布号")
    private String internationalPublicNo;

    @Schema(description = "国际公布日期")
    private LocalDate internationalPublicDate;

    @Schema(description = "进入国家日期")
    private LocalDate entryCountryDate;

    @Schema(description = "分案申请号")
    private String splitApplyNo;

    @Schema(description = "分案申请日期")
    private LocalDate splitApplyDate;

    @Schema(description = "原始奖励等级")
    private String rewardLevel;

    @Schema(description = "原始奖励名称")
    private String rewardName;

    @Schema(description = "原始奖励界次")
    private String rewardSession;

    @Schema(description = "IPC分类号")
    private String ipcCategoryNos;

    @Schema(description = "主IPC分类号")
    private String ipcMainCategoryNo;

    @Schema(description = "CPC分类号")
    private String cpcCategoryNos;

    @Schema(description = "主CPC分类号")
    private String cpcMainCategoryNo;

    @Schema(description = "UC分类号，美国专利商标局（USPTO）的专利分类号，多个以英文逗号分隔")
    private String ucNo;

    @Schema(description = "FI分类号，芬兰的专利分类号，多个以英文逗号分隔")
    private String fiNo;

    @Schema(description = "fterm分类号，File Term Search，专利检索的工具或方法分类号，多个以英文逗号分隔")
    private String ftermNo;

    @Schema(description = "专利类型")
    private Long patentTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "patentTypeDictId",mapValueField = "value")
    @Schema(description = "专利类型对应字典值")
    private String patentTypeDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "patentTypeDictId",mapValueField = "name")
    @Schema(description = "专利类型对应字典名称")
    private String patentTypeDictName;

    @Schema(description = "受理局名称")
    private String receivingOfficeName;

    @Schema(description = "受理国名称")
    private String receivingCountryName;

    @Schema(description = "来源国名称")
    private String originCountryName;

    @Schema(description = "文献类型，如：B")
    private String literatureType;

    @Schema(description = "外观设计方案号")
    private String appearanceSchemeNo;

    @Schema(description = "外观设计方案号补充")
    private String appearanceSchemeNoSupplement;

    @Schema(description = "国民经济行业分类")
    private String nationalEconomicClassification;

    @Schema(description = "是否为pct专利，1-是，0-否")
    private Boolean isPct;

    @Schema(description = "专利图片地址")
    private String patentImageUrl;

    @Schema(description = "是否当前有效")
    private Boolean isCurrentValid;

    @Schema(description = "当前权利状态，字典id")
    private Long currentRightStatusDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "currentRightStatusDictId",mapValueField = "value")
    @Schema(description = "当前权利状态值")
    private String currentRightStatusDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "currentRightStatusDictId",mapValueField = "name")
    @Schema(description = "当前权利状态名称")
    private String currentRightStatusDictName;

    @Schema(description = "说明书页数")
    private Integer instructionManualPageSize;

    @Schema(description = "洛迦诺分类号")
    private String locarnoCategoryNos;

    @Schema(description = "专利强度")
    private BigDecimal patentStrength;

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;


}
