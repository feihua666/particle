package com.particle.global.ai.endpoint;

import com.particle.global.ai.endpoint.command.ChatCommand;
import com.particle.global.ai.endpoint.command.EmbeddingAndStoreCommand;
import com.particle.global.ai.endpoint.command.EmbeddingCommand;
import com.particle.global.ai.endpoint.command.SimilaritySearchCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * <p>
 * 嵌入模型相关服务
 * </p>
 *
 * @author yangwei
 * @since 2024-12-31 17:03:38
 */
@Tag(name = "全局服务ai相关接口")
@RestController
@RequestMapping("/ai")
public class AiController {

	@Autowired
	private EmbeddingModel embeddingModel;
	@Autowired
	private VectorStore vectorStore;

	@Autowired
	private ChatModel chatModel;
	/**
	 * 嵌入
	 * @param embeddingCommand
	 * @return
	 */
	@PreAuthorize("hasAuthority('ai:embedding')")
	@Operation(summary = "嵌入")
	@PostMapping("/embedding")
	public SingleResponse<EmbeddingResponse> embedding(@RequestBody @Valid EmbeddingCommand embeddingCommand) {
		// 使用 postgresml 时，需要切换数据源
		// DatasourceTool.changeDatasource(GlobalAiAutoConfiguration.pgDatasourceName);
        EmbeddingResponse embeddingResponse;
        try {
            embeddingResponse = this.embeddingModel.embedForResponse(List.of(embeddingCommand.getMessage()));
        } finally {
			// 使用 postgresml 时，需要切换数据源
			// DatasourceTool.restoreDatasource(GlobalAiAutoConfiguration.pgDatasourceName);
        }
        return SingleResponse.of(embeddingResponse);
	}
	/**
	 * 嵌入并存储
	 * @param embeddingAndStoreCommand
	 * @return
	 */
	@PreAuthorize("hasAuthority('ai:embeddingAndStore')")
	@Operation(summary = "嵌入并存储")
	@PostMapping("/embeddingAndStore")
	public Response embeddingAndStore(@RequestBody @Valid EmbeddingAndStoreCommand embeddingAndStoreCommand) {
		List<Document> documents = List.of(new Document(embeddingAndStoreCommand.getMessage()));
		vectorStore.add(documents);
		return Response.buildSuccess();
	}
	/**
	 * 嵌入
	 * @param similaritySearchCommand
	 * @return
	 */
	@PreAuthorize("hasAuthority('ai:similaritySearch')")
	@Operation(summary = "相似性搜索")
	@PostMapping("/similaritySearch")
	public MultiResponse<Document> similaritySearch(@RequestBody @Valid SimilaritySearchCommand similaritySearchCommand) {
		SearchRequest.Builder query = SearchRequest.builder().query(similaritySearchCommand.getMessage());
        if (similaritySearchCommand.getTopK() != null) {
			query.topK(similaritySearchCommand.getTopK());
        }
		List<Document> results = vectorStore.similaritySearch(query.build());
        return MultiResponse.of(results);
	}

	/**
	 * 对话聊天
	 * @param chatCommand
	 * @return
	 */
	@PreAuthorize("hasAuthority('ai:chat')")
	@Operation(summary = "对话聊天")
	@PostMapping("/chat")
	public SingleResponse<String> chat(@RequestBody @Valid ChatCommand chatCommand) {
		String result = this.chatModel.call(chatCommand.getMessage());
		return SingleResponse.of(result);
	}

	/**
	 * 流式对话聊天
	 * @param chatCommand
	 * @return
	 */
	@PreAuthorize("hasAuthority('ai:streamChat')")
	@Operation(summary = "流式对话聊天")
	@PostMapping("/streamChat")
	public Flux<ChatResponse> streamChat(@RequestBody @Valid ChatCommand chatCommand) {
		Prompt prompt = new Prompt(new UserMessage(chatCommand.getMessage()));
		Flux<ChatResponse> stream = this.chatModel.stream(prompt);
		return stream;
	}
}
