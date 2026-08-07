import GroovyScriptProcessNode from './GroovyScriptProcessNode.vue'
import HttpProcessNode from './HttpProcessNode.vue'
import {InputPortInputControl, type NodeRegistryConfig} from '../../../registry/NodeRegistryConfig'
import {NodeGroupEnum} from '../../../registry/NodeGroupRegistryConfig'
import { NodeRole, NodeType, DataType } from '../../../workflow'
import {PortType} from "../../../workflow/enums.ts";

/**
 * 处理节点内置配置
 */
export const processNodeBuiltInConfigs: NodeRegistryConfig[] = [
    {
        type: 'groovyScriptProcessNode',
        component: GroovyScriptProcessNode,
        ui: {
            group: NodeGroupEnum.PROCESS,
            typeName: 'Groovy脚本节点',
            description: '自定义Groovy脚本',
            defaultWidth: 200,
            defaultHeight: 150,
        },
        data: {
            nodeRole: NodeRole.PROCESS,
            nodeType: NodeType.GROOVY_SCRIPT,
            inputPorts: {
                input: { portType: PortType.DATA, dataType: DataType.ANY, description: 'Groovy脚本输入', data: null },
            },
            valuePorts: {
                content: { portType: PortType.DATA, dataType: DataType.STRING, description: 'Groovy脚本内容填写', data: null },
            },
            outputPorts: {
                output: { portType: PortType.DATA, dataType: DataType.ANY, description: 'Groovy脚本输出', data: null },
            },
            config: {},
        },
    },
    {
        type: 'httpProcessNode',
        component: HttpProcessNode,
        ui: {
            group: NodeGroupEnum.PROCESS,
            typeName: 'http节点',
            description: '处理http请求',
        },
        data: {
            nodeRole: NodeRole.PROCESS,
            nodeType: NodeType.HTTP,
            inputPorts: {
                ...InputPortInputControl,
                httpConfig: { portType: PortType.DATA, dataType: DataType.JSON, valueType:'httpConfig', description: 'http配置表单输入', data: null },
                input: { portType: PortType.DATA, dataType: DataType.STRING, description: 'http请求body输入', data: null },
            },
            valuePorts: {
                content: { portType: PortType.DATA, dataType: DataType.JSON,inputPortName:'httpConfig', description: 'http配置表单填写', data: null },
            },
            outputPorts: {
                output: { portType: PortType.DATA, dataType: DataType.STRING, description: 'http请求返回输出', data: null },
            },
            config: {},
        },
    },
]
