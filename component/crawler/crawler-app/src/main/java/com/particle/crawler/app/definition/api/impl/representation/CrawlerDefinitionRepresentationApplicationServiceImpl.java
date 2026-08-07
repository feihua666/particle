package com.particle.crawler.app.definition.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crawler.app.definition.executor.representation.CrawlerDefinitionQueryCommandExecutor;
import com.particle.crawler.client.definition.api.representation.ICrawlerDefinitionRepresentationApplicationService;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerDefinitionPageQueryCommand;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerDefinitionQueryListCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerDefinitionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 爬虫定义 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:37
 */
@Service
@CatchAndLog
public class CrawlerDefinitionRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrawlerDefinitionRepresentationApplicationService {

    private CrawlerDefinitionQueryCommandExecutor crawlerDefinitionQueryCommandExecutor;

    @Override
    public SingleResponse<CrawlerDefinitionVO> queryDetail(CommonIdCommand detailCommand) {
        return crawlerDefinitionQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CrawlerDefinitionVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
        return crawlerDefinitionQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CrawlerDefinitionVO> pageQuery(CrawlerDefinitionPageQueryCommand crawlerDefinitionPageQueryCommand) {
        return crawlerDefinitionQueryCommandExecutor.execute(crawlerDefinitionPageQueryCommand);
    }

    @Override
    public MultiResponse<CrawlerDefinitionVO> queryList(CrawlerDefinitionQueryListCommand crawlerDefinitionQueryListCommand) {
        return crawlerDefinitionQueryCommandExecutor.execute(crawlerDefinitionQueryListCommand);
    }


    @Autowired
    public void setCrawlerDefinitionQueryCommandExecutor(CrawlerDefinitionQueryCommandExecutor crawlerDefinitionQueryCommandExecutor) {
        this.crawlerDefinitionQueryCommandExecutor = crawlerDefinitionQueryCommandExecutor;
    }
}
