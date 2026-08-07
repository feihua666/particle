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
 * 爬虫定义历史表
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:55
 */
@Accessors(chain = true)
@Data
@TableName("component_crawler_definition_history")
public class CrawlerDefinitionHistoryDO extends BaseDO {

    /**
    * 爬虫定义id
    */
    private Long crawlerDefinitionId;

    /**
    * 定义版本号，从1开始递增
    */
    private Integer crawlerDefinitionVersion;

    /**
    * 流程图数据
    */
    private String definitionJson;

    /**
    * 爬虫级配置json
    */
    private String configJson;

    /**
    * 是否发布，1=已发布，0=未发布，草稿
    */
    private Boolean isPublish;


}
