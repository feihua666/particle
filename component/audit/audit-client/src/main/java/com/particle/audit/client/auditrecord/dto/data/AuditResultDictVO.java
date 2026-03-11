package com.particle.audit.client.auditrecord.dto.data;

import com.particle.global.dto.basic.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 审核结果 响应对象
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
@Data
@Schema
public class AuditResultDictVO extends VO {

    @Schema(description = "审核结果字典项")
    private List<AuditResultDictItemVO> auditResultDictItemVOList;

    @Schema(description = "同意字 典项")
    private AuditResultDictItemVO pass;

    @Schema(description = "不同意 字典项")
    private AuditResultDictItemVO unPass;



    @Data
    @Schema
    public static class AuditResultDictItemVO extends VO {

        @Schema(description = "审核结果字典id")
        private Long auditResultDictId;

        @Schema(description = "审核结果字典编码")
        private String auditResultDictCode;

        @Schema(description = "审核结果字典名称")
        private String auditResultDictName;

        @Schema(description = "审核结果字典值")
        private String auditResultDictValue;

        @Schema(description = "是否 同意")
        private Boolean isPass;

        @Schema(description = "是否 不同意")
        private Boolean isUnPass;


        @Schema(description = "私有标识,模糊查询")
        private String privateFlag;

        @Schema(description = "私有标识备忘")
        private String privateFlagMemo;

        @Schema(description = "分组标识")
        private String groupFlag;

        @Schema(description = "分组标识备忘")
        private String groupFlagMemo;

        @Schema(description = "标签，多个以逗号分隔，用来区分字典项")
        private String tags;
    }

    public static AuditResultDictItemVO createItem(
            Long auditResultDictId,
            String auditResultDictCode,
            String auditResultDictName,
            String auditResultDictValue,
            Boolean isPass,
            Boolean isUnPass,
            String privateFlag,
            String privateFlagMemo,
            String groupFlag,
            String groupFlagMemo,
            String tags
    ) {
        AuditResultDictItemVO auditResultDictItemVO = new AuditResultDictItemVO();
        auditResultDictItemVO.auditResultDictId = auditResultDictId;
        auditResultDictItemVO.auditResultDictCode = auditResultDictCode;
        auditResultDictItemVO.auditResultDictName = auditResultDictName;
        auditResultDictItemVO.auditResultDictValue = auditResultDictValue;
        auditResultDictItemVO.isPass = isPass;
        auditResultDictItemVO.isUnPass = isUnPass;
        auditResultDictItemVO.privateFlag = privateFlag;
        auditResultDictItemVO.privateFlagMemo = privateFlagMemo;
        auditResultDictItemVO.groupFlag = groupFlag;
        auditResultDictItemVO.groupFlagMemo = groupFlagMemo;
        auditResultDictItemVO.tags = tags;
        return auditResultDictItemVO;
    }
}
