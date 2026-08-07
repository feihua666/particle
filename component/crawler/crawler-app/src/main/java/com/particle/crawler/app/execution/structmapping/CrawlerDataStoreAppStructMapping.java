package com.particle.crawler.app.execution.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.crawler.client.execution.dto.data.CrawlerDataStoreVO;
import com.particle.crawler.domain.execution.CrawlerDataStore;
import com.particle.crawler.domain.execution.CrawlerDataStoreId;
import com.particle.crawler.infrastructure.execution.dos.CrawlerDataStoreDO;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerDataStorePageQueryCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerDataStoreQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 爬虫结构数据存储 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:19
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrawlerDataStoreAppStructMapping  implements IBaseQueryCommandMapStruct<CrawlerDataStoreDO>{
	public static CrawlerDataStoreAppStructMapping instance = Mappers.getMapper( CrawlerDataStoreAppStructMapping.class );

	protected Long map(CrawlerDataStoreId crawlerDataStoreId){
		if (crawlerDataStoreId == null) {
			return null;
		}
		return crawlerDataStoreId.getId();
	}
	/**
	 * 爬虫结构数据存储领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerDataStoreAppStructMapping#map(CrawlerDataStoreId)}
	 * @param crawlerDataStore
	 * @return
	 */
	public abstract CrawlerDataStoreVO toCrawlerDataStoreVO(CrawlerDataStore crawlerDataStore);


	/**
	 * 数据对象转视图对象
	 * @param crawlerDataStoreDO
	 * @return
	 */
	public abstract CrawlerDataStoreVO crawlerDataStoreDOToCrawlerDataStoreVO(CrawlerDataStoreDO crawlerDataStoreDO);

	/**
	 * 批量转换
	 * @param crawlerDataStoreDOs
	 * @return
	 */
	public abstract List<CrawlerDataStoreVO> crawlerDataStoreDOsToCrawlerDataStoreVOs(List<CrawlerDataStoreDO> crawlerDataStoreDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CrawlerDataStoreVO> infrastructurePageToPageResponse(Page<CrawlerDataStoreDO> page) {
		return PageResponse.of(crawlerDataStoreDOsToCrawlerDataStoreVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CrawlerDataStoreDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CrawlerDataStorePageQueryCommand) {
			return pageQueryCommandToDO((CrawlerDataStorePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CrawlerDataStoreQueryListCommand) {
			return queryListCommandToDO(((CrawlerDataStoreQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CrawlerDataStoreDO pageQueryCommandToDO(CrawlerDataStorePageQueryCommand crawlerDataStorePageQueryCommand);

	public abstract CrawlerDataStoreDO queryListCommandToDO(CrawlerDataStoreQueryListCommand crawlerDataStoreQueryListCommand);
}
