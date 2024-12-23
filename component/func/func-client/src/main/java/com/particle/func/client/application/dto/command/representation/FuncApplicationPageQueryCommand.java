package com.particle.func.client.application.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 功能应用 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Data
@Schema
public class FuncApplicationPageQueryCommand extends AbstractBaseTreePageQueryCommand {



    @Like
        @Schema(description = "应用编码,左前缀匹配")
    private String code;


    @Like
        @Schema(description = "功能应用名称,左前缀匹配")
    private String name;


    @Schema(description = "是否禁用")
    private Boolean isDisabled;



    @Schema(description = "应用主题")
    private String applicationTheme;


    @Schema(description = "应用默认的页面路由")
    private String applicationDefaultRoute;



    @Schema(description = "是否为分组")
    private Boolean isGroup;























}
