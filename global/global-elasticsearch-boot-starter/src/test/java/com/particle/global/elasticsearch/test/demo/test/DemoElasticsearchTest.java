package com.particle.global.elasticsearch.test.demo.test;

import cn.hutool.core.util.RandomUtil;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.particle.global.elasticsearch.test.demo.TestEsDO;
import com.particle.global.elasticsearch.test.demo.repository.TestElasticsearchRepository;
import com.particle.global.test.SuperTest;
import com.particle.global.tool.id.SnowflakeIdTool;
import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.data.elasticsearch.core.query.StringQueryBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 参考官方文档：https://docs.spring.io/spring-data/elasticsearch/docs/4.4.18/reference/html/ 并搜索（New Elasticsearch client）
 * 截止到目前（2023-12-08）我们使用的是spring-data-elasticsearch 4.4.18版本，官方文档说明如下
 * New Elasticsearch client
 *
 * Elasticsearch has introduced it’s new ElasticsearchClient and has deprecated the previous ElasticsearchClient. Spring Data Elasticsearch 4.4 still uses the old client as the default client for the following reasons:
 *
 *     The new client forces applications to use the jakarta.json.spi.JsonProvider package whereas Spring Boot will stick to jakarta.json.spi.JsonProvider until version 3. So switching the default implementaiton in Spring Data Elasticsearch can only come with Spring Data Elasticsearch 5 (Spring Data 3, Spring 6).
 *
 *     There are still some bugs in the Elasticsearch client which need to be resolved
 *
 *     The implementation using the new client in Spring Data Elasticsearch is not yet complete, due to limited resources working on that - remember Spring Data Elasticsearch is a community driven project that lives from public contributions.
 * </p>
 *
 * @author yangwei
 * @since 2023/12/7 14:05
 */
@SpringBootTest
public class DemoElasticsearchTest extends SuperTest {

    @Autowired
    private TestElasticsearchRepository testElasticsearchRepository;
    @Autowired
    protected ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    protected ElasticsearchClient elasticsearchClient;
    @Autowired
    protected RestClient restClient;

    @Test
    public void demoEntryTest(){

        // queryData();

        createIndex();

        deleteAllData();
        insertTestData();
        queryData();
        queryDataPage();
    }

    private void createIndex(){
        boolean delete = elasticsearchTemplate.indexOps(TestEsDO.class).delete();
        boolean b = elasticsearchTemplate.indexOps(TestEsDO.class).create();
    }

    private void deleteAllData() {
        testElasticsearchRepository.deleteAll();
    }
    private void insertTestData() {
        List<TestEsDO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String name = "测试es测试es" + i;
            TestEsDO testEsDO = new TestEsDO();
            testEsDO.initAddData();
            testEsDO.setTitle(name);
            testEsDO.setContent("内容" +name);
            testEsDO.setIsSuccess(RandomUtil.randomBoolean());
            testEsDO.setSomeId(SnowflakeIdTool.nextId());
            testEsDO.setSomeDateTime(LocalDateTime.now());
            testEsDO.setSomeDate(LocalDate.now());

            list.add(testEsDO);
        }
        testElasticsearchRepository.saveAll(list);

        System.out.println(list);
    }

    private void queryData() {
        Iterable<TestEsDO> all = testElasticsearchRepository.findAll();
        System.out.println(all);
    }

    private void queryDataPage() {
        String json = "{\n" +
                "        \"match\": {\n" +
                "            \"title\": \"测试es0\"\n" +
                "        }\n" +
                "    }";
        StringQuery stringQuery = new StringQueryBuilder(json).build();
        PageRequest pageRequest = PageRequest.of(4, 2);
        stringQuery.setPageable(pageRequest);

        stringQuery.addSort(Sort.by(Sort.Direction.DESC,"id"));

        SearchHits<Map> search = elasticsearchTemplate.search(stringQuery, Map.class, IndexCoordinates.of(TestEsDO.indexName));

        // elasticsearchClient.search()
        long totalHits = search.getTotalHits();
        List<SearchHit<Map>> searchHits = search.getSearchHits();
        for (SearchHit<Map> searchHit : searchHits) {
            Map content = searchHit.getContent();
            System.out.println(content);
        }

    }
}
