package com.particle.agi.infrastructure.ai;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.agi.infrastructure.chat.dos.*;
import com.particle.agi.infrastructure.chat.service.*;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.*;
import org.springframework.ai.model.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 智能体对话数据库存储实现
 * </p>
 *
 * @author yangwei
 * @since 2025/2/21 15:38
 */

public class AgiAgentChatMemory implements ChatMemory {

    private IAgiAgentChatService agiAgentChatService;
    private IAgiAgentChatMessageService agiAgentChatMessageService;
    private IAgiAgentChatMessageMediaService agiAgentChatMessageMediaService;
    private IAgiAgentChatMessageToolcallService agiAgentChatMessageToolcallService;
    private IAgiAgentChatMessageToolService agiAgentChatMessageToolService;

    @Override
    public void add(String conversationId, List<Message> messages) {
        AgiAgentChatDO agiAgentChatDO = agiAgentChatService.getByChatId(conversationId);
        for (Message msg : messages) {
            // USER
            if (MessageType.USER == msg.getMessageType()) {
                UserMessage message = (UserMessage) msg;
                AgiAgentChatMessageDO agiAgentChatMessageDO = AgiAgentChatMessageDO.createByUserMessage(message,
                        agiAgentChatDO.getId(),
                        agiAgentChatDO.getAgiAgentId(),
                        conversationId,
                        agiAgentChatDO.getUserId());
                AgiAgentChatMessageDO agiAgentChatMessageDOAdded = agiAgentChatMessageService.add(agiAgentChatMessageDO);
                // Media
                List<Media> medias = message.getMedia();
                if (CollectionUtil.isNotEmpty(medias)) {
                    for (Media media : medias) {
                        AgiAgentChatMessageMediaDO agiAgentChatMessageMediaDO = AgiAgentChatMessageMediaDO.createByMedia(
                                media,
                                agiAgentChatMessageDOAdded.getId()
                        );
                        agiAgentChatMessageMediaService.add(agiAgentChatMessageMediaDO);
                    }
                }
            }
            // ASSISTANT
            else if (MessageType.ASSISTANT == msg.getMessageType()) {
                AssistantMessage message = (AssistantMessage) msg;
                AgiAgentChatMessageDO agiAgentChatMessageDO = AgiAgentChatMessageDO.createByAssistantMessage(message,
                        agiAgentChatDO.getId(),
                        agiAgentChatDO.getAgiAgentId(),
                        conversationId,
                        agiAgentChatDO.getUserId());
                AgiAgentChatMessageDO agiAgentChatMessageDOAdded = agiAgentChatMessageService.add(agiAgentChatMessageDO);
                // ToolCall
                List<AssistantMessage.ToolCall> toolCalls = message.getToolCalls();
                if (CollectionUtil.isNotEmpty(toolCalls)) {
                    for (AssistantMessage.ToolCall toolCall : toolCalls) {
                        AgiAgentChatMessageToolcallDO agiAgentChatMessageToolcallDO = AgiAgentChatMessageToolcallDO.createByToolCall(
                                toolCall,
                                agiAgentChatMessageDOAdded.getId()
                        );
                        agiAgentChatMessageToolcallService.add(agiAgentChatMessageToolcallDO);
                    }
                }
                // Media
                List<Media> medias = message.getMedia();
                if (CollectionUtil.isNotEmpty(medias)) {
                    for (Media media : medias) {
                        AgiAgentChatMessageMediaDO agiAgentChatMessageMediaDO = AgiAgentChatMessageMediaDO.createByMedia(
                                media,
                                agiAgentChatMessageDOAdded.getId()
                        );
                        agiAgentChatMessageMediaService.add(agiAgentChatMessageMediaDO);
                    }
                }

            }
            // SYSTEM
            else if (MessageType.SYSTEM == msg.getMessageType()) {
                SystemMessage message = (SystemMessage) msg;

                AgiAgentChatMessageDO agiAgentChatMessageDO = AgiAgentChatMessageDO.createBySystemMessage(
                        message,
                        agiAgentChatDO.getId(),
                        agiAgentChatDO.getAgiAgentId(),
                        conversationId,
                        agiAgentChatDO.getUserId()
                        );
                agiAgentChatMessageService.add(agiAgentChatMessageDO);
            }
            // TOOL
            else if (MessageType.TOOL == msg.getMessageType()) {
                ToolResponseMessage message = (ToolResponseMessage) msg;
                AgiAgentChatMessageDO agiAgentChatMessageDO = AgiAgentChatMessageDO.createByToolResponseMessage(
                        message,
                        agiAgentChatDO.getId(),
                        agiAgentChatDO.getAgiAgentId(),
                        conversationId,
                        agiAgentChatDO.getUserId()
                );
                AgiAgentChatMessageDO agiAgentChatMessageDOAdded = agiAgentChatMessageService.add(agiAgentChatMessageDO);
                // ToolResponse
                List<ToolResponseMessage.ToolResponse> responses = message.getResponses();
                if (CollectionUtil.isNotEmpty(responses)) {
                    for (ToolResponseMessage.ToolResponse response : responses) {
                        AgiAgentChatMessageToolDO agiAgentChatMessageToolDO = AgiAgentChatMessageToolDO.createByToolResponse(
                                response,
                                agiAgentChatMessageDOAdded.getId()
                        );
                        agiAgentChatMessageToolService.add(agiAgentChatMessageToolDO);
                    }
                }
            }
        }
    }

