package com.particle.global.tool.collection;

import cn.hutool.core.util.NumberUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * map键值根据key获取值
 * </p>
 *
 * @author yangwei
 * @since 2021-06-22 21:38
 */
public class CollectionTool {
	/**
	 * 深度获取 obj 属性值,仅支付key为字符串
	 * @param obj
	 * @param deepKey 如：a.b.c 支持集合如：a.0.b
	 * @param <T>
	 * @return
	 */
	public static <T> T getDeep(Object obj, String deepKey){
		if (obj == null) {
			return null;
		}
		Object value = obj;
		for (String key : deepKey.split(".")) {
			if(value instanceof Map){
				value = ((Map) value).get(key);
			}else if(value instanceof List){
				if(NumberUtil.isInteger(key)){
					value = ((List) value).get(NumberUtil.parseInt(key));
				}
			}
		}
		return (T) value;
	}


	/**
	 * 根据属性去重
	 * 结合filter使用
	 * @param keyExtractor
	 * @param <T>
	 * @return
	 */
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}


	/**
	 * 新建一个数组
	 * @param items
	 * @return
	 */
	public static <T> List<T> newArrayList(T... items){
		if (items != null) {
			return Stream.of(items).collect(Collectors.toList());
		}
		return null;
	}



	/**
	 * iterable 转为 list
	 * @param iterable
	 * @param <T>
	 * @return
	 */
	public static  <T> List<T> iterableToList(Iterable<T> iterable) {
		List<T> list = new ArrayList<>();
		iterable.forEach(e->list.add(e));
		return list;
	}
}
