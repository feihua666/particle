package com.particle.crawler.app.definition.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crawler.app.definition.executor.representation.CrawlerProjectQueryCommandExecutor;
import com.particle.crawler.client.definition.api.representation.ICrawlerProjectRepresentationApplicationService;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerProjectPageQueryCommand;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerProjectQueryListCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 爬虫项目 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:21
 */
@Service
@CatchAndLog
public class CrawlerProjectRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrawlerProjectRepresentationApplicationService {

    private CrawlerProjectQueryCommandExecutor crawlerProjectQueryCommandExecutor;

    @Override
    public SingleResponse<CrawlerProjectVO> queryDetail(CommonIdCommand detailCommand) {
        return crawlerProjectQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CrawlerProjectVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
        return crawlerProjectQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CrawlerProjectVO> pageQuery(CrawlerProjectPageQueryCommand crawlerProjectPageQueryCommand) {
        return crawlerProjectQueryCommandExecutor.execute(crawlerProjectPageQueryCommand);
    }

    @Override
    public MultiResponse<CrawlerProjectVO> queryList(CrawlerProjectQueryListCommand crawlerProjectQueryListCommand) {
        return crawlerProjectQueryCommandExecutor.execute(crawlerProjectQueryListCommand);
    }


    @Autowired
    public void setCrawlerProjectQueryCommandExecutor(CrawlerProjectQueryCommandExecutor crawlerProjectQueryCommandExecutor) {
        this.crawlerProjectQueryCommandExecutor = crawlerProjectQueryCommandExecutor;
    }
}
