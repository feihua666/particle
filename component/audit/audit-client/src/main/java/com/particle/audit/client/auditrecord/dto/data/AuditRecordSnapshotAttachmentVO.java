package com.particle.audit.client.auditrecord.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 审核记录附件快照 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2026-01-19 15:37:34
 */
@Data
@Schema
public class AuditRecordSnapshotAttachmentVO extends AbstractBaseIdVO {

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
