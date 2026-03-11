package com.particle.audit.infrastructure.auditrecord.mapper;

import com.particle.audit.infrastructure.auditrecord.dos.AuditRecordSnapshotAttachmentDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 审核记录附件快照 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2026-01-19 15:37:34
 */
@Mapper
public interface AuditRecordSnapshotAttachmentMapper extends IBaseMapper<AuditRecordSnapshotAttachmentDO> {

}
