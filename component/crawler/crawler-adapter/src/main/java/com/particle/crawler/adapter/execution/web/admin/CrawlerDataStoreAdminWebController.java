package com.particle.crawler.adapter.execution.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.crawler.client.execution.api.ICrawlerDataStoreApplicationService;
import com.particle.crawler.client.execution.api.representation.ICrawlerDataStoreRepresentationApplicationService;
import com.particle.crawler.client.execution.dto.command.CrawlerDataStoreCreateCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerDataStoreVO;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.crawler.client.execution.dto.command.CrawlerDataStoreUpdateCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerDataStorePageQueryCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerDataStoreQueryListCommand;
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
 * 爬虫结构数据存储后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:19
 */
@Tag(name = "爬虫结构数据存储pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/crawler_data_store")
public class CrawlerDataStoreAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ICrawlerDataStoreApplicationService iCrawlerDataStoreApplicationService;
    @Autowired
    private ICrawlerDataStoreRepresentationApplicationService iCrawlerDataStoreRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:crawlerDataStore:create')")
    @Operation(summary = "添加爬虫结构数据存储")
    @PostMapping("/create")
    @OpLog(name = "添加爬虫结构数据存储",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.create)
    public SingleResponse<CrawlerDataStoreVO> create(@RequestBody CrawlerDataStoreCreateCommand crawlerDataStoreCreateCommand){
        return iCrawlerDataStoreApplicationService.create(crawlerDataStoreCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDataStore:delete')")
    @Operation(summary = "删除爬虫结构数据存储")
    @DeleteMapping("/delete")
    @OpLog(name = "删除爬虫结构数据存储",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.delete)
    public SingleResponse<CrawlerDataStoreVO> delete(@RequestBody CommonIdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iCrawlerDataStoreApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDataStore:update')")
    @Operation(summary = "更新爬虫结构数据存储")
    @PutMapping("/update")
    @OpLog(name = "更新爬虫结构数据存储",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.update)
    public SingleResponse<CrawlerDataStoreVO> update(@RequestBody CrawlerDataStoreUpdateCommand crawlerDataStoreUpdateCommand){
        crawlerDataStoreUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCrawlerDataStoreApplicationService.update(crawlerDataStoreUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDataStore:update')")
    @Operation(summary = "爬虫结构数据存储更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<CrawlerDataStoreVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand){
        return iCrawlerDataStoreRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDataStore:detail')")
    @Operation(summary = "爬虫结构数据存储详情展示")
    @GetMapping("/detail")
    public SingleResponse<CrawlerDataStoreVO> queryDetail(CommonIdCommand detailCommand){
        return iCrawlerDataStoreRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDataStore:queryList')")
    @Operation(summary = "列表查询爬虫结构数据存储")
    @GetMapping("/list")
    public MultiResponse<CrawlerDataStoreVO> queryList(CrawlerDataStoreQueryListCommand crawlerDataStoreQueryListCommand){
        crawlerDataStoreQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCrawlerDataStoreRepresentationApplicationService.queryList(crawlerDataStoreQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDataStore:pageQuery')")
    @Operation(summary = "分页查询爬虫结构数据存储")
    @GetMapping("/page")
    public PageResponse<CrawlerDataStoreVO> pageQueryList(CrawlerDataStorePageQueryCommand crawlerDataStorePageQueryCommand){
        crawlerDataStorePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCrawlerDataStoreRepresentationApplicationService.pageQuery(crawlerDataStorePageQueryCommand);
    }
}
