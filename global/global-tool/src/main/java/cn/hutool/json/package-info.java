/**
 * <p>
 * 主要解决不根据是否忽略null值将null转为 {@link cn.hutool.json.JSONNull},这在模板渲染时会默认输出null字符串
 * 参见修改：{@link cn.hutool.json.JSONUtil#wrap(java.lang.Object, cn.hutool.json.JSONConfig)}
 * 参见修改：{@link cn.hutool.json.InternalJSONUtil#stringToValue(java.lang.String)}
 * </p>
 *
 * @author yangwei
 * @since 2023-06-06 17:42
 */
package cn.hutool.json;