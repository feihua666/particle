import { generateUUID } from "../../../../common/tools/UuidTools"
import type { VueFlowStore } from '@vue-flow/core'
import type { Node } from '@vue-flow/core'
import type { WorkflowNodeData } from '../workflow'
import type { NodeRegistryConfig } from '../registry/NodeRegistryConfig'

/**
 * 创建节点（纯函数，无副作用，不依赖 VueFlow 实例）
 *
 * 从 NodeRegistryConfig.data 创建节点数据，
 * 深拷贝端口配置，避免引用问题
 *
 * @param nodeConfig 节点注册配置
 */
export function createNewNode(nodeConfig: NodeRegistryConfig): Node {
    const { data: dataTemplate, ui } = nodeConfig

    // 构建节点 data（运行时数据）
    const data: WorkflowNodeData = {
        typeName: ui.typeName,  // 节点类型名称
        name: '',              // 用户自定义标题（新建时为空）
        nodeRole: dataTemplate.nodeRole,
        nodeType: dataTemplate.nodeType,
    }

    // 如果 NodeRegistryConfig.data 中有 DAG 元数据，写入节点 data
    if (dataTemplate.inputPorts) {
        data.inputPorts = {...dataTemplate.inputPorts}
    }
    if (dataTemplate.outputPorts) {
        data.outputPorts = {...dataTemplate.outputPorts}
    }
    if (dataTemplate.valuePorts) {
        // 深拷贝（避免引用问题）
        data.valuePorts = JSON.parse(JSON.stringify(dataTemplate.valuePorts))
    }
    if (dataTemplate.config) {
        data.config = { ...dataTemplate.config }
    }

    return {
        id: generateUUID(),
        type: nodeConfig.type,
        data,
        position: { x: 0, y: 0 },
        width: ui.defaultWidth,
        height: ui.defaultHeight,
    }
}

/**
 * 添加节点到 Vue Flow 中
 * @param nodeConfig 节点注册配置
 * @param usedVueFlow VueFlow 实例
 */
export function addNodeToFlow(nodeConfig: NodeRegistryConfig, usedVueFlow: VueFlowStore): void {
    const { nodes, addNodes, dimensions, screenToFlowCoordinate } = usedVueFlow

    const flowPosition = getFlowCenterCoordinate(
        dimensions.value,
        screenToFlowCoordinate
    )

    // 多个节点时轻微错开，避免完全重叠
    const offset = nodes.value.length * 10

    const node = createNewNode(nodeConfig)
    node.position = {
        x: flowPosition.x + offset,
        y: flowPosition.y + offset,
    }
    addNodes([node])
}

/**
 * 获取画布可视区域中心点对应的画布坐标
 * @param dimensions 画布尺寸
 * @param screenToFlowCoordinate 屏幕坐标转画布坐标函数
 */
function getFlowCenterCoordinate(
    dimensions: { width: number; height: number },
    screenToFlowCoordinate: (position: { x: number; y: number }) => { x: number; y: number }
): { x: number; y: number } {
    const centerX = dimensions.width / 2
    const centerY = dimensions.height / 2
    return screenToFlowCoordinate({ x: centerX, y: centerY })
}

export { nodeRegistry, createNodeRegistry } from '../registry/NodeRegistry'
