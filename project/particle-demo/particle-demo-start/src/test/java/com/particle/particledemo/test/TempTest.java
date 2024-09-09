package com.particle.particledemo.test;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.google.common.collect.Lists;
import com.particle.area.client.dto.command.representation.AreaPageQueryCommand;
import com.particle.global.tool.json.JsonTool;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023/10/9 16:47
 */
public class TempTest {
    public static void main(String[] args) {

        Set<String> existCheck = new HashSet<>();

        ExcelReader reader = ExcelUtil.getReader(new File("/Users/yw/project/haiguan/副本tariff database_202401(3).xlsx"));

        List<List<Object>> read = reader.read(1);
        FileUtil.touch("/Users/yw/project/haiguan/test.txt");
        int id = 0;
        for (List<Object> objects : read) {
            id++;
            Object get0 = objects.get(0);
            Object get1 = objects.get(1);

            Map<String, Object> map2 = new HashMap<>();
            map2.put("_index", "hs_code_wco_standard");
            map2.put("_id", get0.toString());

            Map<String, Object> map1 = new HashMap<>();
            map1.put("index", map2);


            if (existCheck.contains(get0.toString())) {
                System.out.println("重复了：" + get0);
            }else {
                existCheck.add(get0.toString());
            }

            Map<String, Object> map = new HashMap<>();
            map.put("code", get0.toString());
            map.put("text", get1.toString());
            FileUtil.appendUtf8Lines(Lists.newArrayList(JsonTool.toJsonStr(map1)), "/Users/yw/project/haiguan/test.txt");
            FileUtil.appendUtf8Lines(Lists.newArrayList(JsonTool.toJsonStr(map)), "/Users/yw/project/haiguan/test.txt");
        }

    }
}
