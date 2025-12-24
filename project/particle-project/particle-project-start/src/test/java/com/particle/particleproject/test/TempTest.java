package com.particle.particleproject.test;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.particle.dict.client.dto.command.DictCreateCommand;
import com.particle.global.tool.http.HttpClientTool;
import com.particle.global.tool.json.JsonTool;
import lombok.SneakyThrows;

import java.io.File;
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


    static Long parentId = 1907257257159041025L;

    static String codePrefix = "company_industry_";
    static Map<String,Long> groupCodeMap = new HashMap<>();

    public static void main(String[] args) {

        ExcelReader reader = ExcelUtil.getReader(new File("/Users/yw/temp/副本2017行业分类门大中小四类详情.xlsx"));

        List<List<Object>> read = reader.read(1);


        for (List<Object> objects : read) {
            String code1 = (String) objects.get(0);
            String name1 = (String) objects.get(1);
            Long parentId1 = add(code1, name1, code1, parentId, true, true);

            String code2 = (String) objects.get(2);
            String name2 = (String) objects.get(3);
            Long parentId2 = add(code2, name2, code2, parentId1, true, true);


            String code3 = (String) objects.get(4);
            String name3 = (String) objects.get(5);
            Long parentId3 = add(code3, name3, code3, parentId2, true, true);


            String code4 = (String) objects.get(6);
            String name4 = (String) objects.get(7);
            Long parentId4 = add(code4, name4, code4, parentId3, false, true);





        }

    }

    @SneakyThrows
    private static Long add(String code, String name, String value, Long parentId, Boolean isGroup, Boolean isItem) {
        if (groupCodeMap.containsKey(codePrefix + code)) {
            return groupCodeMap.get(codePrefix + code);
        }
        String codeTemp = code;
        if (!isGroup) {
            codeTemp = null;
        }
        System.out.println(StrUtil.format("code={},name={},value={},parentId={}", codeTemp, name, value, parentId));
        DictCreateCommand createCommand = createCommand(codeTemp, name, value, parentId);
        createCommand.setIsGroup(isGroup);
        createCommand.setIsItem(isItem);
        String body = JsonTool.toJsonStr(createCommand);
        // c-token-id
        // 	67dcde68-3a5f-453e-a915-94c1c8013d53
        HttpClientTool.ExtConfig extConfig = HttpClientTool.ExtConfig.create().addHeader("c-token-id", "67dcde68-3a5f-453e-a915-94c1c8013d53");
        String postedJson = HttpClientTool.postJson("http://localhost:8080/admin/web/dict/create", body, extConfig);
        JSONObject entries = JSONUtil.parseObj(postedJson);
        JSONObject data = entries.getJSONObject("data");
        Long id = data.getLong("id");
        groupCodeMap.put(codePrefix + code, id);
        return id;
    }
    private static DictCreateCommand createCommand(String code, String name, String value, Long parentId) {
        DictCreateCommand dictCreateCommandCode1 = new DictCreateCommand();
        dictCreateCommandCode1.setCode(code);
        dictCreateCommandCode1.setName(name);
        dictCreateCommandCode1.setValue(value);
        dictCreateCommandCode1.setIsSystem(true);
        dictCreateCommandCode1.setIsPublic(true);
        dictCreateCommandCode1.setIsDisabled(false);
        dictCreateCommandCode1.setSeq(1000);
        dictCreateCommandCode1.setIsGroup(true);
        dictCreateCommandCode1.setParentId(parentId);

        return dictCreateCommandCode1;
    }
}
