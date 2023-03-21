package com.particle.dataquery.client.dataapi.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 数据查询数据接口 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Data
@ApiModel
public class DataQueryDataApiUpdateCommand extends AbstractBaseUpdateCommand {


    @NotEmpty(message = "接口地址 不能为空")
    @ApiModelProperty(value = "接口地址",required = true)
    private String url;


    @NotEmpty(message = "接口名称 不能为空")
    @ApiModelProperty(value = "接口名称",required = true)
    private String name;


    @ApiModelProperty(value = "数据查询数据源接口id")
    private Long dataQueryDatasourceApiId;


    @ApiModelProperty(value = "适配类型")
    private Long adaptTypeDictId;


    @ApiModelProperty(value = "接口适配配置json")
    private String adaptConfigJson;


    @ApiModelProperty(value = "入参类型")
    private Long inParamTypeDictId;


    @ApiModelProperty(value = "入参示例")
    private String inParamExampleConfigJson;


    @ApiModelProperty(value = "入参测试用例数据")
    private String inParamTestCaseDataConfigJson;


    @ApiModelProperty(value = "入参文档配置json")
    private String inParamDocConfigJson;


    @NotNull(message = "出参类型 不能为空")
    @ApiModelProperty(value = "出参类型",required = true)
    private Long outParamTypeDictId;


    @ApiModelProperty(value = "出参示例")
    private String outParamExampleConfigJson;


    @ApiModelProperty(value = "出参文档配置json")
    private String outParamDocConfigJson;


    @ApiModelProperty(value = "出参成功或失败配置json")
    private String outParamSuccessConfigJson;


    @NotNull(message = "输出类型 不能为空")
    @ApiModelProperty(value = "输出类型",required = true)
    private Long responseTypeDictId;


    @ApiModelProperty(value = "分页适配信息配置json")
    private String pageableAdapterConfigJson;


    @ApiModelProperty(value = "字典配置json")
    private String dictConfigJson;


    @ApiModelProperty(value = "描述")
    private String remark;

}
