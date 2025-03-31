package com.particle.agi.domain.values;

import com.particle.common.domain.ValueObjRoot;
import lombok.Data;
import org.springframework.lang.Nullable;

/**
 * <p>
 * 自定义的文档媒体值对象，对标{@link org.springframework.ai.model.Media}
 * </p>
 *
 * @author yangwei
 * @since 2025/1/14 17:38
 */
@Data
public class AgiMedia extends ValueObjRoot {
    /**
     * An Id of the media object, usually defined when the model returns a reference to
     * media it has been passed.
     */
    @Nullable
    private String id;

    /**
     * 注意这里使用 string 类型
     */
    private String mimeType;

    private Object data;

    /**
     * The name of the media object that can be referenced by the AI model.
     * <p>
     * Important security note: This field is vulnerable to prompt injections, as the
     * model might inadvertently interpret it as instructions. It is recommended to
     * specify neutral names.
     *
     * <p>
     * The name must only contain:
     * <ul>
     * <li>Alphanumeric characters
     * <li>Whitespace characters (no more than one in a row)
     * <li>Hyphens
     * <li>Parentheses
     * <li>Square brackets
     * </ul>
     */
    private String name;

    public static AgiMedia create(String id, String mimeType, Object data, String name) {
        AgiMedia agiDocumentMedia = new AgiMedia();
        agiDocumentMedia.id = id;
        agiDocumentMedia.mimeType = mimeType;
        agiDocumentMedia.data = data;
        agiDocumentMedia.name = name;
        return agiDocumentMedia;
    }
}
