package com.particle.crawler.app.definition.executor;

import com.particle.crawler.app.definition.structmapping.CrawlerDefinitionAppStructMapping;
import com.particle.crawler.client.definition.dto.command.CrawlerDefinitionCreateCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerDefinitionVO;
import com.particle.crawler.domain.definition.CrawlerDefinition;
import com.particle.crawler.domain.definition.gateway.CrawlerDefinitionGateway;
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
 * 爬虫定义 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:37
 */
@Component
@Validated
public class CrawlerDefinitionCreateCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerDefinitionGateway crawlerDefinitionGateway;

	/**
	 * 执行爬虫定义添加指令
	 * @param crawlerDefinitionCreateCommand
	 * @return
	 */
	public SingleResponse<CrawlerDefinitionVO> execute(@Valid CrawlerDefinitionCreateCommand crawlerDefinitionCreateCommand) {
		CrawlerDefinition crawlerDefinition = createByCrawlerDefinitionCreateCommand(crawlerDefinitionCreateCommand);
		crawlerDefinition.setAddControl(crawlerDefinitionCreateCommand);
		boolean save = crawlerDefinitionGateway.save(crawlerDefinition);
		if (save) {
			return SingleResponse.of(CrawlerDefinitionAppStructMapping.instance.toCrawlerDefinitionVO(crawlerDefinition));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据爬虫定义创建指令创建爬虫定义模型
	 * @param crawlerDefinitionCreateCommand
	 * @return
	 */
	private CrawlerDefinition createByCrawlerDefinitionCreateCommand(CrawlerDefinitionCreateCommand crawlerDefinitionCreateCommand){
		CrawlerDefinition crawlerDefinition = CrawlerDefinition.create();
		CrawlerDefinitionCreateCommandToCrawlerDefinitionMapping.instance.fillCrawlerDefinitionByCrawlerDefinitionCreateCommand(crawlerDefinition, crawlerDefinitionCreateCommand);
		return crawlerDefinition;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CrawlerDefinitionCreateCommandToCrawlerDefinitionMapping{
		CrawlerDefinitionCreateCommandToCrawlerDefinitionMapping instance = Mappers.getMapper( CrawlerDefinitionCreateCommandToCrawlerDefinitionMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crawlerDefinition
		 * @param crawlerDefinitionCreateCommand
		 */
		void fillCrawlerDefinitionByCrawlerDefinitionCreateCommand(@MappingTarget CrawlerDefinition crawlerDefinition, CrawlerDefinitionCreateCommand crawlerDefinitionCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param crawlerDefinitionGateway
	 */
	@Autowired
	public void setCrawlerDefinitionGateway(CrawlerDefinitionGateway crawlerDefinitionGateway) {
		this.crawlerDefinitionGateway = crawlerDefinitionGateway;
	}
}
