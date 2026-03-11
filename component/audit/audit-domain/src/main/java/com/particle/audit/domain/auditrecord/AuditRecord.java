package com.particle.audit.domain.auditrecord;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 审核记录 领域模型
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
@Data
@Entity
public class AuditRecord extends AggreateRoot {

    private AuditRecordId id;

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



    /**
     * 创建审核记录领域模型对象
     * @return 审核记录领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static AuditRecord create(){
        return DomainFactory.create(AuditRecord.class);
    }
}
