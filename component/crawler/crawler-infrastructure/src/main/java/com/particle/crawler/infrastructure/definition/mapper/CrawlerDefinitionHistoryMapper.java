package com.particle.crawler.infrastructure.definition.mapper;

import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionHistoryDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 爬虫定义历史 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:55
 */
@Mapper
public interface CrawlerDefinitionHistoryMapper extends IBaseMapper<CrawlerDefinitionHistoryDO> {

}
