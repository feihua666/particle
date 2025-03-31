package com.particle.agi.client.agent.dto.data;

import com.particle.common.client.dto.data.AbstractBaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 智能体对话响应数据对象
 * 承接{@link com.particle.agi.domain.agent.value.AgiAgentChatResultDTO} 的结果前端响应
 * </p>
 *
 * @author yangwei
 * @since 2025-02-24 13:45:04
 */
@Data
@Schema
public class AgiAgentChatResponseVO extends AbstractBaseVO {

    @Schema(description = "响应的元数据信息")
    private AgiAgentChatResponseMetadataVO metadata;

    @Schema(description = "响应的内容结果信息")
    private List<AgiAgentChatResponseContentVO> results;

    public static AgiAgentChatResponseVO create(AgiAgentChatResponseMetadataVO metadata,List<AgiAgentChatResponseContentVO> results) {
        AgiAgentChatResponseVO agiAgentChatResponseVO = new AgiAgentChatResponseVO();
        agiAgentChatResponseVO.metadata = metadata;
        agiAgentChatResponseVO.results = results;
        return agiAgentChatResponseVO;
    }
}
