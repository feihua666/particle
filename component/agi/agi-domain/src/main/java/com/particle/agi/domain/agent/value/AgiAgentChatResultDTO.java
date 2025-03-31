package com.particle.agi.domain.agent.value;

import com.particle.global.dto.basic.VO;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 智能体对话结果
 * 参考{@link org.springframework.ai.chat.model.ChatResponse}
 * </p>
 *
 * @author yangwei
 * @since 2025/2/18 13:26
 */
@Data
public class AgiAgentChatResultDTO extends VO {
    /**
     * 响应的元数据信息
     */
    private AgiAgentChatResultMetadataDTO metadata;
    /**
     * 响应的内容结果信息
     */
    private List<AgiAgentChatResultContentDTO> results;

    public static AgiAgentChatResultDTO create(AgiAgentChatResultMetadataDTO metadata,List<AgiAgentChatResultContentDTO> results) {
        AgiAgentChatResultDTO agiAgentChatResultDTO = new AgiAgentChatResultDTO();
        agiAgentChatResultDTO.metadata = metadata;
        agiAgentChatResultDTO.results = results;
        return agiAgentChatResultDTO;
    }
}
