package com.particle.global.tool.state;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 状态依赖，主要维护依赖关系
 * </p>
 *
 * @author yangwei
 * @since 2021-08-16 16:54
 */
public class StateDepend<K,V> {

	private Map<K,V> state;

	/**
	 * 添加一个依赖项
	 * @param key
	 * @param value
	 */
	public void addDepend(K key,V value){
		if (state == null) {
			state = new HashMap<>();
		}
		state.put(key,value);
	}

	/**
	 * 获取依赖项
	 * @param key
	 * @return
	 */
	public V getDepend(K key){
		if (state == null) {
			return null;
		}
		return state.get(key);
	}

	/**
	 * 获取所有依赖
	 * @return
	 */
	public List<V> getAllDepend(){
		if (state == null) {
			return null;
		}
		return state.values().stream().collect(Collectors.toList());
	}
}
