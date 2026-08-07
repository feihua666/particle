package com.particle.crawler.app.execution.api.impl;

import com.particle.crawler.app.execution.executor.CrawlerRawStoreCreateCommandExecutor;
import com.particle.crawler.app.execution.executor.CrawlerRawStoreDeleteCommandExecutor;
import com.particle.crawler.app.execution.executor.CrawlerRawStoreUpdateCommandExecutor;
import com.particle.crawler.app.execution.executor.CrawlerRawStoreCommandExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.crawler.client.execution.dto.command.CrawlerRawStoreUpdateCommand;
import com.particle.crawler.client.execution.api.ICrawlerRawStoreApplicationService;
import com.particle.crawler.client.execution.dto.command.CrawlerRawStoreCreateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerRawStoreVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 爬虫原始数据存储 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:47
 */
@Transactional
@Service
@CatchAndLog
public class CrawlerRawStoreApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrawlerRawStoreApplicationService {

    private CrawlerRawStoreCreateCommandExecutor crawlerRawStoreCreateCommandExecutor;

    private CrawlerRawStoreDeleteCommandExecutor crawlerRawStoreDeleteCommandExecutor;

    private CrawlerRawStoreUpdateCommandExecutor crawlerRawStoreUpdateCommandExecutor;

    private CrawlerRawStoreCommandExecutor crawlerRawStoreCommandExecutor;


    @Override
    public SingleResponse<CrawlerRawStoreVO> create(CrawlerRawStoreCreateCommand crawlerRawStoreCreateCommand) {
        return crawlerRawStoreCreateCommandExecutor.execute(crawlerRawStoreCreateCommand);
    }

    @Override
    public SingleResponse<CrawlerRawStoreVO> delete(CommonIdCommand deleteCommand) {
        return crawlerRawStoreDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<CrawlerRawStoreVO> update(CrawlerRawStoreUpdateCommand crawlerRawStoreUpdateCommand) {
        return crawlerRawStoreUpdateCommandExecutor.execute(crawlerRawStoreUpdateCommand);
    }


    @Autowired
    public void setCrawlerRawStoreCreateCommandExecutor(CrawlerRawStoreCreateCommandExecutor crawlerRawStoreCreateCommandExecutor) {
        this.crawlerRawStoreCreateCommandExecutor = crawlerRawStoreCreateCommandExecutor;
    }

    @Autowired
    public void setCrawlerRawStoreDeleteCommandExecutor(CrawlerRawStoreDeleteCommandExecutor crawlerRawStoreDeleteCommandExecutor) {
        this.crawlerRawStoreDeleteCommandExecutor = crawlerRawStoreDeleteCommandExecutor;
    }
    @Autowired
    public void setCrawlerRawStoreUpdateCommandExecutor(CrawlerRawStoreUpdateCommandExecutor crawlerRawStoreUpdateCommandExecutor) {
        this.crawlerRawStoreUpdateCommandExecutor = crawlerRawStoreUpdateCommandExecutor;
    }
    @Autowired
    public void setCrawlerRawStoreCommandExecutor(CrawlerRawStoreCommandExecutor crawlerRawStoreCommandExecutor) {
        this.crawlerRawStoreCommandExecutor = crawlerRawStoreCommandExecutor;
    }
}
