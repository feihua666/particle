package com.particle.audit.client.auditrecord.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 审核记录数据快照 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
@Data
@Schema
public class AuditRecordSnapshotDataVO extends AbstractBaseIdVO {

    @Schema(description = "审核记录id")
    private Long auditRecordId;
    
    @Schema(description = "数据id")
    private Long dataId;

	@Schema(description = "快照数据id，标识是哪个快照数据的id")
	private Long dataSnapshotDataId;
    
    @Schema(description = "快照内容json")
    private String snapshotJson;
    


}
