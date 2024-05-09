package com.particle.crm.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 客户公司 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
@Data
@Schema
public class CrmCompanyCreateCommand extends AbstractBaseCommand {



    @Schema(description = "公司编码")
    private String code;


    @NotEmpty(message = "公司名称 不能为空")
        @Schema(description = "公司名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @Schema(description = "公司简称名称")
    private String nameSimple;


    @Schema(description = "备注")
    private String remark;


    @NotNull(message = "排序 不能为空")
        @Schema(description = "排序",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer seq;



    @Schema(description = "父级")
    private Long parentId;



















}
