package com.particle.crawler.app.execution.executor;

import com.particle.crawler.app.execution.structmapping.CrawlerRawStoreContentAppStructMapping;
import com.particle.crawler.client.execution.dto.command.CrawlerRawStoreContentUpdateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerRawStoreContentVO;
import com.particle.crawler.domain.execution.CrawlerRawStoreContent;
import com.particle.crawler.domain.execution.CrawlerRawStoreContentId;
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
 * 爬虫原始数据存储内容 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CrawlerRawStoreContentUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerRawStoreContentGateway crawlerRawStoreContentGateway;

	/**
	 * 执行 爬虫原始数据存储内容 更新指令
	 * @param crawlerRawStoreContentUpdateCommand
	 * @return
	 */
	public SingleResponse<CrawlerRawStoreContentVO> execute(@Valid CrawlerRawStoreContentUpdateCommand crawlerRawStoreContentUpdateCommand) {
		CrawlerRawStoreContent crawlerRawStoreContent = createByCrawlerRawStoreContentUpdateCommand(crawlerRawStoreContentUpdateCommand);
		crawlerRawStoreContent.setUpdateControl(crawlerRawStoreContentUpdateCommand);
		boolean save = crawlerRawStoreContentGateway.save(crawlerRawStoreContent);
		if (save) {
			return SingleResponse.of(CrawlerRawStoreContentAppStructMapping.instance.toCrawlerRawStoreContentVO(crawlerRawStoreContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据爬虫原始数据存储内容更新指令创建爬虫原始数据存储内容模型
	 * @param crawlerRawStoreContentUpdateCommand
	 * @return
	 */
	private CrawlerRawStoreContent createByCrawlerRawStoreContentUpdateCommand(CrawlerRawStoreContentUpdateCommand crawlerRawStoreContentUpdateCommand){
		CrawlerRawStoreContent crawlerRawStoreContent = CrawlerRawStoreContent.create();
		CrawlerRawStoreContentUpdateCommandToCrawlerRawStoreContentMapping.instance.fillCrawlerRawStoreContentByCrawlerRawStoreContentUpdateCommand(crawlerRawStoreContent, crawlerRawStoreContentUpdateCommand);
		return crawlerRawStoreContent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CrawlerRawStoreContentUpdateCommandToCrawlerRawStoreContentMapping{
		CrawlerRawStoreContentUpdateCommandToCrawlerRawStoreContentMapping instance = Mappers.getMapper(CrawlerRawStoreContentUpdateCommandToCrawlerRawStoreContentMapping.class );

		default CrawlerRawStoreContentId map(Long id){
			if (id == null) {
				return null;
			}
			return CrawlerRawStoreContentId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crawlerRawStoreContent
		 * @param crawlerRawStoreContentUpdateCommand
		 */
		void fillCrawlerRawStoreContentByCrawlerRawStoreContentUpdateCommand(@MappingTarget CrawlerRawStoreContent crawlerRawStoreContent, CrawlerRawStoreContentUpdateCommand crawlerRawStoreContentUpdateCommand);
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
