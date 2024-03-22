package com.particle.openplatform.infrastructure.doc.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 开放接口文档模板表
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:39
 */
@Data
@TableName("component_openplatform_doc_api_doc_template")
public class OpenplatformDocApiDocTemplateDO extends BaseDO {

    /**
    * 模板名称
    */
    private String name;

    /**
    * 请求地址前缀，可全局配置
    */
    private String requestUrlPrefix;

    /**
    * 请求类型，字典id，如：post、get
    */
    private Long requestTypeDictId;

    /**
    * 请求体类型，字典id，如：json、xml
    */
    private Long requestBodyTypeDictId;

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


}
