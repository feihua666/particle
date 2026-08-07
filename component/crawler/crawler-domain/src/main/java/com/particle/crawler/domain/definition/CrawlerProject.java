package com.particle.crawler.domain.definition;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 爬虫项目 领域模型
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:21
 */
@Data
@Entity
public class CrawlerProject extends AggreateRoot {

    private CrawlerProjectId id;

    /**
    * 项目名称
    */
    private String name;

    /**
    * 配置参数json
    */
    private String configJson;

    /**
    * 归属用户id
    */
    private Long userId;

    /**
    * 是否公开，1=是，所有人可见，0=否，用户自己可见
    */
    private Boolean isPublic;

    /**
    * 描述
    */
    private String remark;


    public void initForAdd(){
        this.isPublic = false;
    }
    public void changeUserId(Long userId){
        this.userId = userId;
    }


    /**
     * 创建爬虫项目领域模型对象
     * @return 爬虫项目领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CrawlerProject create(){
        return DomainFactory.create(CrawlerProject.class);
    }
}
