package com.particle.crawler.client.execution.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.crawler.client.execution.dto.command.CrawlerExecutionCreateCommand;
import com.particle.crawler.client.execution.dto.command.CrawlerExecutionExecuteCommand;
import com.particle.crawler.client.execution.dto.command.CrawlerExecutionUpdateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerExecutionExecuteVO;
import com.particle.crawler.client.execution.dto.data.CrawlerExecutionVO;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 爬虫执行 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2026-05-12 16:38:35
 */
public interface ICrawlerExecutionExecuteApplicationService extends IBaseApplicationService {

	/**
	 * 执行
	 * @param crawlerExecutionExecuteCommand
	 * @return
	 */
	public SingleResponse<CrawlerExecutionExecuteVO> execute(CrawlerExecutionExecuteCommand crawlerExecutionExecuteCommand);

}
