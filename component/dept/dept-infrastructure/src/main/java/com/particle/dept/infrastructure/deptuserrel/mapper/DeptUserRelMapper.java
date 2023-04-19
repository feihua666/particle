package com.particle.dept.infrastructure.deptuserrel.mapper;

import com.particle.dept.infrastructure.deptuserrel.dos.DeptUserRelDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 部门用户关系 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-04-12 17:28:09
 */
@Mapper
public interface DeptUserRelMapper extends IBaseMapper<DeptUserRelDO> {

}
