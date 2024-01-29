package com.particle.dataquery.domain.dataapi;

import cn.hutool.core.util.StrUtil;
import com.particle.common.domain.AggreateRoot;
import com.particle.dataquery.domain.dataapi.enums.DataQueryDataApiAdaptType;
import com.particle.dataquery.domain.dataapi.value.DataQueryDataApiCustomScriptAdaptConfig;
import com.particle.dataquery.domain.dataapi.value.DataQueryDataApiMultipleAggregationAdaptConfig;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceType;
import com.particle.dataquery.domain.datasource.value.*;
import com.particle.dataquery.domain.gateway.DataQueryDictGateway;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import com.particle.global.exception.Assert;
import com.particle.global.tool.json.JsonTool;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;

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
	 * 入参扩展配置json
	 */
	private String inParamExtConfigJson;
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
	 * 出参翻译配置json
	 */
	private String outParamTransConfigJson;

	/**
	 * 出参扩展配置json
	 */
	private String outParamExtConfigJson;

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
	 * 是否使用远程服务
	 */
	private Boolean isUseRemote;

	/**
	 * 是否已发布，已发布不能修改和删除
	 */
	private Boolean isPublished;

	/**
	 * 是否为主版本，非主版本视为开发版本
	 */
	private Boolean isMaster;

	/**
	 * 主版本id
	 */
	private Long masterId;

	/**
	 * 是否测试通过，测试通过才能发布
	 */
	private Boolean isTestPassed;

    /**
    * 描述,注意事项等
    */
    private String remark;

    /**
     * 开发配置相关
     * 在添加时使用，
     */
    public void initForAdd() {
        this.isPublished = false;
        this.isMaster = true;
        this.masterId = null;
        changeTestNotPassed();
    }

    /**
     * 设置为测试通过
     */
    public void changeTestPassed() {
        this.isTestPassed = true;
    }

    /**
     * 设置为未测试通过
     */
    public void changeTestNotPassed() {
        this.isTestPassed = false;
    }

    /**
     * 变更id
     * @param id
     */
    public void changeIdTo(DataQueryDataApiId id) {
        this.id = id;
    }
    /**
     * dev合并到本master时，处理一些数据
     * @param url
     * @param name
     */
    public void devMergeToMaster(String url, String name) {
        String suffix = "__dev";
        if (StrUtil.endWith(url, suffix)) {
            this.url = StrUtil.strip(url, null, suffix);
        }else {
            this.url = null;
        }
        if (StrUtil.endWith(name, suffix)) {
            this.name = StrUtil.strip(name, null, suffix);
        }else {
            this.name = null;
        }
        this.isPublished = null;
        this.isMaster = null;
        this.masterId = null;
        this.isTestPassed = null;
    }
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
    /**
     * 入参扩展配置
     * @return
     */
    public DataQueryDatasourceApiInParamExtConfig inParamExtConfig(){
        if (StrUtil.isEmpty(inParamExtConfigJson)) {
            return null;
        }
        DataQueryDatasourceApiInParamExtConfig fromJsonStr = DataQueryDatasourceApiInParamExtConfig.createFromJsonStr(inParamExtConfigJson);
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
     * 出参翻译配置
     * @return
     */
    public DataQueryDatasourceApiTransConfig outParamTransConfig() {
        if (StrUtil.isEmpty(outParamTransConfigJson)) {
            return null;
        }
        return DataQueryDatasourceApiTransConfig.createFromJsonStr(outParamTransConfigJson);
    }

    /**
     * 字典配置
     * @return
     */
    public DataQueryDatasourceApiDictConfig dictConfig() {
        if (StrUtil.isEmpty(dictConfigJson)) {
            return null;
        }
        return DataQueryDatasourceApiDictConfig.createFromJsonStr(dictConfigJson);
    }
    /**
     * 出参扩展配置
     * @return
     */
    public DataQueryDatasourceApiOutParamExtConfig outParamExtConfig(){
        if (StrUtil.isEmpty(outParamExtConfigJson)) {
            return null;
        }
        DataQueryDatasourceApiOutParamExtConfig fromJsonStr = DataQueryDatasourceApiOutParamExtConfig.createFromJsonStr(outParamExtConfigJson);
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
     * 经量级预热，主要是编译脚本
     */
    public void warmUpLight() {
        // 自定义脚本，适配类型
        DataQueryDataApiCustomScriptAdaptConfig dataQueryDataApiCustomScriptAdaptConfig = customScriptAdaptConfig();
        if (dataQueryDataApiCustomScriptAdaptConfig != null) {
            try {
                dataQueryDataApiCustomScriptAdaptConfig.warmUpLight();
            } catch (ScriptException e) {
            }

        }
        // 入参校验
        DataQueryDatasourceApiInParamValidateConfig dataQueryDatasourceApiInParamValidateConfig = inParamValidateConfig();
        if (dataQueryDatasourceApiInParamValidateConfig != null) {
            List<DataQueryDatasourceApiInParamValidateConfig.ApiValidateItem> inParamValidateItems = dataQueryDatasourceApiInParamValidateConfig.getInParamValidateItems();
            if (inParamValidateItems != null) {
                for (DataQueryDatasourceApiInParamValidateConfig.ApiValidateItem inParamValidateItem : inParamValidateItems) {
                    try {
                        inParamValidateItem.warmUpLight();
                    } catch (ScriptException e) {
                    }
                }
            }
        }

        // 入参扩展配置
        DataQueryDatasourceApiInParamExtConfig dataQueryDatasourceApiInParamExtConfig = inParamExtConfig();
        if (dataQueryDatasourceApiInParamExtConfig != null) {
            try {
                dataQueryDatasourceApiInParamExtConfig.warmUpLight();
            } catch (ScriptException e) {
            }
        }

        // 出参成功校验配置
        DataQueryDatasourceApiInSuccessValidateConfig dataQueryDatasourceApiInSuccessValidateConfig = outParamSuccessConfigJson();
        if (dataQueryDatasourceApiInSuccessValidateConfig != null) {
            List<DataQueryDatasourceApiInSuccessValidateConfig.ApiValidateItem> outParamValidateItems = dataQueryDatasourceApiInSuccessValidateConfig.getOutParamValidateItems();
            if (outParamValidateItems != null) {
                for (DataQueryDatasourceApiInSuccessValidateConfig.ApiValidateItem outParamValidateItem : outParamValidateItems) {
                    try {
                        outParamValidateItem.warmUpLight();
                    } catch (ScriptException e) {
                    }
                }
            }

        }
        // 出参扩展配置
        DataQueryDatasourceApiOutParamExtConfig dataQueryDatasourceApiOutParamExtConfig = outParamExtConfig();
        if (dataQueryDatasourceApiOutParamExtConfig != null) {
            try {
                dataQueryDatasourceApiOutParamExtConfig.warmUpLight();
            } catch (ScriptException e) {
            }
        }

        // 分页信息配置
        DataQueryDatasourceApiPageableAdapterConfig dataQueryDatasourceApiPageableAdapterConfig = pageableAdapterConfig();
        if (dataQueryDatasourceApiPageableAdapterConfig != null) {
            try {
                dataQueryDatasourceApiPageableAdapterConfig.warmUpLightInParam();
            } catch (ScriptException e) {
            }
            try {
                dataQueryDatasourceApiPageableAdapterConfig.warmUpLightOutParam();
            } catch (ScriptException e) {
            }
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