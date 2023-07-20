package com.particle.dataquery.client.dataapi.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class DataQueryDataApiUpdateCommand extends AbstractBaseUpdateCommand {


    @NotEmpty(message = "接口地址 不能为空")
    @Schema(description = "接口地址",required = true)
    private String url;

    @NotEmpty(message = "接口名称 不能为空")
    @Schema(description = "接口名称",required = true)
    private String name;

    @Schema(description = "数据查询数据源接口id")
    private Long dataQueryDatasourceApiId;

    @NotNull(message = "适配类型 不能为空")
    @Schema(description = "适配类型",required = true)
    private Long adaptTypeDictId;

    @Schema(description = "接口适配配置json")
    private String adaptConfigJson;

    @Schema(description = "入参类型,非一对一直连必填")
    private Long inParamTypeDictId;

    @Schema(description = "入参示例")
    private String inParamExampleConfigJson;

    @Schema(description = "入参测试用例数据")
    private String inParamTestCaseDataConfigJson;

    @Schema(description = "入参文档配置json")
    private String inParamDocConfigJson;

    @Schema(description = "入参校验配置json")
    private String inParamValidateConfigJson;

    @Schema(description = "出参类型,非一对一直连必填")
    private Long outParamTypeDictId;

    @Schema(description = "出参示例")
    private String outParamExampleConfigJson;

    @Schema(description = "出参文档配置json")
    private String outParamDocConfigJson;

    @Schema(description = "出参成功或失败配置json")
    private String outParamSuccessConfigJson;

    @Schema(description = "输出类型,非一对一直连必填")
    private Long responseTypeDictId;

    @Schema(description = "分页适配信息配置json")
    private String pageableAdapterConfigJson;

    @Schema(description = "字典配置json")
    private String dictConfigJson;

    @Schema(description = "描述")
    private String remark;

}
