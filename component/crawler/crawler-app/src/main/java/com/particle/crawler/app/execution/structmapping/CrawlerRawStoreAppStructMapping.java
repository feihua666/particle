package com.particle.crawler.app.execution.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.crawler.client.execution.dto.data.CrawlerRawStoreVO;
import com.particle.crawler.domain.execution.CrawlerRawStore;
import com.particle.crawler.domain.execution.CrawlerRawStoreId;
import com.particle.crawler.infrastructure.execution.dos.CrawlerRawStoreDO;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerRawStorePageQueryCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerRawStoreQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 爬虫原始数据存储 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:47
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrawlerRawStoreAppStructMapping  implements IBaseQueryCommandMapStruct<CrawlerRawStoreDO>{
	public static CrawlerRawStoreAppStructMapping instance = Mappers.getMapper( CrawlerRawStoreAppStructMapping.class );

	protected Long map(CrawlerRawStoreId crawlerRawStoreId){
		if (crawlerRawStoreId == null) {
			return null;
		}
		return crawlerRawStoreId.getId();
	}
	/**
	 * 爬虫原始数据存储领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerRawStoreAppStructMapping#map(CrawlerRawStoreId)}
	 * @param crawlerRawStore
	 * @return
	 */
	public abstract CrawlerRawStoreVO toCrawlerRawStoreVO(CrawlerRawStore crawlerRawStore);


	/**
	 * 数据对象转视图对象
	 * @param crawlerRawStoreDO
	 * @return
	 */
	public abstract CrawlerRawStoreVO crawlerRawStoreDOToCrawlerRawStoreVO(CrawlerRawStoreDO crawlerRawStoreDO);

	/**
	 * 批量转换
	 * @param crawlerRawStoreDOs
	 * @return
	 */
	public abstract List<CrawlerRawStoreVO> crawlerRawStoreDOsToCrawlerRawStoreVOs(List<CrawlerRawStoreDO> crawlerRawStoreDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CrawlerRawStoreVO> infrastructurePageToPageResponse(Page<CrawlerRawStoreDO> page) {
		return PageResponse.of(crawlerRawStoreDOsToCrawlerRawStoreVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CrawlerRawStoreDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CrawlerRawStorePageQueryCommand) {
			return pageQueryCommandToDO((CrawlerRawStorePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CrawlerRawStoreQueryListCommand) {
			return queryListCommandToDO(((CrawlerRawStoreQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CrawlerRawStoreDO pageQueryCommandToDO(CrawlerRawStorePageQueryCommand crawlerRawStorePageQueryCommand);

	public abstract CrawlerRawStoreDO queryListCommandToDO(CrawlerRawStoreQueryListCommand crawlerRawStoreQueryListCommand);
}
