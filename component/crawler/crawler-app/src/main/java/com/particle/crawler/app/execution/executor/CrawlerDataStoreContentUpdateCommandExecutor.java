package com.particle.crawler.app.execution.executor;

import com.particle.crawler.app.execution.structmapping.CrawlerDataStoreContentAppStructMapping;
import com.particle.crawler.client.execution.dto.command.CrawlerDataStoreContentUpdateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerDataStoreContentVO;
import com.particle.crawler.domain.execution.CrawlerDataStoreContent;
import com.particle.crawler.domain.execution.CrawlerDataStoreContentId;
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
 * 爬虫结构数据存储内容 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CrawlerDataStoreContentUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerDataStoreContentGateway crawlerDataStoreContentGateway;

	/**
	 * 执行 爬虫结构数据存储内容 更新指令
	 * @param crawlerDataStoreContentUpdateCommand
	 * @return
	 */
	public SingleResponse<CrawlerDataStoreContentVO> execute(@Valid CrawlerDataStoreContentUpdateCommand crawlerDataStoreContentUpdateCommand) {
		CrawlerDataStoreContent crawlerDataStoreContent = createByCrawlerDataStoreContentUpdateCommand(crawlerDataStoreContentUpdateCommand);
		crawlerDataStoreContent.setUpdateControl(crawlerDataStoreContentUpdateCommand);
		boolean save = crawlerDataStoreContentGateway.save(crawlerDataStoreContent);
		if (save) {
			return SingleResponse.of(CrawlerDataStoreContentAppStructMapping.instance.toCrawlerDataStoreContentVO(crawlerDataStoreContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据爬虫结构数据存储内容更新指令创建爬虫结构数据存储内容模型
	 * @param crawlerDataStoreContentUpdateCommand
	 * @return
	 */
	private CrawlerDataStoreContent createByCrawlerDataStoreContentUpdateCommand(CrawlerDataStoreContentUpdateCommand crawlerDataStoreContentUpdateCommand){
		CrawlerDataStoreContent crawlerDataStoreContent = CrawlerDataStoreContent.create();
		CrawlerDataStoreContentUpdateCommandToCrawlerDataStoreContentMapping.instance.fillCrawlerDataStoreContentByCrawlerDataStoreContentUpdateCommand(crawlerDataStoreContent, crawlerDataStoreContentUpdateCommand);
		return crawlerDataStoreContent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CrawlerDataStoreContentUpdateCommandToCrawlerDataStoreContentMapping{
		CrawlerDataStoreContentUpdateCommandToCrawlerDataStoreContentMapping instance = Mappers.getMapper(CrawlerDataStoreContentUpdateCommandToCrawlerDataStoreContentMapping.class );

		default CrawlerDataStoreContentId map(Long id){
			if (id == null) {
				return null;
			}
			return CrawlerDataStoreContentId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crawlerDataStoreContent
		 * @param crawlerDataStoreContentUpdateCommand
		 */
		void fillCrawlerDataStoreContentByCrawlerDataStoreContentUpdateCommand(@MappingTarget CrawlerDataStoreContent crawlerDataStoreContent, CrawlerDataStoreContentUpdateCommand crawlerDataStoreContentUpdateCommand);
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
