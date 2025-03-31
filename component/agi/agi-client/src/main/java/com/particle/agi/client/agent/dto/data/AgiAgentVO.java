package com.particle.agi.client.agent.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 智能体 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
@Data
@Schema
public class AgiAgentVO extends AbstractBaseIdVO {

    @Schema(description = "智能体名称")
    private String name;
    
    @Schema(description = "智能体头像")
    private String avatar;
    
    @Schema(description = "智能体简介")
    private String profile;
    
    @Schema(description = "角色设定")
    private String role;
    
    @Schema(description = "是否使用开场白")
    private Boolean isUsePrologue;
    
    @Schema(description = "开场白开场文案")
    private String prologue;
    
    @Schema(description = "开场白问题")
    private String prologueQuestionsJson;
    
    @Schema(description = "是否自动生成开场白开场文案")
    private Boolean isAutoPrologue;
    
    @Schema(description = "是否使用自动追问")
    private Boolean isUseAutoAsk;
    
    @Schema(description = "预设对话")
    private String presetDialogueJson;
    
    @Schema(description = "自动追问规则")
    private String autoAskRule;
    
    @Schema(description = "快捷指令")
    private String quickInstructionsJson;
    
    @Schema(description = "是否使用联网搜索")
    private Boolean isUseOnlineSearch;
    
    @Schema(description = "是否使用知识库")
    private Boolean isUseKnowledgeBase;
    
    @Schema(description = "知识库id")
    private Long agiKnowledgeBaseId;
    
    @Schema(description = "是否使用mcp")
    private Boolean isUseMcp;
    
    @Schema(description = "mcp插件配置")
    private String mcpPluginsJson;
    
    @Schema(description = "是否使用长期记忆")
    private Boolean isUseLongTermMemory;
    
    @Schema(description = "是否使用声音")
    private Boolean isUseVoice;
    
    @Schema(description = "声音配置")
    private String voiceJson;
    
    @Schema(description = "模型配置")
    private String modelJson;
    
    @Schema(description = "附带历史消息数")
    private Integer historyMessageMaxLength;
    
    @Schema(description = "历史消息长度压缩阈值")
    private Integer historyMessageCompressionThreshold;
    
    @Schema(description = "描述")
    private String remark;
    


}
