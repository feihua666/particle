package com.particle.agi.domain.values;

import com.particle.common.domain.ValueObjRoot;
import com.particle.global.tool.json.JsonTool;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * <p>
 * 自定义的文档值对象，对标{@link org.springframework.ai.document.Document}
 * </p>
 *
 * @author yangwei
 * @since 2025/1/14 17:38
 */
@Data
public class AgiDocument extends ValueObjRoot {

    /**
     * Unique ID
     */
    private String id;

    /**
     * Document string content.
     */
    private String text;

    /**
     * Document media content
     */
    private AgiMedia media;

    /**
     * Metadata for the document. It should not be nested and values should be restricted
     * to string, int, float, boolean for simple use with Vector Dbs.
     */
    private Map<String, Object> metadata;

    /**
     * A numeric score associated with this document that can represent various types of
     * relevance measures.
     * <p>
     * Common uses include:
     * <ul>
     * <li>Measure of similarity between the embedding value of the document's text/media
     * and a query vector, where higher scores indicate greater similarity (opposite of
     * distance measure)
     * <li>Text relevancy rankings from retrieval systems
     * <li>Custom relevancy metrics from RAG patterns
     * </ul>
     * <p>
     * Higher values typically indicate greater relevance or similarity.
     */
    @Nullable
    private Double score;


    public String metadataToJson() {
        if (metadata == null) {
            return null;
        }
        return JsonTool.toJsonStr(metadata);
    }

    public static AgiDocument create(String id, String text, AgiMedia media, Map<String, Object> metadata, Double score) {
        AgiDocument agiDocument = new AgiDocument();
        agiDocument.id = id;
        agiDocument.text = text;
        agiDocument.media = media;
        agiDocument.metadata = metadata;
        agiDocument.score = score;
        return agiDocument;
    }

}
