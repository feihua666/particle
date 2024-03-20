package com.particle.openplatform.infrastructure.doc.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 开放接口文档接口表
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
@Data
@TableName("component_openplatform_doc_api")
public class OpenplatformDocApiDO extends BaseDO {

    /**
    * 编码，唯一
    */
    private String code;

    /**
    * 名称
    */
    private String name;

    /**
    * 简称
    */
    private String nameSimple;

	/**
	 * 图标地址
	 */
	private String logoUrl;

    /**
    * 每次价格，单位元
    */
    private Double pricePerTime;

    /**
    * 价格文本，price_per_time不支持时可以作为备用
    */
    private String pricePerTimeDesc;

    /**
    * 描述
    */
    private String description;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;


}