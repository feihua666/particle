package com.particle.crawler.infrastructure.definition.structmapping;

import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionDO;
import com.particle.crawler.domain.definition.CrawlerDefinition;
import com.particle.crawler.domain.definition.CrawlerDefinitionId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 爬虫定义 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:37
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrawlerDefinitionInfrastructureStructMapping {
	public static CrawlerDefinitionInfrastructureStructMapping instance = Mappers.getMapper( CrawlerDefinitionInfrastructureStructMapping.class );

	protected CrawlerDefinitionId map(Long id){
		if (id == null) {
			return null;
		}
		return CrawlerDefinitionId.of(id);
	}
	protected Long map(CrawlerDefinitionId crawlerDefinitionId){
		if (crawlerDefinitionId == null) {
			return null;
		}
		return crawlerDefinitionId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerDefinitionInfrastructureStructMapping#map(java.lang.Long)}
	 * @param crawlerDefinitionDO
	 * @return
	 */
	public abstract CrawlerDefinition crawlerDefinitionDOToCrawlerDefinition(@MappingTarget CrawlerDefinition crawlerDefinition,CrawlerDefinitionDO crawlerDefinitionDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerDefinitionInfrastructureStructMapping#map(CrawlerDefinitionId)}
	 * @param crawlerDefinition
	 * @return
	 */
	public abstract CrawlerDefinitionDO crawlerDefinitionToCrawlerDefinitionDO(CrawlerDefinition crawlerDefinition);

}
