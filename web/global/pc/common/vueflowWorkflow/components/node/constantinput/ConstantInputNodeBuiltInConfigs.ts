import TextConstantInputNode from './TextConstantInputNode.vue'
import ImageConstantInputNode from './ImageConstantInputNode.vue'
import VideoConstantInputNode from './VideoConstantInputNode.vue'
import HttpConfigConstantInputNode from './HttpConfigConstantInputNode.vue'
import type { NodeRegistryConfig } from '../../../registry/NodeRegistryConfig'
import {NodeGroupEnum} from '../../../registry/NodeGroupRegistryConfig'
import { NodeRole, NodeType, DataType } from '../../../workflow'
import {PortType} from "../../../workflow/enums.ts";

/**
 * 常量输入内置节点配置
 *
 * 每个配置包含两层：
 * - ui: 显示元数据（分组、名称、图标、尺寸）
 * - data: DAG 元数据（节点角色、类型、端口定义）
 */
export const constantInputNodeBuiltInConfigs: NodeRegistryConfig[] = [
    {
        type: 'textConstantInputNode',
        component: TextConstantInputNode,
        ui: {
            group: NodeGroupEnum.CONSTANT_INPUT,
            typeName: '常量文本输入节点',
            description: '携带文本内容，可作为输入',
            defaultWidth: 200,
            defaultHeight: 150,
        },
        data: {
            nodeRole: NodeRole.CONSTANT_INPUT,
            nodeType: NodeType.CONSTANT_INPUT_TEXT,
            valuePorts: {
                content: { name: 'content',portType: PortType.DATA, dataType: DataType.STRING, description: '文本内容输入', data: null }
            },
            outputPorts: {
                output: { name: 'output',portType: PortType.DATA, dataType: DataType.STRING, description: '文本内容输出', data: null },
            },
            config: {},
        },
    },
    {
        type: 'imageConstantInputNode',
        component: ImageConstantInputNode,
        ui: {
            group: NodeGroupEnum.CONSTANT_INPUT,
            typeName: '常量图片输入节点',
            description: '携带图片URL，可作为输入',
            defaultWidth: 200,
            defaultHeight: 150,
        },
        data: {
            nodeRole: NodeRole.CONSTANT_INPUT,
            nodeType: NodeType.CONSTANT_INPUT_IMAGE,
            valuePorts: {
                content: { portType: PortType.DATA, dataType: DataType.STRING, description: '图片URL上传', data: null },
            },
            outputPorts: {
                output: { portType: PortType.DATA, dataType: DataType.STRING, description: '图片URL输出', data: null },
            },
            config: {},
        },
    },
    {
        type: 'videoConstantInputNode',
        component: VideoConstantInputNode,
        ui: {
            group: NodeGroupEnum.CONSTANT_INPUT,
            typeName: '常量视频输入节点',
            description: '携带视频URL，可作为输入',
            defaultWidth: 200,
            defaultHeight: 150,
        },
        data: {
            nodeRole: NodeRole.CONSTANT_INPUT,
            nodeType: NodeType.CONSTANT_INPUT_VIDEO,
            valuePorts: {
                content: { portType: PortType.DATA, dataType: DataType.STRING, description: '视频URL上传', data: null },
            },
            outputPorts: {
                output: { portType: PortType.DATA, dataType: DataType.STRING, description: '视频URL输出', data: null },
            },
            config: {},
        },
    },
    {
        type: 'httpConfigConstantInputNode',
        component: HttpConfigConstantInputNode,
        ui: {
            group: NodeGroupEnum.CONSTANT_INPUT,
            typeName: '常量http配置输入节点',
            description: '携带http配置内容，可作为输入',
            // defaultWidth: 200,
            // defaultHeight: 150,
        },
        data: {
            nodeRole: NodeRole.CONSTANT_INPUT,
            nodeType: NodeType.CONSTANT_INPUT_HTTP_CONFIG,
            outputPorts: {
                output: { portType: PortType.DATA, dataType: DataType.JSON ,valueType:'httpConfig', description: 'http配置表单输出', data: null },
            },
            valuePorts: {
                content: { portType: PortType.DATA, dataType: DataType.JSON, description: '配置表单输入', data: {} },
            },
            config: {},
        },
    },
]
