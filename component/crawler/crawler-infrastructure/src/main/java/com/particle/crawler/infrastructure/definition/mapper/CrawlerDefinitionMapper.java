package com.particle.crawler.infrastructure.definition.mapper;

import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 爬虫定义 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:37
 */
@Mapper
public interface CrawlerDefinitionMapper extends IBaseMapper<CrawlerDefinitionDO> {

}
