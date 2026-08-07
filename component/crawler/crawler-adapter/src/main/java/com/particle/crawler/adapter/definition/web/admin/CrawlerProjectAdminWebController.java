package com.particle.crawler.adapter.definition.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.crawler.client.definition.api.ICrawlerProjectApplicationService;
import com.particle.crawler.client.definition.api.representation.ICrawlerProjectRepresentationApplicationService;
import com.particle.crawler.client.definition.dto.command.CrawlerProjectCreateCommand;
import com.particle.crawler.client.definition.dto.data.CrawlerProjectVO;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.crawler.client.definition.dto.command.CrawlerProjectUpdateCommand;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerProjectPageQueryCommand;
import com.particle.crawler.client.definition.dto.command.representation.CrawlerProjectQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.login.LoginUser;
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
 * 爬虫项目后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:21
 */
@Tag(name = "爬虫项目pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/crawler_project")
public class CrawlerProjectAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ICrawlerProjectApplicationService iCrawlerProjectApplicationService;
    @Autowired
    private ICrawlerProjectRepresentationApplicationService iCrawlerProjectRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:crawlerProject:create')")
    @Operation(summary = "添加爬虫项目")
    @PostMapping("/create")
    @OpLog(name = "添加爬虫项目",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.create)
    public SingleResponse<CrawlerProjectVO> create(@RequestBody CrawlerProjectCreateCommand crawlerProjectCreateCommand, LoginUser loginUser){
        crawlerProjectCreateCommand.luid(loginUser.getId());
        return iCrawlerProjectApplicationService.create(crawlerProjectCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerProject:delete')")
    @Operation(summary = "删除爬虫项目")
    @DeleteMapping("/delete")
    @OpLog(name = "删除爬虫项目",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.delete)
    public SingleResponse<CrawlerProjectVO> delete(@RequestBody CommonIdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iCrawlerProjectApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerProject:update')")
    @Operation(summary = "更新爬虫项目")
    @PutMapping("/update")
    @OpLog(name = "更新爬虫项目",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.update)
    public SingleResponse<CrawlerProjectVO> update(@RequestBody CrawlerProjectUpdateCommand crawlerProjectUpdateCommand){
        crawlerProjectUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCrawlerProjectApplicationService.update(crawlerProjectUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerProject:update')")
    @Operation(summary = "爬虫项目更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<CrawlerProjectVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand){
        return iCrawlerProjectRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerProject:detail')")
    @Operation(summary = "爬虫项目详情展示")
    @GetMapping("/detail")
    public SingleResponse<CrawlerProjectVO> queryDetail(CommonIdCommand detailCommand){
        return iCrawlerProjectRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerProject:queryList')")
    @Operation(summary = "列表查询爬虫项目")
    @GetMapping("/list")
    public MultiResponse<CrawlerProjectVO> queryList(CrawlerProjectQueryListCommand crawlerProjectQueryListCommand){
        crawlerProjectQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCrawlerProjectRepresentationApplicationService.queryList(crawlerProjectQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerProject:pageQuery')")
    @Operation(summary = "分页查询爬虫项目")
    @GetMapping("/page")
    public PageResponse<CrawlerProjectVO> pageQueryList(CrawlerProjectPageQueryCommand crawlerProjectPageQueryCommand){
        crawlerProjectPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCrawlerProjectRepresentationApplicationService.pageQuery(crawlerProjectPageQueryCommand);
    }
}
