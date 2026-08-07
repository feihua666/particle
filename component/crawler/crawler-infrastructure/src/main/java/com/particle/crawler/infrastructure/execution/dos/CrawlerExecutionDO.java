package com.particle.crawler.infrastructure.execution.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
/**
 * <p>
 * 爬虫执行实例表
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@Accessors(chain = true)
@Data
@TableName("component_crawler_execution")
public class CrawlerExecutionDO extends BaseDO {

    /**
    * 爬虫定义ID
    */
    private Long crawlerDefinitionId;

    /**
    * 执行时使用的版本ID
    */
    private Long crawlerDefinitionHistoryId;

    /**
    * 执行状态字典id：running/success/failed
    */
    private Long statusDictId;

    /**
    * 触发方式字典id：manual/api/schedule
    */
    private Long triggerTypeDictId;

    /**
    * 全局上下文数据json
    */
    private String contextJson;

    /**
    * 运行开始时间
    */
    private LocalDateTime startAt;
    
    /**
    * 运行结束时间
    */
    private LocalDateTime finishAt;
    
    /**
    * 错误信息
    */
    private String errorMsg;


}
