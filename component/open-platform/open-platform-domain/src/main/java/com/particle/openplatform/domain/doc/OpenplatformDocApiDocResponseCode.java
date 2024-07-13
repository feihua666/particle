package com.particle.openplatform.domain.doc;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 开放接口文档响应码 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-03-18 14:44:43
 */
@Data
@Entity
public class OpenplatformDocApiDocResponseCode extends AggreateRoot {

    private OpenplatformDocApiDocResponseCodeId id;

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

    public void changeOpenplatformDocApiId(Long openplatformDocApiId) {
        this.openplatformDocApiId = openplatformDocApiId;
    }


    /**
     * 创建开放接口文档响应码领域模型对象
     * @return 开放接口文档响应码领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformDocApiDocResponseCode create(){
        return DomainFactory.create(OpenplatformDocApiDocResponseCode.class);
    }
}