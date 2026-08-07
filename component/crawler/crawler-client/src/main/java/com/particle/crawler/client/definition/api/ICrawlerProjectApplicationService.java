package com.particle.crawler.client.definition.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.crawler.client.definition.dto.command.CrawlerProjectCreateCommand;
import com.particle.crawler.client.definition.dto.command.CrawlerProjectUpdateCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerProjectVO;
/**
 * <p>
 * 爬虫项目 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:21
 */
public interface ICrawlerProjectApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param crawlerProjectCreateCommand
	 * @return
	 */
	SingleResponse<CrawlerProjectVO> create(CrawlerProjectCreateCommand crawlerProjectCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CrawlerProjectVO> delete(CommonIdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param crawlerProjectUpdateCommand
	 * @return
	 */
	SingleResponse<CrawlerProjectVO> update(CrawlerProjectUpdateCommand crawlerProjectUpdateCommand);
}