    @Override
    public List<Message> get(String conversationId, int lastN) {
        Page page = new Page<>();
        page.setCurrent(0);
        page.setSize(lastN);
        page.setSearchCount(false);
        LambdaQueryWrapper<AgiAgentChatMessageDO> lambdaQueryWrapper = Wrappers.<AgiAgentChatMessageDO>lambdaQuery()
                .eq(AgiAgentChatMessageDO::getChatId, conversationId)
                .orderByDesc(AgiAgentChatMessageDO::getCreateAt);
        Page<AgiAgentChatMessageDO> pageResult = agiAgentChatMessageService.page(page, lambdaQueryWrapper);
        return pageResult.getRecords().stream()
                .map(aDo -> agiAgentChatMessageDOToMessage(aDo)).collect(Collectors.toList());
    }

    @Override
    public void clear(String conversationId) {
        // 这会自动级联删除消息下的媒体
        agiAgentChatMessageService.deleteByColumn(conversationId, AgiAgentChatMessageDO::getChatId);

    }

    /**
     * 映射 数据库消息为spring ai消息
     * @param agiAgentChatMessageDO
     * @return
     */
    private Message agiAgentChatMessageDOToMessage(AgiAgentChatMessageDO agiAgentChatMessageDO) {


        if (MessageType.SYSTEM.getValue().equals(agiAgentChatMessageDO.getMessageType())) {
            SystemMessage systemMessage = new SystemMessage(
                    agiAgentChatMessageDO.getContent()
            );
            return systemMessage;
        }
        // metadata
        Map<String, Object> metadata = strToMap(agiAgentChatMessageDO.getMetadataJson());

        if (MessageType.TOOL.getValue().equals(agiAgentChatMessageDO.getMessageType())) {
            List<ToolResponseMessage.ToolResponse> toolResponses = new ArrayList<>();
            List<AgiAgentChatMessageToolDO> agiAgentChatMessageToolDOS = agiAgentChatMessageToolService.getByAgiAgentChatMessageId(agiAgentChatMessageDO.getId());
            for (AgiAgentChatMessageToolDO agiAgentChatMessageToolDO : agiAgentChatMessageToolDOS) {
                ToolResponseMessage.ToolResponse tool = new ToolResponseMessage.ToolResponse(
                        agiAgentChatMessageToolDO.getUniqueId(),
                        agiAgentChatMessageToolDO.getName(),
                        agiAgentChatMessageToolDO.getData()
                );
                toolResponses.add(tool);
            }
            ToolResponseMessage toolResponseMessage = new ToolResponseMessage(
                    toolResponses,
                    metadata);
            return toolResponseMessage;
        }

        // medias
        List<AgiAgentChatMessageMediaDO> agiAgentChatMessageMediaDOList = agiAgentChatMessageMediaService.getByAgiAgentChatMessageId(agiAgentChatMessageDO.getId());
        List<Media> medias = new ArrayList<>();
        for (AgiAgentChatMessageMediaDO agiAgentChatMessageMediaDO : agiAgentChatMessageMediaDOList) {
            Media media = Media.builder()
                    .id(agiAgentChatMessageMediaDO.getUniqueId())
                    .name(agiAgentChatMessageMediaDO.getName())
                    .mimeType(MimeType.valueOf(agiAgentChatMessageMediaDO.getMimeType()))
                    .data(agiAgentChatMessageMediaDO.getUrl())
                    .build();
            medias.add(media);
        }

        if (MessageType.USER.getValue().equals(agiAgentChatMessageDO.getMessageType())) {
            UserMessage userMessage = new UserMessage(
                    agiAgentChatMessageDO.getContent(),
                    medias,metadata);
            return userMessage;

        }else if (MessageType.ASSISTANT.getValue().equals(agiAgentChatMessageDO.getMessageType())) {
            List<AssistantMessage.ToolCall> toolCalls = new ArrayList<>();
            List<AgiAgentChatMessageToolcallDO> agiAgentChatMessageToolcallDOS = agiAgentChatMessageToolcallService.getByAgiAgentChatMessageId(agiAgentChatMessageDO.getId());
            for (AgiAgentChatMessageToolcallDO agiAgentChatMessageToolcallDO : agiAgentChatMessageToolcallDOS) {
                AssistantMessage.ToolCall toolCall = new AssistantMessage.ToolCall(
                        agiAgentChatMessageToolcallDO.getUniqueId(),
                        agiAgentChatMessageToolcallDO.getName(),
                        agiAgentChatMessageToolcallDO.getType(),
                        agiAgentChatMessageToolcallDO.getArguments()
                );
                toolCalls.add(toolCall);
            }


            AssistantMessage assistantMessage = new AssistantMessage(
                    agiAgentChatMessageDO.getContent(),
                    metadata,
                    toolCalls,
                    medias);
            return assistantMessage;
        }
        throw new IllegalArgumentException("不支持的消息类型");
    }

