/**
 * 工作流枚举定义 — 前后端数据契约
 *
 * 与后端保持一致：
 * - NodeRole → NodeRoleConstants (global-dag)
 * - NodeType → NodeTypeConstants (global-dag)
 * - DataType → DataType enum (global-dag)
 */

// ==================== 节点角色 ====================

/**
 * 节点角色 — 决定节点在 DAG 中的行为
 * 基本上和 {@link NodeGroupEnum} 一致
 * 对应后端 - NodeRoleConstants
 */
export enum NodeRole {
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

// ==================== 节点执行类型 ====================

/**
 * 节点执行类型 — 对应后端 NodeTypeConstants
 */
export enum NodeType {
    CONSTANT_INPUT_TEXT = 'CONSTANT_INPUT_TEXT',
    CONSTANT_INPUT_IMAGE = 'CONSTANT_INPUT_IMAGE',
    CONSTANT_INPUT_VIDEO = 'CONSTANT_INPUT_VIDEO',

    CONSTANT_OUTPUT_TEXT = 'CONSTANT_OUTPUT_TEXT',
    CONSTANT_OUTPUT_IMAGE = 'CONSTANT_OUTPUT_IMAGE',
    CONSTANT_OUTPUT_VIDEO = 'CONSTANT_OUTPUT_VIDEO',

    DELAY = 'DELAY',
    GROOVY_SCRIPT = 'GROOVY_SCRIPT',

    CONSTANT_INPUT_HTTP_CONFIG = 'CONSTANT_INPUT_HTTP_CONFIG',
    HTTP = 'HTTP',
}

// ==================== 数据类型 ====================

/**
 * 数据类型 — 对应后端 DataType 枚举
 * 用于端口声明，实现节点间数据传递的类型约束
 * 对应后端 - DataType
 */
export enum DataType {
    STRING = 'STRING',
    NUMBER = 'NUMBER',
    BOOLEAN = 'BOOLEAN',
    JSON = 'JSON',
    ARRAY = 'ARRAY',
    BINARY = 'BINARY',
    MAP = 'MAP',
    ANY = 'ANY',
}
// ==================== 端口 ====================
/**
 * 端口常用名
 * 对应后端 -- {@link com.particle.global.dag.model.NodePort}
 */
export enum PortDefinitionName {
    // 输入端口名
    INPUT = 'input',
    // 值端口名
    CONTENT = 'content',
    // 输出端口名
    OUTPUT = 'output',
    // 输入控制端口名
    INPUTCONTROL = 'inputControl',
    // 输出控制端口名
    OUTPUTCONTROL = 'outputControl'
}
/**
 * 端口种类 — 对应后端 DataType 枚举
 * 用于端口声明，实现节点间数据传递的类型约束
 * 对应后端 - DataType
 */
export enum PortType {
    // 数据端口，默认数据端口
    DATA = 'DATA',
    // 控制端口
    CONTROL = 'CONTROL'
}
