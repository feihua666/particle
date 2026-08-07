package com.particle.crawler.adapter.definition.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.crawler.client.definition.api.ICrawlerDefinitionHistoryApplicationService;
import com.particle.crawler.client.definition.api.representation.ICrawlerDefinitionHistoryRepresentationApplicationService;
import com.particle.crawler.client.definition.dto.command.CrawlerDefinitionHistoryCreateCommand;
import com.particle.crawler.client.definition.dto.command.CrawlerDefinitionHistoryCreateDraftCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerDefinitionHistoryVO;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.crawler.client.definition.dto.command.CrawlerDefinitionHistoryUpdateCommand;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerDefinitionHistoryPageQueryCommand;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerDefinitionHistoryQueryListCommand;
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
 * 爬虫定义历史后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:55
 */
@Tag(name = "爬虫定义历史pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/crawler_definition_history")
public class CrawlerDefinitionHistoryAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ICrawlerDefinitionHistoryApplicationService iCrawlerDefinitionHistoryApplicationService;
    @Autowired
    private ICrawlerDefinitionHistoryRepresentationApplicationService iCrawlerDefinitionHistoryRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:crawlerDefinitionHistory:create')")
    @Operation(summary = "添加爬虫定义历史")
    @PostMapping("/create")
    @OpLog(name = "添加爬虫定义历史",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.create)
    public SingleResponse<CrawlerDefinitionHistoryVO> create(@RequestBody CrawlerDefinitionHistoryCreateCommand crawlerDefinitionHistoryCreateCommand){
        return iCrawlerDefinitionHistoryApplicationService.create(crawlerDefinitionHistoryCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDefinitionHistory:delete')")
    @Operation(summary = "删除爬虫定义历史")
    @DeleteMapping("/delete")
    @OpLog(name = "删除爬虫定义历史",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.delete)
    public SingleResponse<CrawlerDefinitionHistoryVO> delete(@RequestBody CommonIdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iCrawlerDefinitionHistoryApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDefinitionHistory:update')")
    @Operation(summary = "更新爬虫定义历史")
    @PutMapping("/update")
    @OpLog(name = "更新爬虫定义历史",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.update)
    public SingleResponse<CrawlerDefinitionHistoryVO> update(@RequestBody CrawlerDefinitionHistoryUpdateCommand crawlerDefinitionHistoryUpdateCommand){
        crawlerDefinitionHistoryUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCrawlerDefinitionHistoryApplicationService.update(crawlerDefinitionHistoryUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDefinitionHistory:update')")
    @Operation(summary = "爬虫定义历史更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<CrawlerDefinitionHistoryVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand){
        return iCrawlerDefinitionHistoryRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDefinitionHistory:detail')")
    @Operation(summary = "爬虫定义历史详情展示")
    @GetMapping("/detail")
    public SingleResponse<CrawlerDefinitionHistoryVO> queryDetail(CommonIdCommand detailCommand){
        return iCrawlerDefinitionHistoryRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDefinitionHistory:queryList')")
    @Operation(summary = "列表查询爬虫定义历史")
    @GetMapping("/list")
    public MultiResponse<CrawlerDefinitionHistoryVO> queryList(CrawlerDefinitionHistoryQueryListCommand crawlerDefinitionHistoryQueryListCommand){
        crawlerDefinitionHistoryQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCrawlerDefinitionHistoryRepresentationApplicationService.queryList(crawlerDefinitionHistoryQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDefinitionHistory:pageQuery')")
    @Operation(summary = "分页查询爬虫定义历史")
    @GetMapping("/page")
    public PageResponse<CrawlerDefinitionHistoryVO> pageQueryList(CrawlerDefinitionHistoryPageQueryCommand crawlerDefinitionHistoryPageQueryCommand){
        crawlerDefinitionHistoryPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCrawlerDefinitionHistoryRepresentationApplicationService.pageQuery(crawlerDefinitionHistoryPageQueryCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDefinitionHistory:createDraft')")
    @Operation(summary = "添加爬虫定义历史草稿")
    @PostMapping("/createDraft")
    @OpLog(name = "添加爬虫定义历史草稿",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.create)
    public SingleResponse<CrawlerDefinitionHistoryVO> createDraft(@RequestBody CrawlerDefinitionHistoryCreateDraftCommand crawlerDefinitionHistoryCreateDraftCommand){
        return iCrawlerDefinitionHistoryApplicationService.createDraft(crawlerDefinitionHistoryCreateDraftCommand);
    }
}
