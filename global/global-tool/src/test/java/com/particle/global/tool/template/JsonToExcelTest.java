package com.particle.global.tool.template;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2025/12/23 14:37
 */
public class JsonToExcelTest {

    public static void main(String[] args) {
        List<String> filePaths = new ArrayList<>();

        filePaths.add("/Users/yw/temp/jd-sku-detail_1.json");

        ExcelWriter writer = ExcelUtil.getWriter("/Users/yw/temp/jd-sku-detail_1.xlsx");

        boolean isFirst = true;
        for (String filePath : filePaths) {
            List<String> strings = FileUtil.readUtf8Lines(filePath);
            for (String string : strings) {
                if(StrUtil.isNotEmpty(string)){
                    JSONObject jsonObject = JSONUtil.parseObj(string);
                    writer.writeRow(jsonObject, isFirst);
                    isFirst = false;
                }
            }
        }
        writer.close();
    }
}
