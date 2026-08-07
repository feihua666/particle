package com.particle.crawler.app.execution.api.impl;

import com.particle.crawler.app.execution.executor.CrawlerDataStoreContentCreateCommandExecutor;
import com.particle.crawler.app.execution.executor.CrawlerDataStoreContentDeleteCommandExecutor;
import com.particle.crawler.app.execution.executor.CrawlerDataStoreContentUpdateCommandExecutor;
import com.particle.crawler.app.execution.executor.CrawlerDataStoreContentCommandExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.crawler.client.execution.dto.command.CrawlerDataStoreContentUpdateCommand;
import com.particle.crawler.client.execution.api.ICrawlerDataStoreContentApplicationService;
import com.particle.crawler.client.execution.dto.command.CrawlerDataStoreContentCreateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerDataStoreContentVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 爬虫结构数据存储内容 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:34
 */
@Transactional
@Service
@CatchAndLog
public class CrawlerDataStoreContentApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrawlerDataStoreContentApplicationService {

    private CrawlerDataStoreContentCreateCommandExecutor crawlerDataStoreContentCreateCommandExecutor;

    private CrawlerDataStoreContentDeleteCommandExecutor crawlerDataStoreContentDeleteCommandExecutor;

    private CrawlerDataStoreContentUpdateCommandExecutor crawlerDataStoreContentUpdateCommandExecutor;

    private CrawlerDataStoreContentCommandExecutor crawlerDataStoreContentCommandExecutor;


    @Override
    public SingleResponse<CrawlerDataStoreContentVO> create(CrawlerDataStoreContentCreateCommand crawlerDataStoreContentCreateCommand) {
        return crawlerDataStoreContentCreateCommandExecutor.execute(crawlerDataStoreContentCreateCommand);
    }

    @Override
    public SingleResponse<CrawlerDataStoreContentVO> delete(CommonIdCommand deleteCommand) {
        return crawlerDataStoreContentDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<CrawlerDataStoreContentVO> update(CrawlerDataStoreContentUpdateCommand crawlerDataStoreContentUpdateCommand) {
        return crawlerDataStoreContentUpdateCommandExecutor.execute(crawlerDataStoreContentUpdateCommand);
    }


    @Autowired
    public void setCrawlerDataStoreContentCreateCommandExecutor(CrawlerDataStoreContentCreateCommandExecutor crawlerDataStoreContentCreateCommandExecutor) {
        this.crawlerDataStoreContentCreateCommandExecutor = crawlerDataStoreContentCreateCommandExecutor;
    }

    @Autowired
    public void setCrawlerDataStoreContentDeleteCommandExecutor(CrawlerDataStoreContentDeleteCommandExecutor crawlerDataStoreContentDeleteCommandExecutor) {
        this.crawlerDataStoreContentDeleteCommandExecutor = crawlerDataStoreContentDeleteCommandExecutor;
    }
    @Autowired
    public void setCrawlerDataStoreContentUpdateCommandExecutor(CrawlerDataStoreContentUpdateCommandExecutor crawlerDataStoreContentUpdateCommandExecutor) {
        this.crawlerDataStoreContentUpdateCommandExecutor = crawlerDataStoreContentUpdateCommandExecutor;
    }
    @Autowired
    public void setCrawlerDataStoreContentCommandExecutor(CrawlerDataStoreContentCommandExecutor crawlerDataStoreContentCommandExecutor) {
        this.crawlerDataStoreContentCommandExecutor = crawlerDataStoreContentCommandExecutor;
    }
}
