package com.particle.crawler.app.definition.api.impl;

import com.particle.crawler.app.definition.executor.CrawlerDefinitionHistoryCreateCommandExecutor;
import com.particle.crawler.app.definition.executor.CrawlerDefinitionHistoryDeleteCommandExecutor;
import com.particle.crawler.app.definition.executor.CrawlerDefinitionHistoryUpdateCommandExecutor;
import com.particle.crawler.app.definition.executor.CrawlerDefinitionHistoryCommandExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.crawler.client.definition.dto.command.CrawlerDefinitionHistoryCreateDraftCommand;
import com.particle.crawler.client.definition.dto.command.CrawlerDefinitionHistoryUpdateCommand;
import com.particle.crawler.client.definition.api.ICrawlerDefinitionHistoryApplicationService;
import com.particle.crawler.client.definition.dto.command.CrawlerDefinitionHistoryCreateCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerDefinitionHistoryVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 爬虫定义历史 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:55
 */
@Transactional
@Service
@CatchAndLog
public class CrawlerDefinitionHistoryApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrawlerDefinitionHistoryApplicationService {

    private CrawlerDefinitionHistoryCreateCommandExecutor crawlerDefinitionHistoryCreateCommandExecutor;

    private CrawlerDefinitionHistoryDeleteCommandExecutor crawlerDefinitionHistoryDeleteCommandExecutor;

    private CrawlerDefinitionHistoryUpdateCommandExecutor crawlerDefinitionHistoryUpdateCommandExecutor;

    private CrawlerDefinitionHistoryCommandExecutor crawlerDefinitionHistoryCommandExecutor;


    @Override
    public SingleResponse<CrawlerDefinitionHistoryVO> create(CrawlerDefinitionHistoryCreateCommand crawlerDefinitionHistoryCreateCommand) {
        return crawlerDefinitionHistoryCreateCommandExecutor.execute(crawlerDefinitionHistoryCreateCommand);
    }

    @Override
    public SingleResponse<CrawlerDefinitionHistoryVO> createDraft(CrawlerDefinitionHistoryCreateDraftCommand crawlerDefinitionHistoryCreateDraftCommand) {
        return crawlerDefinitionHistoryCreateCommandExecutor.createDraft(crawlerDefinitionHistoryCreateDraftCommand);
    }

    @Override
    public SingleResponse<CrawlerDefinitionHistoryVO> delete(CommonIdCommand deleteCommand) {
        return crawlerDefinitionHistoryDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<CrawlerDefinitionHistoryVO> update(CrawlerDefinitionHistoryUpdateCommand crawlerDefinitionHistoryUpdateCommand) {
        return crawlerDefinitionHistoryUpdateCommandExecutor.execute(crawlerDefinitionHistoryUpdateCommand);
    }


    @Autowired
    public void setCrawlerDefinitionHistoryCreateCommandExecutor(CrawlerDefinitionHistoryCreateCommandExecutor crawlerDefinitionHistoryCreateCommandExecutor) {
        this.crawlerDefinitionHistoryCreateCommandExecutor = crawlerDefinitionHistoryCreateCommandExecutor;
    }

    @Autowired
    public void setCrawlerDefinitionHistoryDeleteCommandExecutor(CrawlerDefinitionHistoryDeleteCommandExecutor crawlerDefinitionHistoryDeleteCommandExecutor) {
        this.crawlerDefinitionHistoryDeleteCommandExecutor = crawlerDefinitionHistoryDeleteCommandExecutor;
    }
    @Autowired
    public void setCrawlerDefinitionHistoryUpdateCommandExecutor(CrawlerDefinitionHistoryUpdateCommandExecutor crawlerDefinitionHistoryUpdateCommandExecutor) {
        this.crawlerDefinitionHistoryUpdateCommandExecutor = crawlerDefinitionHistoryUpdateCommandExecutor;
    }
    @Autowired
    public void setCrawlerDefinitionHistoryCommandExecutor(CrawlerDefinitionHistoryCommandExecutor crawlerDefinitionHistoryCommandExecutor) {
        this.crawlerDefinitionHistoryCommandExecutor = crawlerDefinitionHistoryCommandExecutor;
    }
}
