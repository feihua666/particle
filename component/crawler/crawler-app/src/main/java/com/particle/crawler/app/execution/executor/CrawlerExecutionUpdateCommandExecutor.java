package com.particle.crawler.app.execution.executor;

import com.particle.crawler.app.execution.structmapping.CrawlerExecutionAppStructMapping;
import com.particle.crawler.client.execution.dto.command.CrawlerExecutionUpdateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerExecutionVO;
import com.particle.crawler.domain.execution.CrawlerExecution;
import com.particle.crawler.domain.execution.CrawlerExecutionId;
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
 * 爬虫执行实例 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CrawlerExecutionUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerExecutionGateway crawlerExecutionGateway;

	/**
	 * 执行 爬虫执行实例 更新指令
	 * @param crawlerExecutionUpdateCommand
	 * @return
	 */
	public SingleResponse<CrawlerExecutionVO> execute(@Valid CrawlerExecutionUpdateCommand crawlerExecutionUpdateCommand) {
		CrawlerExecution crawlerExecution = createByCrawlerExecutionUpdateCommand(crawlerExecutionUpdateCommand);
		crawlerExecution.setUpdateControl(crawlerExecutionUpdateCommand);
		boolean save = crawlerExecutionGateway.save(crawlerExecution);
		if (save) {
			return SingleResponse.of(CrawlerExecutionAppStructMapping.instance.toCrawlerExecutionVO(crawlerExecution));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据爬虫执行实例更新指令创建爬虫执行实例模型
	 * @param crawlerExecutionUpdateCommand
	 * @return
	 */
	private CrawlerExecution createByCrawlerExecutionUpdateCommand(CrawlerExecutionUpdateCommand crawlerExecutionUpdateCommand){
		CrawlerExecution crawlerExecution = CrawlerExecution.create();
		CrawlerExecutionUpdateCommandToCrawlerExecutionMapping.instance.fillCrawlerExecutionByCrawlerExecutionUpdateCommand(crawlerExecution, crawlerExecutionUpdateCommand);
		return crawlerExecution;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CrawlerExecutionUpdateCommandToCrawlerExecutionMapping{
		CrawlerExecutionUpdateCommandToCrawlerExecutionMapping instance = Mappers.getMapper(CrawlerExecutionUpdateCommandToCrawlerExecutionMapping.class );

		default CrawlerExecutionId map(Long id){
			if (id == null) {
				return null;
			}
			return CrawlerExecutionId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crawlerExecution
		 * @param crawlerExecutionUpdateCommand
		 */
		void fillCrawlerExecutionByCrawlerExecutionUpdateCommand(@MappingTarget CrawlerExecution crawlerExecution, CrawlerExecutionUpdateCommand crawlerExecutionUpdateCommand);
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
