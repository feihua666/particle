package com.particle.dept.infrastructure.mapper;

import com.particle.dept.infrastructure.dos.DeptTreeDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 部门树 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
@Mapper
public interface DeptTreeMapper extends IBaseMapper<DeptTreeDO> {

}
