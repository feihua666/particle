package com.particle.navigation.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 导航网站标签 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:38:23
 */
@Data
@Entity
public class NavigationSiteTag extends AggreateRoot {

    private NavigationSiteTagId id;

    /**
    * 标签编码
    */
    private String code;

    /**
    * 标签名称
    */
    private String name;

    /**
    * 分组，字典id
    */
    private Long groupDictId;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;

    /**
    * 备注
    */
    private String remark;



    /**
     * 创建导航网站标签领域模型对象
     * @return 导航网站标签领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static NavigationSiteTag create(){
        return DomainFactory.create(NavigationSiteTag.class);
    }
}
