import { nextTick } from 'vue'
import type { Connection, NodeChange, EdgeChange, VueFlowStore } from '@vue-flow/core'
import { newDebouncedShowMsg } from '../../../element-plus/ElmessageTools'
import type { NodeRegistryConfig } from '../registry/NodeRegistryConfig'
import {
    DataType,
    type PortDefinition,
} from '../workflow'

const showMessage = newDebouncedShowMsg()

/**
 * 获取端口类型数组
 */
function getPortTypes(port: PortDefinition): string[] {
    return Array.isArray(port.dataType) ? port.dataType : [port.dataType]
}
/**
 * 获取 port 值
 */
export function getPortData(ports: Record<string, PortDefinition> | undefined, name: string) {
    return ports?.[name]?.data
}

/**
 * 更新 port（返回新对象，保证引用变化）
 */
export function updatePortData(
    ports: Record<string, PortDefinition> | undefined,
    name: string,
    data: any,
    hasIncomingEdge?: boolean
) {
    return {
        ...(ports || {}),
        [name]: {
            ...(ports?.[name] || {}),
            data,
            ...(hasIncomingEdge !== undefined && { hasIncomingEdge: hasIncomingEdge })
        }
    }
}
/**
 * 保存 valuePorts 中指定端口的旧值（直接修改原对象）
 */
export function updatePortOldDataByDataDirect(ports: Record<string, PortDefinition> | undefined, targetHandle: string) {
    if (!ports) return

    for (const key of Object.keys(ports)) {
        const port = ports[key]
        if (port.inputPortName === targetHandle) {
            // 备份
            port.oldData = JSON.parse(JSON.stringify(port.data))
        }
    }
}
/**
 * 保存 valuePorts 中指定端口的旧值（返回新对象，保证引用变化）
 */
export function updatePortOldDataByData(ports: Record<string, PortDefinition> | undefined, targetHandle: string) {
    if (!ports) return undefined
    return Object.fromEntries(
        Object.entries(ports).map(([key, port]) => [
            key,
            port.inputPortName === targetHandle ? { ...port, oldData: JSON.parse(JSON.stringify(port.data)) } : port
        ])
    )
}

/**
 * 恢复 valuePorts 中指定端口的旧值（返回新对象，保证引用变化）
 */
export function updatePortDataByOldData(ports: Record<string, PortDefinition> | undefined, targetHandle: string) {
    if (!ports) return undefined
    return Object.fromEntries(
        Object.entries(ports).map(([key, port]) => [
            key,
            port.inputPortName === targetHandle ? { ...port, data: port.oldData, oldData: undefined } : port
        ])
    )
}
/**
 * 全局连线校验器工厂
 */
