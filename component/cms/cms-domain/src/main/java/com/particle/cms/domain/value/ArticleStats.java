package com.particle.cms.domain.value;

import com.particle.common.domain.ValueObjRoot;
import lombok.Data;

/**
 * <p>
 * 文章统计值对象
 * 用于存储文章的各种统计数据，如字数、图片数量等
 * </p>
 *
 * @author yangwei
 * @since 2026/1/23 20:21
 */
@Data
public class ArticleStats extends ValueObjRoot {

    /**
     * 中文字数统计
     * 统计文章中中文字符的数量
     */
    private Integer chineseCharCount;

    /**
     * 英文单词数统计
     * 统计文章中英文单词的数量
     */
    private Integer englishWordCount;

    /**
     * 图片数量统计
     * 统计文章中图片的数量
     */
    private Integer imageCount;

    /**
     * 表格数量统计
     * 统计文章中表格的数量
     */
    private Integer tableCount;

    /**
     * 引用数量统计
     * 统计文章中引用的数量
     */
    private Integer citationCount;

    /**
     * 参考文献数量统计
     * 统计文章中参考文献的数量
     */
    private Integer referenceCount;
}
