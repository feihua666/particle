package com.particle.global.scheduler;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * <p>
 * 标识是一个任务计划
 * 需要实现该接口并声明一个 public void 方法（必须无参数且为void）并标注 {@link Scheduled}
 * </p>
 *
 * @author yangwei
 * @since 2023-06-09 15:55
 */
public interface IGlobalScheduler {
}
