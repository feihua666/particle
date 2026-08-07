package com.particle.crawler.adapter.definition.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.crawler.client.definition.api.ICrawlerDefinitionApplicationService;
import com.particle.crawler.client.definition.api.representation.ICrawlerDefinitionRepresentationApplicationService;
import com.particle.crawler.client.definition.dto.command.CrawlerDefinitionCreateCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerDefinitionVO;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.crawler.client.definition.dto.command.CrawlerDefinitionUpdateCommand;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerDefinitionPageQueryCommand;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerDefinitionQueryListCommand;
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
 * 爬虫定义后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:37
 */
@Tag(name = "爬虫定义pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/crawler_definition")
public class CrawlerDefinitionAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ICrawlerDefinitionApplicationService iCrawlerDefinitionApplicationService;
    @Autowired
    private ICrawlerDefinitionRepresentationApplicationService iCrawlerDefinitionRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:crawlerDefinition:create')")
    @Operation(summary = "添加爬虫定义")
    @PostMapping("/create")
    @OpLog(name = "添加爬虫定义",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.create)
    public SingleResponse<CrawlerDefinitionVO> create(@RequestBody CrawlerDefinitionCreateCommand crawlerDefinitionCreateCommand){
        return iCrawlerDefinitionApplicationService.create(crawlerDefinitionCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDefinition:delete')")
    @Operation(summary = "删除爬虫定义")
    @DeleteMapping("/delete")
    @OpLog(name = "删除爬虫定义",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.delete)
    public SingleResponse<CrawlerDefinitionVO> delete(@RequestBody CommonIdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iCrawlerDefinitionApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDefinition:update')")
    @Operation(summary = "更新爬虫定义")
    @PutMapping("/update")
    @OpLog(name = "更新爬虫定义",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.update)
    public SingleResponse<CrawlerDefinitionVO> update(@RequestBody CrawlerDefinitionUpdateCommand crawlerDefinitionUpdateCommand){
        crawlerDefinitionUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCrawlerDefinitionApplicationService.update(crawlerDefinitionUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDefinition:update')")
    @Operation(summary = "爬虫定义更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<CrawlerDefinitionVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand){
        return iCrawlerDefinitionRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDefinition:detail')")
    @Operation(summary = "爬虫定义详情展示")
    @GetMapping("/detail")
    public SingleResponse<CrawlerDefinitionVO> queryDetail(CommonIdCommand detailCommand){
        return iCrawlerDefinitionRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDefinition:queryList')")
    @Operation(summary = "列表查询爬虫定义")
    @GetMapping("/list")
    public MultiResponse<CrawlerDefinitionVO> queryList(CrawlerDefinitionQueryListCommand crawlerDefinitionQueryListCommand){
        crawlerDefinitionQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCrawlerDefinitionRepresentationApplicationService.queryList(crawlerDefinitionQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerDefinition:pageQuery')")
    @Operation(summary = "分页查询爬虫定义")
    @GetMapping("/page")
    public PageResponse<CrawlerDefinitionVO> pageQueryList(CrawlerDefinitionPageQueryCommand crawlerDefinitionPageQueryCommand){
        crawlerDefinitionPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCrawlerDefinitionRepresentationApplicationService.pageQuery(crawlerDefinitionPageQueryCommand);
    }
}
