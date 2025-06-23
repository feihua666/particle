package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业知识产权商标入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprTrademarkWarehouseCommand extends AbstractBaseCommand {


    @Schema(description = "商标名称")
    private String name;


    @Schema(description = "商标名称")
    private String nameCn;


    @Schema(description = "商标名称")
    private String nameEn;


    @Schema(description = "注册号")
    private String regNo;


    @Schema(description = "申请号")
    private String applyNo;


    @Schema(description = "尼斯分类号")
    private String niceCategoryNo;


    @Schema(description = "当前权利状态名称")
    private String rightStatusName;


    @Schema(description = "商标类型名称")
    private String typeName;


    @Schema(description = "商标图片地址")
    private String trademarkImageUrl;


    @Schema(description = "注册日期")
    private LocalDate regDate;


    @Schema(description = "申请日期")
    private LocalDate applyDate;


    @Schema(description = "是否共享")
    private Boolean isShare;


    @Schema(description = "是否驰名商标")
    private Boolean isWellKnown;


    @Schema(description = "是否指定颜色")
    private Boolean isSpecifyColor;


    @Schema(description = "优先权日期")
    private LocalDate priorityDate;


    @Schema(description = "专用权期限开始日期")
    private LocalDate specialStartDate;


    @Schema(description = "专用权期限截止日期")
    private LocalDate specialEndDate;


    @Schema(description = "初审公告日期")
    private LocalDate firstTrialPublicDate;


    @Schema(description = "初审公告号")
    private String firstTrialPublicNo;


    @Schema(description = "后期指定日期")
    private LocalDate lateSpecifyDate;


    @Schema(description = "异议截止日期")
    private LocalDate dissentEndDate;


    @Schema(description = "原始语种")
    private String originLang;


    @Schema(description = "注册公告期号")
    private String regPublicIssueNo;


    @Schema(description = "国际注册日期")
    private LocalDate internationalRegDate;

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return StrUtil.isEmpty(name)
                && StrUtil.isEmpty(nameCn)
                && StrUtil.isEmpty(nameEn)
                && StrUtil.isEmpty(regNo)
                && StrUtil.isEmpty(applyNo)
                && StrUtil.isEmpty(niceCategoryNo)
                && StrUtil.isEmpty(rightStatusName)
                && StrUtil.isEmpty(typeName)
                && StrUtil.isEmpty(trademarkImageUrl)
                && Objects.isNull(regDate)
                && Objects.isNull(applyDate)
                && Objects.isNull(isShare)
                && Objects.isNull(isWellKnown)
                && Objects.isNull(isSpecifyColor)
                && Objects.isNull(priorityDate)
                && Objects.isNull(specialStartDate)
                && Objects.isNull(specialEndDate)
                && Objects.isNull(firstTrialPublicDate)
                && StrUtil.isEmpty(firstTrialPublicNo)
                && Objects.isNull(lateSpecifyDate)
                && Objects.isNull(dissentEndDate)
                && StrUtil.isEmpty(originLang)
                && StrUtil.isEmpty(regPublicIssueNo)
                && Objects.isNull(internationalRegDate)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprTrademarkExWarehouseVO exWarehouseVO) {
        if (Objects.equals(name, exWarehouseVO.getName())) {
            this.name = null;
        }
        if (Objects.equals(nameCn, exWarehouseVO.getNameCn())) {
            this.nameCn = null;
        }
        if (Objects.equals(nameEn, exWarehouseVO.getNameEn())) {
            this.nameEn = null;
        }
        if (Objects.equals(regNo, exWarehouseVO.getRegNo())) {
            this.regNo = null;
        }
        if (Objects.equals(applyNo, exWarehouseVO.getApplyNo())) {
            this.applyNo = null;
        }
        if (Objects.equals(niceCategoryNo, exWarehouseVO.getNiceCategoryNo())) {
            this.niceCategoryNo = null;
        }
        if (Objects.equals(rightStatusName, exWarehouseVO.getRightStatusName())) {
            this.rightStatusName = null;
        }
        if (Objects.equals(typeName, exWarehouseVO.getTypeName())) {
            this.typeName = null;
        }
        if (Objects.equals(trademarkImageUrl, exWarehouseVO.getTrademarkImageUrl())) {
            this.trademarkImageUrl = null;
        }
        if (Objects.equals(regDate, exWarehouseVO.getRegDate())) {
            this.regDate = null;
        }
        if (Objects.equals(applyDate, exWarehouseVO.getApplyDate())) {
            this.applyDate = null;
        }
        if (Objects.equals(isShare, exWarehouseVO.getIsShare())) {
            this.isShare = null;
        }
        if (Objects.equals(isWellKnown, exWarehouseVO.getIsWellKnown())) {
            this.isWellKnown = null;
        }
        if (Objects.equals(isSpecifyColor, exWarehouseVO.getIsSpecifyColor())) {
            this.isSpecifyColor = null;
        }
        if (Objects.equals(priorityDate, exWarehouseVO.getPriorityDate())) {
            this.priorityDate = null;
        }
        if (Objects.equals(specialStartDate, exWarehouseVO.getSpecialStartDate())) {
            this.specialStartDate = null;
        }
        if (Objects.equals(specialEndDate, exWarehouseVO.getSpecialEndDate())) {
            this.specialEndDate = null;
        }
        if (Objects.equals(firstTrialPublicDate, exWarehouseVO.getFirstTrialPublicDate())) {
            this.firstTrialPublicDate = null;
        }
        if (Objects.equals(firstTrialPublicNo, exWarehouseVO.getFirstTrialPublicNo())) {
            this.firstTrialPublicNo = null;
        }
        if (Objects.equals(lateSpecifyDate, exWarehouseVO.getLateSpecifyDate())) {
            this.lateSpecifyDate = null;
        }
        if (Objects.equals(dissentEndDate, exWarehouseVO.getDissentEndDate())) {
            this.dissentEndDate = null;
        }
        if (Objects.equals(originLang, exWarehouseVO.getOriginLang())) {
            this.originLang = null;
        }
        if (Objects.equals(regPublicIssueNo, exWarehouseVO.getRegPublicIssueNo())) {
            this.regPublicIssueNo = null;
        }
        if (Objects.equals(internationalRegDate, exWarehouseVO.getInternationalRegDate())) {
            this.internationalRegDate = null;
        }

    }

    public static DataCompanyIprTrademarkWarehouseCommand createByDataCompanyIprTrademarkExWarehouseVO(DataCompanyIprTrademarkExWarehouseVO exWarehouseVO) {
        DataCompanyIprTrademarkWarehouseCommand command = new DataCompanyIprTrademarkWarehouseCommand();
        command.name = exWarehouseVO.getName();
        command.nameCn = exWarehouseVO.getNameCn();
        command.nameEn = exWarehouseVO.getNameEn();
        command.regNo = exWarehouseVO.getRegNo();
        command.applyNo = exWarehouseVO.getApplyNo();
        command.niceCategoryNo = exWarehouseVO.getNiceCategoryNo();
        command.rightStatusName = exWarehouseVO.getRightStatusName();
        command.typeName = exWarehouseVO.getTypeName();
        command.trademarkImageUrl = exWarehouseVO.getTrademarkImageUrl();
        command.regDate = exWarehouseVO.getRegDate();
        command.applyDate = exWarehouseVO.getApplyDate();
        command.isShare = exWarehouseVO.getIsShare();
        command.isWellKnown = exWarehouseVO.getIsWellKnown();
        command.isSpecifyColor = exWarehouseVO.getIsSpecifyColor();
        command.priorityDate = exWarehouseVO.getPriorityDate();
        command.specialStartDate = exWarehouseVO.getSpecialStartDate();
        command.specialEndDate = exWarehouseVO.getSpecialEndDate();
        command.firstTrialPublicDate = exWarehouseVO.getFirstTrialPublicDate();
        command.firstTrialPublicNo = exWarehouseVO.getFirstTrialPublicNo();
        command.lateSpecifyDate = exWarehouseVO.getLateSpecifyDate();
        command.dissentEndDate = exWarehouseVO.getDissentEndDate();
        command.originLang = exWarehouseVO.getOriginLang();
        command.regPublicIssueNo = exWarehouseVO.getRegPublicIssueNo();
        command.internationalRegDate = exWarehouseVO.getInternationalRegDate();

        return command;
    }
}
