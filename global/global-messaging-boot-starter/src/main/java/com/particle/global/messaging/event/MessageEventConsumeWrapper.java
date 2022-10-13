package com.particle.global.messaging.event;

import com.particle.global.dto.messaging.event.AbstractMessageEvent;
import com.particle.global.messaging.event.api.MessageEventConsumeRecorder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

/**
 * <p>
 * 消息事件消费包装类
 * </p>
 *
 * @author yangwei
 * @since 2022-09-20 13:10
 */
@Slf4j
public class MessageEventConsumeWrapper {

    private MessageEventConsumeRecorder consumeRecorder;

    public MessageEventConsumeWrapper(MessageEventConsumeRecorder consumeRecorder) {
        this.consumeRecorder = consumeRecorder;
    }

    @Transactional
    public Object recordAndConsume(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Optional<Object> optionalEvent = Arrays.stream(args)
                .filter(o -> o instanceof AbstractMessageEvent)
                .findFirst();

        if (!optionalEvent.isPresent()) {
            return joinPoint.proceed();
        }

        AbstractMessageEvent event = (AbstractMessageEvent) optionalEvent.get();
        if (!consumeRecorder.record(event)) {
            log.warn("message event {} is already consumed, skip it.", event);
            return null;
        }

        return joinPoint.proceed();

    }
}
