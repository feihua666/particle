package com.particle.crawler.infrastructure.definition.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionHistoryDO;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.util.List;

/**
 * <p>
 * 爬虫定义历史 服务类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:55
 */
public interface ICrawlerDefinitionHistoryService extends IBaseService<CrawlerDefinitionHistoryDO> {


    /**
     * 根据爬虫定义id查询
     * @param crawlerDefinitionId
     * @return
     */
    default List<CrawlerDefinitionHistoryDO> getByCrawlerDefinitionId(Long crawlerDefinitionId) {
        Assert.notNull(crawlerDefinitionId,"crawlerDefinitionId 不能为空");
        return list(Wrappers.<CrawlerDefinitionHistoryDO>lambdaQuery().eq(CrawlerDefinitionHistoryDO::getCrawlerDefinitionId, crawlerDefinitionId));
    }



    /**
     * 根据爬虫定义id查询多个
     * @param crawlerDefinitionIds
     * @return
     */
    default List<CrawlerDefinitionHistoryDO> getByCrawlerDefinitionIds(List<Long> crawlerDefinitionIds) {
        Assert.notEmpty(crawlerDefinitionIds,"crawlerDefinitionIds 不能为空");
        return list(Wrappers.<CrawlerDefinitionHistoryDO>lambdaQuery().in(CrawlerDefinitionHistoryDO::getCrawlerDefinitionId, crawlerDefinitionIds));
    }










}
