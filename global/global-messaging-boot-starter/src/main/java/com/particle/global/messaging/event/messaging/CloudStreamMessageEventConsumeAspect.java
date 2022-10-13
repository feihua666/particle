package com.particle.global.messaging.event.messaging;

import com.particle.global.messaging.event.MessageEventConsumeWrapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * <p>
 * rabbit消息事件消费切面类
 * </p>
 *
 * @author yangwei
 * @since 2022-09-20 13:10
 */
@Slf4j
@Aspect
public class CloudStreamMessageEventConsumeAspect {
    private MessageEventConsumeWrapper consumeWrapper;

    public CloudStreamMessageEventConsumeAspect(MessageEventConsumeWrapper consumeWrapper) {
        this.consumeWrapper = consumeWrapper;
    }

    @Around("@annotation(com.particle.global.messaging.event.messaging.CloudStreamConsume)")
    public Object recordEvents(ProceedingJoinPoint joinPoint) throws Throwable {
        return consumeWrapper.recordAndConsume(joinPoint);
    }
}