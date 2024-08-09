package com.particle.oplog.infrastructure.error.mapper;

import com.particle.oplog.infrastructure.error.dos.OpLogErrorContentDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 操作异常日志内容 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
@Mapper
public interface OpLogErrorContentMapper extends IBaseMapper<OpLogErrorContentDO> {

}
