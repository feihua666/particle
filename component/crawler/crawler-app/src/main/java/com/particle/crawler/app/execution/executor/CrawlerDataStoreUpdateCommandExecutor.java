package com.particle.crawler.app.execution.executor;

import com.particle.crawler.app.execution.structmapping.CrawlerDataStoreAppStructMapping;
import com.particle.crawler.client.execution.dto.command.CrawlerDataStoreUpdateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerDataStoreVO;
import com.particle.crawler.domain.execution.CrawlerDataStore;
import com.particle.crawler.domain.execution.CrawlerDataStoreId;
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
 * 爬虫结构数据存储 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CrawlerDataStoreUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerDataStoreGateway crawlerDataStoreGateway;

	/**
	 * 执行 爬虫结构数据存储 更新指令
	 * @param crawlerDataStoreUpdateCommand
	 * @return
	 */
	public SingleResponse<CrawlerDataStoreVO> execute(@Valid CrawlerDataStoreUpdateCommand crawlerDataStoreUpdateCommand) {
		CrawlerDataStore crawlerDataStore = createByCrawlerDataStoreUpdateCommand(crawlerDataStoreUpdateCommand);
		crawlerDataStore.setUpdateControl(crawlerDataStoreUpdateCommand);
		boolean save = crawlerDataStoreGateway.save(crawlerDataStore);
		if (save) {
			return SingleResponse.of(CrawlerDataStoreAppStructMapping.instance.toCrawlerDataStoreVO(crawlerDataStore));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据爬虫结构数据存储更新指令创建爬虫结构数据存储模型
	 * @param crawlerDataStoreUpdateCommand
	 * @return
	 */
	private CrawlerDataStore createByCrawlerDataStoreUpdateCommand(CrawlerDataStoreUpdateCommand crawlerDataStoreUpdateCommand){
		CrawlerDataStore crawlerDataStore = CrawlerDataStore.create();
		CrawlerDataStoreUpdateCommandToCrawlerDataStoreMapping.instance.fillCrawlerDataStoreByCrawlerDataStoreUpdateCommand(crawlerDataStore, crawlerDataStoreUpdateCommand);
		return crawlerDataStore;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CrawlerDataStoreUpdateCommandToCrawlerDataStoreMapping{
		CrawlerDataStoreUpdateCommandToCrawlerDataStoreMapping instance = Mappers.getMapper(CrawlerDataStoreUpdateCommandToCrawlerDataStoreMapping.class );

		default CrawlerDataStoreId map(Long id){
			if (id == null) {
				return null;
			}
			return CrawlerDataStoreId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crawlerDataStore
		 * @param crawlerDataStoreUpdateCommand
		 */
		void fillCrawlerDataStoreByCrawlerDataStoreUpdateCommand(@MappingTarget CrawlerDataStore crawlerDataStore, CrawlerDataStoreUpdateCommand crawlerDataStoreUpdateCommand);
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
