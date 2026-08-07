package com.particle.global.dag.model;

import lombok.Data;

import java.util.Map;

/**
 * <p>
 * DAG 节点定义 模型，表示DAG中的一个节点
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
@Data
public class DagNode {

    /**
     * 节点唯一标识
     */
    private String id;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 节点类型，如: TEXT, IMAGE, VIDEO, HTTP, GROOVY_SCRIPT, AI 等
     */
    private String type;

    /**
     * 节点角色，DATA（数据节点）或 PROCESSOR（处理节点）
     */
    private String role;

    /**
     * 节点配置信息（行为配置，如 method、delay、operation 等）
     */
    private Map<String, Object> config;

    /**
     * 输入端口声明 — 声明节点需要什么输入，值由引擎从上游注入
     */
    private Map<String,NodePort> inputPorts;

    /**
     * 输出端口声明 — 声明节点产生什么输出，值由 executor 执行后写入
     */
    private Map<String,NodePort> outputPorts;

    /**
     * 预设值端口 — 节点自身携带的预设值，由用户在画布上填写
     * 引擎在节点执行前自动将这些值注入到 context
     */
    private Map<String,NodePort> valuePorts;
}
