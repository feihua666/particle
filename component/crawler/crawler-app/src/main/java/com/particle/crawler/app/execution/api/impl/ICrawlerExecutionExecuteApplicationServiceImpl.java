package com.particle.crawler.app.execution.api.impl;

import com.particle.crawler.app.execution.executor.CrawlerExecutionExecuteCommandExecutor;
import com.particle.crawler.client.execution.api.ICrawlerExecutionExecuteApplicationService;
import com.particle.crawler.client.execution.dto.command.CrawlerExecutionExecuteCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerExecutionExecuteVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 执行实现
 * </p>
 *
 * @author yangwei
 * @since 2026/5/12 16:39
 */
@Transactional
@Service
@CatchAndLog
public class ICrawlerExecutionExecuteApplicationServiceImpl implements ICrawlerExecutionExecuteApplicationService {

    private CrawlerExecutionExecuteCommandExecutor crawlerExecutionExecuteCommandExecutor;

    @Override
    public SingleResponse<CrawlerExecutionExecuteVO> execute(CrawlerExecutionExecuteCommand crawlerExecutionExecuteCommand) {
        return crawlerExecutionExecuteCommandExecutor.execute(crawlerExecutionExecuteCommand);
    }

    @Autowired
    public void setCrawlerExecutionExecuteCommandExecutor(CrawlerExecutionExecuteCommandExecutor crawlerExecutionExecuteCommandExecutor) {
        this.crawlerExecutionExecuteCommandExecutor = crawlerExecutionExecuteCommandExecutor;
    }
}
