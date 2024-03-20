package com.particle.openplatform.domain.doc;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 开放接口文档模板示例代码 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:26
 */
@Data
@Entity
public class OpenplatformDocApiDocTemplateExampleCode extends AggreateRoot {

    private OpenplatformDocApiDocTemplateExampleCodeId id;

    /**
    * 开发语言，字典id
    */
    private Long langDictId;

    /**
    * 示例代码片段
    */
    private String exampleCode;

    /**
    * 示例代码下载地址
    */
    private String exampleDownloadUrl;

    /**
    * 开放接口文档模板id
    */
    private Long openplatformDocApiDocTemplateId;

	/**
	 * 排序,默认按该字段升序排序
	 */
	private Integer seq;

    /**
    * 描述
    */
    private String remark;



    /**
     * 创建开放接口文档模板示例代码领域模型对象
     * @return 开放接口文档模板示例代码领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformDocApiDocTemplateExampleCode create(){
        return DomainFactory.create(OpenplatformDocApiDocTemplateExampleCode.class);
    }
}