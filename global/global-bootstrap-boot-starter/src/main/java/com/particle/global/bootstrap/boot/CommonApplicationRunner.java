package com.particle.global.bootstrap.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 该执行早于 {@link CommonCommandLineRunner} 执行
 * @author yangwei
 * @since 2021/4/1 10:42
 */
@Component
@Slf4j
public class CommonApplicationRunner implements ApplicationRunner {

    @Autowired(required = false)
    List<OnApplicationRunnerListener> listeners;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("app line runner start，listener count={}", Optional.ofNullable(listeners).map(List::size).orElse(0));
        if (listeners != null) {
            for (OnApplicationRunnerListener listener : listeners) {
                listener.run(args);
            }
        }
        log.debug("app line runner finished.");
    }
}
