package com.particle.crawler.app.execution.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crawler.app.execution.executor.representation.CrawlerRawStoreQueryCommandExecutor;
import com.particle.crawler.client.execution.api.representation.ICrawlerRawStoreRepresentationApplicationService;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerRawStorePageQueryCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerRawStoreQueryListCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerRawStoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 爬虫原始数据存储 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:47
 */
@Service
@CatchAndLog
public class CrawlerRawStoreRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrawlerRawStoreRepresentationApplicationService {

    private CrawlerRawStoreQueryCommandExecutor crawlerRawStoreQueryCommandExecutor;

    @Override
    public SingleResponse<CrawlerRawStoreVO> queryDetail(CommonIdCommand detailCommand) {
        return crawlerRawStoreQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CrawlerRawStoreVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
        return crawlerRawStoreQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CrawlerRawStoreVO> pageQuery(CrawlerRawStorePageQueryCommand crawlerRawStorePageQueryCommand) {
        return crawlerRawStoreQueryCommandExecutor.execute(crawlerRawStorePageQueryCommand);
    }

    @Override
    public MultiResponse<CrawlerRawStoreVO> queryList(CrawlerRawStoreQueryListCommand crawlerRawStoreQueryListCommand) {
        return crawlerRawStoreQueryCommandExecutor.execute(crawlerRawStoreQueryListCommand);
    }


    @Autowired
    public void setCrawlerRawStoreQueryCommandExecutor(CrawlerRawStoreQueryCommandExecutor crawlerRawStoreQueryCommandExecutor) {
        this.crawlerRawStoreQueryCommandExecutor = crawlerRawStoreQueryCommandExecutor;
    }
}
