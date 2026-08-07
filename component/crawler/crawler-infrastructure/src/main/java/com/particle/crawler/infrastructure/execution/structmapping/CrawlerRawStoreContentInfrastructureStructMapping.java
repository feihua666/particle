package com.particle.crawler.infrastructure.execution.structmapping;

import com.particle.crawler.infrastructure.execution.dos.CrawlerRawStoreContentDO;
import com.particle.crawler.domain.execution.CrawlerRawStoreContent;
import com.particle.crawler.domain.execution.CrawlerRawStoreContentId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 爬虫原始数据存储内容 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:24:02
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrawlerRawStoreContentInfrastructureStructMapping {
	public static CrawlerRawStoreContentInfrastructureStructMapping instance = Mappers.getMapper( CrawlerRawStoreContentInfrastructureStructMapping.class );

	protected CrawlerRawStoreContentId map(Long id){
		if (id == null) {
			return null;
		}
		return CrawlerRawStoreContentId.of(id);
	}
	protected Long map(CrawlerRawStoreContentId crawlerRawStoreContentId){
		if (crawlerRawStoreContentId == null) {
			return null;
		}
		return crawlerRawStoreContentId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerRawStoreContentInfrastructureStructMapping#map(java.lang.Long)}
	 * @param crawlerRawStoreContentDO
	 * @return
	 */
	public abstract CrawlerRawStoreContent crawlerRawStoreContentDOToCrawlerRawStoreContent(@MappingTarget CrawlerRawStoreContent crawlerRawStoreContent,CrawlerRawStoreContentDO crawlerRawStoreContentDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerRawStoreContentInfrastructureStructMapping#map(CrawlerRawStoreContentId)}
	 * @param crawlerRawStoreContent
	 * @return
	 */
	public abstract CrawlerRawStoreContentDO crawlerRawStoreContentToCrawlerRawStoreContentDO(CrawlerRawStoreContent crawlerRawStoreContent);

}
