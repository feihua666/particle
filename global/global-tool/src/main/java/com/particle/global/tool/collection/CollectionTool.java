package com.particle.global.tool.collection;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;

import java.util.*;
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


	/**
	 * 如果该属性的值对应的key为空，则清空该属性所在的对象
	 */
	private static final String __control_clear_when_empty_keys__ = "__control_clear_when_empty_keys__";
	/**
	 * 如果有该属性，则为true,则清空该属性所在的对象
	 */
	private static final String __control_clear__ = "__control_clear__";
	/**
	 * 如果该属性拼接属性名为true表示移除以该前缀为属性的属性
	 */
	private static final String __control_remove_key__ = "__control_remove_key__";
	/**
	 * 默认情况，会移除值为空的属性，该控制设置为false可以不移除
	 * 如果该属性拼接属性名为false表示不移除以该前缀为属性的属性
	 */
	private static final String __control_not_remove_empty_key__ = "__control_not_remove_empty_key__";

	/**
	 * 针对map的处理入口，主要用于构建json参数，如用于es查询
	 * 负责将map清空，和对应的key移除（如果key对应的值为map或集合为空）
	 * @param map
	 * @param <T>
	 */
	public static <T extends Map> void clearMap(T map) {
		if (map == null) {
			return;
		}
		boolean controlClearExist = map.containsKey(__control_clear__);
		if (controlClearExist) {
			Boolean controlClear = getFromMap(map, __control_clear__);
			if (controlClear) {
				map.clear();
			}else {
				map.remove(__control_clear__);
			}
		}
		// else {
			List<String> toBeRemovedKey = new ArrayList<>();
			Iterator iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry next = (Map.Entry)iterator.next();
				Object key = next.getKey();
				String controlRemoveKeyPrefixKey = __control_remove_key__ + key;
				boolean controlRemoveKeyPrefixKeyExist = map.containsKey(controlRemoveKeyPrefixKey);
				if (controlRemoveKeyPrefixKeyExist) {
					toBeRemovedKey.add(controlRemoveKeyPrefixKey);

					Boolean controlRemovePrefix = getFromMap(map, controlRemoveKeyPrefixKey);
					if (controlRemovePrefix) {
						iterator.remove();
						continue;
					}
				}

				String controlRemoveEmptyKeyPrefixKey = __control_not_remove_empty_key__ + key;
				boolean controlRemoveEmptyKeyPrefixKeyExist = map.containsKey(controlRemoveEmptyKeyPrefixKey);

				if (controlRemoveEmptyKeyPrefixKeyExist) {
					toBeRemovedKey.add(controlRemoveEmptyKeyPrefixKey);
				}
				Object value = next.getValue();

				boolean iteratorRemove = false;
				if (value instanceof Map) {
					Map<?, ?> itemMap = (Map<?, ?>) value;
					clearMap(itemMap);
					if (itemMap == null || itemMap.isEmpty()) {
						iterator.remove();
					}
				} else if (value instanceof Collection) {
					Collection<?> itemCollection = (Collection<?>) value;
					clearCollection(itemCollection);
					if (itemCollection == null || itemCollection.isEmpty()) {
						iterator.remove();
					}
				}

				if (iteratorRemove) {
					if (controlRemoveEmptyKeyPrefixKeyExist) {

						Boolean controlRemovePrefix = getFromMap(map, controlRemoveEmptyKeyPrefixKey);
						if (controlRemovePrefix == null || controlRemovePrefix) {
							continue;
						}
					}
					iterator.remove();
				}
			}
			for (String key : toBeRemovedKey) {
				map.remove(key);
			}
		// }
		/**
		 * 如果存在 {@link __control_clear_when_empty_keys__} 对应的值为key的值为空，那么清空
 		 */

		boolean controlClearWhenEmptyKeysExist = map.containsKey(__control_clear_when_empty_keys__);
		if (controlClearWhenEmptyKeysExist) {
			String controlClearWhenEmptyKeysStr = (String) map.get(__control_clear_when_empty_keys__);
			for (String controlClearExistEmptyKey : controlClearWhenEmptyKeysStr.split(",")) {
				if (map.containsKey(controlClearExistEmptyKey)) {
					Object controlClearExistEmptyKeyValue = map.get(controlClearExistEmptyKey);
					if (controlClearExistEmptyKeyValue == null) {
						map.clear();
						break;
					}
					if (controlClearExistEmptyKeyValue instanceof Map) {
						if (((Map<?, ?>) controlClearExistEmptyKeyValue).isEmpty()) {
							map.clear();
							break;
						}
					} else if (controlClearExistEmptyKeyValue instanceof Collection) {
						if (((Collection<?>) controlClearExistEmptyKeyValue).isEmpty()) {
							map.clear();
							break;
						}
					}
				}else {
					map.clear();
					break;
				}
			}
			map.remove(__control_clear_when_empty_keys__);
		}

	}


	/**
	 * 针对collection的处理入口
	 * @param collection
	 * @param <T>
	 */
	public static <T extends Collection> void clearCollection(T collection) {
		if (collection == null) {
			return;
		}
		Iterator iterator = collection.iterator();

		while (iterator.hasNext()) {
			Object next = iterator.next();
			if (next instanceof Map) {
				Map<?, ?> itemMap = (Map<?, ?>) next;
				clearMap(itemMap);
				if (itemMap == null || itemMap.isEmpty()) {
					iterator.remove();
				}
			} else if (next instanceof Collection) {
				Collection<?> itemCollection = (Collection<?>) next;
				clearCollection(itemCollection);
				if (itemCollection == null || itemCollection.isEmpty()) {
					iterator.remove();
				}
			}
		}
	}

	private static boolean getFromMap(Map map, String key) {
		if (map == null) {
			return false;
		}
		Object value = map.get(key);
		if (value == null) {
			return false;
		}
		if (value instanceof Boolean) {
			return (Boolean) value;
		}
		if (value instanceof String) {
			return Boolean.parseBoolean((String) value);
		}
		return false;
	}
	/**
	 * 将map中的每一个value值为字符串的，且以prefix开头的，替换为replaceValue
	 * @param object
	 * @param prefixReplaceValueMap key=prefix,value=replaceValue
	 */
	public static void replaceMapValueByPrefix(Object object,Map<String, String> prefixReplaceValueMap) {
		if (object instanceof Map) {
			((Map<Object, Object>) object).entrySet().forEach(entry ->{
				Object valueObj = entry.getValue();
				if (valueObj instanceof String) {
					String value = ((String) valueObj);
					for (Map.Entry<String, String> stringStringEntry : prefixReplaceValueMap.entrySet()) {
						String prefix = stringStringEntry.getKey();
						String replaceValue = stringStringEntry.getValue();
						if (StrUtil.startWith(value,prefix)) {
							entry.setValue(replaceValue + (value.substring(prefix.length())));
						}
					}
				}else if (valueObj instanceof Collection) {
					((Collection<?>) valueObj).forEach(item -> replaceMapValueByPrefix(item,prefixReplaceValueMap));
				}else if (valueObj instanceof Map) {
					replaceMapValueByPrefix(valueObj, prefixReplaceValueMap);
				}
			});
		}else if (object instanceof Collection) {
			((Collection<?>) object).forEach(item -> replaceMapValueByPrefix(item,prefixReplaceValueMap));
		}
	}

}
