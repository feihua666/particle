/**
 * <p>
 * 修改版本 5.8.21
 * 主要解决json序列化时不能指定特殊日期格式问题
 * 参见修改：{@link cn.hutool.json.serialize.JSONWriter#writeObjValue(Object, Filter)} 283行
 * </p>
 *
 * @author yangwei
 * @since 2025-05-27 10:02:21
 */
package cn.hutool.json.serialize;

import cn.hutool.core.lang.Filter;
