package com.particle.dataquery.client.datasource.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 数据查询数据源 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@Data
@ApiModel
public class DataQueryDatasourceUpdateCommand extends AbstractBaseUpdateCommand {



    @ApiModelProperty(value = "编码")
    private String code;


    @NotEmpty(message = "名称 不能为空")
    @ApiModelProperty(value = "名称",required = true)
    private String name;


    @NotNull(message = "类型 不能为空")
    @ApiModelProperty(value = "类型",required = true)
    private Long typeDictId;


    @NotEmpty(message = "json配置 不能为空")
    @ApiModelProperty(value = "json配置",required = true)
    private String configJson;


    @ApiModelProperty(value = "用户名")
    private String username;


    @ApiModelProperty(value = "密码")
    private String password;


    @NotNull(message = "数据查询供应商id 不能为空")
    @ApiModelProperty(value = "数据查询供应商id",required = true)
    private Long dataQueryProviderId;


    @ApiModelProperty(value = "描述")
    private String remark;

}
