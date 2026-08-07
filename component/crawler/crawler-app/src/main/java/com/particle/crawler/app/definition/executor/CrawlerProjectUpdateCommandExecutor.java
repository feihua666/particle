package com.particle.crawler.app.definition.executor;

import com.particle.crawler.app.definition.structmapping.CrawlerProjectAppStructMapping;
import com.particle.crawler.client.definition.dto.command.CrawlerProjectUpdateCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerProjectVO;
import com.particle.crawler.domain.definition.CrawlerProject;
import com.particle.crawler.domain.definition.CrawlerProjectId;
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
 * 爬虫项目 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CrawlerProjectUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerProjectGateway crawlerProjectGateway;

	/**
	 * 执行 爬虫项目 更新指令
	 * @param crawlerProjectUpdateCommand
	 * @return
	 */
	public SingleResponse<CrawlerProjectVO> execute(@Valid CrawlerProjectUpdateCommand crawlerProjectUpdateCommand) {
		CrawlerProject crawlerProject = createByCrawlerProjectUpdateCommand(crawlerProjectUpdateCommand);
		crawlerProject.setUpdateControl(crawlerProjectUpdateCommand);
		boolean save = crawlerProjectGateway.save(crawlerProject);
		if (save) {
			return SingleResponse.of(CrawlerProjectAppStructMapping.instance.toCrawlerProjectVO(crawlerProject));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据爬虫项目更新指令创建爬虫项目模型
	 * @param crawlerProjectUpdateCommand
	 * @return
	 */
	private CrawlerProject createByCrawlerProjectUpdateCommand(CrawlerProjectUpdateCommand crawlerProjectUpdateCommand){
		CrawlerProject crawlerProject = CrawlerProject.create();
		CrawlerProjectUpdateCommandToCrawlerProjectMapping.instance.fillCrawlerProjectByCrawlerProjectUpdateCommand(crawlerProject, crawlerProjectUpdateCommand);
		return crawlerProject;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CrawlerProjectUpdateCommandToCrawlerProjectMapping{
		CrawlerProjectUpdateCommandToCrawlerProjectMapping instance = Mappers.getMapper(CrawlerProjectUpdateCommandToCrawlerProjectMapping.class );

		default CrawlerProjectId map(Long id){
			if (id == null) {
				return null;
			}
			return CrawlerProjectId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crawlerProject
		 * @param crawlerProjectUpdateCommand
		 */
		void fillCrawlerProjectByCrawlerProjectUpdateCommand(@MappingTarget CrawlerProject crawlerProject, CrawlerProjectUpdateCommand crawlerProjectUpdateCommand);
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
