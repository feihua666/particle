package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业裁判文书内容 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:53
 */
@Data
@Schema
public class DataCompanyJudgmentDocumentContentQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "裁判文书表id")
    private Long companyJudgmentDocumentId;


    @Schema(description = "裁判内容")
    private String content;

	@Schema(description = "数据md5,content")
	private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}