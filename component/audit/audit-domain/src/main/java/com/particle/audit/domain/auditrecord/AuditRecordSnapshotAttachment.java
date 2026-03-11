package com.particle.audit.domain.auditrecord;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 审核记录附件快照 领域模型
 * </p>
 *
 * @author yw
 * @since 2026-01-19 15:37:34
 */
@Data
@Entity
public class AuditRecordSnapshotAttachment extends AggreateRoot {

    private AuditRecordSnapshotAttachmentId id;

    /**
    * 审核记录id
    */
    private Long auditRecordId;

    /**
    * 数据id，标识是哪个数据的附件
    */
    private Long dataId;

    /**
    * 快照数据id，标识是哪个快照数据的id
    */
    private Long dataSnapshotDataId;

    /**
    * 附件名称
    */
    private String attachmentName;

    /**
    * 附件地址
    */
    private String attachmentUrl;



    /**
     * 创建审核记录附件快照领域模型对象
     * @return 审核记录附件快照领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static AuditRecordSnapshotAttachment create(){
        return DomainFactory.create(AuditRecordSnapshotAttachment.class);
    }
}
