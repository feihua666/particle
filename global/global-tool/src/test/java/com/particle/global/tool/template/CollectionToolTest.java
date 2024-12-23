package com.particle.global.tool.template;

import cn.hutool.core.lang.Dict;
import com.particle.global.tool.collection.CollectionTool;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2024/3/1 13:37
 */
public class CollectionToolTest {
    public static void main(String[] args) {
        Map<Object, Object> map = new HashMap<>();

        map.put("a", "1");

        Map<Object, Object> map1 = new HashMap<>();
        map.put("map1", map1);
        map1.put("a", "aaaa/ccccc");

        CollectionTool.replaceMapValueByPrefix(map, ((Map) Dict.<String, String>of("a", "1111")));


        System.out.println(map);
    }
}
