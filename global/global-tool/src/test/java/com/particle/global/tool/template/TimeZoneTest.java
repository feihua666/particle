package com.particle.global.tool.template;

import static java.time.ZoneId.of;
import static java.util.TimeZone.getTimeZone;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2024/3/7 14:13
 */
public class TimeZoneTest {
    public static void main(String[] args) {
        System.out.println(getTimeZone(of("Asia/Shangha")));
    }
}
