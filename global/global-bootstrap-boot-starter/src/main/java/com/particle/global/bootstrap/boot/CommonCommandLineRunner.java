package com.particle.global.bootstrap.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 该执行晚于 {@link CommonApplicationRunner} 执行
 * @author yangwei
 * @since 2021/4/1 10:45
 */
@Slf4j
@Component
public class CommonCommandLineRunner implements CommandLineRunner {

    @Autowired(required = false)
    List<OnCommandLineListener> listeners;

    @Override
    public void run(String... args) throws Exception {
        log.debug("app command line runner start，listener count={}", Optional.ofNullable(listeners).map(List::size).orElse(0));
        if (listeners != null) {
            for (OnCommandLineListener listener : listeners) {
                listener.run(args);
            }
        }
        log.debug("app command line runner finished.");
    }
}
