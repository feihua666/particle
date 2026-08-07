package com.particle.global.crawler.common.constants;

/**
 * VariableStore 保留关键字
 * <p>
 * 这些 key 被系统内部使用，用户自定义变量应避免使用相同名称
 * </p>
 *
 * @author yangwei
 * @since 2026/05/14
 */
public final class ReservedKeys {

    /**
     * 认证信息
     * <p>
     * 存储认证信息，格式为数组：
     * </p>
     * <pre>
     * [
     *     "wwww.exaple.com@user001",
     *     "wwww.exaple.com@user002"
     * ]
     * </pre>
     */
    public static final String AUTH_CONFIG = "_auth_config";

    /**
     * 当前循环项
     * <p>
     * 在 LoopAction 中，当前迭代的元素会存入此变量
     * </p>
     */
    public static final String LOOP_ITEM = "_loop.item";

    /**
     * 当前循环索引（从 0 开始）
     */
    public static final String LOOP_INDEX = "_loop.index";

    /**
     * 判断是否为系统保留 key
     *
     * @param key 变量名
     * @return true 表示是保留 key
     */
    public static boolean isReserved(String key) {
        if (key == null || key.isEmpty()) {
            return false;
        }
        return key.startsWith("_");
    }

    /**
     * 判断是否为循环相关的保留 key
     */
    public static boolean isLoopReserved(String key) {
        return key != null && key.startsWith("_loop.");
    }
}
