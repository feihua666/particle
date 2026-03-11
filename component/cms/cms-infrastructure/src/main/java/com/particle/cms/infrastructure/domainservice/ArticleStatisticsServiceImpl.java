package com.particle.cms.infrastructure.domainservice;

import com.particle.cms.domain.service.ArticleStatisticsService;
import com.particle.cms.domain.value.ArticleStats;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 文章统计服务实现类
 * 根据HTML内容计算文章的各种统计信息
 * </p>
 *
 * @author yangwei
 * @since 2026/1/23 20:29
 */
@Component
public class ArticleStatisticsServiceImpl implements ArticleStatisticsService {

    /**
     * 中文字符的正则表达式
     */
    private static final Pattern CHINESE_PATTERN = Pattern.compile("[\\u4e00-\\u9fa5]");

    /**
     * 英文单词的正则表达式
     */
    private static final Pattern ENGLISH_WORD_PATTERN = Pattern.compile("\\b[a-zA-Z]+\\b");

    @Override
    public ArticleStats calculate(String contentHtml) {
        if (contentHtml == null || contentHtml.trim().isEmpty()) {
            return createDefaultArticleStatsValue();
        }

        // 解析HTML内容
        Document doc = Jsoup.parse(contentHtml);

        // 提取纯文本内容用于统计字数
        String textContent = doc.text();

        // 统计中文字数
        int chineseCharCount = countChineseCharacters(textContent);

        // 统计英文单词数
        int englishWordCount = countEnglishWords(textContent);

        // 统计图片数量
        int imageCount = countImages(doc);

        // 统计表格数量
        int tableCount = countTables(doc);

        // 统计引用数量（通常以cite或blockquote标签表示）
        int citationCount = countCitations(doc);

        // 统计参考文献数量（通常在ref或reference相关的div或section中）
        int referenceCount = countReferences(doc);

        // 创建并填充统计值对象
        ArticleStats statsValue = new ArticleStats();
        statsValue.setChineseCharCount(chineseCharCount);
        statsValue.setEnglishWordCount(englishWordCount);
        statsValue.setImageCount(imageCount);
        statsValue.setTableCount(tableCount);
        statsValue.setCitationCount(citationCount);
        statsValue.setReferenceCount(referenceCount);

        return statsValue;
    }

    /**
     * 统计中文字符数量
     * @param text 待统计的文本
     * @return 中文字符数量
     */
    private int countChineseCharacters(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        Matcher matcher = CHINESE_PATTERN.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    /**
     * 统计英文单词数量
     * @param text 待统计的文本
     * @return 英文单词数量
     */
    private int countEnglishWords(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        Matcher matcher = ENGLISH_WORD_PATTERN.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    /**
     * 统计图片数量
     * @param doc HTML文档对象
     * @return 图片数量
     */
    private int countImages(Document doc) {
        Elements imgElements = doc.select("img");
        return imgElements.size();
    }

    /**
     * 统计表格数量
     * @param doc HTML文档对象
     * @return 表格数量
     */
    private int countTables(Document doc) {
        Elements tableElements = doc.select("table");
        return tableElements.size();
    }

    /**
     * 统计引用数量
     * @param doc HTML文档对象
     * @return 引用数量
     */
    private int countCitations(Document doc) {
        // 统计 cite 标签
        int citeCount = doc.select("cite").size();

        // 统计 blockquote 标签（通常表示引用）
        int blockquoteCount = doc.select("blockquote").size();

        // 统计具有特定class或id的引用元素
        int refClassCount = doc.select("[class*=cite], [class*=quote], [id*=cite], [id*=quote]").size();

        return citeCount + blockquoteCount + refClassCount;
    }

    /**
     * 统计参考文献数量
     * @param doc HTML文档对象
     * @return 参考文献数量
     */
    private int countReferences(Document doc) {
        // 统计具有参考文献相关class或id的元素
        int refCount = doc.select(".reference, .references, .bibliography, .biblio, #reference, #references, #bibliography, #biblio").size();

        // 统计具有ref或reference的li元素
        int refLiCount = doc.select("li[class*=ref], li[id*=ref], li[class*=reference], li[id*=reference]").size();

        return refCount + refLiCount;
    }

    /**
     * 创建默认的统计值对象
     * @return 默认统计值对象
     */
    private ArticleStats createDefaultArticleStatsValue() {
        ArticleStats statsValue = new ArticleStats();
        statsValue.setChineseCharCount(0);
        statsValue.setEnglishWordCount(0);
        statsValue.setImageCount(0);
        statsValue.setTableCount(0);
        statsValue.setCitationCount(0);
        statsValue.setReferenceCount(0);
        return statsValue;
    }
}
