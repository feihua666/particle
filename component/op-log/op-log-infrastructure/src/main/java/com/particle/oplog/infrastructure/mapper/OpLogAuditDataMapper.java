package com.particle.oplog.infrastructure.mapper;

import com.particle.oplog.infrastructure.dos.OpLogAuditDataDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 操作日志审计数据 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
@Mapper
public interface OpLogAuditDataMapper extends IBaseMapper<OpLogAuditDataDO> {

}
