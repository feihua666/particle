package com.particle.func.client.application.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 功能应用 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Data
@ApiModel
public class FuncApplicationUpdateCommand extends AbstractBaseUpdateCommand {



    @NotEmpty(message = "应用编码 不能为空")
    @ApiModelProperty(value = "应用编码",required = true)
    private String code;


    @NotEmpty(message = "功能应用名称 不能为空")
    @ApiModelProperty(value = "功能应用名称",required = true)
    private String name;


    @NotNull(message = "是否禁用 不能为空")
    @ApiModelProperty(value = "是否禁用",required = true)
    private Boolean isDisabled;


    @ApiModelProperty(value = "禁用原因")
    private String disabledReason;


    @ApiModelProperty(value = "应用主题")
    private String applicationTheme;


    @ApiModelProperty(value = "应用默认的页面路由")
    private String applicationDefaultRoute;


    @ApiModelProperty("应用logo地址")
    private String applicationLogoUrl;

    @ApiModelProperty("应用图标地址")
    private String applicationIconUrl;

    @ApiModelProperty(value = "额外配置json")
    private String configJson;

    @NotNull(message = "是否为分组 不能为空")
    @ApiModelProperty(value = "是否为分组",required = true)
    private Boolean isGroup;

    @NotNull(message = "排序 不能为空")
    @ApiModelProperty(value = "排序",required = true)
    private Integer seq;

    @ApiModelProperty(value = "描述")
    private String remark;

    @ApiModelProperty(value = "父级")
    private Long parentId;

}
