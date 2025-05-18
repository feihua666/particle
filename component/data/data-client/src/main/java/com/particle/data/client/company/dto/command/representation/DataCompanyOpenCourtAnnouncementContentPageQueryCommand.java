package com.particle.data.client.company.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业开庭公告内容 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:18
 */
@Data
@Schema
public class DataCompanyOpenCourtAnnouncementContentPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "企业开庭公告id")
    private Long companyOpenCourtAnnouncementId;


    @Schema(description = "公告内容")
    private String content;

	@Schema(description = "数据md5,content")
	private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}