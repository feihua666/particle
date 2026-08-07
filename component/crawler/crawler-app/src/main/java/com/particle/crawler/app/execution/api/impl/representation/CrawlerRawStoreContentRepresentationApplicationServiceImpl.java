package com.particle.crawler.app.execution.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crawler.app.execution.executor.representation.CrawlerRawStoreContentQueryCommandExecutor;
import com.particle.crawler.client.execution.api.representation.ICrawlerRawStoreContentRepresentationApplicationService;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerRawStoreContentPageQueryCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerRawStoreContentQueryListCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerRawStoreContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 爬虫原始数据存储内容 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:24:02
 */
@Service
@CatchAndLog
public class CrawlerRawStoreContentRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrawlerRawStoreContentRepresentationApplicationService {

    private CrawlerRawStoreContentQueryCommandExecutor crawlerRawStoreContentQueryCommandExecutor;

    @Override
    public SingleResponse<CrawlerRawStoreContentVO> queryDetail(CommonIdCommand detailCommand) {
        return crawlerRawStoreContentQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CrawlerRawStoreContentVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
        return crawlerRawStoreContentQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CrawlerRawStoreContentVO> pageQuery(CrawlerRawStoreContentPageQueryCommand crawlerRawStoreContentPageQueryCommand) {
        return crawlerRawStoreContentQueryCommandExecutor.execute(crawlerRawStoreContentPageQueryCommand);
    }

    @Override
    public MultiResponse<CrawlerRawStoreContentVO> queryList(CrawlerRawStoreContentQueryListCommand crawlerRawStoreContentQueryListCommand) {
        return crawlerRawStoreContentQueryCommandExecutor.execute(crawlerRawStoreContentQueryListCommand);
    }


    @Autowired
    public void setCrawlerRawStoreContentQueryCommandExecutor(CrawlerRawStoreContentQueryCommandExecutor crawlerRawStoreContentQueryCommandExecutor) {
        this.crawlerRawStoreContentQueryCommandExecutor = crawlerRawStoreContentQueryCommandExecutor;
    }
}
