package com.particle.openplatform.infrastructure.doc.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 开放接口文档响应码表
 * </p>
 *
 * @author yw
 * @since 2024-03-18 14:44:43
 */
@Data
@TableName("component_openplatform_doc_api_doc_response_code")
public class OpenplatformDocApiDocResponseCodeDO extends BaseDO {

    /**
    * 编码，码值
    */
    private String code;

	/**
	 * 业务状态码，码值
	 */
	private String codeStatus;

	/**
	 * http响应码,如：200、500
	 */
	private Integer httpCode;

    /**
    * 是否计费
    */
    private Boolean isCharge;

    /**
    * 字段说明
    */
    private String explanation;

    /**
    * 开放接口文档接口id
    */
    private Long openplatformDocApiId;

    /**
    * 开放接口文档id
    */
    private Long openplatformDocApiDocId;

	/**
	 * 排序,默认按该字段升序排序
	 */
	private Integer seq;

    /**
    * 是否全局通用码
    */
    private Boolean isGlobal;

    /**
    * 描述
    */
    private String remark;


}