package com.particle.crawler.app.definition.api.impl;

import com.particle.crawler.app.definition.executor.CrawlerDefinitionCreateCommandExecutor;
import com.particle.crawler.app.definition.executor.CrawlerDefinitionDeleteCommandExecutor;
import com.particle.crawler.app.definition.executor.CrawlerDefinitionUpdateCommandExecutor;
import com.particle.crawler.app.definition.executor.CrawlerDefinitionCommandExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.crawler.client.definition.dto.command.CrawlerDefinitionUpdateCommand;
import com.particle.crawler.client.definition.api.ICrawlerDefinitionApplicationService;
import com.particle.crawler.client.definition.dto.command.CrawlerDefinitionCreateCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerDefinitionVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 爬虫定义 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:37
 */
@Transactional
@Service
@CatchAndLog
public class CrawlerDefinitionApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrawlerDefinitionApplicationService {

    private CrawlerDefinitionCreateCommandExecutor crawlerDefinitionCreateCommandExecutor;

    private CrawlerDefinitionDeleteCommandExecutor crawlerDefinitionDeleteCommandExecutor;

    private CrawlerDefinitionUpdateCommandExecutor crawlerDefinitionUpdateCommandExecutor;

    private CrawlerDefinitionCommandExecutor crawlerDefinitionCommandExecutor;


    @Override
    public SingleResponse<CrawlerDefinitionVO> create(CrawlerDefinitionCreateCommand crawlerDefinitionCreateCommand) {
        return crawlerDefinitionCreateCommandExecutor.execute(crawlerDefinitionCreateCommand);
    }

    @Override
    public SingleResponse<CrawlerDefinitionVO> delete(CommonIdCommand deleteCommand) {
        return crawlerDefinitionDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<CrawlerDefinitionVO> update(CrawlerDefinitionUpdateCommand crawlerDefinitionUpdateCommand) {
        return crawlerDefinitionUpdateCommandExecutor.execute(crawlerDefinitionUpdateCommand);
    }


    @Autowired
    public void setCrawlerDefinitionCreateCommandExecutor(CrawlerDefinitionCreateCommandExecutor crawlerDefinitionCreateCommandExecutor) {
        this.crawlerDefinitionCreateCommandExecutor = crawlerDefinitionCreateCommandExecutor;
    }

    @Autowired
    public void setCrawlerDefinitionDeleteCommandExecutor(CrawlerDefinitionDeleteCommandExecutor crawlerDefinitionDeleteCommandExecutor) {
        this.crawlerDefinitionDeleteCommandExecutor = crawlerDefinitionDeleteCommandExecutor;
    }
    @Autowired
    public void setCrawlerDefinitionUpdateCommandExecutor(CrawlerDefinitionUpdateCommandExecutor crawlerDefinitionUpdateCommandExecutor) {
        this.crawlerDefinitionUpdateCommandExecutor = crawlerDefinitionUpdateCommandExecutor;
    }
    @Autowired
    public void setCrawlerDefinitionCommandExecutor(CrawlerDefinitionCommandExecutor crawlerDefinitionCommandExecutor) {
        this.crawlerDefinitionCommandExecutor = crawlerDefinitionCommandExecutor;
    }
}
