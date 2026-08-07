package com.particle.crawler.app.definition.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.crawler.client.definition.dto.data.CrawlerProjectVO;
import com.particle.crawler.domain.definition.CrawlerProject;
import com.particle.crawler.domain.definition.CrawlerProjectId;
import com.particle.crawler.infrastructure.definition.dos.CrawlerProjectDO;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerProjectPageQueryCommand;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerProjectQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 爬虫项目 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:21
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrawlerProjectAppStructMapping  implements IBaseQueryCommandMapStruct<CrawlerProjectDO>{
	public static CrawlerProjectAppStructMapping instance = Mappers.getMapper( CrawlerProjectAppStructMapping.class );

	protected Long map(CrawlerProjectId crawlerProjectId){
		if (crawlerProjectId == null) {
			return null;
		}
		return crawlerProjectId.getId();
	}
	/**
	 * 爬虫项目领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerProjectAppStructMapping#map(CrawlerProjectId)}
	 * @param crawlerProject
	 * @return
	 */
	public abstract CrawlerProjectVO toCrawlerProjectVO(CrawlerProject crawlerProject);


	/**
	 * 数据对象转视图对象
	 * @param crawlerProjectDO
	 * @return
	 */
	public abstract CrawlerProjectVO crawlerProjectDOToCrawlerProjectVO(CrawlerProjectDO crawlerProjectDO);

	/**
	 * 批量转换
	 * @param crawlerProjectDOs
	 * @return
	 */
	public abstract List<CrawlerProjectVO> crawlerProjectDOsToCrawlerProjectVOs(List<CrawlerProjectDO> crawlerProjectDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CrawlerProjectVO> infrastructurePageToPageResponse(Page<CrawlerProjectDO> page) {
		return PageResponse.of(crawlerProjectDOsToCrawlerProjectVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CrawlerProjectDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CrawlerProjectPageQueryCommand) {
			return pageQueryCommandToDO((CrawlerProjectPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CrawlerProjectQueryListCommand) {
			return queryListCommandToDO(((CrawlerProjectQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CrawlerProjectDO pageQueryCommandToDO(CrawlerProjectPageQueryCommand crawlerProjectPageQueryCommand);

	public abstract CrawlerProjectDO queryListCommandToDO(CrawlerProjectQueryListCommand crawlerProjectQueryListCommand);
}
