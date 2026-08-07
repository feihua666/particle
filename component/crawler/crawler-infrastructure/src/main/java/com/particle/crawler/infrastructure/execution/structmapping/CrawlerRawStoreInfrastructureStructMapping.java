package com.particle.crawler.infrastructure.execution.structmapping;

import com.particle.crawler.infrastructure.execution.dos.CrawlerRawStoreDO;
import com.particle.crawler.domain.execution.CrawlerRawStore;
import com.particle.crawler.domain.execution.CrawlerRawStoreId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 爬虫原始数据存储 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:47
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrawlerRawStoreInfrastructureStructMapping {
	public static CrawlerRawStoreInfrastructureStructMapping instance = Mappers.getMapper( CrawlerRawStoreInfrastructureStructMapping.class );

	protected CrawlerRawStoreId map(Long id){
		if (id == null) {
			return null;
		}
		return CrawlerRawStoreId.of(id);
	}
	protected Long map(CrawlerRawStoreId crawlerRawStoreId){
		if (crawlerRawStoreId == null) {
			return null;
		}
		return crawlerRawStoreId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerRawStoreInfrastructureStructMapping#map(java.lang.Long)}
	 * @param crawlerRawStoreDO
	 * @return
	 */
	public abstract CrawlerRawStore crawlerRawStoreDOToCrawlerRawStore(@MappingTarget CrawlerRawStore crawlerRawStore,CrawlerRawStoreDO crawlerRawStoreDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerRawStoreInfrastructureStructMapping#map(CrawlerRawStoreId)}
	 * @param crawlerRawStore
	 * @return
	 */
	public abstract CrawlerRawStoreDO crawlerRawStoreToCrawlerRawStoreDO(CrawlerRawStore crawlerRawStore);

}
