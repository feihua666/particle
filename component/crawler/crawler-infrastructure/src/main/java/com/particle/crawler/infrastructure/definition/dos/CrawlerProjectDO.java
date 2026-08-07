package com.particle.crawler.infrastructure.definition.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 爬虫项目表
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:21
 */
@Accessors(chain = true)
@Data
@TableName("component_crawler_project")
public class CrawlerProjectDO extends BaseDO {

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


}
