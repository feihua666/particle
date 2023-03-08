package com.particle.dataquery.client.provider.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 数据查询供应商 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@PropValid
@Data
@ApiModel
public class DataQueryProviderUpdateCommand extends AbstractBaseUpdateCommand {



    @NotEmpty(message = "供应商名称 不能为空")
    @ApiModelProperty(value = "供应商名称",required = true)
    private String name;


    @NotNull(message = "是否禁用 不能为空")
    @ApiModelProperty(value = "是否禁用",required = true)
    private Boolean isDisabled;

    /**
     * 禁用时，禁用原因必填
     */
    @PropValid.DependCondition(message = "禁用原因不能为空",dependProp = "isDisabled",ifEqual = "true")
    @ApiModelProperty("禁用原因")
    private String disabledReason;

    @ApiModelProperty(value = "联系人姓名")
    private String contactUserName;


    @ApiModelProperty(value = "联系人邮箱")
    private String contactUserEmail;


    @ApiModelProperty(value = "联系人电话")
    private String contactUserPhone;


    @ApiModelProperty(value = "描述")
    private String remark;

}
