package com.particle.global.dag.constants;

/**
 * <p>
 * DAG 节点角色常量
 * </p>
 *
 * @author particle
 * @since 2026-04-29
 */
public class NodeRoleConstants {

    /**
     * 输入节点 — 工作流入口数据
     */
    public static final String INPUT = "INPUT";

    /**
     * 输出节点 — 工作流出口数据
     */
    public static final String OUTPUT = "OUTPUT";

    /**
     * 常量输入节点 — 携带数据，Executor 优先取 valuePorts
     */
    public static final String CONSTANT_INPUT = "CONSTANT_INPUT";

    /**
     * 常量输出节点 — 展示数据，Executor 接收 inputMap 并展示
     */
    public static final String CONSTANT_OUTPUT = "CONSTANT_OUTPUT";

    /**
     * 处理节点 — 有实际执行逻辑，Executor 进行计算/调用/转换等操作
     */
    public static final String PROCESS = "PROCESS";

    /**
     * 控制节点 — 延迟、条件、转换等控制逻辑
     */
    public static final String CONTROL = "CONTROL";
}
