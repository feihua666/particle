package com.particle.crawler.adapter.execution.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.crawler.client.execution.api.ICrawlerExecutionApplicationService;
import com.particle.crawler.client.execution.api.ICrawlerExecutionExecuteApplicationService;
import com.particle.crawler.client.execution.api.representation.ICrawlerExecutionRepresentationApplicationService;
import com.particle.crawler.client.execution.dto.command.CrawlerExecutionCreateCommand;
import com.particle.crawler.client.execution.dto.command.CrawlerExecutionExecuteCommand;
import com.particle.crawler.client.execution.dto.data.CrawlerExecutionExecuteVO;
import com.particle.crawler.client.execution.dto.data.CrawlerExecutionVO;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.crawler.client.execution.dto.command.CrawlerExecutionUpdateCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerExecutionPageQueryCommand;
import com.particle.crawler.client.execution.dto.command.representation.CrawlerExecutionQueryListCommand;
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
 * 爬虫执行实例后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@Tag(name = "爬虫执行实例pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/crawler_execution")
public class CrawlerExecutionAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ICrawlerExecutionApplicationService iCrawlerExecutionApplicationService;
    @Autowired
    private ICrawlerExecutionRepresentationApplicationService iCrawlerExecutionRepresentationApplicationService;

    @Autowired
    private ICrawlerExecutionExecuteApplicationService iCrawlerExecutionExecuteApplicationService;

    @PreAuthorize("hasAuthority('admin:web:crawlerExecution:create')")
    @Operation(summary = "添加爬虫执行实例")
    @PostMapping("/create")
    @OpLog(name = "添加爬虫执行实例",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.create)
    public SingleResponse<CrawlerExecutionVO> create(@RequestBody CrawlerExecutionCreateCommand crawlerExecutionCreateCommand){
        return iCrawlerExecutionApplicationService.create(crawlerExecutionCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerExecution:delete')")
    @Operation(summary = "删除爬虫执行实例")
    @DeleteMapping("/delete")
    @OpLog(name = "删除爬虫执行实例",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.delete)
    public SingleResponse<CrawlerExecutionVO> delete(@RequestBody CommonIdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iCrawlerExecutionApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerExecution:update')")
    @Operation(summary = "更新爬虫执行实例")
    @PutMapping("/update")
    @OpLog(name = "更新爬虫执行实例",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.update)
    public SingleResponse<CrawlerExecutionVO> update(@RequestBody CrawlerExecutionUpdateCommand crawlerExecutionUpdateCommand){
        crawlerExecutionUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCrawlerExecutionApplicationService.update(crawlerExecutionUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerExecution:update')")
    @Operation(summary = "爬虫执行实例更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<CrawlerExecutionVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand){
        return iCrawlerExecutionRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerExecution:detail')")
    @Operation(summary = "爬虫执行实例详情展示")
    @GetMapping("/detail")
    public SingleResponse<CrawlerExecutionVO> queryDetail(CommonIdCommand detailCommand){
        return iCrawlerExecutionRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerExecution:queryList')")
    @Operation(summary = "列表查询爬虫执行实例")
    @GetMapping("/list")
    public MultiResponse<CrawlerExecutionVO> queryList(CrawlerExecutionQueryListCommand crawlerExecutionQueryListCommand){
        crawlerExecutionQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCrawlerExecutionRepresentationApplicationService.queryList(crawlerExecutionQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerExecution:pageQuery')")
    @Operation(summary = "分页查询爬虫执行实例")
    @GetMapping("/page")
    public PageResponse<CrawlerExecutionVO> pageQueryList(CrawlerExecutionPageQueryCommand crawlerExecutionPageQueryCommand){
        crawlerExecutionPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCrawlerExecutionRepresentationApplicationService.pageQuery(crawlerExecutionPageQueryCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:crawlerExecution:execute')")
    @Operation(summary = "执行爬虫定义")
    @PostMapping("/execute")
    @OpLog(name = "执行爬虫定义",module = OpLogConstants.Module.crawler,type = OpLogConstants.Type.other)
    public SingleResponse<CrawlerExecutionExecuteVO> execute(@RequestBody CrawlerExecutionExecuteCommand crawlerExecutionExecuteCommand){
        return iCrawlerExecutionExecuteApplicationService.execute(crawlerExecutionExecuteCommand);
    }
}
