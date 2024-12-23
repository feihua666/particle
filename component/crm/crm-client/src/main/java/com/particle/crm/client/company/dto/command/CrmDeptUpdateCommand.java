package com.particle.crm.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 客户公司部门 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 10:16:52
 */
@Data
@Schema
public class CrmDeptUpdateCommand extends AbstractBaseUpdateCommand {



    @Schema(description = "部门编码")
    private String code;


    @NotEmpty(message = "部门名称 不能为空")
        @Schema(description = "部门名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotNull(message = "客户公司id 不能为空")
        @Schema(description = "客户公司id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long crmCompanyId;


    @Schema(description = "备注")
    private String remark;


    @NotNull(message = "排序 不能为空")
        @Schema(description = "排序",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer seq;



    @Schema(description = "父级")
    private Long parentId;



















}
