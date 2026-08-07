package com.particle.crawler.app.execution.api.impl;

import com.particle.crawler.app.execution.executor.CrawlerExecutionCreateCommandExecutor;
import com.particle.crawler.app.execution.executor.CrawlerExecutionDeleteCommandExecutor;
import com.particle.crawler.app.execution.executor.CrawlerExecutionUpdateCommandExecutor;
import com.particle.crawler.app.execution.executor.CrawlerExecutionCommandExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.crawler.client.execution.dto.command.CrawlerExecutionUpdateCommand;
import com.particle.crawler.client.execution.api.ICrawlerExecutionApplicationService;
import com.particle.crawler.client.execution.dto.command.CrawlerExecutionCreateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerExecutionVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 爬虫执行实例 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@Transactional
@Service
@CatchAndLog
public class CrawlerExecutionApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrawlerExecutionApplicationService {

    private CrawlerExecutionCreateCommandExecutor crawlerExecutionCreateCommandExecutor;

    private CrawlerExecutionDeleteCommandExecutor crawlerExecutionDeleteCommandExecutor;

    private CrawlerExecutionUpdateCommandExecutor crawlerExecutionUpdateCommandExecutor;

    private CrawlerExecutionCommandExecutor crawlerExecutionCommandExecutor;


    @Override
    public SingleResponse<CrawlerExecutionVO> create(CrawlerExecutionCreateCommand crawlerExecutionCreateCommand) {
        return crawlerExecutionCreateCommandExecutor.execute(crawlerExecutionCreateCommand);
    }

    @Override
    public SingleResponse<CrawlerExecutionVO> delete(CommonIdCommand deleteCommand) {
        return crawlerExecutionDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<CrawlerExecutionVO> update(CrawlerExecutionUpdateCommand crawlerExecutionUpdateCommand) {
        return crawlerExecutionUpdateCommandExecutor.execute(crawlerExecutionUpdateCommand);
    }


    @Autowired
    public void setCrawlerExecutionCreateCommandExecutor(CrawlerExecutionCreateCommandExecutor crawlerExecutionCreateCommandExecutor) {
        this.crawlerExecutionCreateCommandExecutor = crawlerExecutionCreateCommandExecutor;
    }

    @Autowired
    public void setCrawlerExecutionDeleteCommandExecutor(CrawlerExecutionDeleteCommandExecutor crawlerExecutionDeleteCommandExecutor) {
        this.crawlerExecutionDeleteCommandExecutor = crawlerExecutionDeleteCommandExecutor;
    }
    @Autowired
    public void setCrawlerExecutionUpdateCommandExecutor(CrawlerExecutionUpdateCommandExecutor crawlerExecutionUpdateCommandExecutor) {
        this.crawlerExecutionUpdateCommandExecutor = crawlerExecutionUpdateCommandExecutor;
    }
    @Autowired
    public void setCrawlerExecutionCommandExecutor(CrawlerExecutionCommandExecutor crawlerExecutionCommandExecutor) {
        this.crawlerExecutionCommandExecutor = crawlerExecutionCommandExecutor;
    }
}
