package com.particle.workflow.infrastructure.execution.mapper;

import com.particle.workflow.infrastructure.execution.dos.WorkflowExecutionNodeDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 工作流节点执行实例 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
@Mapper
public interface WorkflowExecutionNodeMapper extends IBaseMapper<WorkflowExecutionNodeDO> {

}
