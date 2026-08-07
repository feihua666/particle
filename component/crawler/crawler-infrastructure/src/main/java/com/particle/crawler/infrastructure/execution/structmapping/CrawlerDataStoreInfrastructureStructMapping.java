package com.particle.crawler.infrastructure.execution.structmapping;

import com.particle.crawler.infrastructure.execution.dos.CrawlerDataStoreDO;
import com.particle.crawler.domain.execution.CrawlerDataStore;
import com.particle.crawler.domain.execution.CrawlerDataStoreId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 爬虫结构数据存储 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:19
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrawlerDataStoreInfrastructureStructMapping {
	public static CrawlerDataStoreInfrastructureStructMapping instance = Mappers.getMapper( CrawlerDataStoreInfrastructureStructMapping.class );

	protected CrawlerDataStoreId map(Long id){
		if (id == null) {
			return null;
		}
		return CrawlerDataStoreId.of(id);
	}
	protected Long map(CrawlerDataStoreId crawlerDataStoreId){
		if (crawlerDataStoreId == null) {
			return null;
		}
		return crawlerDataStoreId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerDataStoreInfrastructureStructMapping#map(java.lang.Long)}
	 * @param crawlerDataStoreDO
	 * @return
	 */
	public abstract CrawlerDataStore crawlerDataStoreDOToCrawlerDataStore(@MappingTarget CrawlerDataStore crawlerDataStore,CrawlerDataStoreDO crawlerDataStoreDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerDataStoreInfrastructureStructMapping#map(CrawlerDataStoreId)}
	 * @param crawlerDataStore
	 * @return
	 */
	public abstract CrawlerDataStoreDO crawlerDataStoreToCrawlerDataStoreDO(CrawlerDataStore crawlerDataStore);

}
