package com.particle.dataquery.client.datasource.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.global.light.share.mybatis.anno.SetNullWhenNull;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 数据查询数据源接口 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Data
@Schema
public class DataQueryDatasourceApiUpdateCommand extends AbstractBaseUpdateCommand {



    @SetNullWhenNull
    @Schema(description = "编码")
    private String code;


    @NotEmpty(message = "名称 不能为空")
    @Schema(description = "名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotNull(message = "分类 不能为空")
    @Schema(description = "分类",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long categoryDictId;


    @NotNull(message = "数据查询供应商id 不能为空")
    @Schema(description = "数据查询供应商id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dataQueryProviderId;


    @Schema(description = "文档链接")
    private String dataQueryProviderDocLinkUrl;


    @NotNull(message = "数据查询数据源id 不能为空")
    @Schema(description = "数据查询数据源id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dataQueryDatasourceId;

    @SetNullWhenNull
    @Schema(description = "入参类型，字典id")
    private Long inParamTypeDictId;

    @Schema(description = "入参示例")
    private String inParamExampleConfigJson;


    @Schema(description = "入参测试用例数据")
    private String inParamTestCaseDataConfigJson;


    @Schema(description = "入参文档配置json")
    private String inParamDocConfigJson;


    @Schema(description = "入参校验配置json")
    private String inParamValidateConfigJson;


    @Schema(description = "入参扩展配置json")
    private String inParamExtConfigJson;

    @NotNull(message = "出参类型 不能为空")
    @Schema(description = "出参类型，字典id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long outParamTypeDictId;

    @Schema(description = "出参示例")
    private String outParamExampleConfigJson;


    @Schema(description = "出参文档配置json")
    private String outParamDocConfigJson;


    @Schema(description = "出参成功或失败配置json")
    private String outParamSuccessConfigJson;

    @Schema(description = "出参翻译配置json")
    private String outParamTransConfigJson;

    @Schema(description = "出参扩展配置json")
    private String outParamExtConfigJson;

    @NotNull(message = "输出类型 不能为空")
    @Schema(description = "输出类型",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long responseTypeDictId;

    @Schema(description = "分页适配信息配置json")
    private String pageableAdapterConfigJson;

    @Schema(description = "基础配置json")
    private String configJson;


    @Schema(description = "字典配置json")
    private String dictConfigJson;


    @Schema(description = "流量控制配置json")
    private String rateLimitControlConfigJson;


    @Schema(description = "熔断控制配置json")
    private String circuitBreakerControlConfigJson;


    @Schema(description = "读取等待时间")
    private Integer readTimeout;


    @Schema(description = "连接等待时间")
    private Integer connectTimeout;

	@Schema(description = "是否使用缓存")
	private Boolean isUseCache;

	@Schema(description = "缓存配置json")
	private String cacheConfigJson;


    @Schema(description = "等同标签")
    private String sameTag;

	@Schema(description = "是否支持翻译数据")
	private Boolean isSupportTrans;

	@Schema(description = "是否已发布，已发布不能修改和删除")
	private Boolean isPublished;

    @Schema(description = "描述")
    private String remark;

}