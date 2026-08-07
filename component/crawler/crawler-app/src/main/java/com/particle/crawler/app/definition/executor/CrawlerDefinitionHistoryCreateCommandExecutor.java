package com.particle.crawler.app.definition.executor;

import com.particle.crawler.app.definition.structmapping.CrawlerDefinitionHistoryAppStructMapping;
import com.particle.crawler.client.definition.dto.command.CrawlerDefinitionHistoryCreateCommand;
import com.particle.crawler.client.definition.dto.command.CrawlerDefinitionHistoryCreateDraftCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerDefinitionHistoryVO;
import com.particle.crawler.domain.definition.CrawlerDefinitionHistory;
import com.particle.crawler.domain.definition.gateway.CrawlerDefinitionHistoryGateway;
import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionDO;
import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionHistoryDO;
import com.particle.crawler.infrastructure.definition.service.ICrawlerDefinitionHistoryService;
import com.particle.crawler.infrastructure.definition.service.ICrawlerDefinitionService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
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
 * 爬虫定义历史 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:55
 */
@Component
@Validated
public class CrawlerDefinitionHistoryCreateCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerDefinitionHistoryGateway crawlerDefinitionHistoryGateway;

	private ICrawlerDefinitionService crawlerDefinitionService;
	private ICrawlerDefinitionHistoryService crawlerDefinitionHistoryService;
	/**
	 * 执行爬虫定义历史添加指令
	 * @param crawlerDefinitionHistoryCreateCommand
	 * @return
	 */
	public SingleResponse<CrawlerDefinitionHistoryVO> execute(@Valid CrawlerDefinitionHistoryCreateCommand crawlerDefinitionHistoryCreateCommand) {
		CrawlerDefinitionHistory crawlerDefinitionHistory = createByCrawlerDefinitionHistoryCreateCommand(crawlerDefinitionHistoryCreateCommand);
		crawlerDefinitionHistory.setAddControl(crawlerDefinitionHistoryCreateCommand);
		boolean save = crawlerDefinitionHistoryGateway.save(crawlerDefinitionHistory);
		if (save) {
			return SingleResponse.of(CrawlerDefinitionHistoryAppStructMapping.instance.toCrawlerDefinitionHistoryVO(crawlerDefinitionHistory));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}
	/**
	 * 执行爬虫定义历史添加草稿指令
	 * @param crawlerDefinitionHistoryCreateDraftCommand
	 * @return
	 */
	public SingleResponse<CrawlerDefinitionHistoryVO> createDraft(CrawlerDefinitionHistoryCreateDraftCommand crawlerDefinitionHistoryCreateDraftCommand) {
		// 检查是否已经存在草稿
		CrawlerDefinitionDO crawlerDefinitionDO = crawlerDefinitionService.getById(crawlerDefinitionHistoryCreateDraftCommand.getCrawlerDefinitionId());
		Assert.isTrue(crawlerDefinitionDO.getDraftCrawlerDefinitionHistoryId() == null, "草稿已存在请勿重复创建");

		// 判断新建草稿的版本，从最新发布的历史版本开始加1
		Long latestPublishCrawlerDefinitionHistoryId = crawlerDefinitionDO.getLatestPublishCrawlerDefinitionHistoryId();
		Integer crawlerDefinitionVersion = null;
		if (latestPublishCrawlerDefinitionHistoryId != null) {
			CrawlerDefinitionHistoryDO crawlerDefinitionHistoryDO = crawlerDefinitionHistoryService.getById(latestPublishCrawlerDefinitionHistoryId);
			crawlerDefinitionVersion = crawlerDefinitionHistoryDO.getCrawlerDefinitionVersion() + 1;
			crawlerDefinitionHistoryCreateDraftCommand.setDefinitionJson(crawlerDefinitionHistoryDO.getDefinitionJson());
			crawlerDefinitionHistoryCreateDraftCommand.setConfigJson(crawlerDefinitionHistoryDO.getConfigJson());
		}

		CrawlerDefinitionHistory crawlerDefinitionHistory = createByCrawlerDefinitionHistoryCreateDraftCommand(crawlerDefinitionHistoryCreateDraftCommand);
		crawlerDefinitionHistory.setAddControl(crawlerDefinitionHistoryCreateDraftCommand);
		crawlerDefinitionHistory.initForAdd();
		// 如果有版本，则设置
		if (crawlerDefinitionVersion != null) {
			crawlerDefinitionHistory.changeCrawlerDefinitionVersion(crawlerDefinitionVersion);
		}
		boolean save = crawlerDefinitionHistoryGateway.save(crawlerDefinitionHistory);
		if (save) {
			// 草稿新建成功之后，需要回填流程定义中的草稿历史id
			CrawlerDefinitionDO crawlerDefinitionDOForUpdate = new CrawlerDefinitionDO();
			crawlerDefinitionDOForUpdate.setId(crawlerDefinitionDO.getId());
			crawlerDefinitionDOForUpdate.setVersion(crawlerDefinitionDO.getVersion());
			crawlerDefinitionDOForUpdate.setDraftCrawlerDefinitionHistoryId(crawlerDefinitionHistory.getId().getId());
			crawlerDefinitionService.update(crawlerDefinitionDOForUpdate);
			return SingleResponse.of(CrawlerDefinitionHistoryAppStructMapping.instance.toCrawlerDefinitionHistoryVO(crawlerDefinitionHistory));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据爬虫定义历史创建指令创建爬虫定义历史模型
	 * @param crawlerDefinitionHistoryCreateCommand
	 * @return
	 */
	private CrawlerDefinitionHistory createByCrawlerDefinitionHistoryCreateCommand(CrawlerDefinitionHistoryCreateCommand crawlerDefinitionHistoryCreateCommand){
		CrawlerDefinitionHistory crawlerDefinitionHistory = CrawlerDefinitionHistory.create();
		CrawlerDefinitionHistoryCreateCommandToCrawlerDefinitionHistoryMapping.instance.fillCrawlerDefinitionHistoryByCrawlerDefinitionHistoryCreateCommand(crawlerDefinitionHistory, crawlerDefinitionHistoryCreateCommand);
		return crawlerDefinitionHistory;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CrawlerDefinitionHistoryCreateCommandToCrawlerDefinitionHistoryMapping{
		CrawlerDefinitionHistoryCreateCommandToCrawlerDefinitionHistoryMapping instance = Mappers.getMapper( CrawlerDefinitionHistoryCreateCommandToCrawlerDefinitionHistoryMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crawlerDefinitionHistory
		 * @param crawlerDefinitionHistoryCreateCommand
		 */
		void fillCrawlerDefinitionHistoryByCrawlerDefinitionHistoryCreateCommand(@MappingTarget CrawlerDefinitionHistory crawlerDefinitionHistory, CrawlerDefinitionHistoryCreateCommand crawlerDefinitionHistoryCreateCommand);
	}

	/**
	 * 根据爬虫定义历史草稿创建指令创建爬虫定义历史模型
	 * @param crawlerDefinitionHistoryCreateDraftCommand
	 * @return
	 */
	private CrawlerDefinitionHistory createByCrawlerDefinitionHistoryCreateDraftCommand(CrawlerDefinitionHistoryCreateDraftCommand crawlerDefinitionHistoryCreateDraftCommand){
		CrawlerDefinitionHistory crawlerDefinitionHistory = CrawlerDefinitionHistory.create();
		CrawlerDefinitionHistoryCreateDraftCommandToCrawlerDefinitionHistoryMapping.instance.fillCrawlerDefinitionHistoryByCrawlerDefinitionHistoryCreateDraftCommand(crawlerDefinitionHistory, crawlerDefinitionHistoryCreateDraftCommand);
		return crawlerDefinitionHistory;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CrawlerDefinitionHistoryCreateDraftCommandToCrawlerDefinitionHistoryMapping{
		CrawlerDefinitionHistoryCreateDraftCommandToCrawlerDefinitionHistoryMapping instance = Mappers.getMapper( CrawlerDefinitionHistoryCreateDraftCommandToCrawlerDefinitionHistoryMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crawlerDefinitionHistory
		 * @param crawlerDefinitionHistoryCreateDraftCommand
		 */
		void fillCrawlerDefinitionHistoryByCrawlerDefinitionHistoryCreateDraftCommand(@MappingTarget CrawlerDefinitionHistory crawlerDefinitionHistory, CrawlerDefinitionHistoryCreateDraftCommand crawlerDefinitionHistoryCreateDraftCommand);
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
	public void setCrawlerDefinitionService(ICrawlerDefinitionService crawlerDefinitionService) {
		this.crawlerDefinitionService = crawlerDefinitionService;
	}
	@Autowired
	public void setCrawlerDefinitionHistoryService(ICrawlerDefinitionHistoryService crawlerDefinitionHistoryService) {
		this.crawlerDefinitionHistoryService = crawlerDefinitionHistoryService;
	}
}
