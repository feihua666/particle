import type {NodeRegistryConfig} from "./NodeRegistryConfig.ts";

/**
 * 分组注册配置（含该分组下的节点列表）
 *
 * Toolbar 直接遍历使用
 */
export interface NodeGroupRegistryConfig {
    /** 分组枚举值 */
    group: NodeGroupEnum
    /** 分组显示名称 */
    label: string
    /** 分组图标 */
    icon?: string
    /** 排序权重（越小越靠前） */
    order: number,
    /** 分组描述 */
    description: string
    /** 该分组下的节点 */
    nodes?: NodeRegistryConfig[]
}

/**
 * 节点分组枚举
 */
export enum NodeGroupEnum {
    /** 输入节点分组：工作流入口数据 */
    INPUT = 'INPUT',
    /** 输出节点分组：工作流出口数据 */
    OUTPUT = 'OUTPUT',
    /** 常量输入节点：携带/展示数据（文本、图片、视频） */
    CONSTANT_INPUT = 'CONSTANT_INPUT',
    /** 常量输出节点：携带/展示数据（文本、图片、视频） */
    CONSTANT_OUTPUT = 'CONSTANT_OUTPUT',
    /** 处理节点：有执行逻辑（HTTP、AI、脚本、数据库） */
    PROCESS = 'PROCESS',
    /** 控制节点：延迟、条件、转换 */
    CONTROL = 'CONTROL',
}


