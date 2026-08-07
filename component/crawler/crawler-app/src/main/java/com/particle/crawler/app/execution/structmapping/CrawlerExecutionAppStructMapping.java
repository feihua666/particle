package com.particle.crawler.app.execution.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.crawler.client.execution.dto.data.CrawlerExecutionExecuteVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.crawler.client.execution.dto.data.CrawlerExecutionVO;
import com.particle.crawler.domain.execution.CrawlerExecution;
import com.particle.crawler.domain.execution.CrawlerExecutionId;
import com.particle.crawler.infrastructure.execution.dos.CrawlerExecutionDO;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerExecutionPageQueryCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerExecutionQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 爬虫执行实例 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrawlerExecutionAppStructMapping  implements IBaseQueryCommandMapStruct<CrawlerExecutionDO>{
	public static CrawlerExecutionAppStructMapping instance = Mappers.getMapper( CrawlerExecutionAppStructMapping.class );

	protected Long map(CrawlerExecutionId crawlerExecutionId){
		if (crawlerExecutionId == null) {
			return null;
		}
		return crawlerExecutionId.getId();
	}
	/**
	 * 爬虫执行实例领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrawlerExecutionAppStructMapping#map(CrawlerExecutionId)}
	 * @param crawlerExecution
	 * @return
	 */
	public abstract CrawlerExecutionVO toCrawlerExecutionVO(CrawlerExecution crawlerExecution);


	/**
	 * 数据对象转视图对象
	 * @param crawlerExecutionDO
	 * @return
	 */
	public abstract CrawlerExecutionVO crawlerExecutionDOToCrawlerExecutionVO(CrawlerExecutionDO crawlerExecutionDO);

	/**
	 * 批量转换
	 * @param crawlerExecutionDOs
	 * @return
	 */
	public abstract List<CrawlerExecutionVO> crawlerExecutionDOsToCrawlerExecutionVOs(List<CrawlerExecutionDO> crawlerExecutionDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CrawlerExecutionVO> infrastructurePageToPageResponse(Page<CrawlerExecutionDO> page) {
		return PageResponse.of(crawlerExecutionDOsToCrawlerExecutionVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}

	public abstract CrawlerExecutionExecuteVO toCrawlerExecutionExecuteVO(CrawlerExecutionVO crawlerExecutionVO);

	@Override
	public CrawlerExecutionDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CrawlerExecutionPageQueryCommand) {
			return pageQueryCommandToDO((CrawlerExecutionPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CrawlerExecutionQueryListCommand) {
			return queryListCommandToDO(((CrawlerExecutionQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CrawlerExecutionDO pageQueryCommandToDO(CrawlerExecutionPageQueryCommand crawlerExecutionPageQueryCommand);

	public abstract CrawlerExecutionDO queryListCommandToDO(CrawlerExecutionQueryListCommand crawlerExecutionQueryListCommand);
}
