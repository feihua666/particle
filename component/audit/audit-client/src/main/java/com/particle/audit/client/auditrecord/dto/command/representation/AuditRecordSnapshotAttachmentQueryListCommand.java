package com.particle.audit.client.auditrecord.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 审核记录附件快照 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2026-01-19 15:37:34
 */
@Data
@Schema
public class AuditRecordSnapshotAttachmentQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "审核记录id")
    private Long auditRecordId;


    @Schema(description = "数据id")
    private Long dataId;


    @Schema(description = "快照数据id")
    private Long dataSnapshotDataId;


    @Schema(description = "附件名称")
    private String attachmentName;


    @Schema(description = "附件地址")
    private String attachmentUrl;









}
