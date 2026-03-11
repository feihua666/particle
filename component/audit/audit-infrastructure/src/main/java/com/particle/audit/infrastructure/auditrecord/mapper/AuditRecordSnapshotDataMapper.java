package com.particle.audit.infrastructure.auditrecord.mapper;

import com.particle.audit.infrastructure.auditrecord.dos.AuditRecordSnapshotDataDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 审核记录数据快照 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
@Mapper
public interface AuditRecordSnapshotDataMapper extends IBaseMapper<AuditRecordSnapshotDataDO> {

}
