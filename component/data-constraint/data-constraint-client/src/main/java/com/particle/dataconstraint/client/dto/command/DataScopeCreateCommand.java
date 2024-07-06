package com.particle.dataconstraint.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 数据范围 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:38
 */
@Data
@Schema
public class DataScopeCreateCommand extends AbstractBaseCommand {



    @NotEmpty(message = "数据范围编码 不能为空")
        @Schema(description = "数据范围编码",requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;


    @NotEmpty(message = "数据范围名称 不能为空")
        @Schema(description = "数据范围名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotNull(message = "数据对象id 不能为空")
        @Schema(description = "数据对象id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dataObjectId;

	@Schema(description = "约束条件内容类型，字典id")
	private Long constraintContentTypeDictId;


    @Schema(description = "约束条件内容")
    private String constraintContent;


    @NotNull(message = "是否自定义 不能为空")
        @Schema(description = "是否自定义",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isCustom;


    @NotNull(message = "是否用于删除 不能为空")
        @Schema(description = "是否用于删除",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isForDelete;


    @NotNull(message = "是否用于修改 不能为空")
        @Schema(description = "是否用于修改",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isForUpdate;


    @NotNull(message = "是否用于查询 不能为空")
        @Schema(description = "是否用于查询",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isForQuery;


    @NotNull(message = "是否用于其它 不能为空")
        @Schema(description = "是否用于其它",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isForOther;


    @Schema(description = "描述")
    private String remark;









}