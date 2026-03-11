package com.particle.audit.client.auditrecord.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 审核记录 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
@Data
@Schema
public class AuditRecordQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "数据id")
    private Long dataId;


    @Schema(description = "审核结果类型")
    private Long auditResultDictId;


    @Schema(description = "审核意见")
    private String auditComment;


    @Schema(description = "审核时间")
    private LocalDateTime auditAt;
    

    @Schema(description = "审核人")
    private Long auditBy;


    @Schema(description = "数据审核之前状态")
    private Long dataPreStatusDictId;


    @Schema(description = "数据审核之后状态")
    private Long dataPostStatusDictId;


    @Schema(description = "分组标识")
    private String groupFlag;


    @Schema(description = "分组标识备忘")
    private String groupFlagMemo;










}
