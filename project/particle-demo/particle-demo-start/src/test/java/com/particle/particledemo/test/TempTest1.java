package com.particle.particledemo.test;

import cn.hutool.core.io.FileUtil;
import com.google.common.collect.Lists;
import com.particle.global.tool.json.JsonTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

        Map<String, String> codes = new HashMap<>(3500000,1.0F);
        BufferedReader utf8Reader = FileUtil.getUtf8Reader("/Users/yw/project/haiguan/HSCODE20240820.csv");
        String line = utf8Reader.readLine();
        int id = 0;
        while (true) {
            id++;
            line = utf8Reader.readLine();

            if (line == null) {
                break;
            }
            String[] split = line.split("\",");
            System.out.println("id = "+ id);

            String text = "";
            String code = "";
            if (split.length == 2) {
                text = split[0];
                code = split[1];
            }else if (split.length > 2) {
                for (int i = 0; i < split.length - 1; i++) {
                    text += split[i] + " ";
                }
                text = text.substring(0, text.length() - 1);
                code = split[split.length - 1];
            }else{
                split = line.split(",");
                if (split.length == 2) {
                    text = split[0];
                    code = split[1];
                }
                else if (split.length > 2) {
                    for (int i = 0; i < split.length - 1; i++) {
                        text += split[i] + " ";
                    }
                    text = text.substring(0, text.length() - 1);
                    code = split[split.length - 1];
                }
                else{
                    System.out.println("id = "+ id +", line = " + line);
                    FileUtil.appendUtf8Lines(Lists.newArrayList(line), "/Users/yw/project/haiguan/test1_error1.txt");
                    continue;
                }

            }
            text = text.trim();
            if (text.startsWith("\"")) {
                text = text.substring(1);
            }
            if (text.endsWith("\"")) {
                text = text.substring(0, text.length() - 1);
            }

            code = code.replace(".", "").trim();
            if (codes.containsKey(code)) {
                codes.put(code, codes.get(code) + "\n" + text);
            }else {
                codes.put(code, text);
            }


        }

        for (String key : codes.keySet()) {
            String code = key;
            String text = codes.get(key);
            Map<String, Object> map2 = new HashMap<>();
            map2.put("_index", "hs_code_search");
            map2.put("_id", code);

            Map<String, Object> map1 = new HashMap<>();
            map1.put("index", map2);


            Map<String, Object> map = new HashMap<>();
            map.put("code", code);
            map.put("text", text);
            FileUtil.appendUtf8Lines(Lists.newArrayList(JsonTool.toJsonStr(map1)), "/Users/yw/project/haiguan/test1.txt");
            FileUtil.appendUtf8Lines(Lists.newArrayList(JsonTool.toJsonStr(map)), "/Users/yw/project/haiguan/test1.txt");
        }

    }
}
