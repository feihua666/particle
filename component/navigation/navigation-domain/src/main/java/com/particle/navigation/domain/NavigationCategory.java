package com.particle.navigation.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 导航分类 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
@Data
@Entity
public class NavigationCategory extends AggreateRoot {

    private NavigationCategoryId id;

    /**
    * 编码
    */
    private String code;

    /**
    * 名称
    */
    private String name;

    /**
    * 图标
    */
    private String icon;

    /**
    * 描述
    */
    private String remark;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;

    /**
    * 父级
    */
    private Long parentId;



    /**
     * 创建导航分类领域模型对象
     * @return 导航分类领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static NavigationCategory create(){
        return DomainFactory.create(NavigationCategory.class);
    }
}
