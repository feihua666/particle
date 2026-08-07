import type { XYPosition, VueFlowStore } from '@vue-flow/core'
import type { VueFlowGraphData } from '../workflow'

/**
 * 获取画布可视区域中心点对应的画布坐标
 * @param dimensions 画布尺寸
 * @param screenToFlowCoordinate 屏幕坐标转画布坐标函数
 */
export function getFlowCenterCoordinate(
    dimensions: { width: number; height: number },
    screenToFlowCoordinate: (position: XYPosition) => XYPosition
): XYPosition {
    const centerX = dimensions.width / 2
    const centerY = dimensions.height / 2
    return screenToFlowCoordinate({ x: centerX, y: centerY })
}

/**
 * 初始化数据 (VueFlow 原始格式，直接设置)
 * @param data VueFlow 原始数据
 * @param usedVueFlow VueFlow 实例
 */
export function initData(data: VueFlowGraphData, usedVueFlow: VueFlowStore): void {
    const { setNodes, setEdges, setViewport,fromObject } = usedVueFlow
    setNodes(data.nodes)
    setEdges(data.edges)
    setViewport(data.viewport)
    // fromObject(data)
}

/**
 * 获取 VueFlow 原始数据 (未经转换)
 * @param usedVueFlow VueFlow 实例
 */
export function getData(usedVueFlow: VueFlowStore): VueFlowGraphData {
    const { toObject } = usedVueFlow
    return toObject()
}
