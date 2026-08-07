package com.particle.crawler.app.execution.executor;

import com.particle.crawler.app.execution.structmapping.CrawlerRawStoreAppStructMapping;
import com.particle.crawler.client.execution.dto.command.CrawlerRawStoreUpdateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerRawStoreVO;
import com.particle.crawler.domain.execution.CrawlerRawStore;
import com.particle.crawler.domain.execution.CrawlerRawStoreId;
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
 * 爬虫原始数据存储 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CrawlerRawStoreUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CrawlerRawStoreGateway crawlerRawStoreGateway;

	/**
	 * 执行 爬虫原始数据存储 更新指令
	 * @param crawlerRawStoreUpdateCommand
	 * @return
	 */
	public SingleResponse<CrawlerRawStoreVO> execute(@Valid CrawlerRawStoreUpdateCommand crawlerRawStoreUpdateCommand) {
		CrawlerRawStore crawlerRawStore = createByCrawlerRawStoreUpdateCommand(crawlerRawStoreUpdateCommand);
		crawlerRawStore.setUpdateControl(crawlerRawStoreUpdateCommand);
		boolean save = crawlerRawStoreGateway.save(crawlerRawStore);
		if (save) {
			return SingleResponse.of(CrawlerRawStoreAppStructMapping.instance.toCrawlerRawStoreVO(crawlerRawStore));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据爬虫原始数据存储更新指令创建爬虫原始数据存储模型
	 * @param crawlerRawStoreUpdateCommand
	 * @return
	 */
	private CrawlerRawStore createByCrawlerRawStoreUpdateCommand(CrawlerRawStoreUpdateCommand crawlerRawStoreUpdateCommand){
		CrawlerRawStore crawlerRawStore = CrawlerRawStore.create();
		CrawlerRawStoreUpdateCommandToCrawlerRawStoreMapping.instance.fillCrawlerRawStoreByCrawlerRawStoreUpdateCommand(crawlerRawStore, crawlerRawStoreUpdateCommand);
		return crawlerRawStore;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CrawlerRawStoreUpdateCommandToCrawlerRawStoreMapping{
		CrawlerRawStoreUpdateCommandToCrawlerRawStoreMapping instance = Mappers.getMapper(CrawlerRawStoreUpdateCommandToCrawlerRawStoreMapping.class );

		default CrawlerRawStoreId map(Long id){
			if (id == null) {
				return null;
			}
			return CrawlerRawStoreId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crawlerRawStore
		 * @param crawlerRawStoreUpdateCommand
		 */
		void fillCrawlerRawStoreByCrawlerRawStoreUpdateCommand(@MappingTarget CrawlerRawStore crawlerRawStore, CrawlerRawStoreUpdateCommand crawlerRawStoreUpdateCommand);
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
