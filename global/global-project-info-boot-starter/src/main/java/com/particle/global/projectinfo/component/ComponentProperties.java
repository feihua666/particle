package com.particle.global.projectinfo.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>
 * 组件配置信息
 * </p>
 *
 * @author yangwei
 * @since 2023-04-14 14:12
 */
@Data
@Component
@ConfigurationProperties(prefix = "particle.project-info.component")
public class ComponentProperties {

	/**
	 * 如果配置存在all代表全部启用
	 */
	private static String ALLKEY = "all";
	/**
	 * key为组件名称 一般应该和 particle/component目录下的组件保持一致
	 * value为true或false 来决定整个项目中是否启用了某个组件
	 */
	private Map<String,Boolean> enable;

	/**
	 * 判断组件是否启用
	 * @param componentName
	 * @return
	 */
	public boolean componentEnabled(String componentName) {
		if (enable != null) {
			// 如果启用了全部，那么代表全部启用
			Boolean all = enable.get(ALLKEY);
			if (all !=null && all) {
				return all;
			}

			Boolean aBoolean = enable.get(componentName);
			return aBoolean == null ? false :  Boolean.valueOf(aBoolean);
		}
		return false;
	}
}
