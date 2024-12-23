package com.particle.oplog.infrastructure.error.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.oplog.infrastructure.error.dos.OpLogErrorDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 操作异常日志 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:09
 */
@Mapper
public interface OpLogErrorMapper extends IBaseMapper<OpLogErrorDO> {

}
