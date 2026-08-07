package com.particle.crawler.app.definition.executor;

import com.particle.crawler.app.definition.structmapping.CrawlerDefinitionAppStructMapping;
import com.particle.crawler.client.definition.dto.command.CrawlerDefinitionUpdateCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerDefinitionVO;
import com.particle.crawler.domain.definition.CrawlerDefinition;
import com.particle.crawler.domain.definition.CrawlerDefinitionId;
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
 * 爬虫定义 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CrawlerDefinitionUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerDefinitionGateway crawlerDefinitionGateway;

	/**
	 * 执行 爬虫定义 更新指令
	 * @param crawlerDefinitionUpdateCommand
	 * @return
	 */
	public SingleResponse<CrawlerDefinitionVO> execute(@Valid CrawlerDefinitionUpdateCommand crawlerDefinitionUpdateCommand) {
		CrawlerDefinition crawlerDefinition = createByCrawlerDefinitionUpdateCommand(crawlerDefinitionUpdateCommand);
		crawlerDefinition.setUpdateControl(crawlerDefinitionUpdateCommand);
		boolean save = crawlerDefinitionGateway.save(crawlerDefinition);
		if (save) {
			return SingleResponse.of(CrawlerDefinitionAppStructMapping.instance.toCrawlerDefinitionVO(crawlerDefinition));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据爬虫定义更新指令创建爬虫定义模型
	 * @param crawlerDefinitionUpdateCommand
	 * @return
	 */
	private CrawlerDefinition createByCrawlerDefinitionUpdateCommand(CrawlerDefinitionUpdateCommand crawlerDefinitionUpdateCommand){
		CrawlerDefinition crawlerDefinition = CrawlerDefinition.create();
		CrawlerDefinitionUpdateCommandToCrawlerDefinitionMapping.instance.fillCrawlerDefinitionByCrawlerDefinitionUpdateCommand(crawlerDefinition, crawlerDefinitionUpdateCommand);
		return crawlerDefinition;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CrawlerDefinitionUpdateCommandToCrawlerDefinitionMapping{
		CrawlerDefinitionUpdateCommandToCrawlerDefinitionMapping instance = Mappers.getMapper(CrawlerDefinitionUpdateCommandToCrawlerDefinitionMapping.class );

		default CrawlerDefinitionId map(Long id){
			if (id == null) {
				return null;
			}
			return CrawlerDefinitionId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crawlerDefinition
		 * @param crawlerDefinitionUpdateCommand
		 */
		void fillCrawlerDefinitionByCrawlerDefinitionUpdateCommand(@MappingTarget CrawlerDefinition crawlerDefinition, CrawlerDefinitionUpdateCommand crawlerDefinitionUpdateCommand);
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
