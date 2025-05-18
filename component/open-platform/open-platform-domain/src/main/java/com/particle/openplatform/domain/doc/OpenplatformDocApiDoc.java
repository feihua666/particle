package com.particle.openplatform.domain.doc;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 开放接口文档 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:37
 */
@Data
@Entity
public class OpenplatformDocApiDoc extends AggreateRoot {


    private OpenplatformDocApiDocId id;

    /**
     * 开放接口文档接口id
     */
    private Long openplatformDocApiId;

    /**
    * 请求地址前缀，可全局配置
    */
    private String requestUrlPrefix;

	/**
	 * 内网请求地址前缀，可全局配置
	 */
	private String requestUrlIntranetPrefix;

    /**
    * 请求地址
    */
    private String requestUrl;

    /**
    * 请求类型，字典id，如：post、get
    */
    private Long requestTypeDictId;

    /**
    * 请求体类型，字典id，如：json、xml
    */
    private Long requestBodyTypeDictId;

	/**
	 * 请求参数类型，字典id，如：string、array
	 */
	private Long requestParamTypeDictId;

	/**
	 * 请求参数嵌套字段类型，字典id，一般用于字段类型为array时里面的类型，如：string、object
	 */
	private Long requestParamNestTypeDictId;

    /**
    * 响应体类型，字典id，如：json、xml
    */
    private Long responseBodyTypeDictId;

    /**
    * 最大响应时长，单位ms
    */
    private Integer responseMaxDuration;

    /**
    * 响应时长文本，response_max_duration不支持时作为备用
    */
    private String responseMaxDurationDesc;

    /**
    * 认证方式
    */
    private String authenticationType;

    /**
    * 详细描述，详细描述文档内容或返回参数信息
    */
    private String descriptionDetail;

    /**
    * 请求参数示例
    */
    private String requestParamExample;

    /**
    * 响应参数示例
    */
    private String responseParamExample;

	/**
	 * 开放接口文档模板id
	 */
	private Long openplatformDocApiDocTemplateId;


    public void changeOpenplatformDocApiId(Long openplatformDocApiId) {
        this.openplatformDocApiId = openplatformDocApiId;
    }

    public void changeRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void changeRequestParamExample(String requestParamExample) {
        this.requestParamExample = requestParamExample;
    }
    public void changeResponseParamExample(String responseParamExample) {
        this.responseParamExample = responseParamExample;
    }
    public void changeOpenplatformDocApiDocTemplateId(Long openplatformDocApiDocTemplateId) {
        this.openplatformDocApiDocTemplateId = openplatformDocApiDocTemplateId;
    }

    /**
     * 创建开放接口文档领域模型对象
     * @return 开放接口文档领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformDocApiDoc create(){
        return DomainFactory.create(OpenplatformDocApiDoc.class);
    }
}
