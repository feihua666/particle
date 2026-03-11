package com.particle.common.client.dto.command;

import com.particle.global.validation.props.PropValid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 通用 审核指令对象
 * </p>
 *
 * @author yw
 * @since 2026-01-19 10:36:54
 */
@PropValid
@Data
@Schema
public class CommonAuditCommand extends AbstractIdCommand {

    /**
     * 适用于直接指定审核结果场景
     * {@link com.particle.audit.domain.enums.AuditResult}
     */
    @PropValid.DependCondition(message = "审核结果 不能为空",dependProp = "isPassAudit",ifEqual = "null")
    @Schema(description = "审核结果，字典id，是否审核通过二选一")
    private Long auditResultDictId;

    /**
     * 适用于按逻辑执行场景
     */
    @PropValid.DependCondition(message = "审核结果 不能为空",dependProp = "auditStatusDictId",ifEqual = "null")
    @Schema(description = "是否审核通过，审核结果二选一")
    private Boolean isPassAudit;

    @NotNull(message = "审核意见 不能为空")
    @Schema(description = "审核意见")
    private String comment;

    public static CommonAuditCommand create(Long id, Long auditStatusDictId,String comment) {
        CommonAuditCommand commonPublicCommand = new CommonAuditCommand();
        commonPublicCommand.setId(id);
        commonPublicCommand.auditResultDictId = auditStatusDictId;
        commonPublicCommand.comment=comment;
        return commonPublicCommand;
    }
}
