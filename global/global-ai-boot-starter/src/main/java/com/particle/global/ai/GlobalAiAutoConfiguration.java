package com.particle.global.ai;

import com.particle.global.ai.gateway.AiModelRegistry;
import com.particle.global.ai.gateway.AiModelRouterService;
import com.particle.global.ai.gateway.ModelConfigGateway;
import com.particle.global.ai.strategy.AiModelInvocationStrategy;
import com.particle.global.projectinfo.ProjectInfo;
import com.particle.global.swagger.ApplicationContexSwaggertHelper;
import com.particle.global.swagger.SwaggerInfo;
import com.particle.global.swagger.factory.SwaggerFactory;
import io.micrometer.observation.ObservationRegistry;
import io.milvus.client.MilvusServiceClient;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.vectorstore.milvus.autoconfigure.MilvusVectorStoreProperties;
import org.springframework.ai.embedding.BatchingStrategy;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.milvus.MilvusVectorStore;
import org.springframework.ai.vectorstore.observation.VectorStoreObservationConvention;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration(proxyBeanMethods = false)
@ComponentScan
public class GlobalAiAutoConfiguration {
    /**
     * 本项目可能会使用pgvector数据库，所以需要注入pgvector的datasource
     */
    public static final String pgDatasourceName = "masterPg";

    /**
     * ai相关接口文档
     * @param projectInfo 参数不能去，依赖projectInfo
     * @return
     */
    @ConditionalOnBean({ApplicationContexSwaggertHelper.class})
    @Bean
    public GroupedOpenApi createGlobalAiRestApi(ProjectInfo projectInfo) {
        List<SecurityScheme> parameters = new ArrayList<>();

        return SwaggerFactory.createRestApi(SwaggerInfo.builder()
                .groupName("ai接口")
                .basePackage("com.particle.global.ai.endpoint")
                .securitySchemes(parameters)
                .version(ProjectInfo.VERSION)
                .title(ProjectInfo.NAME + " Swagger Apis")
                .description(ProjectInfo.NAME + " Swagger Apis Description")
                .build());
    }

    @ConditionalOnBean({MilvusServiceClient.class,EmbeddingModel.class})
    @Bean
    public MilvusVectorStore vectorStore(MilvusServiceClient milvusClient, EmbeddingModel embeddingModel,
                                         MilvusVectorStoreProperties properties, BatchingStrategy batchingStrategy,
                                         ObjectProvider<ObservationRegistry> observationRegistry,
                                         ObjectProvider<VectorStoreObservationConvention> customObservationConvention) {

        return MilvusVectorStore.builder(milvusClient, embeddingModel)
                .iDFieldName(properties.getIdFieldName())
                .collectionName(properties.getCollectionName())
                .initializeSchema(properties.isInitializeSchema())
                .batchingStrategy(batchingStrategy)
                .observationRegistry(observationRegistry.getIfUnique(() -> ObservationRegistry.NOOP))
                .customObservationConvention(customObservationConvention.getIfAvailable(() -> null))
                .build();
    }

    /**
     * AI 模型注册表
     * 自动收集所有 ChatModel Bean 并注册到注册表
     */
    @Bean
    @ConditionalOnMissingBean
    public AiModelRegistry aiModelRegistry(
            ObjectProvider<List<ChatModel>> chatModelsProvider,
            ObjectProvider<List<AiModelInvocationStrategy>> strategiesProvider,
            ObjectProvider<ChatModel> defaultChatModelProvider) {
        
        AiModelRegistry registry = new AiModelRegistry();
        
        // 注册所有 ChatModel
        if (chatModelsProvider.getIfAvailable() != null) {
            for (ChatModel chatModel : chatModelsProvider.getIfAvailable()) {
                // 从 Bean 名称或自定义注解中提取 providerCode 和 modelCode
                // 这里使用默认策略，具体实现由业务模块完善
                String beanName = chatModel.getClass().getSimpleName();
                registry.registerChatModel("default:" + beanName, chatModel);
            }
        }
        
        // 注册所有策略
        if (strategiesProvider.getIfAvailable() != null) {
            for (AiModelInvocationStrategy strategy : strategiesProvider.getIfAvailable()) {
                registry.registerStrategy(strategy);
            }
        }
        
        // 设置默认 ChatModel
        if (defaultChatModelProvider.getIfAvailable() != null) {
            registry.setDefaultChatModel(defaultChatModelProvider.getIfAvailable());
        }
        
        return registry;
    }

    /**
     * AI 模型路由服务
     */
    @Bean
    @ConditionalOnMissingBean
    public AiModelRouterService aiModelRouterService(AiModelRegistry aiModelRegistry) {
        return new AiModelRouterService(aiModelRegistry);
    }
}
