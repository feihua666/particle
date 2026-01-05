package com.particle.oplog.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 操作日志 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025/12/27 12:31
 */
@Data
@Schema
public class OpLogCreateCommand  extends AbstractBaseCommand {

    /**
     * 主键id，可以自己指定id，不填会自动生成
     */
    @Schema(description = "主键id")
    private Long id;

    @NotEmpty(message = "操作名称 不能为空")
    @Schema(description = "操作名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "模块对应的字典id")
    private Long moduleDictId;

    @NotEmpty(message = "模块 不能为空")
    @Schema(description = "模块",requiredMode = Schema.RequiredMode.REQUIRED)
    private String module;

    @Schema(description = "类型对应的字典id")
    private Long typeDictId;

    @NotEmpty(message = "类型 不能为空")
    @Schema(description = "类型",requiredMode = Schema.RequiredMode.REQUIRED)
    private String type;

    @Schema(description = "操作用户id")
    private Long userId;

    @Schema(description = "操作用户姓名")
    private String userName;

    @Schema(description = "操作用户昵称")
    private String userNickname;

    @Schema(description = "操作用户头像")
    private String userAvatar;

    @Schema(description = "请求地址")
    private String url;

    @Schema(description = "请求ip")
    private String ip;

    @Schema(description = "主数据id")
    private Long mainDataId;

    @Schema(description = "主数据表名")
    private String mainDataTable;

    @Schema(description = "主数据载体")
    private String mainDataEntity;

    @NotNull(message = "操作时间 不能为空")
    @Schema(description = "操作时间",requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime operateAt;

    @Schema(description = "主数据载体")
    private String remark;

    @Schema(description = "父级id")
    private Long parentId;

    @Schema(description = "操作日志审计数据")
    private List<OpLogAuditDataCreateCommand> auditDataCreateCommandList;
}
