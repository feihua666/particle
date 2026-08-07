package com.particle.crawler.app.execution.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crawler.app.execution.executor.representation.CrawlerDataStoreQueryCommandExecutor;
import com.particle.crawler.client.execution.api.representation.ICrawlerDataStoreRepresentationApplicationService;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerDataStorePageQueryCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerDataStoreQueryListCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerDataStoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 爬虫结构数据存储 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:19
 */
@Service
@CatchAndLog
public class CrawlerDataStoreRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrawlerDataStoreRepresentationApplicationService {

    private CrawlerDataStoreQueryCommandExecutor crawlerDataStoreQueryCommandExecutor;

    @Override
    public SingleResponse<CrawlerDataStoreVO> queryDetail(CommonIdCommand detailCommand) {
        return crawlerDataStoreQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CrawlerDataStoreVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
        return crawlerDataStoreQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CrawlerDataStoreVO> pageQuery(CrawlerDataStorePageQueryCommand crawlerDataStorePageQueryCommand) {
        return crawlerDataStoreQueryCommandExecutor.execute(crawlerDataStorePageQueryCommand);
    }

    @Override
    public MultiResponse<CrawlerDataStoreVO> queryList(CrawlerDataStoreQueryListCommand crawlerDataStoreQueryListCommand) {
        return crawlerDataStoreQueryCommandExecutor.execute(crawlerDataStoreQueryListCommand);
    }


    @Autowired
    public void setCrawlerDataStoreQueryCommandExecutor(CrawlerDataStoreQueryCommandExecutor crawlerDataStoreQueryCommandExecutor) {
        this.crawlerDataStoreQueryCommandExecutor = crawlerDataStoreQueryCommandExecutor;
    }
}
