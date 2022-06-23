package com.particle.global.tool.diff;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;


/**
 * <p>
 * 简单数据对象字典比对工具
 * </p>
 * 依赖 swagger 注解
 * @author yangwei
 * @since 2021-10-14 12:08
 */
@Slf4j
public class PojoPropertyDiffTool {

	/**
	 * 忽略的属性
	 */
	public static List<String> ignoreProperties = new ArrayList<>(2);

	/**
	 *
	 * @param oldPojo 支持内省对象和map
	 * @param newPojo 支持内省对象和map
	 * @return
	 */
	public static List<PropertyDiffResult> diff(Object oldPojo,Object newPojo){
		Assert.notNull(newPojo,"新对象不能为空");
		List<PropertyResult> newResults = propertyResults(newPojo);

		long start = System.currentTimeMillis();
		log.debug("diff 开始");
		List<PropertyDiffResult> results = new ArrayList<>();

		if (oldPojo == null) {
			results = newResults.stream().map(item -> {
				return new PropertyDiffResult()
						.setName(item.getName())
						.setProperty(item.getProperty())
						.setNewValue(item.getValue());
			}).collect(Collectors.toList());

		}else {
			List<PropertyResult> oldResults = propertyResults(oldPojo);
			for (PropertyResult newResult : newResults) {
				for (PropertyResult oldResult : oldResults) {
					if(Objects.equals(oldResult.getProperty(),newResult.getProperty()) && !Objects.equals(newResult.getValue(),oldResult.getValue())){
						results.add(new PropertyDiffResult()
								.setName(Optional.ofNullable(newResult.getName()).orElse(oldResult.getName()))
								.setProperty(newResult.getProperty())
								.setNewValue(newResult.getValue())
								.setOldValue(oldResult.getValue())
						);
					}else {
						if (!Objects.equals(oldResult.getProperty(), newResult.getProperty())) {
							log.debug("property不相等,不考虑,oldResult.getProperty()={},newResult.getProperty()={}",oldResult.getProperty(),newResult.getProperty());
						}else {
							log.debug("值相等，不考虑");
						}
					}
				}
			}
		}


		log.debug("diff 结束");
		log.info("diff 耗时{} ms",System.currentTimeMillis() - start);

		return results;
	}

	/**
	 * 获取对比结果的字符串
	 * @param oldPojo
	 * @param newPojo
	 * @return
	 */
	public static String diffString(Object oldPojo,Object newPojo){
		List<PropertyDiffResult> diff = diff(oldPojo, newPojo);
		return formatDiffResult(diff);
	}

	/**
	 * 转为字符串
	 * @param results
	 * @return
	 */
	public static String formatDiffResult(List<PropertyDiffResult> results){
		return results.stream().map(Objects::toString).collect(Collectors.joining(","));

	}
	/**
	 * 获取每个对象的值
	 * @param object
	 * @return
	 */
	private static List<PropertyResult> propertyResults(Object object){

		List<PropertyResult> results = new ArrayList<>();
		PropertyResult temp = null;
		Object valueTemp = null;
		if(object instanceof Map){
			Map newMap = ((Map<?, ?>) object);
			for (Object key : newMap.keySet()) {
				if(key instanceof String){
					temp = new PropertyResult();
					temp.setProperty(key.toString());
					valueTemp = newMap.get(key);
					valueTemp = Convert.toStr(valueTemp);
					temp.setValue((String)valueTemp);
					results.add(temp);
				}else {
					log.warn("key 不为 String 类型不处理，key={}",key);
				}
			}
		}else {
			Field[] fields = ReflectUtil.getFields(object.getClass());
			ApiModelProperty annotation = null;
			for (Field field : fields) {
				temp = new PropertyResult();
				temp.setProperty(field.getName());
				annotation = AnnotationUtil.getAnnotation(field, ApiModelProperty.class);
				if(annotation != null){
					temp.setName(annotation.value());
				}
				valueTemp = ReflectUtil.getFieldValue(object,field);
				valueTemp = Convert.toStr(valueTemp);
				temp.setValue((String)valueTemp);
				results.add(temp);
			}
		}
		results = results.stream().filter(item -> !ignoreProperties.contains(item)).collect(Collectors.toList());
		return results;
	}

	/**
	 * 每个对象的属性值
	 */
	@Setter
	@Getter
	private static class PropertyResult{
		private String name;
		private String property;
		private String value;

	}

	/**
	 * 单个字典对比结果
	 */
	@Setter
	@Getter
	@Accessors(chain = true)
	public static class PropertyDiffResult{
		private String name;
		private String property;
		private String oldValue;

		private String newValue;

		@Override
		public String toString() {
			String prefix = property;
			if (StrUtil.isNotEmpty(name)) {
				prefix += StrUtil.format("({})",name);
			}
			return StrUtil.format("{} : {} -> {}",prefix,oldValue,newValue);
		}
	}

}
