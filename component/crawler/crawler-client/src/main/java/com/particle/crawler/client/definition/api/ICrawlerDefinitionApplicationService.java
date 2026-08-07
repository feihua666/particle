package com.particle.crawler.client.definition.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.crawler.client.definition.dto.command.CrawlerDefinitionCreateCommand;
import com.particle.crawler.client.definition.dto.command.CrawlerDefinitionUpdateCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerDefinitionVO;
/**
 * <p>
 * 爬虫定义 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:37
 */
public interface ICrawlerDefinitionApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param crawlerDefinitionCreateCommand
	 * @return
	 */
	SingleResponse<CrawlerDefinitionVO> create(CrawlerDefinitionCreateCommand crawlerDefinitionCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CrawlerDefinitionVO> delete(CommonIdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param crawlerDefinitionUpdateCommand
	 * @return
	 */
	SingleResponse<CrawlerDefinitionVO> update(CrawlerDefinitionUpdateCommand crawlerDefinitionUpdateCommand);
}
