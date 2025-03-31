package com.particle.agi.domain.agent;

import cn.hutool.core.util.StrUtil;
import com.particle.agi.domain.agent.value.AgiAgentPresetDialogue;
import com.particle.agi.domain.agent.value.AgiAgentPrologueQuestion;
import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 智能体 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
@Data
@Entity
public class AgiAgent extends AggreateRoot {

    private AgiAgentId id;

    /**
    * 智能体名称
    */
    private String name;

    /**
    * 智能体头像
    */
    private String avatar;

    /**
    * 智能体简介
    */
    private String profile;

    /**
    * 角色设定，描述人设与回复逻辑的文本
    */
    private String role;

    /**
    * 是否使用开场白
    */
    private Boolean isUsePrologue;

    /**
    * 开场白开场文案
    */
    private String prologue;

    /**
    * 开场白问题,这应该是一个包含问题的数组，一般填写2-3个
    */
    private String prologueQuestionsJson;

    /**
    * 是否自动生成开场白开场文案，这一般基于历史对话使用ai生成
    */
    private Boolean isAutoPrologue;

    /**
    * 是否使用自动追问，在对话后自动追问问题
    */
    private Boolean isUseAutoAsk;

    /**
    * 预设对话，这应该是一个包含预设对话的数组，一般包含system、user、assistant角色的对话
    */
    private String presetDialogueJson;

    /**
    * 自动追问规则，描述追问的规则文本
    */
    private String autoAskRule;

    /**
    * 快捷指令,预设的快速输入，内容是一个数组，数组项包含指令内容、图标等
    */
    private String quickInstructionsJson;

    /**
    * 是否使用联网搜索
    */
    private Boolean isUseOnlineSearch;

    /**
    * 是否使用知识库
    */
    private Boolean isUseKnowledgeBase;

    /**
    * 知识库id
    */
    private Long agiKnowledgeBaseId;

    /**
    * 是否使用mcp
    */
    private Boolean isUseMcp;

    /**
    * mcp插件配置，预留暂没想好
    */
    private String mcpPluginsJson;

    /**
    * 是否使用长期记忆
    */
    private Boolean isUseLongTermMemory;

    /**
    * 是否使用声音
    */
    private Boolean isUseVoice;

    /**
    * 声音配置，应该为一个对象，配置声音的音色等
    */
    private String voiceJson;

    /**
    * 模型配置，应该为一个对象，配置使用的模型、随机性 (temperature)、核采样 (top_p)、单次回复限制 (max_tokens)、话题新鲜度 (presence_penalty)、频率惩罚度 (frequency_penalty)等
    */
    private String modelJson;

    /**
    * 附带历史消息数,一般从0-100
    */
    private Integer historyMessageMaxLength;

    /**
    * 历史消息长度压缩阈值，当未压缩的历史消息超过该值时，将进行压缩
    */
    private Integer historyMessageCompressionThreshold;

    /**
    * 描述
    */
    private String remark;

    public AgiAgentPrologueQuestion prologueQuestions(){
        if (StrUtil.isEmpty(prologueQuestionsJson)) {
            return null;
        }
        return AgiAgentPrologueQuestion.createFromJsonStr(prologueQuestionsJson);
    }
    public AgiAgentPresetDialogue presetDialogue() {
        if (StrUtil.isEmpty(presetDialogueJson)) {
            return null;
        }
        return AgiAgentPresetDialogue.createFromJsonStr(presetDialogueJson);
    }


    /**
     * 创建智能体领域模型对象
     * @return 智能体领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static AgiAgent create(){
        return DomainFactory.create(AgiAgent.class);
    }


}
