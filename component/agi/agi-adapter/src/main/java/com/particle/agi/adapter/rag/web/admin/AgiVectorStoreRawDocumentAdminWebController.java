package com.particle.agi.adapter.rag.web.admin;

import com.particle.agi.client.rag.api.IAgiVectorStoreRawDocumentApplicationService;
import com.particle.agi.client.rag.api.representation.IAgiVectorStoreRawDocumentRepresentationApplicationService;
import com.particle.agi.client.rag.dto.command.AgiVectorStoreRawDocumentCreateCommand;
import com.particle.agi.client.rag.dto.command.representation.AgiVectorStoreRawDocumentPageQueryCommand;
import com.particle.agi.client.rag.dto.command.representation.AgiVectorStoreRawDocumentQueryListCommand;
import com.particle.agi.client.rag.dto.data.AgiVectorStoreRawDocumentVO;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 知识存储原始文档后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:54:59
 */
@Tag(name = "知识存储原始文档pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/agi_vector_store_raw_document")
public class AgiVectorStoreRawDocumentAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IAgiVectorStoreRawDocumentApplicationService iAgiVectorStoreRawDocumentApplicationService;
    @Autowired
    private IAgiVectorStoreRawDocumentRepresentationApplicationService iAgiVectorStoreRawDocumentRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:agiVectorStoreRawDocument:create')")
    @Operation(summary = "添加知识存储原始文档")
    @PostMapping("/create")
    @OpLog(name = "添加知识存储原始文档",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.create)
    public SingleResponse<AgiVectorStoreRawDocumentVO> create(@RequestBody AgiVectorStoreRawDocumentCreateCommand agiVectorStoreRawDocumentCreateCommand){
        return iAgiVectorStoreRawDocumentApplicationService.create(agiVectorStoreRawDocumentCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiVectorStoreRawDocument:delete')")
    @Operation(summary = "删除知识存储原始文档")
    @DeleteMapping("/delete")
    @OpLog(name = "删除知识存储原始文档",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.delete)
    public SingleResponse<AgiVectorStoreRawDocumentVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iAgiVectorStoreRawDocumentApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiVectorStoreRawDocument:detail')")
    @Operation(summary = "知识存储原始文档详情展示")
    @GetMapping("/detail")
    public SingleResponse<AgiVectorStoreRawDocumentVO> queryDetail(IdCommand detailCommand){
        return iAgiVectorStoreRawDocumentRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiVectorStoreRawDocument:queryList')")
    @Operation(summary = "列表查询知识存储原始文档")
    @GetMapping("/list")
    public MultiResponse<AgiVectorStoreRawDocumentVO> queryList(AgiVectorStoreRawDocumentQueryListCommand agiVectorStoreRawDocumentQueryListCommand){
        agiVectorStoreRawDocumentQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAgiVectorStoreRawDocumentRepresentationApplicationService.queryList(agiVectorStoreRawDocumentQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiVectorStoreRawDocument:pageQuery')")
    @Operation(summary = "分页查询知识存储原始文档")
    @GetMapping("/page")
    public PageResponse<AgiVectorStoreRawDocumentVO> pageQueryList(AgiVectorStoreRawDocumentPageQueryCommand agiVectorStoreRawDocumentPageQueryCommand){
        agiVectorStoreRawDocumentPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAgiVectorStoreRawDocumentRepresentationApplicationService.pageQuery(agiVectorStoreRawDocumentPageQueryCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiVectorStoreRawDocument:embedding')")
    @Operation(summary = "嵌入未嵌入的片段")
    @PostMapping("/embedding")
    @OpLog(name = "嵌入未嵌入的片段",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.create)
    public Response embedding(@RequestBody IdCommand idCommand){
        return iAgiVectorStoreRawDocumentApplicationService.embedding(idCommand);
    }
    @PreAuthorize("hasAuthority('admin:web:agiVectorStoreRawDocument:reEmbedding')")
    @Operation(summary = "重新嵌入所有片段")
    @PostMapping("/reEmbedding")
    @OpLog(name = "重新嵌入所有片段",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.create)
    public Response reEmbedding(@RequestBody IdCommand idCommand){
        return iAgiVectorStoreRawDocumentApplicationService.reEmbedding(idCommand);
    }
}
