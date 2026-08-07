package com.particle.global.crawler.runtime;

import com.particle.global.crawler.common.enums.BrowserType;
import com.particle.global.crawler.common.enums.DriverType;
import com.particle.global.tool.json.JsonTool;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2026/5/14 16:25
 */
public class RunitimeOptionsBuild {
    public static void main(String[] args) {
        CrawlRuntimeOptions options = CrawlRuntimeOptions.defaultOptions();

        options.getDeriver().setDriverType(DriverType.PLAYWRIGHT);
        options.getBrowser().getPlaywright().setBrowserType(BrowserType.FIREFOX);

        String jsonOptions = JsonTool.toJsonStrForHttp(options, JsonTool.getObjectMapper());

        System.out.println(jsonOptions);
    }
}
