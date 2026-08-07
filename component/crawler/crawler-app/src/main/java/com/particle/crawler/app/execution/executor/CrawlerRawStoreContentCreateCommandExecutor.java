package com.particle.crawler.app.execution.executor;

import com.particle.crawler.app.execution.structmapping.CrawlerRawStoreContentAppStructMapping;
import com.particle.crawler.client.execution.dto.command.CrawlerRawStoreContentCreateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerRawStoreContentVO;
import com.particle.crawler.domain.execution.CrawlerRawStoreContent;
import com.particle.crawler.domain.execution.gateway.CrawlerRawStoreContentGateway;
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
 * 爬虫原始数据存储内容 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:24:02
 */
@Component
@Validated
public class CrawlerRawStoreContentCreateCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerRawStoreContentGateway crawlerRawStoreContentGateway;

	/**
	 * 执行爬虫原始数据存储内容添加指令
	 * @param crawlerRawStoreContentCreateCommand
	 * @return
	 */
	public SingleResponse<CrawlerRawStoreContentVO> execute(@Valid CrawlerRawStoreContentCreateCommand crawlerRawStoreContentCreateCommand) {
		CrawlerRawStoreContent crawlerRawStoreContent = createByCrawlerRawStoreContentCreateCommand(crawlerRawStoreContentCreateCommand);
		crawlerRawStoreContent.setAddControl(crawlerRawStoreContentCreateCommand);
		boolean save = crawlerRawStoreContentGateway.save(crawlerRawStoreContent);
		if (save) {
			return SingleResponse.of(CrawlerRawStoreContentAppStructMapping.instance.toCrawlerRawStoreContentVO(crawlerRawStoreContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据爬虫原始数据存储内容创建指令创建爬虫原始数据存储内容模型
	 * @param crawlerRawStoreContentCreateCommand
	 * @return
	 */
	private CrawlerRawStoreContent createByCrawlerRawStoreContentCreateCommand(CrawlerRawStoreContentCreateCommand crawlerRawStoreContentCreateCommand){
		CrawlerRawStoreContent crawlerRawStoreContent = CrawlerRawStoreContent.create();
		CrawlerRawStoreContentCreateCommandToCrawlerRawStoreContentMapping.instance.fillCrawlerRawStoreContentByCrawlerRawStoreContentCreateCommand(crawlerRawStoreContent, crawlerRawStoreContentCreateCommand);
		return crawlerRawStoreContent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CrawlerRawStoreContentCreateCommandToCrawlerRawStoreContentMapping{
		CrawlerRawStoreContentCreateCommandToCrawlerRawStoreContentMapping instance = Mappers.getMapper( CrawlerRawStoreContentCreateCommandToCrawlerRawStoreContentMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crawlerRawStoreContent
		 * @param crawlerRawStoreContentCreateCommand
		 */
		void fillCrawlerRawStoreContentByCrawlerRawStoreContentCreateCommand(@MappingTarget CrawlerRawStoreContent crawlerRawStoreContent, CrawlerRawStoreContentCreateCommand crawlerRawStoreContentCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param crawlerRawStoreContentGateway
	 */
	@Autowired
	public void setCrawlerRawStoreContentGateway(CrawlerRawStoreContentGateway crawlerRawStoreContentGateway) {
		this.crawlerRawStoreContentGateway = crawlerRawStoreContentGateway;
	}
}
