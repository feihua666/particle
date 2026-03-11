package com.particle.cms.domain.service;

import com.particle.cms.domain.value.ArticleStats;
import com.particle.common.domain.domainservice.IBaseDomainService;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 文章统计服务接口
 * 提供文章内容统计分析相关的领域服务功能
 * </p>
 *
 * @author yangwei
 * @since 2026/1/23 19:54
 */
public interface ArticleStatisticsService extends IBaseDomainService {

    /**
     * 根据文章内容计算统计信息
     * 传入文章的HTML内容，分析并返回各种统计数据，如字数、图片数量等
     *
     * @param contentHtml 文章内容的HTML字符串
     * @return ArticleStats 包含文章统计数据的值对象
     */
    public ArticleStats calculate(String contentHtml);
}
