package com.particle.agi.domain.model;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * AI模型提供商 领域模型
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:23:16
 */
@Data
@Entity
public class AgiModelProvider extends AggreateRoot {

    private AgiModelProviderId id;

    /**
    * 提供商编码，如：（openai, dashscope, ollama, deepseek）
    */
    private String code;

    /**
    * 提供商显示名称，如：（OpenAI, 阿里灵积, Ollama本地）
    */
    private String name;

    /**
    * 提供商类型,如：（cloud-云服务, local-本地服务, self-hosted-自建）
    */
    private Long typeDictId;

    /**
    * API 基础地址
    */
    private String baseUrl;

    /**
    * 加密的 API Key
    */
    private String apiKeyEncrypted;

    /**
    * 是否禁用
    */
    private Boolean isDisabled;

    /**
    * 扩展配置，如：（JSON格式，如超时时间、重试次数等）
    */
    private String extraConfigJson;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;

    /**
    * 描述
    */
    private String remark;



    /**
     * 创建AI模型提供商领域模型对象
     * @return AI模型提供商领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static AgiModelProvider create(){
        return DomainFactory.create(AgiModelProvider.class);
    }
}
