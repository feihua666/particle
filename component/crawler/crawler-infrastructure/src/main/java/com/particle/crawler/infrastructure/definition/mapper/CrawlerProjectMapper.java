package com.particle.crawler.infrastructure.definition.mapper;

import com.particle.crawler.infrastructure.definition.dos.CrawlerProjectDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 爬虫项目 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:21
 */
@Mapper
public interface CrawlerProjectMapper extends IBaseMapper<CrawlerProjectDO> {

}
