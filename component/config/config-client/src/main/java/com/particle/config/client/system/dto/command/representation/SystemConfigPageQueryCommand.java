package com.particle.config.client.system.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 系统参数配置 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-05-30 10:29:04
 */
@Data
@Schema
public class SystemConfigPageQueryCommand extends AbstractBasePageQueryCommand {



    @Like
        @Schema(description = "参数配置编码,左前缀匹配")
    private String code;


    @Like
        @Schema(description = "参数配置名称,左前缀匹配")
    private String name;


    @Schema(description = "参数配置值")
    private String value;


    @Schema(description = "是否内置")
    private Boolean isBuiltIn;


    @Schema(description = "是否禁用")
    private Boolean isDisabled;


    @Schema(description = "禁用原因")
    private String disabledReason;


    @Like
        @Schema(description = "标签,左前缀匹配")
    private String tag;










}
