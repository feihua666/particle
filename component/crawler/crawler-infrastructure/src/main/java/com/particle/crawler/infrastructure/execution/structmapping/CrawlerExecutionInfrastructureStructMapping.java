package com.particle.crawler.infrastructure.execution.structmapping;

import com.particle.crawler.infrastructure.execution.dos.CrawlerExecutionDO;
import com.particle.crawler.domain.execution.CrawlerExecution;
import com.particle.crawler.domain.execution.CrawlerExecutionId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 爬虫执行实例 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrawlerExecutionInfrastructureStructMapping {
	public static CrawlerExecutionInfrastructureStructMapping instance = Mappers.getMapper( CrawlerExecutionInfrastructureStructMapping.class );

	protected CrawlerExecutionId map(Long id){
		if (id == null) {
			return null;
		}
		return CrawlerExecutionId.of(id);
	}
	protected Long map(CrawlerExecutionId crawlerExecutionId){
		if (crawlerExecutionId == null) {
			return null;
		}
		return crawlerExecutionId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerExecutionInfrastructureStructMapping#map(java.lang.Long)}
	 * @param crawlerExecutionDO
	 * @return
	 */
	public abstract CrawlerExecution crawlerExecutionDOToCrawlerExecution(@MappingTarget CrawlerExecution crawlerExecution,CrawlerExecutionDO crawlerExecutionDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerExecutionInfrastructureStructMapping#map(CrawlerExecutionId)}
	 * @param crawlerExecution
	 * @return
	 */
	public abstract CrawlerExecutionDO crawlerExecutionToCrawlerExecutionDO(CrawlerExecution crawlerExecution);

}
