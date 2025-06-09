package com.particle.agi.infrastructure.agent.gateway.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.agi.domain.agent.AgiAgent;
import com.particle.agi.domain.agent.AgiAgentId;
import com.particle.agi.domain.agent.gateway.AgiAgentGateway;
import com.particle.agi.domain.agent.value.*;
import com.particle.agi.domain.enums.AiMessageType;
import com.particle.agi.domain.values.AgiMedia;
import com.particle.agi.infrastructure.agent.service.IAgiAgentService;
import com.particle.agi.infrastructure.agent.dos.AgiAgentDO;
import com.particle.agi.infrastructure.agent.structmapping.AgiAgentInfrastructureStructMapping;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatDO;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageDO;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageService;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatService;
import com.particle.agi.infrastructure.tool.AgiDocumentTool;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.VectorStoreChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.metadata.ChatResponseMetadata;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * <p>
 * 智能体 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
@Component
public class AgiAgentGatewayImpl extends AbstractBaseGatewayImpl<AgiAgentId,AgiAgent> implements AgiAgentGateway {

    private IAgiAgentService iAgiAgentService;

    private ChatModel chatModel;

    private ChatMemory chatMemory;

    private IAgiAgentChatService iAgiAgentChatService;

    private IAgiAgentChatMessageService iAgiAgentChatMessageService;

    private VectorStore vectorStore;
    /**
     * 定义缓存，避免重复创建
     * key = agiAgentId，value=chatClient
     */
    private Map<Long, ChatClient> chatClientMap = new ConcurrentHashMap<>();

    @Override
    public AgiAgent getById(AgiAgentId agiAgentId) {
        AgiAgentDO byId = iAgiAgentService.getById(agiAgentId.getId());
        AgiAgent agiAgent = DomainFactory.create(AgiAgent.class);
        agiAgent = AgiAgentInfrastructureStructMapping.instance. agiAgentDOToAgiAgent(agiAgent,byId);
        return agiAgent;
    }

    @Override
    public boolean doSave(AgiAgent agiAgent) {
        AgiAgentDO agiAgentDO = AgiAgentInfrastructureStructMapping.instance.agiAgentToAgiAgentDO(agiAgent);
        if (agiAgentDO.getId() == null) {
            agiAgentDO.setAddControl(agiAgent.getAddControl());
            AgiAgentDO add = iAgiAgentService.add(agiAgentDO);
            agiAgent.setId(AgiAgentId.of(add.getId()));
            return add != null;
        }
        agiAgentDO.setUpdateControl(agiAgent.getUpdateControl());
        AgiAgentDO update = iAgiAgentService.update(agiAgentDO);
        return update != null;
    }

    @Override
    public boolean delete(AgiAgentId agiAgentId) {
        return iAgiAgentService.deleteById(agiAgentId.getId());
    }

    @Override
    public boolean delete(AgiAgentId agiAgentId, IdCommand idCommand) {
        return iAgiAgentService.deleteById(idCommand);
    }


