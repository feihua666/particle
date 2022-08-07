package com.particle.global.tool.state;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 状态依赖机，主要维护依赖关系
 * </p>
 *
 * @author yangwei
 * @since 2021-08-16 16:54
 */

public class StateDependMultiple<K,V> extends StateDepend<K, List<V>> {


	/**
	 * 添加依赖项
	 * @param key
	 * @param value
	 */
	public void addDependItem(K key, V value) {
		List<V> depend = super.getDepend(key);
		if (depend == null) {
			depend = new ArrayList<>();
			super.addDepend(key,depend);
		}
		depend.add(value);
	}
}
