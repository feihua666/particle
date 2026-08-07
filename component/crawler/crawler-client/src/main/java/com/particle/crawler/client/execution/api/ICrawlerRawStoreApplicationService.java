package com.particle.crawler.client.execution.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.crawler.client.execution.dto.command.CrawlerRawStoreCreateCommand;
import com.particle.crawler.client.execution.dto.command.CrawlerRawStoreUpdateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerRawStoreVO;
/**
 * <p>
 * 爬虫原始数据存储 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:47
 */
public interface ICrawlerRawStoreApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param crawlerRawStoreCreateCommand
	 * @return
	 */
	SingleResponse<CrawlerRawStoreVO> create(CrawlerRawStoreCreateCommand crawlerRawStoreCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CrawlerRawStoreVO> delete(CommonIdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param crawlerRawStoreUpdateCommand
	 * @return
	 */
	SingleResponse<CrawlerRawStoreVO> update(CrawlerRawStoreUpdateCommand crawlerRawStoreUpdateCommand);
}
