package com.particle.agi.client.agent.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 智能体 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
@Data
@Schema
public class AgiAgentQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "智能体名称")
    private String name;





    @Schema(description = "是否使用开场白")
    private Boolean isUsePrologue;




    @Schema(description = "是否自动生成开场白开场文案")
    private Boolean isAutoPrologue;


    @Schema(description = "是否使用自动追问")
    private Boolean isUseAutoAsk;





    @Schema(description = "是否使用联网搜索")
    private Boolean isUseOnlineSearch;


    @Schema(description = "是否使用知识库")
    private Boolean isUseKnowledgeBase;


    @Schema(description = "知识库id")
    private Long agiKnowledgeBaseId;


    @Schema(description = "是否使用mcp")
    private Boolean isUseMcp;



    @Schema(description = "是否使用长期记忆")
    private Boolean isUseLongTermMemory;


    @Schema(description = "是否使用声音")
    private Boolean isUseVoice;














}