    @Override
    public Flux<AgiAgentChatResultDTO> chatStream(AgiAgentChatParam agiAgentChatParam) {
        ChatClient chatClient = chatClientMap.computeIfAbsent(agiAgentChatParam.getAgiAgentId(), (key) -> {
            return buildChatClient(agiAgentChatParam);
        });
        // 智能体
        AgiAgent agiAgent = getById(AgiAgentId.of(agiAgentChatParam.getAgiAgentId()));

        ChatClient.ChatClientRequestSpec chatClientRequestSpec = chatClient.prompt();
        // 角色设定
        if (StrUtil.isNotEmpty(agiAgent.getRole())) {
            chatClientRequestSpec.system(agiAgent.getRole());
        }
        // 对话
        AgiAgentChatDO agiAgentChatDO = iAgiAgentChatService.getByChatId(agiAgentChatParam.getChatId());
        if (agiAgentChatDO == null) {
            AgiAgentChatDO agiAgentChatDOToBeInsert = AgiAgentChatDO.create(
                    agiAgentChatParam.getAgiAgentId(),
                    agiAgentChatParam.getChatId(),
                    agiAgentChatParam.getUserId(),
                    StrUtil.isEmpty(agiAgentChatParam.getChatTitle()) ? "新对话" : agiAgentChatParam.getChatTitle(),
                    StrUtil.isEmpty(agiAgentChatParam.getChatTitleMemo()) ? "默认初始化" : agiAgentChatParam.getChatTitleMemo(),
                    null
            );
            agiAgentChatDO = iAgiAgentChatService.add(agiAgentChatDOToBeInsert);
        }
        // 预设对话,将预设对话保存到聊天记忆中,在使用记忆时，会自动添加到历史消息中
        AgiAgentPresetDialogue presetDialogue = agiAgent.presetDialogue();
        if (presetDialogue != null) {
            if (CollectionUtil.isNotEmpty(presetDialogue.getMessages())) {
                Boolean existByChatId = iAgiAgentChatMessageService.existByChatId(agiAgentChatParam.getChatId());
                if (existByChatId != null) {
                    List<Message> presetMessages = new ArrayList<>();
                    for (AgiAgentPresetDialogue.AgiAgentPresetDialogueMessage message : presetDialogue.getMessages()) {
                        if (AiMessageType.user == AiMessageType.valueOf(message.getMessageType())) {
                            UserMessage userMessage = new UserMessage(message.getText());
                            presetMessages.add(userMessage);
                        }
                        AgiAgentChatMessageDO agiAgentChatMessageDO = AgiAgentChatMessageDO.createByAgiAgentPresetDialogueMessage(message,
                                agiAgentChatDO.getId(),
                                agiAgentChatParam.getAgiAgentId(),
                                agiAgentChatParam.getChatId(),
                                agiAgentChatParam.getUserId()
                        );
                        iAgiAgentChatMessageService.add(agiAgentChatMessageDO);
                    }
                }

            }
        }

        // 用户消息设置
        chatClientRequestSpec.user(agiAgentChatParam.getMessage());
        // 对话记忆 todo agiAgent.getHistoryMessageMaxLength()
        MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory)
                .conversationId(agiAgentChatParam.getChatId()).build();
        chatClientRequestSpec.advisors(messageChatMemoryAdvisor);
        // 长期记忆
        if (agiAgent.getIsUseLongTermMemory()) {
            VectorStoreChatMemoryAdvisor vectorStoreChatMemoryAdvisor = (VectorStoreChatMemoryAdvisor)VectorStoreChatMemoryAdvisor.builder(vectorStore)
                    .conversationId(agiAgentChatParam.getChatId())
                    .defaultTopK(5)
                    .build();
            chatClientRequestSpec.advisors(vectorStoreChatMemoryAdvisor);
        }
        // 返回结果
        Flux<ChatResponse> chatResponseFlux = chatClientRequestSpec.stream().chatResponse();

        Flux<AgiAgentChatResultDTO> agiAgentChatResultDTOFlux = chatResponseFlux.map(item -> chatResponseToAgiAgentChatResultDTO(item));

