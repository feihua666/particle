package com.particle.openplatform.domain.doc;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 开放接口文档模板参数字段 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:56
 */
@Data
@Entity
public class OpenplatformDocApiDocTemplateParamField extends AggreateRoot {

    private OpenplatformDocApiDocTemplateParamFieldId id;

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
    * 开放接口文档模板id
    */
    private Long openplatformDocApiDocTemplateId;

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

    /**
    * 父级
    */
    private Long parentId;


    public void changeDictGroupDictId(Long dictGroupDictId) {
        this.dictGroupDictId = dictGroupDictId;
    }

    /**
     * 创建开放接口文档模板参数字段领域模型对象
     * @return 开放接口文档模板参数字段领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformDocApiDocTemplateParamField create(){
        return DomainFactory.create(OpenplatformDocApiDocTemplateParamField.class);
    }
}