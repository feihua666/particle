package com.particle.crawler.infrastructure.definition.structmapping;

import com.particle.crawler.infrastructure.definition.dos.CrawlerProjectDO;
import com.particle.crawler.domain.definition.CrawlerProject;
import com.particle.crawler.domain.definition.CrawlerProjectId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 爬虫项目 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:21
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrawlerProjectInfrastructureStructMapping {
	public static CrawlerProjectInfrastructureStructMapping instance = Mappers.getMapper( CrawlerProjectInfrastructureStructMapping.class );

	protected CrawlerProjectId map(Long id){
		if (id == null) {
			return null;
		}
		return CrawlerProjectId.of(id);
	}
	protected Long map(CrawlerProjectId crawlerProjectId){
		if (crawlerProjectId == null) {
			return null;
		}
		return crawlerProjectId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerProjectInfrastructureStructMapping#map(java.lang.Long)}
	 * @param crawlerProjectDO
	 * @return
	 */
	public abstract CrawlerProject crawlerProjectDOToCrawlerProject(@MappingTarget CrawlerProject crawlerProject,CrawlerProjectDO crawlerProjectDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerProjectInfrastructureStructMapping#map(CrawlerProjectId)}
	 * @param crawlerProject
	 * @return
	 */
	public abstract CrawlerProjectDO crawlerProjectToCrawlerProjectDO(CrawlerProject crawlerProject);

}
