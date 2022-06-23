package com.particle.global.bootstrap.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 该执行早于 {@link CommonCommandLineRunner} 执行
 * Created by yangwei
 * Created at 2021/4/1 10:42
 */
@Component
@Slf4j
public class CommonApplicationRunner implements ApplicationRunner {

    @Autowired(required = false)
    List<OnApplicationRunnerListener> listeners;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("开始执行：" + this.getClass().getName());
        if (listeners != null) {
            for (OnApplicationRunnerListener listener : listeners) {
                listener.run(args);
            }
        }
        log.debug("执行完成：" + this.getClass().getName());
    }
}
