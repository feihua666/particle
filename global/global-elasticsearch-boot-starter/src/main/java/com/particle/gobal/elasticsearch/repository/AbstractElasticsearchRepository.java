package com.particle.gobal.elasticsearch.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

/**
 * <p>
 * 抽象实现，该类只是一个示例，表明可以使用neo4jClient进行引用，满足复杂需求
 * co.elastic.clients.elasticsearch.ElasticsearchClient 和 org.elasticsearch.client.ElasticsearchClient 都与 Elasticsearch 客户端相关，但是它们属于不同的 Elasticsearch Java 客户端库。
 *
 *     co.elastic.clients.elasticsearch.ElasticsearchClient:
 *         这是 Elastic 提供的新一代 Elasticsearch 客户端，被称为 "Elasticsearch Java High Level REST Client v7+"。
 *         这个客户端库是为 Elasticsearch 7.x 版本设计的，具有一些新的特性和改进。
 *         它支持异步操作，提供了更好的性能和可维护性。
 *
 *     org.elasticsearch.client.ElasticsearchClient:
 *         这是 Elasticsearch 官方的传统 Java 客户端，被称为 "TransportClient"。
 *         这个客户端库主要用于 Elasticsearch 6.x 版本及更早的版本。
 *         由于 Elasticsearch 官方推荐使用 High Level REST Client 替代 TransportClient，因此 org.elasticsearch.client.ElasticsearchClient 不再被推荐使用，并且在将来的版本中可能会被弃用。
 *
 * 总体而言，如果你使用的是 Elasticsearch 7.x 版本或更新的版本，推荐使用 co.elastic.clients.elasticsearch.ElasticsearchClient，它是官方新一代的 Java 客户端。如果你使用的是 Elasticsearch 6.x 版本或更早的版本，可以使用 org.elasticsearch.client.ElasticsearchClient，但考虑到官方的推荐，建议迁移到 High Level REST Client。
 * </p>
 *
 * @author yangwei
 * @since 2023-12-07 12:57:45
 */
public abstract class AbstractElasticsearchRepository<T, ID> implements BaseElasticsearchRepository<T, ID> {


    /**
     * spring boot 默认没有注入该bean
     */
    // @Autowired
    // protected co.elastic.clients.elasticsearch.ElasticsearchClient elasticsearchClient;


    /**
     * spring boot 默认没有注入该bean
     * Spring Data Elasticsearch已经不再建议直接使用ElasticsearchTemplate，而是建议使用ElasticsearchRestTemplate
     */
    // @Autowired
    // protected ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 以下没有测试，鉴于es7.x版本已经更新客户端，所以下面的先注释
     */

    @Autowired
    protected ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    protected RestHighLevelClient restHighLevelClient;

    @Autowired
    protected RestClient restClient;
    /**
     * spring boot 默认没有注入该bean
     */
    // @Autowired
    // protected RestClients.ElasticsearchRestClient elasticsearchRestClient;


    /**
     * spring boot 默认没有注入该bean
     */
    // @Autowired
    // protected org.elasticsearch.client.ElasticsearchClient orgElasticsearchClient;
}
