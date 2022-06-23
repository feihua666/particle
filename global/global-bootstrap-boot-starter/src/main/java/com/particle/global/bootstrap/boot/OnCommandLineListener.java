package com.particle.global.bootstrap.boot;

/**
 * 服务启动完成调用
 * @see OnApplicationRunnerListener
 * 为了统一服务启动完成调用，请实现 scatter.common.boot.CommonCommandLineRunner 接口来回调
 * Created by yangwei
 * Created at 2021/4/1 10:39
 */
public interface OnCommandLineListener {
    void run(String... args) throws Exception;
}
