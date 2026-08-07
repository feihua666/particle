import type { DataType,PortType } from './enums'

/**
 * 端口定义 — 声明节点的一个输入/输出端口
 *
 * 用于 inputPorts、outputPorts、valuePorts
 * 与后端 PortDefinitionDTO 对应
 */
export interface PortDefinition {
    /** 端口名称，如 "output", "body", "statusCode" */
    name?: string
    /** 端口类型 对应 {@link PortType}*/
    portType: string,
    /** 端口数据类型 对应 {@link DataType}，粗略的定义 data 字段的数据类型*/
    dataType: string | string[],
    // 值类型，主要为了校验特定输入和输出匹配,注意该类型与 data 字段没有关系，不是说的 value 的类型
    valueType?: string | string[],
    /** 是否必填（仅对输入端口有效） */
    required?: boolean
    /** 端口描述，用于 UI 提示 */
    description?: string
    /** 用户预设的值（valuePorts 使用，inputPorts/outputPorts 通常为 null） */
    data?: any,
    // 旧值，用于回滚
    oldData?: any,
    // 关联的输入端口名称，用于处理输入端口的旧值
    inputPortName?: string,
    // 是否有输入连接
    hasIncomingEdge?: boolean
}
