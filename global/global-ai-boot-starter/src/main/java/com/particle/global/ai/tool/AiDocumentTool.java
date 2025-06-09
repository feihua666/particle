package com.particle.global.ai.tool;

import com.particle.global.ai.enums.AIDocumentType;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.DocumentReader;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.reader.markdown.MarkdownDocumentReader;
import org.springframework.ai.reader.markdown.config.MarkdownDocumentReaderConfig;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.ParagraphPdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.model.transformer.KeywordMetadataEnricher;
import org.springframework.ai.model.transformer.SummaryMetadataEnricher;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.core.io.Resource;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * ai文档相关工具类，主要用于将各种文件转换为Document
 * </p>
 *
 * @author yangwei
 * @since 2025/1/9 14:33
 */
public class AiDocumentTool {

    /**
     * 将资源转换为文档
     * @param resource
     * @param AIDocumentType
     * @return
     */
    public static List<Document> loadAsDocuments(Resource resource, AIDocumentType AIDocumentType) {

        DocumentReader documentReader = null;
        List<Document> documentList = null;
        switch (AIDocumentType) {
            case word :{
                // 经测试，默认只生成一个单个文档文本
                documentReader = new TikaDocumentReader(resource);
                break;
            }
            case excel :{
                documentReader = new TikaDocumentReader(resource);
                break;
            }
            case pdf :{
                try {
                    // 该实现依赖文档目录，但有的文档并没有目录，所以预先读取，看看是否有异常
                    documentReader = new ParagraphPdfDocumentReader(resource,
                            PdfDocumentReaderConfig.builder()
                                    .withPageTopMargin(0)
                                    .withPageExtractedTextFormatter(ExtractedTextFormatter.builder()
                                            .withNumberOfTopTextLinesToDelete(0)
                                            .build())
                                    .withPagesPerDocument(1)
                                    .build());
                } catch (Exception e) {
                    documentReader = new PagePdfDocumentReader(resource,
                            PdfDocumentReaderConfig.builder()
                                    .withPageTopMargin(0)
                                    .withPageExtractedTextFormatter(ExtractedTextFormatter.builder()
                                            .withNumberOfTopTextLinesToDelete(0)
                                            .build())
                                    .withPagesPerDocument(1)
                                    .build());
                }
                break;
            }
            case html :{
                documentReader = new TikaDocumentReader(resource);
                break;
            }
            case xml :{
                documentReader = new TikaDocumentReader(resource);
                break;
            }
            case markdown :{
                MarkdownDocumentReaderConfig config = MarkdownDocumentReaderConfig.builder()
                        .withHorizontalRuleCreateDocument(true)
                        .withIncludeCodeBlock(false)
                        .withIncludeBlockquote(false)
                        .build();

                documentReader = new MarkdownDocumentReader(resource, config);
                break;
            }
            case txt :{
                documentReader = new TextReader(resource);
                break;
            }
            case json :{
                documentReader = new JsonReader(resource);
                break;
            }
            case ppt :{
                documentReader = new TikaDocumentReader(resource);
                break;
            }
        }

        if (documentList == null) {
            if (documentReader != null) {
                documentList = documentReader.read();
            }
        }
        if (documentList == null) {
            documentList = Collections.emptyList();
        }
        return documentList;
    }

    /**
     * 文档拆分，主要用于单个文件太大，有些模型可能不支持
     * @param documents
     * @return
     */
    public static List<Document> splitDocuments(List<Document> documents) {
        TokenTextSplitter splitter = new TokenTextSplitter();
        return splitter.apply(documents);
    }

    /**
     * 使用大模型，将文档进行元数据提取，主要是提取文档的关联词，并添加到Metadata
     * @param documents
     * @param chatModel
     * @return
     */
    public static List<Document> enrichKeywordDocuments(List<Document> documents, ChatModel chatModel) {
        KeywordMetadataEnricher enricher = new KeywordMetadataEnricher(chatModel, 5);
        return enricher.apply(documents);
    }

    /**
     * 使用大模型，将文档进行摘要提取，主要是提取文档的摘要，并添加到Metadata
     * @param documents
     * @param chatModel
     * @return
     */
    public static List<Document> enrichSummaryDocuments(List<Document> documents, ChatModel chatModel) {
        SummaryMetadataEnricher enricher = new SummaryMetadataEnricher(chatModel,
                List.of(SummaryMetadataEnricher.SummaryType.PREVIOUS, SummaryMetadataEnricher.SummaryType.CURRENT, SummaryMetadataEnricher.SummaryType.NEXT));
        return enricher.apply(documents);
    }
}
