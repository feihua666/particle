package com.particle.global.tool.http;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;

import java.net.URI;
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

    /**
     * 从 origin URL 提取域名
     * <p><b>示例：</b></p>
     * <pre>
     * extractHostWithPort("https://example.com")           → "example.com"
     * extractHostWithPort("https://example.com:443")       → "example.com"      (443 是默认端口)
     * extractHostWithPort("https://example.com:8443")      → "example.com:8443"
     * extractHostWithPort("http://example.com")            → "example.com"
     * extractHostWithPort("http://example.com:80")         → "example.com"      (80 是默认端口)
     * extractHostWithPort("http://example.com:8080")       → "example.com:8080"
     * extractHostWithPort("https://192.168.1.1:8080")      → "192.168.1.1:8080"
     * extractHostWithPort("invalid-url")                   → "invalid-url"
     * </pre>
     */
    public static String extractHostWithPort(String origin) {
        if (origin == null) return null;
        try {
            URI uri = new URI(origin);
            String host = uri.getHost();
            if (host == null) return origin;

            int port = uri.getPort();

            // 如果端口是 -1（默认端口），不添加
            if (port != -1) {
                return host + ":" + port;
            }
            return host;
        } catch (Exception e) {
            return origin;
        }
    }
}
