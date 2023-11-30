package com.particle.dataquery.domain.dataapi;

import cn.hutool.core.util.StrUtil;
import com.particle.common.domain.AggreateRoot;
import com.particle.dataquery.domain.dataapi.enums.DataQueryDataApiAdaptType;
import com.particle.dataquery.domain.dataapi.value.DataQueryDataApiCustomScriptAdaptConfig;
import com.particle.dataquery.domain.dataapi.value.DataQueryDataApiMultipleAggregationAdaptConfig;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceType;
import com.particle.dataquery.domain.datasource.value.DataQueryDatasourceApiInParamValidateConfig;
import com.particle.dataquery.domain.datasource.value.DataQueryDatasourceApiInSuccessValidateConfig;
import com.particle.dataquery.domain.datasource.value.DataQueryDatasourceApiPageableAdapterConfig;
import com.particle.dataquery.domain.gateway.DataQueryDictGateway;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import com.particle.global.exception.Assert;
import com.particle.global.tool.json.JsonTool;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * <p>
 * 数据查询数据接口 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Data
@Entity
public class DataQueryDataApi extends AggreateRoot {

    private DataQueryDataApiId id;

    /**
    * 接口地址
    */
    private String url;

    /**
    * 接口名称
    */
    private String name;

    /**
    * 数据查询数据源接口id，接口直连时使用
    */
    private Long dataQueryDatasourceApiId;

    /**
    * 适配类型，字典id，直连、聚合等
    */
    private Long adaptTypeDictId;

    /**
    * 接口适配配置json
    */
    private String adaptConfigJson;

    /**
    * 入参类型，字典id，为空表示无入参
    */
    private Long inParamTypeDictId;

    /**
    * 入参示例，纯文本展示
    */
    private String inParamExampleConfigJson;

    /**
    * 入参测试用例数据，该数据应该配合入参配置json或json配置识别
    */
    private String inParamTestCaseDataConfigJson;

    /**
    * 入参文档配置json
    */
    private String inParamDocConfigJson;
    /**
     * 入参校验配置json
     */
    private String inParamValidateConfigJson;
    /**
    * 出参类型，字典id
    */
    private Long outParamTypeDictId;

    /**
    * 出参示例，纯文本展示
    */
    private String outParamExampleConfigJson;

    /**
    * 出参文档配置json
    */
    private String outParamDocConfigJson;

    /**
    * 出参成功或失败配置json
    */
    private String outParamSuccessConfigJson;

    /**
    * 输出类型，字典id，用来定义响应数据格式
    */
    private Long responseTypeDictId;

    /**
    * 分页适配信息配置json
    */
    private String pageableAdapterConfigJson;

    /**
    * 字典配置json，为入参和出参字典提供支持
    */
    private String dictConfigJson;

	/**
	 * 是否使用缓存
	 */
	private Boolean isUseRemote;

    /**
    * 描述,注意事项等
    */
    private String remark;

    /**
     * 调用该方法，确保 {@link DataQueryDataApi#adaptTypeDictId} 为多接口聚合类型
     * 注意：{@link DataQueryDataApiMultipleAggregationAdaptConfig.AggregationItem#dataQueryDatasourceApiId} 类型为 Long，但{@link DataQueryDataApi#adaptConfigJson}中数据为字符串
     * json字符串转对象能自动转换类型，千万不要使用 {@link DataQueryDataApiMultipleAggregationAdaptConfig}对象转字符串保存到数据库中，否则前端解析时会丢失精度，前端没有Long类型
     * @return
     */
    public DataQueryDataApiMultipleAggregationAdaptConfig multipleAggregationAdaptConfig(){

        if (StrUtil.isEmpty(adaptConfigJson)) {
            return null;
        }
        DataQueryDataApiMultipleAggregationAdaptConfig fromJsonStr = DataQueryDataApiMultipleAggregationAdaptConfig.createFromJsonStr(adaptConfigJson);
        return fromJsonStr;
    }

    /**
     * 调用该方法，确保 {@link DataQueryDataApi#adaptTypeDictId} 为多自定义脚本类型
     * @return
     */
    public DataQueryDataApiCustomScriptAdaptConfig customScriptAdaptConfig(){
        if (StrUtil.isEmpty(adaptConfigJson)) {
            return null;
        }
        DataQueryDataApiCustomScriptAdaptConfig fromJsonStr = DataQueryDataApiCustomScriptAdaptConfig.createFromJsonStr(adaptConfigJson);
        return fromJsonStr;
    }


    public DataQueryDatasourceApiPageableAdapterConfig pageableAdapterConfig() {
        if (StrUtil.isEmpty(pageableAdapterConfigJson)) {
            return null;
        }
        DataQueryDatasourceApiPageableAdapterConfig fromJsonStr = DataQueryDatasourceApiPageableAdapterConfig.createFromJsonStr(pageableAdapterConfigJson);
        return fromJsonStr;
    }

    /**
     * 入参校验配置
     * @return
     */
    public DataQueryDatasourceApiInParamValidateConfig inParamValidateConfig(){
        if (StrUtil.isEmpty(inParamValidateConfigJson)) {
            return null;
        }
        DataQueryDatasourceApiInParamValidateConfig fromJsonStr = DataQueryDatasourceApiInParamValidateConfig.createFromJsonStr(inParamValidateConfigJson);
        return fromJsonStr;
    }

    public DataQueryDatasourceApiInSuccessValidateConfig outParamSuccessConfigJson(){
        if (StrUtil.isEmpty(outParamSuccessConfigJson)) {
            return null;
        }
        DataQueryDatasourceApiInSuccessValidateConfig fromJsonStr = DataQueryDatasourceApiInSuccessValidateConfig.createFromJsonStr(outParamSuccessConfigJson);
        return fromJsonStr;
    }

    /**
     * 在创建时校验参数
     */
    public void validateOnCreate(){
        String dictValueById = dataQueryDictGateway.getDictValueById(adaptTypeDictId);
        DataQueryDataApiAdaptType dataQueryDataApiAdaptType = DataQueryDataApiAdaptType.valueOf(dictValueById);
        if (DataQueryDataApiAdaptType.single_direct == dataQueryDataApiAdaptType) {
            Assert.notNull(dataQueryDatasourceApiId,"数据源接口id不能为空");
        }

        if (DataQueryDataApiAdaptType.single_direct != dataQueryDataApiAdaptType) {
            Assert.notNull(inParamTypeDictId,"入参类型不能为空");
            Assert.notNull(outParamTypeDictId,"出参类型不能为空");
            Assert.notNull(responseTypeDictId,"输出类型不能为空");
            Assert.isTrue(StrUtil.isNotEmpty(adaptConfigJson),"接口适配配置不能为空");
        }
    }

    /**
     * 在全字典更新时验证
     */
    public void validateOnFullUpdate() {
        validateOnCreate();
    }

    private DataQueryDictGateway dataQueryDictGateway;

    @Autowired
    public void setDataQueryDictGateway(DataQueryDictGateway dataQueryDictGateway) {
        this.dataQueryDictGateway = dataQueryDictGateway;
    }

    /**
     * 创建数据查询数据接口领域模型对象
     * @return 数据查询数据接口领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataQueryDataApi create(){
        return DomainFactory.create(DataQueryDataApi.class);
    }
}