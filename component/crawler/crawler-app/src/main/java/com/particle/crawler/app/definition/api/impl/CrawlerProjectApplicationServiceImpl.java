package com.particle.crawler.app.definition.api.impl;

import com.particle.crawler.app.definition.executor.CrawlerProjectCreateCommandExecutor;
import com.particle.crawler.app.definition.executor.CrawlerProjectDeleteCommandExecutor;
import com.particle.crawler.app.definition.executor.CrawlerProjectUpdateCommandExecutor;
import com.particle.crawler.app.definition.executor.CrawlerProjectCommandExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.crawler.client.definition.dto.command.CrawlerProjectUpdateCommand;
import com.particle.crawler.client.definition.api.ICrawlerProjectApplicationService;
import com.particle.crawler.client.definition.dto.command.CrawlerProjectCreateCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerProjectVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 爬虫项目 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:21
 */
@Transactional
@Service
@CatchAndLog
public class CrawlerProjectApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrawlerProjectApplicationService {

    private CrawlerProjectCreateCommandExecutor crawlerProjectCreateCommandExecutor;

    private CrawlerProjectDeleteCommandExecutor crawlerProjectDeleteCommandExecutor;

    private CrawlerProjectUpdateCommandExecutor crawlerProjectUpdateCommandExecutor;

    private CrawlerProjectCommandExecutor crawlerProjectCommandExecutor;


    @Override
    public SingleResponse<CrawlerProjectVO> create(CrawlerProjectCreateCommand crawlerProjectCreateCommand) {
        return crawlerProjectCreateCommandExecutor.execute(crawlerProjectCreateCommand);
    }

    @Override
    public SingleResponse<CrawlerProjectVO> delete(CommonIdCommand deleteCommand) {
        return crawlerProjectDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<CrawlerProjectVO> update(CrawlerProjectUpdateCommand crawlerProjectUpdateCommand) {
        return crawlerProjectUpdateCommandExecutor.execute(crawlerProjectUpdateCommand);
    }


    @Autowired
    public void setCrawlerProjectCreateCommandExecutor(CrawlerProjectCreateCommandExecutor crawlerProjectCreateCommandExecutor) {
        this.crawlerProjectCreateCommandExecutor = crawlerProjectCreateCommandExecutor;
    }

    @Autowired
    public void setCrawlerProjectDeleteCommandExecutor(CrawlerProjectDeleteCommandExecutor crawlerProjectDeleteCommandExecutor) {
        this.crawlerProjectDeleteCommandExecutor = crawlerProjectDeleteCommandExecutor;
    }
    @Autowired
    public void setCrawlerProjectUpdateCommandExecutor(CrawlerProjectUpdateCommandExecutor crawlerProjectUpdateCommandExecutor) {
        this.crawlerProjectUpdateCommandExecutor = crawlerProjectUpdateCommandExecutor;
    }
    @Autowired
    public void setCrawlerProjectCommandExecutor(CrawlerProjectCommandExecutor crawlerProjectCommandExecutor) {
        this.crawlerProjectCommandExecutor = crawlerProjectCommandExecutor;
    }
}
