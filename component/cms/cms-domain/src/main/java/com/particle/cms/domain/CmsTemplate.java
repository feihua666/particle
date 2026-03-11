package com.particle.cms.domain;

import com.particle.cms.domain.gateway.CmsTemplateGateway;
import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import com.particle.global.tool.str.NetPathTool;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 模板 领域模型
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
@Data
@Entity
public class CmsTemplate extends AggreateRoot {

    private CmsTemplateId id;

    /**
    * 唯一键
    */
    private String templateKey;

    /**
    * 文件名，目录名
    */
    private String name;

    /**
    * 是否为目录，1=目录，0=文件
    */
    private Boolean isDirectory;

    /**
    * 模板内容
    */
    private String content;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;

    /**
    * 父级
    */
    private Long parentId;


    public void initForAdd(){
        if (isDirectory) {
            this.content = null;
        }
        updateTemplateKey();
    }

    public void initForUpdate(){
        updateTemplateKey();
    }
    public void updateTemplateKey() {
        if (parentId == null) {
            this.templateKey = name;
        }else {
            CmsTemplate parentCmsTemplate = cmsTemplateGateway.getById(CmsTemplateId.of(parentId));
            this.templateKey = NetPathTool.concat(parentCmsTemplate.getTemplateKey(), name);
        }
    }

    /**
     * 创建模板领域模型对象
     * @return 模板领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CmsTemplate create(){
        return DomainFactory.create(CmsTemplate.class);
    }


    private CmsTemplateGateway cmsTemplateGateway;

    @Autowired
    public void setCmsTemplateGateway(CmsTemplateGateway cmsTemplateGateway) {
        this.cmsTemplateGateway = cmsTemplateGateway;
    }
}
