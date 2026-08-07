package com.particle.global.dag.runtime.executor.constantinput;

import com.particle.global.dag.constants.NodeTypeConstants;
import com.particle.global.dag.model.DagNode;

/**
 * <p>
 * 常量http配置输入节点执行器
 *
 * </p>
 *
 * @author yangwei
 * @since 2026/5/5 17:36
 */
public class HttpConfigConstantInputNodeExecutor extends BaseConstantInputNodeExecutor {
    @Override
    public boolean supports(DagNode node) {
        return NodeTypeConstants.CONSTANT_INPUT_HTTP_CONFIG.equals(node.getType());
    }
}
