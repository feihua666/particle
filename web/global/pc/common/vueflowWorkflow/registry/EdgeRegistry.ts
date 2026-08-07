import { markRaw } from 'vue'
import type { Component } from 'vue'
import { MarkerType } from '@vue-flow/core'
import type { Edge, VueFlowStore, Connection } from '@vue-flow/core'
import type { EdgeRegistryConfig } from './EdgeRegistryConfig'
import { generateUUID } from '../../../../common/tools/UuidTools'

/**
 * 边注册中心
 *
 * 注册边配置，供 Toolbar 展示和 VueFlow 边类型注册使用
 */
export class EdgeRegistry {
    private edges = new Map<string, EdgeRegistryConfig>()

    register(config: EdgeRegistryConfig): this {
        if (this.edges.has(config.type)) {
            console.warn(`[EdgeRegistry] 边类型 "${config.type}" 已存在，将被覆盖`)
        }
        this.edges.set(config.type, {
            ...config,
            component: markRaw(config.component) as Component,
        })
        return this
    }

    registerAll(configs: EdgeRegistryConfig[]): this {
        configs.forEach(c => this.register(c))
        return this
    }

    getConfig(type: string): EdgeRegistryConfig | null {
        return this.edges.get(type) ?? null
    }

    getComponent(type: string): Component | null {
        return this.getConfig(type)?.component ?? null
    }

    has(type: string): boolean {
        return this.edges.has(type)
    }

    getAllConfigs(): EdgeRegistryConfig[] {
        return Array.from(this.edges.values())
    }

    /**
     * 适用于直接传给 vue-flow 的 edgeTypes 参数
     */
    getEdgeTypes(): Record<string, Component> {
        return Object.fromEntries(
            this.getAllConfigs()
                .filter(c => c.component)
                .map(c => [c.type, c.component!] as [string, Component])
        )
    }
}

export const edgeRegistry = new EdgeRegistry()
export const createEdgeRegistry = () => new EdgeRegistry()


