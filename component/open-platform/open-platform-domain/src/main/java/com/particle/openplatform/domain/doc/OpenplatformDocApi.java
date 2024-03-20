package com.particle.openplatform.domain.doc;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 开放接口文档接口 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
@Data
@Entity
public class OpenplatformDocApi extends AggreateRoot {

    private OpenplatformDocApiId id;

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



    /**
     * 创建开放接口文档接口领域模型对象
     * @return 开放接口文档接口领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformDocApi create(){
        return DomainFactory.create(OpenplatformDocApi.class);
    }
}