    /**
     * 将json字符串转换为map
     * @param str
     * @return
     */
    private Map<String, Object> strToMap(String str) {
        if (StrUtil.isEmpty(str)) {
            return Collections.emptyMap();
        }
        return JSONUtil.toBean(str, Map.class);
    }

    @Autowired
    public void setAgiAgentChatService(IAgiAgentChatService agiAgentChatService) {
        this.agiAgentChatService = agiAgentChatService;
    }
    @Autowired
    public void setAgiAgentChatMessageService(IAgiAgentChatMessageService agiAgentChatMessageService) {
        this.agiAgentChatMessageService = agiAgentChatMessageService;
    }
    @Autowired
    public void setAgiAgentChatMessageMediaService(IAgiAgentChatMessageMediaService agiAgentChatMessageMediaService) {
        this.agiAgentChatMessageMediaService = agiAgentChatMessageMediaService;
    }
    @Autowired
    public void setAgiAgentChatMessageToolcallService(IAgiAgentChatMessageToolcallService agiAgentChatMessageToolcallService) {
        this.agiAgentChatMessageToolcallService = agiAgentChatMessageToolcallService;
    }
    @Autowired
    public void setAgiAgentChatMessageToolService(IAgiAgentChatMessageToolService agiAgentChatMessageToolService) {
        this.agiAgentChatMessageToolService = agiAgentChatMessageToolService;
    }
}
