package com.particle.global.tool.http;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * url 相关工具
 * </p>
 *
 * @author yangwei
 * @since 2026/1/27 15:31
 */
public class UrlTool {


    /**
     * 构建分页URL，保留原有参数并更新分页参数
     *
     * @param currentUrl 当前URL
     * @param pageNo 页码
     * @param pageSize 每页大小
     * @return 包含更新后分页参数的URL
     */
    public static String appendPageParamsToUrl(String currentUrl, Long pageNo, Long pageSize) {
        Map<String, Object> form = new HashMap<>();
        form.put("pageNo", pageNo);
        form.put("pageSize", pageSize);
        return appendParamsToUrl(currentUrl, form);
    }
    /**
     * 添加参数到现有URL
     *
     * @param currentUrl 当前URL
     * @param additionalParams 额外参数
     * @return 更新后的URL
     */
    public static String appendParamsToUrl(String currentUrl, Map<String, Object> additionalParams) {
        Map<String, Object> form = new HashMap<>();

        // 这里判断一下，否则会有问题，如：currentUrl = "/xxx/xxx" 会把 currentUrl解析为参数key
        if (currentUrl.contains("?") || currentUrl.contains("http")) {
            Map<String, String> paramMap = HttpUtil.decodeParamMap(currentUrl, CharsetUtil.CHARSET_UTF_8);
            form.putAll(paramMap);
        }
        // 覆盖原来的
        form.putAll(additionalParams);
        // 重建URL
        // 获取基础URL部分
        String baseUrl = currentUrl.split("\\?")[0];
        return HttpUtil.urlWithForm(baseUrl, form,CharsetUtil.CHARSET_UTF_8, true);
    }
}
