package com.particle.crawler.app.definition.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crawler.app.definition.executor.representation.CrawlerDefinitionHistoryQueryCommandExecutor;
import com.particle.crawler.client.definition.api.representation.ICrawlerDefinitionHistoryRepresentationApplicationService;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerDefinitionHistoryPageQueryCommand;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerDefinitionHistoryQueryListCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerDefinitionHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 爬虫定义历史 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:55
 */
@Service
@CatchAndLog
public class CrawlerDefinitionHistoryRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrawlerDefinitionHistoryRepresentationApplicationService {

    private CrawlerDefinitionHistoryQueryCommandExecutor crawlerDefinitionHistoryQueryCommandExecutor;

    @Override
    public SingleResponse<CrawlerDefinitionHistoryVO> queryDetail(CommonIdCommand detailCommand) {
        return crawlerDefinitionHistoryQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CrawlerDefinitionHistoryVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
        return crawlerDefinitionHistoryQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CrawlerDefinitionHistoryVO> pageQuery(CrawlerDefinitionHistoryPageQueryCommand crawlerDefinitionHistoryPageQueryCommand) {
        return crawlerDefinitionHistoryQueryCommandExecutor.execute(crawlerDefinitionHistoryPageQueryCommand);
    }

    @Override
    public MultiResponse<CrawlerDefinitionHistoryVO> queryList(CrawlerDefinitionHistoryQueryListCommand crawlerDefinitionHistoryQueryListCommand) {
        return crawlerDefinitionHistoryQueryCommandExecutor.execute(crawlerDefinitionHistoryQueryListCommand);
    }


    @Autowired
    public void setCrawlerDefinitionHistoryQueryCommandExecutor(CrawlerDefinitionHistoryQueryCommandExecutor crawlerDefinitionHistoryQueryCommandExecutor) {
        this.crawlerDefinitionHistoryQueryCommandExecutor = crawlerDefinitionHistoryQueryCommandExecutor;
    }
}
