package com.particle.crawler.client.execution.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.crawler.client.execution.dto.command.CrawlerRawStoreContentCreateCommand;
import com.particle.crawler.client.execution.dto.command.CrawlerRawStoreContentUpdateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerRawStoreContentVO;
/**
 * <p>
 * 爬虫原始数据存储内容 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:24:02
 */
public interface ICrawlerRawStoreContentApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param crawlerRawStoreContentCreateCommand
	 * @return
	 */
	SingleResponse<CrawlerRawStoreContentVO> create(CrawlerRawStoreContentCreateCommand crawlerRawStoreContentCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CrawlerRawStoreContentVO> delete(CommonIdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param crawlerRawStoreContentUpdateCommand
	 * @return
	 */
	SingleResponse<CrawlerRawStoreContentVO> update(CrawlerRawStoreContentUpdateCommand crawlerRawStoreContentUpdateCommand);
}
