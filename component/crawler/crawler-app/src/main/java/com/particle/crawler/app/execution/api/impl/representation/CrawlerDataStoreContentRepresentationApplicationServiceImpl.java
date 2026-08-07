package com.particle.crawler.app.execution.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crawler.app.execution.executor.representation.CrawlerDataStoreContentQueryCommandExecutor;
import com.particle.crawler.client.execution.api.representation.ICrawlerDataStoreContentRepresentationApplicationService;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerDataStoreContentPageQueryCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerDataStoreContentQueryListCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerDataStoreContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 爬虫结构数据存储内容 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:34
 */
@Service
@CatchAndLog
public class CrawlerDataStoreContentRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrawlerDataStoreContentRepresentationApplicationService {

    private CrawlerDataStoreContentQueryCommandExecutor crawlerDataStoreContentQueryCommandExecutor;

    @Override
    public SingleResponse<CrawlerDataStoreContentVO> queryDetail(CommonIdCommand detailCommand) {
        return crawlerDataStoreContentQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CrawlerDataStoreContentVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
        return crawlerDataStoreContentQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CrawlerDataStoreContentVO> pageQuery(CrawlerDataStoreContentPageQueryCommand crawlerDataStoreContentPageQueryCommand) {
        return crawlerDataStoreContentQueryCommandExecutor.execute(crawlerDataStoreContentPageQueryCommand);
    }

    @Override
    public MultiResponse<CrawlerDataStoreContentVO> queryList(CrawlerDataStoreContentQueryListCommand crawlerDataStoreContentQueryListCommand) {
        return crawlerDataStoreContentQueryCommandExecutor.execute(crawlerDataStoreContentQueryListCommand);
    }


    @Autowired
    public void setCrawlerDataStoreContentQueryCommandExecutor(CrawlerDataStoreContentQueryCommandExecutor crawlerDataStoreContentQueryCommandExecutor) {
        this.crawlerDataStoreContentQueryCommandExecutor = crawlerDataStoreContentQueryCommandExecutor;
    }
}
