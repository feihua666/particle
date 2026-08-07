import type { NodeRegistryConfig } from './NodeRegistryConfig'
import type { EdgeRegistryConfig } from './EdgeRegistryConfig'
import {NodeGroupEnum, type NodeGroupRegistryConfig} from './NodeGroupRegistryConfig'
import { constantInputNodeBuiltInConfigs } from '../components/node/constantinput/ConstantInputNodeBuiltInConfigs'
import { constantOutputNodeBuiltInConfigs } from '../components/node/constantoutput/ConstantOutputNodeBuiltInConfigs'
import { controlNodeBuiltInConfigs } from '../components/node/control/ControlNodeBuiltInConfigs'
import { processNodeBuiltInConfigs } from '../components/node/process/ProcessNodeBuiltInConfigs'

// ==================== 内置分组 ====================

/**
 * 内置分组定义
 */
export const builtInGroups: NodeGroupRegistryConfig[] = [
    { group: NodeGroupEnum.CONSTANT_INPUT, label: '常量输入', order: 1, description: '携带数据' },
    { group: NodeGroupEnum.CONSTANT_OUTPUT, label: '常量输出', order: 2, description: '展示数据' },
    { group: NodeGroupEnum.PROCESS, label: '处理节点', order: 3, description: '有执行逻辑' },
    { group: NodeGroupEnum.CONTROL, label: '控制节点', order: 4, description: '延迟、条件、转换' },
    { group: NodeGroupEnum.INPUT, label: '输入节点', order: 5, description: '工作流入口数据' },
    { group: NodeGroupEnum.OUTPUT, label: '输出节点', order: 6, description: '工作流出口数据' },
]

// ==================== 内置节点 ====================

/**
 * 内置节点配置
 */
export const builtInNodeConfigs: NodeRegistryConfig[] = [
    ...constantInputNodeBuiltInConfigs,
    ...constantOutputNodeBuiltInConfigs,
    ...controlNodeBuiltInConfigs,
    ...processNodeBuiltInConfigs,
]

// ==================== 内置边 ====================

/**
 * 内置边配置
 */
export const builtInEdgeConfigs: EdgeRegistryConfig[] = []
