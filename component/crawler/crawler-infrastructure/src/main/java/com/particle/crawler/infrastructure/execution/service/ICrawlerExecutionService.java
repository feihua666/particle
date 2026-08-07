package com.particle.crawler.infrastructure.execution.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.crawler.infrastructure.execution.dos.CrawlerExecutionDO;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.util.List;

/**
 * <p>
 * 爬虫执行实例 服务类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
public interface ICrawlerExecutionService extends IBaseService<CrawlerExecutionDO> {





    /**
     * 根据爬虫定义ID查询
     * @param crawlerDefinitionId
     * @return
     */
    default List<CrawlerExecutionDO> getByCrawlerDefinitionId(Long crawlerDefinitionId) {
        Assert.notNull(crawlerDefinitionId,"crawlerDefinitionId 不能为空");
        return list(Wrappers.<CrawlerExecutionDO>lambdaQuery().eq(CrawlerExecutionDO::getCrawlerDefinitionId, crawlerDefinitionId));
    }



    /**
     * 根据爬虫定义ID查询多个
     * @param crawlerDefinitionIds
     * @return
     */
    default List<CrawlerExecutionDO> getByCrawlerDefinitionIds(List<Long> crawlerDefinitionIds) {
        Assert.notEmpty(crawlerDefinitionIds,"crawlerDefinitionIds 不能为空");
        return list(Wrappers.<CrawlerExecutionDO>lambdaQuery().in(CrawlerExecutionDO::getCrawlerDefinitionId, crawlerDefinitionIds));
    }










}
