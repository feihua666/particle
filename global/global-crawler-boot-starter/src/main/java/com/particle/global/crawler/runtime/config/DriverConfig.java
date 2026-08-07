package com.particle.global.crawler.runtime.config;

import com.particle.global.crawler.common.enums.DriverType;
import lombok.Data;

/**
 * Driver 配置
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Data
public class DriverConfig {
    /**
     * 默认 Driver 类型
     */
    private DriverType driverType;

    /**
     * 是否自动关闭
     * 仅在不使用 session 生效
     */
    private Boolean isAutoClose;
    /**
     * 合并配置（当前配置优先）
     */
    public DriverConfig merge(DriverConfig defaults) {
        if (defaults == null) {
            return this;
        }
        if (this.driverType == null) {
            this.driverType = defaults.driverType;
        }
        return this;
    }

    public static DriverConfig defaultConfig() {
        DriverConfig config = new DriverConfig();
        config.setIsAutoClose(true);
        config.setDriverType(DriverType.PLAYWRIGHT);
        return config;
    }
}
