package com.particle.audit.infrastructure.auditrecord.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 审核记录附件快照表
 * </p>
 *
 * @author yw
 * @since 2026-01-19 15:37:34
 */
@Accessors(chain = true)
@Data
@TableName("component_audit_record_snapshot_attachment")
public class AuditRecordSnapshotAttachmentDO extends BaseDO {

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


}
