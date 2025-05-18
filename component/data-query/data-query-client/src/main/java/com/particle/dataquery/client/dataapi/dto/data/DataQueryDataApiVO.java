package com.particle.dataquery.client.dataapi.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 数据查询数据接口 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Data
@Schema
public class DataQueryDataApiVO extends AbstractBaseIdVO {

    @Schema(description = "接口地址")
    private String url;

    @Schema(description = "接口名称")
    private String name;

    @Schema(description = "数据查询数据源接口id")
    private Long dataQueryDatasourceApiId;

    @TransBy(tableName = TransTableNameConstants.component_data_query_datasource_api, byFieldName = "dataQueryDatasourceApiId", mapValueField = "name")
    @Schema(description = "数据查询数据源接口名称")
    private String dataQueryDatasourceApiName;

    @Schema(description = "适配类型")
    private Long adaptTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "adaptTypeDictId",mapValueField = "name")
    @Schema(description = "适配类型对应字典名称")
    private String adaptTypeDictName;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "adaptTypeDictId",mapValueField = "value")
    @Schema(description = "适配类型对应字典值")
    private String adaptTypeDictValue;

    @Schema(description = "接口适配配置json")
    private String adaptConfigJson;

    @Schema(description = "入参类型")
    private Long inParamTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "inParamTypeDictId",mapValueField = "name")
    @Schema(description = "入参类型对应字典名称")
    private String inParamTypeDictName;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "inParamTypeDictId",mapValueField = "value")
    @Schema(description = "入参类型字典值")
    private String inParamTypeDictValue;

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

    @Schema(description = "出参类型")
    private Long outParamTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "outParamTypeDictId",mapValueField = "name")
    @Schema(description = "出参类型对应字典名称")
    private String outParamTypeDictName;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "outParamTypeDictId",mapValueField = "value")
    @Schema(description = "出参类型字典值")
    private String outParamTypeDictValue;

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

    @Schema(description = "输出类型")
    private Long responseTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "responseTypeDictId",mapValueField = "name")
    @Schema(description = "输出类型对应字典名称")
    private String responseTypeDictName;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "responseTypeDictId",mapValueField = "value")
    @Schema(description = "输出类型对应字典值")
    private String responseTypeDictValue;

    @Schema(description = "分页适配信息配置json")
    private String pageableAdapterConfigJson;

    @Schema(description = "字典配置json")
    private String dictConfigJson;

	@Schema(description = "是否使用远程服务")
	private Boolean isUseRemote;

	@Schema(description = "是否已发布，已发布不能修改和删除")
	private Boolean isPublished;

	@Schema(description = "是否为主版本，非主版本视为开发版本")
	private Boolean isMaster;

	@Schema(description = "主版本id")
	private Long masterId;

    @TransBy(tableName = TransTableNameConstants.component_data_query_data_api, byFieldName = "masterId", mapValueField = "name")
    @Schema(description = "主版本名称")
    private String masterName;

	@Schema(description = "是否测试通过，测试通过才能发布")
	private Boolean isTestPassed;

	@Schema(description = "数据查询供应商id,用于支持开放接口")
	private Long dataQueryProviderId;

	@Schema(description = "接口标识，一般与url对等,用于支持开放接口")
	private String apiIdentifier;

	@Schema(description = "接口版本标识，标识同一个接口的不同版本,用于支持开放接口")
	private String apiVersion;

	@Schema(description = "是否支持入库,用于支持开放接口")
	private Boolean isSupportWarehouse;

    @Schema(description = "描述")
    private String remark;



}