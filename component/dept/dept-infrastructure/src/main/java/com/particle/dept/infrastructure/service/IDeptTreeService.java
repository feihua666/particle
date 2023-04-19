package com.particle.dept.infrastructure.service;

import com.particle.dept.infrastructure.dos.DeptTreeDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 部门树 服务类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
public interface IDeptTreeService extends IBaseService<DeptTreeDO> {

    /**
     * 根据部门id查询
     * @param deptId
     * @return
     */
    default List<DeptTreeDO> getByDeptId(Long deptId) {
        Assert.notNull(deptId,"deptId 不能为空");
        return list(Wrappers.<DeptTreeDO>lambdaQuery().eq(DeptTreeDO::getDeptId, deptId));
    }



    /**
     * 根据部门id查询多个
     * @param deptIds
     * @return
     */
    default List<DeptTreeDO> getByDeptIds(List<Long> deptIds) {
        Assert.notEmpty(deptIds,"deptIds 不能为空");
        return list(Wrappers.<DeptTreeDO>lambdaQuery().in(DeptTreeDO::getDeptId, deptIds));
    }
            

    /**
     * 根据部门树名称id查询
     * @param deptTreeNameId
     * @return
     */
    default List<DeptTreeDO> getByDeptTreeNameId(Long deptTreeNameId) {
        Assert.notNull(deptTreeNameId,"deptTreeNameId 不能为空");
        return list(Wrappers.<DeptTreeDO>lambdaQuery().eq(DeptTreeDO::getDeptTreeNameId, deptTreeNameId));
    }



    /**
     * 根据部门树名称id查询多个
     * @param deptTreeNameIds
     * @return
     */
    default List<DeptTreeDO> getByDeptTreeNameIds(List<Long> deptTreeNameIds) {
        Assert.notEmpty(deptTreeNameIds,"deptTreeNameIds 不能为空");
        return list(Wrappers.<DeptTreeDO>lambdaQuery().in(DeptTreeDO::getDeptTreeNameId, deptTreeNameIds));
    }
            





















}
