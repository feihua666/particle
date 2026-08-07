package com.particle.crawler.app.execution.executor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.crawler.app.execution.structmapping.CrawlerExecutionAppStructMapping;
import com.particle.crawler.client.execution.dto.command.CrawlerExecutionCreateCommand;
import com.particle.crawler.client.execution.dto.command.CrawlerExecutionExecuteCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerExecutionExecuteVO;
import com.particle.crawler.client.execution.dto.data.CrawlerExecutionVO;
import com.particle.crawler.domain.definition.CrawlerProject;
import com.particle.crawler.domain.definition.CrawlerProjectId;
import com.particle.crawler.domain.definition.gateway.CrawlerProjectGateway;
import com.particle.crawler.domain.enums.CrawlerExecutionStatus;
import com.particle.crawler.domain.enums.CrawlerTriggerType;
import com.particle.crawler.domain.execution.CrawlerExecution;
import com.particle.crawler.domain.execution.CrawlerExecutionId;
import com.particle.crawler.domain.execution.gateway.CrawlerExecutionExecuteGateway;
import com.particle.crawler.domain.execution.gateway.CrawlerExecutionGateway;
import com.particle.crawler.domain.execution.value.ExecutionCrawlerResultDTO;
import com.particle.crawler.domain.gateway.CrawlerDictGateway;
import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionDO;
import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionHistoryDO;
import com.particle.crawler.infrastructure.definition.service.ICrawlerDefinitionHistoryService;
import com.particle.crawler.infrastructure.definition.service.ICrawlerDefinitionService;
import com.particle.crawler.infrastructure.execution.service.ICrawlerExecutionService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.tool.json.JsonTool;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 爬虫执行 指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-12 16:40:47
 */
@Component
@Validated
public class CrawlerExecutionExecuteCommandExecutor extends AbstractBaseExecutor {

	private CrawlerExecutionGateway crawlerExecutionGateway;
	private ICrawlerExecutionService iCrawlerExecutionService;

	private CrawlerExecutionExecuteGateway crawlerExecutionExecuteGateway;

	private CrawlerExecutionCreateCommandExecutor crawlerExecutionCreateCommandExecutor;

	private ICrawlerDefinitionService iCrawlerDefinitionService;
	private ICrawlerDefinitionHistoryService iCrawlerDefinitionHistoryService;

	private CrawlerDictGateway crawlerDictGateway;
	private CrawlerProjectGateway crawlerProjectGateway;
	/**
	 * 执行 爬虫执行 指令
	 * @param crawlerExecutionExecuteCommand
	 * @return
	 */
	public SingleResponse<CrawlerExecutionExecuteVO> execute(@Valid CrawlerExecutionExecuteCommand crawlerExecutionExecuteCommand) {
		// 先获取定义数据
		CrawlerDefinitionHistoryDO crawlerDefinitionHistoryDO = iCrawlerDefinitionHistoryService.getById(crawlerExecutionExecuteCommand.getCrawlerDefinitionHistoryId());
		Assert.isTrue(crawlerDefinitionHistoryDO != null, "爬虫定义历史不存在");
		Assert.isTrue(Objects.equals(crawlerDefinitionHistoryDO.getCrawlerDefinitionId(), crawlerExecutionExecuteCommand.getCrawlerDefinitionId()),
				"爬虫定义历史中的定义和参数中的不匹配");

		Long runningStatusDictId = crawlerDictGateway.getDictIdByGroupCodeAndItemValue(CrawlerExecutionStatus.Group.crawler_execution_status.groupCode(),
				CrawlerExecutionStatus.RUNNING.itemValue());
		// 先添加一个 execution 实例数据

		Long triggerTypeDictId = crawlerExecutionExecuteCommand.getTriggerTypeDictId();
		if (triggerTypeDictId == null) {
			triggerTypeDictId = crawlerDictGateway.getDictIdByGroupCodeAndItemValue(CrawlerTriggerType.Group.crawler_trigger_type.groupCode(),
					crawlerExecutionExecuteCommand.getTriggerTypeDictValue());
		}
		CrawlerExecutionCreateCommand crawlerExecutionCreateCommand = new CrawlerExecutionCreateCommand();
		crawlerExecutionCreateCommand.setCrawlerDefinitionId(crawlerExecutionExecuteCommand.getCrawlerDefinitionId());
		crawlerExecutionCreateCommand.setCrawlerDefinitionHistoryId(crawlerExecutionExecuteCommand.getCrawlerDefinitionHistoryId());
		crawlerExecutionCreateCommand.setStatusDictId(runningStatusDictId);
		crawlerExecutionCreateCommand.setTriggerTypeDictId(triggerTypeDictId);
		crawlerExecutionCreateCommand.setStartAt(LocalDateTime.now());
		SingleResponse<CrawlerExecutionVO> execute = crawlerExecutionCreateCommandExecutor.execute(crawlerExecutionCreateCommand);

		String definitionJson = crawlerDefinitionHistoryDO.getDefinitionJson();

		// 合并配置
		// 先从项目配置中获取
		// 先获取定义
		CrawlerDefinitionDO crawlerDefinitionDO = iCrawlerDefinitionService.getById(crawlerDefinitionHistoryDO.getCrawlerDefinitionId());
		CrawlerProject crawlerProject = crawlerProjectGateway.getById(CrawlerProjectId.of(crawlerDefinitionDO.getCrawlerProjectId()));
		Map<String, Object> mergededConfigVariables = mergedConfig(crawlerProject.getConfigJson(), crawlerDefinitionHistoryDO.getConfigJson(), crawlerExecutionExecuteCommand.getParam());

		// 真正执行
		ExecutionCrawlerResultDTO executionCrawlerResultDTO = crawlerExecutionExecuteGateway.executeCrawler(definitionJson,
				mergededConfigVariables,
				crawlerExecutionExecuteCommand.getCrawlRuntimeOptionsJson());


		// 更新状态
		CrawlerExecution crawlerExecution = crawlerExecutionGateway.getById(CrawlerExecutionId.of(execute.getData().getId()));
		crawlerExecution.changeFinishAt(LocalDateTime.now());
		Long finishStatusDictId = crawlerDictGateway.getDictIdByGroupCodeAndItemValue(CrawlerExecutionStatus.Group.crawler_execution_status.groupCode(),
				executionCrawlerResultDTO.getExecutionStatus().itemValue());
		crawlerExecution.changeStatusDictId(finishStatusDictId);
		crawlerExecutionGateway.save(crawlerExecution);

		// 再查询最新的数据返回
		crawlerExecution = crawlerExecutionGateway.getById(CrawlerExecutionId.of(execute.getData().getId()));

		CrawlerExecutionVO crawlerExecutionVO = CrawlerExecutionAppStructMapping.instance.toCrawlerExecutionVO(crawlerExecution);

		CrawlerExecutionExecuteVO crawlerExecutionExecuteVO = CrawlerExecutionAppStructMapping.instance.toCrawlerExecutionExecuteVO(crawlerExecutionVO);
		crawlerExecutionExecuteVO.setResultData(executionCrawlerResultDTO.getResultData());
		return SingleResponse.of(crawlerExecutionExecuteVO);
	}

