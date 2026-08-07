package com.particle.workflow.infrastructure.definition.mapper;

import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 工作流定义 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:48
 */
@Mapper
public interface WorkflowDefinitionMapper extends IBaseMapper<WorkflowDefinitionDO> {

}
