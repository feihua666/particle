package com.particle.crawler.infrastructure.execution.mapper;

import com.particle.crawler.infrastructure.execution.dos.CrawlerDataStoreDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 爬虫结构数据存储 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:19
 */
@Mapper
public interface CrawlerDataStoreMapper extends IBaseMapper<CrawlerDataStoreDO> {

}