	/**
	 * 按优先级从低到高将项目、定义、参数合并
	 * @return
	 */
	@SneakyThrows
    private Map<String, Object> mergedConfig(String projectConfigJson, String definitionConfigJson, Map<String, Object> param) {

		Map<String, Object> result = new HashMap<>();
		if (projectConfigJson != null && !projectConfigJson.isEmpty()) {
			Map<String, Object> projectResult = JsonTool.getObjectMapper().readValue(projectConfigJson, new TypeReference<Map<String, Object>>() {});
			result.putAll(projectResult);
		}
		if (definitionConfigJson != null && !definitionConfigJson.isEmpty()) {
			Map<String, Object> definitionResult = JsonTool.getObjectMapper().readValue(definitionConfigJson, new TypeReference<Map<String, Object>>() {});
			result.putAll(definitionResult);
		}
		if (param != null && !param.isEmpty()) {
			result.putAll(param);
		}
		return result;

	}

	/**
	 * 注入使用set方法
	 * @param crawlerExecutionGateway
	 */
	@Autowired
	public void setCrawlerExecutionGateway(CrawlerExecutionGateway crawlerExecutionGateway) {
		this.crawlerExecutionGateway = crawlerExecutionGateway;
	}
	@Autowired
	public void setICrawlerExecutionService(ICrawlerExecutionService iCrawlerExecutionService) {
		this.iCrawlerExecutionService = iCrawlerExecutionService;
	}

	@Autowired
	public void setCrawlerExecutionExecuteGateway(CrawlerExecutionExecuteGateway crawlerExecutionExecuteGateway) {
		this.crawlerExecutionExecuteGateway = crawlerExecutionExecuteGateway;
	}
	@Autowired
	public void setCrawlerExecutionCreateCommandExecutor(CrawlerExecutionCreateCommandExecutor crawlerExecutionCreateCommandExecutor) {
		this.crawlerExecutionCreateCommandExecutor = crawlerExecutionCreateCommandExecutor;
	}
	@Autowired
	public void setICrawlerDefinitionService(ICrawlerDefinitionService iCrawlerDefinitionService) {
		this.iCrawlerDefinitionService = iCrawlerDefinitionService;
	}
	@Autowired
	public void setICrawlerDefinitionHistoryService(ICrawlerDefinitionHistoryService iCrawlerDefinitionHistoryService) {
		this.iCrawlerDefinitionHistoryService = iCrawlerDefinitionHistoryService;
	}
	@Autowired
	public void setCrawlerDictGateway(CrawlerDictGateway crawlerDictGateway) {
		this.crawlerDictGateway = crawlerDictGateway;
	}
	@Autowired
	public void setCrawlerProjectGateway(CrawlerProjectGateway crawlerProjectGateway) {
		this.crawlerProjectGateway = crawlerProjectGateway;
	}
}
