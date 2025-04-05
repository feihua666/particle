package com.particle.particledemo.test;

import cn.hutool.core.io.FileUtil;
import com.google.common.collect.Lists;
import com.particle.global.tool.json.JsonTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023/10/9 16:47
 */
public class TempTest1 {
    public static void main(String[] args) throws IOException {

        List<String> strings = FileUtil.readUtf8Lines("/Users/yw/temp/temp.txt");
        Set<String> collect = strings.stream().map(s -> "(" + s + "),").collect(Collectors.toSet());
        FileUtil.writeUtf8Lines(Lists.newArrayList(collect), "/Users/yw/temp/temp1.txt");

    }
}
