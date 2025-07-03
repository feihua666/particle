package com.particle.cms.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 模板内容 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:13:35
 */
@Data
@Entity
public class CmsTemplateContent extends AggreateRoot {

    private CmsTemplateContentId id;

    /**
    * 数据id，不同模板类型，分类对象不同id
    */
    private Long dataId;

    /**
    * 模板类型，site=端点首页模板、channel=栏目模板、content=内容模板
    */
    private String typeName;

    /**
    * 模板内容
    */
    private String content;



    /**
     * 创建模板内容领域模型对象
     * @return 模板内容领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CmsTemplateContent create(){
        return DomainFactory.create(CmsTemplateContent.class);
    }
}
