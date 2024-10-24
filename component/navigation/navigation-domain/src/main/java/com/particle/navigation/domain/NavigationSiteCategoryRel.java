package com.particle.navigation.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 导航网站分类关系 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
@Data
@Entity
public class NavigationSiteCategoryRel extends AggreateRoot {

    private NavigationSiteCategoryRelId id;

    /**
    * 导航网站id
    */
    private Long navigationSiteId;

    /**
    * 导航分类id
    */
    private Long navigationCategoryId;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;



    /**
     * 创建导航网站分类关系领域模型对象
     * @return 导航网站分类关系领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static NavigationSiteCategoryRel create(){
        return DomainFactory.create(NavigationSiteCategoryRel.class);
    }
}
