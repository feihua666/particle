package com.particle.crawler.app.execution.executor;

import com.particle.crawler.app.execution.structmapping.CrawlerRawStoreAppStructMapping;
import com.particle.crawler.client.execution.dto.command.CrawlerRawStoreCreateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerRawStoreVO;
import com.particle.crawler.domain.execution.CrawlerRawStore;
import com.particle.crawler.domain.execution.gateway.CrawlerRawStoreGateway;
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
 * 爬虫原始数据存储 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:47
 */
@Component
@Validated
public class CrawlerRawStoreCreateCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerRawStoreGateway crawlerRawStoreGateway;

	/**
	 * 执行爬虫原始数据存储添加指令
	 * @param crawlerRawStoreCreateCommand
	 * @return
	 */
	public SingleResponse<CrawlerRawStoreVO> execute(@Valid CrawlerRawStoreCreateCommand crawlerRawStoreCreateCommand) {
		CrawlerRawStore crawlerRawStore = createByCrawlerRawStoreCreateCommand(crawlerRawStoreCreateCommand);
		crawlerRawStore.setAddControl(crawlerRawStoreCreateCommand);
		boolean save = crawlerRawStoreGateway.save(crawlerRawStore);
		if (save) {
			return SingleResponse.of(CrawlerRawStoreAppStructMapping.instance.toCrawlerRawStoreVO(crawlerRawStore));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据爬虫原始数据存储创建指令创建爬虫原始数据存储模型
	 * @param crawlerRawStoreCreateCommand
	 * @return
	 */
	private CrawlerRawStore createByCrawlerRawStoreCreateCommand(CrawlerRawStoreCreateCommand crawlerRawStoreCreateCommand){
		CrawlerRawStore crawlerRawStore = CrawlerRawStore.create();
		CrawlerRawStoreCreateCommandToCrawlerRawStoreMapping.instance.fillCrawlerRawStoreByCrawlerRawStoreCreateCommand(crawlerRawStore, crawlerRawStoreCreateCommand);
		return crawlerRawStore;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CrawlerRawStoreCreateCommandToCrawlerRawStoreMapping{
		CrawlerRawStoreCreateCommandToCrawlerRawStoreMapping instance = Mappers.getMapper( CrawlerRawStoreCreateCommandToCrawlerRawStoreMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crawlerRawStore
		 * @param crawlerRawStoreCreateCommand
		 */
		void fillCrawlerRawStoreByCrawlerRawStoreCreateCommand(@MappingTarget CrawlerRawStore crawlerRawStore, CrawlerRawStoreCreateCommand crawlerRawStoreCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param crawlerRawStoreGateway
	 */
	@Autowired
	public void setCrawlerRawStoreGateway(CrawlerRawStoreGateway crawlerRawStoreGateway) {
		this.crawlerRawStoreGateway = crawlerRawStoreGateway;
	}
}
