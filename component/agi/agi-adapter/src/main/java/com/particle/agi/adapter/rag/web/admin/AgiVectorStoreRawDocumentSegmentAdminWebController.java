package com.particle.agi.adapter.rag.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.agi.client.rag.api.IAgiVectorStoreRawDocumentSegmentApplicationService;
import com.particle.agi.client.rag.api.representation.IAgiVectorStoreRawDocumentSegmentRepresentationApplicationService;
import com.particle.agi.client.rag.dto.command.AgiVectorStoreRawDocumentSegmentCreateCommand;
import com.particle.agi.client.rag.dto.data.AgiVectorStoreRawDocumentSegmentVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.agi.client.rag.dto.command.AgiVectorStoreRawDocumentSegmentUpdateCommand;
import com.particle.agi.client.rag.dto.command.representation.AgiVectorStoreRawDocumentSegmentPageQueryCommand;
import com.particle.agi.client.rag.dto.command.representation.AgiVectorStoreRawDocumentSegmentQueryListCommand;
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
 * 知识存储原始文档片段后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:55:48
 */
@Tag(name = "知识存储原始文档片段pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/agi_vector_store_raw_document_segment")
public class AgiVectorStoreRawDocumentSegmentAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IAgiVectorStoreRawDocumentSegmentApplicationService iAgiVectorStoreRawDocumentSegmentApplicationService;
    @Autowired
    private IAgiVectorStoreRawDocumentSegmentRepresentationApplicationService iAgiVectorStoreRawDocumentSegmentRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:agiVectorStoreRawDocumentSegment:create')")
    @Operation(summary = "添加知识存储原始文档片段")
    @PostMapping("/create")
    @OpLog(name = "添加知识存储原始文档片段",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.create)
    public SingleResponse<AgiVectorStoreRawDocumentSegmentVO> create(@RequestBody AgiVectorStoreRawDocumentSegmentCreateCommand agiVectorStoreRawDocumentSegmentCreateCommand){
        return iAgiVectorStoreRawDocumentSegmentApplicationService.create(agiVectorStoreRawDocumentSegmentCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiVectorStoreRawDocumentSegment:delete')")
    @Operation(summary = "删除知识存储原始文档片段")
    @DeleteMapping("/delete")
    @OpLog(name = "删除知识存储原始文档片段",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.delete)
    public SingleResponse<AgiVectorStoreRawDocumentSegmentVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iAgiVectorStoreRawDocumentSegmentApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiVectorStoreRawDocumentSegment:update')")
    @Operation(summary = "更新知识存储原始文档片段")
    @PutMapping("/update")
    @OpLog(name = "更新知识存储原始文档片段",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.update)
    public SingleResponse<AgiVectorStoreRawDocumentSegmentVO> update(@RequestBody AgiVectorStoreRawDocumentSegmentUpdateCommand agiVectorStoreRawDocumentSegmentUpdateCommand){
        agiVectorStoreRawDocumentSegmentUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iAgiVectorStoreRawDocumentSegmentApplicationService.update(agiVectorStoreRawDocumentSegmentUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiVectorStoreRawDocumentSegment:update')")
    @Operation(summary = "知识存储原始文档片段更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<AgiVectorStoreRawDocumentSegmentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iAgiVectorStoreRawDocumentSegmentRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiVectorStoreRawDocumentSegment:detail')")
    @Operation(summary = "知识存储原始文档片段详情展示")
    @GetMapping("/detail")
    public SingleResponse<AgiVectorStoreRawDocumentSegmentVO> queryDetail(IdCommand detailCommand){
        return iAgiVectorStoreRawDocumentSegmentRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiVectorStoreRawDocumentSegment:queryList')")
    @Operation(summary = "列表查询知识存储原始文档片段")
    @GetMapping("/list")
    public MultiResponse<AgiVectorStoreRawDocumentSegmentVO> queryList(AgiVectorStoreRawDocumentSegmentQueryListCommand agiVectorStoreRawDocumentSegmentQueryListCommand){
        agiVectorStoreRawDocumentSegmentQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAgiVectorStoreRawDocumentSegmentRepresentationApplicationService.queryList(agiVectorStoreRawDocumentSegmentQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:agiVectorStoreRawDocumentSegment:pageQuery')")
    @Operation(summary = "分页查询知识存储原始文档片段")
    @GetMapping("/page")
    public PageResponse<AgiVectorStoreRawDocumentSegmentVO> pageQueryList(AgiVectorStoreRawDocumentSegmentPageQueryCommand agiVectorStoreRawDocumentSegmentPageQueryCommand){
        agiVectorStoreRawDocumentSegmentPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAgiVectorStoreRawDocumentSegmentRepresentationApplicationService.pageQuery(agiVectorStoreRawDocumentSegmentPageQueryCommand);
    }

    /**
     * 先尝试删除已经向量化的对应的id，再向量化
     * @param idCommand
     * @return
     */
    @PreAuthorize("hasAuthority('admin:web:agiVectorStoreRawDocumentSegment:embedding')")
    @Operation(summary = "嵌入知识存储原始文档片段")
    @PostMapping("/embedding")
    @OpLog(name = "嵌入知识存储原始文档片段",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.create)
    public Response embedding(@RequestBody IdCommand idCommand){
        return iAgiVectorStoreRawDocumentSegmentApplicationService.embedding(idCommand);
    }
}
