package com.particle.crawler.adapter.execution.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.crawler.client.execution.api.ICrawlerDataStoreContentApplicationService;
import com.particle.crawler.client.execution.api.representation.ICrawlerDataStoreContentRepresentationApplicationService;
import com.particle.crawler.client.execution.dto.command.CrawlerDataStoreContentCreateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerDataStoreContentVO;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.crawler.client.execution.dto.command.CrawlerDataStoreContentUpdateCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerDataStoreContentPageQueryCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerDataStoreContentQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
/**
 * <p>
 * 爬虫结构数据存储内容后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:34
 */
@Tag(name = "爬虫结构数据存储内容pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/crawler_data_store_content")
public class CrawlerDataStoreContentAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ICrawlerDataStoreContentApplicationService iCrawlerDataStoreContentApplicationService;
    @Autowired
    private ICrawlerDataStoreContentRepresentationApplicationService iCrawlerDataStoreContentRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:crawlerDataStoreContent:create')")
    @Operation(summary = "添加爬虫结构数据存储内容")
    @PostMapping("/create")
    @OpLog(name = "添加爬虫结构数据存储内容",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.create)
    public SingleResponse<CrawlerDataStoreContentVO> create(@RequestBody CrawlerDataStoreContentCreateCommand crawlerDataStoreContentCreateCommand){
        return iCrawlerDataStoreContentApplicationService.create(crawlerDataStoreContentCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDataStoreContent:delete')")
    @Operation(summary = "删除爬虫结构数据存储内容")
    @DeleteMapping("/delete")
    @OpLog(name = "删除爬虫结构数据存储内容",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.delete)
    public SingleResponse<CrawlerDataStoreContentVO> delete(@RequestBody CommonIdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iCrawlerDataStoreContentApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDataStoreContent:update')")
    @Operation(summary = "更新爬虫结构数据存储内容")
    @PutMapping("/update")
    @OpLog(name = "更新爬虫结构数据存储内容",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.update)
    public SingleResponse<CrawlerDataStoreContentVO> update(@RequestBody CrawlerDataStoreContentUpdateCommand crawlerDataStoreContentUpdateCommand){
        crawlerDataStoreContentUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCrawlerDataStoreContentApplicationService.update(crawlerDataStoreContentUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDataStoreContent:update')")
    @Operation(summary = "爬虫结构数据存储内容更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<CrawlerDataStoreContentVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand){
        return iCrawlerDataStoreContentRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDataStoreContent:detail')")
    @Operation(summary = "爬虫结构数据存储内容详情展示")
    @GetMapping("/detail")
    public SingleResponse<CrawlerDataStoreContentVO> queryDetail(CommonIdCommand detailCommand){
        return iCrawlerDataStoreContentRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDataStoreContent:queryList')")
    @Operation(summary = "列表查询爬虫结构数据存储内容")
    @GetMapping("/list")
    public MultiResponse<CrawlerDataStoreContentVO> queryList(CrawlerDataStoreContentQueryListCommand crawlerDataStoreContentQueryListCommand){
        crawlerDataStoreContentQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCrawlerDataStoreContentRepresentationApplicationService.queryList(crawlerDataStoreContentQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDataStoreContent:pageQuery')")
    @Operation(summary = "分页查询爬虫结构数据存储内容")
    @GetMapping("/page")
    public PageResponse<CrawlerDataStoreContentVO> pageQueryList(CrawlerDataStoreContentPageQueryCommand crawlerDataStoreContentPageQueryCommand){
        crawlerDataStoreContentPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCrawlerDataStoreContentRepresentationApplicationService.pageQuery(crawlerDataStoreContentPageQueryCommand);
    }
}
