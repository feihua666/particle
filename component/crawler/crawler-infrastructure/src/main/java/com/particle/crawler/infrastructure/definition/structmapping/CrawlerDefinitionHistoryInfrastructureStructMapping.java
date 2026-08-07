package com.particle.crawler.infrastructure.definition.structmapping;

import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionHistoryDO;
import com.particle.crawler.domain.definition.CrawlerDefinitionHistory;
import com.particle.crawler.domain.definition.CrawlerDefinitionHistoryId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 爬虫定义历史 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:55
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrawlerDefinitionHistoryInfrastructureStructMapping {
	public static CrawlerDefinitionHistoryInfrastructureStructMapping instance = Mappers.getMapper( CrawlerDefinitionHistoryInfrastructureStructMapping.class );

	protected CrawlerDefinitionHistoryId map(Long id){
		if (id == null) {
			return null;
		}
		return CrawlerDefinitionHistoryId.of(id);
	}
	protected Long map(CrawlerDefinitionHistoryId crawlerDefinitionHistoryId){
		if (crawlerDefinitionHistoryId == null) {
			return null;
		}
		return crawlerDefinitionHistoryId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerDefinitionHistoryInfrastructureStructMapping#map(java.lang.Long)}
	 * @param crawlerDefinitionHistoryDO
	 * @return
	 */
	public abstract CrawlerDefinitionHistory crawlerDefinitionHistoryDOToCrawlerDefinitionHistory(@MappingTarget CrawlerDefinitionHistory crawlerDefinitionHistory,CrawlerDefinitionHistoryDO crawlerDefinitionHistoryDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerDefinitionHistoryInfrastructureStructMapping#map(CrawlerDefinitionHistoryId)}
	 * @param crawlerDefinitionHistory
	 * @return
	 */
	public abstract CrawlerDefinitionHistoryDO crawlerDefinitionHistoryToCrawlerDefinitionHistoryDO(CrawlerDefinitionHistory crawlerDefinitionHistory);

}
