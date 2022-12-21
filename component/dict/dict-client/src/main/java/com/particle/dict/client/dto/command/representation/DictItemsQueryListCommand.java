package com.particle.dict.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 字典项或字典组查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Data
@ApiModel
public class DictItemsQueryListCommand extends AbstractBaseQueryCommand {

    @NotEmpty(message="字典组编码不能为空")
    @ApiModelProperty(value = "字典组编码",required = true)
    private String groupCode;

    @ApiModelProperty(value = "私有标识")
    private String privateFlag;

    @ApiModelProperty(value = "分组标识")
    private String groupFlag;

    @ApiModelProperty(value = "标签，多个以逗号分隔，用来区分字典项")
    private String tags;


}
