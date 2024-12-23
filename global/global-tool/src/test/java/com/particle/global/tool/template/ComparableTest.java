package com.particle.global.tool.template;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.comparator.CompareUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-09-20 15:34
 */
public class ComparableTest {
	public static void main(String[] args) {

		String s = FileUtil.readUtf8String("/Users/yw/yuansu/temp/test.json");
		JSONObject jsonObject = JSONUtil.parseObj(s);
		System.out.println(jsonObject);

		List<Object> list = new ArrayList<>();
		list.add(2021);
		list.add(2033);
		list.add(2021);
		list.add(2022);
		list.add(2011);
		list.add(2041);
		System.out.println(comparableSort(list, true));
		System.out.println(comparableSort(list, false));
	}



	public static Collection<Object> comparableSort(Collection<Object> collection, boolean asc) {
		if (CollectionUtil.isEmpty(collection)) {
			return collection;
		}
		List<Object> sort = CollectionUtil.sort(collection, (v1, v2) -> {
			if (asc) {
				return CompareUtil.compare(v1, v2, false);
			}
			return 0 - CompareUtil.compare(v1, v2, false);
		});
		return sort;
	}
}
