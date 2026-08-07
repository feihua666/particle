package com.particle.agi.domain.model;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.math.BigDecimal;
/**
 * <p>
 * AI模型 领域模型
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:22:25
 */
@Data
@Entity
public class AgiAiModel extends AggreateRoot {

    private AgiAiModelId id;

    /**
    * 模型编码,如：（gpt-4o, qwen-plus, qwen2:7b）
    */
    private String code;

    /**
    * 模型显示名称
    */
    private String name;

    /**
    * 模型类型,如：（chat-对话, embedding-嵌入, image-图像, rerank-重排）
    */
    private Long typeDictId;

    /**
    * 最大 Token 数
    */
    private Integer maxTokens;

    /**
    * 默认温度值（0.00-2.00）
    */
    private BigDecimal defaultTemperature;
    
    /**
    * 默认 Top P 值
    */
    private BigDecimal defaultTopP;
    
    /**
    * 是否禁用
    */
    private Boolean isDisabled;

    /**
    * 是否为默认模型
    */
    private Boolean isDefault;

    /**
    * 扩展配置，如：（JSON格式，如超时时间、重试次数等）
    */
    private String extraConfigJson;

    /**
    * 所属提供商ID
    */
    private Long agiModelProviderId;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;

    /**
    * 描述
    */
    private String remark;



    /**
     * 创建AI模型领域模型对象
     * @return AI模型领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static AgiAiModel create(){
        return DomainFactory.create(AgiAiModel.class);
    }
}
