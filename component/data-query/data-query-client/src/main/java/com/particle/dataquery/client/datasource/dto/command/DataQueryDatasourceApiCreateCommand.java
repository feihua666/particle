package com.particle.dataquery.client.datasource.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 数据查询数据源接口 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Data
@ApiModel
public class DataQueryDatasourceApiCreateCommand extends AbstractBaseCommand {



    @ApiModelProperty(value = "编码")
    private String code;


    @NotEmpty(message = "名称 不能为空")
    @ApiModelProperty(value = "名称",required = true)
    private String name;


    @NotNull(message = "分类 不能为空")
    @ApiModelProperty(value = "分类",required = true)
    private Long categoryDictId;


    @NotNull(message = "数据查询供应商id 不能为空")
    @ApiModelProperty(value = "数据查询供应商id",required = true)
    private Long dataQueryProviderId;


    @ApiModelProperty(value = "文档链接")
    private String dataQueryProviderDocLinkUrl;


    @NotNull(message = "数据查询数据源id 不能为空")
    @ApiModelProperty(value = "数据查询数据源id",required = true)
    private Long dataQueryDatasourceId;

    @ApiModelProperty(value = "入参类型，字典id")
    private Long inParamTypeDictId;

    @ApiModelProperty(value = "入参示例")
    private String inParamExampleConfigJson;


    @ApiModelProperty(value = "入参测试用例数据")
    private String inParamTestCaseDataConfigJson;


    @ApiModelProperty(value = "入参文档配置json")
    private String inParamDocConfigJson;


    @ApiModelProperty(value = "入参校验配置json")
    private String inParamValidateConfigJson;


    @ApiModelProperty(value = "入参扩展配置json")
    private String inParamExtConfigJson;

    @NotNull(message = "出参类型 不能为空")
    @ApiModelProperty(value = "出参类型，字典id",required = true)
    private Long outParamTypeDictId;

    @ApiModelProperty(value = "出参示例")
    private String outParamExampleConfigJson;


    @ApiModelProperty(value = "出参文档配置json")
    private String outParamDocConfigJson;


    @ApiModelProperty(value = "出参成功或失败配置json")
    private String outParamSuccessConfigJson;

    @ApiModelProperty(value = "出参翻译配置json")
    private String outParamTransConfigJson;

    @ApiModelProperty(value = "出参扩展配置json")
    private String outParamExtConfigJson;

    @NotNull(message = "输出类型 不能为空")
    @ApiModelProperty(value = "输出类型",required = true)
    private Long responseTypeDictId;

    @ApiModelProperty(value = "分页适配信息配置json")
    private String pageableAdapterConfigJson;

    @ApiModelProperty(value = "基础配置json")
    private String configJson;


    @ApiModelProperty(value = "字典配置json")
    private String dictConfigJson;


    @ApiModelProperty(value = "流量控制配置json")
    private String rateLimitControlConfigJson;


    @ApiModelProperty(value = "熔断控制配置json")
    private String circuitBreakerControlConfigJson;


    @ApiModelProperty(value = "读取等待时间")
    private Integer readTimeout;


    @ApiModelProperty(value = "连接等待时间")
    private Integer connectTimeout;


    @ApiModelProperty(value = "等同标签")
    private String sameTag;


    @ApiModelProperty(value = "描述")
    private String remark;

}
