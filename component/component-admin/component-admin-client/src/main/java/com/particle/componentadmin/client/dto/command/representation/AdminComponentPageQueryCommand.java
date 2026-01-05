package com.particle.componentadmin.client.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 组件 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:21:31
 */
@Data
@Schema
public class AdminComponentPageQueryCommand extends AbstractBasePageQueryCommand {

    @Like
    @Schema(description = "组件英文名称如：area、user")
    private String code;

    @Like
    @Schema(description = "组件中文名称")
    private String name;


    @Schema(description = "组件路径")
    private String path;



}
