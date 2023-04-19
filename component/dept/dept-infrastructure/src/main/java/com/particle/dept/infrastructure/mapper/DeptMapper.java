package com.particle.dept.infrastructure.mapper;

import com.particle.dept.infrastructure.dos.DeptDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 部门 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Mapper
public interface DeptMapper extends IBaseMapper<DeptDO> {

}
