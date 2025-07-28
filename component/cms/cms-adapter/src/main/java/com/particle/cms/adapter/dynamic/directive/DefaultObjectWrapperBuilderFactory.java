package com.particle.cms.adapter.dynamic.directive;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.Version;

/**
 * 用于将数据包装
 */
public class DefaultObjectWrapperBuilderFactory {

	private DefaultObjectWrapperBuilderFactory() {}

	private static final Version v = Configuration.getVersion();
	private static DefaultObjectWrapperBuilder defaultObjectWrapperBuilder = null;

    //静态工厂方法
    public static DefaultObjectWrapperBuilder getInstance() {
        if (defaultObjectWrapperBuilder == null) {
            defaultObjectWrapperBuilder =  new DefaultObjectWrapperBuilder(v);
        }
        return defaultObjectWrapperBuilder;
    }

    public static DefaultObjectWrapper getDefaultObjectWrapper() {
        return getInstance().build();
    }

}
