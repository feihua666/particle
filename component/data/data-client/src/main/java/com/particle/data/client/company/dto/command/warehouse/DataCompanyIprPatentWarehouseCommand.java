package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业知识产权专利入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprPatentWarehouseCommand extends AbstractBaseCommand {


    @Schema(description = "原始标题")
    private String title;

    @Schema(description = "中文标题")
    private String titleCn;

    @Schema(description = "英文标题")
    private String titleEn;


    @NotEmpty(message = "原始申请号 不能为空")
    @Schema(description = "原始申请号",requiredMode = Schema.RequiredMode.REQUIRED)
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


    @NotNull(message = "专利类型 不能为空")
    @Schema(description = "专利类型",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long patentTypeDictId;


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

    @Schema(description = "说明书页数")
    private Integer instructionManualPageSize;


    @Schema(description = "洛迦诺分类号")
    private String locarnoCategoryNos;


    @Schema(description = "专利强度")
    private BigDecimal patentStrength;


    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return StrUtil.isEmpty(title)
                && StrUtil.isEmpty(titleCn)
                && StrUtil.isEmpty(titleEn)
                && StrUtil.isEmpty(applyNo)
                && StrUtil.isEmpty(standardApplyNo)
                && Objects.isNull(applyDate)
                && StrUtil.isEmpty(publicNo)
                && StrUtil.isEmpty(standardPublicNo)
                && Objects.isNull(publicDate)
                && StrUtil.isEmpty(authorizePublicNo)
                && Objects.isNull(authorizePublicDate)
                && StrUtil.isEmpty(priorityNo)
                && Objects.isNull(priorityDate)
                && Objects.isNull(invalidDate)
                && StrUtil.isEmpty(internationalApplyNo)
                && Objects.isNull(internationalApplyDate)
                && StrUtil.isEmpty(internationalPublicNo)
                && Objects.isNull(internationalPublicDate)
                && Objects.isNull(entryCountryDate)
                && StrUtil.isEmpty(splitApplyNo)
                && Objects.isNull(splitApplyDate)
                && StrUtil.isEmpty(rewardLevel)
                && StrUtil.isEmpty(rewardName)
                && StrUtil.isEmpty(rewardSession)
                && StrUtil.isEmpty(ipcCategoryNos)
                && StrUtil.isEmpty(ipcMainCategoryNo)
                && StrUtil.isEmpty(cpcCategoryNos)
                && StrUtil.isEmpty(cpcMainCategoryNo)
                && StrUtil.isEmpty(ucNo)
                && StrUtil.isEmpty(fiNo)
                && StrUtil.isEmpty(ftermNo)
                && Objects.isNull(patentTypeDictId)
                && StrUtil.isEmpty(receivingOfficeName)
                && StrUtil.isEmpty(receivingCountryName)
                && StrUtil.isEmpty(originCountryName)
                && StrUtil.isEmpty(literatureType)
                && StrUtil.isEmpty(appearanceSchemeNo)
                && StrUtil.isEmpty(appearanceSchemeNoSupplement)
                && StrUtil.isEmpty(nationalEconomicClassification)
                && Objects.isNull(isPct)
                && StrUtil.isEmpty(patentImageUrl)
                && Objects.isNull(isCurrentValid)
                && Objects.isNull(currentRightStatusDictId)
                && Objects.isNull(instructionManualPageSize)
                && StrUtil.isEmpty(locarnoCategoryNos)
                && Objects.isNull(patentStrength);
    }
    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprPatentExWarehouseVO exWarehouseVO) {
        if (Objects.equals(title, exWarehouseVO.getTitle())) {
            this.title = null;
        }
        if (Objects.equals(titleCn, exWarehouseVO.getTitleCn())) {
            this.titleCn = null;
        }
        if (Objects.equals(titleEn, exWarehouseVO.getTitleEn())) {
            this.titleEn = null;
        }
        if (Objects.equals(applyNo, exWarehouseVO.getApplyNo())) {
            this.applyNo = null;
        }
        if (Objects.equals(standardApplyNo, exWarehouseVO.getStandardApplyNo())) {
            this.standardApplyNo = null;
        }
        if (Objects.equals(applyDate, exWarehouseVO.getApplyDate())) {
            this.applyDate = null;
        }
        if (Objects.equals(publicNo, exWarehouseVO.getPublicNo())) {
            this.publicNo = null;
        }
        if (Objects.equals(standardPublicNo, exWarehouseVO.getStandardPublicNo())) {
            this.standardPublicNo = null;
        }
        if (Objects.equals(publicDate, exWarehouseVO.getPublicDate())) {
            this.publicDate = null;
        }
        if (Objects.equals(authorizePublicNo, exWarehouseVO.getAuthorizePublicNo())) {
            this.authorizePublicNo = null;
        }
        if (Objects.equals(authorizePublicDate, exWarehouseVO.getAuthorizePublicDate())) {
            this.authorizePublicDate = null;
        }
        if (Objects.equals(priorityNo, exWarehouseVO.getPriorityNo())) {
            this.priorityNo = null;
        }
        if (Objects.equals(priorityDate, exWarehouseVO.getPriorityDate())) {
            this.priorityDate = null;
        }
        if (Objects.equals(invalidDate, exWarehouseVO.getInvalidDate())) {
            this.invalidDate = null;
        }
        if (Objects.equals(internationalApplyNo, exWarehouseVO.getInternationalApplyNo())) {
            this.internationalApplyNo = null;
        }
        if (Objects.equals(internationalApplyDate, exWarehouseVO.getInternationalApplyDate())) {
            this.internationalApplyDate = null;
        }
        if (Objects.equals(internationalPublicNo, exWarehouseVO.getInternationalPublicNo())) {
            this.internationalPublicNo = null;
        }
        if (Objects.equals(internationalPublicDate, exWarehouseVO.getInternationalPublicDate())) {
            this.internationalPublicDate = null;
        }
        if (Objects.equals(entryCountryDate, exWarehouseVO.getEntryCountryDate())) {
            this.entryCountryDate = null;
        }
        if (Objects.equals(splitApplyNo, exWarehouseVO.getSplitApplyNo())) {
            this.splitApplyNo = null;
        }
        if (Objects.equals(splitApplyDate, exWarehouseVO.getSplitApplyDate())) {
            this.splitApplyDate = null;
        }
        if (Objects.equals(rewardLevel, exWarehouseVO.getRewardLevel())) {
            this.rewardLevel = null;
        }
        if (Objects.equals(rewardName, exWarehouseVO.getRewardName())) {
            this.rewardName = null;
        }
        if (Objects.equals(rewardSession, exWarehouseVO.getRewardSession())) {
            this.rewardSession = null;
        }
        if (Objects.equals(ipcCategoryNos, exWarehouseVO.getIpcCategoryNos())) {
            this.ipcCategoryNos = null;
        }
        if (Objects.equals(ipcMainCategoryNo, exWarehouseVO.getIpcMainCategoryNo())) {
            this.ipcMainCategoryNo = null;
        }
        if (Objects.equals(cpcCategoryNos, exWarehouseVO.getCpcCategoryNos())) {
            this.cpcCategoryNos = null;
        }
        if (Objects.equals(cpcMainCategoryNo, exWarehouseVO.getCpcMainCategoryNo())) {
            this.cpcMainCategoryNo = null;
        }
        if (Objects.equals(ucNo, exWarehouseVO.getUcNo())) {
            this.ucNo = null;
        }
        if (Objects.equals(fiNo, exWarehouseVO.getFiNo())) {
            this.fiNo = null;
        }
        if (Objects.equals(ftermNo, exWarehouseVO.getFtermNo())) {
            this.ftermNo = null;
        }
        if (Objects.equals(patentTypeDictId, exWarehouseVO.getPatentTypeDictId())) {
            this.patentTypeDictId = null;
        }
        if (Objects.equals(receivingOfficeName, exWarehouseVO.getReceivingOfficeName())) {
            this.receivingOfficeName = null;
        }
        if (Objects.equals(receivingCountryName, exWarehouseVO.getReceivingCountryName())) {
            this.receivingCountryName = null;
        }
        if (Objects.equals(originCountryName, exWarehouseVO.getOriginCountryName())) {
            this.originCountryName = null;
        }
        if (Objects.equals(literatureType, exWarehouseVO.getLiteratureType())) {
            this.literatureType = null;
        }
        if (Objects.equals(appearanceSchemeNo, exWarehouseVO.getAppearanceSchemeNo())) {
            this.appearanceSchemeNo = null;
        }
        if (Objects.equals(appearanceSchemeNoSupplement, exWarehouseVO.getAppearanceSchemeNoSupplement())) {
            this.appearanceSchemeNoSupplement = null;
        }
        if (Objects.equals(nationalEconomicClassification, exWarehouseVO.getNationalEconomicClassification())) {
            this.nationalEconomicClassification = null;
        }
        if (Objects.equals(isPct, exWarehouseVO.getIsPct())) {
            this.isPct = null;
        }
        if (Objects.equals(patentImageUrl, exWarehouseVO.getPatentImageUrl())) {
            this.patentImageUrl = null;
        }
        if (Objects.equals(isCurrentValid, exWarehouseVO.getIsCurrentValid())) {
            this.isCurrentValid = null;
        }
        if (Objects.equals(currentRightStatusDictId, exWarehouseVO.getCurrentRightStatusDictId())) {
            this.currentRightStatusDictId = null;
        }
        if (Objects.equals(instructionManualPageSize, exWarehouseVO.getInstructionManualPageSize())) {
            this.instructionManualPageSize = null;
        }
        if (Objects.equals(locarnoCategoryNos, exWarehouseVO.getLocarnoCategoryNos())) {
            this.locarnoCategoryNos = null;
        }
        if (NumberUtil.equals(patentStrength, exWarehouseVO.getPatentStrength())) {
            this.patentStrength = null;
        }
    }

    public static DataCompanyIprPatentWarehouseCommand createByDataCompanyIprPatentExWarehouseVO(DataCompanyIprPatentExWarehouseVO dataCompanyBasicWarehouseCommand){
        DataCompanyIprPatentWarehouseCommand command = new DataCompanyIprPatentWarehouseCommand();
        command.title = dataCompanyBasicWarehouseCommand.getTitle();
        command.titleCn = dataCompanyBasicWarehouseCommand.getTitleCn();
        command.titleEn = dataCompanyBasicWarehouseCommand.getTitleEn();
        command.applyNo = dataCompanyBasicWarehouseCommand.getApplyNo();
        command.standardApplyNo = dataCompanyBasicWarehouseCommand.getStandardApplyNo();
        command.applyDate = dataCompanyBasicWarehouseCommand.getApplyDate();
        command.publicNo = dataCompanyBasicWarehouseCommand.getPublicNo();
        command.standardPublicNo = dataCompanyBasicWarehouseCommand.getStandardPublicNo();
        command.publicDate = dataCompanyBasicWarehouseCommand.getPublicDate();
        command.authorizePublicNo = dataCompanyBasicWarehouseCommand.getAuthorizePublicNo();
        command.authorizePublicDate = dataCompanyBasicWarehouseCommand.getAuthorizePublicDate();
        command.priorityNo = dataCompanyBasicWarehouseCommand.getPriorityNo();
        command.priorityDate = dataCompanyBasicWarehouseCommand.getPriorityDate();
        command.invalidDate = dataCompanyBasicWarehouseCommand.getInvalidDate();
        command.internationalApplyNo = dataCompanyBasicWarehouseCommand.getInternationalApplyNo();
        command.internationalApplyDate = dataCompanyBasicWarehouseCommand.getInternationalApplyDate();
        command.internationalPublicNo = dataCompanyBasicWarehouseCommand.getInternationalPublicNo();
        command.internationalPublicDate = dataCompanyBasicWarehouseCommand.getInternationalPublicDate();
        command.entryCountryDate = dataCompanyBasicWarehouseCommand.getEntryCountryDate();
        command.splitApplyNo = dataCompanyBasicWarehouseCommand.getSplitApplyNo();
        command.splitApplyDate = dataCompanyBasicWarehouseCommand.getSplitApplyDate();
        command.rewardLevel = dataCompanyBasicWarehouseCommand.getRewardLevel();
        command.rewardName = dataCompanyBasicWarehouseCommand.getRewardName();
        command.rewardSession = dataCompanyBasicWarehouseCommand.getRewardSession();
        command.ipcCategoryNos = dataCompanyBasicWarehouseCommand.getIpcCategoryNos();
        command.ipcMainCategoryNo = dataCompanyBasicWarehouseCommand.getIpcMainCategoryNo();
        command.cpcCategoryNos = dataCompanyBasicWarehouseCommand.getCpcCategoryNos();
        command.cpcMainCategoryNo = dataCompanyBasicWarehouseCommand.getCpcMainCategoryNo();
        command.ucNo = dataCompanyBasicWarehouseCommand.getUcNo();
        command.fiNo = dataCompanyBasicWarehouseCommand.getFiNo();
        command.ftermNo = dataCompanyBasicWarehouseCommand.getFtermNo();
        command.patentTypeDictId = dataCompanyBasicWarehouseCommand.getPatentTypeDictId();
        command.receivingOfficeName = dataCompanyBasicWarehouseCommand.getReceivingOfficeName();
        command.receivingCountryName = dataCompanyBasicWarehouseCommand.getReceivingCountryName();
        command.originCountryName = dataCompanyBasicWarehouseCommand.getOriginCountryName();
        command.literatureType = dataCompanyBasicWarehouseCommand.getLiteratureType();
        command.appearanceSchemeNo = dataCompanyBasicWarehouseCommand.getAppearanceSchemeNo();
        command.appearanceSchemeNoSupplement = dataCompanyBasicWarehouseCommand.getAppearanceSchemeNoSupplement();
        command.nationalEconomicClassification = dataCompanyBasicWarehouseCommand.getNationalEconomicClassification();
        command.isPct = dataCompanyBasicWarehouseCommand.getIsPct();
        command.patentImageUrl = dataCompanyBasicWarehouseCommand.getPatentImageUrl();
        command.isCurrentValid = dataCompanyBasicWarehouseCommand.getIsCurrentValid();
        command.currentRightStatusDictId = dataCompanyBasicWarehouseCommand.getCurrentRightStatusDictId();
        command.instructionManualPageSize = dataCompanyBasicWarehouseCommand.getInstructionManualPageSize();
        command.locarnoCategoryNos = dataCompanyBasicWarehouseCommand.getLocarnoCategoryNos();
        command.patentStrength = dataCompanyBasicWarehouseCommand.getPatentStrength();


        return command;
    }
}
