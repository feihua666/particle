package com.particle.workflow.infrastructure.definition.mapper;

import com.particle.workflow.infrastructure.definition.dos.WorkflowProjectDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 工作流项目 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:12
 */
@Mapper
public interface WorkflowProjectMapper extends IBaseMapper<WorkflowProjectDO> {

}
