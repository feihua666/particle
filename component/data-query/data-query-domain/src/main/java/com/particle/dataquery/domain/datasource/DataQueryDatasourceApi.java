package com.particle.dataquery.domain.datasource;

import cn.hutool.core.util.StrUtil;
import com.particle.common.domain.AggreateRoot;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceType;
import com.particle.dataquery.domain.datasource.value.DataQueryDatasourceApiInParamValidateConfig;
import com.particle.dataquery.domain.datasource.value.DataQueryDatasourceApiInSuccessValidateConfig;
import com.particle.dataquery.domain.datasource.value.DataQueryDatasourceApiJdbcBasicConfig;
import com.particle.dataquery.domain.datasource.value.DataQueryDatasourceApiPageableAdapterConfig;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
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
    * 等同标签，如果两个api的入参和出参相同，对接口打一个标签，同时另一个相同的接口打同样的标签，以代表两个接口相同
    */
    private String sameTag;

    /**
    * 描述,注意事项等
    */
    private String remark;


    /**
     * 在调用之前确保对应的数据源类型为 {@link DataQueryDatasourceType#datasource_jdbc}
     * @return
     */
    public DataQueryDatasourceApiJdbcBasicConfig jdbcBasicConfig(){
        DataQueryDatasourceApiJdbcBasicConfig fromJsonStr = DataQueryDatasourceApiJdbcBasicConfig.createFromJsonStr(configJson);
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
     * 创建数据查询数据源接口领域模型对象
     * @return 数据查询数据源接口领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataQueryDatasourceApi create(){
        return DomainFactory.create(DataQueryDatasourceApi.class);
    }
}
