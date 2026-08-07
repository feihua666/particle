package com.particle.crawler.app.definition.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.crawler.client.definition.dto.data.CrawlerDefinitionVO;
import com.particle.crawler.domain.definition.CrawlerDefinition;
import com.particle.crawler.domain.definition.CrawlerDefinitionId;
import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionDO;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerDefinitionPageQueryCommand;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerDefinitionQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 爬虫定义 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:37
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrawlerDefinitionAppStructMapping  implements IBaseQueryCommandMapStruct<CrawlerDefinitionDO>{
	public static CrawlerDefinitionAppStructMapping instance = Mappers.getMapper( CrawlerDefinitionAppStructMapping.class );

	protected Long map(CrawlerDefinitionId crawlerDefinitionId){
		if (crawlerDefinitionId == null) {
			return null;
		}
		return crawlerDefinitionId.getId();
	}
	/**
	 * 爬虫定义领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerDefinitionAppStructMapping#map(CrawlerDefinitionId)}
	 * @param crawlerDefinition
	 * @return
	 */
	public abstract CrawlerDefinitionVO toCrawlerDefinitionVO(CrawlerDefinition crawlerDefinition);


	/**
	 * 数据对象转视图对象
	 * @param crawlerDefinitionDO
	 * @return
	 */
	public abstract CrawlerDefinitionVO crawlerDefinitionDOToCrawlerDefinitionVO(CrawlerDefinitionDO crawlerDefinitionDO);

	/**
	 * 批量转换
	 * @param crawlerDefinitionDOs
	 * @return
	 */
	public abstract List<CrawlerDefinitionVO> crawlerDefinitionDOsToCrawlerDefinitionVOs(List<CrawlerDefinitionDO> crawlerDefinitionDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CrawlerDefinitionVO> infrastructurePageToPageResponse(Page<CrawlerDefinitionDO> page) {
		return PageResponse.of(crawlerDefinitionDOsToCrawlerDefinitionVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CrawlerDefinitionDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CrawlerDefinitionPageQueryCommand) {
			return pageQueryCommandToDO((CrawlerDefinitionPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CrawlerDefinitionQueryListCommand) {
			return queryListCommandToDO(((CrawlerDefinitionQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CrawlerDefinitionDO pageQueryCommandToDO(CrawlerDefinitionPageQueryCommand crawlerDefinitionPageQueryCommand);

	public abstract CrawlerDefinitionDO queryListCommandToDO(CrawlerDefinitionQueryListCommand crawlerDefinitionQueryListCommand);
}
