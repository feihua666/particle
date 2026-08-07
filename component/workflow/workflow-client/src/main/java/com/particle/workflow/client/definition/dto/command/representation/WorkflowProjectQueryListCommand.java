package com.particle.workflow.client.definition.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 工作流项目 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:12
 */
@Data
@Schema
public class WorkflowProjectQueryListCommand extends AbstractBaseQueryCommand {



    @Like
    @Schema(description = "项目名称,左前缀匹配")
    private String name;


    @Schema(description = "归属用户id")
    private Long userId;


    @Schema(description = "是否公开")
    private Boolean isPublic;










}
