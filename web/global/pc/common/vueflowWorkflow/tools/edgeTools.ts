import type {EdgeRegistryConfig} from "../registry/EdgeRegistryConfig.ts";
import {type Connection, type Edge, MarkerType, type VueFlowStore} from "@vue-flow/core";
import {generateUUID} from "../../../../common/tools/UuidTools.ts";

/**
 * 创建边（纯函数，无副作用）
 * @param edgeConfig 边的配置
 * @param connection 连接信息
 */
export function createNewEdge(edgeConfig: EdgeRegistryConfig, connection: Connection): Edge {
    return {
        id: generateUUID(),
        type: edgeConfig?.type,
        source: connection.source,
        target: connection.target,
        sourceHandle: connection.sourceHandle,
        targetHandle: connection.targetHandle,
        markerEnd: MarkerType.ArrowClosed,
    }
}

/**
 * 添加边到 Vue Flow 中
 * @param edgeConfig 边的配置
 * @param connection 连接信息
 * @param usedVueFlow VueFlow 实例
 */
export function addEdgeToFlow(
    edgeConfig: EdgeRegistryConfig,
    connection: Connection,
    usedVueFlow: VueFlowStore
): void {
    const { addEdges } = usedVueFlow
    const edge = createNewEdge(edgeConfig, connection)
    addEdges([edge])
}
export { edgeRegistry, createEdgeRegistry } from '../registry/EdgeRegistry'
