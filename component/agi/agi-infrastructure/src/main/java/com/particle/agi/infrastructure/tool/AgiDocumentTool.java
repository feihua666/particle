package com.particle.agi.infrastructure.tool;

import cn.hutool.core.util.StrUtil;
import com.particle.agi.domain.values.AgiDocument;
import com.particle.agi.domain.values.AgiMedia;
import org.springframework.ai.document.Document;
import org.springframework.ai.content.Media;
import org.springframework.util.MimeType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 文档相关操作工具类
 * </p>
 *
 * @author yangwei
 * @since 2025/1/15 10:19
 */
public class AgiDocumentTool {


    /**
     * 文档类型批量转换
     * @param documents
     * @return
     */
    public static List<AgiDocument> toAgiDocumentList(List<Document> documents) {
        if (documents == null) {
            return null;
        }
        return documents.stream().map(AgiDocumentTool::toAgiDocument).collect(Collectors.toList());
    }
    /**
     * 文档类型转换
     * @param document
     * @return
     */
    public static AgiDocument toAgiDocument(Document document) {
        AgiMedia agiDocumentMedia = toAgiMedia(document.getMedia());
        return AgiDocument.create(document.getId(), document.getText(), agiDocumentMedia,document.getMetadata(), document.getScore());
    }


    /**
     * 文档媒体类型转换
     * @param media
     * @return
     */
    public static AgiMedia toAgiMedia(Media media) {
        if (media == null) {
            return null;
        }
        return AgiMedia.create(media.getId(), media.getMimeType().toString(),media.getData(),media.getName());
    }

    /**
     * 文档类型批量转换
     * @param documents
     * @return
     */
    public static List<Document> toDocumentList(List<AgiDocument> documents) {
        if (documents == null) {
            return null;
        }
        return documents.stream().map(AgiDocumentTool::toDocument).collect(Collectors.toList());
    }

    /**
     * 文档类型转换
     * @param document
     * @return
     */
    public static Document toDocument(AgiDocument document) {
        Document.Builder builder = Document.builder();
        // 没有id不添加，这会在build的时候自动生成
        if (document.getId() != null) {
            builder.id(document.getId());
        }
        builder.text(document.getText());
        builder.media(toMedia(document.getMedia()));
        if (document.getMetadata() != null) {
            builder.metadata(document.getMetadata());
        }
        builder.score(document.getScore());
        return builder.build();

    }

    /**
     * 文档媒体类型转换
     * @param media
     * @return
     */
    public static Media toMedia(AgiMedia media) {
        if (media == null) {
            return null;
        }
        Media.Builder builder = Media.builder();
        builder.id(media.getId());
        if (StrUtil.isNotEmpty(media.getMimeType())) {
            builder.mimeType(MimeType.valueOf(media.getMimeType()));
        }
        builder.data(media.getData());
        builder.name(media.getName());

        return builder.build();
    }
}
