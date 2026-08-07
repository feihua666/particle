package com.particle.global.crawler.runtime.config.browser;

import lombok.Data;

/**
 * Playwright 开发者选项
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Data
public class DevOptions {
    /**
     * 是否开启慢速模式
     */
    private Boolean isSlowMo;

    /**
     * 慢速模式延迟（毫秒）
     */
    private Integer slowMoDelay;

    /**
     * 是否记录视频
     */
    private Boolean isRecordVideo;

    /**
     * 视频保存路径
     */
    private String videoPath;

    /**
     * 是否记录追踪
     */
    private Boolean isRecordTrace;

    /**
     * 追踪保存路径
     */
    private String tracePath;

    /**
     * 合并配置（当前配置优先）
     */
    public DevOptions merge(DevOptions defaults) {
        if (defaults == null) {
            return this;
        }
        if (this.isSlowMo == null) {
            this.isSlowMo = defaults.isSlowMo;
        }
        if (this.slowMoDelay == null) {
            this.slowMoDelay = defaults.slowMoDelay;
        }
        if (this.isRecordVideo == null) {
            this.isRecordVideo = defaults.isRecordVideo;
        }
        if (this.videoPath == null) {
            this.videoPath = defaults.videoPath;
        }
        if (this.isRecordTrace == null) {
            this.isRecordTrace = defaults.isRecordTrace;
        }
        if (this.tracePath == null) {
            this.tracePath = defaults.tracePath;
        }
        return this;
    }
    
    public static DevOptions defaultOptions(){
        DevOptions devOptions = new DevOptions();
        devOptions.setIsSlowMo(false);
        devOptions.setSlowMoDelay(0);
        devOptions.setIsRecordVideo(false);
        devOptions.setVideoPath(null);
        devOptions.setIsRecordTrace(false);
        devOptions.setTracePath(null);
        return devOptions;
    }
}
