package com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.executor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.big.datasource.bigdatasource.AbstractBigDatasource;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.api.config.ElasticsearchBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.executor.JdbcBigDatasourceApiExecutor;
import lombok.Data;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.data.elasticsearch.core.query.StringQueryBuilder;

import java.util.*;
import java.util.function.LongSupplier;
import java.util.stream.Collectors;

/**
 * <p>
 * elasticsearch 大数据源接口执行器抽象父类
 * </p>
 *
 * @author yangwei
 * @since 2023/11/21 09:48
 */
@Data
public class ElasticsearchBigDatasourceApiExecutor extends AbstractElasticsearchBigDatasourceApiExecutor {


    private static final String indexNames = "indexNames";
    private static final String query = "query";

    private static final String from = "from";
    private static final String size = "size";

    private static final String sort = "sort";
    private static final String order = "order";

    protected ElasticsearchRestTemplate elasticsearchRestTemplate;

    protected RestHighLevelClient restHighLevelClient;

    /**
     * 不建议使用
     */
    protected RestClient restClient;

    /**
     * 实例数据，持有一些其它数据以供扩展使用，主要是解决生产和测试或框架系统版本不兼容，放置一些额外数据以做处理
     * 该属性应该是{@link AbstractBigDatasource#instanceMap}的引用
     */
    private Map<String, Object> elasticsearchBigDatasourceInstanceMap;


    public static ElasticsearchBigDatasourceApiExecutor create(ElasticsearchRestTemplate elasticsearchRestTemplate,
                                                               RestHighLevelClient restHighLevelClient,
                                                               RestClient restClient,
                                                       Map<String, Object> elasticsearchBigDatasourceInstanceMap){
        ElasticsearchBigDatasourceApiExecutor elasticsearchBigDatasourceApiExecutor = new ElasticsearchBigDatasourceApiExecutor();
        elasticsearchBigDatasourceApiExecutor.elasticsearchRestTemplate = elasticsearchRestTemplate;
        elasticsearchBigDatasourceApiExecutor.restHighLevelClient = restHighLevelClient;
        elasticsearchBigDatasourceApiExecutor.restClient = restClient;
        elasticsearchBigDatasourceApiExecutor.setElasticsearchBigDatasourceInstanceMap(elasticsearchBigDatasourceInstanceMap);
        // 初始化监听
        elasticsearchBigDatasourceApiExecutor.executorInfrastructureListenerInitFromSpring();
        elasticsearchBigDatasourceApiExecutor.bigDatasourceApiExecutorExeCacheInitFromSpring();

        return elasticsearchBigDatasourceApiExecutor;
    }

