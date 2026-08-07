package com.particle.crawler.app.execution.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crawler.app.execution.executor.representation.CrawlerExecutionQueryCommandExecutor;
import com.particle.crawler.client.execution.api.representation.ICrawlerExecutionRepresentationApplicationService;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerExecutionPageQueryCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerExecutionQueryListCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerExecutionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 爬虫执行实例 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@Service
@CatchAndLog
public class CrawlerExecutionRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrawlerExecutionRepresentationApplicationService {

    private CrawlerExecutionQueryCommandExecutor crawlerExecutionQueryCommandExecutor;

    @Override
    public SingleResponse<CrawlerExecutionVO> queryDetail(CommonIdCommand detailCommand) {
        return crawlerExecutionQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CrawlerExecutionVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
        return crawlerExecutionQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CrawlerExecutionVO> pageQuery(CrawlerExecutionPageQueryCommand crawlerExecutionPageQueryCommand) {
        return crawlerExecutionQueryCommandExecutor.execute(crawlerExecutionPageQueryCommand);
    }

    @Override
    public MultiResponse<CrawlerExecutionVO> queryList(CrawlerExecutionQueryListCommand crawlerExecutionQueryListCommand) {
        return crawlerExecutionQueryCommandExecutor.execute(crawlerExecutionQueryListCommand);
    }


    @Autowired
    public void setCrawlerExecutionQueryCommandExecutor(CrawlerExecutionQueryCommandExecutor crawlerExecutionQueryCommandExecutor) {
        this.crawlerExecutionQueryCommandExecutor = crawlerExecutionQueryCommandExecutor;
    }
}
