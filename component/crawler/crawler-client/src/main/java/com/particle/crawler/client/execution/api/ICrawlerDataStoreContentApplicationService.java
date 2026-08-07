package com.particle.crawler.client.execution.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.crawler.client.execution.dto.command.CrawlerDataStoreContentCreateCommand;
import com.particle.crawler.client.execution.dto.command.CrawlerDataStoreContentUpdateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerDataStoreContentVO;
/**
 * <p>
 * 爬虫结构数据存储内容 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:34
 */
public interface ICrawlerDataStoreContentApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param crawlerDataStoreContentCreateCommand
	 * @return
	 */
	SingleResponse<CrawlerDataStoreContentVO> create(CrawlerDataStoreContentCreateCommand crawlerDataStoreContentCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CrawlerDataStoreContentVO> delete(CommonIdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param crawlerDataStoreContentUpdateCommand
	 * @return
	 */
	SingleResponse<CrawlerDataStoreContentVO> update(CrawlerDataStoreContentUpdateCommand crawlerDataStoreContentUpdateCommand);
}
