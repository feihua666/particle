package com.particle.openplatform.infrastructure.doc.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 开放接口文档参数字段表
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:55
 */
@Data
@TableName("component_openplatform_doc_api_doc_param_field")
public class OpenplatformDocApiDocParamFieldDO extends BaseTreeDO {

    /**
    * 字段名称，一般为英文
    */
    private String name;

    /**
    * 字段类型，字典id，如：string、array
    */
    private Long typeDictId;

	/**
	 * 嵌套字段类型，字典id，一般用于字段类型为array时里面的类型，如：string、object
	 */
	private Long nestTypeDictId;

    /**
    * 是否一定有值
    */
    private Boolean isRequired;

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
    * 分类，字典id，如：header、request_param等
    */
    private Long categoryDictId;

	/**
	 * 默认值
	 */
	private String defaultValue;

	/**
	 * 最大长度
	 */
	private String exampleValue;

	/**
	 * 最大长度
	 */
	private Integer maxLength;

	/**
	 * 字典组字典，字典组id，字典组下面的字典项为字段枚举
	 */
	private Long dictGroupDictId;

	/**
	 * 字典项标签，如果某一个字典组下的字典项过多可以根据标签过滤
	 */
	private String dictItemTags;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;


}