package com.particle.audit.client.auditrecord.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
import java.time.LocalDateTime;
/**
 * <p>
 * 审核记录 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
@Data
@Schema
public class AuditRecordVO extends AbstractBaseIdVO {

    @Schema(description = "数据id")
    private Long dataId;

    @Schema(description = "审核结果类型")
    private Long auditResultDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "auditResultDictId",mapValueField = "name")
    @Schema(description = "审核结果类型对应字典名称")
    private String auditResultDictName;

    @Schema(description = "审核意见")
    private String auditComment;

    @Schema(description = "审核时间")
    private LocalDateTime auditAt;

    @Schema(description = "审核人")
    private Long auditBy;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "auditBy",mapValueField = "nickname")
    @Schema(description = "审核人昵称")
    private String auditByUserNickname;

    @Schema(description = "数据审核之前状态")
    private Long dataPreStatusDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "dataPreStatusDictId",mapValueField = "name")
    @Schema(description = "数据审核之前状态对应字典名称")
    private String dataPreStatusDictName;

    @Schema(description = "数据审核之后状态")
    private Long dataPostStatusDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "dataPostStatusDictId",mapValueField = "name")
    @Schema(description = "数据审核之后状态对应字典名称")
    private String dataPostStatusDictName;

    @Schema(description = "分组标识")
    private String groupFlag;

    @Schema(description = "分组标识备忘")
    private String groupFlagMemo;

    @Schema(description = "描述")
    private String remark;



}
