/**
 * 参考后端工具实现
 * com.particle.global.tool.str.NetPathTool
 */

/**
 * 确保开始以指定分隔符开头
 * @param {string} str
 * @param {string} separator
 * @returns {string}
 */
export function ensureBeginSeparator(str, separator = '/') {
    if (str != null) {
        if (!str.startsWith(separator)) {
            return separator + str;
        }
    }
    return str;
}

/**
 * 确保开始不以指定分隔符开头
 * @param {string} str
 * @param {string} separator
 * @returns {string}
 */
export function ensureNotBeginSeparator(str, separator = '/') {
    if (str != null) {
        if (str.startsWith(separator)) {
            return str.substring(1);
        }
    }
    return str;
}

/**
 * 确保结尾以指定分隔符结束
 * @param {string} str
 * @param {string} separator
 * @returns {string}
 */
export function ensureEndSeparator(str, separator = '/') {
    if (str != null) {
        if (!str.endsWith(separator)) {
            return str + separator;
        }
    }
    return str;
}

/**
 * 确保不以指定分隔符结尾
 * @param {string} str
 * @param {string} separator
 * @returns {string}
 */
export function ensureNotEndSeparator(str, separator = '/') {
    if (str != null) {
        if (str.endsWith(separator)) {
            return str.substring(0, str.length - 1);
        }
    }
    return str;
}

/**
 * 确保开始以.开头
 * @param {string} str
 * @returns {string}
 */
export function ensureBeginDot(str) {
    if (str != null) {
        if (!str.startsWith('.')) {
            return '.' + str;
        }
    }
    return str;
}

/**
 * 拼接路径，第一个元素的开头和最后一个元素的结尾不拼接分隔符
 * @param {string} separator
 * @param {...string} paths
 * @returns {string}
 */
export function concatPaths(separator = '/', ...paths) {
    if (paths == null) {
        return null;
    }

    if (paths.length === 0) {
        return '';
    }

    // 处理null或undefined转为空字符串
    const normalizedPaths = paths.map(p => p == null ? '' : p);

    if (normalizedPaths.length === 1) {
        return normalizedPaths[0];
    }

    const result = [];
    for (let i = 0; i < normalizedPaths.length; i++) {
        let path = normalizedPaths[i];

        if (i === 0) {
            // 第一个元素确保不以分隔符结尾
            path = ensureNotEndSeparator(path, separator);
        } else if (i === normalizedPaths.length - 1) {
            // 最后一个元素确保以分隔符开头
            path = ensureBeginSeparator(path, separator);
        } else {
            // 中间元素确保以分隔符开头且不以分隔符结尾
            path = ensureBeginSeparator(path, separator);
            path = ensureNotEndSeparator(path, separator);
        }

        result.push(path);
    }

    return result.join('');
}

// 默认导出所有函数
export default {
    ensureBeginSeparator,
    ensureNotBeginSeparator,
    ensureEndSeparator,
    ensureNotEndSeparator,
    ensureBeginDot,
    concatPaths
};
