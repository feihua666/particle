package com.particle.data.infrastructure.dynamictable.mapper;

import com.particle.data.infrastructure.dynamictable.dos.DynamicTableUploadRecordDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 动态数据表格上传记录 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:17
 */
@Mapper
public interface DynamicTableUploadRecordMapper extends IBaseMapper<DynamicTableUploadRecordDO> {

}
