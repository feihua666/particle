package com.particle.crawler.app.execution.executor;

import com.particle.crawler.app.execution.structmapping.CrawlerDataStoreContentAppStructMapping;
import com.particle.crawler.client.execution.dto.command.CrawlerDataStoreContentCreateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerDataStoreContentVO;
import com.particle.crawler.domain.execution.CrawlerDataStoreContent;
import com.particle.crawler.domain.execution.gateway.CrawlerDataStoreContentGateway;
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
 * 爬虫结构数据存储内容 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:34
 */
@Component
@Validated
public class CrawlerDataStoreContentCreateCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerDataStoreContentGateway crawlerDataStoreContentGateway;

	/**
	 * 执行爬虫结构数据存储内容添加指令
	 * @param crawlerDataStoreContentCreateCommand
	 * @return
	 */
	public SingleResponse<CrawlerDataStoreContentVO> execute(@Valid CrawlerDataStoreContentCreateCommand crawlerDataStoreContentCreateCommand) {
		CrawlerDataStoreContent crawlerDataStoreContent = createByCrawlerDataStoreContentCreateCommand(crawlerDataStoreContentCreateCommand);
		crawlerDataStoreContent.setAddControl(crawlerDataStoreContentCreateCommand);
		boolean save = crawlerDataStoreContentGateway.save(crawlerDataStoreContent);
		if (save) {
			return SingleResponse.of(CrawlerDataStoreContentAppStructMapping.instance.toCrawlerDataStoreContentVO(crawlerDataStoreContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据爬虫结构数据存储内容创建指令创建爬虫结构数据存储内容模型
	 * @param crawlerDataStoreContentCreateCommand
	 * @return
	 */
	private CrawlerDataStoreContent createByCrawlerDataStoreContentCreateCommand(CrawlerDataStoreContentCreateCommand crawlerDataStoreContentCreateCommand){
		CrawlerDataStoreContent crawlerDataStoreContent = CrawlerDataStoreContent.create();
		CrawlerDataStoreContentCreateCommandToCrawlerDataStoreContentMapping.instance.fillCrawlerDataStoreContentByCrawlerDataStoreContentCreateCommand(crawlerDataStoreContent, crawlerDataStoreContentCreateCommand);
		return crawlerDataStoreContent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CrawlerDataStoreContentCreateCommandToCrawlerDataStoreContentMapping{
		CrawlerDataStoreContentCreateCommandToCrawlerDataStoreContentMapping instance = Mappers.getMapper( CrawlerDataStoreContentCreateCommandToCrawlerDataStoreContentMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crawlerDataStoreContent
		 * @param crawlerDataStoreContentCreateCommand
		 */
		void fillCrawlerDataStoreContentByCrawlerDataStoreContentCreateCommand(@MappingTarget CrawlerDataStoreContent crawlerDataStoreContent, CrawlerDataStoreContentCreateCommand crawlerDataStoreContentCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param crawlerDataStoreContentGateway
	 */
	@Autowired
	public void setCrawlerDataStoreContentGateway(CrawlerDataStoreContentGateway crawlerDataStoreContentGateway) {
		this.crawlerDataStoreContentGateway = crawlerDataStoreContentGateway;
	}
}
