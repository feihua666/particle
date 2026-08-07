package com.particle.crawler.infrastructure.definition.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionDO;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.util.List;

/**
 * <p>
 * 爬虫定义 服务类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:37
 */
public interface ICrawlerDefinitionService extends IBaseService<CrawlerDefinitionDO> {



    /**
     * 根据项目id查询
     * @param crawlerProjectId
     * @return
     */
    default List<CrawlerDefinitionDO> getByCrawlerProjectId(Long crawlerProjectId) {
        Assert.notNull(crawlerProjectId,"crawlerProjectId 不能为空");
        return list(Wrappers.<CrawlerDefinitionDO>lambdaQuery().eq(CrawlerDefinitionDO::getCrawlerProjectId, crawlerProjectId));
    }



    /**
     * 根据项目id查询多个
     * @param crawlerProjectIds
     * @return
     */
    default List<CrawlerDefinitionDO> getByCrawlerProjectIds(List<Long> crawlerProjectIds) {
        Assert.notEmpty(crawlerProjectIds,"crawlerProjectIds 不能为空");
        return list(Wrappers.<CrawlerDefinitionDO>lambdaQuery().in(CrawlerDefinitionDO::getCrawlerProjectId, crawlerProjectIds));
    }









}
