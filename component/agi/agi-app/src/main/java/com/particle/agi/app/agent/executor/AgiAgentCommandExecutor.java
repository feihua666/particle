package com.particle.agi.app.agent.executor;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.agi.client.agent.dto.command.AgiAgentChatCommand;
import com.particle.agi.client.agent.dto.data.*;
import com.particle.agi.domain.agent.AgiAgent;
import com.particle.agi.domain.agent.AgiAgentId;
import com.particle.agi.domain.agent.gateway.AgiAgentGateway;
import com.particle.agi.domain.agent.value.*;
import com.particle.agi.domain.values.AgiMedia;
import com.particle.agi.infrastructure.agent.service.IAgiAgentService;
import com.particle.agi.infrastructure.agent.dos.AgiAgentDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 智能体 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
@Component
@Validated
public class AgiAgentCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentGateway agiAgentGateway;
	private IAgiAgentService iAgiAgentService;

	/**
	 * 智能体对话
	 * @param agiAgentChatCommand
	 * @return
	 */
	public Flux<SingleResponse<AgiAgentChatResponseVO>> chatStream(@Valid AgiAgentChatCommand agiAgentChatCommand) {
		AgiAgentChatParam agiAgentChatParam = AgiAgentChatParam.create(agiAgentChatCommand.getAgiAgentId()
		,agiAgentChatCommand.getChatId(),agiAgentChatCommand.getMessage());

		Flux<AgiAgentChatResultDTO> chat = agiAgentGateway.chatStream(agiAgentChatParam);

		// 映射
		Flux<AgiAgentChatResponseVO> chatWrap = chat.map(item -> agiAgentChatResultDTOToAgiAgentChatResponseVO(item));
		// 映射
		Flux<SingleResponse<AgiAgentChatResponseVO>> singleResponseFlux = chatWrap.map(item -> SingleResponse.of(item));
		return singleResponseFlux;
	}

	/**
	 * 映射 {@link AgiAgentChatResultDTO} to {@link AgiAgentChatResponseVO}
	 * @param agiAgentChatResultDTO
	 * @return
	 */
	private AgiAgentChatResponseVO agiAgentChatResultDTOToAgiAgentChatResponseVO(AgiAgentChatResultDTO agiAgentChatResultDTO) {
		// metadata
		AgiAgentChatResultMetadataDTO metadataDTO = agiAgentChatResultDTO.getMetadata();
		AgiAgentChatResponseMetadataVO agiAgentChatResponseMetadataVO = null;
		if (metadataDTO != null) {
			// rateLimit
			AgiAgentChatResponseMetadataVO.RateLimitVO rateLimitVO = null;
			AgiAgentChatResultMetadataDTO.RateLimitDTO rateLimitDTO = metadataDTO.getRateLimit();
			if (rateLimitDTO != null) {
				rateLimitVO = AgiAgentChatResponseMetadataVO.RateLimitVO.create(
						rateLimitDTO.getRequestsLimit(),
						rateLimitDTO.getRequestsRemaining(),
						rateLimitDTO.getRequestsReset(),
						rateLimitDTO.getTokensLimit(),
						rateLimitDTO.getTokensRemaining(),
						rateLimitDTO.getTokensReset()
				);
			}
			// usage
			AgiAgentChatResponseMetadataVO.UsageVO usageVO = null;
			AgiAgentChatResultMetadataDTO.UsageDTO usageDTO = metadataDTO.getUsage();
			if (usageDTO != null) {
				usageVO = AgiAgentChatResponseMetadataVO.UsageVO.create(
						usageDTO.getPromptTokens(),
						usageDTO.getCompletionTokens(),
						usageDTO.getTotalTokens(),
						usageDTO.getNativeUsage()
				);
			}

			agiAgentChatResponseMetadataVO = AgiAgentChatResponseMetadataVO.create(
					metadataDTO.getId(),
					metadataDTO.getModel(),
					rateLimitVO,
					usageVO
			);
		}

		// results
		List<AgiAgentChatResultContentDTO> resultContentDTOS = agiAgentChatResultDTO.getResults();
		List<AgiAgentChatResponseContentVO> agiAgentChatResponseContentVOs = null;
        if (CollectionUtil.isNotEmpty(resultContentDTOS)) {
			agiAgentChatResponseContentVOs = new ArrayList<>();
			for (AgiAgentChatResultContentDTO resultContentDTO : resultContentDTOS) {
				// metadata
				AgiAgentChatResponseContentVO.ChatGenerationMetadataVO chatGenerationMetadataVO = null;
				AgiAgentChatResultContentDTO.ChatGenerationMetadataDTO chatGenerationMetadataDTO = resultContentDTO.getMetadata();
                if (chatGenerationMetadataDTO != null) {
					chatGenerationMetadataVO = AgiAgentChatResponseContentVO.ChatGenerationMetadataVO.create(
							chatGenerationMetadataDTO.getFinishReason(),
							chatGenerationMetadataDTO.getContentFilters(),
							chatGenerationMetadataDTO.getMetadata()
					);
				}
				// output
				AgiAgentChatAssistantMessageVO agiAgentChatAssistantMessageVO = null;
				AgiAgentChatAssistantMessageDTO outputDTO = resultContentDTO.getOutput();
				if (outputDTO != null) {
					// toolcalls
					List<AgiAgentChatToolCallVO> agiAgentChatToolCallVOS = null;
					List<AgiAgentChatToolCallDTO> agiAgentChatToolCallDTOList = outputDTO.getToolCalls();
                    if (CollectionUtil.isNotEmpty(agiAgentChatToolCallDTOList)) {
						agiAgentChatToolCallVOS = new ArrayList<>();
						for (AgiAgentChatToolCallDTO agiAgentChatToolCallDTO : agiAgentChatToolCallDTOList) {
							AgiAgentChatToolCallVO agiAgentChatToolCallVO = AgiAgentChatToolCallVO.create(
									agiAgentChatToolCallDTO.getId(),
									agiAgentChatToolCallDTO.getType(),
									agiAgentChatToolCallDTO.getName(),
									agiAgentChatToolCallDTO.getArguments()
							);
							agiAgentChatToolCallVOS.add(agiAgentChatToolCallVO);
						}
                    }
					// medias
					List<AgiAgentChatMediaVO> agiAgentChatMediaVOS = null;
					List<AgiMedia> agiAgentChatMediaDTOList = outputDTO.getMedias();
					if (CollectionUtil.isNotEmpty(agiAgentChatMediaDTOList)) {
						agiAgentChatMediaVOS = new ArrayList<>();
						for (AgiMedia agiAgentChatMediaDTO : agiAgentChatMediaDTOList) {
							AgiAgentChatMediaVO agiAgentChatMediaVO = AgiAgentChatMediaVO.create(
									agiAgentChatMediaDTO.getId(),
									agiAgentChatMediaDTO.getMimeType(),
									agiAgentChatMediaDTO.getData(),
									agiAgentChatMediaDTO.getName()
							);
							agiAgentChatMediaVOS.add(agiAgentChatMediaVO);
						}

					}
					agiAgentChatAssistantMessageVO = AgiAgentChatAssistantMessageVO.create(
							outputDTO.getMessageType(),
							outputDTO.getText(),
							outputDTO.getMetadata(),
							agiAgentChatToolCallVOS,
							agiAgentChatMediaVOS
					);
					AgiAgentChatResponseContentVO agiAgentChatResponseContentVO = AgiAgentChatResponseContentVO.create(
							agiAgentChatAssistantMessageVO,
							chatGenerationMetadataVO
					);
					agiAgentChatResponseContentVOs.add(agiAgentChatResponseContentVO);
				}
			}
        }

		AgiAgentChatResponseVO agiAgentChatResponseVO = AgiAgentChatResponseVO.create(agiAgentChatResponseMetadataVO,agiAgentChatResponseContentVOs);
		return agiAgentChatResponseVO;
	}

	/**
	 * 注入使用set方法
	 * @param agiAgentGateway
	 */
	@Autowired
	public void setAgiAgentGateway(AgiAgentGateway agiAgentGateway) {
		this.agiAgentGateway = agiAgentGateway;
	}
	@Autowired
	public void setIAgiAgentService(IAgiAgentService iAgiAgentService) {
		this.iAgiAgentService = iAgiAgentService;
	}
}
