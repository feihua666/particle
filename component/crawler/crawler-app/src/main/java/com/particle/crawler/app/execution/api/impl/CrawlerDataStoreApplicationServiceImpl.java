package com.particle.crawler.app.execution.api.impl;

import com.particle.crawler.app.execution.executor.CrawlerDataStoreCreateCommandExecutor;
import com.particle.crawler.app.execution.executor.CrawlerDataStoreDeleteCommandExecutor;
import com.particle.crawler.app.execution.executor.CrawlerDataStoreUpdateCommandExecutor;
import com.particle.crawler.app.execution.executor.CrawlerDataStoreCommandExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.crawler.client.execution.dto.command.CrawlerDataStoreUpdateCommand;
import com.particle.crawler.client.execution.api.ICrawlerDataStoreApplicationService;
import com.particle.crawler.client.execution.dto.command.CrawlerDataStoreCreateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerDataStoreVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 爬虫结构数据存储 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:19
 */
@Transactional
@Service
@CatchAndLog
public class CrawlerDataStoreApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrawlerDataStoreApplicationService {

    private CrawlerDataStoreCreateCommandExecutor crawlerDataStoreCreateCommandExecutor;

    private CrawlerDataStoreDeleteCommandExecutor crawlerDataStoreDeleteCommandExecutor;

    private CrawlerDataStoreUpdateCommandExecutor crawlerDataStoreUpdateCommandExecutor;

    private CrawlerDataStoreCommandExecutor crawlerDataStoreCommandExecutor;


    @Override
    public SingleResponse<CrawlerDataStoreVO> create(CrawlerDataStoreCreateCommand crawlerDataStoreCreateCommand) {
        return crawlerDataStoreCreateCommandExecutor.execute(crawlerDataStoreCreateCommand);
    }

    @Override
    public SingleResponse<CrawlerDataStoreVO> delete(CommonIdCommand deleteCommand) {
        return crawlerDataStoreDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<CrawlerDataStoreVO> update(CrawlerDataStoreUpdateCommand crawlerDataStoreUpdateCommand) {
        return crawlerDataStoreUpdateCommandExecutor.execute(crawlerDataStoreUpdateCommand);
    }


    @Autowired
    public void setCrawlerDataStoreCreateCommandExecutor(CrawlerDataStoreCreateCommandExecutor crawlerDataStoreCreateCommandExecutor) {
        this.crawlerDataStoreCreateCommandExecutor = crawlerDataStoreCreateCommandExecutor;
    }

    @Autowired
    public void setCrawlerDataStoreDeleteCommandExecutor(CrawlerDataStoreDeleteCommandExecutor crawlerDataStoreDeleteCommandExecutor) {
        this.crawlerDataStoreDeleteCommandExecutor = crawlerDataStoreDeleteCommandExecutor;
    }
    @Autowired
    public void setCrawlerDataStoreUpdateCommandExecutor(CrawlerDataStoreUpdateCommandExecutor crawlerDataStoreUpdateCommandExecutor) {
        this.crawlerDataStoreUpdateCommandExecutor = crawlerDataStoreUpdateCommandExecutor;
    }
    @Autowired
    public void setCrawlerDataStoreCommandExecutor(CrawlerDataStoreCommandExecutor crawlerDataStoreCommandExecutor) {
        this.crawlerDataStoreCommandExecutor = crawlerDataStoreCommandExecutor;
    }
}
