package com.particle.crawler.app.execution.executor;

import com.particle.crawler.app.execution.structmapping.CrawlerExecutionAppStructMapping;
import com.particle.crawler.client.execution.dto.command.CrawlerExecutionCreateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerExecutionVO;
import com.particle.crawler.domain.execution.CrawlerExecution;
import com.particle.crawler.domain.execution.gateway.CrawlerExecutionGateway;
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
 * 爬虫执行实例 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@Component
@Validated
public class CrawlerExecutionCreateCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerExecutionGateway crawlerExecutionGateway;

	/**
	 * 执行爬虫执行实例添加指令
	 * @param crawlerExecutionCreateCommand
	 * @return
	 */
	public SingleResponse<CrawlerExecutionVO> execute(@Valid CrawlerExecutionCreateCommand crawlerExecutionCreateCommand) {
		CrawlerExecution crawlerExecution = createByCrawlerExecutionCreateCommand(crawlerExecutionCreateCommand);
		crawlerExecution.setAddControl(crawlerExecutionCreateCommand);
		boolean save = crawlerExecutionGateway.save(crawlerExecution);
		if (save) {
			return SingleResponse.of(CrawlerExecutionAppStructMapping.instance.toCrawlerExecutionVO(crawlerExecution));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据爬虫执行实例创建指令创建爬虫执行实例模型
	 * @param crawlerExecutionCreateCommand
	 * @return
	 */
	private CrawlerExecution createByCrawlerExecutionCreateCommand(CrawlerExecutionCreateCommand crawlerExecutionCreateCommand){
		CrawlerExecution crawlerExecution = CrawlerExecution.create();
		CrawlerExecutionCreateCommandToCrawlerExecutionMapping.instance.fillCrawlerExecutionByCrawlerExecutionCreateCommand(crawlerExecution, crawlerExecutionCreateCommand);
		return crawlerExecution;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CrawlerExecutionCreateCommandToCrawlerExecutionMapping{
		CrawlerExecutionCreateCommandToCrawlerExecutionMapping instance = Mappers.getMapper( CrawlerExecutionCreateCommandToCrawlerExecutionMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crawlerExecution
		 * @param crawlerExecutionCreateCommand
		 */
		void fillCrawlerExecutionByCrawlerExecutionCreateCommand(@MappingTarget CrawlerExecution crawlerExecution, CrawlerExecutionCreateCommand crawlerExecutionCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param crawlerExecutionGateway
	 */
	@Autowired
	public void setCrawlerExecutionGateway(CrawlerExecutionGateway crawlerExecutionGateway) {
		this.crawlerExecutionGateway = crawlerExecutionGateway;
	}
}
