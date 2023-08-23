package com.particle.openplatform.infrastructure.openapi.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 开放平台开放接口费用表
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:59:32
 */
@Data
@TableName("component_openplatform_openapi_fee")
public class OpenplatformOpenapiFeeDO extends BaseDO {

	/**
	 * 费用名称，可以理解为类似一个套餐
	 */
	private String name;

    /**
    * 单价，分/条
    */
    private Integer price;

    /**
    * 计费方式，字典id，如：按条，按月，按阶梯等
    */
    private Long feeTypeDictId;

    /**
    * 按计费方式配置
    */
    private String feeTypeJson;

    /**
    * 去重方式，字典id，如：按月去重，按周去重，不去重等
    */
    private Long deduplicateTypeDictId;

    /**
    * 去重条数，如果去重表示每多少条算一条
    */
    private Integer deduplicateCount;

    /**
    * 是否按请求参数去重，1=按参数去重，0=按接口去重
    */
    private Boolean isDeduplicateByParameter;

    /**
    * 是否检查是否返回值，1=检查，如果没有返回值不计费，0=不检查，直接计费
    */
    private Boolean isCheckHasValue;

    /**
    * 是否检查处理时长，1=检查，0=不检查
    */
    private Boolean isCheckHandleDuration;

    /**
    * 处理时长，单位毫秒，如果检查处理时长，超过该时长不计费
    */
    private Integer handleDuration;

    /**
    * 描述
    */
    private String remark;


}