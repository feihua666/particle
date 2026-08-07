package com.particle.crawler.app.definition.executor;

import com.particle.crawler.app.definition.structmapping.CrawlerProjectAppStructMapping;
import com.particle.crawler.client.definition.dto.command.CrawlerProjectCreateCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerProjectVO;
import com.particle.crawler.domain.definition.CrawlerProject;
import com.particle.crawler.domain.definition.gateway.CrawlerProjectGateway;
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
 * 爬虫项目 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:21
 */
@Component
@Validated
public class CrawlerProjectCreateCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerProjectGateway crawlerProjectGateway;

	/**
	 * 执行爬虫项目添加指令
	 * @param crawlerProjectCreateCommand
	 * @return
	 */
	public SingleResponse<CrawlerProjectVO> execute(@Valid CrawlerProjectCreateCommand crawlerProjectCreateCommand) {
		CrawlerProject crawlerProject = createByCrawlerProjectCreateCommand(crawlerProjectCreateCommand);
		crawlerProject.setAddControl(crawlerProjectCreateCommand);
		crawlerProject.changeUserId(crawlerProjectCreateCommand.getLoginUserId());
		crawlerProject.initForAdd();
		boolean save = crawlerProjectGateway.save(crawlerProject);
		if (save) {
			return SingleResponse.of(CrawlerProjectAppStructMapping.instance.toCrawlerProjectVO(crawlerProject));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据爬虫项目创建指令创建爬虫项目模型
	 * @param crawlerProjectCreateCommand
	 * @return
	 */
	private CrawlerProject createByCrawlerProjectCreateCommand(CrawlerProjectCreateCommand crawlerProjectCreateCommand){
		CrawlerProject crawlerProject = CrawlerProject.create();
		CrawlerProjectCreateCommandToCrawlerProjectMapping.instance.fillCrawlerProjectByCrawlerProjectCreateCommand(crawlerProject, crawlerProjectCreateCommand);
		return crawlerProject;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CrawlerProjectCreateCommandToCrawlerProjectMapping{
		CrawlerProjectCreateCommandToCrawlerProjectMapping instance = Mappers.getMapper( CrawlerProjectCreateCommandToCrawlerProjectMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crawlerProject
		 * @param crawlerProjectCreateCommand
		 */
		void fillCrawlerProjectByCrawlerProjectCreateCommand(@MappingTarget CrawlerProject crawlerProject, CrawlerProjectCreateCommand crawlerProjectCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param crawlerProjectGateway
	 */
	@Autowired
	public void setCrawlerProjectGateway(CrawlerProjectGateway crawlerProjectGateway) {
		this.crawlerProjectGateway = crawlerProjectGateway;
	}
}
