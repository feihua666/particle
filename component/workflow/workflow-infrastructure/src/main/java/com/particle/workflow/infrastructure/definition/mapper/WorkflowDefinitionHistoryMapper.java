package com.particle.workflow.infrastructure.definition.mapper;

import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionHistoryDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 工作流定义历史 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:56:12
 */
@Mapper
public interface WorkflowDefinitionHistoryMapper extends IBaseMapper<WorkflowDefinitionHistoryDO> {

}
