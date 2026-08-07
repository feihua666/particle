package com.particle.crawler.app.execution.api.impl;

import com.particle.crawler.app.execution.executor.CrawlerRawStoreContentCreateCommandExecutor;
import com.particle.crawler.app.execution.executor.CrawlerRawStoreContentDeleteCommandExecutor;
import com.particle.crawler.app.execution.executor.CrawlerRawStoreContentUpdateCommandExecutor;
import com.particle.crawler.app.execution.executor.CrawlerRawStoreContentCommandExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.crawler.client.execution.dto.command.CrawlerRawStoreContentUpdateCommand;
import com.particle.crawler.client.execution.api.ICrawlerRawStoreContentApplicationService;
import com.particle.crawler.client.execution.dto.command.CrawlerRawStoreContentCreateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerRawStoreContentVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 爬虫原始数据存储内容 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:24:02
 */
@Transactional
@Service
@CatchAndLog
public class CrawlerRawStoreContentApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrawlerRawStoreContentApplicationService {

    private CrawlerRawStoreContentCreateCommandExecutor crawlerRawStoreContentCreateCommandExecutor;

    private CrawlerRawStoreContentDeleteCommandExecutor crawlerRawStoreContentDeleteCommandExecutor;

    private CrawlerRawStoreContentUpdateCommandExecutor crawlerRawStoreContentUpdateCommandExecutor;

    private CrawlerRawStoreContentCommandExecutor crawlerRawStoreContentCommandExecutor;


    @Override
    public SingleResponse<CrawlerRawStoreContentVO> create(CrawlerRawStoreContentCreateCommand crawlerRawStoreContentCreateCommand) {
        return crawlerRawStoreContentCreateCommandExecutor.execute(crawlerRawStoreContentCreateCommand);
    }

    @Override
    public SingleResponse<CrawlerRawStoreContentVO> delete(CommonIdCommand deleteCommand) {
        return crawlerRawStoreContentDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<CrawlerRawStoreContentVO> update(CrawlerRawStoreContentUpdateCommand crawlerRawStoreContentUpdateCommand) {
        return crawlerRawStoreContentUpdateCommandExecutor.execute(crawlerRawStoreContentUpdateCommand);
    }


    @Autowired
    public void setCrawlerRawStoreContentCreateCommandExecutor(CrawlerRawStoreContentCreateCommandExecutor crawlerRawStoreContentCreateCommandExecutor) {
        this.crawlerRawStoreContentCreateCommandExecutor = crawlerRawStoreContentCreateCommandExecutor;
    }

    @Autowired
    public void setCrawlerRawStoreContentDeleteCommandExecutor(CrawlerRawStoreContentDeleteCommandExecutor crawlerRawStoreContentDeleteCommandExecutor) {
        this.crawlerRawStoreContentDeleteCommandExecutor = crawlerRawStoreContentDeleteCommandExecutor;
    }
    @Autowired
    public void setCrawlerRawStoreContentUpdateCommandExecutor(CrawlerRawStoreContentUpdateCommandExecutor crawlerRawStoreContentUpdateCommandExecutor) {
        this.crawlerRawStoreContentUpdateCommandExecutor = crawlerRawStoreContentUpdateCommandExecutor;
    }
    @Autowired
    public void setCrawlerRawStoreContentCommandExecutor(CrawlerRawStoreContentCommandExecutor crawlerRawStoreContentCommandExecutor) {
        this.crawlerRawStoreContentCommandExecutor = crawlerRawStoreContentCommandExecutor;
    }
}
