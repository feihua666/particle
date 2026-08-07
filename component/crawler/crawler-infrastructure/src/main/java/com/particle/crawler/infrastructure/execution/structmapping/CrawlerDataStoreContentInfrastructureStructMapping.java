package com.particle.crawler.infrastructure.execution.structmapping;

import com.particle.crawler.infrastructure.execution.dos.CrawlerDataStoreContentDO;
import com.particle.crawler.domain.execution.CrawlerDataStoreContent;
import com.particle.crawler.domain.execution.CrawlerDataStoreContentId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 爬虫结构数据存储内容 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:34
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrawlerDataStoreContentInfrastructureStructMapping {
	public static CrawlerDataStoreContentInfrastructureStructMapping instance = Mappers.getMapper( CrawlerDataStoreContentInfrastructureStructMapping.class );

	protected CrawlerDataStoreContentId map(Long id){
		if (id == null) {
			return null;
		}
		return CrawlerDataStoreContentId.of(id);
	}
	protected Long map(CrawlerDataStoreContentId crawlerDataStoreContentId){
		if (crawlerDataStoreContentId == null) {
			return null;
		}
		return crawlerDataStoreContentId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerDataStoreContentInfrastructureStructMapping#map(java.lang.Long)}
	 * @param crawlerDataStoreContentDO
	 * @return
	 */
	public abstract CrawlerDataStoreContent crawlerDataStoreContentDOToCrawlerDataStoreContent(@MappingTarget CrawlerDataStoreContent crawlerDataStoreContent,CrawlerDataStoreContentDO crawlerDataStoreContentDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerDataStoreContentInfrastructureStructMapping#map(CrawlerDataStoreContentId)}
	 * @param crawlerDataStoreContent
	 * @return
	 */
	public abstract CrawlerDataStoreContentDO crawlerDataStoreContentToCrawlerDataStoreContentDO(CrawlerDataStoreContent crawlerDataStoreContent);

}
