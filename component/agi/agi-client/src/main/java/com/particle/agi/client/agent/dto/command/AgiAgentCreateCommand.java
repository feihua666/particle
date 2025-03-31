package com.particle.agi.client.agent.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 智能体 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
@Data
@Schema
public class AgiAgentCreateCommand extends AbstractBaseCommand {



    @NotEmpty(message = "智能体名称 不能为空")
        @Schema(description = "智能体名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @Schema(description = "智能体头像")
    private String avatar;


    @NotEmpty(message = "智能体简介 不能为空")
        @Schema(description = "智能体简介",requiredMode = Schema.RequiredMode.REQUIRED)
    private String profile;


    @Schema(description = "角色设定")
    private String role;


    @NotNull(message = "是否使用开场白 不能为空")
        @Schema(description = "是否使用开场白",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isUsePrologue;


    @Schema(description = "开场白开场文案")
    private String prologue;


    @Schema(description = "开场白问题")
    private String prologueQuestionsJson;


    @NotNull(message = "是否自动生成开场白开场文案 不能为空")
        @Schema(description = "是否自动生成开场白开场文案",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isAutoPrologue;


    @NotNull(message = "是否使用自动追问 不能为空")
        @Schema(description = "是否使用自动追问",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isUseAutoAsk;


    @Schema(description = "预设对话")
    private String presetDialogueJson;


    @Schema(description = "自动追问规则")
    private String autoAskRule;


    @Schema(description = "快捷指令")
    private String quickInstructionsJson;


    @NotNull(message = "是否使用联网搜索 不能为空")
        @Schema(description = "是否使用联网搜索",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isUseOnlineSearch;


    @NotNull(message = "是否使用知识库 不能为空")
        @Schema(description = "是否使用知识库",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isUseKnowledgeBase;


    @Schema(description = "知识库id")
    private Long agiKnowledgeBaseId;


    @NotNull(message = "是否使用mcp 不能为空")
        @Schema(description = "是否使用mcp",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isUseMcp;


    @Schema(description = "mcp插件配置")
    private String mcpPluginsJson;


    @NotNull(message = "是否使用长期记忆 不能为空")
        @Schema(description = "是否使用长期记忆",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isUseLongTermMemory;


    @NotNull(message = "是否使用声音 不能为空")
        @Schema(description = "是否使用声音",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isUseVoice;


    @Schema(description = "声音配置")
    private String voiceJson;


    @Schema(description = "模型配置")
    private String modelJson;


    @NotNull(message = "附带历史消息数 不能为空")
        @Schema(description = "附带历史消息数",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer historyMessageMaxLength;


    @NotNull(message = "历史消息长度压缩阈值 不能为空")
        @Schema(description = "历史消息长度压缩阈值",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer historyMessageCompressionThreshold;


    @Schema(description = "描述")
    private String remark;









}