export function createIsValidConnectionValidator(
    nodeConfigs: NodeRegistryConfig[],
    usedVueFlow: VueFlowStore
): (connection: Connection) => boolean {
    const { getNodes, getEdges } = usedVueFlow

    return (connection: Connection) => {
        const { source, target, sourceHandle, targetHandle } = connection

        const sourceNode = getNodes.value.find(n => n.id === source)
        const targetNode = getNodes.value.find(n => n.id === target)
        const sourceConfig = sourceNode ? nodeConfigs.find(c => c.type === sourceNode.type) : null
        const targetConfig = targetNode ? nodeConfigs.find(c => c.type === targetNode.type) : null

        // 1. 不允许自连
        if (source === target) {
            showMessage('不允许节点连接自己', 'error')
            return false
        }

        // 2. 节点必须存在
        if (!sourceNode || !targetNode) {
            showMessage('找不到源节点或目标节点', 'error')
            return false
        }

        // 3. 节点配置必须存在
        if (!sourceConfig || !targetConfig) {
            showMessage(`节点类型配置不存在: ${sourceNode.type} -> ${targetNode.type}`, 'error')
            return false
        }

        // 4. 端口句柄必须存在
        if (!sourceHandle || !targetHandle) {
            showMessage('端口标识不能为空', 'error')
            return false
        }

        // 5. 连接方向校验 + 端口存在校验（合并，避免重复查找）
        const sourcePort = sourceConfig.data.outputPorts?.[sourceHandle]
        const targetPort = targetConfig.data.inputPorts?.[targetHandle]

        if (!sourcePort || !targetPort) {
            showMessage('连接方向错误：只能从输出端口连接到输入端口', 'error')
            return false
        }

        // 6. 数据类型匹配校验
        const sourceTypes = getPortTypes(sourcePort)
        const targetTypes = getPortTypes(targetPort)
        const hasMatch = sourceTypes.includes(DataType.ANY) || targetTypes.includes(DataType.ANY) || sourceTypes.some(t => targetTypes.includes(t))
        if (!hasMatch) {
            showMessage(`数据类型不匹配: ${sourceTypes.join(',')} -> ${targetTypes.join(',')}`, 'error')
            return false
        }
        // 7 valueType 校验（如果有定义）
        if (sourcePort.valueType && targetPort.valueType) {
            const sourceValueTypes = Array.isArray(sourcePort.valueType)
                ? sourcePort.valueType : [sourcePort.valueType]
            const targetValueTypes = Array.isArray(targetPort.valueType)
                ? targetPort.valueType : [targetPort.valueType]
            const hasValueTypeMatch = sourceValueTypes.some(t => targetValueTypes.includes(t))
            if (!hasValueTypeMatch) {
                showMessage(`值类型不匹配: ${sourceValueTypes.join(',')} -> ${targetValueTypes.join(',')}`, 'error')
                return false
            }
        }
        // 8. 目标端口只能有一个输入连接
        const existingEdges = getEdges.value.filter(
            e => e.target === target && e.targetHandle === targetHandle
        )
        if (existingEdges.length > 0) {
            showMessage('该输入端口已有连接线，一个端口只能接收一个输入', 'error')
            return false
        }

        return true
    }
}

/**
 * 连线时触发
 */
export function onEdgeConnect(connection: Connection, usedVueFlow: VueFlowStore): void {
    const { updateNodeData, getNodes } = usedVueFlow
    const { source, target, sourceHandle, targetHandle } = connection

    const sourceNode = getNodes.value.find(n => n.id === source)
    const targetNode = getNodes.value.find(n => n.id === target)

    if (!sourceNode || !targetNode || !sourceHandle || !targetHandle) return

    const sourceOutputPort: PortDefinition = sourceNode.data.outputPorts?.[sourceHandle]

    if (targetNode.data.valuePorts) {
        // updateNodeData(targetNode.id, {
        //     valuePorts: updatePortOldDataByData(targetNode.data.valuePorts,targetHandle),
        // })
        updatePortOldDataByDataDirect(targetNode.data.valuePorts,targetHandle)
    }
    updateNodeData(targetNode.id, {
        inputPorts: updatePortData(targetNode.data.inputPorts, targetHandle, sourceOutputPort?.data,true),
    })
}

/**
 * 监听节点变化
 */
export function onNodesChanges(changes: NodeChange[], usedVueFlow: VueFlowStore): void {
    // TODO: 按需实现
}

/**
 * 监听边变化
 */
export function onEdgesChanges(changes: EdgeChange[], usedVueFlow: VueFlowStore): void {
    const { getNodes, updateNodeData } = usedVueFlow

    changes.forEach(change => {
        if (change.type !== 'remove') return

        const targetNodeId = change.target
        const targetHandle = change.targetHandle

        if (!targetNodeId || !targetHandle) return

        const targetNode = getNodes.value.find(n => n.id === targetNodeId)
        if (!targetNode) return

        // 清除 inputPorts 对应端口的值
        updateNodeData(targetNodeId, {
            inputPorts: updatePortData(targetNode.data.inputPorts, targetHandle, null,false),
        })

        // 从 oldValue 恢复 valuePorts
        if (targetNode.data.valuePorts) {
            nextTick(() => {
                updateNodeData(targetNode.id, {
                    valuePorts: updatePortDataByOldData(targetNode.data.valuePorts,targetHandle)
                })
            })
        }
    })
}
