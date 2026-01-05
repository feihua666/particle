package com.particle.oplog.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 操作日志审计数据 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025/12/27 12:31
 */
@Data
@Schema
public class OpLogAuditDataCreateCommand extends AbstractBaseCommand {

    @NotEmpty(message = "数据字段名称 不能为空")
    @Schema(description = "数据字段名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotEmpty(message = "数据字段英文名称 不能为空")
    @Schema(description = "数据字段英文名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String propertyName;

    @Schema(description = "旧值")
    private String oldValue;

    @Schema(description = "新值")
    private String newValue;

    @Schema(description = "值改变类型字典id")
    private Long changeTypeDictId;

    @NotEmpty(message = "值改变类型 不能为空")
    @Schema(description = "值改变类型",requiredMode = Schema.RequiredMode.REQUIRED)
    private String changeType;

    @Schema(description = "类型对应的字典id")
    private Long typeDictId;

    @NotEmpty(message = "类型 不能为空")
    @Schema(description = "类型",requiredMode = Schema.RequiredMode.REQUIRED)
    private String type;

    @Schema(description = "操作用户id")
    private Long userId;

    @Schema(description = "数据id")
    private Long dataId;

    @Schema(description = "数据表名")
    private String dataTable;

    @Schema(description = "数据载体")
    private String dataEntity;

    @NotNull(message = "操作日志id 不能为空")
    @Schema(description = "操作日志id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long opLogId;


}
