package com.particle.agi.client.agent.dto.data;

import com.particle.global.dto.basic.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 媒体响数据对象
 * 对标{@link org.springframework.ai.model.Media}
 * 承接{@link com.particle.agi.domain.values.AgiMedia} 的前端响应数据对话
 * </p>
 *
 * @author yangwei
 * @since 2025/1/14 17:38
 */
@Schema
@Data
public class AgiAgentChatMediaVO extends VO {

    @Schema(description = "媒体id")
    private String id;

    @Schema(description = "媒体类型")
    private String mimeType;

    @Schema(description = "媒体数据")
    private Object data;

    @Schema(description = "媒体名称")
    private String name;

    public static AgiAgentChatMediaVO create(String id, String mimeType, Object data, String name) {
        AgiAgentChatMediaVO agiDocumentMedia = new AgiAgentChatMediaVO();
        agiDocumentMedia.id = id;
        agiDocumentMedia.mimeType = mimeType;
        agiDocumentMedia.data = data;
        agiDocumentMedia.name = name;
        return agiDocumentMedia;
    }
}
