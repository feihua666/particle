package com.particle.crawler.domain.execution;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 爬虫执行实例 领域模型
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@Data
@Entity
public class CrawlerExecution extends AggreateRoot {

    private CrawlerExecutionId id;

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


    public void changeFinishAt(LocalDateTime finishAt) {
        this.finishAt = finishAt;
    }
    public void changeStatusDictId(Long statusDictId) {
        this.statusDictId = statusDictId;
    }

    /**
     * 创建爬虫执行实例领域模型对象
     * @return 爬虫执行实例领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CrawlerExecution create(){
        return DomainFactory.create(CrawlerExecution.class);
    }
}
