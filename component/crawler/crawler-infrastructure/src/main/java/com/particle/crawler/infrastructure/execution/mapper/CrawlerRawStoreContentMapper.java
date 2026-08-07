package com.particle.crawler.infrastructure.execution.mapper;

import com.particle.crawler.infrastructure.execution.dos.CrawlerRawStoreContentDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 爬虫原始数据存储内容 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:24:02
 */
@Mapper
public interface CrawlerRawStoreContentMapper extends IBaseMapper<CrawlerRawStoreContentDO> {

}
