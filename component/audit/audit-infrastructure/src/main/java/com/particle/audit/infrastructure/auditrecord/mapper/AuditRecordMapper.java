package com.particle.audit.infrastructure.auditrecord.mapper;

import com.particle.audit.infrastructure.auditrecord.dos.AuditRecordDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 审核记录 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
@Mapper
public interface AuditRecordMapper extends IBaseMapper<AuditRecordDO> {

}
