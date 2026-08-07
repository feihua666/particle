import type { NodeGroupRegistryConfig } from './NodeGroupRegistryConfig'

/**
 * 分组注册中心
 *
 * 注册分组配置，供 Toolbar 展示使用
 */
export class NodeGroupRegistry {
    private groups = new Map<string, NodeGroupRegistryConfig>()

    register(config: NodeGroupRegistryConfig): this {
        if (this.groups.has(config.group)) {
            console.warn(`[NodeGroupRegistry] 分组 "${config.group}" 已存在，将被覆盖`)
        }
        this.groups.set(config.group, config)
        return this
    }

    registerAll(configs: NodeGroupRegistryConfig[]): this {
        configs.forEach(c => this.register(c))
        return this
    }

    getConfig(group: string): NodeGroupRegistryConfig | null {
        return this.groups.get(group) ?? null
    }

    has(group: string): boolean {
        return this.groups.has(group)
    }

    getAllConfigs(): NodeGroupRegistryConfig[] {
        return Array.from(this.groups.values())
    }
}

export const nodeGroupRegistry = new NodeGroupRegistry()
export const createNodeGroupRegistry = () => new NodeGroupRegistry()
