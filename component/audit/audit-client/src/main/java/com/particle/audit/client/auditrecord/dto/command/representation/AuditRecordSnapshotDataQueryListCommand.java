package com.particle.audit.client.auditrecord.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 审核记录数据快照 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
@Data
@Schema
public class AuditRecordSnapshotDataQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "审核记录id")
    private Long auditRecordId;


    @Schema(description = "数据id")
    private Long dataId;

	@Schema(description = "快照数据id，标识是哪个快照数据的id")
	private Long dataSnapshotDataId;


    @Schema(description = "快照内容json")
    private String snapshotJson;









}
