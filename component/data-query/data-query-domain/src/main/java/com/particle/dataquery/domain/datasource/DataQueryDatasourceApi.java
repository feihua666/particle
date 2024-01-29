package com.particle.dataquery.domain.datasource;

import cn.hutool.core.util.StrUtil;
import com.particle.common.domain.AggreateRoot;
import com.particle.dataquery.domain.dataapi.value.DataQueryDataApiCustomScriptAdaptConfig;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceType;
import com.particle.dataquery.domain.datasource.value.*;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import javax.script.ScriptException;
import java.util.List;

/**
 * <p>
 * 数据查询数据源接口 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Data
@Entity
public class DataQueryDatasourceApi extends AggreateRoot {

    private DataQueryDatasourceApiId id;

    /**
    * 编码
    */
    private String code;

    /**
    * 名称
    */
    private String name;

    /**
    * 分类，字典id，一般仅用来分类
    */
    private Long categoryDictId;

    /**
    * 数据查询供应商id,服务接口必填
    */
    private Long dataQueryProviderId;

    /**
    * 文档链接,应该填写供应商提供的链接，以查阅相关文档，一般以http(s)开头
    */
    private String dataQueryProviderDocLinkUrl;

    /**
    * 数据查询数据源id,服务接口必填
    */
    private Long dataQueryDatasourceId;
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
    * 类型，字典id，用来定义响应数据格式
    */
    private Long responseTypeDictId;
    /**
     * 分页适配信息配置json
     */
    private String pageableAdapterConfigJson;
    /**
    * 基础配置json，根据数据源类型对应不同的配置信息
    */
    private String configJson;

    /**
    * 字典配置json，为入参和出参字典提供支持
    */
    private String dictConfigJson;

    /**
    * 流量控制配置json
    */
    private String rateLimitControlConfigJson;

    /**
    * 熔断控制配置json
    */
    private String circuitBreakerControlConfigJson;

    /**
    * 读取等待时间，单位ms，超过该时间将会放弃
    */
    private Integer readTimeout;

    /**
    * 连接等待时间，单位ms，超过该时间将会放弃
    */
    private Integer connectTimeout;

	/**
	 * 是否使用缓存
	 */
	private Boolean isUseCache;

    /**
    * 等同标签，如果两个api的入参和出参相同，对接口打一个标签，同时另一个相同的接口打同样的标签，以代表两个接口相同
    */
    private String sameTag;

	/**
	 * 是否支持翻译数据
	 */
	private Boolean isSupportTrans;

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
        this.isTestPassed = false;
    }

    /**
     * 修改为测试通过
     */
    public void changeTestPassed() {
        this.isTestPassed = true;
    }

    /**
     * 修改为未测试通过
     */
    public void changeTestNotPassed() {
        this.isTestPassed = false;
    }

    /**
     * 变更id
     * @param id
     */
    public void changeIdTo(DataQueryDatasourceApiId id) {
        this.id = id;
    }

    /**
     * dev合并到本master时，处理一些数据
     * @param code
     * @param name
     */
    public void devMergeToMaster(String code, String name) {
        String suffix = "__dev";
        if (StrUtil.endWith(code, suffix)) {
            this.code = StrUtil.strip(code, null, suffix);
        }else {
            this.code = null;
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
     * 在调用之前确保对应的数据源类型为 {@link DataQueryDatasourceType#datasource_jdbc}
     * @return
     */
    public DataQueryDatasourceApiJdbcBasicConfig jdbcBasicConfig(){
        DataQueryDatasourceApiJdbcBasicConfig fromJsonStr = DataQueryDatasourceApiJdbcBasicConfig.createFromJsonStr(configJson);
        return fromJsonStr;
    }

    public DataQueryDatasourceApiHttpBasicConfig httpBasicConfig() {
        DataQueryDatasourceApiHttpBasicConfig fromJsonStr = DataQueryDatasourceApiHttpBasicConfig.createFromJsonStr(configJson);
        return fromJsonStr;
    }
    public DataQueryDatasourceApiNeo4jBasicConfig neo4jBasicConfig() {
        DataQueryDatasourceApiNeo4jBasicConfig fromJsonStr = DataQueryDatasourceApiNeo4jBasicConfig.createFromJsonStr(configJson);
        return fromJsonStr;
    }
    public DataQueryDatasourceApiEsBasicConfig esBasicConfig() {
        DataQueryDatasourceApiEsBasicConfig fromJsonStr = DataQueryDatasourceApiEsBasicConfig.createFromJsonStr(configJson);
        return fromJsonStr;
    }
    /**
     * 除了数据源类型为 {@link DataQueryDatasourceType#datasource_jdbc}时可以不用配置（大数据源已经兼容），其它情形一般都需要配置
     * @return
     */
    public DataQueryDatasourceApiPageableAdapterConfig pageableAdapterConfig() {
        if (StrUtil.isEmpty(pageableAdapterConfigJson)) {
            return null;
        }
        DataQueryDatasourceApiPageableAdapterConfig fromJsonStr = DataQueryDatasourceApiPageableAdapterConfig.createFromJsonStr(pageableAdapterConfigJson);
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
     * 经量级预热，主要是编译脚本
     */
    public void warmUpLight() {
        // 基本配置
        // jdbc基本配置
        DataQueryDatasourceApiJdbcBasicConfig dataQueryDatasourceApiJdbcBasicConfig = jdbcBasicConfig();
        if (dataQueryDatasourceApiJdbcBasicConfig != null) {
            try {
                dataQueryDatasourceApiJdbcBasicConfig.warmUpLight();
            } catch (ScriptException e) {

            }
        }
        // http基本配置
        DataQueryDatasourceApiHttpBasicConfig dataQueryDatasourceApiHttpBasicConfig = httpBasicConfig();
        if (dataQueryDatasourceApiHttpBasicConfig != null) {
            try {
                dataQueryDatasourceApiHttpBasicConfig.warmUpLight();
            } catch (ScriptException e) {

            }
        }
        // neo4j基本配置
        DataQueryDatasourceApiNeo4jBasicConfig dataQueryDatasourceApiNeo4jBasicConfig = neo4jBasicConfig();
        if (dataQueryDatasourceApiNeo4jBasicConfig != null) {
            try {
                dataQueryDatasourceApiNeo4jBasicConfig.warmUpLight();
            } catch (ScriptException e) {

            }
            try {
                dataQueryDatasourceApiNeo4jBasicConfig.warmUpLightForCount();
            } catch (ScriptException e) {

            }
        }
        // es基本配置
        DataQueryDatasourceApiEsBasicConfig dataQueryDatasourceApiEsBasicConfig = esBasicConfig();
        if (dataQueryDatasourceApiEsBasicConfig != null) {
            try {
                dataQueryDatasourceApiEsBasicConfig.warmUpLight();
            } catch (ScriptException e) {

            }
            try {
                dataQueryDatasourceApiEsBasicConfig.warmUpLightForCount();
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
     * 创建数据查询数据源接口领域模型对象
     * @return 数据查询数据源接口领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataQueryDatasourceApi create(){
        return DomainFactory.create(DataQueryDatasourceApi.class);
    }
}