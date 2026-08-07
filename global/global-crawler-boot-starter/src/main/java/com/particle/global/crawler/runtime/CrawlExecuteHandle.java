package com.particle.global.crawler.runtime;

import com.particle.global.crawler.pipeline.CrawlPipeline;
import lombok.Data;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 爬虫执行结果
 * <p>
 * Pipeline 执行完成后的完整结果。
 * </p>
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Data
public class CrawlExecuteHandle {

    private RuntimeContext runtimeContext;
    /**
     * Pipeline
     */
    private CrawlPipeline pipeline;
    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;


    /**
     * 执行状态
     */
    @Setter
    private RuntimeStatus status;

    /**
     * 结果数据
     * 需要指定存储的 key 参考 {@link CrawlPipeline#resultDataKeys()}
     */
    private Map<String, Object> resultData;


    /**
     * 取消执行
     */
    public void cancel() {
        if (runtimeContext != null) {
            runtimeContext.setIsCancel(true);
        }
        this.status = RuntimeStatus.CANCELLED;
    }
    /**
     * 获取执行时长
     * @return
     */
    public long getDurationInMs() {
        if (endTime != null) {
            return Duration.between(startTime, endTime).toMillis();
        } else {
            return Duration.between(startTime, LocalDateTime.now()).toMillis();
        }
    }
}
