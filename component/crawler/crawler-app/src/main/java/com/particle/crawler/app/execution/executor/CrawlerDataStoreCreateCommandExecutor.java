package com.particle.crawler.app.execution.executor;

import com.particle.crawler.app.execution.structmapping.CrawlerDataStoreAppStructMapping;
import com.particle.crawler.client.execution.dto.command.CrawlerDataStoreCreateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerDataStoreVO;
import com.particle.crawler.domain.execution.CrawlerDataStore;
import com.particle.crawler.domain.execution.gateway.CrawlerDataStoreGateway;
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
 * 爬虫结构数据存储 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:19
 */
@Component
@Validated
public class CrawlerDataStoreCreateCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerDataStoreGateway crawlerDataStoreGateway;

	/**
	 * 执行爬虫结构数据存储添加指令
	 * @param crawlerDataStoreCreateCommand
	 * @return
	 */
	public SingleResponse<CrawlerDataStoreVO> execute(@Valid CrawlerDataStoreCreateCommand crawlerDataStoreCreateCommand) {
		CrawlerDataStore crawlerDataStore = createByCrawlerDataStoreCreateCommand(crawlerDataStoreCreateCommand);
		crawlerDataStore.setAddControl(crawlerDataStoreCreateCommand);
		boolean save = crawlerDataStoreGateway.save(crawlerDataStore);
		if (save) {
			return SingleResponse.of(CrawlerDataStoreAppStructMapping.instance.toCrawlerDataStoreVO(crawlerDataStore));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据爬虫结构数据存储创建指令创建爬虫结构数据存储模型
	 * @param crawlerDataStoreCreateCommand
	 * @return
	 */
	private CrawlerDataStore createByCrawlerDataStoreCreateCommand(CrawlerDataStoreCreateCommand crawlerDataStoreCreateCommand){
		CrawlerDataStore crawlerDataStore = CrawlerDataStore.create();
		CrawlerDataStoreCreateCommandToCrawlerDataStoreMapping.instance.fillCrawlerDataStoreByCrawlerDataStoreCreateCommand(crawlerDataStore, crawlerDataStoreCreateCommand);
		return crawlerDataStore;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CrawlerDataStoreCreateCommandToCrawlerDataStoreMapping{
		CrawlerDataStoreCreateCommandToCrawlerDataStoreMapping instance = Mappers.getMapper( CrawlerDataStoreCreateCommandToCrawlerDataStoreMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crawlerDataStore
		 * @param crawlerDataStoreCreateCommand
		 */
		void fillCrawlerDataStoreByCrawlerDataStoreCreateCommand(@MappingTarget CrawlerDataStore crawlerDataStore, CrawlerDataStoreCreateCommand crawlerDataStoreCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param crawlerDataStoreGateway
	 */
	@Autowired
	public void setCrawlerDataStoreGateway(CrawlerDataStoreGateway crawlerDataStoreGateway) {
		this.crawlerDataStoreGateway = crawlerDataStoreGateway;
	}
}
