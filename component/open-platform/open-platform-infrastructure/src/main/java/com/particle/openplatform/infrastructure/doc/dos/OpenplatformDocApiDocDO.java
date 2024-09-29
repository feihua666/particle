package com.particle.openplatform.infrastructure.doc.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 开放接口文档表
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:37
 */
@Data
@TableName("component_openplatform_doc_api_doc")
public class OpenplatformDocApiDocDO extends BaseDO {

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


}