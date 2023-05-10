package com.particle.tracking.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 埋点页面 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
@Data
@ApiModel
public class TrackingPageCreateCommand extends AbstractBaseCommand {



    @NotEmpty(message = "页面编码 不能为空")
    @ApiModelProperty(value = "页面编码",required = true)
    private String code;


    @NotEmpty(message = "页面名称 不能为空")
    @ApiModelProperty(value = "页面名称",required = true)
    private String name;


    @NotEmpty(message = "页面图片地址 不能为空")
    @ApiModelProperty(value = "页面图片地址",required = true)
    private String imageUrl;


    @NotEmpty(message = "页面访问地址 不能为空")
    @ApiModelProperty(value = "页面访问地址",required = true)
    private String absoluteUrl;


    @NotEmpty(message = "路径说明 不能为空")
    @ApiModelProperty(value = "路径说明",required = true)
    private String pathMemo;


    @NotEmpty(message = "页面版本 不能为空")
    @ApiModelProperty(value = "页面版本",required = true)
    private String pageVersion;


    @NotEmpty(message = "分组标识 不能为空")
    @ApiModelProperty(value = "分组标识",required = true)
    private String groupFlag;


    @NotNull(message = "排序 不能为空")
    @ApiModelProperty(value = "排序",required = true)
    private Integer seq;


    @ApiModelProperty(value = "描述")
    private String remark;

    @ApiModelProperty("父级")
    private Long parentId;
}
