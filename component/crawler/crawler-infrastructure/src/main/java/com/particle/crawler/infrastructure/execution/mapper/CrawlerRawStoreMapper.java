package com.particle.crawler.infrastructure.execution.mapper;

import com.particle.crawler.infrastructure.execution.dos.CrawlerRawStoreDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 爬虫原始数据存储 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:47
 */
@Mapper
public interface CrawlerRawStoreMapper extends IBaseMapper<CrawlerRawStoreDO> {

}