    @Override
    public Object doExecutePage(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {
        Page pageQuery = JdbcBigDatasourceApiExecutor.pageQuery(bigDatasourceApi, command, queryString);
        ElasticsearchBigDatasourceApiConfig config = (ElasticsearchBigDatasourceApiConfig) bigDatasourceApi.config();
        ElasticsearchBigDatasourceApiConfig.RenderResult renderResult = config.render(elasticsearchRestTemplate, restHighLevelClient, restClient,elasticsearchBigDatasourceInstanceMap, command, queryString);
        if (renderResult.getResult() != null) {
            boolean b = renderResult.getResult() instanceof Page;
            if (b) {
                return ((Page<?>) renderResult.getResult());
            } else {
                throw new BigDatasourceException(" elasticsearch render result must be instance of " + Page.class.getName());
            }
        }
        // 开始处理
        PageRequest pageRequest = PageRequest.of((int) (pageQuery.getCurrent() - 1), ((int) pageQuery.getSize()));

        String strTemplateResult = renderResult.getStrTemplateResult();
        JSONObject strTemplateResultObj = JSONUtil.parseObj(strTemplateResult);

        // 索引名称
        List<String> strIndexNameList = extractIndexNames(strTemplateResultObj,config.obtainIndexNames());

        StringQuery stringQuery = extractStringQuery(strTemplateResultObj);

        // 支持截取数据查询
        PageRequest pageRequestExtracted = extractPageRequest(strTemplateResultObj);
        if (pageRequestExtracted != null) {
            stringQuery.setPageable(pageRequestExtracted);
            pageQuery = new Page(pageRequestExtracted.getPageNumber(), pageRequestExtracted.getPageSize());
        }else {
            // 设置分页
            stringQuery.setPageable(pageRequest);
        }

        // 设置排序
        List<Sort> sorts = extractSorts(strTemplateResultObj);
        if (CollectionUtil.isNotEmpty(sorts)) {
            for (Sort sort : sorts) {
                stringQuery.addSort(sort);
            }
        }


        // 执行查询
        IndexCoordinates indexCoordinates = IndexCoordinates.of(strIndexNameList.toArray(new String[strIndexNameList.size()]));

        SearchHits<Map> search = elasticsearchRestTemplate.search(stringQuery, Map.class, indexCoordinates);
        long totalHits = search.getTotalHits();

        List<SearchHit<Map>> searchHits = search.getSearchHits();
        List<?> allResult = searchHits.stream().map(item -> item.getContent()).collect(Collectors.toList());

        // 尝试查询总数
        // 发现es查询时会返回总数
        LongSupplier totalCountSupplier = () -> totalHits;

        // 如果设置了计数脚本，则使用计数脚本,计数脚本可以不用设置索引字段，这时会使用查询脚本字段
        if (StrUtil.isNotEmpty(renderResult.getStrCountTemplateResult())) {
            JSONObject strCountTemplateResultObj = JSONUtil.parseObj(renderResult.getStrCountTemplateResult());
            List<String> strCountIndexNameList = extractIndexNames(strCountTemplateResultObj,config.obtainIndexNames());

            IndexCoordinates countIndexCoordinates = null;
            if (strCountIndexNameList.size() > 0) {
                countIndexCoordinates = IndexCoordinates.of(strCountIndexNameList.toArray(new String[strCountIndexNameList.size()]));
            }

            StringQuery stringCountQuery = extractStringQuery(strCountTemplateResultObj);
            long count = elasticsearchRestTemplate.count(stringCountQuery, Optional.ofNullable(countIndexCoordinates).orElse(indexCoordinates));
            totalCountSupplier = () -> count;
        }

        pageQuery.setTotal(totalCountSupplier.getAsLong());
        pageQuery.setRecords(allResult);
        return pageQuery;
    }

    @Override
    public Object doExecuteMulti(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {
        ElasticsearchBigDatasourceApiConfig config = (ElasticsearchBigDatasourceApiConfig) bigDatasourceApi.config();
        ElasticsearchBigDatasourceApiConfig.RenderResult renderResult = config.render(elasticsearchRestTemplate, restHighLevelClient, restClient, elasticsearchBigDatasourceInstanceMap,command,queryString);
        if (renderResult.getResult() != null) {
            boolean b = renderResult.getResult() instanceof Collection;
            if (b) {
                return ((Collection) renderResult.getResult());
            }else {
                throw new BigDatasourceException(" elasticsearch render result must be instance of " + Collection.class.getName());
            }
        }


        String strTemplateResult = renderResult.getStrTemplateResult();
        JSONObject strTemplateResultObj = JSONUtil.parseObj(strTemplateResult);
        List<String> strIndexNameList = extractIndexNames(strTemplateResultObj,config.obtainIndexNames());

        StringQuery stringQuery = extractStringQuery(strTemplateResultObj);

        // 支持截取数据查询
        PageRequest pageRequest = extractPageRequest(strTemplateResultObj);
        if (pageRequest != null) {
            stringQuery.setPageable(pageRequest);
        }

        // 设置排序
        List<Sort> sorts = extractSorts(strTemplateResultObj);
        if (CollectionUtil.isNotEmpty(sorts)) {
            for (Sort sort : sorts) {
                stringQuery.addSort(sort);
            }
        }

        IndexCoordinates indexCoordinates = IndexCoordinates.of(strIndexNameList.toArray(new String[strIndexNameList.size()]));

        SearchHits<Map> search = elasticsearchRestTemplate.search(stringQuery, Map.class, indexCoordinates);
        long totalHits = search.getTotalHits();

        List<SearchHit<Map>> searchHits = search.getSearchHits();
        List<?> allResult = searchHits.stream().map(item -> item.getContent()).collect(Collectors.toList());

        return allResult;
    }

    @Override
    public Object doExecuteSingle(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {
        ElasticsearchBigDatasourceApiConfig config = (ElasticsearchBigDatasourceApiConfig) bigDatasourceApi.config();
        ElasticsearchBigDatasourceApiConfig.RenderResult renderResult = config.render(elasticsearchRestTemplate, restHighLevelClient, restClient,elasticsearchBigDatasourceInstanceMap, command,queryString);
        if (renderResult.getResult() != null) {
            return renderResult.getResult();
        }

        String strTemplateResult = renderResult.getStrTemplateResult();
        JSONObject strTemplateResultObj = JSONUtil.parseObj(strTemplateResult);
        List<String> strIndexNameList = extractIndexNames(strTemplateResultObj,config.obtainIndexNames());

        StringQuery stringQuery = extractStringQuery(strTemplateResultObj);

        // 支持截取数据查询
        PageRequest pageRequest = extractPageRequest(strTemplateResultObj);
        if (pageRequest != null) {
            stringQuery.setPageable(pageRequest);
        }
        // 设置排序
        List<Sort> sorts = extractSorts(strTemplateResultObj);
        if (CollectionUtil.isNotEmpty(sorts)) {
            for (Sort sort : sorts) {
                stringQuery.addSort(sort);
            }
        }

        IndexCoordinates indexCoordinates = IndexCoordinates.of(strIndexNameList.toArray(new String[strIndexNameList.size()]));

        SearchHits<Map> search = elasticsearchRestTemplate.search(stringQuery, Map.class, indexCoordinates);
        long totalHits = search.getTotalHits();

        List<SearchHit<Map>> searchHits = search.getSearchHits();
        List<Map> allResult = searchHits.stream().map(item -> item.getContent()).collect(Collectors.toList());

        Map result = allResult.iterator().next();
        return result;
    }


    /**
     * 提取索引名称，可以是多个
     * @param templateMap
     * @return
     */
    private List<String> extractIndexNames(JSONObject templateMap,List<String> configIndexNames) {
        if (CollectionUtil.isNotEmpty(configIndexNames)) {
            return configIndexNames;
        }

        // 这里兼容两种格式，字符串和数组
        List<String> strIndexNameList = new ArrayList<>();
        if (!templateMap.containsKey(indexNames)) {
            return strIndexNameList;
        }
        try {
            JSONArray jsonArray = templateMap.getJSONArray(indexNames);
            List<String> collect = jsonArray.stream().map(item -> item.toString()).collect(Collectors.toList());
            strIndexNameList.addAll(collect);
        } catch (Exception e) {
            String str = templateMap.getStr(indexNames);
            if (StrUtil.isNotEmpty(str)) {
                // 字符串时，支持多个以逗号分隔
                for (String indexName : str.split(",")) {
                    strIndexNameList.add(indexName);

                }
            }
        }
        return strIndexNameList;
    }

    /**
     * 提取为 {@link StringQuery}，以作为es查询条件
     * @param templateMap
     * @return
     */
    private StringQuery extractStringQuery(JSONObject templateMap) {

        String string = templateMap.getJSONObject(query).toString();
        StringQuery stringQuery = new StringQueryBuilder(string).build();
        return stringQuery;
    }

    /**
     * 提取排序
     * @param templateMap
     * @return
     */
    private List<Sort> extractSorts(JSONObject templateMap) {

        List<Sort> sorts = new ArrayList<>();

        // 添加排序
        Object o = templateMap.get(sort);
        if (o != null) {
            List<Map> orderMapList = new ArrayList<>();
            // o 不是 map 就是一个 list，支持两种方式
            if (o instanceof Map) {
                orderMapList.add(((Map<?, ?>) o));
            }else {
                orderMapList.addAll(((List) o));
            }
            if (!orderMapList.isEmpty()) {
                for (Map map : orderMapList) {
                    // key为字段名称字符串
                    for (Object key : map.keySet()) {
                        // value是一个map如：{
                        //                 "order": "desc",
                        //                 "mode": "min"
                        //             }
                        Object value = map.get(key);
                        Map valueMap = (Map)value;
                        Object orderDirection = valueMap.get(order);
                        Sort.Direction direction = Sort.Direction.fromString(Optional.of(orderDirection).map(Object::toString).orElse(Sort.Direction.ASC.name()));
                        sorts.add(Sort.by(direction, key.toString()));

                    }

                }
            }

        }

        return sorts;
    }

    private Long extractFrom(JSONObject templateMap) {
        if (!templateMap.containsKey(from)) {
            return null;
        }
        return templateMap.getLong(from);
    }
    private Long extractSize(JSONObject templateMap) {
        if (!templateMap.containsKey(size)) {
            return null;
        }
        return templateMap.getLong(size);
    }

    private PageRequest extractPageRequest(JSONObject templateMap) {
        Long from = extractFrom(templateMap);
        Long size = extractSize(templateMap);
        if (from == null && size == null) {
            return null;
        }
        if (from == null) {
            from = 0L;
        }
        if (size == null) {
            size = 10L;
        }
        Long pageNo = (from + size) / size;
        PageRequest pageRequest = PageRequest.of(pageNo.intValue(), size.intValue());

        return pageRequest;
    }
}
