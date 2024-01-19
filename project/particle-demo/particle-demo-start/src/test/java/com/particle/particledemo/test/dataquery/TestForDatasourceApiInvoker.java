package com.particle.particledemo.test.dataquery;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.particle.dataquery.adapter.datasource.web.admin.DataQueryDatasourceApiAdminWebController;
import com.particle.dataquery.adapter.datasource.web.admin.DataQueryDatasourceApiAdminWebTestController;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceApiQueryCommand;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceApiQueryListCommand;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceApiParamType;
import com.particle.global.tool.http.HttpClientTool;
import com.particle.global.tool.json.JsonTool;
import lombok.SneakyThrows;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 仅测试使用，远程调用
 * </p>
 *
 * @author yangwei
 * @since 2024/1/19 13:48
 */
public class TestForDatasourceApiInvoker {

    /**
     * 改这里
     */
    private static String remoteDomain = "http://localhost:8080";
    /**
     * 改这里
     */
    private static String cTokenId = "7c559753-729c-4cc2-83f7-20b21fb1a17d";

    /**
     * 改这里
     * 可以设置响应数据内容类型，设置后，如果自动适配不了，可以根据这个再适配
     */
    private static DataQueryDatasourceApiParamType dataQueryDatasourceApiResponseParamType;

    /**
     * 用来测试，{@link DataQueryDatasourceApiAdminWebTestController#test(DataQueryDatasourceApiQueryCommand)}
     */
    private static String remoteTestApi = "/admin/web/data_query_datasource_api/test/api_test";

    /**
     * 用来获取id {@link DataQueryDatasourceApiAdminWebController#queryList(DataQueryDatasourceApiQueryListCommand)}
     */
    private static String remoteListApi = "/admin/web/data_query_datasource_api/list";


    /**
     * code获取数据缓存
     */
    private Map<String, Long> cache = new ConcurrentHashMap<>();

    /**
     * 脚本方法
     * @param datasourceApiCode
     * @param param
     * @param queryString
     * @return
     */
    @SneakyThrows
    public Object invoke(String datasourceApiCode, Object param, String queryString) {

        Long dataqueryDatasourceApiId = fetchDataqueryDatasourceApiIdByCode(datasourceApiCode);

        DataQueryDatasourceApiQueryCommand dataQueryDatasourceApiQueryCommand = new DataQueryDatasourceApiQueryCommand();
        dataQueryDatasourceApiQueryCommand.setDataQueryDatasourceApiId(dataqueryDatasourceApiId);
        dataQueryDatasourceApiQueryCommand.setParam(param);

        String testUrl = remoteDomain + remoteTestApi;
        String s = HttpClientTool.postJson(testUrl, JsonTool.toJsonStr(dataQueryDatasourceApiQueryCommand), config());


        try {
            return JSONUtil.parseObj(s);
        } catch (Exception e) {
            try {
                return JSONUtil.parseArray(s);
            } catch (Exception ex) {
                // 适配一下类型
                if (dataQueryDatasourceApiResponseParamType != null) {
                    return dataQueryDatasourceApiResponseParamType.adaptType(s);
                }
            }
        }
        return s;
    }


    @SneakyThrows
    private Long fetchDataqueryDatasourceApiIdByCode(String datasourceApiCode) {
        return cache.computeIfAbsent(datasourceApiCode, this::doFetchDataqueryDatasourceApiIdByCode);
    }
    @SneakyThrows
    private Long doFetchDataqueryDatasourceApiIdByCode(String datasourceApiCode) {

        String listUrl = remoteDomain + remoteListApi + "?code=" + datasourceApiCode;
        String s = HttpClientTool.get(listUrl, config());

        JSONObject entries = JSONUtil.parseObj(s);
        JSONArray jsonArray = entries.getJSONArray("data");
        if (CollectionUtil.isEmpty(jsonArray)) {
            return null;
        }
        Object next = jsonArray.stream().iterator().next();
        JSONObject firstItem = (JSONObject) next;

        return Long.parseLong(firstItem.get("id").toString());

    }

    /**
     * 请求头配置
     * @return
     */
    private HttpClientTool.ExtConfig config() {
        return HttpClientTool.ExtConfig.builder().build().addHeader("c-token-id", cTokenId);
    }
}