        return agiAgentChatResultDTOFlux;
    }

    /**
     * 对象映射 {@link ChatResponse} to {@link AgiAgentChatResultDTO}
     * @param chatResponse
     * @return
     */
    private AgiAgentChatResultDTO chatResponseToAgiAgentChatResultDTO(ChatResponse chatResponse) {
        // metadata
        AgiAgentChatResultMetadataDTO metadata = chatResponseMetadataToAgiAgentChatResultMetadataDTO(chatResponse.getMetadata());
        // results
        List<AgiAgentChatResultContentDTO> results = Optional.ofNullable(chatResponse.getResults())
                .map(res -> res.stream().map(re -> generationToAgiAgentChatResultContentDTO(re)).collect(Collectors.toList()))
                .orElse(null);
        return AgiAgentChatResultDTO.create(
                metadata,
                results
        );
    }

    /**
     * 对象映射 {@link ChatResponseMetadata} to {@link AgiAgentChatResultMetadataDTO}
     * @param metadata
     * @return
     */
    private AgiAgentChatResultMetadataDTO chatResponseMetadataToAgiAgentChatResultMetadataDTO(ChatResponseMetadata metadata) {
        if (metadata == null) {
            return null;
        }
        AgiAgentChatResultMetadataDTO.RateLimitDTO rateLimitDTO = Optional.ofNullable(metadata.getRateLimit())
                .map(rl -> AgiAgentChatResultMetadataDTO.RateLimitDTO.create(
                        rl.getRequestsLimit(),
                        rl.getRequestsRemaining(),
                        rl.getRequestsReset(),
                        rl.getTokensLimit(),
                        rl.getTokensRemaining(),
                        rl.getTokensReset()
                )).orElse(null);

        AgiAgentChatResultMetadataDTO.UsageDTO usageDTO = Optional.ofNullable(metadata.getUsage())
                .map(u -> AgiAgentChatResultMetadataDTO.UsageDTO.create(
                        u.getPromptTokens(),
                        u.getCompletionTokens(),
                        u.getTotalTokens(),
                        u.getNativeUsage()
                )).orElse(null);

        AgiAgentChatResultMetadataDTO metadataDTO = AgiAgentChatResultMetadataDTO.create(metadata.getId(),
                metadata.getModel(),
                rateLimitDTO, usageDTO);
        return metadataDTO;
    }

    /**
     * 对象映射 {@link Generation} to {@link AgiAgentChatResultContentDTO}
     * @param generation
     * @return
     */
    private AgiAgentChatResultContentDTO generationToAgiAgentChatResultContentDTO(Generation generation) {
        if (generation == null) {
            return null;
        }
        // output
        AgiAgentChatAssistantMessageDTO output = null;
        if (generation.getOutput() != null) {
            // toolCalls
            List<AgiAgentChatToolCallDTO> toolCalls = Optional.ofNullable(generation.getOutput().getToolCalls()).map(tcs ->
                    tcs.stream().map(toolCall ->
                            AgiAgentChatToolCallDTO.create(
                                    toolCall.id(),
                                    toolCall.name(),
                                    toolCall.type(),
                                    toolCall.arguments()
                            )
                    ).collect(Collectors.toList())

            ).orElse(null);
            // medias
            List<AgiMedia> medias = Optional.ofNullable(generation.getOutput().getMedia()).map(mds ->
                    mds.stream().map(md -> AgiDocumentTool.toAgiMedia(md))
                            .collect(Collectors.toList())
            ).orElse(null);

            AiMessageType aiMessageType = messageTypeToAiMessageType(generation.getOutput().getMessageType());
            output = AgiAgentChatAssistantMessageDTO.create(
                    aiMessageType.name(),
                    generation.getOutput().getText(),
                    generation.getOutput().getMetadata(),
                    toolCalls, medias);
        }
        // metadata
        AgiAgentChatResultContentDTO.ChatGenerationMetadataDTO metadata = null;
        if (generation.getMetadata() != null) {
            Map<String, Object> innerMetadata = null;
            if (!generation.getMetadata().isEmpty()) {
                innerMetadata = new HashMap<>();
                for (String key : generation.getMetadata().keySet()) {
                    innerMetadata.put(key, generation.getMetadata().get(key));
                }
            }
            metadata = AgiAgentChatResultContentDTO.ChatGenerationMetadataDTO.create(
                    generation.getMetadata().getFinishReason(),
                    generation.getMetadata().getContentFilters(),
                    innerMetadata
            );
        }
        return AgiAgentChatResultContentDTO.create(output, metadata);
    }

    /**
     * 对象映射 {@link MessageType} to {@link AiMessageType}
     * @param messageType
     * @return
     */
    private AiMessageType messageTypeToAiMessageType(MessageType messageType) {
        if (messageType == null) {
            return null;
        }
        return AiMessageType.valueOf(messageType.getValue());
    }
    private ChatClient buildChatClient(AgiAgentChatParam agiAgentChatParam) {
        return ChatClient.builder(chatModel)
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    @Autowired
    public void setIAgiAgentService(IAgiAgentService iAgiAgentService) {
        this.iAgiAgentService = iAgiAgentService;
    }

    @Autowired(required = false)
    public void setChatModel(ChatModel chatModel) {
        this.chatModel = chatModel;
    }
    @Autowired
    public void setChatMemory(ChatMemory chatMemory) {
        this.chatMemory = chatMemory;
    }
    @Autowired
    public void setiAgiAgentChatService(IAgiAgentChatService iAgiAgentChatService) {
        this.iAgiAgentChatService = iAgiAgentChatService;
    }
    @Autowired
    public void setiAgiAgentChatMessageService(IAgiAgentChatMessageService iAgiAgentChatMessageService) {
        this.iAgiAgentChatMessageService = iAgiAgentChatMessageService;
    }
    @Autowired(required = false)
    public void setVectorStore(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }
}
