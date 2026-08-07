package com.particle.workflow.infrastructure.execution.mapper;

import com.particle.workflow.infrastructure.execution.dos.WorkflowExecutionDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 工作流执行实例 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:57:51
 */
@Mapper
public interface WorkflowExecutionMapper extends IBaseMapper<WorkflowExecutionDO> {

}
