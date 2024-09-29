package com.particle.openplatform.domain.doc;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 开放接口文档模板 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:39
 */
@Data
@Entity
public class OpenplatformDocApiDocTemplate extends AggreateRoot {

    private OpenplatformDocApiDocTemplateId id;

    /**
    * 模板名称
    */
    private String name;

    /**
    * 请求地址前缀，可全局配置
    */
    private String requestUrlPrefix;

	/**
	 * 内网请求地址前缀，可全局配置
	 */
	private String requestUrlIntranetPrefix;

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
     * 创建开放接口文档模板领域模型对象
     * @return 开放接口文档模板领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformDocApiDocTemplate create(){
        return DomainFactory.create(OpenplatformDocApiDocTemplate.class);
    }
}