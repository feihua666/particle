package com.particle.area.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 区域 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Data
@ApiModel
public class AreaUpdateCommand extends AbstractBaseUpdateCommand {


    @NotEmpty(message = "编码不能为空")
    @ApiModelProperty(value = "编码，唯一",required = true)
    private String code;

    @NotEmpty(message = "编码不能为空")
    @ApiModelProperty(value = "区域名称",required = true)
    private String name;

    @ApiModelProperty("区域简称")
    private String nameSimple;

    @ApiModelProperty("类型，字典id")
    private Long typeDictId;

    @ApiModelProperty("经度")
    private String longitude;

    @ApiModelProperty("纬度")
    private String latitude;

    @ApiModelProperty("备注")
    private String remark;

    @NotNull(message = "排序不能为空")
    @ApiModelProperty("排序,默认按该字段升序排序")
    private Integer seq;


}
