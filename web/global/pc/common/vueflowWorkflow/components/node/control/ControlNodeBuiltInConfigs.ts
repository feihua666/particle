import DelayControlNode from './DelayControlNode.vue'
import {
    InputPortInputControl,
    type NodeRegistryConfig,
    OutputPortOutputControl
} from '../../../registry/NodeRegistryConfig'
import {NodeGroupEnum} from '../../../registry/NodeGroupRegistryConfig'
import { NodeRole, NodeType, DataType } from '../../../workflow'
import {PortType} from "../../../workflow/enums.ts";

/**
 * 控制节点内置配置
 */
export const controlNodeBuiltInConfigs: NodeRegistryConfig[] = [
    {
        type: 'delayControlNode',
        component: DelayControlNode,
        ui: {
            group: NodeGroupEnum.CONTROL,
            typeName: '延迟节点',
            description: '根据输入，延迟指定毫秒数',
        },
        data: {
            nodeRole: NodeRole.CONTROL,
            nodeType: NodeType.DELAY,
            inputPorts: {
                ...InputPortInputControl,
                input: { portType: PortType.DATA, dataType: DataType.NUMBER, description: '延迟时间（毫秒）输入', data: null, },
            },
            valuePorts: {
                content: { portType: PortType.DATA, dataType: DataType.NUMBER, description: '延迟时间（毫秒）填写', data: null },
                isUseInput: { portType: PortType.DATA, dataType: DataType.BOOLEAN, description: '是否使用输入作为延迟时间填写', data: false },
            },
            outputPorts: {
                output: { portType: PortType.DATA, dataType: DataType.NUMBER, description: '延迟时间（毫秒）输出', data: null },
                ...OutputPortOutputControl,
            },
            config: {},
        },
    },
]
