package com.particle.crawler.client.execution.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.crawler.client.execution.dto.command.CrawlerExecutionExecuteCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.crawler.client.execution.dto.command.CrawlerExecutionCreateCommand;
import com.particle.crawler.client.execution.dto.command.CrawlerExecutionUpdateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerExecutionVO;
/**
 * <p>
 * 爬虫执行实例 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
public interface ICrawlerExecutionApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param crawlerExecutionCreateCommand
	 * @return
	 */
	SingleResponse<CrawlerExecutionVO> create(CrawlerExecutionCreateCommand crawlerExecutionCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CrawlerExecutionVO> delete(CommonIdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param crawlerExecutionUpdateCommand
	 * @return
	 */
	SingleResponse<CrawlerExecutionVO> update(CrawlerExecutionUpdateCommand crawlerExecutionUpdateCommand);
}
