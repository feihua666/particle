import TextConstantOutputNode from './TextConstantOutputNode.vue'
import ImageConstantOutputNode from './ImageConstantOutputNode.vue'
import VideoConstantOutputNode from './VideoConstantOutputNode.vue'
import type { NodeRegistryConfig } from '../../../registry/NodeRegistryConfig'
import {NodeGroupEnum} from '../../../registry/NodeGroupRegistryConfig'
import { NodeRole, NodeType, DataType } from '../../../workflow'
import {PortType} from "../../../workflow/enums.ts";

/**
 * 常量输出内置节点配置
 *
 * 每个配置包含两层：
 * - ui: 显示元数据（分组、名称、图标、尺寸）
 * - data: DAG 元数据（节点角色、类型、端口定义）
 */
export const constantOutputNodeBuiltInConfigs: NodeRegistryConfig[] = [
    {
        type: 'textConstantOutputNode',
        component: TextConstantOutputNode,
        ui: {
            group: NodeGroupEnum.CONSTANT_OUTPUT,
            typeName: '常量文本输出节点',
            description: '展示文本内容，可作为输出',
            defaultWidth: 200,
            defaultHeight: 150,
        },
        data: {
            nodeRole: NodeRole.CONSTANT_OUTPUT,
            nodeType: NodeType.CONSTANT_OUTPUT_TEXT,
            inputPorts: {
                input: { portType: PortType.DATA, dataType: [DataType.STRING, DataType.JSON], description: '文本内容输入', data: null },
            },
            config: {},
        },
    },
    {
        type: 'imageConstantOutputNode',
        component: ImageConstantOutputNode,
        ui: {
            group: NodeGroupEnum.CONSTANT_OUTPUT,
            typeName: '常量图片输出节点',
            description: '展示图片URL渲染，可作为输出',
            defaultWidth: 200,
            defaultHeight: 150,
        },
        data: {
            nodeRole: NodeRole.CONSTANT_OUTPUT,
            nodeType: NodeType.CONSTANT_OUTPUT_IMAGE,
            inputPorts: {
                input: { portType: PortType.DATA, dataType: DataType.STRING, description: '图片URL输入', data: null },
            },
            config: {},
        },
    },
    {
        type: 'videoConstantOutputNode',
        component: VideoConstantOutputNode,
        ui: {
            group: NodeGroupEnum.CONSTANT_OUTPUT,
            typeName: '常量视频输出节点',
            description: '展示视频URL渲染，可作为输出',
            defaultWidth: 200,
            defaultHeight: 150,
        },
        data: {
            nodeRole: NodeRole.CONSTANT_OUTPUT,
            nodeType: NodeType.CONSTANT_OUTPUT_VIDEO,
            inputPorts: {
                input: { portType: PortType.DATA, dataType: DataType.STRING, description: '视频URL输入', data: null },
            },
            config: {},
        },
    },
]
