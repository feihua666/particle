package com.particle.crawler.infrastructure.execution.mapper;

import com.particle.crawler.infrastructure.execution.dos.CrawlerExecutionDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 爬虫执行实例 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@Mapper
public interface CrawlerExecutionMapper extends IBaseMapper<CrawlerExecutionDO> {

}
