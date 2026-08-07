package com.particle.crawler.app.definition.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.crawler.client.definition.dto.data.CrawlerDefinitionHistoryVO;
import com.particle.crawler.domain.definition.CrawlerDefinitionHistory;
import com.particle.crawler.domain.definition.CrawlerDefinitionHistoryId;
import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionHistoryDO;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerDefinitionHistoryPageQueryCommand;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerDefinitionHistoryQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 爬虫定义历史 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:55
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrawlerDefinitionHistoryAppStructMapping  implements IBaseQueryCommandMapStruct<CrawlerDefinitionHistoryDO>{
	public static CrawlerDefinitionHistoryAppStructMapping instance = Mappers.getMapper( CrawlerDefinitionHistoryAppStructMapping.class );

	protected Long map(CrawlerDefinitionHistoryId crawlerDefinitionHistoryId){
		if (crawlerDefinitionHistoryId == null) {
			return null;
		}
		return crawlerDefinitionHistoryId.getId();
	}
	/**
	 * 爬虫定义历史领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerDefinitionHistoryAppStructMapping#map(CrawlerDefinitionHistoryId)}
	 * @param crawlerDefinitionHistory
	 * @return
	 */
	public abstract CrawlerDefinitionHistoryVO toCrawlerDefinitionHistoryVO(CrawlerDefinitionHistory crawlerDefinitionHistory);


	/**
	 * 数据对象转视图对象
	 * @param crawlerDefinitionHistoryDO
	 * @return
	 */
	public abstract CrawlerDefinitionHistoryVO crawlerDefinitionHistoryDOToCrawlerDefinitionHistoryVO(CrawlerDefinitionHistoryDO crawlerDefinitionHistoryDO);

	/**
	 * 批量转换
	 * @param crawlerDefinitionHistoryDOs
	 * @return
	 */
	public abstract List<CrawlerDefinitionHistoryVO> crawlerDefinitionHistoryDOsToCrawlerDefinitionHistoryVOs(List<CrawlerDefinitionHistoryDO> crawlerDefinitionHistoryDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CrawlerDefinitionHistoryVO> infrastructurePageToPageResponse(Page<CrawlerDefinitionHistoryDO> page) {
		return PageResponse.of(crawlerDefinitionHistoryDOsToCrawlerDefinitionHistoryVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CrawlerDefinitionHistoryDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CrawlerDefinitionHistoryPageQueryCommand) {
			return pageQueryCommandToDO((CrawlerDefinitionHistoryPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CrawlerDefinitionHistoryQueryListCommand) {
			return queryListCommandToDO(((CrawlerDefinitionHistoryQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CrawlerDefinitionHistoryDO pageQueryCommandToDO(CrawlerDefinitionHistoryPageQueryCommand crawlerDefinitionHistoryPageQueryCommand);

	public abstract CrawlerDefinitionHistoryDO queryListCommandToDO(CrawlerDefinitionHistoryQueryListCommand crawlerDefinitionHistoryQueryListCommand);
}
