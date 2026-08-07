package com.particle.crawler.app.definition.executor;

import com.particle.crawler.app.definition.structmapping.CrawlerDefinitionHistoryAppStructMapping;
import com.particle.crawler.client.definition.dto.command.CrawlerDefinitionHistoryUpdateCommand;
import com.particle.crawler.client.definition.dto.command.CrawlerDefinitionUpdateControlCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerDefinitionHistoryVO;
import com.particle.crawler.domain.definition.CrawlerDefinition;
import com.particle.crawler.domain.definition.CrawlerDefinitionHistory;
import com.particle.crawler.domain.definition.CrawlerDefinitionHistoryId;
import com.particle.crawler.domain.definition.CrawlerDefinitionId;
import com.particle.crawler.domain.definition.gateway.CrawlerDefinitionGateway;
import com.particle.crawler.domain.definition.gateway.CrawlerDefinitionHistoryGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 爬虫定义历史 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CrawlerDefinitionHistoryUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerDefinitionHistoryGateway crawlerDefinitionHistoryGateway;
	private CrawlerDefinitionGateway crawlerDefinitionGateway;
	/**
	 * 执行 爬虫定义历史 更新指令
	 * @param crawlerDefinitionHistoryUpdateCommand
	 * @return
	 */
	public SingleResponse<CrawlerDefinitionHistoryVO> execute(@Valid CrawlerDefinitionHistoryUpdateCommand crawlerDefinitionHistoryUpdateCommand) {
		CrawlerDefinitionHistory crawlerDefinitionHistory = createByCrawlerDefinitionHistoryUpdateCommand(crawlerDefinitionHistoryUpdateCommand);
		crawlerDefinitionHistory.setUpdateControl(crawlerDefinitionHistoryUpdateCommand);
		boolean save = crawlerDefinitionHistoryGateway.save(crawlerDefinitionHistory);
		if (save) {
			// 如果更新为发布，则需要将爬虫定义对应的已发布字段设置
			CrawlerDefinition crawlerDefinition = crawlerDefinitionGateway.getById(CrawlerDefinitionId.of(crawlerDefinitionHistory.getCrawlerDefinitionId()));
			if (crawlerDefinitionHistoryUpdateCommand.getIsPublish() != null && crawlerDefinitionHistoryUpdateCommand.getIsPublish()
					&& crawlerDefinitionHistoryUpdateCommand.getId().equals(crawlerDefinition.getDraftCrawlerDefinitionHistoryId())) {
				crawlerDefinition.changeLatestPublishCrawlerDefinitionHistoryId(crawlerDefinitionHistoryUpdateCommand.getId());
				crawlerDefinition.changeDraftCrawlerDefinitionHistoryId(null);

				// 一个更新控制对象，主要是更新时将草稿更新为null，因为草稿已经是发布状态了
				CrawlerDefinitionUpdateControlCommand crawlerDefinitionUpdateControlCommand = new CrawlerDefinitionUpdateControlCommand();
				crawlerDefinitionUpdateControlCommand.setLatestPublishCrawlerDefinitionHistoryId(crawlerDefinition.getLatestPublishCrawlerDefinitionHistoryId());
				crawlerDefinitionUpdateControlCommand.setDraftCrawlerDefinitionHistoryId(null);

				crawlerDefinition.setUpdateControl(crawlerDefinitionUpdateControlCommand);
				crawlerDefinitionGateway.save(crawlerDefinition);
			}
			return SingleResponse.of(CrawlerDefinitionHistoryAppStructMapping.instance.toCrawlerDefinitionHistoryVO(crawlerDefinitionHistory));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据爬虫定义历史更新指令创建爬虫定义历史模型
	 * @param crawlerDefinitionHistoryUpdateCommand
	 * @return
	 */
	private CrawlerDefinitionHistory createByCrawlerDefinitionHistoryUpdateCommand(CrawlerDefinitionHistoryUpdateCommand crawlerDefinitionHistoryUpdateCommand){
		CrawlerDefinitionHistory crawlerDefinitionHistory = CrawlerDefinitionHistory.create();
		CrawlerDefinitionHistoryUpdateCommandToCrawlerDefinitionHistoryMapping.instance.fillCrawlerDefinitionHistoryByCrawlerDefinitionHistoryUpdateCommand(crawlerDefinitionHistory, crawlerDefinitionHistoryUpdateCommand);
		return crawlerDefinitionHistory;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CrawlerDefinitionHistoryUpdateCommandToCrawlerDefinitionHistoryMapping{
		CrawlerDefinitionHistoryUpdateCommandToCrawlerDefinitionHistoryMapping instance = Mappers.getMapper(CrawlerDefinitionHistoryUpdateCommandToCrawlerDefinitionHistoryMapping.class );

		default CrawlerDefinitionHistoryId map(Long id){
			if (id == null) {
				return null;
			}
			return CrawlerDefinitionHistoryId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crawlerDefinitionHistory
		 * @param crawlerDefinitionHistoryUpdateCommand
		 */
		void fillCrawlerDefinitionHistoryByCrawlerDefinitionHistoryUpdateCommand(@MappingTarget CrawlerDefinitionHistory crawlerDefinitionHistory, CrawlerDefinitionHistoryUpdateCommand crawlerDefinitionHistoryUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param crawlerDefinitionHistoryGateway
	 */
	@Autowired
	public void setCrawlerDefinitionHistoryGateway(CrawlerDefinitionHistoryGateway crawlerDefinitionHistoryGateway) {
		this.crawlerDefinitionHistoryGateway = crawlerDefinitionHistoryGateway;
	}
	@Autowired
	public void setCrawlerDefinitionGateway(CrawlerDefinitionGateway crawlerDefinitionGateway) {
		this.crawlerDefinitionGateway = crawlerDefinitionGateway;
	}
}
