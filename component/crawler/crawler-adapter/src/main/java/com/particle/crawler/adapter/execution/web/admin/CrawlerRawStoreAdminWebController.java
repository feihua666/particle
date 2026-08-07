package com.particle.crawler.adapter.execution.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.crawler.client.execution.api.ICrawlerRawStoreApplicationService;
import com.particle.crawler.client.execution.api.representation.ICrawlerRawStoreRepresentationApplicationService;
import com.particle.crawler.client.execution.dto.command.CrawlerRawStoreCreateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerRawStoreVO;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.crawler.client.execution.dto.command.CrawlerRawStoreUpdateCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerRawStorePageQueryCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerRawStoreQueryListCommand;
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
 * 爬虫原始数据存储后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:47
 */
@Tag(name = "爬虫原始数据存储pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/crawler_raw_store")
public class CrawlerRawStoreAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ICrawlerRawStoreApplicationService iCrawlerRawStoreApplicationService;
    @Autowired
    private ICrawlerRawStoreRepresentationApplicationService iCrawlerRawStoreRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:crawlerRawStore:create')")
    @Operation(summary = "添加爬虫原始数据存储")
    @PostMapping("/create")
    @OpLog(name = "添加爬虫原始数据存储",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.create)
    public SingleResponse<CrawlerRawStoreVO> create(@RequestBody CrawlerRawStoreCreateCommand crawlerRawStoreCreateCommand){
        return iCrawlerRawStoreApplicationService.create(crawlerRawStoreCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerRawStore:delete')")
    @Operation(summary = "删除爬虫原始数据存储")
    @DeleteMapping("/delete")
    @OpLog(name = "删除爬虫原始数据存储",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.delete)
    public SingleResponse<CrawlerRawStoreVO> delete(@RequestBody CommonIdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iCrawlerRawStoreApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerRawStore:update')")
    @Operation(summary = "更新爬虫原始数据存储")
    @PutMapping("/update")
    @OpLog(name = "更新爬虫原始数据存储",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.update)
    public SingleResponse<CrawlerRawStoreVO> update(@RequestBody CrawlerRawStoreUpdateCommand crawlerRawStoreUpdateCommand){
        crawlerRawStoreUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCrawlerRawStoreApplicationService.update(crawlerRawStoreUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerRawStore:update')")
    @Operation(summary = "爬虫原始数据存储更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<CrawlerRawStoreVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand){
        return iCrawlerRawStoreRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerRawStore:detail')")
    @Operation(summary = "爬虫原始数据存储详情展示")
    @GetMapping("/detail")
    public SingleResponse<CrawlerRawStoreVO> queryDetail(CommonIdCommand detailCommand){
        return iCrawlerRawStoreRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerRawStore:queryList')")
    @Operation(summary = "列表查询爬虫原始数据存储")
    @GetMapping("/list")
    public MultiResponse<CrawlerRawStoreVO> queryList(CrawlerRawStoreQueryListCommand crawlerRawStoreQueryListCommand){
        crawlerRawStoreQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCrawlerRawStoreRepresentationApplicationService.queryList(crawlerRawStoreQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerRawStore:pageQuery')")
    @Operation(summary = "分页查询爬虫原始数据存储")
    @GetMapping("/page")
    public PageResponse<CrawlerRawStoreVO> pageQueryList(CrawlerRawStorePageQueryCommand crawlerRawStorePageQueryCommand){
        crawlerRawStorePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCrawlerRawStoreRepresentationApplicationService.pageQuery(crawlerRawStorePageQueryCommand);
    }
}
