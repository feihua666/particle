package com.particle.crawler.adapter.execution.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.crawler.client.execution.api.ICrawlerRawStoreContentApplicationService;
import com.particle.crawler.client.execution.api.representation.ICrawlerRawStoreContentRepresentationApplicationService;
import com.particle.crawler.client.execution.dto.command.CrawlerRawStoreContentCreateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerRawStoreContentVO;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.crawler.client.execution.dto.command.CrawlerRawStoreContentUpdateCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerRawStoreContentPageQueryCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerRawStoreContentQueryListCommand;
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
 * 爬虫原始数据存储内容后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:24:02
 */
@Tag(name = "爬虫原始数据存储内容pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/crawler_raw_store_content")
public class CrawlerRawStoreContentAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ICrawlerRawStoreContentApplicationService iCrawlerRawStoreContentApplicationService;
    @Autowired
    private ICrawlerRawStoreContentRepresentationApplicationService iCrawlerRawStoreContentRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:crawlerRawStoreContent:create')")
    @Operation(summary = "添加爬虫原始数据存储内容")
    @PostMapping("/create")
    @OpLog(name = "添加爬虫原始数据存储内容",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.create)
    public SingleResponse<CrawlerRawStoreContentVO> create(@RequestBody CrawlerRawStoreContentCreateCommand crawlerRawStoreContentCreateCommand){
        return iCrawlerRawStoreContentApplicationService.create(crawlerRawStoreContentCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerRawStoreContent:delete')")
    @Operation(summary = "删除爬虫原始数据存储内容")
    @DeleteMapping("/delete")
    @OpLog(name = "删除爬虫原始数据存储内容",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.delete)
    public SingleResponse<CrawlerRawStoreContentVO> delete(@RequestBody CommonIdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iCrawlerRawStoreContentApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerRawStoreContent:update')")
    @Operation(summary = "更新爬虫原始数据存储内容")
    @PutMapping("/update")
    @OpLog(name = "更新爬虫原始数据存储内容",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.update)
    public SingleResponse<CrawlerRawStoreContentVO> update(@RequestBody CrawlerRawStoreContentUpdateCommand crawlerRawStoreContentUpdateCommand){
        crawlerRawStoreContentUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCrawlerRawStoreContentApplicationService.update(crawlerRawStoreContentUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerRawStoreContent:update')")
    @Operation(summary = "爬虫原始数据存储内容更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<CrawlerRawStoreContentVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand){
        return iCrawlerRawStoreContentRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerRawStoreContent:detail')")
    @Operation(summary = "爬虫原始数据存储内容详情展示")
    @GetMapping("/detail")
    public SingleResponse<CrawlerRawStoreContentVO> queryDetail(CommonIdCommand detailCommand){
        return iCrawlerRawStoreContentRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerRawStoreContent:queryList')")
    @Operation(summary = "列表查询爬虫原始数据存储内容")
    @GetMapping("/list")
    public MultiResponse<CrawlerRawStoreContentVO> queryList(CrawlerRawStoreContentQueryListCommand crawlerRawStoreContentQueryListCommand){
        crawlerRawStoreContentQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCrawlerRawStoreContentRepresentationApplicationService.queryList(crawlerRawStoreContentQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerRawStoreContent:pageQuery')")
    @Operation(summary = "分页查询爬虫原始数据存储内容")
    @GetMapping("/page")
    public PageResponse<CrawlerRawStoreContentVO> pageQueryList(CrawlerRawStoreContentPageQueryCommand crawlerRawStoreContentPageQueryCommand){
        crawlerRawStoreContentPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCrawlerRawStoreContentRepresentationApplicationService.pageQuery(crawlerRawStoreContentPageQueryCommand);
    }
}
