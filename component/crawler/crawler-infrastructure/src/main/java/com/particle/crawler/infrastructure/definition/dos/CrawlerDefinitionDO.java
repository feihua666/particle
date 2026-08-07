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
 * 爬虫定义表
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:37
 */
@Accessors(chain = true)
@Data
@TableName("component_crawler_definition")
public class CrawlerDefinitionDO extends BaseDO {

    /**
    * 爬虫名称
    */
    private String name;

    /**
    * 项目id
    */
    private Long crawlerProjectId;

    /**
    * 最新发布版本爬虫定义id
    */
    private Long latestPublishCrawlerDefinitionHistoryId;

    /**
    * 草稿版本爬虫定义id
    */
    private Long draftCrawlerDefinitionHistoryId;

    /**
    * 描述
    */
    private String remark;


}
