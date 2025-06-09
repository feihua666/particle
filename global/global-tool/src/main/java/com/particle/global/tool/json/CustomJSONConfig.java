package com.particle.global.tool.json;

import cn.hutool.json.JSONConfig;
import lombok.Data;

/**
 * <p>
 * 自定义JSON配置
 * </p>
 *
 * @author yangwei
 * @since 2025/5/26 22:15
 */
@Data
public class CustomJSONConfig extends JSONConfig {

    private String LocalDateFormat;

    private String LocalDateTimeFormat;

    public CustomJSONConfig setLocalDateFormat(String localDateFormat) {
        LocalDateFormat = localDateFormat;
        return this;
    }

    public CustomJSONConfig setLocalDateTimeFormat(String localDateTimeFormat) {
        LocalDateTimeFormat = localDateTimeFormat;
        return this;
    }
}
