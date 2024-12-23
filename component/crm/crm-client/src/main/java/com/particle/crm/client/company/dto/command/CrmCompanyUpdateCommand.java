package com.particle.crm.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 客户公司 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
@Data
@Schema
public class CrmCompanyUpdateCommand extends AbstractBaseUpdateCommand {



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
