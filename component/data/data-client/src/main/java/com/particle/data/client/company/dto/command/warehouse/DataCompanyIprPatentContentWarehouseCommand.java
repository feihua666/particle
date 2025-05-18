package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentContentExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * 企业知识产权专利内容入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprPatentContentWarehouseCommand extends AbstractBaseCommand {

    @NotNull(message = "企业知识产权专利表id 不能为空")
    @Schema(description = "企业知识产权专利表id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprPatentId;

    @Schema(description = "原始摘要内容")
    private String abstractContent;

    @Schema(description = "英文摘要内容")
    private String abstractContentEn;

    @Schema(description = "中文摘要内容")
    private String abstractContentCn;

    @Schema(description = "原始摘要内容地址")
    private String abstractContentUrl;

    @Schema(description = "英文摘要内容地址")
    private String abstractContentEnUrl;

    @Schema(description = "中文摘要内容地址")
    private String abstractContentCnUrl;

    @Schema(description = "原始简要说明")
    private String briefContent;

    @Schema(description = "英文简要说明")
    private String briefContentEn;

    @Schema(description = "中文简要说明")
    private String briefContentCn;

    @Schema(description = "原始说明书")
    private String instructionContent;

    @Schema(description = "英文说明书")
    private String instructionContentEn;

    @Schema(description = "中文说明书")
    private String instructionContentCn;

    @Schema(description = "原始说明书地址")
    private String instructionContentUrl;

    @Schema(description = "英文说明书地址")
    private String instructionContentEnUrl;

    @Schema(description = "中文说明书地址")
    private String instructionContentCnUrl;

    @Schema(description = "原始权利要求书")
    private String claimContent;

    @Schema(description = "英文权利要求书")
    private String claimContentEn;

    @Schema(description = "中文权利要求书")
    private String claimContentCn;

    @Schema(description = "原始权利要求书地址")
    private String claimContentUrl;

    @Schema(description = "英文权利要求书地址")
    private String claimContentEnUrl;

    @Schema(description = "中文权利要求书地址")
    private String claimContentCnUrl;

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyIprPatentId)
                && StrUtil.isEmpty(abstractContent)
                && StrUtil.isEmpty(abstractContentEn)
                && StrUtil.isEmpty(abstractContentCn)
                && StrUtil.isEmpty(abstractContentUrl)
                && StrUtil.isEmpty(abstractContentEnUrl)
                && StrUtil.isEmpty(abstractContentCnUrl)
                && StrUtil.isEmpty(briefContent)
                && StrUtil.isEmpty(briefContentEn)
                && StrUtil.isEmpty(briefContentCn)
                && StrUtil.isEmpty(instructionContent)
                && StrUtil.isEmpty(instructionContentEn)
                && StrUtil.isEmpty(instructionContentCn)
                && StrUtil.isEmpty(instructionContentUrl)
                && StrUtil.isEmpty(instructionContentEnUrl)
                && StrUtil.isEmpty(instructionContentCnUrl)
                && StrUtil.isEmpty(claimContent)
                && StrUtil.isEmpty(claimContentEn)
                && StrUtil.isEmpty(claimContentCn)
                && StrUtil.isEmpty(claimContentUrl)
                && StrUtil.isEmpty(claimContentEnUrl)
                && StrUtil.isEmpty(claimContentCnUrl);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprPatentContentExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyIprPatentId, exWarehouseVO.getCompanyIprPatentId())) {
            this.companyIprPatentId = null;
        }
        if (Objects.equals(abstractContent, exWarehouseVO.getAbstractContent())) {
            this.abstractContent = null;
        }
        if (Objects.equals(abstractContentEn, exWarehouseVO.getAbstractContentEn())) {
            this.abstractContentEn = null;
        }
        if (Objects.equals(abstractContentCn, exWarehouseVO.getAbstractContentCn())) {
            this.abstractContentCn = null;
        }
        if (Objects.equals(abstractContentUrl, exWarehouseVO.getAbstractContentUrl())) {
            this.abstractContentUrl = null;
        }
        if (Objects.equals(abstractContentEnUrl, exWarehouseVO.getAbstractContentEnUrl())) {
            this.abstractContentEnUrl = null;
        }
        if (Objects.equals(abstractContentCnUrl, exWarehouseVO.getAbstractContentCnUrl())) {
            this.abstractContentCnUrl = null;
        }
        if (Objects.equals(briefContent, exWarehouseVO.getBriefContent())) {
            this.briefContent = null;
        }
        if (Objects.equals(briefContentEn, exWarehouseVO.getBriefContentEn())) {
            this.briefContentEn = null;
        }
        if (Objects.equals(briefContentCn, exWarehouseVO.getBriefContentCn())) {
            this.briefContentCn = null;
        }
        if (Objects.equals(instructionContent, exWarehouseVO.getInstructionContent())) {
            this.instructionContent = null;
        }
        if (Objects.equals(instructionContentEn, exWarehouseVO.getInstructionContentEn())) {
            this.instructionContentEn = null;
        }
        if (Objects.equals(instructionContentCn, exWarehouseVO.getInstructionContentCn())) {
            this.instructionContentCn = null;
        }
        if (Objects.equals(instructionContentUrl, exWarehouseVO.getInstructionContentUrl())) {
            this.instructionContentUrl = null;
        }
        if (Objects.equals(instructionContentEnUrl, exWarehouseVO.getInstructionContentEnUrl())) {
            this.instructionContentEnUrl = null;
        }
        if (Objects.equals(instructionContentCnUrl, exWarehouseVO.getInstructionContentCnUrl())) {
            this.instructionContentCnUrl = null;
        }
        if (Objects.equals(claimContent, exWarehouseVO.getClaimContent())) {
            this.claimContent = null;
        }
        if (Objects.equals(claimContentEn, exWarehouseVO.getClaimContentEn())) {
            this.claimContentEn = null;
        }
        if (Objects.equals(claimContentCn, exWarehouseVO.getClaimContentCn())) {
            this.claimContentCn = null;
        }
        if (Objects.equals(claimContentUrl, exWarehouseVO.getClaimContentUrl())) {
            this.claimContentUrl = null;
        }
        if (Objects.equals(claimContentEnUrl, exWarehouseVO.getClaimContentEnUrl())) {
            this.claimContentEnUrl = null;
        }
        if (Objects.equals(claimContentCnUrl, exWarehouseVO.getClaimContentCnUrl())) {
            this.claimContentCnUrl = null;
        }
    }

    /**
     * 根据 DataCompanyIprPatentContentExWarehouseVO 创建指令对象
     * @param exWarehouseVO
     * @return
     */
    public static DataCompanyIprPatentContentWarehouseCommand createByDataCompanyIprPatentContentExWarehouseVO(DataCompanyIprPatentContentExWarehouseVO exWarehouseVO) {
        DataCompanyIprPatentContentWarehouseCommand command = new DataCompanyIprPatentContentWarehouseCommand();
        command.companyIprPatentId = exWarehouseVO.getCompanyIprPatentId();
        command.abstractContent = exWarehouseVO.getAbstractContent();
        command.abstractContentEn = exWarehouseVO.getAbstractContentEn();
        command.abstractContentCn = exWarehouseVO.getAbstractContentCn();
        command.abstractContentUrl = exWarehouseVO.getAbstractContentUrl();
        command.abstractContentEnUrl = exWarehouseVO.getAbstractContentEnUrl();
        command.abstractContentCnUrl = exWarehouseVO.getAbstractContentCnUrl();
        command.briefContent = exWarehouseVO.getBriefContent();
        command.briefContentEn = exWarehouseVO.getBriefContentEn();
        command.briefContentCn = exWarehouseVO.getBriefContentCn();
        command.instructionContent = exWarehouseVO.getInstructionContent();
        command.instructionContentEn = exWarehouseVO.getInstructionContentEn();
        command.instructionContentCn = exWarehouseVO.getInstructionContentCn();
        command.instructionContentUrl = exWarehouseVO.getInstructionContentUrl();
        command.instructionContentEnUrl = exWarehouseVO.getInstructionContentEnUrl();
        command.instructionContentCnUrl = exWarehouseVO.getInstructionContentCnUrl();
        command.claimContent = exWarehouseVO.getClaimContent();
        command.claimContentEn = exWarehouseVO.getClaimContentEn();
        command.claimContentCn = exWarehouseVO.getClaimContentCn();
        command.claimContentUrl = exWarehouseVO.getClaimContentUrl();
        command.claimContentEnUrl = exWarehouseVO.getClaimContentEnUrl();
        command.claimContentCnUrl = exWarehouseVO.getClaimContentCnUrl();
        return command;
    }
}
