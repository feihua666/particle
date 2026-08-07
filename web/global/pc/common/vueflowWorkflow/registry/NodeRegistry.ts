import { markRaw } from 'vue'
import type { Component } from 'vue'
import type { NodeRegistryConfig } from './NodeRegistryConfig'
import type { NodeGroupRegistryConfig } from './NodeGroupRegistryConfig'

/**
 * 节点注册中心
 *
 * 注册节点配置，供 Toolbar 展示和 VueFlow 组件注册使用
 */
export class NodeRegistry {
    private nodes = new Map<string, NodeRegistryConfig>()

    register(config: NodeRegistryConfig): this {
        if (this.nodes.has(config.type)) {
            console.warn(`[NodeRegistry] 节点类型 "${config.type}" 已存在，将被覆盖`)
        }
        this.nodes.set(config.type, {
            ...config,
            component: markRaw(config.component) as Component,
        })
        return this
    }

    registerAll(configs: NodeRegistryConfig[]): this {
        configs.forEach(c => this.register(c))
        return this
    }

    getConfig(type: string): NodeRegistryConfig | null {
        return this.nodes.get(type) ?? null
    }

    getComponent(type: string): Component | null {
        return this.getConfig(type)?.component ?? null
    }

    has(type: string): boolean {
        return this.nodes.has(type)
    }

    getAllConfigs(): NodeRegistryConfig[] {
        return Array.from(this.nodes.values())
    }

    /**
     * 适用于直接传给 vue-flow 的 nodeTypes 参数
     */
    getNodeTypes(): Record<string, Component> {
        return Object.fromEntries(
            this.getAllConfigs().map(c => [c.type, c.component!] as [string, Component])
        )
    }

    /**
     * 按分组获取节点列表，Toolbar 直接遍历
     */
    getGroupedNodes(groups: NodeGroupRegistryConfig[]): NodeGroupRegistryConfig[] {
        return groups.map(group => ({
            ...group,
            nodes: this.getAllConfigs().filter(n => n.ui.group === group.group),
        }))
    }
}

export const nodeRegistry = new NodeRegistry()
export const createNodeRegistry = () => new NodeRegistry()
