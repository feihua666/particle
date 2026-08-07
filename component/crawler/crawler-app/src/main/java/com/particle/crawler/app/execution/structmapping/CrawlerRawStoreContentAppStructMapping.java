package com.particle.crawler.app.execution.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.crawler.client.execution.dto.data.CrawlerRawStoreContentVO;
import com.particle.crawler.domain.execution.CrawlerRawStoreContent;
import com.particle.crawler.domain.execution.CrawlerRawStoreContentId;
import com.particle.crawler.infrastructure.execution.dos.CrawlerRawStoreContentDO;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerRawStoreContentPageQueryCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerRawStoreContentQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 爬虫原始数据存储内容 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:24:02
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrawlerRawStoreContentAppStructMapping  implements IBaseQueryCommandMapStruct<CrawlerRawStoreContentDO>{
	public static CrawlerRawStoreContentAppStructMapping instance = Mappers.getMapper( CrawlerRawStoreContentAppStructMapping.class );

	protected Long map(CrawlerRawStoreContentId crawlerRawStoreContentId){
		if (crawlerRawStoreContentId == null) {
			return null;
		}
		return crawlerRawStoreContentId.getId();
	}
	/**
	 * 爬虫原始数据存储内容领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerRawStoreContentAppStructMapping#map(CrawlerRawStoreContentId)}
	 * @param crawlerRawStoreContent
	 * @return
	 */
	public abstract CrawlerRawStoreContentVO toCrawlerRawStoreContentVO(CrawlerRawStoreContent crawlerRawStoreContent);


	/**
	 * 数据对象转视图对象
	 * @param crawlerRawStoreContentDO
	 * @return
	 */
	public abstract CrawlerRawStoreContentVO crawlerRawStoreContentDOToCrawlerRawStoreContentVO(CrawlerRawStoreContentDO crawlerRawStoreContentDO);

	/**
	 * 批量转换
	 * @param crawlerRawStoreContentDOs
	 * @return
	 */
	public abstract List<CrawlerRawStoreContentVO> crawlerRawStoreContentDOsToCrawlerRawStoreContentVOs(List<CrawlerRawStoreContentDO> crawlerRawStoreContentDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CrawlerRawStoreContentVO> infrastructurePageToPageResponse(Page<CrawlerRawStoreContentDO> page) {
		return PageResponse.of(crawlerRawStoreContentDOsToCrawlerRawStoreContentVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CrawlerRawStoreContentDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CrawlerRawStoreContentPageQueryCommand) {
			return pageQueryCommandToDO((CrawlerRawStoreContentPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CrawlerRawStoreContentQueryListCommand) {
			return queryListCommandToDO(((CrawlerRawStoreContentQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CrawlerRawStoreContentDO pageQueryCommandToDO(CrawlerRawStoreContentPageQueryCommand crawlerRawStoreContentPageQueryCommand);

	public abstract CrawlerRawStoreContentDO queryListCommandToDO(CrawlerRawStoreContentQueryListCommand crawlerRawStoreContentQueryListCommand);
}
