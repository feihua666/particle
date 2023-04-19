package com.particle.func.client.application.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreeQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 功能应用 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Data
@ApiModel
public class FuncApplicationQueryListCommand extends AbstractBaseTreeQueryCommand {



    @Like
        @ApiModelProperty(value = "应用编码,左前缀匹配")
    private String code;


    @Like
        @ApiModelProperty(value = "功能应用名称,左前缀匹配")
    private String name;


    @ApiModelProperty(value = "是否禁用")
    private Boolean isDisabled;



    @ApiModelProperty(value = "应用主题")
    private String applicationTheme;


    @ApiModelProperty(value = "应用默认的页面路由")
    private String applicationDefaultRoute;



    @ApiModelProperty(value = "是否为分组")
    private Boolean isGroup;























}
