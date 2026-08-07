package com.particle.agi.infrastructure.model.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * AI模型提供商表
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:23:16
 */
@Accessors(chain = true)
@Data
@TableName("component_agi_model_provider")
public class AgiModelProviderDO extends BaseDO {

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


}
