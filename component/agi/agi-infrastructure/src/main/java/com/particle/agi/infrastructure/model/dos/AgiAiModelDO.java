package com.particle.agi.infrastructure.model.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
/**
 * <p>
 * AI模型表
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:22:25
 */
@Accessors(chain = true)
@Data
@TableName("component_agi_ai_model")
public class AgiAiModelDO extends BaseDO {

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


}
