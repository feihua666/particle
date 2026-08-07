package com.particle.crawler.app.execution.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.crawler.client.execution.dto.data.CrawlerDataStoreContentVO;
import com.particle.crawler.domain.execution.CrawlerDataStoreContent;
import com.particle.crawler.domain.execution.CrawlerDataStoreContentId;
import com.particle.crawler.infrastructure.execution.dos.CrawlerDataStoreContentDO;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerDataStoreContentPageQueryCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerDataStoreContentQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 爬虫结构数据存储内容 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:34
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrawlerDataStoreContentAppStructMapping  implements IBaseQueryCommandMapStruct<CrawlerDataStoreContentDO>{
	public static CrawlerDataStoreContentAppStructMapping instance = Mappers.getMapper( CrawlerDataStoreContentAppStructMapping.class );

	protected Long map(CrawlerDataStoreContentId crawlerDataStoreContentId){
		if (crawlerDataStoreContentId == null) {
			return null;
		}
		return crawlerDataStoreContentId.getId();
	}
	/**
	 * 爬虫结构数据存储内容领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerDataStoreContentAppStructMapping#map(CrawlerDataStoreContentId)}
	 * @param crawlerDataStoreContent
	 * @return
	 */
	public abstract CrawlerDataStoreContentVO toCrawlerDataStoreContentVO(CrawlerDataStoreContent crawlerDataStoreContent);


	/**
	 * 数据对象转视图对象
	 * @param crawlerDataStoreContentDO
	 * @return
	 */
	public abstract CrawlerDataStoreContentVO crawlerDataStoreContentDOToCrawlerDataStoreContentVO(CrawlerDataStoreContentDO crawlerDataStoreContentDO);

	/**
	 * 批量转换
	 * @param crawlerDataStoreContentDOs
	 * @return
	 */
	public abstract List<CrawlerDataStoreContentVO> crawlerDataStoreContentDOsToCrawlerDataStoreContentVOs(List<CrawlerDataStoreContentDO> crawlerDataStoreContentDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CrawlerDataStoreContentVO> infrastructurePageToPageResponse(Page<CrawlerDataStoreContentDO> page) {
		return PageResponse.of(crawlerDataStoreContentDOsToCrawlerDataStoreContentVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CrawlerDataStoreContentDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CrawlerDataStoreContentPageQueryCommand) {
			return pageQueryCommandToDO((CrawlerDataStoreContentPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CrawlerDataStoreContentQueryListCommand) {
			return queryListCommandToDO(((CrawlerDataStoreContentQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CrawlerDataStoreContentDO pageQueryCommandToDO(CrawlerDataStoreContentPageQueryCommand crawlerDataStoreContentPageQueryCommand);

	public abstract CrawlerDataStoreContentDO queryListCommandToDO(CrawlerDataStoreContentQueryListCommand crawlerDataStoreContentQueryListCommand);
}
