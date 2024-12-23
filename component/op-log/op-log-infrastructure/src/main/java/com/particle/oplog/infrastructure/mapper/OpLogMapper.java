package com.particle.oplog.infrastructure.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.oplog.infrastructure.dos.OpLogDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 操作日志 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
@Mapper
public interface OpLogMapper extends IBaseMapper<OpLogDO> {

}
