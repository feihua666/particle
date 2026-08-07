package com.particle.crawler.client.execution.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.crawler.client.execution.dto.command.CrawlerDataStoreCreateCommand;
import com.particle.crawler.client.execution.dto.command.CrawlerDataStoreUpdateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerDataStoreVO;
/**
 * <p>
 * 爬虫结构数据存储 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:19
 */
public interface ICrawlerDataStoreApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param crawlerDataStoreCreateCommand
	 * @return
	 */
	SingleResponse<CrawlerDataStoreVO> create(CrawlerDataStoreCreateCommand crawlerDataStoreCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CrawlerDataStoreVO> delete(CommonIdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param crawlerDataStoreUpdateCommand
	 * @return
	 */
	SingleResponse<CrawlerDataStoreVO> update(CrawlerDataStoreUpdateCommand crawlerDataStoreUpdateCommand);
}
