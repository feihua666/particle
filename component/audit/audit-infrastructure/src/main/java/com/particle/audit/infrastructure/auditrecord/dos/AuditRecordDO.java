package com.particle.audit.infrastructure.auditrecord.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
/**
 * <p>
 * 审核记录表
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
@Accessors(chain = true)
@Data
@TableName("component_audit_record")
public class AuditRecordDO extends BaseDO {

    /**
    * 数据id，标识是哪个数据的审批记录
    */
    private Long dataId;

    /**
    * 审核结果类型，如：同意且无修改意见、同意且有修改意见、不同意且有修改意见
    */
    private Long auditResultDictId;

    /**
    * 审核意见
    */
    private String auditComment;

    /**
    * 审核时间
    */
    private LocalDateTime auditAt;
    
    /**
    * 审核人
    */
    private Long auditBy;

    /**
    * 数据审核之前状态，字典id，如：草稿、退回（可修改）
    */
    private Long dataPreStatusDictId;

    /**
    * 数据审核之后状态，字典id，如：草稿、退回（可修改）
    */
    private Long dataPostStatusDictId;

    /**
    * 分组标识，如：cms_content
    */
    private String groupFlag;

    /**
    * 分组标识备忘，如：cms模板内容审核
    */
    private String groupFlagMemo;

    /**
    * 描述
    */
    private String remark;


}
