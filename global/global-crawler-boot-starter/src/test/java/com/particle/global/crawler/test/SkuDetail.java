package com.particle.global.crawler.test;

import lombok.Data;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2025/12/23 14:58
 */
@Data
public class SkuDetail {
    /**
     * sku详情页url
     */
    private String skuDetailUrl;
    /**
     * skuId
     */
    private String skuId;
    /**
     * sku名称
     */
    private String skuName;
    /**
     * sku最终价格
     */
    private String skuFinalPrice;
    /**
     * sku原价
     */
    private String skuPrice;
    /**
     * sku标题
     */
    private String skuTitle;
    /**
     * 评论数
     */
    private String commentCount;
    /**
     * 销量
     */
    private String saleCount;


    /**
     * 商品主图
     */
    private String skuMainImgUrl;
    /**
     * 商品详情图
     */
    private List<String> skuDetailImgUrls;

    /**
     * 分类id
     */
    private String categoryId;
    /**
     * 分类名称
     */
    private String categoryName;
}
