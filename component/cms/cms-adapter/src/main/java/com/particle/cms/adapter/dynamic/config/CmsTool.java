package com.particle.cms.adapter.dynamic.config;

import com.particle.global.tool.http.UrlTool;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2026/1/27 17:04
 */
@Component
public class CmsTool {

    /**
     * 构建分页URL，保留原有参数并更新分页参数
     *
     * @param currentUrl 当前URL
     * @param pageNo 页码
     * @param pageSize 每页大小
     * @return 包含更新后分页参数的URL
     */
    public String appendPageParamsToUrl(String currentUrl, Long pageNo, Long pageSize) {
        return UrlTool.appendPageParamsToUrl(currentUrl, pageNo, pageSize);
    }

}
