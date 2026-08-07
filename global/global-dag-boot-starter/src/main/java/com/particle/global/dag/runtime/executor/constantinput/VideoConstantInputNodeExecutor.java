package com.particle.global.dag.runtime.executor.constantinput;

import com.particle.global.dag.constants.NodeTypeConstants;
import com.particle.global.dag.model.DagNode;

/**
 * <p>
 * 常量视频输入节点执行器
 *
 * </p>
 *
 * @author yangwei
 * @since 2026/5/5 17:36
 */
public class VideoConstantInputNodeExecutor extends BaseConstantInputNodeExecutor {
    @Override
    public boolean supports(DagNode node) {
        return NodeTypeConstants.CONSTANT_INPUT_VIDEO.equals(node.getType());
    }
}
