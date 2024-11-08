package com.particle.navigation.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 导航网站标签关系 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:39:14
 */
@Data
@Entity
public class NavigationSiteTagRel extends AggreateRoot {

    private NavigationSiteTagRelId id;

    /**
    * 网站id
    */
    private Long navigationSiteId;

    /**
    * 网站标签id
    */
    private Long navigationSiteTagId;



    /**
     * 创建导航网站标签关系领域模型对象
     * @return 导航网站标签关系领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static NavigationSiteTagRel create(){
        return DomainFactory.create(NavigationSiteTagRel.class);
    }
}
